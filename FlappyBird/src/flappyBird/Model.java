package flappyBird;


import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;





public class Model {
	private Random _rand;
	private ArrayList<Rectangle> _columns;
	private FlappyBird _f;
	private Renderer _r;
	private int _ticks, _yMotion, _score;
	private boolean _go, _started;
	public Model(){
		_score=0;
	    _started=false;
		_rand = new Random();
		_columns=new ArrayList<Rectangle>();
	}
	public void addObserver(FlappyBird f){
		_f=f;
	}
	public void addColumn(boolean start){
		int space =300;
		int widt =100;
		int height = 50 +_rand.nextInt(300);
		if(start){
		_columns.add(new Rectangle(_f.getWidth()+widt+_columns.size()*300,_f.getHeight()-height-120,widt,height));
		_columns.add(new Rectangle(_f.getWidth()+widt+(_columns.size()-1)*300,0,widt,_f.getHeight()-height-space));
		}
		else{
			_columns.add(new Rectangle(_columns.get(_columns.size()-1).x+600,_f.getHeight()-height-120,widt,height));
			_columns.add(new Rectangle(_columns.get(_columns.size()-1).x,0,widt,_f.getHeight()-height-space));
		}
	}
	
	public ArrayList<Rectangle> getColumns(){
		return _columns;
	}
	
	public void addRenderer(Renderer r){
		_r=r;
	}
	public void stateChanged(){
		if(_r!=null && _f!=null){
			int speed =10;
			_ticks++;
			if(_started){
			for(int i=0; i<getColumns().size();i++){
				_columns.get(i).x-=speed;
							}
			if(_ticks%2==0 && _yMotion<15){
			_yMotion +=2;
			}
			for(int i=0; i<getColumns().size();i++){
				Rectangle col=_columns.get(i);
				if(col.x+col.width<0){
					_columns.remove(col);
					if(col.y==0){
					this.addColumn(false);
					}
				}
			}
			_f.getBird().y+=_yMotion;
			for(Rectangle c :getColumns()){
				
				if(c.y==0 && (_f.getBird().x+_f.getBird().width/2>c.x+c.width/2-10) && 
						(_f.getBird().x+_f.getBird().width/2<c.x+c.width/2+10)){
					int s = getScore();
					s= s+1;
					
					this.setScore(s);
				}
					
				if(c.intersects(_f.getBird())){
				//	_f.getBird().y =_f.getHeight()-120-_f.getBird().height;
					this.setGameOver(true);
					if(_f.getBird().x<=c.x){
					_f.getBird().x =c.x -_f.getBird().width;
					}
					else {
						if(c.y!=0){
							_f.getBird().y=c.y -_f.getBird().height;
						}
						else if(_f.getBird().y<c.height){
							_f.getBird().y=c.height;
						}
					}
				}
				if(_f.getBird().y>_f.getHeight()-120 || _f.getBird().y<0){
					this.setGameOver(true);
				}
				if(_f.getBird().y + _yMotion >= _f.getHeight() - 120){
					_f.getBird().y =_f.getHeight()-120-_f.getBird().height;
					this.setGameOver(true);
				}
			}
			}
			_r.repaint();
		}
	}
	public void jump(){
		if(_f!=null){
			if(getGameOver()){
				_score=0;
				Rectangle bird = new Rectangle(_f.getWidth()/2-10,_f.getHeight()/2-10,20,20);
				_f.setBird(bird);
				clearColumns();
				addColumn(true);
				addColumn(true);
				addColumn(true);
				addColumn(true);
				this.setGameOver(false);
			}
			//_f.jump();
			if(!this.getStarted()){
			this.setStarted();
			}
			else if(!getGameOver()){
				if(_yMotion>0){
					_yMotion=0;
				}
				_yMotion-=10;
			}
			
		
		}
	}
public void setScore(int x){
	_score = x;
}
	public void setGameOver(Boolean b){
		_go=b;
	}
	public boolean getGameOver(){
		return _go;
	}
	public void setStarted(){
		_started=true;
	}
	public boolean getStarted(){
		return _started;
	}
	public void clearColumns(){
		_columns.clear();
	}
	public int getScore(){
		return _score;
	}
	public String getHighScore() throws IOException{
		changeHighScore();

		Iterator<String> it1 = new HighScoreReader("HighScore/HighScore.txt");
		String s1 =it1.next();
		return s1+"";

	}
	public void changeHighScore() throws IOException{

		Iterator<String> it1 = new HighScoreReader("HighScore/HighScore.txt");
		String s1 =it1.next();
		int result = Integer.parseInt(s1);


		BufferedReader file = new BufferedReader(new FileReader("HighScore/HighScore.txt"));
		String line;
		String input = "";

		while ((line = file.readLine()) != null)
			input += line + System.lineSeparator();
		if(getScore()>result){
			input = input.replace(s1, getScore()+"");
		}
		FileOutputStream os = new FileOutputStream("HighScore/HighScore.txt");
		os.write(input.getBytes());

		file.close();
		os.close();
	}
}
