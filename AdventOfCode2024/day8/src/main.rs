use std::fs;
use std::collections::HashMap;
use std::collections::HashSet;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let matrix_height = matrix.len();
    let matrix_width = matrix[0].len();
    let mut antennas_map = HashMap::<char, Vec<(i32, i32)>>::new();
    for i in 0..matrix_height {
        for j in 0..matrix_width {
            if matrix[i][j] != '.' {
                if let Some(x) = antennas_map.get_mut(&matrix[i][j]) {
                    x.push((i as i32,j as i32));
                } else {
                    antennas_map.insert(matrix[i][j], vec![(i as i32,j as i32)]);
                }
            }
        }
    }
    return antennas_map.iter().fold(HashSet::new(), |mut locations, antennas_list| {
        let list_len = antennas_list.1.len();
        for i in 0..list_len {
            for j in 0..list_len {
                if i == j {continue;}
                let antinode = (2 * antennas_list.1[i].0 - antennas_list.1[j].0, 2 * antennas_list.1[i].1 - antennas_list.1[j].1);
                if (0i32..matrix_width as i32).contains(&antinode.0)
                && (0i32..matrix_height as i32).contains(&antinode.1) {
                    locations.insert(antinode);
                }
            }
        }
        locations
    }).len() as u32;
}

fn part2() -> u32 {
    return 0;
}