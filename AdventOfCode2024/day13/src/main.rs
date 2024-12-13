use std::fs;
use regex::Regex;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn part1() -> isize {
    let regex = Regex::new(r"Button A: X\+([0-9]*), Y\+([0-9]*)\nButton B: X\+([0-9]*), Y\+([0-9]*)\nPrize: X=([0-9]*), Y=([0-9]*)").unwrap();
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    regex.captures_iter(&contents).map(|cap| {
        let a = (cap.get(1).unwrap().as_str().parse::<isize>().unwrap(), cap.get(2).unwrap().as_str().parse::<isize>().unwrap());
        let b = (cap.get(3).unwrap().as_str().parse::<isize>().unwrap(), cap.get(4).unwrap().as_str().parse::<isize>().unwrap());
        let r = (cap.get(5).unwrap().as_str().parse::<isize>().unwrap(), cap.get(6).unwrap().as_str().parse::<isize>().unwrap());
        let alpha = (b.1*r.0 - b.0*r.1)/(b.1*a.0 - b.0*a.1);
        let beta = (a.1*r.0 - a.0*r.1)/(a.1*b.0 - a.0*b.1);
        if alpha*a.0 + beta*b.0 == r.0 && alpha*a.1 + beta*b.1 == r.1 {
            return 3 * alpha + beta;
        } else {
            return 0;
        }
    }).sum::<isize>()
}
 
fn part2() -> isize {
    let regex = Regex::new(r"Button A: X\+([0-9]*), Y\+([0-9]*)\nButton B: X\+([0-9]*), Y\+([0-9]*)\nPrize: X=([0-9]*), Y=([0-9]*)").unwrap();
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let off = 10000000000000;
    regex.captures_iter(&contents).map(|cap| {
        let a = (cap.get(1).unwrap().as_str().parse::<isize>().unwrap(), cap.get(2).unwrap().as_str().parse::<isize>().unwrap());
        let b = (cap.get(3).unwrap().as_str().parse::<isize>().unwrap(), cap.get(4).unwrap().as_str().parse::<isize>().unwrap());
        let r = (cap.get(5).unwrap().as_str().parse::<isize>().unwrap() + off, cap.get(6).unwrap().as_str().parse::<isize>().unwrap() + off);
        let alpha = (b.1*r.0 - b.0*r.1)/(b.1*a.0 - b.0*a.1);
        let beta = (a.1*r.0 - a.0*r.1)/(a.1*b.0 - a.0*b.1);
        if alpha*a.0 + beta*b.0 == r.0 && alpha*a.1 + beta*b.1 == r.1 {
            return 3 * alpha + beta;
        } else {
            return 0;
        }
    }).sum::<isize>()
}