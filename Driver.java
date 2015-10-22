import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gif test = new Gif(args.length>0  ? args[0]:"giphy.gif");
		System.out.println("DONE");
		
		
		test.scrambleTable();
		byte[] testEncode = test.encode();
		//int[][] decoded = test.decode();
		
		
	
		
		
		
		
			FileOutputStream stream = null;
			try {
				stream = new FileOutputStream("scramble.gif");
				   stream.write(testEncode);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(stream!= null)
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			/*
			try{
	            BufferedImage img = new BufferedImage( 
	                10, 10, BufferedImage.TYPE_INT_RGB );

	            File f = new File("MyFile.png");
	           
	            
	            for(int x = 0; x < 10; x++){
	                for(int y = 0; y < 10; y++){
	                    img.setRGB(x, y, decoded[y][x]);
	                    
	                }
	            }
	            ImageIO.write(img, "PNG", f);
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
			
			System.out.println();
			float[] hsv = ColorConverter.rgbToHsv(new Color_Gif(decoded[6][0]));
			System.out.println(hsv[0] + " " + hsv[1] + " " + hsv[2]);
			*/
	}

}
