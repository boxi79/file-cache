package name.qd.fileCache.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.qd.fileCache.cache.IFileCacheObject;

public class FileStorage {
	private String sFilePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String sFilePath) {
		this.sFilePath = sFilePath;
		fileWorker = new ByteArrayFileWorker();
	}
	
	public Map<String, List<byte[]>> loadDataFromFile() throws Exception {
		Map<String, List<byte[]>> map = new HashMap<String, List<byte[]>>();
		File file = new File(sFilePath);
		if(!file.isDirectory()) {
			throw new Exception(sFilePath + " is not a directory.");
		}
		String[] files = file.list();
		if(files == null) return map;
		for(String sFileName : files) {
			IFileCacheObject fileCacheObj = IFileCacheObject.getFileCacheObjInstance(sFileName);
			List<byte[]> lst = fileWorker.read(sFilePath + "/" + sFileName, fileCacheObj.getDataLength());
			map.put(sFileName, lst);
		}
		return map;
	}
	
	public void writeAll(String sFileName, List<byte[]> lst, int iLength) throws IOException {
		fileWorker.writeAll(sFilePath + "/" + sFileName, lst, iLength);
	}
}
