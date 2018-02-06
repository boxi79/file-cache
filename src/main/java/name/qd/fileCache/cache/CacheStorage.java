package name.qd.fileCache.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheStorage {
	private Map<String, CacheManager> map = new HashMap<>();
	private FileStorage fileStorage;
	
	public CacheStorage(FileStorage fileStorage) throws Exception {
		this.fileStorage = fileStorage;
//		Map<String, FileAccessObj> mapData = fileStorage.loadDataFromFile();
//		
//		for(Entry<String, FileAccessObj> entry : mapData.entrySet()) {
//			CacheManager cacheManager = createCacheInstance(entry.getKey(), entry.getValue().getClassName());
//			for(byte[] bData : entry.getValue().getList()) {
//				FileCacheObject cacheObj = FileCacheObject.getFileCacheObjInstance(entry.getValue().getClassName());
//				cacheObj.toValueObject(bData);
//				cacheManager.put(cacheObj.getKeyString(), cacheObj);
//			}
//		}
	}
	
	public CacheManager getCacheInstance(String cacheName) {
		if(!map.containsKey(cacheName)) {
			try {
				FileAccessObj fileAccessObj = fileStorage.loadDataFromFile(cacheName);
				CacheManager cacheManager = createCacheInstance(cacheName, fileAccessObj.getClassName());
				for(byte[] data : fileAccessObj.getList()) {
					FileCacheObject cacheObj = FileCacheObject.getFileCacheObjInstance(fileAccessObj.getClassName());
					cacheObj.toValueObject(data);
					cacheManager.put(cacheObj.getKeyString(), cacheObj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.get(cacheName);
	}
	
	public CacheManager createCacheInstance(String cacheName, String className) throws Exception {
		if(!map.containsKey(cacheName)) {
			map.put(cacheName, new CacheManager(fileStorage, cacheName, className));
		}
		return map.get(cacheName);
	}
	
	public Set<String> getCacheNameSet() {
		return map.keySet();
	}
}
