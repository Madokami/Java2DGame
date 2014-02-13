package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	
	
	public static final int SIZE = 32;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=320;
	public static final int HEIGHT=WIDTH/12*9;
	public static final int SCALE=2;
	public static final int GRIDW=WIDTH*SCALE/SIZE;
	public static final int GRIDH=HEIGHT*SCALE/SIZE;
	public static final String TITLE="Homuhomu booooom";
	private JFrame frame;
	private boolean running= false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public SpriteSheet ss1;
	public SpriteSheet ss2;
	public SpriteSheet ss3;
	
	public Music bgmPlayer;
	
	public Player p;
	public boolean playerIsAlive = true;
	private Menu menu;
	
	public LinkedList<FriendlyInterface> fi;
	public LinkedList<EnemyInterface> ei;
	public LinkedList<WallInterface> wi;
	public LinkedList<Fire> fireList;
	
	private enum STATE{
		MENU,
		GAME
	};
	
	private STATE state = STATE.GAME;
	
	public Controller c; 
	public Explode e;
	private BufferedImage gameOver;
	private BufferedImage background;
	
	
	public void init(){
		menu = new Menu();
		BufferedImage spriteSheetImage=null;
		BufferedImage spriteSheetImage2=null;
		BufferedImage brickImages = null;
		this.requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		//bgmPlayer=new Music();
		bgmPlayer=new Music();
		try{
			spriteSheetImage = loader.loadImage("/PuellaSet2.png");
			spriteSheetImage2 = loader.loadImage("/bomb.png");
			brickImages = loader.loadImage("/bricks.png");
			background = loader.loadImage("/background.png");
			gameOver = loader.loadImage("/gameOver.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.addKeyListener(new Input(this));
		ss1 = new SpriteSheet(spriteSheetImage);
		ss2 = new SpriteSheet(spriteSheetImage2);
		ss3 = new SpriteSheet(brickImages);
		c= new Controller(this);
		e= new Explode(this);
		
		c.addEntity(new Player(4,5,this));
		p = (Player) c.getFList().getLast();
		fi=c.getFList();
		ei=c.getEList();
		wi=c.getWList();
		fireList = e.f;
		//adding enemies
		for(int i=0;i<GRIDW;i=i+3){
			c.addEntity(new Enemy(i,0,this));
			//c.addEntity(new Enemy(0,i,this));
		}
		//adding bricks
		for(int i=4;i<GRIDW-4;i++){
			c.addEntity(new Brick(i,3,this));
			c.addEntity(new Brick(i,GRIDH-2,this));
		}
		
	}
	
	public static void main(String[] args){
		Game g = new Game();
		g.init();
		g.start();
	}
	
	
	public Game(){
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		frame=new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this,BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
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
				System.out.println(updates + "ticks, frame" + frames);
				System.out.println(p.curX+" "+p.curY);
				updates=0;
				frames=0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		if(state==STATE.GAME){
			c.tick();
			e.tick();
		}
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		///////////////////////////
		g.drawImage(image,0,0,getWidth(),getHeight(),this);
		
		if(state==STATE.GAME){
			//g.drawImage(background, 100, 0, null);
			c.render(g);
			e.render(g);
			if(!playerIsAlive){
				g.drawImage(gameOver,16,16,null);
			}
		}
		else if(state==STATE.MENU){
			Menu.render(g);
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
	
			
			if(key==KeyEvent.VK_RIGHT){
				p.moveRight();
			}
			else if(key==KeyEvent.VK_LEFT){
				p.moveLeft();
			}
			else if(key==KeyEvent.VK_UP){
				p.moveUp();

			}
			else if(key==KeyEvent.VK_DOWN){
				p.moveDown();	
			}
		
			else if(key==KeyEvent.VK_SPACE){
				c.addEntity(new Bomb(p.xGrid,p.yGrid,this));
			}
	
	}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		if(state==STATE.GAME){
			if(key==KeyEvent.VK_RIGHT){
				p.moveStop();
			}
			else if(key==KeyEvent.VK_LEFT){
				p.moveStop();
			}
			else if(key==KeyEvent.VK_UP){
				p.moveStop();
			}
			else if(key==KeyEvent.VK_DOWN){
				p.moveStop();
			}
		}
	}
	


}
