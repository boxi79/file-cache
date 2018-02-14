package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheStorage {
	private static Logger log = LoggerFactory.getLogger(CacheStorage.class);
	private Map<String, CacheManager> map = new HashMap<>();
	private FileStorage fileStorage;

	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
	}

	public CacheManager getCacheInstance(String cacheName) {
		if (!map.containsKey(cacheName)) {
			try {
				readCacheFromFile(cacheName);
			} catch (Exception e) {
				log.error("", e);
			}
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
	
	public void removeCacheAndFile(String cacheName) throws IOException {
		if (map.containsKey(cacheName)) {
			map.get(cacheName).removeFile();
			map.remove(cacheName);
		}
	}
	
	public void removeCache(String cacheName) {
		if (map.containsKey(cacheName)) {
			map.remove(cacheName);
		}
	}

	public Set<String> getCacheNameSet() {
		return map.keySet();
	}
}
