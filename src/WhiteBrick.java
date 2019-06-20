

	import java.awt.Graphics;
	import java.awt.Image;
	import java.io.IOException;

	import javax.imageio.ImageIO;

	public class WhiteBrick  extends Brick{
		Image whiteBrick;

		public WhiteBrick(int x, int y) {
			super(x, y);
			openImage();
			
		}
		
		
		public void openImage() {
			try {
				whiteBrick =  ImageIO.read(getClass().getResource("white.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}



		
	public void draw(Graphics g) {
		g.drawImage(whiteBrick, super.getX(),super.getY(),null);
		}

	}


