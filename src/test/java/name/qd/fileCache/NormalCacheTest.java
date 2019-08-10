package name.qd.fileCache;

import java.io.IOException;

import name.qd.fileCache.cache.NormalCacheManager;
import name.qd.fileCache.cache.NormalObject;
import vo.TestNormalObject;

public class NormalCacheTest {
	private final FileCacheManager manager;
	private NormalCacheManager cacheManager;
	private String cacheName = TestNormalObject.class.getSimpleName();
	private String className = TestNormalObject.class.getName();

	public NormalCacheTest(FileCacheManager manager) {
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
			cacheManager = manager.getNormalCacheInstance(cacheName, className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setDataToCache() {
		for(int i = 0 ; i < 10 ; i++) {
			TestNormalObject obj = new TestNormalObject();
			obj.setName(String.valueOf(i));
			cacheManager.put(obj);
		}
	}
	
	private void newDataToCache() {
		for(int i = 10 ; i < 20 ; i++) {
			TestNormalObject obj = new TestNormalObject();
			obj.setName(String.valueOf(i));
			cacheManager.put(obj);
		}
	}
	
	private void printCacheData() {
		for(NormalObject normalObj : cacheManager.values()) {
			TestNormalObject obj = (TestNormalObject) normalObj;
			System.out.println(obj.getName());
		}
	}
	
	private void removeFirstOne() {
		for(NormalObject normalObj : cacheManager.values()) {
			cacheManager.remove(normalObj.getKeyString());
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
	
	public void removeAll() {
		createCache();
		try {
			cacheManager.removeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
