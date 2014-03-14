package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameTimer {
	public int timeCounter;
	public int sGameTime;
	public int mGameTime;
	public BufferedImage[] seconds;
	public BufferedImage[] minutes;
	
	public GameTimer(){
		timeCounter=0;
		sGameTime=0;
		mGameTime=0;
		seconds=IntToImage.toImageSmall(sGameTime);
		minutes=IntToImage.toImageSmall(mGameTime);
		
	}
	
	public void tick(){
		timeCounter++;
		if(timeCounter>=30){
			timeCounter=0;
			sGameTime++;
			seconds=IntToImage.toImageSmall(sGameTime);
		}
		if(sGameTime>=60){
			sGameTime=0;
			mGameTime++;
			seconds=IntToImage.toImageSmall(sGameTime);
			minutes=IntToImage.toImageSmall(mGameTime);
		}
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.HANGING_BASELINE,20));
		//g.drawString(Integer.toString(mGameTime), GameSystem.ABSWIDTH-50, 20);
		g.drawString(":",GameSystem.ABSWIDTH-36,26);
		//g.drawString(Integer.toString(sGameTime), GameSystem.ABSWIDTH-30, 20);
		
		for(int i=0;i<minutes.length;i++){
			g.drawImage(minutes[i], GameSystem.ABSWIDTH-50+16*i, 12, null);
		}
		
		
		
		for(int i=0;i<seconds.length;i++){
			g.drawImage(seconds[i], GameSystem.ABSWIDTH-30+16*i, 12, null);
		}
		
	}
}
