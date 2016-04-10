package name.qd.fileCache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileWorker {

	public void write(Object object, File file, int iIndex) throws IOException ;
	public void read(File file) throws FileNotFoundException ;
}
