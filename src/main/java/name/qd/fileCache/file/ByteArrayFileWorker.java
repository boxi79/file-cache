package name.qd.fileCache.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayFileWorker implements IFileWorker {

	public void write(Object object, File file, int iIndex) throws IOException {
		byte[] bData = (byte[]) object;
		FileOutputStream fOut = new FileOutputStream(file);
		try {
			fOut.write(bData, iIndex, bData.length);
		} finally {
			fOut.close();
		}
	}

	public void read(File file) {
		
	}

}
