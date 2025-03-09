# Directory Word Indexer

This program recursively traverses a directory tree and constructs an index of all words found in the files within the visited directories. It counts the occurrences of distinct words and tracks their file location, including the line and column numbers.

## Features

- Recursively scans directories for text files
- Counts occurrences of distinct words
- Tracks the file, line, and column where each word appears
- Outputs words in alphabetical order along with their occurrences and locations
- Implements a **hash map** as the main data structure
- Developed in **Java**

## Usage

1. Clone the repository:
   ```sh
   git clone <repository-url>
   
2. Compile the Java program:
   ```sh
   javac Main.java
   
3. Run the program:
   ```sh
   java Main <directory-path>

## Example Output

Word: "example"

Occurrences: 3

File: /path/to/file.txt, Line: 5, Column: 12

File: /path/to/another-file.txt, Line: 10, Column: 8


## Technologies Used

- Java
- HashMap for efficient word storage and lookup
