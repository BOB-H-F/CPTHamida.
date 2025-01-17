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
        }
    }
}
