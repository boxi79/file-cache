package name.qd.fileCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileCacheConfigLoader {
	private static final String ACCESS_FILE = "AccessFile";
	private static final String ACCESS_DATA_TYPE = "AccessDataType";
	
	private String sConfigPath;
	private Properties properties;
	private int iAccessDataType;
	private boolean bAccessFile;
	
	public FileCacheConfigLoader(String sConfigPath) throws NumberFormatException, FileNotFoundException, IOException, Exception {
		this.sConfigPath = sConfigPath;
		
		loadProperties();
		
		readAccessFile();
		
		readAccessDataType();
	}
	
	private void loadProperties() throws FileNotFoundException, IOException {
		properties = new Properties();
		FileInputStream fIn = new FileInputStream(sConfigPath);
		properties.load(fIn);
		fIn.close();
	}
	
	private void readAccessFile() throws Exception {
		String sAccessFile = properties.getProperty(ACCESS_FILE);
		if(sAccessFile == null) {
			throw new Exception(getExceptionDesc(ACCESS_FILE));
		}
		bAccessFile = Boolean.getBoolean(sAccessFile);
	}
	
	private void readAccessDataType() throws NumberFormatException, Exception {
		String sAccessDataType = properties.getProperty(ACCESS_DATA_TYPE);
		if(sAccessDataType == null) {
			throw new Exception(getExceptionDesc(ACCESS_DATA_TYPE));
		}
		
		try {
			iAccessDataType = Integer.parseInt(ACCESS_DATA_TYPE);
		} catch(NumberFormatException e) {
			throw e;
		}
	}
	
	public boolean getAccessFile() {
		return bAccessFile;
	}
	
	public int getAccessDataType() {
		return iAccessDataType;
	}
	
	private String getExceptionDesc(String sTag) {
		return sTag + "must set in Config, check the config. ConfigPath:[" + sConfigPath + "]";
	}
}
