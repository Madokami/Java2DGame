package game;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;

public class Story {
	BufferedImageLoader loader;
	BufferedImage sprite;
	BufferedImage background;
	public static Rectangle textBox = new Rectangle(GameSystem.WIDTH*GameSystem.SCALE/10, GameSystem.HEIGHT*GameSystem.SCALE-160,GameSystem.WIDTH*GameSystem.SCALE*4/5,150);
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	BufferedReader br;
	int w = 200;
	int h = 400;
	String path;
	String line1;
	String line2;
	String line3;
	String line4;
	int lineNum;
	@SuppressWarnings("deprecation")
	public Story(){
		loader = new BufferedImageLoader();
		sprite = loader.loadImage("/standingSprite.png");
		background = loader.loadImage("/storyBackground.png");
			path=getClass().getResource("/script.txt").getFile();
			path = URLDecoder.decode(path);
			try {
				br = new BufferedReader(new FileReader(path));
				//line1 = br.readLine();
				lineNum=1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.PLAIN,25);
		g.setFont(f1);
		g.drawImage(background, 0, 0,GameSystem.WIDTH*GameSystem.SCALE,GameSystem.HEIGHT*GameSystem.SCALE, null);
		g.drawImage(sprite,GameSystem.WIDTH*GameSystem.SCALE-w,GameSystem.HEIGHT*GameSystem.SCALE-h,w,h,null);
		g2d.setColor(Color.WHITE);
		g2d.draw(textBox);
		g2d.fill(textBox);
		g.setColor(Color.BLACK);
		try{
		if(lineNum>1){
			g.drawString(line1, (int)textBox.getX()+10, (int)textBox.getY()+20);
		}
		if(lineNum>2){
			g.drawString(line2,(int)textBox.getX()+10, (int)textBox.getY()+57);
		}
		if(lineNum>3){
			g.drawString(line3,(int)textBox.getX()+10, (int)textBox.getY()+94);
		}
		if(lineNum>4){
			g.drawString(line4,(int)textBox.getX()+10, (int)textBox.getY()+131);
		}
		}
		catch(NullPointerException e){
			try {
				br=new BufferedReader(new FileReader(path));
				lineNum=1;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GameSystem.state=GameSystem.STATE.GAME;
		}
		
		//g2d.draw(helpButton);
		//g2d.draw(quitButton);
		
	}
}
