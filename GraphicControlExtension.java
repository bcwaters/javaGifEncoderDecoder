public class GraphicControlExtension extends Extension {

	byte[] localDataArray = new byte[8];

	public GraphicControlExtension(GifData _byteData)
	{
		//This precedes any extension
		super(_byteData);	
	}
	
	public String toString()
	{
	String temp = "GRAPHIC CONTROL EXTENSION: ";
	for(int i = 0; i< localData.size(); i++)
		temp += Integer.toHexString(Byte.toUnsignedInt(localData.get(i))) + " ";
	return temp + "\n";
	}

}
