package name.qd.fileCache.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class ByteArrayFileWorkerTest {
	public static void main(String[] s) {
		new ByteArrayFileWorkerTest();
	}
	
	private ByteArrayFileWorkerTest() {
		ByteArrayFileWorker worker = new ByteArrayFileWorker();
		File file = new File("Test");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			int iNewIndex = worker.write("ABCㄎㄎ".getBytes("UTF-8"), file, 0);
			System.out.println(iNewIndex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw;
		try {
			fw = new FileWriter("Writer");
			fw.write("ABC����");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
