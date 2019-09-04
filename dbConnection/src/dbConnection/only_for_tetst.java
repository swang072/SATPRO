//package dbConnection;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
////import fileReading.FileReading.file;
//
//
//
//public class only_for_tetst {
//	
////	static class OneLine {
////		String string1;
////		String string2;
////		String string3;
////		String string4;
////		String string5;
////		String string6;
////		String string7;
////		public OneLine () {
////			String string1 = "";
////			String string2 = "";
////			String string3 = "";
////			String string4 = "";
////			String string5 = "";
////			String string6 = "";
////			String string7 = "";
////		}
////	}
////	
//	private static final String file_path = "/Users/siyuanwang/Desktop/xingzhan/test.TXT";
//	public static void main(String args[]){  
//		Connection con = null;
//		Statement stmt = null;
//		PreparedStatement ps = null;
//		//java.sql.PreparedStatement
//		//excuteBatch
//		String sql;
//		BufferedReader reader = null;
//		ResultSet rs;
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver"); 
//			con = DriverManager.getConnection(  
//					//"jdbc:mysql://112.74.109.18:3306/rpmdb","rpmdb","a9NajZWz");  
//					"jdbc:mysql://112.74.109.184:3306/rpm","rpmdb","a9NajZWz");  
//			if (con == null)
//				throw new java.lang.NullPointerException("connection is null");
//			
//			stmt =  con.createStatement();
//			
//
//	    	//Reading from file and writing to the database
//			reader = new BufferedReader(new FileReader(file_path)); ////stub filename
//			String line = reader.readLine();
//			int count = 0;
//			while (line != null) {
//				count ++;
//				line = reader.readLine();
//			}
//			reader.close();
//	      
//			reader = new BufferedReader(new FileReader(file_path)); //stub filename
//			line = reader.readLine();
//			//ArrayList<file> lines = new ArrayList<file> (count); //file = seven breaked up strings
//	      
//			
//			long startTime = System.currentTimeMillis();
//			for (int i = 0; i < count; i++) {
//				String[] array = new String[7];
//				array = line.split(",");
//				//assigning values for the object
//				OneLine row = new OneLine();
//	    	  
//				row.string1 = array [0]; ///process a query here      out.print(temp.string1 + ",");
//				row.string2 = array [1];
//				row.string3 = array [2]; 
//				row.string4 = array [3]; 
//				row.string5 = array [4];
//				row.string6 = array [5];
//				row.string7 = array [6];
//				//lines.add(temp);
//	    	  
//				sql =  "INSERT INTO table_for_testing(s1, s2, s3, s4, s5, s6, s7, modified_date) VALUES( '"
//		    			  + row.string1 + "', '" +  row.string2 + "', '" + row.string3
//		    			  + "', '" + row.string4 + "', '" + row.string5 + "', '" + row.string6
//		    			  + "', '" + row.string7 + "', " + "NOW());";
//				System.out.print(sql);System.out.print(count);
//				System.out.print("       ");System.out.println(i);
//				stmt.addBatch(sql);
//				//stmt.execute(sql);
////				ps = con.prepareStatement(sql);
////				int j = 0;
////				ps.setString(++j, row.string1);
////				ps.setString(++j, row.string2);
////				ps.setString(++j, row.string3);
////				ps.setString(++j, row.string4);
////				ps.setString(++j, row.string5);
////				ps.setString(++j, row.string6);
////				ps.setString(++j, row.string7);
////				ps.executeUpdate();
//	    	  //System.out.println("Writing success!");
//			  //adding data into the table
//
//				line = reader.readLine();
//			}
//			int rs1[] = stmt.executeBatch();
//			
//			long endTime = System.nanoTime();
//			long timeused = (endTime - startTime);
//			double total = (double)timeused/ 1_000_000_000;
//			System.out.println("------------------ Time used " + total + "s ------------------");
//			reader.close();
//			System.out.println("------------------ File Writing Done! ------------------");
//			
//			
//			//////////////////////////////////////////search in the database///////////////////////////////
//			String column = "s6";
//			String nameOfTable = "table_for_testing";
//			String condition = "id = 10006";
//			sql = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
//			//stmt.addBatch(sql);
//			rs = stmt.executeQuery(sql);
//			while(rs.next()){
//                String s6 = rs.getString("s6");
//                
//                System.out.println("s6: " + s6);
//                System.out.println("------------------ Data Retriving Done! ------------------");
//            }
//			//////////////////////////////////////////deleting from the database///////////////////////////////
//			condition = "id = 10000";
//			sql = "DELETE FROM " + nameOfTable + " WHERE " + condition;
//			stmt.execute(sql);
//			System.out.println("------------------ Deleting Done ------------------");
//			//////////////////////////////////////////update in the database///////////////////////////////	
//			condition = "id = 10005";
//			sql = "UPDATE " + nameOfTable + " SET " + column + " = '    new content   '" + " WHERE " + condition;
//			stmt.execute(sql);
////////////////////////////////////
//			sql = "SELECT " + column + " FROM " + nameOfTable + " WHERE " + condition;
//			//stmt.addBatch(sql);
//			rs = stmt.executeQuery(sql);
//			while(rs.next()){
//                String s6 = rs.getString("s6");
//                
//                System.out.println("s6: " + s6);
//                System.out.println("------------------ Data Updating Done! ------------------");
//            }
//			
//			
//			
//		} catch (Exception e) {
//            // TODO Auto-generated catch block
//			e.printStackTrace();
//        } finally {
//        	try {
//				if (con != null) {
//					con.close();
//				}
//				reader.close();
//			} catch (Exception ne) {
//				
//			}
//        }
//
//	}
//}
