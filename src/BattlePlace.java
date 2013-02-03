/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class BattlePlace extends JPanel implements KeyListener,MouseListener{
	private BattleShip[] objectOn;
	private int wid,hei;
	public int bulletNo=0,bulletBase=0;
	private ImageIcon backPic;
	private PlayerMove player;
	private AIMove AI;
	private AIFire aiFire;
	private Timer timer;
	private BattlePlaceRefresh fresh;
	private boolean stop = true;
	public boolean shoot = false;
	public JTextArea[] disHp = new JTextArea[2];
	private JTextArea time;
	
	public void paint(Graphics g) {
		if(objectOn[0].getHP()<=0||objectOn[1].getHP()<=0){
			stop = false;
			this.setVisible(false);
			//fthis.setFocusable(false);
		}
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(backPic.getImage(), 0, 0, wid, hei, this); 
		super.paint(g);		 
	}
	public BattlePlace(int width, int height, ImageIcon backPicture) {
		super();
		ImageIcon[] icon = new ImageIcon[2];
		icon[0] = new ImageIcon("./plane1.png");
		icon[1] = new ImageIcon("./enemy1.png");
		
		wid = width;
		hei = height;
		objectOn = new BattleShip[2];
		this.setSize(width, height);
		for(int i = 0;i<2;i++){
			objectOn[i] = new BattleShip(0,0,icon[i],100,100,this);
			this.add(objectOn[i]);
		}
		backPic = backPicture;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setBackground(Color.black);
		this.setLayout(null);
		
		this.setFocusable(true);
		this.requestFocus();
		
		this.setOpaque(false);
		
		Font font = new Font("Arial",Font.BOLD,30);
		JLabel labelPlayer = new JLabel("PLAYER LIFE\n");
		labelPlayer.setSize(300, 50);
		labelPlayer.setLocation(0, 0);
		labelPlayer.setFont(font);
		labelPlayer.setFocusable(false);
		labelPlayer.setForeground(Color.GREEN);
		this.add(labelPlayer);
		
		JLabel labelENEMY = new JLabel("PLAYER LIFE\n");
		labelENEMY.setSize(300, 50);
		labelENEMY.setLocation(1280, 0);
		labelENEMY.setFont(font);
		labelENEMY.setFocusable(false);
		labelENEMY.setForeground(Color.orange);
		this.add(labelENEMY);
		
		
		disHp[0] = new JTextArea();
		disHp[0].setSize(300, 100);
		disHp[0].setLocation(0, 50);
		disHp[0].setText(objectOn[0].returnHP());
		disHp[0].setOpaque(false);
		disHp[0].setFont(font);
		disHp[0].setFocusable(false);
		disHp[0].setEditable(false);
		disHp[0].setForeground(Color.GREEN);
		this.add(disHp[0]);
		
		disHp[1] = new JTextArea();
		disHp[1].setSize(300, 100);
		disHp[1].setLocation(1280, 50);
		disHp[1].setText(objectOn[1].returnHP());
		disHp[1].setOpaque(false);
		disHp[1].setFont(font);
		disHp[1].setFocusable(false);
		disHp[1].setEditable(false);
		disHp[1].setForeground(Color.ORANGE);
		this.add(disHp[1]);
		
		time = new JTextArea();
		time.setEditable(false);
		time.setFocusable(false);
		time.setBounds(550, 0, 400, 100);
		time.setForeground(Color.RED);
		time.setFont(font);
		time.setOpaque(false);
		this.add(time);
		
		
		player = new PlayerMove(objectOn,this);
		player.start();
		
		fresh = new BattlePlaceRefresh(this,objectOn[1]);
		fresh.start();
		
		AI = new AIMove(objectOn,this);
		AI.start();
		
		aiFire = new AIFire(objectOn,this);
		aiFire.start();
		
		timer = new Timer(time,this);
		timer.start();
		
	}
	
	public boolean Stop(){
		return stop;
	}
	public int returnHp(){
		return objectOn[0].getHP();
	}
	
	public String returnScore(){
		return "Your Score: "+objectOn[0].getHP()*3+" + Bonus: "+timer.returnCount()+"\n";
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char a = e.getKeyChar();
		if(a == 'j'){
			shoot = !shoot;
			a = e.getKeyChar();
		}
		switch(a) {
		case 'w':player.modifyUp();break;
		case 's':player.modifyDown();break;
		case 'a':player.modifyLeft();break;
		case 'd':player.modifyRight();break;
		case 'W':player.modifyUp();break;
		case 'S':player.modifyDown();break;
		case 'A':player.modifyLeft();break;
		case 'D':player.modifyRight();break;
		default: break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		player.modifyBlank();
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		new PlayerBulletDis(objectOn[0].returnX(),objectOn[0].returnY(),objectOn[0].returnDegree(),300,this,1,objectOn[1]).start();
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
