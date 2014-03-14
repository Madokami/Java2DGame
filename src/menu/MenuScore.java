package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import system.GameSystem;

public class MenuScore {
	
	
	
	
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameSystem.ABSWIDTH+10, GameSystem.ABSHEIGHT+10);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("press 'space' go to next level", 200, 100);
		g.drawString("please make this page look nicer", 180,200);
	}

	public void keyPressed(int key) {
		if(key==KeyEvent.VK_SPACE){
			Menu.toGameMode();
		}
		
	}
	
	
}
