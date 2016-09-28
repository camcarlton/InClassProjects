package HRS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connection {
	static Connection cnt;

	public connection() {
		try {
			Connection a = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practice", "root", "qhgus9857");
			cnt = a;
			System.out.println("MySQL connected\n");

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static void view() {
		try {
			Statement stmt = cnt.createStatement();

			// 3. Query.
			ResultSet rst = stmt.executeQuery("select * from monday");
			while (rst.next()) {
				System.out.println(rst.getString("DocID") + "" + rst.getString("PatID"));
			}
		} catch (Exception e) {
			System.out.println("view error");
		}
	}

	public static boolean searchInDB(String info) {
		String sql = info;
		try {
			Statement stmt = cnt.createStatement();
			ResultSet rst = stmt.executeQuery("select * from patient");
			while (rst.next()) {
				if (rst.getString("ID").equals(info)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("insert error");
		}
		return false;
	}

	public static void delete(String Pid, int hr, int min, String day, String doc) {
		String sql = "";
		if (min == 0) {
			sql = "update " + day + "_" + doc + " set " + hr + "_" + min + "0='open' where " + hr + "_" + min + "0='"
					+ Pid + "'";
		} else if (min == 30) {
			sql = "update " + day + "_" + doc + " set " + hr + "_" + min + "='open' where " + hr + "_" + min + "='"
					+ Pid + "'";
		}
		try {
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("update error");
		}
	}

	public static void discon() {
		try {
			cnt.close();
			System.out.println("MySQL disconnected\n");
		} catch (SQLException e) {
			System.out.println("MySQL disconnect fail");
		}
	}

	public static void insertId(String pid, String did) {
		String sql = "insert into patient values('" + pid + "', '" + did + "')";
		try {
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("insert error");
		}
	}

	public static void insertApp(String Pid, int hr, int min, String day, String doc) {
		String sql = "";
		if (min == 0) {
			sql = "update " + day + "_" + doc + " set " + hr + "_" + min + "0='" + Pid + "' where " + hr + "_" + min
					+ "0='open'";
		} else if (min == 30) {
			sql = "update " + day + "_" + doc + " set " + hr + "_" + min + "='" + Pid + "' where " + hr + "_" + min
					+ "='open'";
		}
		try {
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("update error");
		}
	}

	public static void showApps(String table) {
		try {
			Statement stmt = cnt.createStatement();
			ResultSet rst = stmt.executeQuery("select * from " + table);
			while (rst.next()) {
				System.out.println("8:00  " + rst.getString("8_00"));
				System.out.println("8:30  " + rst.getString("8_30"));
				System.out.println("9:00  " + rst.getString("9_00"));
				System.out.println("9:30  " + rst.getString("9_30"));
				System.out.println("10:00  " + rst.getString("10_00"));
				System.out.println("10:30  " + rst.getString("10_30"));
				System.out.println("11:00  " + rst.getString("11_00"));
				System.out.println("11:30  " + rst.getString("11_30"));
				System.out.println("1:00  " + rst.getString("1_00"));
				System.out.println("1:30  " + rst.getString("1_30"));
				System.out.println("2:00  " + rst.getString("2_00"));
				System.out.println("2:30  " + rst.getString("2_30"));
				System.out.println("3:00  " + rst.getString("3_00"));
				System.out.println("3:30  " + rst.getString("3_30"));
				System.out.println("4:00  " + rst.getString("4_00"));
				System.out.println("4:30  " + rst.getString("4_30"));
			}
		} catch (Exception e) {
			System.out.println("view error");
		}
	}

	public static String getDocIDFromDB(String Pid) {
		try {
			Statement stmt = cnt.createStatement();
			ResultSet rst = stmt.executeQuery("select * from patient");
			while (rst.next()) {
				if (rst.getString("ID").equals(Pid)) {
					return rst.getString("DocID");
				}
			}
		} catch (Exception e) {
			System.out.println("Doctor not found");
		}
		return "";
	}
	
	public static void lookUpWeek(String docName){
		
		try{
			Statement stmt = cnt.createStatement();
			for(int i = 0; i < 5; i++){
				String table = Timer.dayOfWeek[i] + "_" +docName;
				ResultSet rst = stmt.executeQuery("select * from " + table);
				System.out.println("\n"+ docName + "'s " + Timer.dayOfWeek[i] + " schedule");
				while (rst.next()) {
					System.out.println("8:00  " + rst.getString("8_00"));
					System.out.println("8:30  " + rst.getString("8_30"));
					System.out.println("9:00  " + rst.getString("9_00"));
					System.out.println("9:30  " + rst.getString("9_30"));
					System.out.println("10:00  " + rst.getString("10_00"));
					System.out.println("10:30  " + rst.getString("10_30"));
					System.out.println("11:00  " + rst.getString("11_00"));
					System.out.println("11:30  " + rst.getString("11_30"));
					System.out.println("1:00  " + rst.getString("1_00"));
					System.out.println("1:30  " + rst.getString("1_30"));
					System.out.println("2:00  " + rst.getString("2_00"));
					System.out.println("2:30  " + rst.getString("2_30"));
					System.out.println("3:00  " + rst.getString("3_00"));
					System.out.println("3:30  " + rst.getString("3_30"));
					System.out.println("4:00  " + rst.getString("4_00"));
					System.out.println("4:30  " + rst.getString("4_30"));
				}
			}
		}catch(Exception e){
			System.out.println("exception occured");
		}
	}
}
