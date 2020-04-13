import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MGPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MGPanel p[];
	private JButton gombok[];
	private ImageIcon back=new ImageIcon("back.png");
	private String front[]= {"egyess.jpg","egyess.jpg","ketto.jpg","ketto.jpg","Janice.jpg","Janice.jpg","harom.png","harom.png","elsor.jpg","elsor.jpg","pivot.jpg","pivot.jpg"};
	int kep=0;
	private int probalkozasok=0;
	private int elso, masodik;
	private Random r;
	private Timer timer;
	private JButton thisbutton;
	private JButton previousbutton;
	private int parok=0;
	static boolean bp=true;
	
	public MGPanel() {
		timer=new Timer();
		
		//random sorrendbe teszem a kepeket
		r=new Random();
		String csere;
		for (int i=0;i<12;i++) {
			int j=r.nextInt(12);
			csere=front[i];
			front[i]=front[j];
			front[j]=csere;
		}
		
		//a kartyak eleinte le vannak forditva (back)
		gombok=new JButton[12];
		for(int i=0;i<12;i++) {
			gombok[i]=new JButton();
			gombok[i].setIcon(back);
			gombok[i].setBackground(Color.BLACK);
			add(gombok[i]);
			gombok[i].addActionListener(new BListener());
		}
		
		setLayout(new GridLayout(3,4));
		setVisible(true);
		
	}
	
	

public int getProbalkozasok() {
		return probalkozasok;
	}

public int getParok() {
	return parok;
}


//ha lenyomjik a gombot, megfordulnak a kartyak
public class BListener implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		probalkozasok++;
		
		thisbutton= (JButton) e.getSource();
		
		for(int i=0;i<12;i++)
		{
			//megjelenik a megfelelo kep (i.gomb = i.kep)
			if(e.getSource()==gombok[i]) {
				thisbutton.setIcon(new ImageIcon(front[i]));
				kep=i;
			}
		}
		
		//ha 2 kartya van felfordtva, ha ugyanolyanok akkor ugymarad, ha kulonboznek akkor ujra megjelenik a kartya hata
		if(probalkozasok % 2==0) {
			Options.hany.setText(Integer.toString(probalkozasok/2));
			masodik=kep;
			if(front[elso].equals(front[masodik])) {
				parok++;
				//modositjuk a labelt amin megjelenik hany part talalt el
				Options.hanyt.setText(Integer.toString(parok));
			}
			else {
				timer.schedule(new TimerTask() {
					
				public void run() {
					thisbutton.setIcon(back);
					thisbutton.setEnabled(true);
					previousbutton.setIcon(back);
					previousbutton.setEnabled(true);
				}
				},500);
		}
		}
		else {
			elso=kep;
			previousbutton=thisbutton;
		}
		
		//ha a probalkozasok szama < mint az eddigi High Score akkor uj High Scoret mentunk le
		if(parok==6) {
			int highScore=0;
			File file = new File("C:/Users/krisz/eclipse-workspace/Memory Game/HighScore.txt");
			try(Scanner in=new Scanner(file)){
				highScore=Integer.parseInt(in.nextLine());
			}catch(FileNotFoundException ex) {
				System.out.println("Error");
			}
			if (probalkozasok/2<highScore) {
				JOptionPane.showMessageDialog(null, probalkozasok/2 + " trys, new High Score!");
				try(PrintWriter out=new PrintWriter(file)){
					out.print(probalkozasok/2);
				}catch(FileNotFoundException ex) {
					System.out.println("Error");
				}
			}
			parok=0;
			probalkozasok=0;
			Window.vege();
		}
		
	}
}
}
