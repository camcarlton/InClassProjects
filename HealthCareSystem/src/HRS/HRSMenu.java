package HRS;

import java.util.InputMismatchException;
import java.util.Scanner;
 
public class HRSMenu extends Schedule {
	public static void main(String args[]) {
		
		Staff s = new Staff();
		while (true) {
			connection a = new connection();
			int val = s.post;
			switch (val) {  
			case 1: // staff
				System.out.println("staff");
				displayS(s);
				s.logOut();
				break;
			case 2: // doctor
				System.out.println("doctor");
				lookSch(s);
				s.logOut(); 
				break;
			case 3: // nurse
				System.out.println("nurse");
				lookSch(s);
				s.logOut();
				break;
			default:
				System.out.println("this should not work!!!");
			}
		}
	}

	public static void displayS(Staff s) {
		while (true) { 
			System.out.println("_____________________________________");
			System.out.println("		        HRS Menu			 ");
			System.out.println("_____________________________________");
			System.out.println("\nLoged In as: " + s.getDB()[s.hold][1]);
			System.out.println("	1) Make an Appointment");
			System.out.println("	2) Change an Appointment");
			System.out.println("	3) Cancel an Appointment");
			System.out.println("	4) Log out");
			int val = 0;
			try {
				Scanner input = new Scanner(System.in);
				val = input.nextInt();
			} catch (InputMismatchException e) {
			}
			switch (val) {
			case 1:
				Schedule.makeApp();
				break;
			case 2:
				Schedule.change();
				break;
			case 3:
				Schedule.cancel();
				break;
			case 4:
				System.out.println("logged out");
				connection.discon();
				return;
			default:
				System.out.println("Invalid input, try again");

			}
			if(val==4){
				break;
			}
		}
	}

	public static void lookSch(Staff s) {
		while (true) {
			System.out.println("_____________________________________");
			System.out.println("		        HRS Menu			 ");
			System.out.println("_____________________________________");
			System.out.println("\nLoged In as: " + s.getDB()[s.hold][1]);
			System.out.println("	1) Look up Schedules");
			System.out.println("	2) Log out");

			int val = 0;
			try {
				Scanner input = new Scanner(System.in);
				val = input.nextInt();
			} catch (InputMismatchException e) {
			}
			switch (val) {
			case 1:
				Schedule.lookUp(s.getDB()[s.hold][1]);
				break;
			case 2:
				System.out.println("logged out\n");
				return;
			default:
				System.out.println("Invalid input, try again");

			}
			if(val==2){
				break;
			}
		}
	}
}