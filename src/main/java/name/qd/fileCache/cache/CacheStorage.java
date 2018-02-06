package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<>();
	private FileStorage fileStorage;

	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
	}

	public CacheManager getCacheInstance(String cacheName) throws Exception {
		if (!map.containsKey(cacheName)) {
			readCacheFromFile(cacheName);
		}
		return map.get(cacheName);
	}

	private void readCacheFromFile(String cacheName) throws Exception {
		FileAccessObj fileAccessObj = fileStorage.loadDataFromFile(cacheName);
		if(fileAccessObj == null) return;
		CacheManager cacheManager = createCacheInstance(cacheName, fileAccessObj.getClassName());
		for (byte[] data : fileAccessObj.getList()) {
			FileCacheObject cacheObj = FileCacheObject.getFileCacheObjInstance(fileAccessObj.getClassName());
			cacheObj.toValueObject(data);
			cacheManager.put(cacheObj.getKeyString(), cacheObj);
		}
	}

	public CacheManager createCacheInstance(String cacheName, String className) throws Exception {
		if (!map.containsKey(cacheName)) {
			map.put(cacheName, new CacheManager(fileStorage, cacheName, className));
		}
		return map.get(cacheName);
	}

	public Set<String> getCacheNameSet() {
		return map.keySet();
	}
}
