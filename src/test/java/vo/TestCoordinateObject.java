package vo;

import java.io.IOException;

import name.qd.fileCache.cache.CoordinateObject;
import name.qd.fileCache.common.TransInputStream;
import name.qd.fileCache.common.TransOutputStream;

public class TestCoordinateObject extends CoordinateObject {
	private String x;
	private String y;
	private String value;
	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public byte[] parseToFileFormat() throws IOException {
		TransOutputStream tOut = new TransOutputStream();
		tOut.writeString(x);
		tOut.writeString(y);
		tOut.writeString(value);
		return tOut.toByteArray();
	}

	@Override
	public void toValueObject(byte[] data) throws IOException {
		TransInputStream tIn = new TransInputStream(data);
		x = tIn.getString();
		y = tIn.getString();
		value = tIn.getString();
	}

	@Override
	public String getXKey() {
		return x;
	}

	@Override
	public String getYKey() {
		return y;
	}
}
