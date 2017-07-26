
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
	
	BufferedImage greenSpriteSheet;
	BufferedImage blueSpriteSheet;
	BufferedImage redSpriteSheet;
	BufferedImage redyellowSpriteSheet;
	BufferedImage silverredSpriteSheet;
	BufferedImage silverSpriteSheet;
	BufferedImage bluesilverSpriteSheet;
	BufferedImage blueorangeSpriteSheet;
	BufferedImage silveryellowSpriteSheet;
	BufferedImage greenorangeSpriteSheet;
	BufferedImage greenyellowSpriteSheet;
	
	BufferedImage[] greenSpider = new BufferedImage[50];
	BufferedImage[] blueSpider = new BufferedImage[50];
	BufferedImage[] redSpider = new BufferedImage[50];
	BufferedImage[] redyellowSpider = new BufferedImage[50];
	BufferedImage[] silverredSpider = new BufferedImage[50];
	BufferedImage[] silverSpider = new BufferedImage[50];
	BufferedImage[] bluesilverSpider = new BufferedImage[50];
	BufferedImage[] blueorangeSpider = new BufferedImage[50];
	BufferedImage[] silveryellowSpider = new BufferedImage[50];
	BufferedImage[] greenorangeSpider = new BufferedImage[50];
	BufferedImage[] greenyellowSpider = new BufferedImage[50];
	
	ArrayList<BufferedImage[]> spiders;
	int[] currentAnimationSequence;
	int[] walkingRight = {34,35,36,37,38,39};
	int[] walkingLeft = {14,15,16,17,18,19};
	int poseControl;
	int spiderSelector;
	int walkSpeed;
	
	public ScreenWindow(){
		super();
	
		loadGraphics();
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

	private void loadGraphics(){
		
		
		try {
			greenSpriteSheet = ImageIO.read(new File("spider01.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			greenSpriteSheet = null;
			e.printStackTrace();
		}
		int rows = 5;
		int col = 10;
		int counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				greenSpider[counter] = new BufferedImage(64,64,greenSpriteSheet.getType());
				Graphics2D gr = greenSpider[counter].createGraphics();
				gr.drawImage(greenSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			blueSpriteSheet = ImageIO.read(new File("spider02.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			blueSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				blueSpider[counter] = new BufferedImage(64,64,blueSpriteSheet.getType());
				Graphics2D gr = blueSpider[counter].createGraphics();
				gr.drawImage(blueSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			redSpriteSheet = ImageIO.read(new File("spider03.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			redSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				redSpider[counter] = new BufferedImage(64,64,redSpriteSheet.getType());
				Graphics2D gr = redSpider[counter].createGraphics();
				gr.drawImage(redSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			redyellowSpriteSheet = ImageIO.read(new File("spider04.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			redyellowSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				redyellowSpider[counter] = new BufferedImage(64,64,redyellowSpriteSheet.getType());
				Graphics2D gr = redyellowSpider[counter].createGraphics();
				gr.drawImage(redyellowSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			silverredSpriteSheet = ImageIO.read(new File("spider05.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			silverredSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				silverredSpider[counter] = new BufferedImage(64,64,silverredSpriteSheet.getType());
				Graphics2D gr = silverredSpider[counter].createGraphics();
				gr.drawImage(silverredSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			silverSpriteSheet = ImageIO.read(new File("spider06.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			silverSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				silverSpider[counter] = new BufferedImage(64,64,silverSpriteSheet.getType());
				Graphics2D gr = silverSpider[counter].createGraphics();
				gr.drawImage(silverSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			bluesilverSpriteSheet = ImageIO.read(new File("spider07.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			bluesilverSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				bluesilverSpider[counter] = new BufferedImage(64,64,bluesilverSpriteSheet.getType());
				Graphics2D gr = bluesilverSpider[counter].createGraphics();
				gr.drawImage(bluesilverSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			blueorangeSpriteSheet = ImageIO.read(new File("spider08.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			blueorangeSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				blueorangeSpider[counter] = new BufferedImage(64,64,blueorangeSpriteSheet.getType());
				Graphics2D gr = blueorangeSpider[counter].createGraphics();
				gr.drawImage(blueorangeSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			silveryellowSpriteSheet = ImageIO.read(new File("spider09.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			silveryellowSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				silveryellowSpider[counter] = new BufferedImage(64,64,silveryellowSpriteSheet.getType());
				Graphics2D gr = silveryellowSpider[counter].createGraphics();
				gr.drawImage(silveryellowSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			greenorangeSpriteSheet = ImageIO.read(new File("spider10.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			greenorangeSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				greenorangeSpider[counter] = new BufferedImage(64,64,greenorangeSpriteSheet.getType());
				Graphics2D gr = greenorangeSpider[counter].createGraphics();
				gr.drawImage(greenorangeSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
		
		try {
			greenyellowSpriteSheet = ImageIO.read(new File("spider11.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			greenyellowSpriteSheet = null;
			e.printStackTrace();
		}
		counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				greenyellowSpider[counter] = new BufferedImage(64,64,greenyellowSpriteSheet.getType());
				Graphics2D gr = greenyellowSpider[counter].createGraphics();
				gr.drawImage(greenyellowSpriteSheet, 0, 0, 64,64,64*j,64*i,64*j + 64, 64*i + 64, null);
				gr.dispose();
				counter ++;
			}
		}
	}
}
