public class GraphicControlExtension extends Extension {

	byte[] localData = new byte[8];

	public GraphicControlExtension(byte packedField, short delayBytes, byte transparentByte) {
		
	}
	
	public GraphicControlExtension(GifData _byteData)
	{
		//This precedes any extension
		localData[0] = 0x21;
		for(int i = 1; i<8; i++)
		{
			localData[i] = _byteData.getNextByte();
		}
	}
	
	public String toString()
	{
	String temp = "GRAPHIC CONTROL EXTENSION: ";
	for(int i = 0; i< localData.length; i++)
		temp += Integer.toHexString(Byte.toUnsignedInt(localData[i])) + " ";
	return temp + "\n";
	}

}
