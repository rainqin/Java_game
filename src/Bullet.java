/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Bullet extends JPanel{
	private int xLoc, yLoc, wid, hei;
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.fillOval(0, 0, 10, 10);
		 //g2d.drawImage(icon2.getImage(), 0,0,wid,hei,this);
		  
	}	
	public Bullet(int x, int y, int sizeX, int sizeY, Color c){
		xLoc = x;
		yLoc = y;
		wid = sizeX;
		hei = sizeY;
		this.setSize(wid, hei);
		this.setLocation(xLoc, yLoc);
		this.setOpaque(false);
		this.setForeground(c);
	}
}
