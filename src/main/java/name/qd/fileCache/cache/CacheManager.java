package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	private Map<String, Object> map = new HashMap<String, Object>();
	private Map<Integer, Object> mapIndex = new HashMap<Integer, Object>();
	
	CacheManager() {
	}
	
	public Object get(String sKey) {
		return null;
	}
	
	public int put(String sKey, Object value) {
		return 0;
	}
}
