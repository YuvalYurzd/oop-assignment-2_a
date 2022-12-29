import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class targil4 implements Callable<Integer> {

	private String filename;

	public targil4(String filename) {
		this.filename = filename;
	}

	@Override
	public Integer call() throws Exception {
		Integer count = 0;
		File f = new File("C:\\Users\\yuval\\OneDrive\\Desktop\\textfiles\\" + filename + ".txt");
		Scanner sc;
		try {
			sc = new Scanner(f);
			while (sc.hasNext()) {
				count++;
				sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return count;
	}

}
