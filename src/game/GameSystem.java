package game;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class GameSystem extends Canvas implements Runnable{
	
	
	public static final int SIZE = 32;
	public static final int WIDTH=320;
	public static final int HEIGHT=WIDTH*9/12;
	public static final int SCALE=2;
	public static final int ABSWIDTH=WIDTH*SCALE;
	public static final int ABSHEIGHT=HEIGHT*SCALE;
	public static final int GRIDW=WIDTH*SCALE/SIZE;
	public static final int GRIDH=HEIGHT*SCALE/SIZE;
	public static final String TITLE="Sis Puella Magica";
	public JFrame frame;
	private boolean running= false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH*SCALE,HEIGHT*SCALE,BufferedImage.TYPE_INT_RGB);
	public static Music musicPlayer;
	public Menu menu;
	public Story story;
	public Game game;
	public static boolean musicOn;
	
	public static enum STATE{
		MENU,
		GAME,
		PAUSE,
		LOAD,
		STORY
	};
	
	public static STATE state = STATE.MENU;
	public Controller c; 
	public Explode e;
	public void init(){
		menu = new Menu();
		story = new Story();
		game = new Game(this);
		//m = new Menu2(this);
		this.requestFocus();
		//bgmPlayer=new Music();
		musicPlayer=new Music();
		//event listeners
		//this.addKeyListener(new Input(this));
		this.addKeyListener(new Input(this));
	}
	
	public static void main(String[] args){
		GameSystem sys = new GameSystem();
		sys.init();
		sys.start();
	}
	
	
	public GameSystem(){
		setMinimumSize(new Dimension(ABSWIDTH,ABSHEIGHT));
		setMaximumSize(new Dimension(ABSWIDTH,ABSHEIGHT));
		setPreferredSize(new Dimension(ABSWIDTH,ABSHEIGHT));
		//this.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		frame=new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new BorderLayout());
		frame.setLayout(new BorderLayout());
		//frame.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		//frame.add(this,BorderLayout.CENTER);
		frame.add(this,BorderLayout.CENTER);
		//frame.add(content,BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		musicOn=false;
	}
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 30.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta = delta + (now - lastTime)/ns;
			lastTime = now;
			if(delta>1){
				tick();
				updates++;
				delta--;
			}
			
			render();
			
			frames++;
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				//System.out.println(updates + "ticks, frame" + frames);
				//System.out.println(p.curX+" "+p.curY);
				updates=0;
				frames=0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		if(state==STATE.GAME){
			game.tick();
		}
		else if(state==STATE.MENU){
			menu.tick();
		}
		else if(state==STATE.STORY){
			story.tick();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		///////////////////////////
		g.drawImage(image,0,0,getWidth(),getHeight(),this);
		if(state==STATE.GAME||state==STATE.PAUSE){
			game.render(g);
			if(state==STATE.PAUSE){
				g.setFont(new Font("Arial",Font.BOLD,32));
				g.setColor(Color.RED);
				g.drawString("Game Paused", 220, 220);
			}
		}
		else if(state==STATE.MENU){
			menu.render(g);
		}
		else if(state==STATE.STORY){
			story.render(g);
		}
		///////////////////////////
		g.dispose();
		bs.show();
	}
	
	
	private synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_S){
			try
		      {
				//String path = getClass().getResource("/save/game.ser").toString();
				//path = URLDecoder.decode(path);
				//File newFile = new File(path);
				//String path = getClass().getResource("/save/game.se").toString();
				//path = URLDecoder.decode(path);
				//path = path.concat("r");
				GameData saveData = new GameData();
				//game.p.pData.upDatePlayerData(game.p);
				saveData.updateGameData(game);
		         FileOutputStream fileOut = new FileOutputStream("C:/Users/Attack on Majou/workspace/Java2DGame/res/save/game.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         //game.pData.upDatePlayerData(game.p);
		         out.writeObject(saveData);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /tmp/game.ser");
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
		}
		else if(key==KeyEvent.VK_L){
		      try
		      {
		    	  GameData loadData;
		         FileInputStream fileIn = new FileInputStream("C:/Users/Attack on Majou/workspace/Java2DGame/res/save/game.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		          loadData = (GameData) in.readObject();
		          loadData.loadGame(game);
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("Employee class not found");
		         c.printStackTrace();
		         return;
		      }
		}
		if(state==STATE.MENU){
			menu.keyPressed(key);
		}
		//in story state
		else if(state==STATE.STORY){
			story.keyPressed(e);
		}
		//in game state
		else if(state==STATE.GAME){
			game.keyPressed(key);
		}
		else if(state==STATE.PAUSE){
			if(key==KeyEvent.VK_P){
				state=STATE.GAME;
			}
		}
	
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(state==STATE.GAME){
			game.keyReleased(key);
		}
	}
	
	public static void turnOnBgm(){
		if(musicOn)
			return;
		musicPlayer.playMusic("/sound/sisPuellaMagica.wav");
		musicOn=true;
	}
	//this will play the .wav file idicated given the url
	public static void turnOnBgm(String url){
		if(musicOn)
			return;
		musicOn=true;
		System.out.println("turnOnBgm Called");
		musicPlayer.playMusic(url);
	}
	public static void turnOffBgm(){
		musicOn=false;
		musicPlayer.stopMusic();
	}
	public static void playSwitch(){
		musicPlayer.playVoice("/sound/switch1.wav");
	}
	public static void playConfirm(){
		musicPlayer.playVoice("/sound/choice2.wav");
	}
	public static void playCancel(){
		musicPlayer.playVoice("/sound/cancel2.wav");
	}
	
}

