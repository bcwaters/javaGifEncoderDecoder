//DONE
public class ImageDescriptor extends ValidData {

	int[] localData = new int[10];

	public ImageDescriptor(GifData _byteData) {
			
			for(int i = 0; i<10; i++)
			{
				localData[i] = Byte.toUnsignedInt(_byteData.getNextByte());
			}

	}
	
	public int getImageLeft()
	{
		return localData[1] + (localData[2]<<2);
	}
	
	public int getImageTop()
	{
		return localData[3] + (localData[4]<<2);
	}
	
	public int getImageWidth()
	{
		return localData[5] + (localData[6]<<2);
	}

	public int getImageHeight()
	{
		return localData[7] + (localData[8]<<2);
	}
	
	public boolean isLocalTable()
	{
		return 1 == localData[9]>>7;
	}
	
	public boolean isInterlace()
	{
		return 1 == ((localData[9]>>6) & 1);
	}
	
	public boolean isSort()
	{
		return 1 == ((localData[9]>>5) & 1);
	}
	
	public int getLocalTableSize()
	{
		int N =  localData[9] & 7;
		//the true table size is 2^(N+1) where N is the value currently being stored
				int tableSize = 2;
					while(N>0)
					{
						tableSize *= 2;
						N--;
					}
				//each value in the table needs RGB(3 bytes)
				int bytesNeeded = tableSize *3;
				
				return bytesNeeded;
	}
	
	public String toString()
	{
	String temp = "IMAGE DESCRIPTOR: ";
	for(int i = 0; i< localData.length; i++)
		temp += Integer.toHexString(localData[i]) + " ";
	return temp + "\n";
	}

	@Override
	public void encode(EncoderData _encoderBytes)
	{
		for(int i = 0; i<localData.length; i++)
		{
			_encoderBytes.addByte((byte)localData[i]);
		}
	}

}
