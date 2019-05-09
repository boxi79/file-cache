package name.qd.fileCache.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import name.qd.fileCache.file.vo.FileAccessObj;

public class FileStorage {
	private Path filePath;
	private ByteArrayFileWorker fileWorker;
	
	public FileStorage(String filePath) {
		this.filePath = Paths.get(filePath);
		fileWorker = new ByteArrayFileWorker();
	}
	
	public FileAccessObj loadDataFromFile(String cacheName) throws DirectoryNotEmptyException, IOException {
		
		if(!Files.exists(filePath)) {
			Files.createDirectories(filePath);
		}
		if(!Files.isDirectory(filePath)) {
			throw new DirectoryNotEmptyException(filePath.toString());
		}
		FileAccessObj fileObj = fileWorker.read(filePath + "/" + cacheName);
		return fileObj;
	}
	
	public boolean isFileExist(String cacheName) {
		return Files.exists(Paths.get(filePath.toString(), cacheName));
	}
	
	public void writeAll(String fileName, FileAccessObj fileObj) throws IOException {
		fileWorker.writeAll(filePath + "/" + fileName, fileObj);
	}
	
	public void removeFile(String fileName) throws IOException {
		File file = new File(filePath + "/" + fileName);
		Files.deleteIfExists(file.toPath());
	}
}
