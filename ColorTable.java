import java.util.Random;

//DONE
//REMOVED the actual color array for now, extra bulk with no payoff
public class ColorTable extends ValidData {

	//private Color[] colors;
	private byte[] localData;

	public ColorTable(int tableSize, GifData _byteData) {
		
		localData = new byte[tableSize];
		for(int i = 0; i<tableSize; i++)
		{
			localData[i] = _byteData.getNextByte();
		}
		
		//colors = new Color[data.length/3];
		//setColorTable();
	}
	
	public byte[] getTable()
	{
		
		return localData;
	}
	
	public void scrambleTable()
	{
		
			 new Random().nextBytes(localData);
		
	}
	
	
	
	
	public String toString()
	{
	String temp = "COLOR TABLE: ";
	for(int i = 0; i< localData.length; i++)
		temp += Integer.toHexString(Byte.toUnsignedInt(localData[i])) + " ";
	return temp;
	}

	@Override
	public void encode(EncoderData _encoderBytes) {
		for(int i = 0; i< localData.length; i++)
		{
			_encoderBytes.addByte(localData[i]);
		}
		
	}
}
