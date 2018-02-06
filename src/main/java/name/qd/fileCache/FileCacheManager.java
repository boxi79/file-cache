package name.qd.fileCache;

import name.qd.fileCache.cache.CacheManager;
import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.file.FileStorage;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCacheManager {
	
	private Logger log = LoggerFactory.getLogger(FileCacheManager.class);
	
	private CacheStorage cacheStorage;
	
	public FileCacheManager(String filePath) {
		FileStorage fileStorage = new FileStorage(filePath);
		log.info("Init FileStorage, FilePath:[{}]", filePath);
		
//		log.info("Loading file to cache...");
//		long timestamp = System.currentTimeMillis();
		try {
			cacheStorage = new CacheStorage(fileStorage);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
//		timestamp = System.currentTimeMillis() - timestamp;
//		log.info("File loaded to cache. Loaded time:[{}] ms.", timestamp);
	}
	
	public CacheManager getCacheInstance(String cacheName) {
		return cacheStorage.getCacheInstance(cacheName);
	}
	
	public CacheManager createCacheInstance(String cacheName, String className) {
		CacheManager cacheManager = null;
		try {
			cacheManager = cacheStorage.createCacheInstance(cacheName, className);
		} catch (Exception e) {
			log.error("Cache Object class not exist.");
		}
		return cacheManager;
	}
	
	public Set<String> getLoadedCacheNameSet() {
		return cacheStorage.getCacheNameSet();
	}
}
