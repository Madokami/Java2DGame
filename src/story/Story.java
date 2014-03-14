package story;




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

import system.BufferedImageLoader;
import system.GameSystem;
import system.GameSystem.STATE;
import menu.Menu;
import menu.Menu.MENUSTATE;

public class Story {
	BufferedImageLoader loader;
	BufferedImage spriteLeft,spriteMiddle,spriteRight;
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
		spriteRight = loader.loadImage("/image/talk/md_reg_happy.png");
		background = loader.loadImage("/image/tempBg.png");
		mdTextBox = loader.loadImage("/image/story/mdTextBox.png");
		textBox = loader.loadImage("/image/story/naTextBox.png");
		naTextBox = loader.loadImage("/image/story/naTextBox.png");
		hoTextBox = loader.loadImage("/image/story/hoTextBox.png");
		kyTextBox = loader.loadImage("/image/story/kyTextBox.png");
		saTextBox = loader.loadImage("/image/story/saTextBox.png");
		maTextBox = loader.loadImage("/image/story/maTextBox.png");
		spriteLeft=null;
		spriteMiddle=null;
		speaking = false;
			//path=getClass().getResource("/script/script.txt").getFile();
			//path = URLDecoder.decode(path);
			path = "system/script/script.txt";
			try {
				br = new BufferedReader(new FileReader(path));
				//line1 = br.readLine();
				lineNum=0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		readNextLine();
	}
	public void tick() {
			
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.PLAIN,22);
		g.setFont(f1);
		g.setColor(Color.WHITE);
		//g.fillRect(0, 340, GameSystem.ABSWIDTH+10, 200);
		g.drawImage(background, 0, 0, null);
		g.drawImage(spriteRight,450,87,null);
		g.drawImage(spriteLeft,50,87,null);
		g2d.setColor(Color.WHITE);
		//g2d.draw(textBox);
		//g2d.fill(textBox);
		//g.drawImage(naTextBox,-90,370,900,225,null);
		if(speaking){
			g.drawImage(textBox,-50,330,null);
		}
		g.setColor(Color.BLACK);
		renderLines(g);
	}
	private void renderLines(Graphics g) {
		try{
			for(int i=0;i<lineNum;i++){
				g.drawString(lines[i],120, 408+i*20);
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
		else if(key==KeyEvent.VK_X){
			toMenu();
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
		else if(isSetSprite(line)){
			return true;
		}
		else if(isPlayAudio(line)){
			return true;
		}
		else if(isChangeBackground(line)){
			return true;
		}
		else if(line.equals("")){
			return true;
		}
		else if(line.startsWith("//")){
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
		else if(s.equals("NARRATOR:")){
			textBox=naTextBox;
			speaking = true;
			return true;
		}
		return false;
	}
	public boolean isSetSprite(String line){
		if(line.equals("SP_R:NULL")){
			spriteRight=null;
			return true;
		}
		else if(line.equals("SP_M:NULL")){
			spriteMiddle=null;
			return true;
		}
		else if(line.equals("SP_L:NULL")){
			spriteLeft=null;
			return true;
		}
		else if(line.startsWith("SP_L:")){
			spriteLeft=loader.loadImage(line.substring(5));
			return true;
		}
		else if(line.startsWith("SP_M:")){
			spriteMiddle=loader.loadImage(line.substring(5));
			return true;
		}
		else if(line.startsWith("SP_R:")){
			spriteRight=loader.loadImage(line.substring(5));
			return true;
		}
		return false;
	}
	
	public boolean isPlayAudio(String line){
		if(line.equals("AUDIO_BGM:NULL")){
			GameSystem.turnOffBgm();
		}
		else if(line.startsWith("AUDIO_BGM:")){
			GameSystem.turnOnBgm(line.substring(10));
			return true;
		}
		else if(line.startsWith("AUDIO_VOICE:")){
			GameSystem.playVoice(line.substring(12));
			return true;
		}
		else if(line.startsWith("AUDIO_SOUND:")){
			GameSystem.playSound(line.substring(12));
			return true;
		}
		return false;
	}
	public boolean isChangeBackground(String line){
		if(line.startsWith("BG:")){
			this.background=loader.loadImage(line.substring(3));
			return true;
		}
		return false;
	}
	public void toMenu(){
		GameSystem.state=STATE.MENU;
		Menu.mState=MENUSTATE.MAIN;
		
	}
}
