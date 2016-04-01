package name.qd.fileCache.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

import name.qd.fileCache.FileCacheConfigLoader;
import name.qd.fileCache.constant.AccessDataType;
import name.qd.fileCache.file.trans.IFileStorageParser;

public class FileWritter {

	public static void writeToFile(IFileStorageParser fileParser) throws IOException {
		switch(FileCacheConfigLoader.getInstance().getAccessDataType()) {
		case AccessDataType.BYTE_ARRAY:
			writeByte(fileParser);
			break;
		case AccessDataType.STRING:
			writeString(fileParser);
			break;
		}
	}
	
	private static void writeByte(IFileStorageParser fileParser) throws IOException {
		RandomAccessFile randomAccessFile = null;
		FileChannel fileChannel = null;
		try {
			String fileName = fileParser.getClass().getSimpleName();
			randomAccessFile = new RandomAccessFile(fileName, "rw");
			fileChannel = randomAccessFile.getChannel();
			fileChannel.write((ByteBuffer)fileParser.parseToFileFormat());
		} finally {
			if(randomAccessFile != null) {
				randomAccessFile.close();
			}
			if(fileChannel != null) {
				fileChannel.close();
			}
		}
	}
	
	private static void writeString(IFileStorageParser fileParser) throws IOException {
//		RandomAccessFile randomAccessFile = null;
//		FileChannel fileChannel = null;
//		try {
//			String fileName = fileParser.getClass().getSimpleName();
//			randomAccessFile = new RandomAccessFile(fileName, "rw");
//			fileChannel = randomAccessFile.getChannel();
//			CharBuffer buffer = (CharBuffer)fileParser.parseToFileFormat();
//			fileChannel.write((CharBuffer)fileParser.parseToFileFormat());
//		} finally {
//			if(randomAccessFile != null) {
//				randomAccessFile.close();
//			}
//			if(fileChannel != null) {
//				fileChannel.close();
//			}
//		}
	}
}
