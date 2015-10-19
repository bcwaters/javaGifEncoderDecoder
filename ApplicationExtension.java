import java.util.ArrayList;

public class ApplicationExtension extends Extension {
	
	//This precedes any extension
			ArrayList<Byte> localData = new ArrayList<Byte>();
			
	public ApplicationExtension(GifData _byteData)
	{
		super(_byteData);
		
	}

	public void addSubBlock(GifData _byteData)
	{
		super.addSubBlock(_byteData);
	}
	
	public void getAllBlocks(GifData _byteData)
	{
		super.getAllBlocks(_byteData);
	}
}
