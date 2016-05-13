package name.qd.fileCache;

import java.io.File;
import java.io.IOException;
import java.util.List;

import name.qd.fileCache.cache.CacheManager;
import name.qd.fileCache.cache.CacheStorage;
import name.qd.fileCache.cache.IFileCacheObject;
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
		File file = new File(configLoader.getFilePath());
		if(!file.isDirectory()) {
			logger.error(configLoader.getFilePath() + " is not a directory.");
			return;
		}
		for(String sFileName : file.list()) {
			IFileCacheObject fileCacheObj = getFileCacheObjInstance(sFileName);
			if(fileCacheObj == null) return;
			
			CacheManager cacheManager = cacheStorage.getCacheInstance(sFileName);

			try {
				@SuppressWarnings("rawtypes")
				List lst = fileStorage.read(sFileName, fileCacheObj.getDataLength());
				setDataToCache(cacheManager, lst, sFileName);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	private IFileCacheObject getFileCacheObjInstance(String sClassName) {
		IFileCacheObject fileCacheObj = null;
		try {
			fileCacheObj = (IFileCacheObject) Class.forName(sClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return fileCacheObj;
	}
	
	@SuppressWarnings("rawtypes")
	private void setDataToCache(CacheManager cacheManager, List lst, String sClassName) {
	}
}
