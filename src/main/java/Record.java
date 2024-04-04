import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Record {
	private Vector<String> rVector = new Vector<String>();
	public Vector<String> getRVector(){return rVector;}

	public Record() {
		read();
//		try {
//			Scanner scanner = new Scanner(new FileReader("record.txt"));
//			while(scanner.hasNext()) {
//				String word = scanner.nextLine();
//				rVector.add(word);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	public void read() {
		rVector.clear();
		try {
			Scanner scanner = new Scanner(new FileReader("record.txt"));
			while(scanner.hasNext()) {
				String word = scanner.nextLine();
				rVector.add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < rVector.size(); i ++) {

		}
	}
	
	
	public void addRecord(String name, Long score) {
		if(score > Long.parseLong(rVector.get(1))) {			
			rVector.set(5, rVector.get(3));
			rVector.set(4, rVector.get(2));
			rVector.set(3, rVector.get(1));
			rVector.set(2, rVector.get(0));
			rVector.set(0, name);
			rVector.set(1, Long.toString(score));
		}
		else if(score > Long.parseLong(rVector.get(3))) {
			rVector.set(5, rVector.get(3));
			rVector.set(4, rVector.get(2));
			rVector.set(2, name);
			rVector.set(3, Long.toString(score));
		}
		else if(score > Long.parseLong(rVector.get(5))) {
			rVector.set(4, name);
			rVector.set(5, Long.toString(score));
		}
		writeRecord();
	}
	
	public void writeRecord() {
		FileWriter fw = null;
		File f = new File("record.txt");
		try {
			fw = new FileWriter(f);
			Scanner scanner = new Scanner(System.in);
			
			//System.out.println("number in");
			
			for(int i = 0; i<6; i++) {
				fw.write(rVector.get(i)+"\r\n");
			}
//			while(true) {
//				System.out.print("name number >> ");
//				String line = scanner.nextLine(); // 한줄을 읽는다.
//				if(line.equals("stop"))
//						break; // 입력 종료
//				fw.write(line+"\r\n"); // 한 줄 띄어 저장하기 위해 "\r\n"을 붙인다.
//			}
			//System.out.println(f.getPath()+"saved");
			
			scanner.close();
			fw.close();
		} 
		catch (IOException e) { // 파일을 저장할 수 없는 경우 예외
			e.printStackTrace();
		}
	}
}
