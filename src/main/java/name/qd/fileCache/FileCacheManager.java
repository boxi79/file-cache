package name.qd.fileCache;

import name.qd.fileCache.cache.NormalCacheManager;
import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.cache.CoordinateCacheManager;
import name.qd.fileCache.file.FileStorage;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCacheManager {
	private Logger log = LoggerFactory.getLogger(FileCacheManager.class);
	private CacheStorage cacheStorage;
	
	public FileCacheManager(String filePath) {
		FileStorage fileStorage = new FileStorage(filePath);
		log.info("Init FileStorage, FilePath:[{}]", filePath);
		cacheStorage = new CacheStorage(fileStorage);
	}
	
	public NormalCacheManager getNormalCacheInstance(String cacheName, String className) throws Exception {
		try {
			return cacheStorage.getNormalCacheInstance(cacheName, className);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			log.error("Get normal cache instance failed. CacheName:{}", cacheName, e);
			throw e;
		}
	}
	
	public CoordinateCacheManager getCoordinateCacheInstance(String cacheName, String className) throws Exception {
		try {
			return cacheStorage.getCoordinateCacheInstance(cacheName, className);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			log.error("Get coordinate cache instance failed. CacheName:{}", cacheName, e);
			throw e;
		}
	}
	
	public boolean isCacheExist(String cacheName) {
		return cacheStorage.isCacheExist(cacheName);
	}
	
	public void removeNormalCache(String cacheName) {
		cacheStorage.removeNormalCache(cacheName);
	}
	
	public void removeCoordinateCache(String cacheName) {
		cacheStorage.removeCoordinateCache(cacheName);
	}
	
	public void removeNormalCacheAndFile(String cacheName) {
		try {
			cacheStorage.removeNormalCacheAndFile(cacheName);
		} catch (IOException e) {
			log.error("remove normal cache and file failed.", e);
		}
	}
	
	public void removeCoordinateCacheAndFile(String cacheName) {
		try {
			cacheStorage.removeCoordinateCacheAndFile(cacheName);
		} catch (IOException e) {
			log.error("remove coordinate cache and file failed.", e);
		}
	}
	
	public Set<String> getLoadedNormalCacheNameSet() {
		return cacheStorage.getNormalCacheNameSet();
	}
	
	public Set<String> getLoadedCoordinateCacheNameSet() {
		return cacheStorage.getCoordinateCacheNameSet();
	}
}
