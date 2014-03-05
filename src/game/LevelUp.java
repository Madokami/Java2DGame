package game;

public class LevelUp {
	public int level;
	public int expCurrent;
	public int expRequired;
	
	public void checkIfLevelUp(Player p){
		expCurrent = p.expCurrent;
		level = p.level;
		expRequired = level*100;
		
		if(expCurrent>=expRequired){
			p.level++;
			p.expCurrent=p.expCurrent-expRequired;
			p.BP=p.BP+10;
			p.hp=p.maxHp;
		}
	}
	
	
}
