package gameObject;

public class Ai
{
	//for make step, written by Liu Ge
	boolean[][] map;
	boolean[][] checkPoint=new boolean[500][500];
	int[][] pathx=new int[500][500];
	int[][] pathy=new int[500][500];
	String[][] direct=new String[500][500];
	boolean[][] possible=new boolean[500][500];
	int playerx;
	int playery;
	int aix;
	int aiy;
	int boundx;
	int boundy;
	String d;
	
	//for other AI stuff, written by Xu Zhi Shu
	
	public Ai(){
		
	}
	
	public String isValidStraightLine(boolean[][] m,int pX,int pY,int aiX,int aiY){
		if(pX==aiX&&pY==aiY) return "stop";
		if(pX==aiX||pY==aiY){
			if(pX==aiX){
				if(aiY<pY){
					for(int i=aiY;i<pY;i++){
						if(m[pX][i]) return "stop";
					}
					return "down";
				}
				else if(aiY>pY){
					for(int i=aiY;i>pY;i--){
						if(m[pX][i]) return "stop";
					}
					return "up";
				}
			}
			else if(pY==aiY){
				if(aiX<pX){
					for(int i=aiX;i<pX;i++){
						if(m[i][pY]) return "stop";
					}
					return "right";
				}
				else if(aiX>pX){
					for(int i=aiX;i>pX;i--){
						if(m[i][pY]) return "stop";
					}
					return "left";
				}
			}
		}
		return "stop";
		
		
	}
	public String makeStep(boolean[][] m,int playerx,int playery,int aix,int aiy)
	{
		this.playerx=playerx;
		this.playery=playery;
		this.aix=aix;
		this.aiy=aiy;
		map=m;
		boundx=m.length;
		boundy=m[0].length;
		for (int i=0;i<500;i++)
		{
			for (int j=0;j<500;j++)
			{
				checkPoint[i][j]=true;
				possible[i][j]=true;
			}
		}
		direct[0][0]="stop";
		pathx[0][0]=aix;
		pathy[0][0]=aiy;
		checkPoint[aix][aiy]=false;
		search(1,1);
		return(d);
	}
	private void search(int a, int n)
	{
		int k=0;
		int x;
		int y;
		for (int count=0;count<n;count++)
		{
			x=pathx[a-1][count];
			y=pathy[a-1][count];
			if (x==playerx&&y==playery)
			{
				d=direct[a-1][count];
				return;
			}
			if (possible[a-1][count])
			{
				for (int i=-1;i<=1;i++)
				{
					for (int j=-1;j<=1;j++)
					{
						if (i!=j&&i*j==0)
						{
							if (a==1)
							{
								if (i==-1) direct[a][k]="left";
								if (i==1) direct[a][k]="right";
								if (j==-1) direct[a][k]="up";
								if (j==1) direct[a][k]="down";
							} else
							{
								direct[a][k]=direct[a-1][count];
							}
							pathx[a][k]=x+i;
							pathy[a][k]=y+j;
							if (x+i>0&&x+i<boundx&&y+j>0&&y+j<boundy)
							{
								if (checkPoint[x+i][y+j]&&!map[x+i][y+j])
								{
									checkPoint[x+i][y+j]=false;
									possible[a][k]=true;
								} else
									possible[a][k]=false;
							} else
							{
								possible[a][k]=false;
							}
							k++;
						}
					}
				}
			}
		}
		search(a+1,k);
	}
}