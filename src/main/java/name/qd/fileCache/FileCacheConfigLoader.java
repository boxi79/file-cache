package name.qd.fileCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileCacheConfigLoader {
	private static final String ACCESS_DATA_TYPE = "AccessDataType";
	private static final String FILE_PATH = "FilePath";
	
	private String sConfigPath;
	private Properties properties;
	private int iAccessDataType;
	private String sFilePath;
	
	public FileCacheConfigLoader(String sConfigPath) throws NumberFormatException, FileNotFoundException, IOException, Exception {
		this.sConfigPath = sConfigPath;
		
		loadProperties();
		
		readAccessDataType();
		
		readFilePath();
	}
	
	private void loadProperties() throws FileNotFoundException, IOException {
		properties = new Properties();
		FileInputStream fIn = new FileInputStream(sConfigPath);
		properties.load(fIn);
		fIn.close();
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
	
	private void readFilePath() {
		sFilePath = properties.getProperty(FILE_PATH);
	}
	
	public int getAccessDataType() {
		return iAccessDataType;
	}
	
	public String getFilePath() {
		return sFilePath;
	}
	
	private String getExceptionDesc(String sTag) {
		return sTag + "must set in Config, check the config. ConfigPath:[" + sConfigPath + "]";
	}
}
