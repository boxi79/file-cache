package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	private Map<String, Object> map = new HashMap<String, Object>();
	private Map<String, Integer> mapIndex = new HashMap<String, Integer>();
	
	CacheManager() {
	}
	
	public Object get(String sKey) {
		return map.get(sKey);
	}
	
	public int put(String sKey, Object value) {
		
		return 0;
	}
}
