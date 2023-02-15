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

public class InterfaceInventaire {

	private Personnage perso;

	private int selected1;
	private JButton boutonSelected1;
	private int selected2;
	private JButton boutonSelected2;
	private boolean selec1done = false;

	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	public Dimension dimFenetre = new Dimension(1000, 1000);
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public Font fontNonApparent = new Font(Font.SERIF, Font.BOLD, 0);
	private ImageIcon iconVide = new ImageIcon("images/vide.png");
	private JButton boutons[];
	private InterfaceGenerale intGene;

	public InterfaceInventaire(InterfaceGenerale intGene) {
		this.intGene = intGene;
	}
		
	public ImagePanel getPanelInventaire() {
		this.perso = InterfaceGenerale.personnage;
		this.boutons = new JButton[this.perso.tailleInventaire];
		// Redimension de l'image vide
		Image imageVide = iconVide.getImage().getScaledInstance(1, 1, Image.SCALE_SMOOTH);
		iconVide = new ImageIcon(imageVide);
		
		// Importation de l'image du parchemin
		ImageIcon iconParchemin = new ImageIcon("images/parchemin.png");
		// Redimension de l'image du parchemin
		Image imageParchemin = iconParchemin.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);


		// Importation de l'image du casque
		ImageIcon iconHelmet = new ImageIcon("images/helmet.png");
		// Redimension de l'image du casque
		Image imageHelmet = iconHelmet.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconHelmet = new ImageIcon(imageHelmet);

		// Importation de l'image de l'armure
		ImageIcon iconArmure = new ImageIcon("images/armure.png");
		// Redimension de l'image de l'armure
		Image imageArmure = iconArmure.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconArmure = new ImageIcon(imageArmure);

		// Importation de l'image de main droite
		ImageIcon iconhandD = new ImageIcon("images/handG.png");
		// Redimension de l'image de main droite
		Image imagehandD = iconhandD.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconhandD = new ImageIcon(imagehandD);

		// Importation de l'image de main gauche
		ImageIcon iconhandG = new ImageIcon("images/handD.png");
		// Redimension de l'image de main gauche
		Image imagehandG = iconhandG.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconhandG = new ImageIcon(imagehandG);

		ActionListener actionEcouteur = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Importation de l'image du casque
				ImageIcon iconHelmet = new ImageIcon("images/helmet.png");
				// Redimension de l'image du casque
				Image imageHelmet = iconHelmet.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
				iconHelmet = new ImageIcon(imageHelmet);

				// Importation de l'image de l'armure
				ImageIcon iconArmure = new ImageIcon("images/armure.png");
				// Redimension de l'image de l'armure
				Image imageArmure = iconArmure.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
				iconArmure = new ImageIcon(imageArmure);

				// Importation de l'image de main droite
				ImageIcon iconhandD = new ImageIcon("images/handG.png");
				// Redimension de l'image de main droite
				Image imagehandD = iconhandD.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
				iconhandD = new ImageIcon(imagehandD);

				// Importation de l'image de main gauche
				ImageIcon iconhandG = new ImageIcon("images/handD.png");
				// Redimension de l'image de main gauche
				Image imagehandG = iconhandG.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
				iconhandG = new ImageIcon(imagehandG);
				
				if(!selec1done) {
					boutonSelected1 = (JButton) e.getSource();
					selected1 = Integer.parseInt(boutonSelected1.getText());
					selec1done = true;
				} else {
					boutonSelected2 = (JButton) e.getSource();
					selected2 = Integer.parseInt(boutonSelected2.getText());
					if ((selected1 != selected2) && (InterfaceGenerale.personnage.echangeEquipement(selected1, selected2))) {
						try {
							if(selected2 >= 4) {
								boutonSelected2.setIcon(InterfaceGenerale.personnage.inventaire[selected2 - 4].getIcon(75, 75));
							} else {
								boutonSelected2.setIcon(InterfaceGenerale.personnage.equipementActuel[selected2].getIcon(75, 75));
							}
							boutonSelected2.setFont(fontNonApparent);
						} catch (NullPointerException exception1) {
							if (selected2 >= 4) {
								boutonSelected2.setIcon(iconVide);
								boutonSelected2.setFont(police);
							} else if (selected2 == 0) {
								boutonSelected2.setFont(fontNonApparent);
								boutonSelected2.setIcon(iconhandD);
							} else if (selected2 == 1) {
								boutonSelected2.setFont(fontNonApparent);
								boutonSelected2.setIcon(iconhandG);
							} else if (selected2 == 2) {
								boutonSelected2.setFont(fontNonApparent);
								boutonSelected2.setIcon(iconHelmet);
							} else if (selected2 == 3) {
								boutonSelected2.setFont(fontNonApparent);
								boutonSelected2.setIcon(iconArmure);
							}
						}
						try {
							if(selected1 >= 4) {
								boutonSelected1.setIcon(InterfaceGenerale.personnage.inventaire[selected1 - 4].getIcon(75, 75));
							} else {
								boutonSelected1.setIcon(InterfaceGenerale.personnage.equipementActuel[selected1].getIcon(75, 75));
							}
							boutonSelected1.setFont(fontNonApparent);
						} catch (NullPointerException exception2) {
							if (selected1 >= 4) {
								boutonSelected1.setIcon(iconVide);
								boutonSelected1.setFont(police);
							} else if (selected1 == 0) {
								boutonSelected1.setFont(fontNonApparent);
								boutonSelected1.setIcon(iconhandD);
							} else if (selected1 == 1) {
								boutonSelected1.setFont(fontNonApparent);
								boutonSelected1.setIcon(iconhandG);
							} else if (selected1 == 2) {
								boutonSelected1.setFont(fontNonApparent);
								boutonSelected1.setIcon(iconHelmet);
							} else if (selected1 == 3) {
								boutonSelected1.setFont(fontNonApparent);
								boutonSelected1.setIcon(iconArmure);
							}
						}
						intGene.actualiserStats();
					}
					intGene.actualiserFrame();
					selec1done = false;
				}
			}
		};


		ImagePanel panelInventaire = new ImagePanel(imageParchemin);
		panelInventaire.setLayout(new GridLayout(5, 1, 10, 10));
		panelInventaire.setBackground(transparent);

		JButton boutonTete = new JButton("2");
		if (perso.equipementActuel[2] == null) {
			boutonTete.setFont(fontNonApparent);
			boutonTete.setIcon(iconHelmet);
		} else {
			boutonTete.setFont(fontNonApparent);
			boutonTete.setIcon(perso.equipementActuel[2].getIcon(75, 75));
		}
		boutonTete.addActionListener(actionEcouteur);
		boutonTete.setBackground(transparent);

		JButton boutonArmure = new JButton("3");
		if (perso.equipementActuel[3] == null) {
			boutonArmure.setFont(fontNonApparent);
			boutonArmure.setIcon(iconArmure);
		} else {
			boutonArmure.setFont(fontNonApparent);
			boutonArmure.setIcon(perso.equipementActuel[3].getIcon(75, 75));
		}
		boutonArmure.addActionListener(actionEcouteur);
		boutonArmure.setBackground(transparent);

		JButton boutonBrasDroit = new JButton("0");
		if (perso.equipementActuel[0] == null) {
			boutonBrasDroit.setFont(fontNonApparent);
			boutonBrasDroit.setIcon(iconhandD);
		} else {
			boutonBrasDroit.setFont(fontNonApparent);
			boutonBrasDroit.setIcon(perso.equipementActuel[0].getIcon(75, 75));
		}
		boutonBrasDroit.addActionListener(actionEcouteur);
		boutonBrasDroit.setBackground(transparent);

		JButton boutonBrasGauche = new JButton("1");
		if (perso.equipementActuel[1] == null) {
			boutonBrasGauche.setFont(fontNonApparent);
			boutonBrasGauche.setIcon(iconhandG);
		} else {
			boutonBrasGauche.setFont(fontNonApparent);
			boutonBrasGauche.setIcon(perso.equipementActuel[1].getIcon(75, 75));
		}
		boutonBrasGauche.addActionListener(actionEcouteur);
		boutonBrasGauche.setBackground(transparent);

		
		for (int i = 0; i < perso.tailleInventaire; i++) {
			boutons[i] = new JButton(String.valueOf(i+4));
			if (perso.inventaire[i] ==  null) {
				boutons[i].setFont(police);
			} else {
				boutons[i].setFont(fontNonApparent);
				boutons[i].setIcon(perso.inventaire[i].getIcon(75,75));
			}
			boutons[i].addActionListener(actionEcouteur);
			boutons[i].setBackground(transparent);
		}

		JButton boutonGold = new JButton("Gold");
		boutonGold.setBackground(transparent);

		JPanel panelInventaireLigne1 = new JPanel(new GridLayout(1, 11, 10, 10));
		JPanel panelInventaireLigne2 = new JPanel(new GridLayout(1, 11, 10, 10));
		JPanel panelInventaireLigne3 = new JPanel(new GridLayout(1, 11, 10, 10));
		panelInventaireLigne1.setBackground(transparent);
		panelInventaireLigne2.setBackground(transparent);
		panelInventaireLigne3.setBackground(transparent);

		panelInventaireLigne1.add(new JLabel());
		panelInventaireLigne1.add(new JLabel());
		panelInventaireLigne1.add(new JLabel());
		panelInventaireLigne1.add(boutonTete);
		panelInventaireLigne1.add(new JLabel());
		panelInventaireLigne1.add(boutons[0]);
		panelInventaireLigne1.add(boutons[1]);
		panelInventaireLigne1.add(boutons[2]);
		panelInventaireLigne1.add(boutons[3]);
		panelInventaireLigne1.add(new JLabel());
		panelInventaireLigne1.add(new JLabel());

		panelInventaireLigne2.add(new JLabel());
		panelInventaireLigne2.add(new JLabel());
		panelInventaireLigne2.add(new JLabel());
		panelInventaireLigne2.add(boutonArmure);
		panelInventaireLigne2.add(new JLabel());
		panelInventaireLigne2.add(boutons[4]);
		panelInventaireLigne2.add(boutons[5]);
		panelInventaireLigne2.add(boutons[6]);
		panelInventaireLigne2.add(boutons[7]);
		panelInventaireLigne2.add(new JLabel());
		panelInventaireLigne2.add(new JLabel());

		panelInventaireLigne3.add(new JLabel());
		panelInventaireLigne3.add(new JLabel());
		panelInventaireLigne3.add(boutonBrasDroit);
		panelInventaireLigne3.add(new JLabel());
		panelInventaireLigne3.add(boutonBrasGauche);
		panelInventaireLigne3.add(boutons[8]);
		panelInventaireLigne3.add(boutons[9]);
		panelInventaireLigne3.add(boutons[10]);
		panelInventaireLigne3.add(boutonGold);
		panelInventaireLigne3.add(new JLabel());
		panelInventaireLigne3.add(new JLabel());
		

		JPanel labelTransparent = new JPanel();
		labelTransparent.setBackground(transparent);
		panelInventaire.add(labelTransparent);
		panelInventaire.add(panelInventaireLigne1);
		panelInventaire.add(panelInventaireLigne2);
		panelInventaire.add(panelInventaireLigne3);
		panelInventaire.setPreferredSize(new Dimension(1000, 500));
		return panelInventaire;
	}

	public void actualiserInventaire() {
		Personnage personne = InterfaceGenerale.personnage;
		for (int i = 0; i < personne.tailleInventaire; i++) {
			if (personne.inventaire[i] != null) {
				boutons[i].setIcon(personne.inventaire[i].getIcon(75, 75));
				boutons[i].setFont(fontNonApparent);
			} else {
				boutons[i].setIcon(iconVide);
				boutons[i].setFont(police);
			}
		} 
	}
}
