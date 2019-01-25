這是一個Cache管理的工具  
會在使用者需要的時間，將Cache寫入到檔案中  
並且在下次程式啟動時，從檔案中將Cache讀回

Maven Repo

    <repositories>
        <repository>
            <id>FileCache-mvn-repo</id>
            <url>https://raw.github.com/qqdog1/FileCache/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
    
Dependency

    <dependency>
        <groupId>name.qd</groupId>
        <artifactId>fileCache</artifactId>
        <version>1.0</version>
    </dependency>

## 1. 建立FileCache

    FileCacheManager fileCacheManager = new FileCacheManager("./data/");

建立一個FileCacheManager，並指定檔案路徑。  
檔案路徑裡面只能有這個工具產生的檔案  
因為重開時，會去資料夾內讀取所有檔案並還原成Cache

## 2. 實做CacheVo
繼承IFileCacheObject，並實作相關Methods

    public class TestObject implements IFileCacheObject  

需實做的Methods

    public byte[] parseToFileFormat() throws IOException;
    public void toValueObject(byte[] bData) throws IOException;
    public String getKeyString();

<table>
<tr><td>parseToFileFormat</td><td>VO轉成byte[]的實作</td></tr>
<tr><td>toValueObject</td><td>byte[]轉回成VO的實作</td></tr>
<tr><td>getKeyString</td><td>Key的組法</td></tr>
</table>

byte[]與VO間的轉換可使用 TransInputStream & TransOutputStream  

    TransOutputStream tOut = new TransOutputStream();
    tOut.writeString(date);

    TransInputStream tIn = new TransInputStream(bData);
    String date = tIn.getString();

## 3. 建立CacheManager
CacheManager就是Cache的主體  
擁有CacheManager才可對Cache做新增修改刪除  

    CacheManager cacheManager = fileCacheManager.createCacheInstance("cacheName", "className");

cacheName為你想要這個Cache的名稱  
className為這個cache內的class名稱，建議直接用Class.class.getName()  
用於讀取檔案後還原class object用，所以名稱一定要對
(因為所有Object都繼承IFileCacheObject，但我無法取得實作名稱)

## 4. 取得Cache
FileCacheManager.getCacheInstance就可取得CacheManager  
以CacheName就可取回

    CacheManager cacheManager = fileCacheManager.getCacheInstance(TestObject.class.getName());


## 5. 操作Cache

    public IFileCacheObject get(String sKey) 
    public void put(String sKey, IFileCacheObject value)
    public void delete(String sKey)

## 6. 寫檔
寫檔的時機由使用者自行控制  

    cacheManager.writeCacheToFile();

