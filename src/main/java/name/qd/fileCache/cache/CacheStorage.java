package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import name.qd.fileCache.file.FileStorage;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<String, CacheManager>();
	private FileStorage fileStorage;
	
	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
		Map<String, List<byte[]>> mapData = fileStorage.loadDataFromFile();
		
		for(Entry<String, List<byte[]>> entry : mapData.entrySet()) {
			CacheManager cacheManager = getCacheInstance(entry.getKey());
			for(byte[] bData : entry.getValue()) {
				IFileCacheObject cacheObj = IFileCacheObject.getFileCacheObjInstance(entry.getKey());
				cacheObj.toValueObject(bData);
				cacheManager.put(cacheObj.getKeyString(), cacheObj);
			}
		}
	}
	
	public CacheManager getCacheInstance(String sCacheName) throws Exception {
		if(!map.containsKey(sCacheName)) {
			map.put(sCacheName, new CacheManager(fileStorage, sCacheName));
		}
		return map.get(sCacheName);
	}
}
