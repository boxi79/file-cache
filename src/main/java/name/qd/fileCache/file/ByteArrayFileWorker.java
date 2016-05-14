package name.qd.fileCache.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class ByteArrayFileWorker {
	
	public void write(String sFilePath, byte[] bData, int iIndex, int iLength) throws IOException {
		bData = toFileFormat(bData, iLength);
		try (RandomAccessFile rFile = new RandomAccessFile(sFilePath, "rws")) {
			rFile.seek(iIndex * iLength);
			rFile.write(bData);
		}
	}
	
	public void writeAll(String sFilePath, List<byte[]> lst, int iLength) throws IOException {
		File file = new File(sFilePath);
		try (FileOutputStream fOut = new FileOutputStream(file)) {
			for(byte[] bData : lst) {
				bData = toFileFormat(bData, iLength);
				fOut.write(bData);
			}
		}
	}

	public List<byte[]> read(String sFilePath, int iLength) throws IOException {
		File file = new File(sFilePath);
		List<byte[]> lst = new ArrayList<byte[]>();
		try (FileInputStream fIn = new FileInputStream(file);) {
			int iDataLength = fIn.available();
			int iDataCount = iDataLength / iLength;
			for(int i = 0 ; i < iDataCount ; i++) {
				byte[] bData = new byte[iLength];
				fIn.read(bData);
				lst.add(bData);
			}
		}
		return lst;
	}

	private byte[] toFileFormat(byte[] bData, int iLength) {
		byte[] bCombine = new byte[iLength];
		System.arraycopy(bData, 0, bCombine, 0, bData.length);
		return bCombine;
	}
}
