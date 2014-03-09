package game;

import java.awt.image.BufferedImage;

public class IntToImage {
	 
	public static BufferedImage[] toImageSmall(int value){
		SpriteSheet numbers = SpriteData.num_18_16;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 18, 16);
		}
		return ret;
	}
	public static BufferedImage[] toImageGriefSyndrome(int value){
		SpriteSheet numbers = SpriteData.num_20_36;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 20, 36);
		}
		return ret;
	}
}
