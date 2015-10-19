//DONE

public class GifData {
	
	byte[] data;
	int index;
	boolean validData;
	
	public GifData(byte[] _data)
	{
		data = _data;
		index = 0;
		validData = true;
	}
	
	public byte getNextByte()
	{
		if(index+1<data.length)
		{
			index++;
			return data[index - 1];
		}else
		{
			return 0;
		}
	}
	
	public int getPosition()
	{
		return index;
	}
	
	public byte peek()
	{
		if(index<data.length)
		{
		return data[index];
		}else
		{
			return 0;
		}
	}
	
	public boolean isValidData()
	{
		return validData;
	}
	
	public void setInvalid()
	{
		 validData = false;
	}

}
