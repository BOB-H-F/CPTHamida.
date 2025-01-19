/**satrt code */
import arc.*;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables for All
        String StrWhere = "H";   
        String strLogInName = "";
        int intscore = 0;  // score
        int intNOBMQ = 0;  // the number of questions in Basic Math
        int intNOALQ = 0;  // the number of questions in Algebra (Level 2)
        int intNOFLQ = 0;  // the number of questions in Functions (Level 3)

        // Arrays for questions and answers (5th column for random number)
        String BM[][] = new String[intNOBMQ = CPTmethods.countQuestionsInFile("BasicMath.txt")][5];  // Add 5th column for random number
        String AL[][] = new String[intNOALQ = CPTmethods.countQuestionsInFile("Algebra.txt")][5];  // Add 5th column for random number for Algebra
        String FL[][] = new String[intNOFLQ = CPTmethods.countQuestionsInFile("Functions.txt")][5];  // Add 5th column for random number for Functions

/**sound */
        // Sound file path
        File file = new File("SoundSquidGame.wav.wav");

        // Try to load and play sound
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            con.println("Error playing sound: " + e.getMessage());
        }


        while (true) {
            if (StrWhere.equalsIgnoreCase("H")) {
                // Front interface (Home Screen)
                con.clear();
                con.println("To exit press E");
                con.println("To play game press P");
                con.println("To see score press S");
                con.print("Which command would you like to proceed with?: ");
                StrWhere = con.readLine();
                
                // Check for the "S" command here to show the score
                if (StrWhere.equalsIgnoreCase("S")) {
                    con.clear();
                    CPTmethods.Intleaderbord(con, strLogInName); //the leaderboard 
                    con.print("Press H to go back to the home screen: ");
                    StrWhere = con.readLine();
                    if (StrWhere.equalsIgnoreCase("H")) {
                        continue;  // Go back to the home screen
                    }
                }

                // If user chooses something else (e.g., "P" for play), continue to the next logic
                CPTmethods.StrScreen(StrWhere, con);
            }

            // Play screen logic (when "P" is chosen)
            if (StrWhere.equalsIgnoreCase("P")) {
                con.clear();
                
                con.println("Login");

                // Ask for username
                if (strLogInName.equalsIgnoreCase("")) {
                    con.print("Enter Name: ");
                    strLogInName = con.readLine();

                    // Check if the name is already in the file and load the current score
                    intscore = checkAndLoadScore("Score.txt", strLogInName);
                }

                con.clear();

                // The Test Options
                con.println("The level you're in is the number of points you will get per question ");
                con.println(" ");
                con.println("(LEVEL 1 test) for Basic Math press B ");
                con.println("(LEVEL 2 test) for Basic Algebra press A");
                con.println("(LEVEL 3 test) for the test Functions F");

                con.print("Which test would you like to take?: ");
                StrWhere = con.readLine();
                
                // Level 1: Basic Math (existing)
                if (StrWhere.equalsIgnoreCase("B")) {
                    con.clear();
                    // Get the number of questions from the BasicMath.txt file
                    intNOBMQ = CPTmethods.countQuestionsInFile("BasicMath.txt");

                    // Reopen the file to read the questions again
                    TextInputFile BasicMath = new TextInputFile("BasicMath.txt");

                    // Initialize the array for Basic Math with the correct number of rows based on intNOBMQ
                    BM = new String[intNOBMQ][5];  // Add a 5th column for the random number

                    Random rand = new Random();  // Random number generator

                    // Now populate the array with the questions and answers
                    int row = 0;
                    while (!BasicMath.eof()) {
                        String StrQeshtion = BasicMath.readLine();
                        String intAnw = BasicMath.readLine();
                        String dblAnw = BasicMath.readLine();
                        String StrAnw = BasicMath.readLine();

                        // Store the question and answers in the array
                        BM[row][0] = StrQeshtion;  // Question
                        BM[row][1] = intAnw;       // Answer 
                        BM[row][2] = dblAnw;       // Answer 
                        BM[row][3] = StrAnw;       // Answer
                        BM[row][4] = Integer.toString(rand.nextInt(1000)); // Store random number in 5th column

                        row++;
                    }

                    BasicMath.close();  // Close the file after reading

							// varuble 
							int intBTestscore = 0;
                    // Shuffle the questions based on the random number in the 5th column
                    for (int i = 0; i < intNOBMQ; i++) {
                        for (int j = i + 1; j < intNOBMQ; j++) {
                            if (Integer.parseInt(BM[i][4]) > Integer.parseInt(BM[j][4])) {
                                // Swap the rows
                                String[] temp = BM[i];
                                BM[i] = BM[j];
                                BM[j] = temp;
                            }
                        }
                    }

                    // Asking questions in the shuffled order
                    for (int i = 0; i < intNOBMQ; i++) {  // Use intNOBMQ here
						con.clear();
						con.println(strLogInName + " score: " + intBTestscore);
                        con.println("Question: " + BM[i][0]);
                        con.print("Your answer: ");
                        String userAnswer = con.readLine();
                        

                        // Check if the answer is correct
                        if (userAnswer.equals(BM[i][3]) || userAnswer.equals(BM[i][1]) || userAnswer.equals(BM[i][2])) {
                            con.println("Correct!");
                            intscore = intscore + 1;  // Increase score in memory
                            intBTestscore = intBTestscore + 1;
                        } else {
                            con.println("Incorrect :<");
                        }
                    }

                    // After the test, save the updated score
                    updateScore("Score.txt", strLogInName, intscore);

					con.println(strLogInName + " for this test your score is " + intBTestscore);
                    con.println(strLogInName + " final score: " + intscore);

                    // After the test, return to the home screen
                    con.println("Press H to go back to the home screen");
                    StrWhere = con.readLine();
                    if (StrWhere.equalsIgnoreCase("H")) {
                        continue;  // Go back to the home screen
                    }
                }

                // Level 2: Basic Algebra (new part)
                if (StrWhere.equalsIgnoreCase("A")) {
                    con.clear();
                    // Get the number of questions from the Algebra.txt file
                    intNOALQ = CPTmethods.countQuestionsInFile("Algebra.txt");

                    // Reopen the file to read the questions again
                    TextInputFile Algebra = new TextInputFile("Algebra.txt");

                    // Initialize the array for Algebra with the correct number of rows based on intNOALQ
                    AL = new String[intNOALQ][5];  // Add a 5th column for the random number

                    Random rand = new Random();  // Random number generator

                    // Now populate the array with the questions and answers
                    int row = 0;
                    while (!Algebra.eof()) {
                        String StrQeshtion = Algebra.readLine();
                        String intAnw = Algebra.readLine();
                        String dblAnw = Algebra.readLine();
                        String StrAnw = Algebra.readLine();

                        // Store the question and answers in the array
                        AL[row][0] = StrQeshtion;  // Question
                        AL[row][1] = intAnw;       // Answer 
                        AL[row][2] = dblAnw;       // Answer 
                        AL[row][3] = StrAnw;       // Answer
                        AL[row][4] = Integer.toString(rand.nextInt(1000)); // Store random number in 5th column

                        row++;
                    }

                    Algebra.close();  // Close the file after reading

                    // Shuffle the questions based on the random number in the 5th column
                    for (int i = 0; i < intNOALQ; i++) {
                        for (int j = i + 1; j < intNOALQ; j++) {
                            if (Integer.parseInt(AL[i][4]) > Integer.parseInt(AL[j][4])) {
                                // Swap the rows
                                String[] temp = AL[i];
                                AL[i] = AL[j];
                                AL[j] = temp;
                            }
                        }
                    }
							// varuble 
							int intATestscore = 0;
							
                    // Asking questions in the shuffled order
                    for (int i = 0; i < intNOALQ; i++) {  // Use intNOALQ here
						con.clear();
						con.println(strLogInName + " score: " + intATestscore);
                        con.println("Question: " + AL[i][0]);
                        con.print("Your answer: ");
                        String userAnswer = con.readLine();
							
							
                        // Check if the answer is correct
                        if (userAnswer.equals(AL[i][3]) || userAnswer.equals(AL[i][1]) || userAnswer.equals(AL[i][2])) {
                            con.println("Correct!");
                            intscore = intscore + 2;  // Increase score in memory
                            intATestscore = intATestscore + 2;
                        } else {
                            con.println("Incorrect :<");
                        }
                    }

                    // After the test, save the updated score
                    updateScore("Score.txt", strLogInName, intscore);
                    
                    con.println(strLogInName + " for this test your score is " + intATestscore);
                    con.println(strLogInName + " final score: " + intscore);

                    // After the test, return to the home screen
                    con.println("Press H to go back to the home screen");
                    StrWhere = con.readLine();
                    if (StrWhere.equalsIgnoreCase("H")) {
                        continue;  // Go back to the home screen
                    }
                }

                // Level 3: Functions (new part with 3 points per correct answer)
                if (StrWhere.equalsIgnoreCase("F")) {
                    con.clear();
                    // Get the number of questions from the Functions.txt file
                    intNOFLQ = CPTmethods.countQuestionsInFile("Functions.txt");

                    // Reopen the file to read the questions again
                    TextInputFile Functions = new TextInputFile("Functions.txt");

                    // Initialize the array for Functions with the correct number of rows based on intNOFLQ
                    FL = new String[intNOFLQ][5];  // Add a 5th column for the random number

                    Random rand = new Random();  // Random number generator

                    // Now populate the array with the questions and answers
                    int row = 0;
                    while (!Functions.eof()) {
                        String StrQeshtion = Functions.readLine();
                        String intAnw = Functions.readLine();
                        String dblAnw = Functions.readLine();
                        String StrAnw = Functions.readLine();

                        // Store the question and answers in the array
                        FL[row][0] = StrQeshtion;  // Question
                        FL[row][1] = intAnw;       // Answer 
                        FL[row][2] = dblAnw;       // Answer 
                        FL[row][3] = StrAnw;       // Answer
                        FL[row][4] = Integer.toString(rand.nextInt(1000)); // Store random number in 5th column

                        row++;
                    }

                    Functions.close();  // Close the file after reading

							// varuble 
							int intFTestscore = 0;
							
                    // Shuffle the questions based on the random number in the 5th column
                    for (int i = 0; i < intNOFLQ; i++) {
                        for (int j = i + 1; j < intNOFLQ; j++) {
                            if (Integer.parseInt(FL[i][4]) > Integer.parseInt(FL[j][4])) {
                                // Swap the rows
                                String[] temp = FL[i];
                                FL[i] = FL[j];
                                FL[j] = temp;
                            }
                        }
                    }

                    // Asking questions in the shuffled order
                    for (int i = 0; i < intNOFLQ; i++) {  // Use intNOFLQ here
						
                        con.println("Question: " + FL[i][0]);
                        con.print("Your answer: ");
                        String userAnswer = con.readLine();

                        // Check if the answer is correct
                        if (userAnswer.equals(FL[i][3]) || userAnswer.equals(FL[i][1]) || userAnswer.equals(FL[i][2])) {
                            con.println("Correct!");
                            intscore = intscore + 3;  
                            intFTestscore = intFTestscore + 3;
                            
                        } else {
                            con.println("Incorrect :<");
                        }
                    }

                    // After the test, save the updated score
                    updateScore("Score.txt", strLogInName, intscore);

					con.println(strLogInName + " for this test your score is " + intFTestscore);
                    con.println(strLogInName + " final score: " + intscore);

                    // After the test, return to the home screen
                    con.println("Press H to go back to the home screen");
                    StrWhere = con.readLine();
                    if (StrWhere.equalsIgnoreCase("H")) {
                        continue;  // Go back to the home screen
                    }
                }
            }
        }
    }

    // Method to check if name exists and load the score from file
    public static int checkAndLoadScore(String fileName, String playerName) {
        boolean nameExists = false;
        String strAllNam;
        int score = 0;

        // Open the file to read names and scores
        TextInputFile scoreFile = new TextInputFile(fileName);

        // Read all existing names and check for duplication
        while ((strAllNam = scoreFile.readLine()) != null) {
            if (strAllNam.equalsIgnoreCase(playerName)) {
                // Name found, read the score
                score = Integer.parseInt(scoreFile.readLine());
                nameExists = true;
                break; // Stop reading if the name is found
            }
        }
        scoreFile.close(); // Close after reading

        // If the name doesn't exist, add it with a score of 0
        if (!nameExists) {
            TextOutputFile outputFile = new TextOutputFile(fileName, true);
            outputFile.println(playerName); // Add the name
            outputFile.println(0); // Add initial score as 0
            outputFile.close();
        }

        return score;  // Return the score
    }

    // Method to update the score for the user in the file
    public static void updateScore(String fileName, String playerName, int newScore) {
        TextInputFile scoreFile = new TextInputFile(fileName);
        TextOutputFile tempFile = new TextOutputFile("temp.txt"); // Temporary file to rewrite

        boolean playerFound = false;
        String line;

        // Read the original file and write to the temp file
        while ((line = scoreFile.readLine()) != null) {
            tempFile.println(line);  // Write the line as-is
            if (line.equalsIgnoreCase(playerName)) {
                playerFound = true;
                tempFile.println(newScore);  // Write the updated score
                scoreFile.readLine();  // Skip the old score
            }
        }

        // Close files
        scoreFile.close();
        tempFile.close();

        // Replace the old file with the new temp file
        TextInputFile tempInput = new TextInputFile("temp.txt");
        TextOutputFile originalFile = new TextOutputFile(fileName, false);  // Overwrite the original file

        // Copy everything from the temp file back to the original file
        while ((line = tempInput.readLine()) != null) {
            originalFile.println(line);
        }

        // Close files
        tempInput.close();
        originalFile.close();
    }
}
