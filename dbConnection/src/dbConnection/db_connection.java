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
	
	static class file {
		String string1;
		String string2;
		String string3;
		String string4;
		String string5;
		String string6;
		String string7;
		public file () {
			String string1 = "";
			String string2 = "";
			String string3 = "";
			String string4 = "";
			String string5 = "";
			String string6 = "";
			String string7 = "";
		}
	}
	
	
	
	public static void main(String args[]){  
		Connection con = null;
		Statement stmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(  
					//"jdbc:mysql://112.74.109.18:3306/rpmdb","rpmdb","a9NajZWz");  
					"jdbc:mysql://112.74.109.184:3306/rpm","rpmdb","a9NajZWz");  
			if (con != null) {
                System.out.println("connection success!");
            }
			
///////////////////////////////////////////////////////////////
			//processing the query
//			stmt =  con.createStatement();
//			sql = "SELECT * FROM aassssssssssMyhome";
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			//printing resultSet
//			while(rs.next()){
//				int f = rs.getInt("forniture");
//				int m = rs.getInt("members");
//				int l = rs.getInt("location");
//				String i = rs.getString("income");
//				
//				System.out.print("Forniture" + f);
//                System.out.print(", Members: " + m);
//                System.out.print(", location: " + l);
//                System.out.print(", Income: " + i);
//                System.out.print("\n");
//				
//			}
//			
///////////////////////////////////////////////////////////////This is only for testing
			
		  stmt =  con.createStatement();
		  sql =  "DROP TABLE IF EXISTS tableFortesting \n" +
		  		  "CREATE TABLE tableFortesting ( \n" +
				  "`s1` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s2` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s3` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s4` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s5` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s6` VARCHAR(255) DEFAULT NULL, \n" +
//				  "`s7` VARCHAR(255) DEFAULT NULL \n" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		  System.out.println(sql);
		  stmt.execute(sql);  
		  //new table created
		  //System.out.println("------------------------------------------");
		
	    	//counting # of lines////////////////////
	      BufferedReader reader;
	      reader = new BufferedReader(new FileReader("filename")); ////stub filename
	      String line = reader.readLine();
	      int count = 0;
	      while(line != null) {
	    	  count ++;
	    	  line = reader.readLine();
	    	  
	      }
	      
	      BufferedReader reader2 = new BufferedReader(new FileReader("fileName")); //stub filename
	      line = reader2.readLine();
	      ArrayList<file> lines = new ArrayList<file> (count);
	      
	      for(int i = 0; i < count; i++) {
	    	  String[] array = new String[7];
	    	  array = line.split(",");
	    	  //assigning values for the object
	    	  file temp = new file();
	    	  
	    	  temp.string1 = array [0]; ///process a query here      out.print(temp.string1 + ",");
	    	  temp.string2 = array [1];
	    	  temp.string3 = array [2]; 
	    	  temp.string4 = array [3]; 
	    	  temp.string5 = array [4];
	    	  temp.string6 = array [5];
	    	  temp.string7 = array [6];
	    	  lines.add(temp); 
	    	  //now "lines" is storing a line of data. Starting processing a query
	    	  
	    	  sql =  "INSERT INTO tableFortesting VALUES(" + lines.get(i).string1 +
	    			  ',' + lines.get(i).string2 + ',' + lines.get(i).string3 + ',' + lines.get(i).string4
	    			  + ',' + lines.get(i).string5 + ',' + lines.get(i).string6 + ',' + lines.get(i).string7 + ')';
	    	  System.out.println(sql);
	    	  stmt.execute(sql);
			  //adding data into the table
			  
	    	  line = reader2.readLine();
	      }
			
			
			if (con != null) {
				con.close();
                System.out.println("connection closed");
            }
			
			
		} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
}
