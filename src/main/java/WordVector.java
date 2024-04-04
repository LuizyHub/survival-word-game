import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class WordVector {
	private Vector<String> wVector = new Vector<String>(); public Vector<String> getWVector(){return wVector;}
//	static {
//		try {
//			Scanner scanner = new Scanner(new FileReader("words.txt"));
//			while(scanner.hasNext()) {
//				String word = scanner.nextLine();
//				wVector.add(word);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void addWord(String s) {
		try
		{
		    String filename= "words.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write("\n" + s);//appends the string to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public WordVector() {
		
		try {
			Scanner scanner = new Scanner(new FileReader("words.txt"));
			while(scanner.hasNext()) {
				String word = scanner.nextLine();
				wVector.add(word);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getWord() {
		int index;
		do {
			index = (int)(Math.random()*wVector.size());
			
		}while(wVector.get(index).length()>7);
		
		//int index = (int)(Math.random()*wVector.size());
		
		return wVector.get(index).toLowerCase();
	}
}
