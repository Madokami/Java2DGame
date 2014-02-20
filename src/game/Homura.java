package game;

public class Homura extends Player{
	public Homura(int x, int y, Game game) {
		super(x, y, game);
		bombStrength = 3;
		bombLength = 3;
		ssX=1;
		ssY=1;
		ss=SpriteData.char2;
		image=ss.grabImage(ssX, ssY, size, size);
		ultCount=200;
		// TODO Auto-generated constructor stub
	}
	public void useUltimate(){
		playUltimateSound();
		game.event1.startEvent(2000, "timeStopCutIn");
		game.event2.startEvent(5000, "timeStop");
		game.musicPlayer.playSwoosh();
		ultCount--;
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		musicPlayer.playMusic(url);
	}
	public void playUltimateSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoUlt";
		url=url.concat(Integer.toString(x)).concat(".wav");
		System.out.println(url);
		musicPlayer.playMusic(url);
	}
	
}