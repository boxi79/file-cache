package name.qd.fileCache.file;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorage {
	private static Logger log = LoggerFactory.getLogger(FileStorage.class);
	
	private String sFilePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String sFilePath) {
		this.sFilePath = sFilePath;
		fileWorker = new ByteArrayFileWorker();
		log.info("Init FileStorage, FilePath:[" + sFilePath + "]");
	}
	
	public void write(String sFileName, byte[] bData, int iIndex, int iLength) throws IOException {
		fileWorker.write(sFilePath + "/" + sFileName, bData, iIndex, iLength);
	}
	
	public List<byte[]> read(String sFileName, int iLength) throws IOException {
		return fileWorker.read(sFilePath + "/" + sFileName, iLength);
	}
}
