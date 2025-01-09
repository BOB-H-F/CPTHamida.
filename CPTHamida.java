import arc.*;

public class CPTHamida {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables
        String StrWhere;

        /** Front interface */
        con.print("Which command would you like to proceed with? ");
        con.println("To exit press E:");
        StrWhere = con.readLine();

        CPTmethods.StrPage(StrWhere, con);
    }
}
