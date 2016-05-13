package name.qd.fileCache.file;

import java.io.IOException;
import java.util.List;

public interface IFileWorker {

	// return new index
	public void write(String sFilePath, Object object, int iIndex, int iLength) throws IOException ;
	@SuppressWarnings("rawtypes")
	public List read(String sFilePath, int iLength) throws IOException ;
}
