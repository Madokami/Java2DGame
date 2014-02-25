package game;




import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	BufferedImage background,mdTextBox;
	public static Rectangle textBox = new Rectangle(GameSystem.WIDTH*GameSystem.SCALE/10, GameSystem.HEIGHT*GameSystem.SCALE-160,GameSystem.WIDTH*GameSystem.SCALE*4/5,150);
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	BufferedReader br;
	int w = 320;
	int h = 400;
	String path;
	String[] lines = new String[4];
	int lineNum;
	private Music musicPlayer;
	private boolean musicOn;
	
	private String speaker = "";
	
	@SuppressWarnings("deprecation")
	public Story(){
		loader = new BufferedImageLoader();
		musicPlayer = new Music();
		sprite = loader.loadImage("/image/mdStand1.png");
		background = loader.loadImage("/storyBackground.png");
		mdTextBox = loader.loadImage("/image/mdTextBox.png");
			path=getClass().getResource("/script.txt").getFile();
			path = URLDecoder.decode(path);
			try {
				br = new BufferedReader(new FileReader(path));
				//line1 = br.readLine();
				lineNum=0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public void tick() {
		if(!musicOn){
			playMusic();
		}
		
	}
	private void playMusic() {
		musicPlayer.playMusic("/sound/bgm1.wav");
		musicOn=true;
	}
	private void stopMusic(){
		musicOn=false;
		musicPlayer.stopMusic();
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.PLAIN,25);
		g.setFont(f1);
		g.drawImage(background, 0, 0,GameSystem.WIDTH*GameSystem.SCALE,GameSystem.HEIGHT*GameSystem.SCALE, null);
		g.drawImage(sprite,GameSystem.WIDTH*GameSystem.SCALE-w,GameSystem.HEIGHT*GameSystem.SCALE-h,w,h,null);
		g.drawImage(mdTextBox,(GameSystem.ABSWIDTH-mdTextBox.getWidth())/2-170,GameSystem.ABSHEIGHT-mdTextBox.getHeight()-90,GameSystem.WIDTH*GameSystem.SCALE*4/5+200,220,null);
		g2d.setColor(Color.WHITE);
		//g2d.draw(textBox);
		//g2d.fill(textBox);
		g.setColor(Color.BLACK);
		renderLines(g);
	}
	private void renderLines(Graphics g) {
		try{
			g.drawString(speaker,(int)textBox.getX()+10, (int)textBox.getY());
			for(int i=0;i<lineNum;i++){
				g.drawString(lines[i],(int)textBox.getX()+10, (int)textBox.getY()+20+i*37);
			}
			//the above forloop is basically a simplification of the following
			/*
			if(lineNum>0){
				g.drawString(line1, (int)textBox.getX()+10, (int)textBox.getY()+20);
			}
			if(lineNum>1){
				g.drawString(line2,(int)textBox.getX()+10, (int)textBox.getY()+57);
			}
			if(lineNum>2){
				g.drawString(line3,(int)textBox.getX()+10, (int)textBox.getY()+94);
			}
			if(lineNum>3){
				g.drawString(line4,(int)textBox.getX()+10, (int)textBox.getY()+131);
			}
			*/
		}
		catch(NullPointerException e){
			try {
				br=new BufferedReader(new FileReader(path));
				lineNum=0;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GameSystem.state=GameSystem.STATE.GAME;
		}
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_Z){
			readNextLine();
		}
		
	}
	
	public void readNextLine(){
		if(lineNum>3){
			lineNum=0;
		}
		try{
		lines[lineNum]=br.readLine();
		}catch(Exception abc){
			System.out.println("can't read from line");
		}
		if(isEndOfSection(lines[lineNum])){
			changeToGameState();
		}
		else if(isCharacterName(lines[lineNum])){
			speaker = lines[lineNum];
			lineNum=0;
			try {
				lines[lineNum]=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(isStopSpeaking(lines[lineNum])){
			speaker="";
			lineNum=0;
			try {
				lines[lineNum]=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lineNum++;
	}

	private void changeToGameState() {
		stopMusic();
		GameSystem.state=GameSystem.STATE.GAME;
	}

	private boolean isEndOfSection(String s) {
		if(s.equals("END")){
			return true;
		}
		return false;
	}

	private boolean isStopSpeaking(String s) {
		if(s.equals("STOPSPEAKING")){
			return true;
		}
		return false;
	}

	private boolean isCharacterName(String s) {
		if(s.equals("Madoka:")){
			return true;
		}
		return false;
	}
}
