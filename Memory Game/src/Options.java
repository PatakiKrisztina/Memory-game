import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Options extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JButton newGame;
	private JButton quit;
	private JLabel probak;
	static JLabel hany;
	private JLabel talalatok;
	static JLabel hanyt;
	private MGPanel p;
	static boolean b=true;
	
	public Options() {
		setLayout(new GridLayout(6,1));
		setBackground(new Color(216,191,216));
		p=new MGPanel();
		
		newGame=new JButton("New Game");
		newGame.setBackground(new Color(216,191,216));
		add(newGame);
		
		//probalkozasok szama
		probak=new JLabel("Moves");
		probak.setBackground(new Color(216,191,216));
		add(probak);
		
		hany=new JLabel();
		hany.setBackground(new Color(216,191,216));
		hany.setText(Integer.toString(p.getProbalkozasok()));
		add(hany);
		
		//talalatok szama
		talalatok=new JLabel("Matches");
		talalatok.setBackground(new Color(216,191,216));
		add(talalatok);
		
		hanyt=new JLabel();
		hanyt.setBackground(new Color(216,191,216));
		hanyt.setText(Integer.toString(p.getProbalkozasok()));
		add(hanyt);
		
		quit = new JButton("Quit");
		quit.setBackground(new Color(216,191,216));
		add(quit);
		
		Options.newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Window.ujjatek();
			}
		});
		
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});
		
		repaint();
		
		setVisible(true);
	}
}
