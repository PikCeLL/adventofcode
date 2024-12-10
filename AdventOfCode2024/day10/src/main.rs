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
    if (less_x, start_pos.1) != start_pos && ((matrix[less_x][start_pos.1] as u8).saturating_sub(matrix[start_pos.0][start_pos.1] as u8) == 1) {
        res.push((less_x, start_pos.1));
    }
    if (more_x, start_pos.1) != start_pos && ((matrix[more_x][start_pos.1] as u8).saturating_sub(matrix[start_pos.0][start_pos.1] as u8) == 1) {
        res.push((more_x, start_pos.1));
    }
    if (start_pos.0, less_y) != start_pos && ((matrix[start_pos.0][less_y] as u8).saturating_sub(matrix[start_pos.0][start_pos.1] as u8) == 1) {
        res.push((start_pos.0, less_y));
    }
    if (start_pos.0, more_y) != start_pos && ((matrix[start_pos.0][more_y] as u8).saturating_sub(matrix[start_pos.0][start_pos.1] as u8) == 1) {
        res.push((start_pos.0, more_y));
    }
    res
}

fn get_9th_neightbours_count1(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> HashSet<(usize, usize)> {
    if matrix[start_pos.0][start_pos.1] == '9' {
        return HashSet::from([start_pos]);
    } else {
        return get_valid_neighbour_coords(matrix, start_pos).iter().map(|pos| get_9th_neightbours_count1(matrix, *pos)).reduce(|mut full_set, set| {full_set.extend(set.into_iter()); full_set}).unwrap_or_else(|| HashSet::new());
    }
}

fn count_paths1(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> u32 {
    get_valid_neighbour_coords(matrix, start_pos).iter()
    .map(|neightbour| get_9th_neightbours_count1(matrix, *neightbour))
    .reduce(|mut full_set, set| {full_set.extend(set.into_iter()); full_set})
    .expect("({start_pos.0},{start_pos.1}) doesn't have any path ?").len() as u32
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut sum = 0;
    for i in 0..matrix.len() {
        for j in 0..matrix[0].len() {
            if matrix[i][j] == '0' {
                sum += count_paths1(&matrix, (i, j));
            }
        }
    }
    sum
}

fn get_9th_neightbours_count2(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> usize {
    if matrix[start_pos.0][start_pos.1] == '9' {
        return 1;
    } else {
        return get_valid_neighbour_coords(matrix, start_pos).iter().map(|pos| get_9th_neightbours_count2(matrix, *pos)).reduce(|sum, count| sum + count).unwrap_or(0);
    }
}

fn count_paths2(matrix: &Vec<Vec<char>>, start_pos: (usize, usize)) -> u32 {
    get_valid_neighbour_coords(matrix, start_pos).iter()
    .map(|neightbour| get_9th_neightbours_count2(matrix, *neightbour))
    .reduce(|sum, count| sum + count)
    .expect("({start_pos.0},{start_pos.1}) doesn't have any path ?") as u32
}

fn part2() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut sum = 0;
    for i in 0..matrix.len() {
        for j in 0..matrix[0].len() {
            if matrix[i][j] == '0' {
                sum += count_paths2(&matrix, (i, j));
            }
        }
    }
    sum
}