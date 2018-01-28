import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;


public class Bricks {

	int map[][];
	 int brickswidth;
	 int bricksheight;
	Bricks(int row,int col){
		map=new int[row][col];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1;
			}
		}
		brickswidth=520/col;
		bricksheight=100/row;
	}
	
	public  void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{  if(map[i][j]==1)
			{
				g.setColor(Color.WHITE);
				g.fillRect(j*(brickswidth+10)+80, i*(bricksheight+30)+50, brickswidth, bricksheight);
				
			}
			}
	
	
}
}
	
}