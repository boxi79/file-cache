package name.qd.fileCache.cache;

import java.io.IOException;

public interface IFileCacheObject {
	
	public static IFileCacheObject getFileCacheObjInstance(String sClassName) throws Exception {
		return (IFileCacheObject) Class.forName(sClassName).newInstance();
	}
	
	public byte[] parseToFileFormat() throws IOException;
	public void toValueObject(byte[] bData) throws IOException;
	public String getKeyString();
}
