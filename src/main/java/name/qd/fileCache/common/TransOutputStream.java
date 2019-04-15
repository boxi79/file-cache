package name.qd.fileCache.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TransOutputStream {
	private DataOutputStream dOut;
	private ByteArrayOutputStream bOut;
	
	public TransOutputStream() {
		bOut = new ByteArrayOutputStream();
		dOut = new DataOutputStream(bOut);
	}
	
	public void writeBoolean(boolean b) throws IOException {
		dOut.writeBoolean(b);
	}
	
	public void writeInt(int i) throws IOException {
		dOut.writeInt(i);
	}
	
	public void writeDouble(double d) throws IOException {
		dOut.writeDouble(d);
	}

	public void writeLong(long l) throws IOException {
		dOut.writeLong(l);
	}

	public void writeChar(char c) throws IOException {
		dOut.writeChar(c);
	}

	public void writeByte(byte b) throws IOException {
		dOut.writeByte(b);
	}
	
	public void writeBytes(byte[] b) throws IOException {
		dOut.writeInt(b.length);
		bOut.write(b);
	}
	
	public void writeString(String s) throws IOException {
		dOut.writeInt(s.getBytes().length);
		dOut.write(s.getBytes());
	}
	
	public byte[] toByteArray() {
		return bOut.toByteArray();
	}
}
