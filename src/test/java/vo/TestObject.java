package vo;

import java.nio.ByteBuffer;

import name.qd.fileCache.file.trans.IFileStorageParser;

public class TestObject implements IFileStorageParser {
	private String name;
	private int id;
	private long lValue;
	private double dValue;
	private byte bValue;
	private boolean bool;
	private char cValue;
	
	private ByteBuffer buffer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getlValue() {
		return lValue;
	}

	public void setlValue(long lValue) {
		this.lValue = lValue;
	}

	public double getdValue() {
		return dValue;
	}

	public void setdValue(double dValue) {
		this.dValue = dValue;
	}

	public byte getbValue() {
		return bValue;
	}

	public void setbValue(byte bValue) {
		this.bValue = bValue;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public char getcValue() {
		return cValue;
	}

	public void setcValue(char cValue) {
		this.cValue = cValue;
	}

	public Object parseToFileFormat() {
		return null;
	}

	public void toValueObject(Object object) {
		
	}
}
