package name.qd.fileCache.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import name.qd.fileCache.file.vo.FileAccessObj;

public class FileStorage {
	private String sFilePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String sFilePath) {
		this.sFilePath = sFilePath;
		fileWorker = new ByteArrayFileWorker();
	}
	
	public Map<String, FileAccessObj> loadDataFromFile() throws Exception {
		Map<String, FileAccessObj> map = new HashMap<String, FileAccessObj>();
		File file = new File(sFilePath);
		if(!file.exists()) {
			file.mkdir();
		}
		if(!file.isDirectory()) {
			throw new Exception(sFilePath + " is not a directory.");
		}
		String[] files = file.list();
		if(files == null) return map;
		for(String sFileName : files) {
			FileAccessObj fileObj = fileWorker.read(sFilePath + "/" + sFileName);
			map.put(sFileName, fileObj);
		}
		return map;
	}
	
	public void writeAll(String sFileName, FileAccessObj fileObj) throws IOException {
		fileWorker.writeAll(sFilePath + "/" + sFileName, fileObj);
	}
}
