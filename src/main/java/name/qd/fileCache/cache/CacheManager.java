package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheManager {
	private Map<String, FileCacheObject> map = new HashMap<>();
	private FileStorage fileStorage;
	private String cacheName;
	private String className;
	
	CacheManager(FileStorage fileStorage, String cacheName, String className) throws Exception {
		this.fileStorage = fileStorage;
		this.cacheName = cacheName;
		this.className = className;
	}
	
	public FileCacheObject get(String key) {
		return map.get(key);
	}
	
	public void put(String key, FileCacheObject value) {
		map.put(key, value);
	}
	
	public void remove(String key) {
		if(map.containsKey(key)) {
			map.remove(key);
		}
	}
	
	public Collection<FileCacheObject> values() {
		return map.values();
	}
	
	public void writeCacheToFile() throws IOException {
		ArrayList<byte[]> lst = new ArrayList<byte[]>();
		for(FileCacheObject cacheObj : map.values()) {
			lst.add(cacheObj.parseToFileFormat());
		}
		fileStorage.writeAll(cacheName, new FileAccessObj(className, lst));
	}
	
	public String getClassName() {
		return className;
	}
	
	public void removeFile() throws IOException {
		fileStorage.removeFile(cacheName);
	}
}
