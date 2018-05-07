import imagereader.Runner;
public final class ImageRunner {

	private ImageRunner() {}
	 public static void main(String[] args) {
	       	ImplementImageIO imageioer = new ImplementImageIO();  
	        ImplementImageProcessor processor = new ImplementImageProcessor();  
	        Runner.run(imageioer, processor);  
	 } 
}