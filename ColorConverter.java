import java.awt.Color;
import java.util.ArrayList;

public class ColorConverter {

	
	public static float[] rgbToHsv(Color_Gif rgb)
	{
		float[] hsbvals = new float[3];
		return Color.RGBtoHSB(Byte.toUnsignedInt(rgb.getRed()), Byte.toUnsignedInt(rgb.getGreen()), Byte.toUnsignedInt(rgb.getBlue()), hsbvals);
	}
	
	public static int rgbToPalleteMatch(int input)
	{
		float[] colorToHsv = new float[3];
		Color_Gif rgb = new Color_Gif(input);
		Color.RGBtoHSB(Byte.toUnsignedInt(rgb.getRed()), Byte.toUnsignedInt(rgb.getGreen()), Byte.toUnsignedInt(rgb.getBlue()), colorToHsv);
		ArrayList<Color_Gif> pallete = new ArrayList<Color_Gif>();
		
		
	
		pallete.add(new Color_Gif(0x3D9e7c));
		pallete.add(new Color_Gif(0xEcb15c));
		pallete.add(new Color_Gif(0xEC8a5c));
/*

		pallete.add(new Color_Gif(0x426f99));
		pallete.add(new Color_Gif(0xAA491a));
		pallete.add(new Color_Gif(0xD19237));
	*/	//pallete.add(new Color_Gif(0x6e9e6b));
		
		
		
		
		float currentSmallest = 1;
		float keeper = 1;
		
		for(Color_Gif c : pallete)
		{
			float temp = colorToHsv[0];
			float cFloat = rgbToHsv(c)[0];
			if(Math.abs(temp - cFloat)<currentSmallest)
			{
				currentSmallest = Math.abs(temp - cFloat);
				keeper = cFloat;
			}
			
			System.out.println("input " + temp*360 + " current: " + cFloat*360 + " keeper: " + keeper);
		}
		
		return Color.HSBtoRGB(keeper, colorToHsv[1], colorToHsv[2]);
	}
	
	
	//3e311e 514433 718c85 49706d ad8458 d2a059 161618 be3403 ed713e 432314 ce914e 28130e 195b69
	//2d717e 442d1d c0a055 941907
}
