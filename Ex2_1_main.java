import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex2_1_main {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		String[] fileNames = createTextFiles(250, 123, 256);
		long start_Time = 0, end_Time = 0, total_Time = 0;

		start_Time = System.nanoTime();
		getNumOfLines(fileNames);
		end_Time = System.nanoTime();
		total_Time = end_Time - start_Time;
		System.out.println("time taken for first method: " + total_Time / 1000000000.0);

		start_Time = System.nanoTime();
		getNumOfLinesThreads(fileNames);
		end_Time = System.nanoTime();
		total_Time = end_Time - start_Time;
		System.out.println("time taken for second method: " + total_Time / 1000000000.0);

		start_Time = System.nanoTime();
		getNumOfLinesThreadPool(fileNames);
		end_Time = System.nanoTime();
		total_Time = end_Time - start_Time;
		System.out.println("time taken for third method: " + total_Time / 1000000000.0);
	}

	public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
		String s = "file_";
		String[] res = new String[n];
		Random rand = new Random(seed);
		for (Integer i = 1; i <= n; i++) {
			s = "file_";
			int x = rand.nextInt(bound);
			s += i.toString();
			File f = new File("C:\\Users\\yuval\\OneDrive\\Desktop\\textfiles\\" + s + ".txt");
			f.createNewFile();
			FileWriter fw = new FileWriter("C:\\Users\\yuval\\OneDrive\\Desktop\\textfiles\\" + s + ".txt");
			for (int j = 0; j < x - 1; j++) {
				fw.write("Hello World!\n");
			}
			fw.write("Hello World!");
			fw.close();
			res[i - 1] = s;
		}

		return res;
	}

	public static int getNumOfLines(String[] fileNames) throws FileNotFoundException {
		int countlines = 0;
		for (int i = 0; i < fileNames.length; i++) {
			File f = new File("C:\\Users\\yuval\\OneDrive\\Desktop\\textfiles\\" + fileNames[i] + ".txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				countlines++;
				sc.nextLine();
			}
		}
		return countlines;
	}

	public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
		int sumlines = 0;
		for (int i = 0; i < fileNames.length; i++) {
			targil3 t = new targil3(fileNames[i]);
			t.start();
			t.join();
			sumlines += t.getcount();
		}
		return sumlines;
	}

	public static int getNumOfLinesThreadPool(String[] fileNames) throws InterruptedException, ExecutionException {
		int sumlines = 0;
		ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
		for (int i = 0; i < fileNames.length; i++) {
			targil4 t = new targil4(fileNames[i]);
			Future<Integer> future = executor.submit(t);
			sumlines += future.get();
		}
		executor.shutdown();
		return sumlines;
	}
}