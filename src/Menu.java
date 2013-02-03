/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener{
	private HW5 frame;
	private Dimension size;
	private MyButton newGame,exit;
	private BattlePlace[] level = new BattlePlace[10];
	private int i = 0;
	private ImageIcon menuPic = new ImageIcon("./Menu.jpg");
	private ImageIcon startPic = new ImageIcon("./buttonstart.png");
	private ImageIcon exitPic = new ImageIcon("./buttonexit.png");
	private JTextArea score,notice;
	
	
	public void paint(Graphics g) {
			//exit.setFocusable(true);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(menuPic.getImage(), 0, 0, 1500, 800, this); 
		super.paint(g);
		if(level[i]!=null){
			if(!level[i].Stop()){
				if(level[i].returnHp()==0)
					score.setText("Sorry You Failed. Try For Revenge!");
				else
					score.setText(level[i].returnScore());
				level[i].setFocusable(false);
				this.remove(level[i]);
				newGame.setVisible(true);
				exit.setVisible(true);
				score.setVisible(true);
				newGame.setFocusable(true);
				notice.setVisible(true);
				i++;
			}
		}
	}	
	
	public Menu(HW5 hw5){
		frame = hw5;
		size = frame.getSize();
		this.setLocation(0,0);
		this.setSize(size);
		this.setLayout(null);
		this.setOpaque(false);
		newGame = new MyButton("Start",startPic);
		newGame.setLocation(300, 200);
		newGame.setForeground(Color.GREEN);
		newGame.addActionListener(this);
		exit = new MyButton("Exit",exitPic);
		exit.setLocation(900, 200);
		exit.setForeground(Color.BLUE);
		exit.addActionListener(this);
		
		
		this.add(newGame);
		this.add(exit);
		this.setVisible(true);
		for(int n = 0;n<10;n++){
			level[n] = null;
		}
		Font font2 = new Font("Arial Black",Font.BOLD,40);
		score= new JTextArea();
		score.setSize(1000, 100);
		score.setLocation(400, 50);
		score.setOpaque(false);
		score.setFont(font2);
		score.setForeground(Color.RED);
		score.setEditable(false);
		score.setFocusable(false);
		score.setVisible(false);
		this.add(score);
		
		Font font3 = new Font("Arial Black",Font.BOLD,20);
		notice = new JTextArea();
		notice.setSize(270, 500);
		notice.setLocation(20, 50);
		notice.setOpaque(false);
		notice.setFont(font3);
		notice.setForeground(Color.YELLOW);
		notice.setEditable(false);
		notice.setFocusable(false);
		notice.setVisible(true);
		notice.setText("--Press W,S,A,D for\nUp,Down,Left,Right\n\n--Click mouse left\n  button for fire\n\n--Try beat enemy\nsoon and keep your HP");
		this.add(notice);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newGame){
			newGame.setFocusable(false);
			exit.setFocusable(false);
			newGame.setVisible(false);
			exit.setVisible(false);
			score.setVisible(false);
			notice.setVisible(false);
			ImageIcon icon = new ImageIcon("./03.jpg");
			level[i] = new BattlePlace(1500,800,icon);
			this.add(level[i]);
			level[i].requestDefaultFocus();
		}
		
		if(e.getSource()==exit){
			System.exit(0);
		}
	}
}
