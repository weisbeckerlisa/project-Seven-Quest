import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InterfacePersonnage {
	
	public Dimension dimFenetre = new Dimension(1000, 1000);
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public Font fontNonApparent = new Font(Font.SERIF, Font.BOLD, 0);
	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	private int reponse = -1;
	public String nomPerso = "";
	JTextField tNom; 

	public InterfacePersonnage() {
	}

	public ImagePanel demanderPanel() {
		// Importation de l'image du village et redimension
		ImageIcon iconVillage = new ImageIcon("images/background/village.jpg");
		// Redimension de l'icon
		Image imageVillage = iconVillage.getImage().getScaledInstance((int) dimFenetre.getWidth(),(int) dimFenetre.getHeight(), Image.SCALE_SMOOTH);
	    Icon scaledIconVillage = new ImageIcon(imageVillage);

		// Création du JLabel représentant le fond de la fenêtre principale
		JLabel fondPrincipal = new JLabel(scaledIconVillage);
		// Ajout de l'image en fond de la fenêtre
		fondPrincipal.setBackground(transparent);
		//frame.setContentPane(fondPrincipal);
		

		
		
		// Importation de l'image du magicien
		ImageIcon iconMagicien = new ImageIcon("images/magicien/magicien_face.png");
		// Redimension de l'image du magicien
		Image imageMagicien = iconMagicien.getImage().getScaledInstance(628, 990, Image.SCALE_SMOOTH);
		iconMagicien = new ImageIcon(imageMagicien);
		
		// Importation de l'image du guerrier
		ImageIcon iconGuerrier = new ImageIcon("images/guerrier/guerrier_face.png");
		// Redimension de l'image du guerrier
		Image imageGuerrier = iconGuerrier.getImage().getScaledInstance(450, 712, Image.SCALE_SMOOTH);
		iconGuerrier = new ImageIcon(imageGuerrier);

		//Nord	
		JPanel panelNom = new JPanel(new FlowLayout());
		panelNom.setBackground(Color.LIGHT_GRAY);		
		tNom = new JTextField();
		tNom.setOpaque(false);
		tNom.setEditable(true);
		tNom.setPreferredSize(new Dimension(500, 20));
		tNom.setBackground(transparent);
		panelNom.add(new JLabel("Nom de votre personnage : "));
		panelNom.add(tNom);
			 
		
		ActionListener actionEcouteur = new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				JButton bPerso = (JButton) e.getSource();
				reponse = Integer.parseInt(bPerso.getText());
			}
		};

		//Ouest
		JPanel panelMagicien = new JPanel();
		panelMagicien.setBackground(transparent);
		JButton bMagicien = new JButton(iconMagicien); 
		bMagicien.setText("1");
		bMagicien.setFont(fontNonApparent);
		bMagicien.addActionListener(actionEcouteur);
		bMagicien.setPreferredSize(new Dimension(475, 800));
		bMagicien.setBackground(transparent);
		panelMagicien.add(bMagicien);
		
		
		//Est
		JPanel panelGuerrier = new JPanel();
		panelGuerrier.setBackground(transparent);		
		JButton bGuerrier = new JButton(iconGuerrier); 	
		bGuerrier.setText("2");
		bGuerrier.setFont(fontNonApparent);
		bGuerrier.addActionListener(actionEcouteur);
		bGuerrier.setPreferredSize(new Dimension(475, 800));
		bGuerrier.setBackground(transparent);
		panelGuerrier.add(bGuerrier);
		
		//Centre
		JPanel panelCentre = new JPanel(new FlowLayout());
		panelCentre.setBackground(transparent);
		panelCentre.add(panelMagicien);
		panelCentre.add(panelGuerrier);
			
		//Sud
		JPanel panelRetour = new JPanel();
		panelRetour.setBackground(transparent);		
		JButton bRetour = new JButton("Retour");
		bRetour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				reponse = 0;
			}
		});
		bRetour.setPreferredSize(new Dimension(300, 75));
		bRetour.setBackground(Color.LIGHT_GRAY);
		panelRetour.add(bRetour);

		ImagePanel panelFrame = new ImagePanel(imageVillage);
		panelFrame.setBackground(transparent);
		panelFrame.setLayout(new BorderLayout());

		panelFrame.add(panelNom, BorderLayout.NORTH);
		panelFrame.add(panelCentre, BorderLayout.CENTER);
		panelFrame.add(panelRetour, BorderLayout.SOUTH);
		
		return panelFrame;
	}
	
	public int reponsePersonnage() {
		reponse = -1;
		while (reponse == -1) {
			System.out.print("");
			this.nomPerso = this.tNom.getText();
		}
		if (this.nomPerso.equals("") && reponse != 0) {
			return reponsePersonnage();
		} else {
			return reponse;
		}
	}		
}
