use std::fs;
use std::cmp;
use std::collections::HashMap;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn get_valid_neighbour_coords(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> Vec<(usize, usize)> {
    let less_x = start_pos.0.saturating_sub(1);
    let less_y = start_pos.1.saturating_sub(1);
    let more_x = cmp::min(start_pos.0 + 1, matrix.len() - 1);
    let more_y = cmp::min(start_pos.1 + 1, matrix[0].len() - 1);
    let mut res = vec![];
    if (less_x, start_pos.1) != start_pos && (matrix[less_x][start_pos.1] == matrix[start_pos.0][start_pos.1]) {
        res.push((less_x, start_pos.1));
    }
    if (more_x, start_pos.1) != start_pos && (matrix[more_x][start_pos.1] == matrix[start_pos.0][start_pos.1]) {
        res.push((more_x, start_pos.1));
    }
    if (start_pos.0, less_y) != start_pos && (matrix[start_pos.0][less_y] == matrix[start_pos.0][start_pos.1]) {
        res.push((start_pos.0, less_y));
    }
    if (start_pos.0, more_y) != start_pos && (matrix[start_pos.0][more_y] == matrix[start_pos.0][start_pos.1]) {
        res.push((start_pos.0, more_y));
    }
    res
}

fn fill_region(matrix: &Vec<Vec<char>>, seed: (usize, usize), map: &mut HashMap<(usize, usize), usize>) {
    let neighbours = get_valid_neighbour_coords(matrix, seed);
    map.insert(seed, 4 - neighbours.len());
    neighbours.iter().for_each(|neighbour| if !map.contains_key(neighbour) {fill_region(matrix, *neighbour, map)});
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
                fill_region(&matrix, (i, j), &mut region);
                regions.push(region);
            }
        }
    }
    regions.into_iter().fold(0, |sum, region| sum + (region.len() * region.values().sum::<usize>()))
}

fn part2() -> usize {
    0
}