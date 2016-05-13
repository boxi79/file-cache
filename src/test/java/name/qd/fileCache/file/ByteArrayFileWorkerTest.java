package name.qd.fileCache.file;

import java.io.IOException;
import java.util.List;

public class ByteArrayFileWorkerTest {
	public static void main(String[] s) {
		new ByteArrayFileWorkerTest();
	}
	
	private ByteArrayFileWorkerTest() {
//		writeFile();
		readFile();
	}
	
	private void writeFile() {
		ByteArrayFileWorker worker = new ByteArrayFileWorker();
		try {
			worker.write("Test", "QQ".getBytes(), 2, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readFile() {
		ByteArrayFileWorker worker = new ByteArrayFileWorker();
		try {
			@SuppressWarnings("unchecked")
			List<byte[]> lst = worker.read("Test", 10);
			for(byte[] bData : lst) {
				System.out.println(new String(bData));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
