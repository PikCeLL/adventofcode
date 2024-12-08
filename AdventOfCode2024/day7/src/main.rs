use std::fs;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}

fn is_valid_p1(key_vals: &(u64, Vec<u64>)) -> bool {
    let nb_val = key_vals.1.len();
    let max_bool_mask = 2u64.pow(nb_val as u32);
    for b in 0..max_bool_mask {
        let mut res = *key_vals.1.get(0).unwrap();
        for i in 1..nb_val {
            let val = *key_vals.1.get(i).unwrap() as u64;
            if ((b >> (i - 1)) & 1) {
                res = res * val;
            } else {
                res = res + val;
            }
        }
        if res == key_vals.0 {
            return true;
        }
    }
    false
}

fn part1() -> u64 {
    let contents = fs::read_to_string("res/input").expect("Should have been able to read the file");
    return contents.lines().map(|line| line.split_once(':').unwrap())
    .map(|key_vals_str| (key_vals_str.0.parse::<u64>().unwrap(), key_vals_str.1.split_whitespace().map(|num| num.parse::<u64>().unwrap()).collect::<Vec<_>>()))
    .filter(|key_vals| is_valid_p1(key_vals))
    .fold(0, |sum, vals| sum + vals.0);
}

fn is_valid_p2(key_vals: &(u64, Vec<u64>)) -> bool {
    let nb_val = key_vals.1.len();
    let max_trool_mask = 3u64.pow(nb_val as u32);
    for b in 0..max_trool_mask {
        let mut res = *key_vals.1.get(0).unwrap();
        let mut trool_mask = b;
        for i in 1..nb_val {
            let trool = (trool_mask / 3, trool_mask % 3);
            let val = *key_vals.1.get(i).unwrap() as u64;
            if trool.1 == 0 {
                res = res * val;
            } else if trool.1 == 1 {
                res = res + val;
            } else {
                let mut as_string = res.to_string();
                as_string.push_str(&val.to_string());
                res = as_string.parse::<u64>().unwrap();
            }
            trool_mask = trool.0;
        }
        if res == key_vals.0 {
            return true;
        }
    }
    false
}


fn part2() -> u64 {
    let contents = fs::read_to_string("res/input").expect("Should have been able to read the file");
    return contents.lines().map(|line| line.split_once(':').unwrap())
    .map(|key_vals_str| (key_vals_str.0.parse::<u64>().unwrap(), key_vals_str.1.split_whitespace().map(|num| num.parse::<u64>().unwrap()).collect::<Vec<_>>()))
    .filter(|key_vals| is_valid_p2(key_vals))
    .fold(0, |sum, vals| sum + vals.0);
}