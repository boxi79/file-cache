package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import name.qd.fileCache.file.FileStorage;

public class CacheManager {
	private Map<String, IFileCacheObject> map = new HashMap<String, IFileCacheObject>();
	private Map<String, Integer> mapIndex = new HashMap<String, Integer>();
	private AtomicInteger atomicSqn = new AtomicInteger();
	private FileStorage fileStorage;
	
	CacheManager(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}
	
	public IFileCacheObject get(String sKey) {
		return map.get(sKey);
	}
	
	public void put(String sKey, IFileCacheObject value) throws IOException {
		int iIndex = -1;
		if(map.containsKey(sKey)) {
			iIndex = updateCache(sKey, value);
		} else {
			iIndex = insertCache(sKey, value);
		}
		fileStorage.write(value.getClass().getName(), value.parseToFileFormat(), iIndex, value.getDataLength());
	}
	
	private int updateCache(String sKey, IFileCacheObject value) {
		map.put(sKey, value);
		return mapIndex.get(sKey);
	}
	
	private int insertCache(String sKey, IFileCacheObject value) {
		map.put(sKey, value);
		int iSqn = atomicSqn.getAndIncrement();
		mapIndex.put(sKey, iSqn);
		return iSqn;
	}
}
