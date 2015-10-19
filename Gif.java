import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Gif implements Encodable {

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
			
			//debuging
			writeFile();
			//System.out.println(global);
			//System.out.println(local);
			
	}
	
	
	public void writeFile()
	{
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("GIF_OUTPUT.txt", "UTF-8");
			writer.println(global.toString());
			writer.println(local.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(writer != null)
		{
			writer.close();
		}
	
	}
	
	public byte[] encode()
	{
		EncoderData encodedData = new EncoderData();
		encode(encodedData);
		//add trailer
		encodedData.addByte((byte) 0x3B);
		byte[] temp = new byte[encodedData.byteData.size()];
		for(int i =0; i<encodedData.byteData.size(); i++)
		{
			temp[i] = encodedData.byteData.get(i);
		}
		return temp;
	}
	
	public void scrambleTable()
	{
		global.scrambleTable();
	}
	
	public void encode(EncoderData _encoderBytes)
	{
		global.encode(_encoderBytes);
		local.encode(_encoderBytes);
		
	}
	
	public byte[] loadFile(String FileName) throws IOException
	{
		byte[] bFile;
		FileInputStream in = null;
		
	        try {
	        	File file = new File("src//Res//" + FileName);
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
