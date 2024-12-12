use std::fs;
use std::cmp;
use std::collections::HashSet;

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

fn get_region(matrix: &Vec<Vec<char>>, seed: (usize, usize)) -> HashSet<(usize, usize, usize)> {
    get_valid_neighbour_coords(matrix, start_pos).iter()
    .map(|neightbour| get_9th_neightbours_count1(matrix, *neightbour))
    .reduce(|mut full_set, set| {full_set.extend(set.into_iter()); full_set})
    .expect("({start_pos.0},{start_pos.1}) doesn't have any path ?").len() as u32
}

fn part1() -> usize {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    0
}

fn part2() -> usize {
    0
}