
import java.awt.Color;
import java.awt.Frame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class ScreenWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	int xpos;
	
	
	BufferedImage[] greenSpider;
	BufferedImage[] blueSpider;
	BufferedImage[] redSpider;
	BufferedImage[] redyellowSpider;
	BufferedImage[] silverredSpider;
	BufferedImage[] silverSpider;
	BufferedImage[] bluesilverSpider;
	BufferedImage[] blueorangeSpider;
	BufferedImage[] silveryellowSpider;
	BufferedImage[] greenorangeSpider;
	BufferedImage[] greenyellowSpider;
	
	ArrayList<BufferedImage[]> spiders;
	int[] currentAnimationSequence;
	int[] walkingRight = {34,35,36,37,38,39};
	int[] walkingLeft = {14,15,16,17,18,19};
	int poseControl;
	int spiderSelector;
	int walkSpeed;
	
	public ScreenWindow(){
		super();
		
		
		
		greenSpider = SpriteSheet.getAsArray("spider01.png", 5, 10, 64, 64);
		blueSpider = SpriteSheet.getAsArray("spider02.png", 5, 10, 64, 64);
		redSpider = SpriteSheet.getAsArray("spider03.png", 5, 10, 64, 64);
		redyellowSpider = SpriteSheet.getAsArray("spider04.png", 5, 10, 64, 64);
		silverredSpider = SpriteSheet.getAsArray("spider05.png", 5, 10, 64, 64);
		silverSpider = SpriteSheet.getAsArray("spider06.png", 5, 10, 64, 64);
		bluesilverSpider = SpriteSheet.getAsArray("spider07.png", 5, 10, 64, 64);
		blueorangeSpider = SpriteSheet.getAsArray("spider08.png", 5, 10, 64, 64);
		silveryellowSpider = SpriteSheet.getAsArray("spider09.png", 5, 10, 64, 64);
		greenorangeSpider = SpriteSheet.getAsArray("spider10.png", 5, 10, 64, 64);
		greenyellowSpider = SpriteSheet.getAsArray("spider11.png", 5, 10, 64, 64);
		
		
		poseControl = 0;
		spiderSelector = 0;
		
		walkSpeed = 5;
		
		xpos = 0;
		imgBuffer = this.createImage(800, 150);
	
		currentAnimationSequence = walkingRight;
		
		spiders = new ArrayList<BufferedImage[]>();
		spiders.add(greenSpider);
		spiders.add(blueSpider);
		spiders.add(redSpider);
		spiders.add(redyellowSpider);
		spiders.add(silverredSpider);
		spiders.add(silverSpider);
		spiders.add(bluesilverSpider);
		spiders.add(blueorangeSpider);
		spiders.add(silveryellowSpider);
		spiders.add(greenorangeSpider);
		spiders.add(greenyellowSpider);
		
		//more window stuff
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setSize(800,150);
		this.setTitle("FIDGET SPIDER");
		isRunning = true;
		isDone = false;
		this.setVisible(true);
		this.setResizable(false);
		this.setIconImage(redSpider[21]);
		
	
		
	}
	
	public void run(){
		while(isRunning){
			draw();
			poseControl++;
			if(poseControl >= currentAnimationSequence.length){
				poseControl = 0;
			}
			
			xpos += walkSpeed;
			if(xpos > 800){
				xpos = 0;
			}
			if(xpos < 0){
				xpos = 800;
			}
			
			
			try{
				Thread.sleep(100);
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
		}
		isDone = true;
	}
	
	
	public void draw(){
		imgBuffer = this.createImage(this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D)imgBuffer.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//background color
		//g2.setColor(new Color(153,204,255));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		g2.drawImage(spiders.get(spiderSelector)[currentAnimationSequence[poseControl]], xpos, 50, null);
		
	
		
	
			
		
		g2 = (Graphics2D)this.getGraphics();
		g2.drawImage(imgBuffer, 0, 0, this.getWidth(), this.getHeight(), 0, 0, this.getWidth(), this.getHeight(), null);
		g2.dispose();
	}
	
	
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		while(true){
			if(isDone){
				System.exit(0);
			}try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		isRunning = false;
		this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//right arrow
		if(e.getKeyCode() == 39){
			walkSpeed = 5;
			currentAnimationSequence = walkingRight;
		}
		//left arrow
		if(e.getKeyCode() == 37){
			walkSpeed = -5;
			currentAnimationSequence = walkingLeft;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
	public void mousePressed(MouseEvent arg0) {
		spiderSelector++;
		if(spiderSelector >= spiders.size()){
			spiderSelector = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
