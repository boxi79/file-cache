package name.qd.fileCache.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import name.qd.fileCache.file.vo.FileAccessObj;

public class FileStorage {
	private String filePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String filePath) {
		this.filePath = filePath;
		fileWorker = new ByteArrayFileWorker();
	}
	
	public FileAccessObj loadDataFromFile(String cacheName) throws Exception {
		File file = new File(filePath);
		if(!file.exists()) {
			file.mkdir();
		}
		if(!file.isDirectory()) {
			throw new Exception(filePath + " is not a directory.");
		}
		FileAccessObj fileObj = fileWorker.read(filePath + "/" + cacheName);
		return fileObj;
	}
	
	public void writeAll(String fileName, FileAccessObj fileObj) throws IOException {
		fileWorker.writeAll(filePath + "/" + fileName, fileObj);
	}
	
	public void removeFile(String fileName) throws IOException {
		File file = new File(filePath + "/" + fileName);
		Files.deleteIfExists(file.toPath());
	}
}
