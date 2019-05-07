package name.qd.fileCache;

public class FileCacheTest {
	private FileCacheManager fileCacheManager;
	private NormalCacheTest normalTest;
	private CoordinateCacheTest coordinateTest;
	
	public static void main(String[] s) {
		new FileCacheTest();
	}
	
	private FileCacheTest() {
		try {
			fileCacheManager = new FileCacheManager("./data/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		normalTest = new NormalCacheTest(fileCacheManager);
//		normalTest.setSomeData();
//		normalTest.reloadAndAdd();
//		normalTest.reloadFromCache();
//		normalTest.removeObj();
		
		coordinateTest = new CoordinateCacheTest(fileCacheManager);
//		coordinateTest.setSomeData();
//		coordinateTest.reloadAndAdd();
//		coordinateTest.reloadFromCache();
		coordinateTest.removeObj();
	}
}
