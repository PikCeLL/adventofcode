use std::fs;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn count_word_around(matrix: &Vec<Vec<char>>, word: &str, pos: (isize, isize)) -> u32 {
    let mut res = 0;
    for dir_x in -1..=1 {
        for dir_y in -1..=1 {
            let mut is_word_complete = true;
            for i in 1..word.len() {
                let pos_x: isize = pos.0 + (i as isize * dir_x);
                let pos_y: isize = pos.1 + (i as isize * dir_y);
                is_word_complete &= (pos_x >= 0) && (pos_y >= 0) && (pos_x < matrix.len() as isize) && (pos_y < matrix[0].len() as isize)
                && (word.chars().nth(i).unwrap() == matrix[pos_x as usize][pos_y as usize]);
            }
            if is_word_complete {
                res += 1;
            }
        }
    }
    return res;
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let word = "XMAS";
    let mut res = 0;
    for (i, line) in matrix.iter().enumerate() {
        for (j, c) in line.iter().enumerate() {
            if word.chars().nth(0).unwrap() == *c {
                res += count_word_around(&matrix, word, (i as isize, j as isize));
            }
        }
    }
    return res;
}

fn part2() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let mut res = 0;
    for i in 1..matrix.len()-1 {
        for j in 1..matrix[0].len()-1 {
            if matrix[i][j] == 'A'
            && ((matrix[i-1][j+1] == 'M' && matrix[i+1][j-1] == 'S') || (matrix[i-1][j+1] == 'S' && matrix[i+1][j-1] == 'M'))
            && ((matrix[i+1][j+1] == 'M' && matrix[i-1][j-1] == 'S') || (matrix[i+1][j+1] == 'S' && matrix[i-1][j-1] == 'M')) {
                res += 1;
            }
        }
    }
    return res;
}