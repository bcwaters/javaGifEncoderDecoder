import java.util.ArrayList;

public class EncoderData {

	ArrayList<Byte> byteData;
	
	public EncoderData()
	{
		byteData = new ArrayList<Byte>();
	}
	
	public void addByte(byte input)
	{
		byteData.add(input);
	}
}
