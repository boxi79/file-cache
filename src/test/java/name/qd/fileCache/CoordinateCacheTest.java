package name.qd.fileCache;

import java.io.IOException;

import name.qd.fileCache.cache.CoordinateCacheManager;
import name.qd.fileCache.cache.CoordinateObject;
import vo.TestCoordinateObject;

public class CoordinateCacheTest {
	private final FileCacheManager manager;
	private CoordinateCacheManager cacheManager;
	private String cacheName = TestCoordinateObject.class.getSimpleName();
	private String className = TestCoordinateObject.class.getName();

	public CoordinateCacheTest(FileCacheManager manager) {
		this.manager = manager;
	}
	
	public void setSomeData() {
		createCache();
		printCacheData();
		setDataToCache();
		printCacheData();
		saveToFile();
	}
	
	public void reloadFromCache() {
		createCache();
		printCacheData();
		saveToFile();
	}
	
	public void reloadAndAdd() {
		createCache();
		newDataToCache();
		printCacheData();
		saveToFile();
	}
	
	public void removeObj() {
		createCache();
		removeFirstOne();
		printCacheData();
		saveToFile();
	}
	
	private void createCache() {
		try {
			cacheManager = manager.getCoordinateCacheInstance(cacheName, className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setDataToCache() {
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 4; j++) {
				TestCoordinateObject obj = new TestCoordinateObject();
				obj.setX(String.valueOf(i));
				obj.setY(String.valueOf(j));
				obj.setValue(String.valueOf(i*j));
				cacheManager.put(obj);
			}
		}
	}
	
	private void newDataToCache() {
		for(int i = 3 ; i < 6 ; i++) {
			for(int j = 4 ; j < 6; j++) {
				TestCoordinateObject obj = new TestCoordinateObject();
				obj.setX(String.valueOf(i));
				obj.setY(String.valueOf(j));
				obj.setValue(String.valueOf(i*j));
				cacheManager.put(obj);
			}
		}
	}
	
	private void printCacheData() {
		for(CoordinateObject cObj : cacheManager.values()) {
			TestCoordinateObject obj = (TestCoordinateObject) cObj;
			System.out.println(obj.getX()+":"+obj.getY()+"="+obj.getValue());
		}
	}
	
	private void removeFirstOne() {
		for(CoordinateObject cObj : cacheManager.values()) {
			cacheManager.remove(cObj.getXKey(), cObj.getYKey());
			break;
		}
	}
	
	private void saveToFile() {
		try {
			cacheManager.writeCacheToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
