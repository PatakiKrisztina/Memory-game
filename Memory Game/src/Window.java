import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Options o;
	static MGPanel p;
	static Window w;
	
	//ha uj jatekot akar kezdeni, leszedunk a framerol mindent es ujat teszunk ra
	static void ujjatek() {
		w.remove(p);
		w.remove(o);
		p=new MGPanel();
		w.add(p,BorderLayout.CENTER);
		o=new Options();
		w.add(o,BorderLayout.EAST);
		w.revalidate();
		w.repaint();
	}
	
	//ha megtalalt minden part elajanlja a jatekosnak hogy uj jatekot kezdjen, vagy hogy kilepjen
	static void vege() {
		int valaszt = JOptionPane.showOptionDialog(null, "Want to start a new game?", "Uj jatek", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null,null);
		if(valaszt== JOptionPane.YES_OPTION) {
			ujjatek();
		}
		else {
			System.exit(0);
		}
	}
	
	public Window(){
		setLayout(new BorderLayout(0,0));
		
		w=this;
		o=new Options();
		add(o,BorderLayout.EAST);
		
		p=new MGPanel();
		add(p,BorderLayout.CENTER);
		
		setBounds(0,0,1500,550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Memory Game");
		setVisible(true);
	}
}
