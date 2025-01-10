import arc.*;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables for All
        String StrWhere;

        /** Front interface */

        con.println("To exit press E");
		con.println("To play game press P");
		con.print("Which command would you like to proceed with?: ");	
        StrWhere = con.readLine();

        CPTmethods.StrScreen(StrWhere, con);
        
        /** Game play */
        
        // Variables
        String strLogInName;
      
        
        //play screen login: 
       if (StrWhere.equals("P")){
		 con.println("Login");
		TextOutputFile Score = new TextOutputFile("Score.txt", true);
		
		con.print("Enter Name: ");
		strLogInName = con.readLine();
		Score.close();
		

	  }
      
       
    }
}
