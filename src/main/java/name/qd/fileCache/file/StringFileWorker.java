package name.qd.fileCache.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

class StringFileWorker implements IFileWorker {

	@Override
	public void write(String sFilePath, Object object, int iIndex, int iLength) throws IOException {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List read(String sFilePath, int iLength) throws FileNotFoundException {
		return null;
	}
}
