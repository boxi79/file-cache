package name.qd.fileCache;

import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.file.FileStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCacheManager {
	
	private Logger logger = LoggerFactory.getLogger(FileCacheManager.class);
	
	private FileCacheConfigLoader configLoader;
	private CacheStorage cacheStorage;
	private FileStorage fileStorage;
	
	public FileCacheManager(String sConfigFile) {
		try {
			configLoader = new FileCacheConfigLoader(sConfigFile);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			return;
		}
		
		cacheStorage = new CacheStorage();
		
		fileStorage = new FileStorage(configLoader.getFilePath(), configLoader.getAccessDataType());
		
		logger.info("Loading file to cache...");
		long lTime = System.currentTimeMillis();
		loadFile();
		lTime = System.currentTimeMillis() - lTime;
		logger.info("File loaded to cache. Loaded time:[" + lTime + "] ms.");
	}
	
	private void loadFile() {
	}
}
