use std::fs;
use std::collections::HashSet;

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
    // At first the guard is looking North
    let mut dir: (isize, isize) = (-1, 0);
    let mut visited_places = HashSet::new();
    while (0..matrix_height).contains(&guard_pos.0) && (0..matrix_width).contains(&guard_pos.1) {
        if matrix[guard_pos.0 as usize][guard_pos.1 as usize] == '#' {
            guard_pos = (guard_pos.0 - dir.0, guard_pos.1 - dir.1);
            dir = next_dir(dir);
        } else {
            visited_places.insert(guard_pos);
            guard_pos = (guard_pos.0 + dir.0, guard_pos.1 + dir.1);
        }
    }
    return visited_places.len() as u32;
}

fn part2() -> u32 {
    // TODO : garder toutes les positions visitées de la P1, et reboucler dessus en les remplaçant par '#'. Stocker un couple position + direction, et valider la position de l'obstacle si on repasse dans l'une d'elles (pas forcément la première) à un moment
    return 0;
}