package via.jpe.vsb.tier2.mainServer;


public class Tier2Main {
    public static void main( String[] args )
    {
        try {

            Tier2MainController controller = new Tier2MainController();

            System.out.println( "Tier2 Main ready" );
        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
}
