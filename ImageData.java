//DONE FOR NOW
import java.util.ArrayList;

public class ImageData extends ValidData {

	private ColorTable LocalColorTable;
	private ArrayList<Byte> compressionBytes;
	ImageDescriptor LocalDescriptor;
	static int count = 0;
	
	public ImageData(GifData _byteData, ColorTable _LocalColorTable, ImageDescriptor _localDescriptor)
	{
		LocalColorTable = _LocalColorTable;
		LocalDescriptor = _localDescriptor;
		compressionBytes = new ArrayList<Byte>();
		compressionBytes.add(_byteData.getNextByte());
		
		while(_byteData.peek() !=0)
		addSubBlock(_byteData);
		compressionBytes.add(_byteData.getNextByte());
		count++;
	}
	
	private void addSubBlock(GifData _byteData)
	{
		byte startBlock = _byteData.getNextByte();
		compressionBytes.add(startBlock);
		int blockLength = Byte.toUnsignedInt(startBlock);
		while(blockLength>0)
		{
			compressionBytes.add(_byteData.getNextByte());
			blockLength--;
		}
	}
	
	public ColorTable getColorTable(){
		
		return LocalColorTable;
	}
	
	public ArrayList<Byte> getLocalData()
	{
		return compressionBytes;
	}
	
	public byte getLZW_Minimum()
	{
		return compressionBytes.get(0);
	}
	
	public String toString()
	{
		String temp = "IMAGE DATA:";
		for(int i = 0; i< compressionBytes.size(); i++)
			temp += Integer.toHexString(Byte.toUnsignedInt(compressionBytes.get(i))) + " ";
		return temp + "\n";
		
	}

	@Override
	public void encode(EncoderData _encoderBytes) {
		for(int i = 0; i< compressionBytes.size(); i++)
		{
			_encoderBytes.addByte(compressionBytes.get(i));
		}
		
	}

}
