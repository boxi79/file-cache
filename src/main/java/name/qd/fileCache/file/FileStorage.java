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
	
	public void write(String sFileName, byte[] bData, int iIndex, int iLength) throws IOException {
		fileWorker.write(sFilePath + "/" + sFileName, bData, iIndex, iLength);
	}
	
	public List<byte[]> read(String sFileName, int iLength) throws IOException {
		return fileWorker.read(sFilePath + "/" + sFileName, iLength);
	}
	
	public Map<String, List<byte[]>> loadDataFromFile() throws Exception {
		Map<String, List<byte[]>> map = new HashMap<String, List<byte[]>>();
		File file = new File(sFilePath);
		if(!file.isDirectory()) {
			throw new Exception(sFilePath + " is not a directory.");
		}
		for(String sFileName : file.list()) {
			IFileCacheObject fileCacheObj = IFileCacheObject.getFileCacheObjInstance(sFileName);
			if(fileCacheObj == null) return null;

			List<byte[]> lst = fileWorker.read(sFilePath + "/" + sFileName, fileCacheObj.getDataLength());
			map.put(sFileName, lst);
		}
		return map;
	}
}
