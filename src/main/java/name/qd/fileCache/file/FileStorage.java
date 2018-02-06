package name.qd.fileCache.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import name.qd.fileCache.file.vo.FileAccessObj;

public class FileStorage {
	private String filePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String filePath) {
		this.filePath = filePath;
		fileWorker = new ByteArrayFileWorker();
	}
	
	public Map<String, FileAccessObj> loadDataFromFile() throws Exception {
		Map<String, FileAccessObj> map = new HashMap<String, FileAccessObj>();
		File file = new File(filePath);
		if(!file.exists()) {
			file.mkdir();
		}
		if(!file.isDirectory()) {
			throw new Exception(filePath + " is not a directory.");
		}
		String[] files = file.list();
		if(files == null) return map;
		for(String fileName : files) {
			FileAccessObj fileObj = fileWorker.read(filePath + "/" + fileName);
			map.put(fileName, fileObj);
		}
		return map;
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
}
