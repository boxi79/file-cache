package name.qd.fileCache.file.trans;

public interface IFileStorageParser {
	public Object parseToFileFormat();
	public void toValueObject(Object object);
}
