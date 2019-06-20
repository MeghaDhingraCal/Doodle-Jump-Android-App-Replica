

	import java.awt.Color;
import java.awt.Graphics;
	import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

	import javax.imageio.ImageIO;
import javax.swing.Timer;

	public class Dot  {
		int x;
		int y;
		Timer dotT;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
			dotT = new Timer(10, null);
			dotT.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Dottick();
		}

	});
	
	dotT.start();
	
}

public void Dottick() {

			higherDot();
			
			
		}
		
		public int getX() {return x;}
		public int getY() {return y;}
		public void higherDot() {y--;}




		
	public void draw(Graphics g) {
		
		g.setColor(Color.RED);
		g.drawOval(x-5,y-6,15,15);
		g.fillOval(x, y, 5, 5);
		}

	}


