package dbConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import fileReading.FileReading.file;



public class db_connection {
	
//	public class OneLine {
//		String string1;
//		String string2;
//		String string3;
//		String string4;
//		String string5;
//		String string6;
//		String string7;
//		public OneLine () {
//			String string1 = "";
//			String string2 = "";
//			String string3 = "";
//			String string4 = "";
//			String string5 = "";
//			String string6 = "";
//			String string7 = "";
//		}
//	}
	
	private static boolean isNotEmpty(String s) {
		return s != null && s.length() > 0;
	}
	
	private static void logCostTime(long startTime) {
		long endTime = System.currentTimeMillis();
		long timeused = (endTime - startTime);
		long total = timeused/ 1000;
		//return total;
		System.out.println("------------------ Time used " + total + "s ------------------");
		
	}
	private static ArrayList<String> parse() {
		BufferedReader reader = null;
		ArrayList<String> lines = new ArrayList<String>();
		long startTime = System.currentTimeMillis(); 
		try {
			reader = new BufferedReader(new FileReader(file_path));
			String line = reader.readLine();
			while (isNotEmpty(line)) {
				lines.add(line);
				line = reader.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logCostTime(startTime);
		return lines;
	}
//	private static OneLine newObject(String line) {
//		String[] array = line.split(",");
//		
//		OneLine row = new OneLine();
//		row.string1 = array [0]; ///process a query here
//		row.string2 = array [1];
//		row.string3 = array [2]; 
//		row.string4 = array [3]; 
//		row.string5 = array [4];
//		row.string6 = array [5];
//		row.string7 = array [6];
//		
//		return row;
//	}
	
	private static final String file_path = "/Users/siyuanwang/Desktop/xingzhan/test.TXT";
	
	private static final String sql = "INSERT INTO table_for_testing(s1, s2, s3, s4, s5, s6, s7, modified_date) VALUES(?, ?, ?, ?, ?, ?, ?, NOW());";
	public static void main(String args[]) {  
		Connection con = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		BufferedReader reader = null;
		ResultSet rs;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(  
					//"jdbc:mysql://112.74.109.18:3306/rpmdb","rpmdb","a9NajZWz");  
					"jdbc:mysql://112.74.109.184:3306/rpm","rpmdb","a9NajZWz");  
			if (con == null)
				throw new java.lang.NullPointerException("connection is null");
			
	    	//Reading from file and writing to the database
			//reader = new BufferedReader(new FileReader(file_path)); ////stub filename
//			String line = reader.readLine();
//			int count = 0;
//			while (line != null) {
//				count ++;
//				line = reader.readLine();
//			}
//			reader.close();
	      
			//reader = new BufferedReader(new FileReader(file_path)); //stub filename
			ps = con.prepareStatement(sql);
			ArrayList<String> lines = parse();
			long startTime = System.currentTimeMillis();
			for (String line : lines) {
				OneLine row = new OneLine(line);
				row.setValue(ps);
				ps.executeUpdate();
			}
			//long startTime = System.currentTimeMillis();
			
			
//			String line = reader.readLine();
//			while (isNotEmpty(line)) {
//				OneLine row = new OneLine(line);
//				row.setValue(ps);
//				ps.executeUpdate();
//				line = reader.readLine();
//			}
						
			logCostTime(startTime);
			
			con.setAutoCommit(false);

			startTime = System.currentTimeMillis();		
			for (String line : lines) {
				OneLine row = new OneLine(line);
				row.setValue(ps);
				ps.addBatch();
			}
			
//			while (isNotEmpty(line)) {
//				OneLine row = new OneLine(line);
//				row.setValue(ps);
//				ps.addBatch();
//				line = reader.readLine();
//			}
			ps.executeBatch();
			
			logCostTime(startTime);
			
			System.out.println("------------------ File Writing Done! ------------------");
			
			
			//////////////////////////////////////////search in the database///////////////////////////////
			String column = "s6";
			String nameOfTable = "table_for_testing";
			String condition = "id = 10006";
			String sql2;
			sql2 = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
			//stmt.addBatch(sql);
			stmt =  con.createStatement();
			rs = stmt.executeQuery(sql2);
			while(rs.next()){
                String s6 = rs.getString("s6");
                
                System.out.println("s6: " + s6);
                System.out.println("------------------ Data Retriving Done! ------------------");
            }
			//////////////////////////////////////////deleting from the database///////////////////////////////
			condition = "id = 10000";
			sql2 = "DELETE FROM " + nameOfTable + " WHERE " + condition;
			stmt.execute(sql2);
			System.out.println("------------------ Deleting Done ------------------");
			//////////////////////////////////////////update in the database///////////////////////////////	
			condition = "id = 10005";
			sql2 = "UPDATE " + nameOfTable + " SET " + column + " = '    new content   '" + " WHERE " + condition;
			stmt.execute(sql2);
//////////////////////////////////
			sql2 = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
			//stmt.addBatch(sql);
			rs = stmt.executeQuery(sql2);
			while(rs.next()){
                String s6 = rs.getString("s6");
                
                System.out.println("s6: " + s6);
                System.out.println("------------------ Data Updating Done! ------------------");
            }
			
			
			
		} catch (Exception e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        } finally {
        	try {
				if (con != null) {
					con.close();
				}
				reader.close();
			} catch (Exception ne) {
				
			}
        }

	}
}
