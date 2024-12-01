use std::fs;
use std::iter::zip;
use std::collections::HashMap;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let mut lists = contents.lines().map(|line| line.split_whitespace()
                                    .map(|num| num.parse::<u32>().unwrap())
                                    .collect::<Vec<u32>>())
                    .fold((Vec::new(), Vec::new()), |mut acc, vals| {
                        acc.0.push(vals[0]);
                        acc.1.push(vals[1]);
                        return acc;
                    });
    lists.0.sort();
    lists.1.sort();
    return zip(lists.0,lists.1).map(|vals| vals.0.abs_diff(vals.1)).reduce(|sum, val| sum + val).expect("Should return a u32");
}

fn part2() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let lists = contents.lines().map(|line| line.split_whitespace()
                                    .map(|num| num.parse::<u32>().unwrap())
                                    .collect::<Vec<u32>>())
                    .fold((Vec::new(), Vec::new()), |mut acc, vals| {
                        acc.0.push(vals[0]);
                        acc.1.push(vals[1]);
                        return acc;
                    });
    let mut freq_map = HashMap::new();
    lists.1.into_iter().for_each(|val| {let _ = freq_map.insert(val, freq_map.get(&val).unwrap_or(&0) + 1);});
    return lists.0.into_iter().fold(0, |sum, num| sum + num * freq_map.get(&num).unwrap_or(&0));
}