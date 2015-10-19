import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gif {

	final byte EXTENSION_INTRODUCER = 0x21 ;
	final byte TRAILER = 0x3B;
	final byte IMAGE_SEPARATOR = 0x2C;
	final byte GRAPHIC_CONTROL_LABEL = (byte) 0xF9;
	
	private GifData byteData;
	private GlobalData global;
	private LocalData local;
	
	
	public Gif(String FileName)
	{
		
		boolean loadedFile = true;
			try {
			byteData = new GifData (loadFile(FileName));
		    } catch (IOException e) {
			e.printStackTrace();
			loadedFile = false;
		}	
			if(loadedFile)
			{
				setGlobalData();
				setLocalData();
			}else
			{
				System.out.println("ERROR IN LOADING FILE");
			}
			
			System.out.println(global);
			System.out.println(local);
	}
	
	public byte[] loadFile(String FileName) throws IOException
	{
		byte[] bFile;
		FileInputStream in = null;
		
	        try {
	        	File file = new File("src//Res//testGimp.gif");
	            in = new FileInputStream(file);
	            bFile = new byte[(int) file.length()];
	            in.read(bFile);
	            
	 
	            } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					byte[] temp = {0};
					bFile = temp;
				}
	         finally {
	            if (in != null) {
	                in.close();
	            }
	        } 
	        return bFile;
	}
	
	private void setGlobalData()
	{
		global = new GlobalData(byteData);
	}
	
	private void setLocalData(){
		local = new LocalData(global, byteData);
	}
	
	
	public void printUnsignedByte(byte b)
	{
		System.out.print(Integer.toHexString(Byte.toUnsignedInt(b)) + " ");
	}

}
