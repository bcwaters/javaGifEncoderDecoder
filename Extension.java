import java.util.ArrayList;

public class Extension extends ValidData {
	ArrayList<Byte> localData;
	
	public Extension(GifData _byteData)
	{
		localData = new ArrayList<Byte>();
		localData.add((byte)0x21);
		//add Extension type lable
		localData.add(_byteData.getNextByte());
		getAllBlocks(_byteData);
	}
	
	protected void addSubBlock(GifData _byteData)
	{
		byte startBlock = _byteData.getNextByte();
		localData.add(startBlock);
		int blockLength = Byte.toUnsignedInt(startBlock);
		while(blockLength>0)
		{
			localData.add(_byteData.getNextByte());
			blockLength--;
		}
	}
	
	protected void getAllBlocks(GifData _byteData)
	{
		while(_byteData.peek() !=0)
			addSubBlock(_byteData);
			localData.add(_byteData.getNextByte());
	}

}
