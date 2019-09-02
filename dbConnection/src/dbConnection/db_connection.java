package dbConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import fileReading.FileReading.file;



public class db_connection {
	
	static class oneLine {
		String string1;
		String string2;
		String string3;
		String string4;
		String string5;
		String string6;
		String string7;
		public oneLine () {
			String string1 = "";
			String string2 = "";
			String string3 = "";
			String string4 = "";
			String string5 = "";
			String string6 = "";
			String string7 = "";
		}
	}
	
	private static final String file_path = "/Users/siyuanwang/Desktop/xingzhan/test.TXT";
	public static void main(String args[]){  
		Connection con = null;
		Statement stmt = null;
		//java.sql.PreparedStatement
		//excuteBatch
		String sql;
		BufferedReader reader = null;
		ResultSet rs;
		//BufferedReader reader2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(  
					//"jdbc:mysql://112.74.109.18:3306/rpmdb","rpmdb","a9NajZWz");  
					"jdbc:mysql://112.74.109.184:3306/rpm","rpmdb","a9NajZWz");  
			if (con == null)
				throw new java.lang.NullPointerException("connection is null");
			
			stmt =  con.createStatement();
			

	    	//Reading from file and writing to the database
			reader = new BufferedReader(new FileReader(file_path)); ////stub filename
			String line = reader.readLine();
			int count = 0;
			while (line != null) {
				count ++;
				line = reader.readLine();
			}
			reader.close();
	      
			reader = new BufferedReader(new FileReader(file_path)); //stub filename
			line = reader.readLine();
			//ArrayList<file> lines = new ArrayList<file> (count); //file = seven breaked up strings
	      
			for (int i = 0; i < count; i++) {
				String[] array = new String[7];
				array = line.split(",");
				//assigning values for the object
				oneLine Row = new oneLine();
	    	  
				Row.string1 = array [0]; ///process a query here      out.print(temp.string1 + ",");
				Row.string2 = array [1];
				Row.string3 = array [2]; 
				Row.string4 = array [3]; 
				Row.string5 = array [4];
				Row.string6 = array [5];
				Row.string7 = array [6];
				//lines.add(temp); 	
	    	  
				sql =  "INSERT INTO table_for_testing(s1, s2, s3, s4, s5, s6, s7, modified_date) VALUES( '"
	    			  + Row.string1 + "', '" +  Row.string2 + "', '" + Row.string3
	    			  + "', '" + Row.string4 + "', '" + Row.string5 + "', '" + Row.string6
	    			  + "', '" + Row.string7 + "', " + "NOW());";
				System.out.println(sql);
	    
				stmt.execute(sql);
	    	  //System.out.println("Writing success!");
			  //adding data into the table

				line = reader.readLine();
			}
			reader.close();
			System.out.println("------------------ File Writing Done! ------------------");
			
			
			//////////////////////////////////////////search in the database///////////////////////////////
			String column = "s6";
			String nameOfTable = "table_for_testing";
			String condition = "id = 10006";
			sql = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
			//stmt.addBatch(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
                String s6 = rs.getString("s6");
                
                System.out.println("s6: " + s6);
                System.out.println("------------------ Data Retriving Done! ------------------");
            }
			//////////////////////////////////////////deleting from the database///////////////////////////////
			condition = "id = 10000";
			sql = "DELETE FROM " + nameOfTable + " WHERE " + condition;
			stmt.execute(sql);
			System.out.println("------------------ Deleting Done ------------------");
			//////////////////////////////////////////update in the database///////////////////////////////	
			condition = "id = 10005";
			sql = "UPDATE " + nameOfTable + " SET " + column + " = '    new content   '" + " WHERE " + condition;
			stmt.execute(sql);
//////////////////////////////////
			sql = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
			//stmt.addBatch(sql);
			rs = stmt.executeQuery(sql);
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
