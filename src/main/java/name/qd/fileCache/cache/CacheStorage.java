package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<String, CacheManager>();
	
	public CacheStorage() {
	}
	
	public CacheManager getCacheInstance(String sCacheName) {
		if(!map.containsKey(sCacheName)) {
			map.put(sCacheName, new CacheManager());
		}
		return map.get(sCacheName);
	}
}
