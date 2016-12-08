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
	
	public FileCacheManager(String sFilePath) {
		FileStorage fileStorage = new FileStorage(sFilePath);
		log.info("Init FileStorage, FilePath:[{}]", sFilePath);
		
		log.info("Loading file to cache...");
		long lTime = System.currentTimeMillis();
		try {
			cacheStorage = new CacheStorage(fileStorage);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		lTime = System.currentTimeMillis() - lTime;
		log.info("File loaded to cache. Loaded time:[{}] ms.", lTime);
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		return cacheStorage.getCacheInstance(sCacheName);
	}
	
	public CacheManager createCacheInstance(String sCacheName, String sClassName) {
		CacheManager cacheManager = null;
		try {
			cacheManager = cacheStorage.createCacheInstance(sCacheName, sClassName);
		} catch (Exception e) {
			log.error("Cache Object class not exist.");
		}
		return cacheManager;
	}
	
	public Set<String> getCacheNameSet() {
		return cacheStorage.getCacheNameSet();
	}
}
