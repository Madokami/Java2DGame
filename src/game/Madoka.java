package game;

public class Madoka extends Player{

	public Madoka(int x, int y, Game game) {
		super(x, y, game);
		ssX=7;
		ssY=1;
		ss=SpriteData.char1;
		image=ss.grabImage(ssX, ssY, size, size);
		// TODO Auto-generated constructor stub
	}

}
