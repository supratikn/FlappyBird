package flappyBird;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventHandler implements ActionListener, MouseListener, KeyListener{
	private Model _m;
	//private Renderer _r;
	public EventHandler(Model m){
		//_r=r;
		_m=m;
	}
@Override
public void actionPerformed(ActionEvent e){
	_m.stateChanged();
	
}
@Override
public void mouseClicked(MouseEvent e) {
	_m.jump();
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_SPACE){
		_m.jump();
	}
}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
	
}
