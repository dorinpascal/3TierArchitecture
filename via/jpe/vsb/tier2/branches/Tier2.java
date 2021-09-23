/*
 * 12.09.2018 Original version
 */


package via.jpe.vsb.tier2.branches;

import java.util.Scanner;

public class Tier2
{
	public static void main( String[] args )
	{
		try {
			Scanner scanner=new Scanner(System.in);
			String y=scanner.nextLine();

			Tier2Controller controller = new Tier2Controller(y);

			System.out.println( "Tier2 ready" );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
}
