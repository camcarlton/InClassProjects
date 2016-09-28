package HRS;

import java.util.Scanner;

public class Timer {
	static String docsID[] = { "FEEJOE2", "BRANOT2", "WESKEE2", "BIGZAC2", "SHOMIK2" };
	static String DocName[] = { "joe", "notouch", "kee", "bigerson", "showman" };
	static String nurseID[] = { "HALWAY3", "CROILE3", "EMASHA3", "STAISA3", "GUNCLE3" };
	static String dayOfWeek[] = { "monday", "tuesday", "wednesday", "thursday", "friday" };
	static String nurse[] = { "Waylon Hallman", "Ilse Crouch", "Shalanda Emanuel", "Isadora Stamm", "Cleo Gunter" };
	static String doctor[] = { "Joe Feelgood", "Brandy Notouch", "West Kee", "Zachary Bigerson", "Mike Showman" };

	public static void createApp(String DocId, String PId) {
		Scanner in = new Scanner(System.in);
		String app;
		while (true) {
			System.out.print("Appointment day of the week(monday~friday): ");
			app = in.next();
			if (app.equals(dayOfWeek[0]) || app.equals(dayOfWeek[1]) || app.equals(dayOfWeek[2])
					|| app.equals(dayOfWeek[3]) || app.equals(dayOfWeek[4])) {
				break;
			}
			System.out.println("\nInvalid day of the week, try again");
		}

		if (DocId.equals(docsID[0])) {
			System.out.println("Time open for Joe Feelgood on " + app + " : ");
			String table = app + "_" + DocName[0];
			connection.showApps(table);

		} else if (DocId.equals(docsID[1])) {
			System.out.println("Time open for Brandy Notouch on " + app + " : ");
			String table = app + "_" + DocName[1];
			connection.showApps(table);

		} else if (DocId.equals(docsID[2])) {
			System.out.println("Time open for West Kee on " + app + " : ");
			String table = app + "_" + DocName[2];
			connection.showApps(table);
		} else if (DocId.equals(docsID[3])) {
			System.out.println("Time open for Zachary Bigerson on " + app + " : ");
			String table = app + "_" + DocName[3];
			connection.showApps(table);
		} else if (DocId.equals(docsID[4])) {
			System.out.println("Time open for Mike Showman on " + app + " : ");
			String table = app + "_" + DocName[4];
			connection.showApps(table);
		}

		// patient appointment update
		System.out.println();
		update(PId, app, DocId);

	}

	public static void update(String PId, String day, String DocId) {
		Scanner in = new Scanner(System.in);
		String appTime;
		int hr = 0;
		int min = 0;

		while (true) {
			System.out.print("Enter -1 if you want to go back. ");
			System.out.print("What time on that day (0:00) : ");
			appTime = in.next();
			if (appTime.equals("-1")) {
				break;
			}
			try {
				hr = Integer.parseInt(appTime.split(":")[0]);
				min = Integer.parseInt(appTime.split(":")[1]);
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
			System.out.println("Opens from 8 am to 5 pm, try again\n");
		}

		if (appTime.equals("-1")) {
			createApp(DocId, PId);
			return;
		}

		if (DocId.equals(docsID[0])) {
			connection.insertApp(PId, hr, min, day, DocName[0]);
		} else if (DocId.equals(docsID[1])) {
			connection.insertApp(PId, hr, min, day, DocName[1]);
		} else if (DocId.equals(docsID[2])) {
			connection.insertApp(PId, hr, min, day, DocName[2]);
		} else if (DocId.equals(docsID[3])) {
			connection.insertApp(PId, hr, min, day, DocName[3]);
		} else if (DocId.equals(docsID[4])) {
			connection.insertApp(PId, hr, min, day, DocName[4]);
		}
		System.out.println("appointmenet created");
	}

	public static boolean checkDocId(String DocId) {
		for (int i = 0; i < 5; i++) {
			if (docsID[i].equals(DocId)) {
				return true;
			}
		}
		return false;
	}

	public static int postD(String docId) {
		for (int i = 0; i < 5; i++) {
			if (docsID[i].equals(docId)) {
				return i;
			}
		}
		return 0;
	}

	public static String getDocName(String DID) {
		if (DID.equals(docsID[0])) {
			return DocName[0];
		} else if (DID.equals(docsID[1])) {
			return DocName[1];
		} else if (DID.equals(docsID[2])) {
			return DocName[2];
		} else if (DID.equals(docsID[3])) {
			return DocName[3];
		} else if (DID.equals(docsID[4])) {
			return DocName[4];
		}
		return "";
	}
}
