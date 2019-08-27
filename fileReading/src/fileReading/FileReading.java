package fileReading;
import java.util.*; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*;; 


public class FileReading 
{
	
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
	
//    public static String readFileAsString(String fileName)throws Exception 
//    { 
//      String data = ""; 
//      data = new String(Files.readAllBytes(Paths.get(fileName))); 
//      return data; 
//    } 
	
    public static void readFileAsString(String fileName)throws Exception 
    { 
    	//counting # of lines////////////////////
      BufferedReader reader;
      reader = new BufferedReader(new FileReader(fileName)); 
      String line = reader.readLine();
      int count = 0;
      while(line != null) {
    	  count ++;
    	  line = reader.readLine();
      }
      
      ////////export the text file
      PrintWriter out = new PrintWriter("newFile.txt");
      
      BufferedReader reader2 = new BufferedReader(new FileReader(fileName)); 
      line = reader2.readLine();
      ArrayList<file> lines = new ArrayList<file> (count);
      
      for(int i = 0; i < count; i++) {
    	  String[] array = new String[7];
    	  array = line.split(",");
    	  //assigning values for the object
    	  file temp = new file();
    	  
    	  temp.string1 = array [0]; out.print(temp.string1 + ",");
    	  temp.string2 = array [1];
    	  temp.string2 = "----------ADDED TEXT----------" + temp.string2;
    	  							out.print(temp.string2 + ",");
    	  temp.string3 = array [2]; out.print(temp.string3 + ",");
    	  temp.string4 = array [3]; out.print(temp.string4 + ",");
    	  temp.string5 = array [4]; out.print(temp.string5 + ",");
    	  temp.string6 = array [5]; out.print(temp.string6 + ",");
    	  temp.string7 = array [6]; out.println(temp.string7);
    	  lines.add(temp); 
    	  
    	  //System.out.println(line);
    	  System.out.print(lines.get(i).string1); System.out.print(",");
    	  System.out.print(lines.get(i).string2); System.out.print(",");
    	  System.out.print(lines.get(i).string3); System.out.print(",");
    	  System.out.print(lines.get(i).string4); System.out.print(",");
    	  System.out.print(lines.get(i).string5); System.out.print(",");
    	  System.out.print(lines.get(i).string6); System.out.print(",");
    	  System.out.println(lines.get(i).string7);
    	  line = reader2.readLine();
      }
      
      
      reader.close();
    } 
  
    
    public static void main(String[] args) throws Exception 
    { 
      //String data = readFileAsString("/Users/siyuanwang/Desktop/xingzhan/test.TXT");
      readFileAsString("/Users/siyuanwang/Desktop/xingzhan/test.TXT");
      
      

      //System.out.println(data); 
    } 
  
} 