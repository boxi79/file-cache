package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<String, CacheManager>();
	
	public CacheStorage(Map<String, List<byte[]>> map) throws Exception {
		for(String sCacheName : map.keySet()) {
			CacheManager cacheManager = getCacheInstance(sCacheName);
			for(byte[] bData : map.get(sCacheName)) {
				IFileCacheObject cacheObj = IFileCacheObject.getFileCacheObjInstance(sCacheName);
				cacheObj.toValueObject(bData);
				cacheManager.put(cacheObj.getKeyString(), cacheObj);
			}
		}
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		if(!map.containsKey(sCacheName)) {
			map.put(sCacheName, new CacheManager());
		}
		return map.get(sCacheName);
	}
}
