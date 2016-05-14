package name.qd.fileCache.cache;

public interface IFileCacheObject {
	
	public static IFileCacheObject getFileCacheObjInstance(String sClassName) throws Exception {
		IFileCacheObject fileCacheObj = null;
		fileCacheObj = (IFileCacheObject) Class.forName(sClassName).newInstance();
		return fileCacheObj;
	}
	
	public byte[] parseToFileFormat();
	public void toValueObject(byte[] bData);
	public String getKeyString();
	public int getDataLength();
}
