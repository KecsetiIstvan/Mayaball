//Kecseti István 
//kiim1727
//524/2
package jatekok;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.RootPaneContainer;


public class mayaball extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	static public Boolean jobb=false;
	static public Boolean bal=true;
	
	static Image kep;
	
	static Panel player=new Panel();
	static Panel gameter=new Panel();
	static Panel labda=new Panel();
	static Panel[][] blokkok=new Panel[10][10];
	static int magassag=700;
	
	static int blokkH=30;
	static int blokkW;
	
	static int playerW=150;
	static int playerH=30;
	
	static int labdaW=20;
	static int labdaH=20;
	
	static int pontok=0;
	static Label pontTar=new Label("Pontok:  "+pontok);
	
	public mayaball() throws IOException {
		this.setTitle("Snakee");
		this.setLayout(new BorderLayout());
		gameter.setLayout(null);
		pontTar.setBounds(0,745,100,20);
		labda.setSize(new Dimension(labdaW,labdaH));
		player.setBounds(650,magassag,playerW,playerH);
		pontTar.setFont(new Font("Serif", Font.BOLD, 16));
		this.add(gameter,BorderLayout.CENTER);
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++)
			   blokkok[i][j] = new Panel();
		}
		gameter.add(labda);
		gameter.add(pontTar);
		gameter.add(player);
		this.addKeyListener(this);
		//setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File ("C:\\Users\\Kecseti\\eclipse-workspace\\lab5\\src\\lab5\\hatter.bmp") ) ) ));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stu
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==37) {
			bal=false;
			jobb=true;
		} //jobb
		if(e.getKeyCode()==39) {
			jobb=false;
			bal=true;
		} //balra
		if(e.getKeyCode()==27) {
			System.exit(0);
		}//esc
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		mayaball k=new mayaball();
		k.setExtendedState(Frame.MAXIMIZED_BOTH);
		k.setUndecorated(true);
		k.setVisible(true);
		
		Random rand = new Random();
		gameter.setBackground(new Color(255,255,255));
		pontTar.setBackground(new Color(255,255,255));
		
		
		int speed=25;
		
		Boolean halal=false;
		
		player.setBackground(new Color(0,0,0));
		int px=650;
		
		labda.setBackground(new Color(0,0,0));
		int lx=rand.nextInt(1300)+labdaW;
		int ly=300;
		int lxv=-rand.nextInt(8)+1;
		int lyv=+rand.nextInt(8)+1;
		while(lxv==lyv) {
			lyv=+rand.nextInt(8)+1;
		}
		
		boolean elsoIt=true;
		
		int blokkcount=10;
		int m;
		int e=1;
		for(int i=1;i<blokkcount;i++) {
			e=0;
			for(int j=1;j<blokkcount;j++){
				m=rand.nextInt(150)+30;
				blokkok[i][j].setSize(m,blokkH);
				blokkok[i][j].setBackground(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
				blokkok[i][j].setLocation(e,i*31 );
				e=e+m+rand.nextInt(80);
			gameter.add(blokkok[i][j]);
			}
		}
		while(!halal) {
			
			if(bal)px+=15;
			if(jobb)px-=15;
			
			if(px<=0) px=0;
			if(px+playerW>=gameter.getWidth())px=gameter.getWidth()-playerW;
			
			player.setLocation(px, 700);
			lx+=lxv;
			ly+=lyv;
			if(lx<=0) lxv*=-1;
			if(ly<=0) lyv*=-1;
			if(lx>=gameter.getWidth()-labdaW) lxv*=-1;
			if(ly>=magassag-labdaH && lx>=px && lx-labdaW<=px+playerW) {
				lxv=-rand.nextInt(8)+1;
				lyv=+rand.nextInt(8)+1;
				while(lxv==lyv) {
					lyv=+rand.nextInt(8)+1;
				}
				lyv*=-1;
			}
			for(int i=0;i<blokkcount;i++)
				for(int j=0;j<blokkcount;j++){
					if(ly>=blokkok[i][j].getLocation().getY() - 10 &&
					   ly+labdaW<=blokkok[i][j].getLocation().getY()+blokkok[i][j].getHeight() + 10 &&
					   lx>=blokkok[i][j].getLocation().getX() -10 &&
					   lx+labdaW<=blokkok[i][j].getLocation().getX()+blokkok[i][j].getWidth()+10) {
							lyv*=-1;
					   		blokkok[i][j].setSize(0,0);
					   		pontok++;
					   		pontTar.setText("Pontok:  "+pontok);
					}
			}
			
			
			if(ly>=gameter.getHeight()-labdaH && !elsoIt)halal=true;
			
			labda.setLocation(lx,ly);
			
			TimeUnit.MILLISECONDS.sleep((int)speed);
			elsoIt=false;
		}
		Component frame = null;
		JOptionPane.showMessageDialog(frame,   "  Meghaltál :( \n  Dekár érted :( \n  Azért jó játék volt!  Pontszámod: "+pontok, "Game Over :(", JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}
