import arc.*;
import java.util.Random;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables for All
        String StrWhere = "H";   // Home screen
        String strLogInName = "";
        int intscore = 0;  // Initialize score
        int intNOBMQ = 0;  // Initialize number of questions in Basic Math

        // Arrays for questions and answers (5th column for random number)
        String BM[][] = new String[intNOBMQ = CPTmethods.countQuestionsInFile("BasicMath.txt")][5];  // Add 5th column for random number

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
                    con.println(strLogInName + " final score: " + intscore);
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
				
				
				TextOutputFile Score = new TextOutputFile("Score.txt", true);
                con.println("Login");

                // Ask for username
                
                if (strLogInName.equalsIgnoreCase(" ")) {
					con.print("Enter Name: ");
					strLogInName = con.readLine();
					
					strLogInName = con.readLine();
					Score.println(strLogInName);
					
			}  


                con.clear();

                // The Test Options
                con.println("(LEVEL 1 test) for Basic Math press B ");
                con.println("(LEVEL 2 test) for Basic Algebra press A");
                con.println("(LEVEL 3 test) for the test Functions F");

                con.print("Which test would you like to take?: ");
                StrWhere = con.readLine();

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
                        con.println("Question: " + BM[i][0]);
                        con.print("Your answer: ");
                        String userAnswer = con.readLine();

                        // Check if the answer is correct
                        if (userAnswer.equals(BM[i][3]) || userAnswer.equals(BM[i][1]) || userAnswer.equals(BM[i][2])) {
                            con.println("Correct!");
                            intscore = intscore + 1 ;  // Increase score
                        } else {
                            con.println("Incorrect :<");
                        }
                    }

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
}
