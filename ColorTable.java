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
		
			 
		Color_Gif[] color_Gifs = new Color_Gif[this.getTable().length/3];
		byte[] data = this.getTable();
		int i = 0;
		int colorIndex = 0;
		while(i<data.length)
		{
			Color_Gif temp = new Color_Gif(data[i], data[i+1], data[i+2]);
			color_Gifs[colorIndex] = temp;
			i = i+3;
			colorIndex++;
			//System.out.println("Color added: "+ temp.getHexString());
		}
		int counter = 0;
		for(int ix = 0; ix<color_Gifs.length; ix++)
		{
			color_Gifs[ix] = new Color_Gif(ColorConverter.rgbToPalleteMatch(color_Gifs[ix].getIntValue()));
			localData[counter] = color_Gifs[ix].getRed();
			localData[counter+1] = color_Gifs[ix].getGreen();
			localData[counter+2] = color_Gifs[ix].getBlue();
			counter = counter + 3;
		}
		
	
		
		//new Random().nextBytes(localData);
		
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
