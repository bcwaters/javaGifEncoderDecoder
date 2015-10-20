import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gif test = new Gif("SMILE.gif");
		System.out.println("DONE");
		
		test.scrambleTable();
		byte[] testEncode = test.encode();
		/*
		
		for(int i = 0; i<testEncode.length; i++)
		{
			System.out.print(Integer.toHexString(Byte.toUnsignedInt(testEncode[i])) + " ");
		}
		*/
		
		
		
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
			
		
		 
		
	}

}
