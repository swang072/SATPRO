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
	
	private static void readingData(String nameOfTable, String column, String condition, Connection con) throws SQLException {
		String sql = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
            String s6 = rs.getString("s6");
            
            System.out.println("s6: " + s6);
            System.out.println("------------------ Data Retriving Done! ------------------");
        }
	}
	private static void deletingData(String nameOfTable, String condition, Connection con) throws SQLException {
		String sql = "DELETE FROM " + nameOfTable + " WHERE " + condition;
		Statement stmt = con.createStatement();
		stmt.execute(sql);
		System.out.println("------------------ Deleting Done! ------------------");
	}
	
	private static void updatingData(String nameOfTable, String column, String condition, Connection con) throws SQLException {
		String sql = "UPDATE " + nameOfTable + " SET " + column + " = '    new content   '" + " WHERE " + condition;
		Statement stmt = con.createStatement();
		stmt.execute(sql);
		System.out.println("------------------ Updating Done! ------------------");
		
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
		logCostTime(startTime);			 //print out time used for file reading
		return lines;
	}

	
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
			
			
			ps = con.prepareStatement(sql);
			ArrayList<String> lines = parse();
			long startTime = System.currentTimeMillis();
			for (String line : lines) {
				OneLine row = new OneLine(line);
				row.setValue(ps);
				ps.executeUpdate();
			}

						
			logCostTime(startTime);		//print out time used for normal writing
			
			
			con.setAutoCommit(false);

			startTime = System.currentTimeMillis();		
			for (String line : lines) {
				OneLine row = new OneLine(line);
				row.setValue(ps);
				ps.addBatch();
			}
			

			ps.executeBatch();
			
			logCostTime(startTime);  //print out time used for batching
			
			
			
			System.out.println("------------------ File Writing Done! ------------------");
			
			
			
			
			//////////////////////////////////////////search in the database///////////////////////////////
			String column = "s6";
			String nameOfTable = "table_for_testing";
			String condition = "id = 10001";
			String sql2;
			
			readingData(nameOfTable, column, condition, con);
			
			
			//////////////////////////////////////////deleting from the database///////////////////////////////
			//stmt =  con.createStatement();
			condition = "id = 10006";
			deletingData(nameOfTable, condition, con);
			
			
			//////////////////////////////////////////update in the database///////////////////////////////	
			condition = "id = 10005";	//updating id. Update and read from a new row

			updatingData(nameOfTable, column, condition, con);

			readingData(nameOfTable, column, condition, con);
			
			
			
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
