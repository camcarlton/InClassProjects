package HRS;

import java.util.Scanner;

public class Patient {
	static boolean called = false;

	public static boolean search(String first, String last, String birthday) {
		String id = createId(birthday, last);

		if (connection.searchInDB(id)) {
			return false;
		}
		System.out.println("Patient Not Found");
		return true;
	}

	public static void noob() {
		Scanner input = new Scanner(System.in);
		String first, last, birthday, dId;
		while (true) {
			System.out.print("Enter patient's name : ");
			first = input.next();
			last = input.next();
			System.out.print("Enter patient's birthday(MM/DD/YYYY) : ");
			birthday = input.next();
			System.out.print("Enter primary doctor ID : ");
			dId = input.next();
			if ((first.length() + last.length()) > 4 && Schedule.checkVal(birthday) && Timer.checkDocId(dId)) {
				break;
			}
			System.out.println("\nToo Short name, invalid date of birth or invalid doctor's ID, try again");
		}

		String id = createId(birthday, last);
		connection.insertId(id, dId);
	}

	public static String createId(String birth, String last) {
		String out = "";
		try {
			out = birth.split("/")[0] + birth.split("/")[1] + birth.split("/")[2] + last.charAt(0) + last.charAt(1)
					+ last.charAt(2);
		} catch (Exception e) {
			System.out.println("\nInvalid Date");
		}
		return out;

	}
}
