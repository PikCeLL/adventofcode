use std::fs;
use std::collections::HashMap;


fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn get_nb_stones_next_it(stone: u64, iteration: usize, max_iter: usize, lookup: &mut HashMap<(u64, usize), u64>) -> u64 {
    let cached_value = lookup.get(&(stone, iteration));
    if cached_value.is_some() {
        return *cached_value.unwrap();
    } else {
        let ret;
        if iteration == max_iter {
            ret = 1;
        } else {
            let next_it = iteration + 1;
            if stone == 0 {
                ret = get_nb_stones_next_it(1, next_it, max_iter, lookup);
            } else if stone.ilog10() & 1 == 1 {
                let tenth_pow = 10u64.pow((stone.ilog10() + 1) / 2);
                let first_stone = stone / tenth_pow;
                let second_stone = stone % tenth_pow;
                ret = get_nb_stones_next_it(first_stone, next_it, max_iter, lookup) + get_nb_stones_next_it(second_stone, next_it, max_iter, lookup);
            } else {
                ret = get_nb_stones_next_it(stone * 2024, next_it, max_iter, lookup);
            }
        }
        lookup.insert((stone, iteration), ret);
        return ret;
    }
}

fn blink_stones_rec_cached(input: &Vec<u64>, nb_blinks: usize) -> u64 {
    let mut lookup = HashMap::<(u64, usize), u64>::new();
    input.into_iter().fold(0, |sum, stone| sum + get_nb_stones_next_it(*stone, 0, nb_blinks, &mut lookup))
}

fn part1() -> u64 {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: Vec<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones_rec_cached(&stones, 25)
}

fn part2() -> u64 {
    let content = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let stones: Vec<u64> = content.split_whitespace().into_iter().map(|number| number.parse::<u64>().expect("Should parse a number")).collect();
    blink_stones_rec_cached(&stones, 75)
}
