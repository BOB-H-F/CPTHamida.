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

        // play screen login: 
        if (StrWhere.equals("P")) {
            con.println("Login");
            TextOutputFile Score = new TextOutputFile("Login.txt", true);  // Open the file in append mode

            con.print("Enter Name: ");
            strLogInName = con.readLine();

            // Write the name into the file
            Score.println(strLogInName);  

            // Ask the user for their score
            con.print("Enter your score: ");
            dblMark = con.readDouble();

            // Write the score into the file
            Score.println(dblMark);  


            Score.close();  

            
        }
    }
}
