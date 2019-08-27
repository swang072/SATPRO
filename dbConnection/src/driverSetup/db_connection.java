package driverSetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_connection {
	
	public static void main(String args[]){  
		try {
			//Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection(  
					//"jdbc:mysql://112.74.109.18:3306/rpmdb","rpmdb","a9NajZWz");  
					"jdbc:mysql://112.74.109.184:3306/rpm","rpmdb","a9NajZWz");  
			if (con!=null) {
                System.out.println("connection success!");
            }
		} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
