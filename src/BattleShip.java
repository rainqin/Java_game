/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BattleShip extends JPanel{
	private int x,y,drawWid,drawHei;
	private ImageIcon model;
	private double degree;
	private int HP=100;
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(this.returnDegree(),50,50);
		g2d.drawImage(this.returnImage().getImage(), 0,0,this.returndrawW(),this.returndrawH(),this);
	}	
	public BattleShip(int x,int y,ImageIcon z,int w,int h,BattlePlace war) {
		super();
		this.x = x;
		this.y = y;
		this.model = z;
		drawWid = w;
		drawHei = h;
		this.setLocation(x, y);
		this.setSize(300, 300);
		this.setOpaque(false);
		this.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void Move() {
		this.move(x, y);
	}
	
	public float Distante(BattleShip p) {
		return (float) Math.sqrt((this.x-p.returnX())*(this.x-p.returnX())+(this.y-p.returnY())*(this.y-p.returnY()));
	}
	
	public void calHP(int x){
		HP -= x;
	}
	
	public void getDegree(double d){
		degree = d;
	}
	public void getX(int x) {
		this.x = x+50;
	}
	public void getY(int y) {
		this.y = y+50;
	}
	public int returnX() {
		return this.x;
	}
	public int returnY() {
		return this.y;
	}
	public ImageIcon returnImage() {
		return model;
	}
	public int returndrawW() {
		return this.drawWid;
	}
	public int returndrawH() {
		return this.drawHei;
	}
	public double returnDegree(){
		return degree;
	}
	public String returnHP(){
		return "HP: "+HP+"\n";
	}
	public int getHP(){
		return HP;
	}
}
