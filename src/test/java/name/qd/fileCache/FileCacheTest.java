package name.qd.fileCache;

import java.io.IOException;

import name.qd.fileCache.cache.CacheManager;
import vo.TestObject;

public class FileCacheTest {
	private FileCacheManager fileCacheManager;
	
	public static void main(String[] s) {
		new FileCacheTest();
	}
	
	private FileCacheTest() {
		try {
			fileCacheManager = new FileCacheManager("./data/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		createCacheData();
		printOutData();
		
//		removeCache();
	}
	
	private void createCacheData() {
		CacheManager cacheManager = null;
		try {
			cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getSimpleName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(cacheManager == null) {
			cacheManager = fileCacheManager.createCacheInstance(TestObject.class.getSimpleName(), TestObject.class.getName());
		}
		
		for(int i = 0 ; i < 10 ; i++) {
			TestObject to = new TestObject();
			to.setName("QQ" + i);
			to.setId(i);
			to.setBool(true);
			to.setdValue(i);
			cacheManager.put(to.getKeyString(), to);
		}
		
		try {
			cacheManager.writeCacheToFile();
			cacheManager.remove("QQ3");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void printOutData() {
		CacheManager cacheManager = null;
		try {
			cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TestObject to = (TestObject) cacheManager.get("QQ1");
		
		System.out.println(to.getName());
		System.out.println(to.getId());
		System.out.println(to.isBool());
		System.out.println(to.getdValue());
		System.out.println(to.getlValue());
		
		cacheManager.remove("QQ3");
	}
	
	private void removeCache() {
		CacheManager cacheManager = null;
		try {
			cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getSimpleName());
			cacheManager.removeFile();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
