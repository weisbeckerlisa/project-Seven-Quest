import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceChoix {
	
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	private Choix choix; 
	private InterfaceGenerale intGene;
	private int reponse = -1;
	
	public InterfaceChoix(Choix choix, InterfaceGenerale intGene) {
		this.choix = choix; 
		this.intGene = intGene;
	}

	public ImagePanel creerPanel() {

		intGene.actualiserStats();
		// Importation de l'image du parchemin
		ImageIcon iconParchemin = new ImageIcon("images/parchemin.png");
		
		// Redimension de l'image du parchemin
		Image imageParchemin = iconParchemin.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		
		// Creation du panel Histoire
		ImagePanel panelHistoire = new ImagePanel(imageParchemin); // panel avec un parchemin en fond 
		panelHistoire.setBackground(transparent);
		panelHistoire.setPreferredSize(new Dimension(800, 500));
		panelHistoire.setLayout(new GridLayout(3,1));

		//Set texte scenario 
		JPanel panelScenario = new JPanel();
		panelScenario.setBackground(transparent);
		JLabel scenario = new JLabel(this.choix.getTexte(), JLabel.CENTER);
		scenario.setFont(police);
		panelScenario.add(scenario, BorderLayout.CENTER);
				
		//Add les boutons de choix 
		JPanel panelChoix = new JPanel();
		panelChoix.setBackground(transparent);
		JButton choix1 = new JButton(this.choix.getChoix(1));
		JButton choix2 = new JButton(this.choix.getChoix(2));
		JButton choix3 = new JButton(this.choix.getChoix(3));
		JButton choix4 = new JButton(this.choix.getChoix(4));
		
		// Définition et affectation de l'ActionListener pour les boutons
		ActionListener actionEcouteur = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton boutonSelected = (JButton) e.getSource();
				if (boutonSelected == choix1) {
					reponse = 1;
				} else if(boutonSelected == choix2) {
					reponse = 2;
				} else if(boutonSelected == choix3) {
					reponse = 3;
				} else if(boutonSelected == choix4) {
					reponse = 4;
				}
			}
		};

		choix1.addActionListener(actionEcouteur);
		choix2.addActionListener(actionEcouteur);
		choix3.addActionListener(actionEcouteur);
		choix4.addActionListener(actionEcouteur);

		choix1.setBackground(Color.LIGHT_GRAY);
		panelChoix.add(choix1);
		if (this.choix.getNbChoix()> 1 ) {
			choix2.setBackground(Color.LIGHT_GRAY);
			panelChoix.add(choix2);
			if (this.choix.getNbChoix()>2) {
				choix3.setBackground(Color.LIGHT_GRAY);
				panelChoix.add(choix3);
				if (this.choix.getNbChoix()>3) {
					choix4.setBackground(Color.LIGHT_GRAY);
					panelChoix.add(choix4);
				}
			}
			
		}
		
		//Add les panels au panel principal
		panelHistoire.add(new JLabel());
		panelHistoire.add(panelScenario);
		panelHistoire.add(panelChoix);
		
		return panelHistoire;
	}

	public int afficherDemanderReponse() {
		// Création de l'ImagePanel du choix
		ImagePanel panel = creerPanel();
		// Affichage du panel
		this.intGene.setPanel(panel);

		// On attend que l'utilisateur ai fait un choix
		while(this.reponse == -1){
			System.out.print("");
		}

		// On retourne le choix du joueur
		return reponse;
	}
}
