package name.qd.fileCache.cache;

import java.io.IOException;

public interface FileCacheObject {
	public abstract byte[] parseToFileFormat() throws IOException;
	public abstract void toValueObject(byte[] data) throws IOException;
}
