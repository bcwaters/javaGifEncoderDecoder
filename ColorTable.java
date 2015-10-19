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
	
	/*
		private void setColorTable()
		{
			int i = 0;
			int colorIndex = 0;
			while(i<data.length)
			{
				Color temp = new Color(data[i], data[i+1], data[i+2]);
				colors[colorIndex] = temp;
			}
			i = i+3;
			colorIndex++;
		}
		*/
	
	public String toString()
	{
	String temp = "COLOR TABLE: ";
	for(int i = 0; i< localData.length; i++)
		temp += Integer.toHexString(Byte.toUnsignedInt(localData[i])) + " ";
	return temp;
	}
}
