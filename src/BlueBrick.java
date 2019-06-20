import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class BlueBrick extends Brick {


	Timer timerBlue;
	Image blueBrick;
	boolean goRight;
	
	public BlueBrick(int x, int y) {
		super(x, y);
		timerBlue = new Timer(75, null);
				timerBlue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}

		});
		
		timerBlue.start();
		openImage();
	}

	public void tick() {
		if (goRight) { //as going Down, yPosition increases
			super.incX();; 
		}
		else {
			super.decX(); //going up, yPos decreases
		}

		if (super.getX() >= DoodleJumpPanel.nWIDTH-10) {
			goRight = false; //decrease yPos (go up) 
		}
		
		if (super.getX()<= 0) {
			goRight = true; //increase yPos (go down) 
		}
		 
	}
	
	
	public void openImage() {
		try {
			blueBrick =  ImageIO.read(getClass().getResource("blB.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void sideToSide() {
		
	}
	
public void draw(Graphics g) {
	g.drawImage(blueBrick, super.getX(),super.getY(),null);
	}
}
