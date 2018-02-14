package name.qd.fileCache.common;

import java.io.IOException;
import java.util.Date;

public class StreamTest {
	private byte[] data;
	
	public static void main(String[] s) {
		new StreamTest();
	}
	
	private StreamTest() {
		writeByteArray();
		
		readByteArray();
	}
	
	private void writeByteArray() {
		TransOutputStream tOut = new TransOutputStream();
		try {
			tOut.writeBoolean(true);
			tOut.writeByte((byte)'0');
			tOut.writeBytes("GG".getBytes());
			tOut.writeChar('a');
			tOut.writeDouble(1.234d);
			tOut.writeInt(555);
			tOut.writeLong(987654321);
			tOut.writeString("GGQQ123");
			data = tOut.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readByteArray() {
		TransInputStream tIn = new TransInputStream(data);
		try {
			System.out.println(tIn.getBoolean());
			System.out.println(tIn.getByte());
			System.out.println(new String(tIn.getBytes()));
			System.out.println(tIn.getChar());
			System.out.println(tIn.getDouble());
			System.out.println(tIn.getInt());
			System.out.println(tIn.getLong());
			System.out.println(tIn.getString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
