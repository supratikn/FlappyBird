package flappyBird;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel{

	private static final long serialVersionUID = 1L;
	private FlappyBird _f;
	public Renderer(){
		
	}
	public void addObserver(FlappyBird f){
		_f=f;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(_f!=null){
		_f.repaint(g);
		}
	}
}
