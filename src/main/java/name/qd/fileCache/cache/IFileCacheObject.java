package name.qd.fileCache.cache;

public interface IFileCacheObject {
	public Object parseToFileFormat();
	public void toValueObject(Object object);
	public int getDataLength();
}
