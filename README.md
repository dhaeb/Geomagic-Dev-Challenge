# Geomagic Java developer Challenge
## Overview

This project solves the Geomagics Java developer challenge.
There have been two subtasks.

### Task description

Given is a list of coordinate pairs, each of which represents the starting and end point of a line. Two lines can be joined together to form a polyline if one of the
points of one line coincides with the start/end point of the other line.
If more than two lines meet at one point, then they are not joined at this point.
The input file contains one line for each line. Each line contains 4 floating point numbers
(can also be integers), which are separated from each other by blanks and are to be interpreted as follows
to be interpreted as follows: X1 Y1 X2 Y2

Translated with www.DeepL.com/Translator (free version)

### Calculate Connected Lines from Input file 

Form polylines from as many lines as possible and output them sorted by their total length (length in the plane, not number of lines).
total length (length in the plane, not number of lines) (longest first).

Note: The given lines do not overlap.

### Create window or picture from calculated lines
Output the polylines as a picture or in a window on the screen. Use
a different colour for each polyline.

Note: The coordinates are in the range [0..1024).

## Getting Started

### Prerequisites
The project has been developed with Java 17 and Apache Maven.
You need Maven to build the process.

- [Java (17)](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/download.cgi)

Optional on Windows to run the scripts:

- [bash (CLI), e.g. is installed by "git bash"](https://git-scm.com/downloads)

#### Build the project
This project is built with maven. 
You should install maven on your operating system to be able to build the project
(mvn should be in your `PATH` env).

Use `mvn package` in the root directory of the project 
(the project's POM file is located there). 
This will create multiple jar files in the target directory, especially a so called `jar-with-dependencies`.

#### Run the project
After a successful build, there are two scripts `run-task-one.sh` and `run-task-two.sh` which can be
executed on Unix like systems with `./<respective-scriptname>`. 
On Windows, look into the next paragraph for potential advise of running the project after building.

#### Run on Windows with no bash
One option could be installing a CLI for bash which is included in a software like git.
Alternatively, you could just execute with normal Windows CLI or Powershell:

`java -cp target/JavaDevChallenge-1.0.0-SNAPSHOT.jar <MAIN_NAME>`

where <MAIN_NAME> could be ether `de.geomagic.MainTaskOne` or `de.geomagic.MainTaskTwo`.

### Code coverage

The code coverage has been calculated during a build with the "Jacoco" maven plugin.
See `target\site\jacoco` for insights.


## Developer info

The stage of this project in completed. If there have been some mistakes, please point them out using the issue feature of Github. 

## Colophon

### Used dependencies

Thanks to the [Lombok project](https://projectlombok.org/) 
for reducing the amount of boilerplate I have to write in Java.

Also, this project uses [JUnit 5](https://junit.org/junit5/) for testing, especially [parameterized tests](https://www.baeldung.com/parameterized-tests-junit-5).
Thanks to Stackoverflow, [Baeldung](https://www.baeldung.com/) and the [Java Stream API](https://www.baeldung.com/java-8-streams). 

### Used IDE
The project have been developed with IntelliJ IDEA Ultimate. 

## License

The project is licensed under terms of the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
