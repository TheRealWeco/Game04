package tk.astris.main;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import tk.astris.player.Player;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	paint zeichnen;
	Player player;
	
	public Frame(Player p){
		super("Game");		
		player = p;
		zeichnen = new paint();
		zeichnen.setBounds(0, 0, 800, 600);
		addKeyListener(new keyCheck());
		add(zeichnen);
	}
	
	public void repaintScreen(){
		zeichnen.repaint();
	}
	
	public void update() {
		
	}

	private class paint extends JLabel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			
		}
	}	
}

