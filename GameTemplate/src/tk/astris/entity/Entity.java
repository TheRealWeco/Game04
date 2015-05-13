package tk.astris.entity;

public class Entity {

	public boolean canMove = false;
	
	
	public void update(){
		
		if(canMove){
			move();
		}
		
	}
	
	
	public void move(){}
	
	
}
