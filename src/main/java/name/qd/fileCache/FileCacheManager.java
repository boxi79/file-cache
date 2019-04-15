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
	
	public FileCacheManager(String filePath) throws Exception {
		FileStorage fileStorage = new FileStorage(filePath);
		log.info("Init FileStorage, FilePath:[{}]", filePath);
		cacheStorage = new CacheStorage(fileStorage);
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
	
	public void removeCache(String cacheName) {
		
	}
	
	public void removeCacheAndFile(String cacheName) {
		
	}
	
	public Set<String> getLoadedCacheNameSet() {
		return cacheStorage.getCacheNameSet();
	}
}
