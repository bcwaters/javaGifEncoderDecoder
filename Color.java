public class Color {

	private byte red;

	private byte green;

	private byte blue;

	public Color(byte _red, byte _green, byte _blue) {
		red = _red;
		green = _green;
		blue = _blue;
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

	public String getHexString()
	{
		String temp = Integer.toHexString(Byte.toUnsignedInt(red)<<16 | Byte.toUnsignedInt(green)<<8 | Byte.toUnsignedInt(blue));
		temp+="\nred :" + Integer.toHexString(Byte.toUnsignedInt(red));
		temp+="\ngreen :" + Integer.toHexString(Byte.toUnsignedInt(green));
		temp+="\nblue :" + Integer.toHexString(Byte.toUnsignedInt(blue));
		return temp;
	}
}
