package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheManager {
	private Map<String, IFileCacheObject> map = new HashMap<String, IFileCacheObject>();
	private Map<String, Integer> mapIndex = new HashMap<String, Integer>();
	private AtomicInteger atomicSqn = new AtomicInteger();
	
	CacheManager() {
	}
	
	public IFileCacheObject get(String sKey) {
		return map.get(sKey);
	}
	
	public int put(String sKey, IFileCacheObject value) {
		if(map.containsKey(sKey)) {
			return update(sKey, value);
		} else {
			return insert(sKey, value);
		}
	}
	
	private int update(String sKey, IFileCacheObject value) {
		map.put(sKey, value);
		return mapIndex.get(sKey);
	}
	
	private int insert(String sKey, IFileCacheObject value) {
		map.put(sKey, value);
		int iSqn = atomicSqn.getAndIncrement();
		mapIndex.put(sKey, iSqn);
		return iSqn;
	}
}
