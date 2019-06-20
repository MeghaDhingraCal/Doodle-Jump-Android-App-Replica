import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenBrick  extends Brick{
	Image greenBrick;

	public GreenBrick(int x, int y) {
		super(x, y);
		openImage();
		
	}
	
	
	public void openImage() {
		try {
			greenBrick =  ImageIO.read(getClass().getResource("gB.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	
public void draw(Graphics g) {
	g.drawImage(greenBrick, super.getX(),super.getY(),null);
	}

}
