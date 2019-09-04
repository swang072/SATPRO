package dbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OneLine {
	String string1;
	String string2;
	String string3;
	String string4;
	String string5;
	String string6;
	String string7;
	public OneLine (String line) {
		
		String[] array = line.split(",");
		this.string1 = array [0]; ///process a query here
		this.string2 = array [1];
		this.string3 = array [2]; 
		this.string4 = array [3]; 
		this.string5 = array [4];
		this.string6 = array [5];
		this.string7 = array [6];
		

	}
	public PreparedStatement setValue(PreparedStatement ps) throws SQLException {
		int j = 0;
		ps.setString(++j, this.string1);
		ps.setString(++j, this.string2);
		ps.setString(++j, this.string3);
		ps.setString(++j, this.string4);
		ps.setString(++j, this.string5);
		ps.setString(++j, this.string6);
		ps.setString(++j, this.string7);
		
		return ps;
	}
}
