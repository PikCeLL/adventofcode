use std::fs;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn part1() -> u64 {
    let mut drive = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    if drive.len() % 2 == 0 {
        // If the last entry is free space, it is useless so we just discard it
        drive.pop();
    }
    let drive_lenght = drive.len();
    let mut checksum = 0;
    let mut index = 0;
    let mut reverse_i = drive_lenght - 1;
    let mut reverse_lenght = drive.as_bytes()[reverse_i] - 48;
    for i in 0..drive_lenght{
        let lenght = drive.as_bytes()[i] - 48;
        for _ in 0..lenght {
            if i % 2 == 0 {
                if reverse_i == i {
                    for _ in 0..reverse_lenght {
                        checksum += index * (i as u64 / 2);
                    }
                    break;
                } else {
                    checksum += index * (i as u64 / 2);
                }
            } else {
                if reverse_lenght == 0 {
                    reverse_i -= 2;
                    if reverse_i <= i {
                        break;
                    }
                    reverse_lenght = drive.as_bytes()[reverse_i] - 48;
                }
                checksum += index * (reverse_i as u64 / 2);
                reverse_lenght -= 1;
            }
            index += 1;
        }
        if reverse_i < i {
            break;
        }
    }
    checksum
}


fn part2() -> u64 {
    let mut drive = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    if drive.len() % 2 == 0 {
        // If the last entry is free space, it is useless so we just discard it
        drive.pop();
    }
    let vec_drive = drive.into_bytes();
    let mut pair_vec = vec![];
    for i in 0..vec_drive.len() {
        pair_vec.push((vec_drive[i] - 48, i));
    }
    for mut back_i in (0..pair_vec.len()).rev() {
        if pair_vec[back_i].1 % 2 == 0 {
            let back_lenght = pair_vec[back_i].0;
            for i in 0..back_i {
                let empty_lenght = pair_vec[i].0;
                if (pair_vec[i].1 % 2 == 1) && (empty_lenght >= back_lenght) {
                    pair_vec.remove(i);
                    pair_vec.insert(i, (empty_lenght - back_lenght, 1));
                    pair_vec.insert(i, pair_vec[back_i]);
                    pair_vec.remove(back_i + 1);
                    pair_vec.insert(back_i + 1, (back_lenght, 1));
                    back_i -= 1;
                    break;
                }
            }
        }
    }
    pair_vec.into_iter().fold((0u64, 0u64), |check_and_i, pair| {
        let mut checksum = check_and_i.0;
        if pair.1 % 2 == 0 {
            for i in 0..pair.0 {
                checksum += pair.1 as u64/2 * (i as u64 + check_and_i.1);
            }
        }
        (checksum, check_and_i.1 + pair.0 as u64)
    }).0
}
