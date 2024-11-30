import os
import requests
import subprocess
from pathlib import Path
from sys import argv
from dotenv import load_dotenv

def fetch_input(year, day):
    url = f"https://adventofcode.com/{year}/day/{day}/input"
    headers = {"Cookie": f"session={SESSION}"}

    response = requests.get(url, headers=headers)
    response.raise_for_status()
    input_data = response.text

    input_dir = Path(f"src/main/resources/input/{year}")
    input_dir.mkdir(parents=True, exist_ok=True)

    input_file = input_dir / f"{day}.txt"
    input_file.write_text(input_data)

def create_solution_file(year, day):
    path = f"src/main/kotlin/year{year}"

    solution_dir = Path(path)
    solution_dir.mkdir(parents=True, exist_ok=True)
    
    solution_file = Path(path + f"/Day{day}.kt")

    if not solution_file.exists():
        f = open(path + f"/Day{day}.kt", "a")
        f.write(
f'''package year{year}

import me.koendev.*

fun main() {{
    part1(getInput({year}, {day})).println()
    part2(getInput({year}, {day})).println()
}}


private fun part1(input: List<String>): Int {{


    return 0
}}


private fun part2(input: List<String>): Int {{


    return 0
}}
''')
        f.close()
        subprocess.run(["git", "add", path + f"/Day{day}.kt"])



if __name__ == "__main__":
    load_dotenv()
    SESSION = os.getenv("SESSION")
    fetch_input(argv[1], argv[2])
    
    create_solution_file(argv[1], argv[2])
