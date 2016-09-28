package HRS;

import java.util.Scanner;

public class Staff extends Schedule {
	static private String DB[][] = {
			{ "Joe Feelgood", "FEEJOE2", "ifeel2good" },
			{ "Brandy Notouch", "BRANOT2", "touchmenow" },
			{ "West Kee", "WESKEE2", "westlife" },
			{ "Zachary Bigerson", "BIGZAC2", "bigzach" },
			{ "Mike Showman", "SHOMIK2", "dashow" },
			{ "Waylon Hallman", "HALWAY3", "bestpuppy79" },
			{ "Ilse Crouch", "CROILE3", "kindolive18" },
			{ "Shalanda Emanuel", "EMASHA3", "angrymask15" },
			{ "Isadora Stamm", "STAISA3", "niceroom20" },
			{ "Cleo Gunter", "GUNCLE3", "amberotter75" },
			{ "Elaina Barron", "BARELA1", "coldmark88" },
			{ "Chiquita Jolley", "JOLCHI1", "jadezebra98" },
			{ "Lisbeth Turley", "TURLIS1", "olivekoala34" },
			{ "Ronni Nava", "NAVRON1", "fastring80" },
			{ "Ethelene Elder", "ELDETH1", "whitmustang48" } };
	static boolean streetcred = true;
	static int post = -1;
	static int hold = -1; 
	public Staff() {
		do {
			login();
		} while (streetcred);
	}

	public static void login() {
		System.out.print("Full Name: ");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		System.out.print("Password: ");
		String p = input.next();

		for (int i = 0; i < 15; i++) {
			if (name.equals(getDB()[i][0])) {
				if (p.equals(getDB()[i][2])) {
					System.out.println("Welcome " + getDB()[i][1]);
					post = Integer.parseInt(""+getDB()[i][1].charAt(6));
					hold = i;
					streetcred = false;
				}
			}
		}
		if (post == -1) {
			System.out.println("Invalid name or password. \n");
		}
	}


	public static String[][] getDB() {
		return DB;
	}
	
	public static void logOut(){
		streetcred = true;
		post = -1;
		
		do {
			login();
		} while (streetcred);
		if (post == -1) {
			System.out.println("Invalid name or password. \n");
		}

	}
}
