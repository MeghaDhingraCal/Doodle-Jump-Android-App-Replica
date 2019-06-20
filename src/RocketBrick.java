import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RocketBrick extends Brick {
	private Image rocketBrick; 
	public RocketBrick(int x, int y) {
		super(x, y);
		openImage(); 
	}



	public void isTouching(Creature c, ArrayList<Brick> brickArr) {
		c.loadRocket();
		
	}
	public void openImage() {
		try {
			rocketBrick =  ImageIO.read(getClass().getResource("rocketBrick.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g) {
		g.drawImage(rocketBrick, super.getX(),super.getY(),null);
	}
}
