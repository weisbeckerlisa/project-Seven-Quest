
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





  
public class BackImage {

	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	public Font police = new Font(Font.SERIF, Font.BOLD, 24);
	public JFrame frame;
	public boolean partieLancee = false;
	
	final static int height = 1000;
	final static int weight = 1000;
	public ImagePanel panelMenu;
	
	public BackImage(JFrame frame) {
		this.frame = frame;
	}

	public ImagePanel MiseEnPlace() { // throws IOException, InterruptedException {
		ImageIcon fondIcon = new ImageIcon("images/background/fond_menu.png");
		Image fond = fondIcon.getImage();
		JPanel panel = new JPanel();
		panel.setBackground(transparent);
		panel.setLayout(new GridLayout(5, 1, 50, 50));
		
		JButton lancerPartie = new JButton("Lancer Partie");
		JButton quitter = new JButton("Quitter");
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setForeground(Color.white);
		quitter.setFont(police);
		quitter.addActionListener(e -> {
			System.exit(0);
		});

	    lancerPartie.setOpaque(false);
		lancerPartie.setContentAreaFilled(false);
		lancerPartie.setForeground(Color.white);
		lancerPartie.setFont(police);
		
		panel.add(new JLabel());
		panel.add(new JLabel());


	    panel.add(lancerPartie);
		panel.add(quitter);

		ImagePanel panelMenu = new ImagePanel(fond);
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBackground(transparent);

		JLabel labelVide1 = new JLabel();
		labelVide1.setBackground(transparent);
		labelVide1.setPreferredSize(new Dimension(200, 10));
		JLabel labelVide2 = new JLabel();
		labelVide2.setBackground(transparent);
		labelVide2.setPreferredSize(new Dimension(200, 10));
		
		panelMenu.add(labelVide1, BorderLayout.EAST);
		panelMenu.add(labelVide2, BorderLayout.WEST);
		panelMenu.add(panel, BorderLayout.CENTER);
		
		lancerPartie.addActionListener(e ->{
			this.lancerJeu(panelMenu);
		});
		
		
		return(panelMenu);
		
	}
		
	/*
	public void InsererTexte(String Texte, int height, int width) {
		JLabel input = new JLabel();
		Random rand = new Random();
		int min = 1;int max = 10;
		for (int i = 0; i <Texte.length();i++){ 
			input.setText(input.getText()+Texte.charAt(i));
			try{
				int value = rand.nextInt(max+min) + min;
				Thread.sleep(value*10);	
			} catch(InterruptedException e){
				
			} 
		}	
	}
	*/
	
	public void lancerJeu(ImagePanel panelMenu) {
		partieLancee = true;
	}
	
	public void attendreReponse() {
		while(!partieLancee) {
			System.out.print("");
		}
		partieLancee = false;
	}

	
	public void AnimationLancer(int a,int b) {
		
		int dureeVideo = 9500; // en millisecondes
		
		
		String pathImage1 = new String();
		String pathVideo1  = new String();
		
		String pathImage2  = new String();
		String pathVideo2  = new String();
		
		JLayeredPane lpane = new JLayeredPane();
		
		switch (a) {
			case 1:
				
				pathVideo1 = "images/animation_de_1.gif";
				pathImage1 = "images/animation_dé/image_de_1.png";
				break;
				
			case 2:
				
				pathVideo1 = "images/animation_de_2.gif";
				pathImage1 = "images/animation_dé/image_de_2.png";
				break;	
				
			case 3:
				
				pathVideo1 = "images/animation_de_3.gif";
				pathImage1 = "images/image_de_3.png";
				break;
				
			case 4:
				
				pathVideo1 = "images/animation_de_4.gif";
				pathImage1 = "images/image_de_4.png";
				break;
				
			case 5:
				
				pathVideo1 = "images/animation_de_5.gif";
				pathImage1= "images/image_de_5.png";
				break;
			case 6:
				
				pathVideo1 = "images/animation_de_6.gif";
				pathImage1 = "images/image_de_6.png";
				break;
		}
		
		
		switch (b) {
		case 1:
			
			pathVideo2 = "images/animation_de_1.gif";
			pathImage2 = "images/animation_dé/image_de_1.png";
			break;
			
		case 2:
			
			pathVideo2 = "images/animation_de_2.gif";
			pathImage2 = "images/animation_dé/image_de_2.png";
			break;	
			
		case 3:
			
			pathVideo2 = "images/animation_de_3.gif";
			pathImage2 = "images/image_de_3.png";
			break;
			
		case 4:
			
			pathVideo2 = "images/animation_de_4.gif";
			pathImage2 = "images/image_de_4.png";
			break;
			
		case 5:
			
			pathVideo2 = "images/animation_de_5.gif";
			pathImage2 = "images/image_de_5.png";
			break;
		case 6:
			
			pathVideo2 = "images/animation_de_6.gif";
			pathImage2 = "images/image_de_6.png";
			break;
	}
		
		frame.add(lpane, BorderLayout.CENTER);
		lpane.setBounds(0, 0, 1000, 500);
		
		JLabel LabelVideo1 = new JLabel(new ImageIcon(pathVideo1));
		JLabel LabelImage1 = new JLabel(new ImageIcon(pathImage1));
		
		JLabel LabelVideo2 = new JLabel(new ImageIcon(pathVideo2));
		JLabel LabelImage2 = new JLabel(new ImageIcon(pathImage2));
		
		JPanel Panel1 = new JPanel();
		Panel1.add(LabelVideo1);
		Panel1.setOpaque(true);
		
		JPanel Panel2 = new JPanel();
		Panel2.setOpaque(true);
		Panel2.add(LabelVideo2);
		
		Panel1.setBounds(0,0,500,500);
		Panel2.setBounds(500,0,500,500);
		
		lpane.add(Panel1,0);
		lpane.add(Panel2,0);
		
		try {
		Thread.sleep(dureeVideo);
		}
		catch(Exception e) {
		}
		frame.remove(lpane);  
		
		
		frame.setSize(999,1000);
		frame.setSize(1000,1000);
		
	}
	
}
