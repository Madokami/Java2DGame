package game;

import java.awt.image.BufferedImage;

public class IntToImage {
	 
	public static BufferedImage[] toImageSmall(int value){
		SpriteSheet numbers = SpriteData.numSmall;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 18, 16);
		}
		return ret;
	}
}
