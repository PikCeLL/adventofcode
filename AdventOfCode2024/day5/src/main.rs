use std::fs;
use std::collections::HashMap;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn is_less_or_equal(a: &str, b:&str, rules: &HashMap<&str, Vec<&str>>) -> bool {
    return rules.get(b).map_or(true, |vec| {
        if vec.contains(&a) {
            return false;
        } else {
            vec.iter().all(|new_b| is_less_or_equal(&a, new_b, rules));
        }
    });
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let (rules, updates) = contents.split_once("\n\n").unwrap();
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
    let ret = updates.lines().map(|str_update| str_update.split(',').collect::<Vec<_>>()).filter(|split_update|
        split_update.is_sorted_by(|a, b| is_less_or_equal(a, b, &more_map))).collect::<Vec<_>>();
    return ret.into_iter().map(|valid_update| valid_update.get(valid_update.len()/2).unwrap().parse::<u32>().unwrap() as u32).reduce(|sum, val| sum + val).unwrap();
}

fn part2() -> u32 {
    return 0;
}