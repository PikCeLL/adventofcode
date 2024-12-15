use std::{fs, io::Write};
use std::str;
use regex::Regex;

fn main() {
    println!("The result of the first problem is : {}", part1());
    println!("The result of the second problem is : {}", part2());
}

fn part1() -> isize {
    let regex = Regex::new(r"p=([0-9]*),([0-9]*) v=(-?[0-9]*),(-?[0-9]*)").unwrap();
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let width = 101;
    let height = 103;
    let nb_jumps = 100;
    let counts = regex.captures_iter(&contents).fold((0,0,0,0), |acc, cap| {
        let pos = (cap.get(1).unwrap().as_str().parse::<isize>().unwrap(), cap.get(2).unwrap().as_str().parse::<isize>().unwrap());
        let vec = (cap.get(3).unwrap().as_str().parse::<isize>().unwrap(), cap.get(4).unwrap().as_str().parse::<isize>().unwrap());
        let final_x = (pos.0 + nb_jumps * vec.0) % width;
        let final_y = (pos.1 + nb_jumps * vec.1) % height;
        let final_pos = (if final_x < 0 {width + final_x} else {final_x}, if final_y < 0 {height + final_y} else {final_y});
        if final_pos.0 < width/2 {
            if final_pos.1 < height/2 {
                return (acc.0 + 1, acc.1, acc.2, acc.3);
            } else if final_pos.1 > height/2 {
                return (acc.0, acc.1 + 1, acc.2, acc.3);
            }
        } else if final_pos.0 > width/2 {
            if final_pos.1 < height/2 {
                return (acc.0, acc.1, acc.2 + 1, acc.3);
            } else if final_pos.1 > height/2 {
                return (acc.0, acc.1, acc.2, acc.3 + 1);
            }
        }
        acc
    });
    counts.0 * counts.1 * counts.2 * counts.3
}

fn append_data_to_file(path: &str, data: &[u8]) -> Result<(), Box<dyn std::error::Error>> {
    // Method from stackoverflow
    let mut file = fs::OpenOptions::new().create(true).append(true).open(&path)?;

    // You can either try to write all data at once
    file.write_all(&data)?;

    // Or try to write as much as possible, but need
    // to take care of the remaining bytes yourself
    let remaining = file.write(&data)?;

    if remaining > 0 {
      // You need to handle the remaining bytes
    }

    Ok(())
}
 
fn part2() -> String {
    let regex = Regex::new(r"p=([0-9]*),([0-9]*) v=(-?[0-9]*),(-?[0-9]*)").unwrap();
    let contents = fs::read_to_string("res/input")
        .expect("Should have been able to read the file");
    let width = 101;
    let height = 103;
    let bots = regex.captures_iter(&contents).map(|cap| {
        let pos = (cap.get(1).unwrap().as_str().parse::<isize>().unwrap(), cap.get(2).unwrap().as_str().parse::<isize>().unwrap());
        let vec = (cap.get(3).unwrap().as_str().parse::<isize>().unwrap(), cap.get(4).unwrap().as_str().parse::<isize>().unwrap());
        (pos, vec)
    }).collect::<Vec<_>>();
    for i in 5000..9000 {
        let _ = append_data_to_file("output", i.to_string().as_bytes());
        // We add 1 to width to fit some \n
        let mut buffer: Vec<u8> = vec![b' '; (width + 1) * height];
        bots.iter().for_each(|bot| {
            let final_x = (bot.0.0 + i * bot.1.0) % width as isize;
            let final_y = (bot.0.1 + i * bot.1.1) % height as isize;
            let final_pos = (if final_x < 0 {width as isize + final_x} else {final_x}, if final_y < 0 {height as isize + final_y} else {final_y});
            buffer[final_pos.0 as usize + (final_pos.1 as usize * (width + 1))] = b'#';
        });
        for j in 0..height {
           buffer[width + (width + 1) * j] = b'\n';
        }
        let _ = append_data_to_file("output", &buffer);
    }
    "Look at the output file in the 7.5-8.5k range ;)".to_string()
}