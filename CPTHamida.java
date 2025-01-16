import arc.*;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables for All
        String StrWhere;

        /** Front interface */
        con.println("To exit press E");
        con.println("To play game press P");
        con.println("To see score press S");
        con.print("Which command would you like to proceed with?: ");    
        StrWhere = con.readLine();

        CPTmethods.StrScreen(StrWhere, con);

        /** Game play */
        
        // Variables
        String strLogInName;
        double dblMark; 
        double dbBlcasicMath;
        double dblCount;

        // Variable arrays and while loop only for Basic Math  
        String StrQeshtion;
        String intAnw;
        String dblAnw;
        String StrAnw;
        int intRand;
        int intBMRow;
        
        // Arrays 
        String BM[][];

        // Play screen login: 
        if (StrWhere.equals("P")) {
            con.println("Login");
            TextOutputFile Score = new TextOutputFile("Login.txt", true);  // Open the file in append mode
            
            // Write the name into the file
            con.print("Enter Name: ");
            strLogInName = con.readLine();
            Score.println(strLogInName);  
            
            con.clear();
            
            // The Test 
            con.println("(LEVEL 1)for Basic Math press B ");
            con.println("(LEVEL 2)for Basic Algebra press A");
            con.println("(LEVEL 3)for the test Functions F");
            
            con.println("Which test would you like to take?:");
            StrWhere = con.readLine();
            
            if (StrWhere.equals("B")) {
                
                TextInputFile BasicMath = new TextInputFile("BasicMath.txt");
                
                // Initialize the array for Basic Math
                BM = new String[10][5];  // Assuming there are 10 questions and 5 possible answers (e.g., question, answer1, answer2, correct answer, etc.)
                
                // Taking data from the file
                int row = 0;
                while (!BasicMath.eof() && row < 10) {  // Read only the first 10 lines if there are that many
                    StrQeshtion = BasicMath.readLine();
                    intAnw = BasicMath.readLine();
                    dblAnw = BasicMath.readLine();
                    StrAnw = BasicMath.readLine();
                    
                    // Store the question and answers in the array
                    BM[row][0] = StrQeshtion;  // Question
                    BM[row][1] = intAnw;       // Answer 1
                    BM[row][2] = dblAnw;       // Answer 2
                    BM[row][3] = StrAnw;       // Correct Answer
                    
                    // Generate random number (within the range 1-10)
                    intRand = (int)(Math.random() * 10 + 1);
                    row++;
                }
                
                BasicMath.close();
                
                // Now, you can use the `BM` array for the game logic
                // For example, displaying questions and validating answers
                
                // Code to process the Basic Math questions (for now just displaying them)
                for (int i = 0; i < 10; i++) {
                    con.println("Question: " + BM[i][0]);
                    
                    con.print("Your answer: ");
                    String userAnswer = con.readLine();
                    
                    // Check if the answer is correct
                    if (userAnswer.equals(BM[i][3] ) || userAnswer.equals(BM[i][2] ) || userAnswer.equals(BM[i][1] )|| userAnswer.equals(BM[i][0] )) {
                        con.println("Correct!");
                    } else {
                        con.println("Incorrect :<");
                    }
                }
                
                Score.close();  // Close the score file after finishing the game
            }
        }
    }
}
