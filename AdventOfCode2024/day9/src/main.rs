use std::fs;

fn main() {
    let p1 = part1();
    println!("The result of the first problem is : {p1}");
    let p2 = part2();
    println!("The result of the second problem is : {p2}");
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
    let mut drive = fs::read_to_string("res/exemple1")
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
    let mut reverse_i = pair_vec.len() - 1;
    for mut i in 0..pair_vec.len() {
        // Première itération OK, seconde ratée avec le skip des 8 et déplacement des 7
        if i % 2 == 1 {
            let empty_lenght = pair_vec[i].0;
            loop {
                let reverse_lenght = pair_vec[reverse_i].0;
                if empty_lenght >= reverse_lenght {
                    pair_vec.remove(i);
                    pair_vec.insert(i, pair_vec[reverse_i-1]);
                    i+=1;
                    pair_vec.insert(i, (empty_lenght - reverse_lenght, 1));
                    i+=1;
                    pair_vec.remove(reverse_i + 1);
                    pair_vec.insert(reverse_i + 1, (reverse_lenght, 1));
                    if reverse_i <= i {
                        break;
                    }
                    break;
                }
                reverse_i-=2;
                if reverse_i <= i {
                    break;
                }
            }
        }
        for val in &pair_vec {
            for _ in 0..val.0 {
                if val.1 % 2 == 0 {
                    print!("{}", val.1/2);
                } else {
                    print!(".");
                }
            }
        }
        println!();
    }
    0
}

/*fn part2() -> u64 {
    let mut drive = fs::read_to_string("res/exemple1")
        .expect("Should have been able to read the file");
    if drive.len() % 2 == 0 {
        // If the last entry is free space, it is useless so we just discard it
        drive.pop();
    }
    let mut vec_drive = drive.clone().into_bytes().into_iter().fold((vec![], 0), |mut vec:(Vec<i64>, i64), value| {
        for _ in 0..(value - 48) {
            if vec.1 % 2 == 0 {
                vec.0.push(vec.1/2);
            } else {
                vec.0.push(-1);
            }
        }
        (vec.0, vec.1 + 1)
    }).0;
    let mut empty_start:i64 = -1;
    let mut back_i = drive.len() - 1;
    let mut full_back_i = vec_drive.len() - 1;
    let mut checksum:u64 = 0;
    for i in 0..vec_drive.len() {
        if vec_drive[i] != -1 {
            if empty_start != -1 {
                let space_len = i - empty_start as usize;
                let real_len = (drive.as_bytes()[back_i] - 48) as usize;
                if space_len >= real_len {
                    for sum_i in 0..real_len {
                        checksum += (sum_i * (back_i / 2)) as u64;
                        vec_drive[full_back_i - sum_i] = -1;
                        println!("{}.",  back_i / 2);
                    }
                }
                full_back_i -= real_len + (drive.as_bytes()[back_i - 1] - 48) as usize;
                back_i -= 2;
                empty_start = -1;
            }
            println!("{}",  vec_drive[i]);
            checksum += i as u64 * vec_drive[i] as u64;
        } else if full_back_i > i {
            if empty_start == -1 {
                empty_start = i as i64;
            }
        }
    }
    checksum
}*/