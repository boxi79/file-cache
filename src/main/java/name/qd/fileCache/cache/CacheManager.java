package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheManager {
	private Map<String, IFileCacheObject> map = new HashMap<String, IFileCacheObject>();
	private FileStorage fileStorage;
	private String sCacheName;
	private String sClassName;
	
	CacheManager(FileStorage fileStorage, String sCacheName, String sClassName) throws Exception {
		this.fileStorage = fileStorage;
		this.sCacheName = sCacheName;
		this.sClassName = sClassName;
	}
	
	public IFileCacheObject get(String sKey) {
		return map.get(sKey);
	}
	
	public void put(String sKey, IFileCacheObject value) {
		map.put(sKey, value);
	}
	
	public void delete(String sKey) {
		if(map.containsKey(sKey)) {
			map.remove(sKey);
		}
	}
	
	public void writeCacheToFile() throws IOException {
		ArrayList<byte[]> lst = new ArrayList<byte[]>();
		for(IFileCacheObject cacheObj : map.values()) {
			lst.add(cacheObj.parseToFileFormat());
		}
		fileStorage.writeAll(sCacheName, new FileAccessObj(sClassName, lst));
	}
}
