import java.awt.Graphics;

public abstract class Brick {
	int	x; 
	int y; 
	int origY;
	boolean notBroken;
	public static boolean boinged = false;
	
	public Brick(int x, int y) {
		this.x = x; 
		this.y = y; 
		origY = y;
		notBroken = true;
	}
	public int getX() {
		return x; 
	}

	public int getY() {
		return y; 
	}
	
	public void incX() {
		x+=20;
	}
	
	public void decX() {
		x-=20;
	}
	
	public void lowerY(int num) {
		y+=num;
	}
	
	public void higherY() {
		y-=60;
	}
	
	
	
	public void openImage() {}
	public void tick() {}
	public void breakApart() {}
	
	
public void draw(Graphics G) {
		System.out.println("using brick draw method");
	}
}
