import java.util.ArrayDeque;

//This class should be replaced by something much more efficient,
//right now every bit is represent by a byte

public class BitHolder {
	int currentLength;
	int nextEmpty;
	ArrayDeque<Byte> bits;
	
	public BitHolder(int _length)
	{
		currentLength = _length;
		nextEmpty = 0;
		bits = new ArrayDeque<Byte>();
	}
	

	public void insertByte(byte _input)
	{
		int input = Byte.toUnsignedInt(_input);
				
		for(int i = 0; i<8; i++)
		{
			bits.addLast((byte)(input>>i & 0x01));
		}
	}
	
	
	public boolean isEmpty()
	{
		return bits.peek() == null;
	}
	
	public int getNext(int numberOfBits)
	{
		if (bits.size()<numberOfBits)
		{
			return 0;
		}
		int bitsToReturn = 0;
		for(int i = 0; i<numberOfBits; i++)
		{
			
			bitsToReturn += bits.pollFirst()<<i;
		
		}
		return bitsToReturn;
	}
	
	public void updateLength(){
		currentLength++;
	}
	
	public void print()
	{
		Byte[] temp = bits.toArray(new Byte[bits.size()]);
		for(int i = 0; i<bits.size(); i++)
		{
			System.out.print(temp[i] + " ");
		}
		System.out.println();
	}
	

}
