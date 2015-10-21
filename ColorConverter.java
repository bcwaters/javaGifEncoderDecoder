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
		
		pallete.add(new Color_Gif(0x3e311e));
		pallete.add(new Color_Gif(0x514433));
		pallete.add(new Color_Gif(0x718c85));
		pallete.add(new Color_Gif(0x49706d));
		pallete.add(new Color_Gif(0xad8458));
		pallete.add(new Color_Gif(0xd2a059));
		pallete.add(new Color_Gif(0x161618));
		pallete.add(new Color_Gif(0xbe3403));
		pallete.add(new Color_Gif(0xed713e));
		pallete.add(new Color_Gif(0x432314));
		pallete.add(new Color_Gif(0xce914e));
		pallete.add(new Color_Gif(0x28130e));
		pallete.add(new Color_Gif(0x195b69));
		pallete.add(new Color_Gif(0x2d717e));
		pallete.add(new Color_Gif(0x442d1d));
		pallete.add(new Color_Gif(0xc0a055));
		pallete.add(new Color_Gif(0x941907));
		
		float currentSmallest = 1;
		
		for(Color_Gif c : pallete)
		{
			float temp = colorToHsv[0];
			float cFloat = rgbToHsv(c)[0];
			if(Math.abs(temp - cFloat)<currentSmallest)
			{
				currentSmallest = cFloat;
			}
		}
		
		return Color.HSBtoRGB(currentSmallest, colorToHsv[1], colorToHsv[2]);
	}
	
	
	//3e311e 514433 718c85 49706d ad8458 d2a059 161618 be3403 ed713e 432314 ce914e 28130e 195b69
	//2d717e 442d1d c0a055 941907
}
