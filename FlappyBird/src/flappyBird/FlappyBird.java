package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.util.ArrayList;
//import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements Runnable{
private Renderer _ren;
	private JFrame _frame;
	public final int WIDTH=1200, HEIGHT=800;
	private Model _m;
	private Rectangle _bird;
	
	
	
	@Override
	public void run(){
		_m = new Model();
		_m.addObserver(this);
		_bird = new Rectangle(WIDTH/2-10,HEIGHT/2-10,20,20);
		_ren = new Renderer();
		_ren.addObserver(this);
		_m.addRenderer(_ren);
		Timer t = new Timer(20, new EventHandler(_m));
		
		_frame = new JFrame("Flappy Bird");
		_frame.setSize(WIDTH,HEIGHT);
		_frame.setVisible(true);
		_frame.setResizable(false);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.add(_ren);
		_frame.addMouseListener(new EventHandler(_m));
		_frame.addKeyListener(new EventHandler(_m));
		_m.addColumn(true);
		_m.addColumn(true);
		_m.addColumn(true);
		_m.addColumn(true);
		t.start();
		
	}
	public void repaint(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH,HEIGHT);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);
		
		g.setColor(Color.GREEN);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);
		
		g.setColor(Color.RED);
		g.fillRect(_bird.x,_bird.y, _bird.width, _bird.height);
		
		for(Rectangle c : _m.getColumns()){
			this.paintColumn(g, c);

		}
if(_m.getStarted()){
			
			try {
				g.setColor(Color.LIGHT_GRAY.darker().darker());
				g.setFont(new Font("Times New Roman",1,50));
				g.drawString("High Score: "+_m.getHighScore(), 375, this.getHeight()/2+350);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel",1,100));
		
		if(_m.getGameOver()){
		g.drawString("Game Over!", 300, this.getHeight()/2-300);
		}
		
		if(!_m.getStarted()){
			g.drawString("Click to start!", 300, this.getHeight()/2-300);
		}
		if(!_m.getGameOver() && _m.getStarted()){
			g.drawString(String.valueOf(_m.getScore()), WIDTH/2-25, this.getHeight()/2-300);
		}
	}
	
	public void paintColumn(Graphics g, Rectangle column){
		g.setColor(Color.GREEN.darker().darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	public Rectangle getBird(){
		return _bird;
	}
	public void setBird(Rectangle b){
		_bird =b;
	}

}
