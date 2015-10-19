import java.util.ArrayList;

public class LocalData implements Encodable{
	
	final byte EXTENSION_INTRODUCER = 0x21 ;
	final byte TRAILER = 0x3B;
	final byte IMAGE_SEPARATOR = 0x2C;
	final byte GRAPHIC_CONTROL_LABEL = (byte) 0xF9;
	final byte APPLICATION_EXTENSION = (byte)0xFF;
	final byte PLAINTEXT_EXTENSION = 0x01;
	final byte COMMENT_EXTENSION = (byte)0xFE;

	private ArrayList<ValidData> DataBlocks;
	private GlobalData GlobalDataReference;

	public LocalData(GlobalData gd, GifData _byteData)
	{
		GlobalDataReference = gd;
		DataBlocks = new ArrayList<ValidData>();
		while(addDataBlock(_byteData));
	}
	
	public boolean addDataBlock(GifData _byteData )
	{
		byte dataType = _byteData.peek();
		switch(dataType)
		{
			//Image Descriptor
		case IMAGE_SEPARATOR:
			addImage(_byteData);
			return true;
		//Extension
		case EXTENSION_INTRODUCER:
			addExtension(_byteData);
			return true;
		case TRAILER:
			return false;
	
		}
		return false;
	}
	
	public void addImage(GifData _byteData)
	{
		
		ImageDescriptor tempDescriptor = new ImageDescriptor(_byteData);
		ColorTable tempTable;
		DataBlocks.add(tempDescriptor);
		if(tempDescriptor.isLocalTable())
		{
			tempTable = new ColorTable(tempDescriptor.getLocalTableSize(), _byteData);
			DataBlocks.add(tempTable);
		}else
		{
			tempTable = GlobalDataReference.getGlobalColorTable();
		}
		DataBlocks.add(new ImageData(_byteData, tempTable));
	}
	
	public void addExtension(GifData _byteData)
	{
		//TOSS THIS BYTE, every extension class knows to add this byte
		_byteData.getNextByte();
		byte extensionType = _byteData.peek();
		switch(extensionType)
		{
		case(GRAPHIC_CONTROL_LABEL):
			DataBlocks.add(new GraphicControlExtension(_byteData));
			break;
		case(APPLICATION_EXTENSION):
			DataBlocks.add(new ApplicationExtension(_byteData));
			break;
		case(PLAINTEXT_EXTENSION):
			DataBlocks.add(new PlainTextExtension(_byteData));
			break;
		case(COMMENT_EXTENSION):
			DataBlocks.add(new CommentExtension(_byteData));
			break;
		}
	}
	
	public String toString()
	{
		String temp = "";
		for(int i = 0; i<DataBlocks.size(); i++)
		{
			temp += DataBlocks.get(i);
		}
		return temp + "\n";
	}

	@Override
	public void encode(EncoderData _encoderBytes) {
		for(ValidData valid : DataBlocks)
		{
			valid.encode(_encoderBytes);
		}
		
	}

}
