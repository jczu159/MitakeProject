package com.mirake.server.bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

public class mirakeBo {

	// final static String driver = "com.mysql.cj.jdbc.Driver";
	// // localhost指本機，也可以用本地ip地址代替，3306為MySQL資料庫的預設埠號，“user”為要連線的資料庫名
	// final static String url = "jdbc:mysql://45.32.49.87:3306/myforex";
	// // 填入資料庫的使用者名稱跟密碼
	// final static String dbUserName = "root";
	// final static String dbUserPassword = "36f57bc6fd";

	public static void main(String[] args) throws SQLException {
		HashMap<String, LinkedList<String>> testVal = new HashMap<>();

		testVal = mirakeBo.getCustInformation();
		System.out.println(testVal.get("MTK1624089311"));
//		testVal.put("USERNAME", "王阳明");
//		testVal.put("USERPHONE", "0988664154");
//		testVal.put("USEREMAIL", "test0095154487@gmail.com");
//		testVal.put("ADDRESS", "台北市中正东西一号三楼之一");
//		java.util.Random rnd = new java.util.Random();
//		rnd.setSeed(System.currentTimeMillis());
//		testVal.put("CUSTOMERNUMBER", "MTK" + rnd.nextInt());
//		String ex = addNewMember(testVal);

	}

	public static String addNewMember(HashMap<String, Object> inserVal) {
		Connection conn = null;
		Statement stmt = null;
		String successCode = "";
		// 連接MySQL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			System.out.println("連接成功MySQL");
			stmt = conn.createStatement();

			String sql = "INSERT INTO `fo_customerlist` (`USERNAME`, `USERPHONE`, `USEREMAIL`, `ADDRESS` ,`CREATETIME`, `CUSTOMERNUMBER` ,`CUSTOMER_BENEFIT_EXPIRES`) VALUES ('"
					+ inserVal.get("USERNAME") + "', '" + inserVal.get("USERPHONE") + "', '" + inserVal.get("USEREMAIL")
					+ "', '" + inserVal.get("ADDRESS") + "' , NOW(),'" + inserVal.get("CUSTOMERNUMBER") + "','"+ inserVal.get("CUST_MEMBER_DATATIME")+"');";

			System.out.println("使用DB语法:" + sql);
			stmt.executeUpdate(sql);

			// 撈出剛剛新增的資料
			// ResultSet result = stmt.executeQuery("SELECT * FROM
			// fo_management");
			// while (result.next()) {
			// System.out.print(result.getInt(1) + "\t");
			// System.out.print(result.getString(2) + "\t");
			// System.out.print(result.getString(3) + "\t");
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successCode = "-1";
			try {
				conn.rollback();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				successCode = "-1";
			}
		} finally {
			try {
				stmt.close();
				conn.close();
				System.out.println("關閉搜尋引擎以及關閉DB連線");
				successCode = "0";
			} catch (SQLException e) {
				successCode = "-1";
				e.printStackTrace();
			}
		}
		return successCode;
	}
	
	
	public static String editMember(HashMap<String, Object> editMap) {
		Connection conn = null;
		Statement stmt = null;
		String successCode = "";
		// 連接MySQL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			System.out.println("連接成功MySQL");
			stmt = conn.createStatement();
			
	
			
			String sql = "	UPDATE fo_customerlist SET  USERNAME ='" +editMap.get("USERNAME") +"',USERPHONE ='"+editMap.get("USERPHONE")+"',USEREMAIL='" + editMap.get("USEREMAIL")+
					"',ADDRESS='" +  editMap.get("ADDRESS") +"',UPDATETIME=NOW()";
			
			if(!"".equals(editMap.get("CUST_MEMBER_DATATIME"))) {
				sql = sql+ ",CUSTOMER_BENEFIT_EXPIRES ='"+ editMap.get("CUST_MEMBER_DATATIME") +"' WHERE CUSTOMERNUMBER ='" + editMap.get("CUSTOMERNUMBER")+"'";		
			}else {
				sql = sql+ "WHERE CUSTOMERNUMBER =  '" + editMap.get("CUSTOMERNUMBER")+"'";				
			}
			
	

			System.out.println("使用DB语法:" + sql);
			stmt.executeUpdate(sql);

			// 撈出剛剛新增的資料
			// ResultSet result = stmt.executeQuery("SELECT * FROM
			// fo_management");
			// while (result.next()) {
			// System.out.print(result.getInt(1) + "\t");
			// System.out.print(result.getString(2) + "\t");
			// System.out.print(result.getString(3) + "\t");
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successCode = "-1";
			try {
				conn.rollback();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				successCode = "-1";
			}
		} finally {
			try {
				stmt.close();
				conn.close();
				System.out.println("關閉搜尋引擎以及關閉DB連線");
				successCode = "0";
			} catch (SQLException e) {
				successCode = "-1";
				e.printStackTrace();
			}
		}
		return successCode;
	}

	/**
	 * @author IMI-JAVA-Ryan 刪除會員資料 該筆 傳入會員編號 (亂數那個)
	 * @return
	 * @throws SQLException
	 */
	public static String delectMember(String customerNumber) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		String successCode = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			stmt = conn.createStatement();
			String sql = "DELETE FROM fo_customerlist WHERE CUSTOMERNUMBER = '" + customerNumber + "';";
			stmt.executeUpdate(sql);
			conn.commit();
		} catch (Exception e) {
			System.out.println("資訊錯誤" + e);
			conn.rollback();
			stmt.close();
			conn.close();
			successCode = "-1";
		} finally {

			stmt.close();
			conn.close();
			successCode = "0";

		}
		return successCode;

	}

	public static String verificationAccount(String userName, String password) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		String successCode = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM fo_management  WHERE ACCOUNT='" + userName + "';";
			ResultSet result = stmt.executeQuery(sql);
			int rowCount = result.getRow();

			if (rowCount == 0) {
				System.out.println("查詢不到任何帳號紀錄:");
				successCode = "-1";
			}
			if (result.next()) {

				System.out.print(result.getInt(1) + "\t");
				System.out.print(result.getString(2) + "\t");
				System.out.print(result.getString(3) + "\t");

				if (result.getString(2).equals(userName) && result.getString(3).equals(password)) {
					System.out.println("較驗成功");
					successCode = "0";
				} else {
					successCode = "-1";
				}

			}

		} catch (Exception e) {
			stmt.close();
			conn.close();
			successCode = "-1";
		} finally {

			stmt.close();
			conn.close();
		}
		return successCode;

	}

	/**
	 * @author IMI-JAVA-Ryan 取得三竹資訊資訊的帳號密碼
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, Object> getMitakeSettingVal() throws SQLException {

		HashMap<String, Object> itMap = new HashMap<>();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			stmt = conn.createStatement();
			String sql = "SELECT MITAKEACCOUNT,MITAKEPASSWORD,MITAKEURL FROM fo_systemconfig ;";
			ResultSet result = stmt.executeQuery(sql);

			if (result.next()) {
				itMap.put("MITAKE_ACCOUNT", result.getString(1) == null ? "" : result.getString(1));
				itMap.put("MITAKE_PASSWORD", result.getString(2) == null ? "" : result.getString(2));
				itMap.put("MITAKE_URL", result.getString(3) == null ? "" : result.getString(3));
				System.out.print(result.getString(1) + "\t");
				System.out.print(result.getString(2) + "\t");
				System.out.print(result.getString(3) + "\t");
			}

		} catch (Exception e) {
			stmt.close();
			conn.close();
		} finally {

			stmt.close();
			conn.close();
		}

		return itMap;

	}

	public static HashMap<String, LinkedList<String>> getCustInformation() throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		HashMap<String, LinkedList<String>> custHashMap = new HashMap<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(datasource);
			stmt = conn.createStatement();
			String sql = "SELECT ID,CUSTOMERNUMBER,USERNAME,USERPHONE FROM fo_customerlist ;";
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				LinkedList<String> linkVal = new LinkedList<>();
				linkVal.add(result.getString("ID"));
				linkVal.add(result.getString("CUSTOMERNUMBER"));
				linkVal.add(result.getString("USERNAME"));
				linkVal.add(result.getString("USERPHONE"));
				custHashMap.put(result.getString("CUSTOMERNUMBER"), linkVal);
			}
		} catch (Exception e) {
			stmt.close();
			conn.close();
		} finally {
			stmt.close();
			conn.close();

		}

		return custHashMap;
	}

	/**
	 * @author admin 取得JDBC 連線設定 統整在這裡 2019/08/04 過濾掉不必要的 攏CODE
	 * 
	 * @return
	 */
	public static Connection getJDBCConneciton() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("取得JDBC clas失敗 " + e);
		}
		// System.out.println("連接成功MySQLToJava");
		// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
		String datasource = "jdbc:mysql://45.32.49.87:3306/myforex?user=root&password=36f57bc6fd&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
		// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
		try {
			conn = DriverManager.getConnection(datasource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("取得JDBC 連驗過程中發生 SQL錯誤" + e);
		}

		return conn;

	}

}
