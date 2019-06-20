import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class YellowBrick extends Brick {

	Timer timerYellow;
	Image yBrick;
	boolean goUp = true;
	
	public YellowBrick(int x, int y) {
		super(x, y);
		timerYellow = new Timer(100, null);
				timerYellow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}

		});
		
		timerYellow.start();
		openImage();
	}

	public void tick() {
		if (goUp) { //as go up y pos decrease
			super.higherY(); 
		}
		if(goUp == false) {
			super.lowerY(40); //going down, yPos increases
		}

		if (this.getY() <= (origY-30)) {
			System.out.print("2");
			goUp = false; //decrease yPos (go up) 
		}
		
		if (this.getY() >= (origY+30)) {
			System.out.print("1");
			goUp = true; //increase yPos (go down) 
		}
		 
	}
	
	
	public void openImage() {
		try {
			yBrick =  ImageIO.read(getClass().getResource("lmao 2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
public void draw(Graphics g) {
	g.drawImage(yBrick, super.getX(),super.getY(),null);
	}
}

