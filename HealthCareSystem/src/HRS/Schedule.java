package HRS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Schedule {

	public static void makeApp() {
		Scanner input = new Scanner(System.in);
		String fn, ln, b;
		while (true) {
			System.out.print("Patient's Full name : ");
			fn = input.next();
			ln = input.next();
			System.out.print("Patient's date of birth(MM/DD/YYYY) : ");
			b = input.next();
			if ((fn.length() + ln.length()) > 4 && checkVal(b)) {
				break;
			}
			System.out
					.println("\nToo Short name or invalid date of birth, try again");
		}

		try {
			if (Patient.search(fn, ln, b)) {
				Patient.noob();
			}
		} catch (Exception e) {
			System.out.println("File Not Found(Patient)");
		}
		
		String Pid = Patient.createId(b, ln);
		Timer.createApp(connection.getDocIDFromDB(Pid), Patient.createId(b, ln));

	}

	public static void change() {
		Scanner input = new Scanner(System.in);
		String fn, ln, b;
		String time ="";
		String day = "";
		int hr = 0; int min = 0;
		while (true) {
			System.out.print("Patient's Full name : ");
			fn = input.next();
			ln = input.next();
			System.out.print("Patient's date of birth(MM/DD/YYYY) : ");
			b = input.next();
			if ((fn.length() + ln.length()) > 4 && checkVal(b)) {
				break;
			}
			System.out
					.println("\nToo Short name or invalid date of birth, try again");
		}

		if (Patient.search(fn, ln, b)) {
			System.out.println("Patient does not exist");
			System.out
					.print("Do you want to make new patient information? (y/n) ");
			String answer = input.next();
			if (answer.equals("y")) {
				makeApp();
			} else {
				return;
			}
		} else {
			while (true) {
				System.out.print("\nOld Appointment day(monday~friday) : ");
				day = input.next();
				System.out.print("What time(0:00): ");
				time = input.next();
				try {
					hr = Integer.parseInt(time.split(":")[0]);
					min = Integer.parseInt(time.split(":")[1]);
					if (hr == 8 && min == 0 || hr == 8 && min == 30 || hr == 9 && min == 0 || hr == 9 && min == 30
							|| hr == 10 && min == 0 || hr == 10 && min == 30 || hr == 11 && min == 0
							|| hr == 11 && min == 30 || hr == 1 && min == 0 || hr == 1 && min == 30 || hr == 2 && min == 0
							|| hr == 2 && min == 30 || hr == 3 && min == 0 || hr == 3 && min == 30 || hr == 4 && min == 0
							|| hr == 4 && min == 30) {
						break;
					}
				} catch (Exception e) {
					System.out.println("invalid input");
				}
				System.out.println("\nInvalid date or time, try again");
			}
		}
		String Pid = Patient.createId(b, ln);
		connection.delete(Pid, hr, min, day, Timer.getDocName(connection.getDocIDFromDB(Pid)));
		Timer.createApp(connection.getDocIDFromDB(Pid), Patient.createId(b, ln));

	}

	public static void cancel() {
		Scanner input = new Scanner(System.in);
		String fn, ln, b, nDate;
		String time;
		int hr = 0; int min = 0;
		while (true) {
			System.out.print("Patient's Full name : ");
			fn = input.next();
			ln = input.next();
			System.out.print("Patient's date of birth(MM/DD/YYYY) : ");
			b = input.next();
			if ((fn.length() + ln.length()) > 4 && checkVal(b)) {
				break;
			}
			System.out
					.println("\nToo Short name or invalid date of birth, try again");
		}
		String Pid = Patient.createId(b, ln);
		if (Patient.search(fn, ln, b)) {
			System.out.println("No Patient or Appointment");
		} else {
			while (true) {

				System.out
						.print("Appointment day you want to delete(monday~friday) : ");
				nDate = input.next();
				System.out.print("What time(0:00) : ");
				time = input.next();
				try {
					hr = Integer.parseInt(time.split(":")[0]);
					min = Integer.parseInt(time.split(":")[1]);
					if (hr == 8 && min == 0 || hr == 8 && min == 30 || hr == 9 && min == 0 || hr == 9 && min == 30
							|| hr == 10 && min == 0 || hr == 10 && min == 30 || hr == 11 && min == 0
							|| hr == 11 && min == 30 || hr == 1 && min == 0 || hr == 1 && min == 30 || hr == 2 && min == 0
							|| hr == 2 && min == 30 || hr == 3 && min == 0 || hr == 3 && min == 30 || hr == 4 && min == 0
							|| hr == 4 && min == 30) {
						break;
					}
				} catch (Exception e) {
					System.out.println("invalid input");
				}
				System.out.println("\nInvalid date or time, try again");
			}
			System.out
					.println("\nDo you want to delete this patient's information? (y/n) : ");
			String answer = input.next();
			if (answer.equals("y")) {
				connection.delete(Pid, hr, min, nDate, Timer.getDocName(connection.getDocIDFromDB(Pid)));
			} else {
				return;
			}
		}
		System.out.println("The Appointment Deleted");
	}

	public static boolean checkVal(String birth) {
		int month = 0, day = 0, year = 0;
		try {
			month = Integer.parseInt(birth.split("/")[0]);
			day = Integer.parseInt(birth.split("/")[1]);
			year = Integer.parseInt(birth.split("/")[2]);
		} catch (Exception e) {
		}
		if (month < 1 || day < 1 || month > 13) {
			return false;
		} else if (month == 2 && year % 100 != 0 && year % 4 == 0) {
			if (day > 29) {
				return false;
			}
		} else if (month == 2 && day >= 29) {
			if (year % 100 == 0 || year % 4 != 0) {
				return false;
			}
		} else if (month == 1 || month == 3 || month == 5 || month == 7
				|| month == 8 || month == 10 || month == 12) {
			if (day > 31) {
				return false;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day > 30) {
				return false;
			}
		}
		return true;
	}

	public static void lookUp(String docId) { 
		String docName = Timer.getDocName(docId);
		connection.lookUpWeek(docName);
	}
}
