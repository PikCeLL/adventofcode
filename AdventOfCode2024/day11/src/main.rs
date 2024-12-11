use std::fs;
use std::collections::LinkedList;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn blink_stones(input: &LinkedList<u64>, nb_blinks: usize) -> usize {
    let mut stones = input.clone();
    for i in 0..nb_blinks {
        stones = stones.into_iter().flat_map(|stone| {
            if stone == 0 {
                return vec![1];
            } else if stone.ilog10() & 1 == 1 {
                let tenth_pow = 10u64.pow((stone.ilog10() + 1) / 2);
                let first_stone = stone / tenth_pow;
                let second_stone = stone % tenth_pow;
                return vec![first_stone, second_stone];
            } else {
                return vec![stone * 2024];
            }
        }).collect();
        println!("{}", i);
    }
    stones.len()
}

fn part1() -> usize {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: LinkedList<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones(&stones, 25)
}

fn part2() -> usize {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: LinkedList<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones(&stones, 75)
}
