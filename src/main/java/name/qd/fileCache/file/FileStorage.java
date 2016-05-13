package name.qd.fileCache.file;

import name.qd.fileCache.constant.AccessDataType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorage {
	private static Logger log = LoggerFactory.getLogger(FileStorage.class);
	
	private String sFilePath;
	private int iAccessDataType;
	private IFileWorker fileWorker;
	
	public FileStorage(String sFilePath, int iAccessDataType) {
		this.sFilePath = sFilePath;
		this.iAccessDataType = iAccessDataType;
		
		initFileWorker();
		
		log.info("Init FileStorage, FilePath:[" + sFilePath + "]");
	}
	
	private void initFileWorker() {
		fileWorker = createFileWorker();
	}
	
	private IFileWorker createFileWorker() {
		IFileWorker fileWorker = null;
		switch(iAccessDataType) {
		case AccessDataType.BYTE_ARRAY:
			fileWorker = new ByteArrayFileWorker();
			break;
		case AccessDataType.STRING:
			fileWorker = new StringFileWorker();
			break;
		}
		return fileWorker;
	}
}
