import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class BrownBrick extends Brick {
	Creature c; 
	
	
	BufferedImage brownBrick;
	BufferedImage brknBrownBrick;
	Timer brown;
	
	public BrownBrick(int x, int y) {
		super(x, y);
		openImage();
		brown = new Timer(40, null);
		
		brown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lowerY(40);
			}

		});
	}

	public void openImage() {
		try {
			brownBrick =  ImageIO.read(getClass().getResource("brB.png"));
			brknBrownBrick = ImageIO.read(getClass().getResource("newNewBrokBrick.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void breakApart(){
		notBroken = false;
		brown.start();
	}

	public void draw(Graphics g) {
		
		if (notBroken)
		{	g.drawImage(brownBrick, super.getX(),super.getY(), null);}
		else {
			g.drawImage(brknBrownBrick, super.getX(),super.getY(),190,75,null);
		}
		
	}

	


}
