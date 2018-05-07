import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;

public class ImageJUnitTest {

	@Test
	public void testBlue1() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image blue = processor.showChanelB(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_blue_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = blue.getWidth(null);
			int d = blue.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(blue, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(blue.getWidth(null), testImage.getWidth(null));
			assertEquals(blue.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testBlue2() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/2.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image blue = processor.showChanelB(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/2_blue_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = blue.getWidth(null);
			int d = blue.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(blue, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(blue.getWidth(null), testImage.getWidth(null));
			assertEquals(blue.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGreen1() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image green = processor.showChanelG(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_green_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = green.getWidth(null);
			int d = green.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(green, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(green.getWidth(null), testImage.getWidth(null));
			assertEquals(green.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGreen2() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/2.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image green = processor.showChanelG(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/2_green_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = green.getWidth(null);
			int d = green.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(green, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(green.getWidth(null), testImage.getWidth(null));
			assertEquals(green.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testRed1() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image red = processor.showChanelR(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_red_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = red.getWidth(null);
			int d = red.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(red, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(red.getWidth(null), testImage.getWidth(null));
			assertEquals(red.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testRed2() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/2.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image red = processor.showChanelR(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/2_red_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = red.getWidth(null);
			int d = red.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(red, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(red.getWidth(null), testImage.getWidth(null));
			assertEquals(red.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGray1() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image gray = processor.showGray(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_gray_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = gray.getWidth(null);
			int d = gray.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(gray, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(gray.getWidth(null), testImage.getWidth(null));
			assertEquals(gray.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGray2() throws IOException {
		try{
			ImplementImageIO myImage = new ImplementImageIO();
			Image image= myImage.myRead("/home/administrator/Desktop/ImageReader/bmptest/2.bmp");

			ImplementImageProcessor processor = new ImplementImageProcessor(); 
			Image gray = processor.showGray(image);

			FileInputStream testFile = new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/2_gray_goal.bmp");
			BufferedImage testImage = ImageIO.read(testFile);

			int w = gray.getWidth(null);
			int d = gray.getHeight(null);
			BufferedImage myTestImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
	        myTestImage.getGraphics().drawImage(gray, 0, 0, w, d, null);   
			
			for (int i = 0; i < testImage.getWidth(null); i++)
				for (int j = 0; j < testImage.getHeight(null); j++)
					assertEquals(testImage.getRGB(i, j), myTestImage.getRGB(i, j));

			assertEquals(gray.getWidth(null), testImage.getWidth(null));
			assertEquals(gray.getHeight(null), testImage.getHeight(null));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
