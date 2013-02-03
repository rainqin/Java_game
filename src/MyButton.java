/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class MyButton extends JButton{
	private ImageIcon image;
	public void paint(Graphics g){
		
		g.drawImage(image.getImage(), 0,0,400,400,this);
		super.paint(g);
	}
	public MyButton(String s, ImageIcon ima){
		super(s);
		Font font = new Font("Arial Black",Font.BOLD,100);
		this.setSize(400, 400);
		this.setFont(font);
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		image = ima;
	}
}
