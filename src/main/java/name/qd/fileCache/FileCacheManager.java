package name.qd.fileCache;

import java.util.List;
import java.util.Map;

import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.file.FileStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCacheManager {
	
	private Logger log = LoggerFactory.getLogger(FileCacheManager.class);
	
	private CacheStorage cacheStorage;
	private FileStorage fileStorage;
	
	
	public FileCacheManager(String sFilePath) {
		fileStorage = new FileStorage(sFilePath);
		log.info("Init FileStorage, FilePath:[" + sFilePath + "]");
		
		log.info("Loading file to cache...");
		long lTime = System.currentTimeMillis();
		try {
			Map<String, List<byte[]>> map = fileStorage.loadDataFromFile();
			cacheStorage = new CacheStorage(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		lTime = System.currentTimeMillis() - lTime;
		log.info("File loaded to cache. Loaded time:[" + lTime + "] ms.");
	}
}
