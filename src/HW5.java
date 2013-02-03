/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class HW5 extends JFrame{
	private static int frameWid = 1500, frameHei = 800;
	private JFrame frame;
	private static Menu menu;
	public HW5() {
		super();
		frame = new JFrame("Star War Craft");
		frame.setSize(frameWid, frameHei);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu = new Menu(this);
		frame.setContentPane(menu);
		frame.setVisible(true);
	}
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try{
			URL cb; 
			File f = new File("./MenuMusic.wav"); 
			cb = f.toURL(); 
			AudioClip aau; 
			aau = Applet.newAudioClip(cb); 
			
			//aau.play();
			aau.loop();
			new HW5();
			} catch (MalformedURLException e) { 
				e.printStackTrace(); 
			}
		}
}
