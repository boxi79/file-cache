package name.qd.fileCache.file.vo;

import java.util.List;

public class FileAccessObj {
	private String sClassName;
	private List<byte[]> lst;
	
	public FileAccessObj(String sClassName, List<byte[]> lst) {
		this.sClassName = sClassName;
		this.lst = lst;
	}
	
	public FileAccessObj(){
	}
	
	public void setClassName(String sClassName) {
		this.sClassName = sClassName;
	}
	
	public String getClassName() {
		return sClassName;
	}
	
	public void setList(List<byte[]> lst) {
		this.lst = lst;
	}
	
	public List<byte[]> getList() {
		return lst;
	}
}
