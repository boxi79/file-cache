package name.qd.fileCache.file.trans;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class TransOutputStream {
	private DataOutputStream dOut;
	private ByteArrayOutputStream bOut;
	
	public TransOutputStream() {
		bOut = new ByteArrayOutputStream();
		dOut = new DataOutputStream(bOut);
	}
}
