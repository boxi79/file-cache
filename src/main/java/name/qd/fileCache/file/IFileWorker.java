package name.qd.fileCache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileWorker {

	// return new index
	public int write(Object object, File file, int iIndex) throws IOException ;
	public Object read(File file, int iIndex) throws FileNotFoundException ;
}
