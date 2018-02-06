package name.qd.fileCache.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import name.qd.fileCache.file.vo.FileAccessObj;

class ByteArrayFileWorker {
	public void writeAll(String filePath, FileAccessObj fileObj) throws IOException {
		File file = new File(filePath);
		try (FileOutputStream fOut = new FileOutputStream(file)) {
			writeClassName(fOut, fileObj.getClassName());
			for(byte[] data : fileObj.getList()) {
				int length = data.length;
				fOut.write(length);
				fOut.write(data);
			}
		}
	}
	
	private void writeClassName(FileOutputStream fOut, String className) throws IOException {
		int classNameLength = className.getBytes().length;
		fOut.write(classNameLength);
		fOut.write(className.getBytes());
	}

	public FileAccessObj read(String filePath) throws IOException {
		File file = new File(filePath);
		List<byte[]> lst = new ArrayList<byte[]>();
		FileAccessObj fileObj = new FileAccessObj();
		try (FileInputStream fIn = new FileInputStream(file);) {
			String className = readClassName(fIn);
			while(fIn.available() > 0) {
				int length = fIn.read();
				byte[] data = new byte[length];
				fIn.read(data);
				lst.add(data);
			}
			fileObj.setClassName(className);
			fileObj.setList(lst);
		}
		return fileObj;
	}
	
	private String readClassName(FileInputStream fIn) throws IOException {
		int classNameLength = fIn.read();
		byte[] className = new byte[classNameLength];
		fIn.read(className);
		return new String(className);
	}
}
