package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<String, CacheManager>();
	private FileStorage fileStorage;
	
	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
		Map<String, FileAccessObj> mapData = fileStorage.loadDataFromFile();
		
		for(Entry<String, FileAccessObj> entry : mapData.entrySet()) {
			CacheManager cacheManager = createCacheInstance(entry.getKey(), entry.getValue().getClassName());
			for(byte[] bData : entry.getValue().getList()) {
				IFileCacheObject cacheObj = IFileCacheObject.getFileCacheObjInstance(entry.getValue().getClassName());
				cacheObj.toValueObject(bData);
				cacheManager.put(cacheObj.getKeyString(), cacheObj);
			}
		}
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		return map.get(sCacheName);
	}
	
	public CacheManager createCacheInstance(String sCacheName, String sClassName) throws Exception {
		if(!map.containsKey(sCacheName)) {
			map.put(sCacheName, new CacheManager(fileStorage, sCacheName, sClassName));
		}
		return map.get(sCacheName);
	}
}
