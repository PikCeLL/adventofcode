use std::fs;
use std::collections::HashMap;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn next_dir(dir: (isize, isize)) -> (isize, isize) {
    // Could probably be done in a smarter way
    return match dir {
        (-1,0) => (0,1),
        (0,1) => (1,0),
        (1,0) => (0,-1),
        (0,-1) => (-1,0),
        _ => (0,0),
    }; 
}

fn guard_patrol(init_guard_pos: (isize, isize), matrix: &Vec<Vec<char>>, extra_block: (isize, isize)) -> (HashMap<(isize, isize), Vec<(isize, isize)>>, bool) {
    // At first the guard is looking North
    let mut dir: (isize, isize) = (-1, 0);
    let mut guard_pos = init_guard_pos;
    let mut visited_places: HashMap<(isize, isize), Vec<(isize, isize)>> = HashMap::new();
    let matrix_width = matrix[0].len() as isize;
    let matrix_height = matrix.len() as isize;
    while (0..matrix_height).contains(&guard_pos.0) && (0..matrix_width).contains(&guard_pos.1) {
        if matrix[guard_pos.0 as usize][guard_pos.1 as usize] == '#' || (guard_pos == extra_block) {
            guard_pos = (guard_pos.0 - dir.0, guard_pos.1 - dir.1);
            dir = next_dir(dir);
        } else {
            if let Some(x) = visited_places.get_mut(&guard_pos) {
                if x.contains(&dir) {
                    return (visited_places, true);
                } else {
                    x.push(dir);
                }
            } else {
                visited_places.insert(guard_pos, vec![dir]);
            }
            guard_pos = (guard_pos.0 + dir.0, guard_pos.1 + dir.1);
        }
    }
    return (visited_places, false);
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut guard_pos: (isize, isize) = (0, 0);
    let matrix_width = matrix[0].len() as isize;
    let matrix_height = matrix.len() as isize;
    for i in 0..matrix_height {
        for j in 0..matrix_width {
            if matrix[i as usize][j as usize] == '^' {
                guard_pos = (i,j);
            }
        }
    }
    return guard_patrol(guard_pos, &matrix, (-1, -1)).0.len() as u32;
}

fn part2() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut guard_pos: (isize, isize) = (0, 0);
    let matrix_width = matrix[0].len() as isize;
    let matrix_height = matrix.len() as isize;
    for i in 0..matrix_height {
        for j in 0..matrix_width {
            if matrix[i as usize][j as usize] == '^' {
                guard_pos = (i,j);
            }
        }
    }
    return guard_patrol(guard_pos, &matrix, (-1, -1)).0.iter().filter(|block_pos| guard_patrol(guard_pos, &matrix, *block_pos.0).1).count() as u32;
}