package name.qd.fileCache.cache;

import java.io.IOException;

public interface FileCacheObject {
	public byte[] parseToFileFormat() throws IOException;
	public void toValueObject(byte[] data) throws IOException;
	public String getKeyString();
}
