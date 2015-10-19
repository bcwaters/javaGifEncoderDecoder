//DONE

public class LogicalScreenDescriptor {

	int[] localData = new int[7];
	int packedByte;
	
	public LogicalScreenDescriptor(GifData _byteData) {
		for(int i = 0; i<7; i++)
		{
			localData[i] = Byte.toUnsignedInt(_byteData.getNextByte());
			
		}
		packedByte = localData[4];
		
	}

	public int getCanvasWidth() {
		return localData[0] + (localData[1]<<2);
	}

	public int getCanvasHeight() {
		return localData[2] + (localData[3]<<2);
	}

	public boolean getGlobalTableFlag() {
		
		return 1==(packedByte>>7);
	}

	public int getColorResolution() {
		return (localData[4]>>4) & 0x7;
	}

	public boolean getSortFlag() {
		return 1==((localData[4] >> 3)&1);
	}

	
	//the value within packed bit is N, however this method calculates the actually size in bits
	public int getGlobalColorTableSize() {
		int N = localData[4] & 0x7;
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
	String temp = "LOGICAL SCREEN DESCRIPTOR : ";
	for(int i = 0; i< localData.length; i++)
		temp += Integer.toHexString((localData[i])) + " ";
	return temp + "\n   TableSize: " + getGlobalColorTableSize() + "\n";
	}


}
