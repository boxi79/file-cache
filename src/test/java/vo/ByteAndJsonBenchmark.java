package vo;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class ByteAndJsonBenchmark {
	
	private TestObject testObj;
	private TestObject obj = new TestObject();
	private ByteBuffer buffer;
	private ByteBuffer bufferForRead;
	private JSONObject json = new JSONObject();
	private JSONObject jobj;
	private String jString;

	public static void main(String[] s) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(ByteAndJsonBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(5)
//				.threads(4)
				.timeUnit(TimeUnit.MICROSECONDS)
				.mode(Mode.AverageTime)
//				.mode(Mode.SingleShotTime)
				.forks(1)
				.build();
		new Runner(opt).run();
	}
	
	@Setup(Level.Trial)
	public void init() {
		testObj = new TestObject();
		testObj.setName("QQQQQ");
		testObj.setBool(true);
		testObj.setbValue((byte)'a');
		testObj.setcValue('b');
		testObj.setdValue(9.9);
		testObj.setId(888);
		testObj.setlValue(123456789);
		
		buffer = ByteBuffer.allocate(512);
		
		jobj = new JSONObject().
				put("name", testObj.getName()).
				put("id", testObj.getId()).
				put("byte", testObj.getbValue()).
				put("char", testObj.getcValue()).
				put("double", testObj.getdValue()).
				put("long", testObj.getlValue());
		
		jString = jobj.toString();
		
		bufferForRead = ByteBuffer.allocate(512);
		bufferForRead.putInt(testObj.getName().length());
		bufferForRead.put(testObj.getName().getBytes());
		bufferForRead.put(testObj.getbValue());
		bufferForRead.putChar(testObj.getcValue());
		bufferForRead.putDouble(testObj.getdValue());
		bufferForRead.putInt(testObj.getId());
		bufferForRead.putLong(testObj.getlValue());
		bufferForRead.flip();
	}
	
	@Benchmark
	public void parseToByte() {
		buffer.clear();
		buffer.putInt(testObj.getName().length());
		buffer.put(testObj.getName().getBytes());
		buffer.put(testObj.getbValue());
		buffer.putChar(testObj.getcValue());
		buffer.putDouble(testObj.getdValue());
		buffer.putInt(testObj.getId());
		buffer.putLong(testObj.getlValue());
		buffer.flip();
	}
	
	@Benchmark
	public void parseToJSON() {
		json.
		put("name", testObj.getName()).
		put("id", testObj.getId()).
		put("byte", testObj.getbValue()).
		put("char", testObj.getcValue()).
		put("double", testObj.getdValue()).
		put("long", testObj.getlValue());
//		json.toString();
	}
	
	@Benchmark
	public void byteToObj() {
		int length = bufferForRead.getInt();
		byte[] b = new byte[length];
		bufferForRead.get(b);
		obj.setName(new String(b));
		obj.setbValue(bufferForRead.get());
		obj.setcValue(bufferForRead.getChar());
		obj.setdValue(bufferForRead.getDouble());
		obj.setId(bufferForRead.getInt());
		obj.setlValue(bufferForRead.getLong());
		bufferForRead.flip();
	}
	
	@Benchmark
	public void jsonToObj() {
		JSONObject j = new JSONObject(jString);
		obj.setName(j.getString("name"));
		obj.setId(j.getInt("id"));
//		obj.setbValue((Byte)j.get("byte"));
		obj.setdValue(j.getDouble("double"));
//		obj.setcValue((Character)j.get("char"));
		obj.setlValue(j.getLong("long"));
	}
}
