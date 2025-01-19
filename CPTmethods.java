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

    // Method for string-based screen operations
    public static void StrScreen(String Strwhere, Console con) {
        if (Strwhere.equalsIgnoreCase("E")) {
            con.closeConsole(); // Close the console
        } else if (Strwhere.equals("P")) {
            con.clear();
            // Additional logic for 'P' can be added here
        }
    }

    // Method to read scores, sort them using bubble sort, and print names with scores
    // Adds numbers beside each entry and marks the logged-in user with "(you)"
    public static void Intleaderbord(Console con, String loggedInUser) {
        TextInputFile scoreFile = new TextInputFile("Score.txt");  // Assuming "Score.txt" contains names and scores
        String[] names = new String[100];  // Array to store names (adjust size as needed)
        int[] scores = new int[100];       // Array to store scores
        int count = 0;                     // Counter to keep track of how many records we have

        // First loop to read names and scores from the file
        String name;
        while ((name = scoreFile.readLine()) != null) {
            int score = Integer.parseInt(scoreFile.readLine());
            names[count] = name;
            scores[count] = score;
            count++;
        }
        scoreFile.close();

        // Bubble sort to sort the scores in descending order, adjusting names accordingly
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (scores[j] < scores[j + 1]) {
                    // Swap the scores
                    int tempScore = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = tempScore;

                    // Swap the corresponding names
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }

        // Second loop to print the sorted names and scores
        for (int i = 0; i < count; i++) {
            String displayName = names[i];
            // If the current name matches the logged-in user, add "(you)"
            if (names[i].equalsIgnoreCase(loggedInUser)) {
                displayName += " (you)";
                
            }
            // Print the ranking number, name, and score
            con.println("top "+(i + 1) + ". " + displayName + " with: " + scores[i]);
        }
    }
}
