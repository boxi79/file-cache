![](https://github.com/qqdog1/file-cache/workflows/File%20Cache%20build/badge.svg)

## 目前版本 2.0.2

這是一個Cache管理的工具  
會在使用者需要的時間，將Cache寫入到檔案中  
在下次程式啟動時，第一次使用Cache時，會先嘗試從檔案中將Cache讀回，若沒有檔案Cache，會建立新的Cache  

## Maven Repo 目前無法使用 嘗試使用Github packages但需要github_token  
## 需要請自行抓Source code 並 mvn install
Maven Repo  

    <repositories>
        <repository>
            <id>file-cache-mvn-repo</id>
            <url>https://maven.pkg.github.com/qqdog1/file-cache</url>
        </repository>
    </repositories>
    
Dependency

    <dependency>
        <groupId>name.qd</groupId>
        <artifactId>fileCache</artifactId>
        <version>2.0.2</version>
    </dependency>

## 1. 建立FileCache

    FileCacheManager fileCacheManager = new FileCacheManager("./data/");

  建立一個FileCacheManager，並指定檔案路徑。  
  Cache將會以檔案形勢存在指定資料夾，重開讀回時亦從相同資料夾讀回  

## 2. 繼承並實作對應CacheVo  
### 一對一Cache  
依照需要的Cache類型，實作相對的Vo  
一般一個Key對應一個Object，繼承NormalObject  

    public class TestNormalObject extends NormalObject  

並且實作下列Method  
public abstract String getKeyString();  
public abstract byte[] parseToFileFormat() throws IOException;  
public abstract void toValueObject(byte[] data) throws IOException;  

<table>
<tr><td>getKeyString</td><td>Key的組法</td></tr>
<tr><td>parseToFileFormat</td><td>VO轉成byte[]的實作</td></tr>
<tr><td>toValueObject</td><td>byte[]轉回成VO的實作</td></tr>
</table> 

### 座標型Cache  
若要使用座標型Cache，x軸及y軸對應一個值，且可以一次取得同x上的所有值或同y上的所有值  
需繼承CoordinateObject  

    public class TestCoordinateObject extends CoordinateObject  

亦需實作下列Method  
public abstract String getXKey();  
public abstract String getYKey();  
public abstract byte[] parseToFileFormat() throws IOException;  
public abstract void toValueObject(byte[] data) throws IOException;  

<table>
<tr><td>getXKey</td><td>X座標Key的組法</td></tr>
<tr><td>getYKey</td><td>Y座標Key的組法/td></tr>
<tr><td>parseToFileFormat</td><td>VO轉成byte[]的實作</td></tr>
<tr><td>toValueObject</td><td>byte[]轉回成VO的實作</td></tr>
</table> 

byte[]與VO間的轉換可使用 TransInputStream & TransOutputStream  

    TransOutputStream tOut = new TransOutputStream();
    tOut.writeString(date);

    TransInputStream tIn = new TransInputStream(bData);
    String date = tIn.getString();

## 3. 取得對應CacheManager
CacheManager就是Cache的主體  
擁有CacheManager才可對Cache做新增修改刪除  

一般型NormalCache  

    NormalCacheManager cacheManager = fileCacheManager.getNormalCacheInstance("cacheName", "className");  

座標型CoordinateCache

    CoordinateCacheManager cacheManager = fileCacheManager.getCoordinateCacheInstance("cacheName", "className");  

cacheName為你想要這個Cache的名稱  
className為這個cache內的class名稱，請直接用Class.class.getName()  
用於讀取檔案後還原class object用，所以名稱一定要對

嘗試CacheManager的同時，程式會檢查是否已有對應檔案存在，若有表示舊有Cache存在需讀回至記憶體。  
若沒有則建立新的Cache  

## 4. 操作Cache
不論是一般型或座標型，相同的Method如下  

    public void put(NormalObject/CoordinateObject value)
將Object放入Cache中

    public void remove(String key)
從Cache中移除對應Object
    
    public Collection<NormalObject/CoordinateObject> values()
取得Cache中所有Object

    public void removeFile() throws IOException  
清除所有檔案

從Cache中取得Object的方式不同
### 一般型NormalCache
    public NormalObject get(String key)
用Key從Cache中取得Object  

### 座標型CoordinateCache
    public CoordinateObject get(String x, String y)
取得Cache中對應座標上的Object  

    public List<CoordinateObject> getByX(String x)
取得Cache中所有對應x值的Object  

    public List<CoordinateObject> getByY(String y)
取得Cache中所有對應y值的Object  
    
## 5. 寫檔
寫檔的時機由使用者自行控制  

    cacheManager.writeCacheToFile();

