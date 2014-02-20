package game;


import game.Game.GameState;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameSystem extends Canvas implements Runnable{
	
	
	
	public static final int SIZE = 32;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=320;
	public static final int HEIGHT=WIDTH*9/12;
	public static final int SCALE=2;
	public static final int ABSWIDTH=WIDTH*SCALE;
	public static final int ABSHEIGHT=HEIGHT*SCALE;
	public static final int GRIDW=WIDTH*SCALE/SIZE;
	public static final int GRIDH=HEIGHT*SCALE/SIZE;
	public static final String TITLE="Homuhomu booooom";
	public JFrame frame;
	private boolean running= false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH*SCALE,HEIGHT*SCALE,BufferedImage.TYPE_INT_RGB);
	public Music bgmPlayer;
	public Menu menu;
	public Story story;
	public Game game;
	public boolean musicOn;
	
	public static enum STATE{
		MENU,
		GAME,
		PAUSE,
		LOAD,
		STORY
	};
	
	public static STATE state = STATE.GAME;
	public Controller c; 
	public Explode e;
	public void init(){
		menu = new Menu();
		story = new Story();
		game = new Game(this);
		//m = new Menu2(this);
		this.requestFocus();
		//bgmPlayer=new Music();
		bgmPlayer=new Music();
		//event listeners
		//this.addKeyListener(new Input(this));
		this.addMouseListener(new MouseInput(this));
		this.addMouseMotionListener(new MouseInput(this));
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
		if(musicOn){
			if(state!=STATE.MENU&&state!=STATE.STORY){
				bgmPlayer.stop();
				musicOn=false;
			}
		}
		else if(state==STATE.MENU||state==STATE.STORY){
			if(!musicOn){
				bgmPlayer.playBgm();
				musicOn=true;
			}
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
		if(state==STATE.MENU){
			if(key==KeyEvent.VK_X){
				Menu.mState=Menu.MENUSTATE.MAIN;
			}
			else if(key==KeyEvent.VK_DOWN){
				if(Menu.selected==Menu.SELECTED.STORY){
					Menu.selected=Menu.SELECTED.ARCADE;
				}
			}
			else if(key==KeyEvent.VK_UP){
				if(Menu.selected==Menu.SELECTED.ARCADE){
					Menu.selected=Menu.SELECTED.STORY;
				}
			}
			else if(key==KeyEvent.VK_Z){
				if(Menu.selected==Menu.SELECTED.STORY){
					state=STATE.STORY;
				}
				else if(Menu.selected==Menu.SELECTED.ARCADE){
					Menu.mState=Menu.MENUSTATE.CHOOSECHAR;
				}
			}
		}
		else if(state==STATE.STORY){
			if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_Z){
				if(story.lineNum>4){
					story.lineNum=1;
				}
				int x = story.lineNum;
				try{
				if(x==1){
					story.line1=story.br.readLine();
				}
				else if(x==2){
					story.line2=story.br.readLine();
				}
				else if(x==3){
					story.line3=story.br.readLine();
				}
				else if(x==4){
					story.line4=story.br.readLine();
				}
				}catch(Exception abc){
					System.out.println("can't read from line");
				}
				story.lineNum++;
				
			}
		}
		//in game state
		else if(state==STATE.GAME){
			if(key==KeyEvent.VK_P){
				state=STATE.PAUSE;
			}
			if(game.gState==GameState.PLAY){
				if(key==KeyEvent.VK_T){
					if(game.p.hasUltimate()){
						game.p.useUltimate();
					}
				}
				else if(key==KeyEvent.VK_SPACE){
					game.c.addEntity(new Bomb(game.p.xGridNearest,game.p.yGridNearest,game));
					
				}
			}
			if(!game.p.finishingMove){
				if(key==KeyEvent.VK_RIGHT){
					game.p.moveRight();
				}
				else if(key==KeyEvent.VK_LEFT){
					game.p.moveLeft();
				}
				else if(key==KeyEvent.VK_UP){
					game.p.moveUp();

				}
				else if(key==KeyEvent.VK_DOWN){
					game.p.moveDown();	
				}
				
			}
			
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
			if(!game.p.finishingMove){
				if(key==KeyEvent.VK_RIGHT){				
					game.p.moveStop();
					//game.p.movable=false;
					game.p.moveToNext(game.p.nextX,game.p.nextY);
				}
				else if(key==KeyEvent.VK_LEFT){
					game.p.moveStop();
					//game.p.movable=false;
					game.p.moveToNext(game.p.nextX,game.p.nextY);
				}
				else if(key==KeyEvent.VK_UP){
					game.p.moveStop();
					//game.p.movable=false;
					game.p.moveToNext(game.p.nextX,game.p.nextY);
				}
				else if(key==KeyEvent.VK_DOWN){
					game.p.moveStop();
					//game.p.movable=false;
					game.p.moveToNext(game.p.nextX,game.p.nextY);
				}
			}
		}
	}
}

