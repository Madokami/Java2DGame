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
		this.game=game;
		loader = new BufferedImageLoader();
		//path = getClass().getResource("/witch1.gif").getFile();
		//path = URLDecoder.decode(path);
		//gif = Toolkit.getDefaultToolkit().createImage(path);
		gif=loader.loadGif("/witch1.gif");
	}
	public synchronized void load(){
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
		game.background=loader.loadImage("/image/stage/wall1.png");
		//created the player depending on the player chosen state
		createPlayer(7,11);
		for(int i=0;i<GameSystem.GRIDW;i=i+3){
			game.c.addEntity(new MrPringles(i,0,game));
			game.enemyCount++;
		}
		//adding bricks
		for(int i=2;i<GameSystem.GRIDW-2;i++){
			for(int j=2;j<8;j++){
			game.c.addEntity(new Brick(i,j,game));
			}
		}
		updateList();
	}
	
	
	public void stage2(){
		reset();
		game.background=loader.loadImage("/image/stage/wall1.png");
		createPlayer(1,2);
		for(int i=4;i<GameSystem.GRIDW-4;i++){
			game.c.addEntity(new AdelbertMini(i,3,game));
			game.enemyCount++;
			game.c.addEntity(new Brick(i,GameSystem.GRIDH-2,game));
		}
		updateList();
	}
	public void reset(){
		game.victory=false;
		game.c = new Controller(game);
		game.playerIsAlive=true;
		game.enemyCount=0;
	}
	public void updateList(){
		game.bList=game.c.getBList();
		game.eList=game.c.getEList();
		game.brickList=game.c.getBrickList();
		game.powerUpList=game.c.getPList();
		game.fireList=game.c.fireList;
		game.projectileList=game.c.getProjectileList();
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
	private void createPlayer(int i, int j) {
		if(game.cChosen==Game.CHARACTER.MADOKA){
			game.c.createPlayer(new Madoka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.HOMURA){
			game.c.createPlayer(new Homura(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.SAYAKA){
			game.c.createPlayer(new Sayaka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.MAMI){
			game.c.createPlayer(new Mami(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.KYOUKO){
			game.c.createPlayer(new Kyouko(i,j,game));
		}
		game.p = game.c.getPlayer();
	}
}
