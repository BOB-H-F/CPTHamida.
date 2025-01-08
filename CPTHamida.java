/** start code */
import arc.*;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables
        String StrWhere;

        /** Front interface */
        con.println("To exit press E:");
        StrWhere = con.readLine();

        // Test 
        if (StrWhere.equals("E")) {
            con.closeConsole(); // Close the console
        }
    }
}
