use std::fs;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
}


fn is_report_safe(report: Vec::<i32>) -> bool {
    let dir:i32 = if report[0] < report[1] {1} else {-1};
    let mut prev = report[0];
    for i in 1..report.len() {
        let dist = (report[i] - prev) * dir;
        if dist > 3 || dist < 1 {
            return false;
        }
        prev = report[i];
    }
    return true;
}

fn part1() -> u32 {
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    return contents.lines().filter(|line| {
        return is_report_safe(line.split_whitespace().map(|s| s.parse::<i32>().unwrap()).collect::<Vec<i32>>());
    }).count().try_into().unwrap();
}

fn part2() -> u32 {
    // Added exemple2 from the subreddit which contains edge-cases. All 10 are safe
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    return contents.lines().filter(|line| {
        let report = line.split_whitespace().map(|s| s.parse::<i32>().unwrap()).collect::<Vec<i32>>();
        return (0..report.len()).any(|index| {
            let mut temp_rep = report.clone();
            temp_rep.remove(index);
            return is_report_safe(temp_rep);
        });
    }).count().try_into().unwrap();
}