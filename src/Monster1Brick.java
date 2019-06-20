import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster1Brick extends Brick{
	Image monBrick;

	public Monster1Brick(int x, int y) {
		super(x, y);
		openImage();
		
	}
	
	
	public void openImage() {
		try {
			monBrick =  ImageIO.read(getClass().getResource("mine2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	
public void draw(Graphics g) {
	g.drawImage(monBrick, super.getX(),super.getY(),null);
	}

}


