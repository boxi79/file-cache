package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.qd.fileCache.file.FileStorage;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<String, CacheManager>();
	private FileStorage fileStorage;
	
	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
		Map<String, List<byte[]>> mapData = fileStorage.loadDataFromFile();
		for(String sCacheName : mapData.keySet()) {
			CacheManager cacheManager = getCacheInstance(sCacheName);
			for(byte[] bData : mapData.get(sCacheName)) {
				IFileCacheObject cacheObj = IFileCacheObject.getFileCacheObjInstance(sCacheName);
				cacheObj.toValueObject(bData);
				cacheManager.put(cacheObj.getKeyString(), cacheObj);
			}
		}
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		if(!map.containsKey(sCacheName)) {
			map.put(sCacheName, new CacheManager(fileStorage));
		}
		return map.get(sCacheName);
	}
}
