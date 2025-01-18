import arc.*;

public class CPTmethods {

    // Method to count the number of questions in a given file
    public static int countQuestionsInFile(String fileName) {
        TextInputFile file = new TextInputFile(fileName);
        int questionCount = 0;

        // Read through the file to count the number of questions
        while (!file.eof()) {
            // Skip the lines containing the answers and correct answers
            file.readLine();  // Question line
            file.readLine();  // Answer 
            file.readLine();  // Answer 
            file.readLine();  // Answer 
            
            questionCount++;  // Increment the question count
        }

        file.close();
        return questionCount;  // Return the total number of questions
    }

    public static void StrScreen(String Strwhere, Console con) {
        if (Strwhere.equalsIgnoreCase("E")) {
            con.closeConsole(); // Close the console
        } else if (Strwhere.equals("P")) {
            con.clear();
            // Additional logic for 'P' can be added here
        }
    }

    // Modified strtryAgin method to update Strwhere and handle already on the same screen case
   public static String strtryAgin(String Strwhere, String StrUPWhere, Console con) {
        String userInput = "";  // To store user input

        while (true) {
            // Update StrUPWhere with the current Strwhere
            StrUPWhere = Strwhere;

            // Check if they are trying to go to the same screen
            if (userInput.equalsIgnoreCase(StrUPWhere)) {
                con.print("You're already here. Try a different command: ");
                userInput = con.readLine();  // Read input from the user
            } else {
                // If the input is invalid, prompt again
                if (!userInput.equalsIgnoreCase("H") && !userInput.equalsIgnoreCase("P") &&
                    !userInput.equalsIgnoreCase("E") && !userInput.equalsIgnoreCase("S") &&
                    !userInput.equalsIgnoreCase("B")) {
                    
                    con.print("Ummm... idk what that is, try again: ");
                    userInput = con.readLine();  // Read new input
                } else {
                    // If a valid and different input is entered, update Strwhere
                    Strwhere = userInput;
                    StrUPWhere = Strwhere;  
                    break;  // Exit the loop if valid and different input is provided
                }
            }
        }

        return Strwhere;  // Return the updated Strwhere
    }
}
