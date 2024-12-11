use std::fs;
use std::time::{SystemTime, UNIX_EPOCH};

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn blink_stones(input: &Vec<u64>, nb_blinks: usize) -> usize {
    let mut stones = input.clone();
    for _ in 0..nb_blinks {
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
    }
    stones.len()
}

fn get_nb_stones_next_it(stone: u64, iteration: usize, max_iter: usize) -> usize {
    // TODO : un même chiffre va toujours donner le même résultat au bout du même nombre d'itération -> possibilité de peupler une map (stone, iteration) -> usize.
    // Ou alors stocker les résultats des différentes itération de 0 -> 1 -> 2024 -> 20 24 -> 2 0 2 4 -> 4048 1 4048 8096
    // la suite basique utilise beaucoup de puissances de 2...
    if iteration == max_iter {
        return 1;
    } else {
        let next_it = iteration + 1;
        if stone == 0 {
            return get_nb_stones_next_it(1, next_it, max_iter);
        } else if stone.ilog10() & 1 == 1 {
            let tenth_pow = 10u64.pow((stone.ilog10() + 1) / 2);
            let first_stone = stone / tenth_pow;
            let second_stone = stone % tenth_pow;
            return get_nb_stones_next_it(first_stone, next_it, max_iter) + get_nb_stones_next_it(second_stone, next_it, max_iter);
        } else {
            return get_nb_stones_next_it(stone * 2024, next_it, max_iter);
        }
    }
}

fn blink_stones_rec(input: &Vec<u64>, nb_blinks: usize) -> usize {
    input.into_iter().fold(0, |sum, stone| sum + get_nb_stones_next_it(*stone, 0, nb_blinks))
}

fn part1() -> usize {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: Vec<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones(&stones, 25)
}

fn part2() -> usize {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: Vec<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones_rec(&stones, 75)
}
