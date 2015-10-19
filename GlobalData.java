//DONE

public class GlobalData {

	private byte[] header = new byte[6];
	private ColorTable GlobalColorTable;
	private LogicalScreenDescriptor screenDescriptor;
	
	public GlobalData(GifData _byteData)
	{
		setHeaderBlock(_byteData);
		setLogicalScreenDescriptor(_byteData);
		if(screenDescriptor.getGlobalTableFlag())
		setGlobalColorTable(_byteData);
	}
	
	
	private void setHeaderBlock(GifData _byteData)
	{
		for(int i = 0; i<6; i++)
		{
			header[i] = _byteData.getNextByte();
		}
	}
	
	private void setLogicalScreenDescriptor(GifData _byteData)
	{
		screenDescriptor = new LogicalScreenDescriptor(_byteData);
	}
	
	private void setGlobalColorTable(GifData _byteData)
	{
		int tableSize = screenDescriptor.getGlobalColorTableSize();
		GlobalColorTable = new ColorTable(tableSize, _byteData);
	}

	public ColorTable getGlobalColorTable() {
		return GlobalColorTable ;
	}
	
	public LogicalScreenDescriptor getLogicalScreenDescriptor()
	{
		return screenDescriptor;
	}
	
	public byte[] getHeader()
	{
		return header;
	}

	public String toString()
	{
		return screenDescriptor.toString() + "GLOBAL " + GlobalColorTable.toString();
	}
}
