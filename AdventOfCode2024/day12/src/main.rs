use std::fs;
use std::cmp;
use std::collections::HashMap;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

// sides use flags : 1 North has a fence, 2 East has a fence, 4 South has a fence, 8 West has a fence
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
        res_sides += 8;
    }

    if (more_x, start_pos.1) != start_pos && (matrix[more_x][start_pos.1] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((more_x, start_pos.1));
    } else {
        res_sides += 2;
    }

    if (start_pos.0, less_y) != start_pos && (matrix[start_pos.0][less_y] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((start_pos.0, less_y));
    } else {
        res_sides += 1;
    }

    if (start_pos.0, more_y) != start_pos && (matrix[start_pos.0][more_y] == matrix[start_pos.0][start_pos.1]) {
        res_vec.push((start_pos.0, more_y));
    } else {
        res_sides += 4;
    }

    (res_vec, res_sides)
}

fn fill_region_p1(matrix: &Vec<Vec<char>>, seed: (usize, usize), map: &mut HashMap<(usize, usize), usize>) {
    let (neighbours, _free_sides) = get_valid_neighbour_coords_and_sides(matrix, seed);
    map.insert(seed, 4 - neighbours.len());
    neighbours.iter().for_each(|neighbour| if !map.contains_key(neighbour) {fill_region_p1(matrix, *neighbour, map)});
}

fn fill_region_p2(matrix: &Vec<Vec<char>>, seed: (usize, usize), map: &mut HashMap<(usize, usize), u8>) -> usize {
    // returns the number of new sides
    println!("{}", matrix[seed.0][seed.1]);
    let (neighbours, fenced_sides) = get_valid_neighbour_coords_and_sides(matrix, seed);
    map.insert(seed, fenced_sides);
    neighbours.iter().for_each(|neighbour| if !map.contains_key(neighbour) {fill_region_p2(matrix, *neighbour, map);});
    println!("{}", neighbours.len());
    let mut newly_fenced_sides = fenced_sides;
    for n in neighbours {
        let neighbour_sides = map.get(&n).unwrap_or(&0);
        newly_fenced_sides = newly_fenced_sides&!neighbour_sides;
    }
    fenced_sides.count_ones() as usize
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
    let contents = fs::read_to_string("res/exemple1")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut regions: Vec<HashMap<(usize, usize), u8>> = vec![];
    let mut res = 0;
    for i in 0..matrix.len() {
        for j in 0..matrix[0].len() {
            if regions.iter().all(|region| !region.contains_key(&(i, j))) {
                let mut region: HashMap<(usize, usize), u8> = HashMap::new();
                let sides = fill_region_p2(&matrix, (i, j), &mut region);
                res += sides * region.len();
                regions.push(region);
            }
        }
    }
    res
}