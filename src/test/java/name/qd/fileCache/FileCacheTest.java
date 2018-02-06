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
		fileCacheManager = new FileCacheManager("./data/");
		
		createCacheData();
		printOutData();
	}
	
	private void createCacheData() {
		CacheManager cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getSimpleName());
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
		CacheManager cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getSimpleName());
		
		TestObject to = (TestObject) cacheManager.get("QQ1");
		
		System.out.println(to.getName());
		System.out.println(to.getId());
		System.out.println(to.isBool());
		System.out.println(to.getdValue());
		System.out.println(to.getlValue());
		
		cacheManager.remove("QQ3");
	}
	
	
}
