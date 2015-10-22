public class Color_Gif {

	private byte red;

	private byte green;

	private byte blue;

	public Color_Gif(byte _red, byte _green, byte _blue) {
		red = _red;
		green = _green;
		blue = _blue;
	}
	
	public Color_Gif(int rgb)
	{
		red = (byte) (rgb>>16 &0xFF);
		green = (byte)(rgb >>8 & 0xFF);
		blue = (byte)(rgb & 0xFF);
	}

	public byte getRed() {
		return red;
	}

	public byte getBlue() {
		return blue;
	}

	public byte getGreen() {
		return green;
	}

	public int getIntValue()
	{
		return Byte.toUnsignedInt(red)<<16 | Byte.toUnsignedInt(green)<<8 | Byte.toUnsignedInt(blue);
	}
	public String getHexString()
	{
		String temp = Integer.toHexString(Byte.toUnsignedInt(red)<<16 | Byte.toUnsignedInt(green)<<8 | Byte.toUnsignedInt(blue));
		temp+="\nred :" + Integer.toHexString(Byte.toUnsignedInt(red));
		temp+="\ngreen :" + Integer.toHexString(Byte.toUnsignedInt(green));
		temp+="\nblue :" + Integer.toHexString(Byte.toUnsignedInt(blue));
		return temp;
	}
}
