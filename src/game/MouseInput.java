package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	int mx;
	int my;
	Menu m;
	GameSystem sys;
	public MouseInput(GameSystem sys){
		this.m = sys.menu;
		this.sys=sys;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mx=e.getX();
		my=e.getY();
		if(GameSystem.state==GameSystem.STATE.MENU){
		if(mx>Menu.playButton.x&&mx<Menu.playButton.x+Menu.playButton.getWidth()){
			if(my>Menu.playButton.y&&my<Menu.playButton.y+Menu.playButton.getHeight()){
				sys.game.playerIsAlive=true;
				GameSystem.state=GameSystem.STATE.STORY;
			}
		}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx=e.getX();
		my=e.getY();
		if(mx>Menu.playButton.x&&mx<Menu.playButton.x+Menu.playButton.getWidth()){
			if(my>Menu.playButton.y&&my<Menu.playButton.y+Menu.playButton.getHeight()){
				
			}
			else{
				
			}
		}
		else{
			
		}
	}

}
