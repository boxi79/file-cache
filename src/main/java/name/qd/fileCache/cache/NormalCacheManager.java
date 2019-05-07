package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class NormalCacheManager {
	private Map<String, NormalObject> map = new HashMap<>();
	private final FileStorage fileStorage;
	private final String cacheName;
	private final String className;
	
	NormalCacheManager(FileStorage fileStorage, String cacheName, String className) {
		this.fileStorage = fileStorage;
		this.cacheName = cacheName;
		this.className = className;
	}
	
	public NormalObject get(String key) {
		return map.get(key);
	}
	
	public void put(NormalObject value) {
		map.put(value.getKeyString(), value);
	}
	
	public void remove(String key) {
		if(map.containsKey(key)) {
			map.remove(key);
		}
	}
	
	public Collection<NormalObject> values() {
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
