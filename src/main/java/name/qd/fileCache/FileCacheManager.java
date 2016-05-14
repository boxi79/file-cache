package name.qd.fileCache;

import name.qd.fileCache.cache.CacheManager;
import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.file.FileStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCacheManager {
	
	private Logger log = LoggerFactory.getLogger(FileCacheManager.class);
	
	private CacheStorage cacheStorage;
	
	public FileCacheManager(String sFilePath) {
		FileStorage fileStorage = new FileStorage(sFilePath);
		log.info("Init FileStorage, FilePath:[" + sFilePath + "]");
		
		log.info("Loading file to cache...");
		long lTime = System.currentTimeMillis();
		try {
			cacheStorage = new CacheStorage(fileStorage);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		lTime = System.currentTimeMillis() - lTime;
		log.info("File loaded to cache. Loaded time:[" + lTime + "] ms.");
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		CacheManager cacheManager = null;
		try {
			cacheManager = cacheStorage.getCacheInstance(sCacheName);
		} catch (Exception e) {
			log.error("Cache Object class not exist.");
		}
		return cacheManager;
	}
}
