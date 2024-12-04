use std::fs;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    //let p2 = part2();
    //println!("The result of the second problem is : {p2}");
}

fn count_word_around(matrix: &Vec<Vec<char>>, word: &str, pos: (isize, isize)) -> u32 {
    let mut res = 0;
    for dir_x in -1..1 {
        for dir_y in -1..1 {
            let mut is_word_complete = true;
            for i in 1..word.len() {
                let pos_x = pos.0 + dir_x;
                let pos_y = pos.1 + dir_y;
                is_word_complete &= (pos_x >= 0) && (pos_y >= 0) && (pos_x <= matrix.len().try_into().unwrap()) && (pos_y <= matrix[0].len().try_into().unwrap())
                && (word.chars().nth(i).unwrap() == matrix[TryInto::<usize>::try_into(pos_x).unwrap()][TryInto::<usize>::try_into(pos_y).unwrap()]);
            }
            if is_word_complete {
                res += 1;
            }
        }
    }
    return res;
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/exemple1")
        .expect("Should have been able to read the file");
    let matrix = contents.lines().map(|line| line.chars().collect::<Vec<_>>()).collect::<Vec<_>>();
    let word = "XMAS";
    let mut res = 0;
    for (i, line) in matrix.iter().enumerate() {
        for (j, c) in line.iter().enumerate() {
            if word.chars().nth(0).unwrap() == *c {
                res += count_word_around(&matrix, word, (i.try_into().unwrap(), j.try_into().unwrap()));
            }
        }
    }
    return res;
}