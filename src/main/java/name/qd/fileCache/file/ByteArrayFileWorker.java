package name.qd.fileCache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteArrayFileWorker implements IFileWorker {

	public int write(Object object, File file, int iIndex) throws IOException {
		byte[] bData = (byte[]) object;
		FileOutputStream fOut = new FileOutputStream(file);
		bData = toFileFormat(bData);
		try {
			fOut.write(bData, iIndex, bData.length);
		} finally {
			fOut.close();
		}
		return iIndex + bData.length;
	}

	public Object read(File file, int iIndex) {
		return null;
	}

	private byte[] toFileFormat(byte[] bData) {
		byte[] bCombine = new byte[bData.length + 4];
		byte[] bLength = ByteBuffer.allocate(4).putInt(bData.length).array();
		System.arraycopy(bLength, 0, bCombine, 0, 4);
		System.arraycopy(bData, 0, bCombine, 4, bData.length);
		return bCombine;
	}
	
	private byte[] toCacheFormet(byte[] bData) {
		return null;
	}
}
