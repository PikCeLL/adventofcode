use std::fs;
use std::cmp;
use std::collections::HashMap;
use std::collections::HashSet;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn get_valid_neighbour_coords_and_sides(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> (Vec<(usize, usize)>, u8) {
    let less_x = start_pos.0.saturating_sub(1);
    let less_y = start_pos.1.saturating_sub(1);
    let more_x = cmp::min(start_pos.0 + 1, matrix.len() - 1);
    let more_y = cmp::min(start_pos.1 + 1, matrix[0].len() - 1);
    let mut res_vec = vec![];
    let mut res_sides: u8 = 0;
    if (less_x, start_pos.1) != start_pos && (matrix[less_x][start_pos.1] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((less_x, start_pos.1));
    } else {
        res_sides += 1;
    }

    if (more_x, start_pos.1) != start_pos && (matrix[more_x][start_pos.1] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((more_x, start_pos.1));
    } else {
        res_sides += 2;
    }

    if (start_pos.0, less_y) != start_pos && (matrix[start_pos.0][less_y] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((start_pos.0, less_y));
    } else {
        res_sides += 4;
    }

    if (start_pos.0, more_y) != start_pos && (matrix[start_pos.0][more_y] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((start_pos.0, more_y));
    } else {
        res_sides += 8;
    }

    (res_vec, res_sides)
}

fn fill_region_p1(matrix: &Vec<Vec<char>>, seed: (usize, usize), map: &mut HashMap<(usize, usize), usize>) {
    let (neighbours, _free_sides) = get_valid_neighbour_coords_and_sides(matrix, seed);
    map.insert(seed, 4 - neighbours.len());
    neighbours.iter().for_each(|neighbour| if !map.contains_key(neighbour) {fill_region_p1(matrix, *neighbour, map)});
}

fn get_combined_sides(fencing: Vec<u8>) -> u8 {
    let mut ret = 0;
    for i in 0..4 {
        if fencing.iter().map(|fences| (fences&(2u8.pow(i))).count_ones()).sum::<u32>() > 1 {
            ret += 2u8.pow(i);
        }
    }
    ret
}

fn get_region_p2(matrix: &Vec<Vec<char>>, seed: (usize, usize)) -> (HashMap<(usize, usize), u8>, isize) {
    // Exemple6 exhibits a limitation of the pure glutonous algorithm : a side can be counted twice (or more) if it is reached on the same generation by non-contiguous plots. Result should be 120
    let mut current_gen = HashSet::from([seed.clone()]);
    let mut region: HashMap<(usize, usize), u8> = HashMap::new();
    let mut sides = 0;
    loop {
        let mut next_gen: HashSet<(usize, usize)> = HashSet::new();
        for plot in &current_gen {
            let (neighbours, fenced_sides) = get_valid_neighbour_coords_and_sides(matrix, *plot);
            let mut newly_fenced_sides = fenced_sides;
            let mut fencing:Vec<u8> = Vec::new();
            for n in &neighbours {
                let neighbour_sides = region.get(&n).unwrap_or(&0);
                newly_fenced_sides = newly_fenced_sides&!neighbour_sides;
                fencing.push(*neighbour_sides);
            }
            let comb = get_combined_sides(fencing);
            sides += newly_fenced_sides.count_ones() as isize - (fenced_sides&comb).count_ones() as isize;
            region.insert(*plot, fenced_sides);
            for nei_plot in neighbours {
                if !region.contains_key(&nei_plot) {
                    next_gen.insert(nei_plot);
                }
            }
        }
        current_gen = next_gen;
        if current_gen.is_empty() {
            break;
        }
    }
    (region, sides)
}

fn part1() -> usize {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut regions: Vec<HashMap<(usize, usize), usize>> = vec![];
    for i in 0..matrix.len() {
        for j in 0..matrix[0].len() {
            if regions.iter().all(|region| !region.contains_key(&(i, j))) {
                let mut region: HashMap<(usize, usize), usize> = HashMap::new();
                fill_region_p1(&matrix, (i, j), &mut region);
                regions.push(region);
            }
        }
    }
    regions.into_iter().fold(0, |sum, region| sum + (region.len() * region.values().sum::<usize>()))
}
 
fn part2() -> usize {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut regions: Vec<HashMap<(usize, usize), u8>> = vec![];
    let mut res = 0;
    for i in 0..matrix.len() {
        for j in 0..matrix[0].len() {
            if regions.iter().all(|region| !region.contains_key(&(i, j))) {
                let (region, sides) = get_region_p2(&matrix, (i, j));
                res += &region.len() * sides as usize;
                regions.push(region);
            }
        }
    }
    res
}