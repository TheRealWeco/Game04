package tk.astris.player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import tk.astris.fileManager.Controls;
import tk.astris.main.Main;
import tk.astris.main.keyCheck;
import tk.astris.physics.Collisions;

public class Player {
	
	public int camX = 0;
	public int camY = 0;
	public int x = 0;
	public int y = 0;
	public int speed = 4;
	public boolean isAlive = true;
	
	public int sizeX = 54;
	public int sizeY = 64;
	public int sizeImgX = 64;
	public int sizeImgY = 64;
	
	public Rectangle hitbox = new Rectangle(x, y, sizeX, sizeY);
	public boolean collideLeft = false;
	public boolean collideRight = false;
	
	public BufferedImage imgSheet = Main.images.getImg("sumoHulk_spriteSheet_x4.png");
	public BufferedImage image = imgSheet.getSubimage(0, 0, sizeImgX, sizeImgY);
	public int animationLength = 4;
	
	public boolean jumping = false;
	
	private double jumpBoost = -20;
	private double gravity = 1;
	
	public Player(){
		
	}
	
	public void update(Rectangle borderLeft, Rectangle borderRight){
		
		
		if(jumping){
			jumpBoost = jumpBoost + gravity;

			boolean canMove = Collisions.canMoveY((int) jumpBoost, Main.frame.l.tileObjectList);
			if(canMove && Collisions.willCollideY((int) jumpBoost, Main.frame.l.tileObjectList)){
			
			y = (int) (y + jumpBoost);
			hitbox = new Rectangle(x, y, sizeX, sizeY);
			
			}else{
				jumping = false;
				jumpBoost = -20;
			}
		}
				
		/*if(Collisions.isStuck(Main.frame.l.tileObjectList)){
			y = y - 1;
			hitbox = new Rectangle(x, y, sizeX, sizeY);
		}
		*/
		if(Collisions.inAir(Main.frame.l.tileObjectList) && !jumping){
			if(Collisions.willCollideY(10, Main.frame.l.tileObjectList)){
			y = (int) (y + 10);
			hitbox = new Rectangle(x, y, sizeX, sizeY);
			}
		}

		if(keyCheck.keysCheck(Controls.moveLeft)){
			if(Collisions.canMoveX(-speed, Main.frame.l.tileObjectList) && Collisions.willCollideX(-speed, Main.frame.l.tileObjectList)){
			camX = camX - speed / 2;
			
			
			if(collideLeft){
				camX = camX - speed;
			}else{
				x = x - speed;

			}
			hitbox = new Rectangle(x, y, sizeX, sizeY);
			}

		}
		
		if(keyCheck.keysCheck(Controls.moveRight)){
			
			if(Collisions.canMoveX(speed, Main.frame.l.tileObjectList) && Collisions.willCollideX(speed, Main.frame.l.tileObjectList)){
			camX = camX + speed / 2;
			
			
			if(collideRight){
				camX = camX + speed;
			}else{
				
				x = x + speed;
			}
			hitbox = new Rectangle(x, y, sizeX, sizeY);
			}

		}
		
		if(keyCheck.keysCheck(Controls.jump)){
			jumping = true;
		}
		
		
		
	}
	

}
