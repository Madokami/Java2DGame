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
	BufferedImage background,mdTextBox,textBox,naTextBox,hoTextBox,maTextBox,saTextBox,kyTextBox,qbTextBox;
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	BufferedReader br;
	int w = 320;
	int h = 400;
	String path;
	String[] lines = new String[4];
	int lineNum;
	private boolean speaking;
	
	@SuppressWarnings("deprecation")
	public Story(){
		loader = new BufferedImageLoader();
		//sprite = loader.loadImage("/image/mdStand1.png");
		sprite = loader.loadImage("/image/talk/mdTalk3.png");
		background = loader.loadImage("/image/tempBg.png");
		mdTextBox = loader.loadImage("/image/mdTextBox.png");
		textBox = loader.loadImage("/image/naTextBox.png");
		naTextBox = loader.loadImage("/image/naTextBox.png");
		hoTextBox = loader.loadImage("/image/hoTextBox.png");
		kyTextBox = loader.loadImage("/image/kyTextBox.png");
		saTextBox = loader.loadImage("/image/saTextBox.png");
		maTextBox = loader.loadImage("/image/mdTextBoxDefault.png");
		speaking = false;
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
			GameSystem.turnOnBgm("/sound/bgm1.wav");
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.PLAIN,22);
		g.setFont(f1);
		g.drawImage(background, 0, 0, null);
		g.drawImage(sprite,GameSystem.ABSWIDTH-sprite.getWidth(),GameSystem.ABSHEIGHT-sprite.getHeight(),null);
		g2d.setColor(Color.WHITE);
		//g2d.draw(textBox);
		//g2d.fill(textBox);
		//g.drawImage(naTextBox,-90,370,900,225,null);
		if(speaking){
			g.drawImage(textBox,-50,300,700,175,null);
		}
		g.setColor(Color.BLACK);
		renderLines(g);
	}
	private void renderLines(Graphics g) {
		try{
			for(int i=0;i<lineNum;i++){
				g.drawString(lines[i],120, 378+i*20);
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
		while(isSpecialString(lines[lineNum])){
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
	private boolean isSpecialString(String line){
		if(isEndOfSection(line)){
			try {
				br = new BufferedReader(new FileReader(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//changeToGameState();
			//also should load some file or else get nullpointer
			return true;
		}
		else if(isCharacterName(line)){
			return true;
		}
		else if(isStopSpeaking(lines[lineNum])){
			return true;
		}
		return false;
	}
	private void changeToGameState() {
		GameSystem.turnOffBgm();
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
			speaking=false;
			return true;
		}
		return false;
	}

	private boolean isCharacterName(String s) {
		if(s.equals("MADOKA:")){
			textBox=mdTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("HOMURA:")){
			textBox=hoTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("SAYAKA:")){
			textBox=saTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("KYOUKO:")){
			textBox=kyTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("MAMI:")){
			textBox=maTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("QB:")){
			textBox=qbTextBox;
			speaking = true;
			return true;
		}
		return false;
	}
}
