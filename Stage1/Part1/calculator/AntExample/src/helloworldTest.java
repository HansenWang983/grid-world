import org.junit.Test;
import static org.junit.Assert.*;
public class helloworldTest{
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("helloworldTest");
	}
	public helloworld hello = new helloworld();
	@Test()
	public void testHello() {
		// helloworld hello = new helloworld();
		hello.hello();
		assertEquals(hello.getStr(),"Hello World!");
	}
}