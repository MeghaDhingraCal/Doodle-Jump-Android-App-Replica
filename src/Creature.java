import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Creature {

	private final static int WIDTH = 75;
	private final static int HEIGHT = 75;
	final int normalJumpAmount = 20; // 20 pixels per panel refresh/paint

	private static int xLoc; int yLoc; // location
	private boolean facingLeft;
	private boolean hasRocket = false;
	private long rocketStartTimeMs = 0;
	private boolean goUp = true;
	private final static long ROCKET_EXPIRATION_TIME_MS = 5 * 1000; // 5 seconds
	Image leftImageWithRocket; 
	Image rightImageWithRocket;	
	
	private boolean onSpring = false; 


	static boolean faceLeft;
	Image IjumpRight;
	Image IjumpLeft;
	public final static int nWIDTH = 800;
	public final static int nHEIGHT = 600;

	public Creature(int x, int y) {
		xLoc = x;
		yLoc = y;
		openImage();
		faceLeft = false;
	}

	public void outOfThePanel() {
		if (xLoc > nWIDTH) {
			xLoc = 0; 
		}
		if (xLoc < 0) {
			xLoc = nWIDTH -1; 

		}
	}

	private void openImage() {
		try {
			IjumpRight =  ImageIO.read(getClass().getResource("jRight.png"));
			IjumpLeft = ImageIO.read(getClass().getResource("jLeft.png"));
			leftImageWithRocket = ImageIO.read(getClass().getResource("rocketFaceLEFT.png")); 
			rightImageWithRocket= ImageIO.read(getClass().getResource("rocketFaceRIGHT.png")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public int getX() {
		return xLoc; 
	}
	public int getY() {
		return yLoc; 
	}

	public void higherY(int numY) {
		yLoc-= numY;
	}

	public void lowerY(int numY) {
		yLoc+= numY;
	}

	public static void left() {
		faceLeft = true;
		xLoc-= 20;
	}

	public static void right() {
		faceLeft = false;
		xLoc+= 20;
	}

	public void goDown() {
		goUp = false;
	}
	public void goUp() {
		goUp = true;
	}
	public void toggleDirection() {
		goUp = !goUp;
	}

	public void move() {
		toggleRocket();

		if (goUp) {
			yLoc -= getJumpAmount();
		} else {
			yLoc += getJumpAmount();
		}
	}

	public void jump(int amount) {
		if (goUp) {
			yLoc -= amount;
		} else {
			yLoc += amount;
		}
	}
	private int getJumpAmount() {
		if (hasRocket) {
			// return normalJumpAmount * 2;	
			// when creature loads a rocket we stop moving the creature and instead move the rest
			// of the objects in opposite direction which will give the effect of the creature
			// moving at double the speed
			return 0;
		}
		return normalJumpAmount; 
	}

	public void turnLeft() {
		facingLeft = true;
		xLoc -= getJumpAmount();
	}

	public void turnRight() {
		facingLeft = false;
		xLoc += getJumpAmount();
	}

	public void loadRocket() {
		if (!hasRocket) {
			hasRocket = true;
			rocketStartTimeMs = System.currentTimeMillis();
		}
	}


	public void unloadRocket() {
		hasRocket = false;
	}

	public boolean hasRocket() {
		return hasRocket;
	}

	private void toggleRocket() {
		// check if creature has rocket and if expiration time is up. if so unload.
		if (hasRocket && (System.currentTimeMillis() - rocketStartTimeMs) > ROCKET_EXPIRATION_TIME_MS) {
			unloadRocket(); 
		}
	}
	
	 

	public void draw(Graphics g) {
		toggleRocket(); 

		if (faceLeft && !hasRocket) {
			g.drawImage(IjumpLeft, xLoc, yLoc, 75, 75, null);
		} else if (!facingLeft && !hasRocket) {
			g.drawImage(IjumpRight, xLoc,yLoc, 75, 75, null);
		} else if (facingLeft && hasRocket) {
			g.drawImage(leftImageWithRocket, xLoc,yLoc, 75, 75, null);
		} else {
			g.drawImage(rightImageWithRocket, xLoc,yLoc, 75, 75, null);
		}

	}


}









