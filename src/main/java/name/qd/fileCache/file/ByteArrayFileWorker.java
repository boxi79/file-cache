package name.qd.fileCache.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import name.qd.fileCache.file.vo.FileAccessObj;

class ByteArrayFileWorker {
	public void writeAll(String sFilePath, FileAccessObj fileObj) throws IOException {
		File file = new File(sFilePath);
		try (FileOutputStream fOut = new FileOutputStream(file)) {
			writeClassName(fOut, fileObj.getClassName());
			for(byte[] bData : fileObj.getList()) {
				int iLength = bData.length;
				fOut.write(iLength);
				fOut.write(bData);
			}
		}
	}
	
	private void writeClassName(FileOutputStream fOut, String sClassName) throws IOException {
		int iClassNameLength = sClassName.getBytes().length;
		fOut.write(iClassNameLength);
		fOut.write(sClassName.getBytes());
	}

	public FileAccessObj read(String sFilePath) throws IOException {
		File file = new File(sFilePath);
		List<byte[]> lst = new ArrayList<byte[]>();
		FileAccessObj fileObj = new FileAccessObj();
		try (FileInputStream fIn = new FileInputStream(file);) {
			String sClassName = readClassName(fIn);
			while(fIn.available() > 0) {
				int iLength = fIn.read();
				byte[] bData = new byte[iLength];
				fIn.read(bData);
				lst.add(bData);
			}
			fileObj.setClassName(sClassName);
			fileObj.setList(lst);
		}
		return fileObj;
	}
	
	private String readClassName(FileInputStream fIn) throws IOException {
		int iClassNameLength = fIn.read();
		byte[] bClassName = new byte[iClassNameLength];
		fIn.read(bClassName);
		return new String(bClassName);
	}
}
