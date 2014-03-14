package menu;

import java.awt.Graphics;

public interface GeneralMenu {
	public void tick();
	public void render(Graphics g);
	public void renderSelected(Graphics g);
	public void keyPressed(int key);
}
