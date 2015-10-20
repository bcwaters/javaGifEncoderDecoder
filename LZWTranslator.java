import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

//Algorithm found at:
//http://www.matthewflickinger.com/lab/whatsinagif/lzw_image_data.asp
//This class will be refactored for performance improvement
//most likely to be broken up into LZWEncoder and LZWDecoder
//But for now this is used as a proof of concept
public class LZWTranslator {

	static HashMap<Integer, ArrayList<Integer>> codeTable;
	static Color[] colors;
	
	private static void setColors( ColorTable colorTable)
	{
		colors = new Color[colorTable.getTable().length/3];
		byte[] data = colorTable.getTable();
		int i = 0;
		int colorIndex = 0;
		while(i<data.length)
		{
			Color temp = new Color(data[i], data[i+1], data[i+2]);
			colors[colorIndex] = temp;
			i = i+3;
			colorIndex++;
		}
		
	}
	
	private static void initializeCodeTable()
	{
		codeTable = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i<colors.length; i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(i);
			codeTable.put(i,temp);
		}
	}
	
	public static ImageData compress()
	{
		return new ImageData(null, null, null);
	}
	
	public static int[][] decompress(ImageData _compressedData)
	{
		int LZW_minimum = _compressedData.getLZW_Minimum();
		int numberOfBits = LZW_minimum + 1;
		int clearCode = 1<< LZW_minimum;
		int codeGrow = clearCode*2;
		int EOF = clearCode + 1;
		int height = _compressedData.LocalDescriptor.getImageHeight();
		int width = _compressedData.LocalDescriptor.getImageWidth();
		int[][] uncompressedData = new int[width][height];
		//initializeCodeTable();
		int nextCode = EOF+1;
		setColors(_compressedData.getColorTable());
		initializeCodeTable();
		ArrayDeque<Byte> data = new ArrayDeque<Byte>(_compressedData.getLocalData());
		//toss the first byte it is already saved
		data.pop();
		//the next byte gives the length of the data block
		int blockLength = Byte.toUnsignedInt(data.pop());
		BitHolder test = new BitHolder(numberOfBits);
		for(int i = 0; i<blockLength; i++)
		{
			test.insertByte(data.pop());
		}
		System.out.println();
		
		//uncompressedData[0][0] = data.pop();
		
		int code;
		int oldCode;
		ArrayList<Integer> indexStream = new ArrayList<Integer>();
		//start the actual decompression
		//first number is clear code
		indexStream.add(test.getNext(numberOfBits));
		
		int k;
		//the first code is in the table always
		code = test.getNext(numberOfBits);
	
		//System.out.println(code);
		indexStream.add(code);
		//start loop
	while(!test.isEmpty()  && code != EOF)
	{
		
		oldCode = code;
		code = test.getNext(numberOfBits);
		
		if(code == EOF)
		{
			indexStream.add(code);
		}else
		if(code == clearCode)
		{
			initializeCodeTable();
			 codeGrow = clearCode*2;
			 nextCode = EOF+1;
		}else
		if(codeTable.containsKey(code))
		{
			indexStream.add(code);
			k = codeTable.get(code).get(0);
			ArrayList<Integer> newEntry = new ArrayList<Integer>(codeTable.get(oldCode));
			newEntry.add(k);
			codeTable.put(nextCode, newEntry);
			nextCode++;
			if(nextCode == codeGrow)
			{
				numberOfBits++;
				codeGrow *=2;
			}
			
		}else
		{
			k = codeTable.get(oldCode).get(0);
			ArrayList<Integer> newEntry = new ArrayList<Integer>(codeTable.get(oldCode));
			newEntry.add(k);
			codeTable.put(nextCode, newEntry);
			indexStream.add(nextCode);
			nextCode++;
			if(nextCode == codeGrow)
			{
				numberOfBits++;
				codeGrow *=2;
			}
		}
		
		
	}
		
		ArrayList<Integer> tableConversion = new ArrayList<Integer>();
		
		for(int i = 0; i<indexStream.size(); i++)
		{
			int index = indexStream.get(i);
			if(index != EOF && index != clearCode)
			{
				
				ArrayList<Integer> temp = codeTable.get(indexStream.get(i));
				for(int j = 0; j<temp.size(); j++)
				{
					tableConversion.add(temp.get(j));
				}
			}
		}
		
		
			for(int i = 0; i<tableConversion.size(); i++)
			{
				System.out.print(" "+tableConversion.get(i)+ ",");
				if ((i+1)%width == 0)
					System.out.println();
			}
		
			int currentPixel = 0;
			for(int i = 0; i<height; i++)
			{
				for(int j = 0; i<width; j++)
				{
					uncompressedData[i][j] = tableConversion.get(currentPixel);
					currentPixel++;
				}
			}
		
		
		return uncompressedData;
	}
	
	
	
	
}
