package tk.astris.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;

import tk.astris.data.Level;
import tk.astris.data.TileObject;
import tk.astris.player.Player;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	paint zeichnen;
	Player player;
	public Level l = new Level(); //TODO: testlvl
	boolean showLvl = true;
	public int width = 64 * 16;
	public int height = 64 * 16;
	public boolean debug = true;
	
	Rectangle borderLeft = new Rectangle(0, 0, width / 6, height);
	Rectangle borderRight = new Rectangle(width - width/6, 0, width / 6, height);
	
	public Rectangle shouldDraw = new Rectangle(0,0);

	public Frame(Player p){
		super("Game");		
		player = p;
		zeichnen = new paint();
		zeichnen.setBounds(0, 0, width, height);
		addKeyListener(new keyCheck());
		add(zeichnen);
		setSize(width, height);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		l.load("lvl1");
		p.x = l.spawnX;
		p.y = l.spawnY;

	}
	
	public void repaintScreen(){
		zeichnen.repaint();
	}
	
	public void update() {
		player.update(borderLeft, borderRight);
	}

	private class paint extends JLabel{
		@Override
		public void paintComponent(Graphics g2){
			super.paintComponent(g2);
			
			Graphics2D g = (Graphics2D)g2;
						
			if(showLvl){
			for(TileObject tileObj : l.tileObjectList){
				g.drawImage(tileObj.tile.img, tileObj.x - player.camX, tileObj.y - player.camY, null);
			}
			}
			
			if(player.isAlive){
				g.drawImage(player.image, player.x, player.y, null);
			}
			
			if(debug){
				g.draw(player.hitbox);
				g.draw(borderLeft);
				g.draw(borderRight);
				
				if(player.hitbox.intersects(borderRight)){
					player.collideRight = true;
				}else{
					player.collideRight = false;
				}

				if(player.hitbox.intersects(borderLeft)){
					player.collideLeft = true;
				}else{
					player.collideLeft = false;
				}
				
				for(TileObject t : l.tileObjectList){
					Rectangle r = new Rectangle(t.x - Main.player.camX, t.y - Main.player.camY, 64, 64);
					g.draw(r);
				}
				
			}
			
		}
	}	
}

