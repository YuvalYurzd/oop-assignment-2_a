import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class targil3 extends Thread {
	private String filename;
	private int count;

	public targil3(String filename) {
		this.filename = filename;
		count = 0;
	}

	@Override
	public void run() {
		File f = new File("C:\\Users\\yuval\\OneDrive\\Desktop\\textfiles\\" + filename + ".txt");
		Scanner sc;
		try {
			sc = new Scanner(f);
			while (sc.hasNext()) {
				this.count++;
				sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int getcount() {
		return this.count;
	}
}
