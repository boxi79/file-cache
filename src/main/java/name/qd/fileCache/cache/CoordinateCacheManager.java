package name.qd.fileCache.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.qd.fileCache.file.FileStorage;
import name.qd.fileCache.file.vo.FileAccessObj;

public class CoordinateCacheManager {
	private Map<String, List<CoordinateObject>> mapX = new HashMap<>();
	private Map<String, List<CoordinateObject>> mapY = new HashMap<>();
	private Map<String, Map<String, CoordinateObject>> map = new HashMap<>();
	private final FileStorage fileStorage;
	private final String cacheName;
	private final String className;
	
	CoordinateCacheManager(FileStorage fileStorage, String cacheName, String className) {
		this.fileStorage = fileStorage;
		this.cacheName = cacheName;
		this.className = className;
	}
	
	public List<CoordinateObject> getByX(String x) {
		return mapX.get(x);
	}
	
	public List<CoordinateObject> getByY(String y) {
		return mapY.get(y);
	}
	
	public CoordinateObject get(String x, String y) {
		if(map.containsKey(x)) {
			return map.get(x).get(y);
		}
		return null;
	}
	
	public void put(CoordinateObject value) {
		String x = value.getXKey();
		String y = value.getYKey();
		if(!map.containsKey(x)) {
			map.put(x, new HashMap<>());
		}
		map.get(x).put(y, value);
		
		if(!mapX.containsKey(x)) {
			mapX.put(x, new ArrayList<>());
		}
		mapX.get(x).add(value);
		if(!mapY.containsKey(y)) {
			mapY.put(y, new ArrayList<>());
		}
		mapY.get(y).add(value);
	}
	
	public void remove(String x, String y) {
		if(map.containsKey(x)) {
			Map<String, CoordinateObject> mapInner = map.get(x);
			if(mapInner.containsKey(y)) {
				mapInner.remove(y);
				
				List<CoordinateObject> lstX = mapX.get(x);
				CoordinateObject removeObj = null;
				for(CoordinateObject cache : lstX) {
					if(y.equals(cache.getYKey())) {
						removeObj = cache;
					}
				}
				lstX.remove(removeObj);
				
				List<CoordinateObject> lstY = mapY.get(y);
				for(CoordinateObject cache : lstY) {
					if(x.equals(cache.getXKey())) {
						removeObj = cache;
					}
				}
				lstY.remove(removeObj);
			}
		}
	}
	
	public Collection<CoordinateObject> values() {
		List<CoordinateObject> lst = new ArrayList<>();
		for(Map<String, CoordinateObject> inner : map.values()) {
			lst.addAll(inner.values());
		}
		return lst;
	}
	
	public void writeCacheToFile() throws IOException {
		ArrayList<byte[]> lst = new ArrayList<byte[]>();
		for(FileCacheObject cacheObj : values()) {
			lst.add(cacheObj.parseToFileFormat());
		}
		fileStorage.writeAll(cacheName, new FileAccessObj(className, lst));
	}
	
	public String getClassName() {
		return className;
	}
	
	public void removeFile() throws IOException {
		fileStorage.removeFile(cacheName);
	}
}
