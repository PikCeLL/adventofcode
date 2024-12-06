use std::cmp::Ordering;
use std::fs;
use std::collections::HashMap;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn is_less(a: &str, b:&str, rules: &HashMap<&str, Vec<&str>>) -> bool {
    return rules.get(a).map_or(false, |vec| vec.contains(&b));
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let (rules, updates) = contents.split_once("\n\r\n").unwrap();
    let mut more_map: HashMap<&str, Vec<&str>> = HashMap::new();
    rules.lines().for_each(|rule| {
        let (a, b) = rule.split_once('|').unwrap();
        let mut more_vec = Vec::new();
        let opt_more = more_map.get(a);
        if opt_more.is_some() {
            more_vec = opt_more.unwrap().clone();
        }
        more_vec.push(b);
        more_map.insert(a, more_vec);
    });
    return updates.lines().map(|str_update| str_update.split(',').collect::<Vec<_>>())
        .filter(|split_update| split_update.is_sorted_by(|a, b| is_less(a, b, &more_map)))
        .map(|valid_update| valid_update.get(valid_update.len()/2).unwrap().parse::<u32>().unwrap())
        .reduce(|sum, val| sum + val).unwrap();
}

fn part2() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let (rules, updates) = contents.split_once("\n\r\n").unwrap();
    let mut more_map: HashMap<&str, Vec<&str>> = HashMap::new();
    rules.lines().for_each(|rule| {
        let (a, b) = rule.split_once('|').unwrap();
        let mut more_vec = Vec::new();
        let opt_more = more_map.get(a);
        if opt_more.is_some() {
            more_vec = opt_more.unwrap().clone();
        }
        more_vec.push(b);
        more_map.insert(a, more_vec);
    });
    
    return updates.lines().map(|str_update| str_update.split(',').collect::<Vec<_>>())
        .filter(|split_update| !split_update.is_sorted_by(|a, b| is_less(a, b, &more_map)))
        .map(|mut valid_update| {
            valid_update.sort_by(|a, b| if is_less(a, b, &more_map) {Ordering::Greater} else {Ordering::Less});
            return valid_update.get(valid_update.len()/2).unwrap().parse::<u32>().unwrap();
        })
        .reduce(|sum, val| sum + val).unwrap();
}