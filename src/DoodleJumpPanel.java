import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class DoodleJumpPanel  extends JPanel { 
	private BufferedImage background; 
	private BufferedImage rightJumper; 
	private BufferedImage leftJumper;
	private BufferedImage brownBrick; 
	private Timer stopYellowTimer;
	private Timer timerWhite;
	private Timer timerKill;
	static int score;
	private int prevY = 200;
	JOptionPane death;
	static JFrame frame;
	static DoodleJumpPanel dp;
	Dot d;

	private int yPositionCreature;
	private boolean goDown = false;


	static Creature cret;
	//static Creature c2;

	ArrayList<Brick> brickArr = new ArrayList<Brick>();
	ArrayList<Dot> dotArr = new ArrayList<Dot>();

	public final static int nWIDTH = 800;
	public final static int nHEIGHT = 600 ;
	//test
	// ********
	//static BlueBrick y;

	public DoodleJumpPanel() throws IOException {
		this.addMouseListener(new MouseListener() {

			
			public void mousePressed(MouseEvent arg0) {
				// write your clicking code here!!
				//Button3 = right
				//Button1 = left

				System.out.println("You just clicked: "+arg0);

				if(arg0.getButton()==1)

				{
					shoot(cret.getX(), cret.getY());
				}

				


				repaint();


			}

			private void shoot(int x, int y) {
				 d= new Dot(x,y);
				 dotArr.add(d);
				System.out.println("dot");
				
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			

		});
	
	
		genBrick(1);
		int lowestX=0;
		int lowestY=0;
		for(int c=0; c<brickArr.size(); c++) {
			if(brickArr.get(c).getY()>lowestY)
			{lowestY = brickArr.get(c).getY();
			lowestX = brickArr.get(c).getX();}
		}
		cret = new Creature(lowestX,lowestY);
		background = ImageIO.read(getClass().getResource("background.png"));
		rightJumper = ImageIO.read(getClass().getResource("jRight.png"));
		leftJumper = ImageIO.read(getClass().getResource("jLeft.png"));
		brownBrick =  ImageIO.read(getClass().getResource("brownB.png"));
		death = new JOptionPane();
		//frame.add(death);
	}

	Timer creatureTimer = new Timer(60, null);
	Timer brickTimer = new Timer(200, null);



	public static void main(String[] args) throws IOException {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame("Welcome to Doodle Jump!!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		dp = new DoodleJumpPanel();
		frame.add(dp);
		dp.setPreferredSize(new Dimension(800,600));
		frame.pack();
		frame.setVisible(true);
		dp.setUpKeyMappings();
		dp.startGame();

		//		JFrame frame_score = new JFrame("Score");
		//		frame_score.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		//		DoodleJumpPanel sc = new DoodleJumpPanel();
		//		sc.add(sc.addScore());
		//		frame_score.add(sc);
		//		sc.setPreferredSize(new Dimension(200,100));
		//		frame_score.pack();
		//		frame_score.setVisible(true);

	}

	private JLabel displaysc() {
		JLabel label = new JLabel ();
		return label;

	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				System.out.println("Hit left arrow!!");
				cret.left();
			}
		});
		this.requestFocusInWindow();


		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the right arrow is pressed?
				System.out.println("Hit right arrow!!");
				cret.right();
			}
		});
		this.requestFocusInWindow();
		
		
	
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the right arrow is pressed?
				System.out.println("Hit right arrow!!");
				cret.right();
			}
		});
		this.requestFocusInWindow();

	}

	////   change pic accordinly (cut new pick)

	//   see if touching <  > then green jump and brown falls and breaks (changes brkn boolean)
	//   spring(+=10 instead of ++)
	// add monster
	//make it shoot at things
	//add volume


	public void tickKill() {
		
		
		
		for(Dot dot: dotArr) {
			for(int Bm = 0; Bm<brickArr.size(); Bm++) {
				if(brickArr.get(Bm) instanceof Monster1Brick)
				{    
					if(brickArr.get(Bm).getX()-50 <= dot.getX() && dot.getX()<= brickArr.get(Bm).getX() + 50 &&
							brickArr.get(Bm).getY()-50 <= dot.getY() && dot.getY()<= brickArr.get(Bm).getY() + 50)
					{          brickArr.remove(Bm) ; }
				}
				
			}
		}
		
		
		
	}
	
	
	
	
	
	private void startGame() {
		timerKill = new Timer(10, null);
		timerKill.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tickKill();
	}

});

timerKill.start();





		creatureTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveTheCreature();
			}

		});
		yPositionCreature = 200;
		creatureTimer.start();
		//genBrick(1);

		brickTimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isTouching()) {
					adjust_createB();

				}
			}

		});
		brickTimer.start();
	}

	private String getScore() {
		return Integer.toString(score);
	}
	private JLabel addScore() {
		JLabel label = new JLabel("Score: "+getScore());
		return label;
	}

	public void genBrick(int type) {
		// if type 1, then its at the very beginning and make bricks everywhere
		//if type 2, then its in middle of game when adjusting blocks so you need to make only at top

		int randNum;
		System.out.println("got to generating random number");


		//greenBrick


		if(type == 1)
		{
			for(int count1 = 0; count1<6; count1++) {
				randNum = (int) (Math.random() * 23);
				if(randNum <=9) {
					//check if need to add all specific methods of child classes to brick class
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 60 + ( (int) (Math.random() * (nHEIGHT-60)));
					Brick x = new GreenBrick(randx,randy );
					brickArr.add(x);
					//GreenBrick.draw(null);
				}
				else if(randNum<=13)
				{  //blueBrick
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 60 + ( (int) (Math.random() * (nHEIGHT-60)));
					Brick x = new BlueBrick(randx,randy );
					brickArr.add(x);
				}
				else if(randNum<=16) {
					//spring
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 60 + ( (int) (Math.random() * (nHEIGHT-60)));
					Brick x = new GreenBrickSpring(randx,randy );
					brickArr.add(x);
				}
				else if (randNum<= 20) {
					//brown
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 60 + ( (int) (Math.random() * (nHEIGHT-60)));
					Brick x = new BrownBrick(randx ,randy );
					brickArr.add(x);
				}
				else {
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 60 + ( (int) (Math.random() * (nHEIGHT-60)));
					Brick x = new RocketBrick(randx,randy );
					brickArr.add(x);
				}



			}

		}

		else if(type == 2) {

			for(int count2 = 0; count2<2; count2++) { 
				randNum = (int) (Math.random() * 26);
				if(randNum <=10) {
					//check if need to add all specific methods of child classes to brick class
					int randx =50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new GreenBrick(randx,randy );
					brickArr.add(x);
					//GreenBrick.draw(null);
				}
				else if(randNum<=12)
				{  //blueBrick
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));;
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new BlueBrick(randx,randy );
					brickArr.add(x);
				}
				else if(randNum<=15) {
					//spring
					int randx =50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new GreenBrickSpring(randx,randy );
					brickArr.add(x);
				}
				else if(randNum<=16) {
					//monster 
					int randx =50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new Monster1Brick(randx,randy );
					brickArr.add(x);
				}
				else if (randNum<= 18) {
					//brown
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy =  40 + ((int) (Math.random() * 150)) ;
					Brick x = new BrownBrick(randx,randy);
					brickArr.add(x);
				}
				else if(randNum<=21)
				{  //blueBrick
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));;
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new YellowBrick(randx,randy );

					brickArr.add(x);
					//////

					endYellow();						

					/////
				}
				else if(randNum<=23)
				{  //blueBrick
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));;
					int randy = 40 + ((int) (Math.random() * 150)) ;
					Brick x = new WhiteBrick(randx,randy );

					brickArr.add(x);
					//////

					
					/////
				}


				else {
					//rocket brick
					int randx = 50 + ((int) (Math.random() *(nWIDTH -120)));
					int randy =  40 + ((int) (Math.random() * 150)) ;
					Brick x = new RocketBrick(randx, randy);
					brickArr.add(x);
				}

			}
		}
	}

	private void endYellow() {

		stopYellowTimer = new Timer(5000, null);
		stopYellowTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				yellowStop();
			}

		});

		stopYellowTimer.start();

	}

	public void yellowStop() {
		for(int co=0; co<brickArr.size(); co++) {

			if(brickArr.get(co) instanceof YellowBrick)
			{
				brickArr.remove(co);}
		}
			
		stopYellowTimer.stop();

	}

	protected void moveTheCreature() {
		if (goDown) { //as going Down, yPosition increases
			cret.lowerY(20);
		}
		else if (!cret.hasRocket()) {
			cret.higherY(20); //going up, yPos decreases
		}
		if(isTouching())
		{
			goDown = false;
			adjust_createB();
			score+=20;//decrease yPos (go up) 
		}

		if (!cret.hasRocket() && cret.getY() <= 0 
				//|| cret.getY()<prevY-250
				) {
			goDown = true; //increase yPos (go down) 
		}
		if(cret.getY()>= nHEIGHT) {
			for(Brick b: brickArr) {
				b.higherY();
			}
			endGame();
			//while(cret.getY()<(nHEIGHT/2)) {
			//cret.higherY(nHEIGHT/2);
		}
		cret.outOfThePanel();

		repaint(); 
	}

	private void adjust_createB() {
		for(Brick b: brickArr) {

			b.lowerY(40);
		}
		genBrick(2);
	}

	private void endGame() {
		for(Brick b: brickArr) {
			b.higherY();
		}
		// TODO Auto-generated method stub
		//display score;
		//fall down (already done I think)
		//creatureTimer.setDelay(30);
		String me = Integer.toString(score);
		int result = death.showConfirmDialog(frame,
				"This is your score: "+ me +"  Press OK to leave, you loser!",
				"You died!",
				death.PLAIN_MESSAGE);


		if (result == death.OK_OPTION) {
			System.exit(0);}

		//creatureTimer.stop();

	}
	private boolean isCreatureTouchingBrick(Brick b) {
		return 
				b.getX()-52.5 < cret.getX() &&
				b.getX()+52.5 > cret.getX() && 
				cret.getY() < b.getY()+23 && 
				b.getY()-23 < cret.getY() &&
				(b.getY() < nHEIGHT );
	}
	public boolean isTouching() {
		boolean ret = false;
		//only true if it is touching a brick, which means it can go ahead and jump up again

		for(Brick b : brickArr) {

			if(isCreatureTouchingBrick(b) &&
					!(b instanceof BrownBrick)&&
					!(b instanceof Monster1Brick)) 
			{
				prevY = b.getY();
				ret = true;

			}
			if(b.getX()-47.5 < cret.getX() &&
					b.getX()+47.5 > cret.getX() && 
					//cret.getY() < b.getY()+47.5 && 
					b.getY()-47.5 < cret.getY() &&
					(b.getY()<nHEIGHT ) &&
					(b instanceof Monster1Brick)) 
			{
				endGame();

			}

			if(isCreatureTouchingBrick(b) &&
					(b instanceof BrownBrick)) 
			{

				b.breakApart();

			}
			
			if(isCreatureTouchingBrick(b) && (b instanceof WhiteBrick)) {
				startWhiteTimer(b);
			}


			if(isCreatureTouchingBrick(b) &&
					(b instanceof RocketBrick)) 
			{
				cret.loadRocket();


			}

			if(isCreatureTouchingBrick(b) &&
					(b instanceof GreenBrickSpring)) 
			{
				b.boinged = true;
				score+=10;
				prevY-=200;

				for(Brick b1:brickArr) {
					b1.lowerY(120);
			
					
				}
			}



		}

		return ret;

	}

	private void startWhiteTimer(Brick b) {
		timerWhite = new Timer(600, null);
		timerWhite.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Whitetick();
	}

});

timerWhite.start();

}

public void Whitetick() {
	for(int co=0; co<brickArr.size(); co++) {

		if(brickArr.get(co) instanceof WhiteBrick)
		{
			brickArr.remove(co);}
	}
		
	timerWhite.stop();
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0 , 0, nWIDTH, nHEIGHT, this);

		g.drawImage(background, 0 , 0, nWIDTH, nHEIGHT, this);
		if (cret.hasRocket()) {
			for (Brick b: brickArr) {
				b.lowerY(40);
			}
			adjust_createB();
		}

		for (Brick b: brickArr) {
			b.draw(g);
		}
		cret.draw(g);
		
		for(Dot d: dotArr)
		d.draw(g);
	}




}