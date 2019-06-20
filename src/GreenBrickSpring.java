import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenBrickSpring extends Brick  {
	Image Ispring;
	Image boingSpring;
	

	public GreenBrickSpring(int x, int y) {
		super(x, y);
		openImage();
	}
	
	public void openImage() {
		try {
			Ispring =  ImageIO.read(getClass().getResource("finalSpring.png"));
			boingSpring = ImageIO.read(getClass().getResource("boing.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	
public void draw(Graphics g) {
	if(boinged == false) {
	g.drawImage(Ispring, super.getX(),super.getY(), null);}
	if(boinged ) {g.drawImage(boingSpring, super.getX(),super.getY(), null);}
	}


}
