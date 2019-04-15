package name.qd.fileCache.file.vo;

import java.util.List;

public class FileAccessObj {
	private String className;
	private List<byte[]> lst;
	
	public FileAccessObj(String className, List<byte[]> lst) {
		this.className = className;
		this.lst = lst;
	}
	
	public FileAccessObj(){
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setList(List<byte[]> lst) {
		this.lst = lst;
	}
	
	public List<byte[]> getList() {
		return lst;
	}
}
