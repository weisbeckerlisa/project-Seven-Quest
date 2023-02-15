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
import javax.swing.JProgressBar;

public class InterfaceCombat {
	
	private ImageIcon iconVide = new ImageIcon("images/vide.png");
	// Redimension de l'image vide
	private Image imageVide = iconVide.getImage().getScaledInstance(1, 1, Image.SCALE_SMOOTH);

	private InterfaceGenerale interfaceGraphique;
	private Combat combat;
	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public Font fontNonApparent = new Font(Font.SERIF, Font.BOLD, 0);
	
	private JLabel texteAffiche = new JLabel();
	private boolean suivantPushed = false;
	private int reponseAttaque = -1;
	private JButton next;
	private JPanel panelNext;
	private JPanel panelInfosJoueur;
	private JPanel panelInfosEnnemi;
	private JPanel panelDroite;
	private JPanel panelGauche;

	public InterfaceCombat(InterfaceGenerale intgen, Combat combat) {
		this.interfaceGraphique = intgen;
		this.combat = combat;
	}
	
	public ImagePanel creerPanelCombat() {
		
		ImageIcon iconEpee = new ImageIcon("images/epees/epeePierre.png");
		Image imageEpee = iconEpee.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconEpee = new ImageIcon(imageEpee);
		ImageIcon iconPotion = new ImageIcon("images/epees/potion.png");
		Image imagePotion = iconPotion.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		iconPotion = new ImageIcon(imagePotion);

		// Importation de l'image du parchemin
		ImageIcon iconParchemin = new ImageIcon("images/parchemin.png");
		// Redimension de l'image du parchemin
		Image imageParchemin = iconParchemin.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);



		//Image du joueur
		Image imagePerso = combat.getJoueur().getImageDos();
		//Image de l'ennemi
		Image imageEnnemi = combat.getEnnemi().getImageFace();
		
		// Creation du panel Combat
		ImagePanel panelCombat = new ImagePanel(imageVide);
		panelCombat.setBackground(transparent);
		panelCombat.setPreferredSize(new Dimension(1000, 600));
		panelCombat.setLayout(new BorderLayout());
		

		// Création du panel de gauche :
		panelGauche = new JPanel(new BorderLayout());
		panelGauche.setPreferredSize(new Dimension(250, 600));
		panelGauche.setBackground(transparent);

		// Création du panel des infos du joueur
		panelInfosJoueur = creerPanelInfosPerso(this.combat.getJoueur());

		// Création du panel de l'image du personnage
		ImagePanel panelPersonnage = new ImagePanel(imagePerso);
		panelPersonnage.setBackground(transparent);
		panelPersonnage.setPreferredSize(new Dimension(250, 525));
	
		// Ajout au panel de gauche
		panelGauche.add(panelInfosJoueur, BorderLayout.NORTH);
		panelGauche.add(panelPersonnage, BorderLayout.SOUTH);
		

		// Création du panel de droite :
		panelDroite = new JPanel(new BorderLayout());
		panelDroite.setBackground(transparent);
		panelDroite.setPreferredSize(new Dimension(250, 600));

		// Création du panel des infos du joueur
		panelInfosEnnemi = creerPanelInfosPerso(this.combat.getEnnemi());

		// Création du panel de l'image du personnage
		ImagePanel panelEnnemi = new ImagePanel(imageEnnemi);
		panelEnnemi.setBackground(transparent);
		panelEnnemi.setPreferredSize(new Dimension(250, 525));
	
		// Ajout au panel de droite
		panelDroite.add(panelInfosEnnemi, BorderLayout.SOUTH);
		panelDroite.add(panelEnnemi, BorderLayout.NORTH);
		

		// Création du panel central
		JPanel panelCentre = new JPanel(new BorderLayout());
		panelCentre.setBackground(transparent);

		// Création du panel de texte
		ImagePanel panelTxt = new ImagePanel(imageParchemin);
		panelTxt.setBackground(transparent);
		panelTxt.setLayout(new GridLayout(3,1));

		// Création du panel de texte
		JPanel panelTexte = new JPanel();
		panelTexte.setBackground(transparent);
		texteAffiche.setText("Début du combat !!!");
		texteAffiche.setFont(police);
		panelTexte.add(texteAffiche, BorderLayout.CENTER);

		// Création du panel du bouton
		panelNext = new JPanel();
		panelNext.setBackground(transparent);
		next = new JButton("Suivant");

		// Définition et affectation de l'ActionListener pour le bouton suivant
		ActionListener actionEcouteurSuivant = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suivantPushed = true;
			}
		};

		next.addActionListener(actionEcouteurSuivant);
		next.setBackground(Color.lightGray);
		panelNext.add(next);

		// Ajout des panel au panel principal
		panelTxt.add(new JLabel());
		panelTxt.add(panelTexte);
		panelTxt.add(panelNext);


		// Création du panel de choix des attaques
		ImagePanel panelAttaques = new ImagePanel(imageParchemin);
		panelAttaques.setBackground(transparent);
		panelAttaques.setLayout(new GridLayout(4,8,10,10));
		for (int i = 1; i <= 9; i++) {
			panelAttaques.add(new JLabel());
		}

		// Définition et affectation de l'ActionListener pour les boutons
		ActionListener actionEcouteurAttaques = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bouton = (JButton) e.getSource();
				reponseAttaque =  Integer.parseInt(bouton.getText());
			}
		};
		
		// Création des 6 boutons d'attaques
		JButton rienFaire = new JButton(iconPotion);
		rienFaire.setBackground(transparent);
		rienFaire.setText("0");
		rienFaire.setFont(fontNonApparent);
		rienFaire.addActionListener(actionEcouteurAttaques);
		JButton attaqueLegere = new JButton(iconEpee);
		attaqueLegere.setBackground(transparent);
		attaqueLegere.setText("1");
		attaqueLegere.setFont(fontNonApparent);
		attaqueLegere.addActionListener(actionEcouteurAttaques);
		JButton attaqueLourde = new JButton(iconEpee);
		attaqueLourde.setBackground(transparent);
		attaqueLourde.setText("2");
		attaqueLourde.setFont(fontNonApparent);
		attaqueLourde.addActionListener(actionEcouteurAttaques);
		JButton attaqueSpe = new JButton(iconEpee);
		attaqueSpe.setBackground(transparent);
		attaqueSpe.setText("3");
		attaqueSpe.setFont(fontNonApparent);
		attaqueSpe.addActionListener(actionEcouteurAttaques);
		JButton debuff = new JButton(iconPotion);
		debuff.setBackground(transparent);
		debuff.setText("4");
		debuff.setFont(fontNonApparent);
		debuff.addActionListener(actionEcouteurAttaques);
		JButton buff = new JButton(iconPotion);
		buff.setBackground(transparent);
		buff.setText("5");
		buff.setFont(fontNonApparent);
		buff.addActionListener(actionEcouteurAttaques);

		panelAttaques.add(rienFaire);
		panelAttaques.add(attaqueLegere);
		panelAttaques.add(attaqueLourde);
		panelAttaques.add(attaqueSpe);
		panelAttaques.add(debuff);
		panelAttaques.add(buff);

		for (int i = 1; i <= 2; i++) {
			panelAttaques.add(new JLabel());
		}
		panelAttaques.add(new JLabel("skip", JLabel.CENTER));
		panelAttaques.add(new JLabel("légère", JLabel.CENTER));
		panelAttaques.add(new JLabel("lourde", JLabel.CENTER));
		panelAttaques.add(new JLabel("spé", JLabel.CENTER));
		panelAttaques.add(new JLabel("debuff", JLabel.CENTER));
		panelAttaques.add(new JLabel("buff", JLabel.CENTER));
		
		for (int i = 1; i <= 9; i++) {
			panelAttaques.add(new JLabel());
		}
		panelAttaques.setPreferredSize(new Dimension(500, 200));


		// Ajout des panel au panel centre
		panelCentre.add(panelTxt, BorderLayout.CENTER);
		panelCentre.add(panelAttaques, BorderLayout.SOUTH);


		// Ajout des panel au panel principal
		panelCombat.add(panelGauche, BorderLayout.WEST);
		panelCombat.add(panelCentre, BorderLayout.CENTER);
		panelCombat.add(panelDroite, BorderLayout.EAST);

		return panelCombat;
	}

	private JPanel creerPanelInfosPerso(Personnage personnage) {
		// Création du panel d'affichage des stats du personnage
		//ImagePanel panelInfosPersonnage = new ImagePanel()  //Normalement un ImagePanel mais je n'ai pas trouvé d'images.
		JPanel panelInfosPersonnage = new JPanel();
		panelInfosPersonnage.setLayout(new GridLayout(3, 2));
		panelInfosPersonnage.setBackground(new Color(169, 138, 96));
		
		JLabel name = new JLabel("Nom : " + personnage.getNom(),JLabel.CENTER);
		JLabel level = new JLabel("Niveau : " + personnage.getNiveau(), JLabel.CENTER);
		JLabel vie = new JLabel("Vie : ", JLabel.RIGHT);
		JLabel adresse = new JLabel("Adresse : " + personnage.getAdresse(), JLabel.CENTER);
		JLabel intel = new JLabel("Intelligence : " + personnage.getIntelligence(), JLabel.CENTER);
		JLabel force = new JLabel("Force : " + personnage.getForce(), JLabel.CENTER);

		//Changer la police
		name.setFont(police);
		vie.setFont(police);
		level.setFont(police);
		adresse.setFont(police);
		intel.setFont(police);
		force.setFont(police);

		// BAR DE VIE
		JProgressBar bar = new JProgressBar();
		bar.setMaximum(personnage.getHpMax());
		bar.setMinimum(0);
		bar.setStringPainted(true);
		bar.setValue((int) personnage.getVie());

		JPanel sousPanelInfos = new JPanel(new GridLayout(1,2));
		sousPanelInfos.setBackground(transparent);
		sousPanelInfos.add(vie);
		sousPanelInfos.add(bar);
		
		panelInfosPersonnage.add(name);
		panelInfosPersonnage.add(adresse);
		panelInfosPersonnage.add(level);
		panelInfosPersonnage.add(force);
		panelInfosPersonnage.add(sousPanelInfos);
		panelInfosPersonnage.add(intel);

		return (panelInfosPersonnage);
	}

	public void attendreSuivant() {
		panelNext.add(next);
		suivantPushed = false;
		while (!suivantPushed) {
			System.out.print("");
		}
		panelNext.remove(next);
	}

	public int attendreReponseAttaque() {
		reponseAttaque = -1;
		while (reponseAttaque == -1) {
			System.out.print("");
		}
		return reponseAttaque;
	}

	public void setTexte(String texte) {
		texteAffiche.setText(texte);
		texteAffiche.setHorizontalAlignment(JLabel.CENTER);
		this.interfaceGraphique.actualiserFrame();
	}

	public void actualiserStatistiques() {
		this.panelDroite.remove(panelInfosEnnemi);
		this.panelGauche.remove(panelInfosJoueur);

		this.panelInfosEnnemi = creerPanelInfosPerso(this.combat.getEnnemi());
		this.panelInfosJoueur = creerPanelInfosPerso(this.combat.getJoueur());

		this.panelDroite.add(panelInfosEnnemi, BorderLayout.SOUTH);
		this.panelGauche.add(panelInfosJoueur, BorderLayout.NORTH);
		this.interfaceGraphique.actualiserFrame();
		this.interfaceGraphique.actualiserStats();
	}
}
