package name.qd.fileCache.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TransInputStream {
	private DataInputStream dIn;
	private ByteArrayInputStream bIn;
	
	public TransInputStream(byte[] data) {
		bIn = new ByteArrayInputStream(data);
		dIn = new DataInputStream(bIn);
	}
	
	public boolean getBoolean() throws IOException {
		return dIn.readBoolean();
	}
	
	public int getInt() throws IOException {
		return dIn.readInt();
	}
	
	public double getDouble() throws IOException {
		return dIn.readDouble();
	}
	
	public long getLong() throws IOException {
		return dIn.readLong();
	}
	
	public char getChar() throws IOException {
		return dIn.readChar();
	}
	
	public byte getByte() throws IOException {
		return dIn.readByte();
	}
	
	public byte[] getBytes() throws IOException {
		int length = dIn.readInt();
		byte[] data = new byte[length];
		int readed = dIn.read(data);
		if(length != readed) {
			throw new IOException("read bytes failed.");
		}
		return data;
	}
	
	public String getString() throws IOException {
		int length = dIn.readInt();
		byte[] data = new byte[length];
		int readed = dIn.read(data);
		if(length != readed) {
			throw new IOException("read bytes failed.");
		}
		return new String(data);
	}
}
