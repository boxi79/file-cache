package name.qd.fileCache.cache;

import java.io.IOException;

public interface FileCacheObject {
	
	public static FileCacheObject getFileCacheObjInstance(String className) throws Exception {
		return (FileCacheObject) Class.forName(className).newInstance();
	}
	
	public byte[] parseToFileFormat() throws IOException;
	public void toValueObject(byte[] bData) throws IOException;
	public String getKeyString();
}
