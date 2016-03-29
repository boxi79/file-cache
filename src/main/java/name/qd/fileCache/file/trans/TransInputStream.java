package name.qd.fileCache.file.trans;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TransInputStream {
	private DataInputStream dIn;
	private ByteArrayInputStream bIn;
	
	public TransInputStream(byte[] bData) {
		bIn = new ByteArrayInputStream(bData);
		dIn = new DataInputStream(bIn);
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
		int iLength = dIn.readInt();
		byte[] bData = new byte[iLength];
		int readed = dIn.read(bData);
		if(iLength != readed) {
			throw new IOException("read bytes failed.");
		}
		return bData;
	}
	
	public String getString() throws IOException {
		int iLength = dIn.readInt();
		byte[] bData = new byte[iLength];
		int readed = dIn.read(bData);
		if(iLength != readed) {
			throw new IOException("read bytes failed.");
		}
		return new String(bData);
	}
}
