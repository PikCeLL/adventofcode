use std::fs;
use regex::Regex;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn mult_sum(input: &str) -> u32 {
    let regex = Regex::new(r"mul\(([0-9]{1,3}),([0-9]{1,3})\)").unwrap();
    return regex.captures_iter(input).map(|cap| cap.get(1).unwrap().as_str().parse::<u32>().unwrap() * cap.get(2).unwrap().as_str().parse::<u32>().unwrap()).reduce(|sum, v| sum + v).unwrap();

}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    return mult_sum(&contents);
}

fn part2() -> u32 {
    let mut contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    contents.insert_str(0, "do()");
    return contents.split("don't()").map(|split| {
        let dos = split.split("do()").collect::<Vec<_>>();
        let mut part_sum = 0;
        for i in 1..dos.len() {
            part_sum += mult_sum(dos[i]);
        }
        return part_sum;
    }).reduce(|sum, v| sum + v).unwrap();
}