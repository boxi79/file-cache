package name.qd.fileCache.cache;

import java.io.IOException;

public interface IFileCacheObject {
	
	public static IFileCacheObject getFileCacheObjInstance(String sClassName) throws Exception {
		IFileCacheObject fileCacheObj = null;
		fileCacheObj = (IFileCacheObject) Class.forName(sClassName).newInstance();
		return fileCacheObj;
	}
	
	public byte[] parseToFileFormat() throws IOException;
	public void toValueObject(byte[] bData) throws IOException;
	public String getKeyString();
	public int getDataLength();
}
