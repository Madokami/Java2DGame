package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URLDecoder;

public class LevelLoader {
	Game game;
	BufferedImageLoader loader;
	String title;
	private Image gif;
	private String path;
	private long renderStageStart;
	private int duration;
	
	public LevelLoader(Game game){
		this.game = game;
		loader = new BufferedImageLoader();
		path = getClass().getResource("/witch1.gif").getFile();
		path = URLDecoder.decode(path);
		gif = Toolkit.getDefaultToolkit().createImage(path);
	}
	public synchronized void load(Game game){
		int stage = game.curLevel;
		if(stage==1){
			stage1();
		}
		else if(stage==2){
			stage2();
		}
	}
	public void stage1(){
		reset();
		game.background=loader.loadImage("/background.png");
		game.c.addEntity(new Homura(7,15,game));
		game.p = (Player) game.c.getFList().getLast();
		for(int i=0;i<GameSystem.GRIDW;i=i+3){
			game.c.addEntity(new Enemy(i,0,game));
			game.enemyCount++;
		}
		//adding bricks
		for(int i=2;i<GameSystem.GRIDW-2;i++){
			for(int j=2;j<14;j++){
			game.e.addEntity(new Brick(i,j,game));
			}
		}
		updateList();
	}
	
	public void stage2(){
		reset();
		game.c.addEntity(new Homura(1,2,game));
		game.p = (Player) game.c.getFList().getLast();
		for(int i=4;i<GameSystem.GRIDW-4;i++){
			game.c.addEntity(new Enemy(i,3,game));
			game.enemyCount++;
			game.e.addEntity(new Brick(i,GameSystem.GRIDH-2,game));
		}
		updateList();
	}
	public void reset(){
		game.c = new Controller();
		game.e = new Explode(game);
		game.playerIsAlive=true;
		game.enemyCount=0;
	}
	public void updateList(){
		game.fi=game.c.getFList();
		game.ei=game.c.getEList();
		game.wi=game.e.getWList();
		game.powerUpList=game.c.getPList();
		game.fireList=game.e.f;
	}
	
	public void renderStart(int duration){
		this.renderStageStart = System.currentTimeMillis();
		this.duration = duration;
	}
	
	public void render(Graphics g){
		title = "Stage".concat(" ").concat(Integer.toString(game.curLevel));
		g.drawImage(gif, 100, 100, null);
		g.setFont(new Font("arial",Font.BOLD,32));
		g.setColor(Color.RED);
		g.drawString(title, 270, 240);
		if(System.currentTimeMillis()-renderStageStart>duration){
			Game.gState=Game.GameState.PLAY;
		}
	}
}
