package com.java.util;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MyImage{
  public static void main(String args[])throws IOException{
	  File output = new File("C:\\Users\\do.nguyen\\Downloads\\TaiVe\\1.png");
	  BufferedImage bf = new BufferedImage(50,50, BufferedImage.TYPE_INT_ARGB);
	  bf = ImageIO.read(output);
	  File output1 = new File("C:\\Users\\do.nguyen\\Downloads\\TaiVe\\t.png");
	  ImageIO.write( bf, "png", output1);
  }//main() ends here
}//class ends here