package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CacheStorage {
	private Map<String, NormalCacheManager> mapNormal = new HashMap<>();
	private Map<String, CoordinateCacheManager> mapCoordinate = new HashMap<>();
	private FileStorage fileStorage;

	public CacheStorage(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}

	public NormalCacheManager getNormalCacheInstance(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		if (!mapNormal.containsKey(cacheName)) {
			readNormalCacheFromFile(cacheName, className);
		}
		return mapNormal.get(cacheName);
	}
	
	public CoordinateCacheManager getCoordinateCacheInstance(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		if(!mapCoordinate.containsKey(cacheName)) {
			readCoordinateCacheFromFile(cacheName, className);
		}
		return mapCoordinate.get(cacheName);
	}

	private void readNormalCacheFromFile(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		FileAccessObj fileAccessObj = fileStorage.loadDataFromFile(cacheName);
		if(fileAccessObj == null) {
			createNormalCacheInstance(cacheName, className);
		} else {
			if(!className.equals(fileAccessObj.getClassName())) {
				throw new IOException("Exist file but different class name. In File:" + fileAccessObj.getClassName() + ", className:" + className);
			}
			createNormalCacheInstance(cacheName, fileAccessObj.getClassName());
			NormalCacheManager cacheManager = mapNormal.get(cacheName);
			for (byte[] data : fileAccessObj.getList()) {
				NormalObject cacheObj = (NormalObject) Class.forName(fileAccessObj.getClassName()).newInstance();
				cacheObj.toValueObject(data);
				cacheManager.put(cacheObj);
			}
		}
	}
	
	private void readCoordinateCacheFromFile(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		FileAccessObj fileAccessObj = fileStorage.loadDataFromFile(cacheName);
		if(fileAccessObj == null) {
			createCoordinateCacheInstance(cacheName, className);
		} else {
			if(!className.equals(fileAccessObj.getClassName())) {
				throw new IOException("Exist file but different class name. In File:" + fileAccessObj.getClassName() + ", className:" + className);
			}
			createCoordinateCacheInstance(cacheName, fileAccessObj.getClassName());
			CoordinateCacheManager cacheManager = mapCoordinate.get(cacheName);
			for (byte[] data : fileAccessObj.getList()) {
				CoordinateObject cacheObj = (CoordinateObject) Class.forName(fileAccessObj.getClassName()).newInstance();
				cacheObj.toValueObject(data);
				cacheManager.put(cacheObj);
			}
		}
	}

	private void createNormalCacheInstance(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		mapNormal.put(cacheName, new NormalCacheManager(fileStorage, cacheName, className));
	}
	
	private void createCoordinateCacheInstance(String cacheName, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		mapCoordinate.put(cacheName, new CoordinateCacheManager(fileStorage, cacheName, className));
	}
	
	public void removeNormalCacheAndFile(String cacheName) throws IOException {
		if (mapNormal.containsKey(cacheName)) {
			mapNormal.get(cacheName).removeFile();
			mapNormal.remove(cacheName);
		}
	}
	
	public void removeCoordinateCacheAndFile(String cacheName) throws IOException {
		if (mapCoordinate.containsKey(cacheName)) {
			mapCoordinate.get(cacheName).removeFile();
			mapCoordinate.remove(cacheName);
		}
	}
	
	public void removeNormalCache(String cacheName) {
		if (mapNormal.containsKey(cacheName)) {
			mapNormal.remove(cacheName);
		}
	}
	
	public void removeCoordinateCache(String cacheName) {
		if (mapCoordinate.containsKey(cacheName)) {
			mapCoordinate.remove(cacheName);
		}
	}

	public Set<String> getNormalCacheNameSet() {
		return mapNormal.keySet();
	}
	
	public Set<String> getCoordinateCacheNameSet() {
		return mapCoordinate.keySet();
	}
}
