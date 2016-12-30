**************************BT Codetest ReadMe**************************************

/* CSV-Reader tool that identifies which routers are suitable for patching.
 * 
 * A suitable router must meet the following conditions:
 * 
 * -OS level 12 or higher
 * -Not already patched
 * -No routers with a shared IP
 * -No routers with a shared Hostname
 *
 * @author Stephen Pope - stevempope@gmail.com
 * */

*************************Structure of the program********************************

-This program is currently set up in the form of an Eclipse Neon project, but has
 been tested from the Windows Command line.

-Two csv files, the original sample and one with extra modified data designed to
 correctly test the validation of data in the CSV have been provided. The are stored,
 along with the class files, in the folder \PatchChecker\src\.. The two CSV's are named
 sample.csv and NewSample.csv

-To compile the program from the command line as the instructions state, take the following
 steps:

	-Open cmd window at location %ProjectPath%\PatchChecker\src
	-Run set path=%path%;C:\Program Files\Java\%yourJDKVersion%\bin
		-Example: set path=%path%;C:\Program Files\Java\jdk1.8.0_102\bin
	-Run javac Main.java

-Once compiled, the program can be run using the following command:
	-java Main sample.csv
	or
	-java Main NewSample.csv

-It should be noted that in order to run CSV files from locations other than the src folder,
 you will need to provide the absolute path to that file in quotes. Example:
	-java Main "C:\Users\%Username%\Documents\myNewSample.csv"# BTPatchChecker
Submission for BT Software Engineering Placement Program
