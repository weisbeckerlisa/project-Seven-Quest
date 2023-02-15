import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class InterfaceGenerale {
	
	private ImagePanel panelTotal;
	private JPanel panelSud;
	private InterfaceInventaire interInventaire;
	public boolean inventaireOn = false;
	public Component stockWhileInventaire;
	private InterfaceParametres interParametre;
	public boolean parametreOn = false;
	public Component stockWhileParametre;
	public static Personnage personnage; 
	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	public Dimension dimFenetre = new Dimension(1000, 1000);
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public Font fontNonApparent = new Font(Font.SERIF, Font.BOLD, 0);
	private ImageIcon iconVide = new ImageIcon("images/vide.png");
	private JFrame frame;
	private JMenuItem adresse;
	private JMenuItem intel;
	private JMenuItem force;
	private JLabel level;
	private JProgressBar bar;


	public InterfaceGenerale(Personnage perso, JFrame frame) {
		// Fenêtre générale
		this.frame = frame;

		InterfaceGenerale.personnage  = perso;
	}
	
	public ImagePanel getInterfaceGene() {
		
		// Redimension de l'image vide
		Image imageVide = iconVide.getImage().getScaledInstance(1, 1, Image.SCALE_SMOOTH);
		iconVide = new ImageIcon(imageVide);

		
		interInventaire = new InterfaceInventaire(this);
		ImagePanel panelInventaire = interInventaire.getPanelInventaire();

		interParametre = new InterfaceParametres();
		ImagePanel panelParametres = interParametre.creerParametres();
		

		// Importation de l'image du village et redimension
		ImageIcon iconVillage = new ImageIcon("images/village.jpg");
		// Redimension de l'icon
		Image imageVillage = iconVillage.getImage().getScaledInstance((int) dimFenetre.getWidth(),(int) dimFenetre.getHeight(), Image.SCALE_SMOOTH);
	    Icon scaledIconVillage = new ImageIcon(imageVillage);

		/*
		// Création du JLabel représentant le fond de la fenêtre principale
		JLabel fondPrincipal = new JLabel(scaledIconVillage);
		// Ajout de l'image en fond de la fenêtre
		fondPrincipal.setBackground(transparent);
		frame.setContentPane(fondPrincipal);
		*/

		// Importation de l'image du sac à dos
		ImageIcon iconBackpack = new ImageIcon("images/backpack.png");
		// Redimension de l'image du sac à dos
		Image imageBackpack = iconBackpack.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconBackpack = new ImageIcon(imageBackpack);

		// Importation de l'image de l'engrenage
		ImageIcon iconEngrenage = new ImageIcon("images/engrenage.png");
		// Redimension de l'image du sac à dos
		Image imageEngrenage = iconEngrenage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		iconEngrenage = new ImageIcon(imageEngrenage);

		// Création du panel EST
		JPanel panelEst = new JPanel(new GridLayout(2,1));
		panelEst.setBackground(transparent);

		// Création du panel d'information sur le niveau de vie du personnage, son niveau et son nom
		JLabel vie = new JLabel("Niveau de vie :", JLabel.CENTER);
		JLabel name = new JLabel("Nom du héros : " + personnage.getNom(),JLabel.CENTER);
		level = new JLabel("Niveau : " + personnage.getNiveau(), JLabel.CENTER);

		//Changer la police
		name.setFont(police);
		vie.setFont(police);
		level.setFont(police);

		// BAR DE VIE
		bar = new JProgressBar();
		bar.setMaximum(InterfaceGenerale.personnage.getHpMax());
		bar.setMinimum(0);
		bar.setStringPainted(true);
		bar.setValue((int) personnage.getVie());

		JPanel panelInfos = new JPanel(new GridLayout(4,1));
		panelInfos.setBackground(transparent);
		panelInfos.add(name);
		panelInfos.add(level);
		panelInfos.add(vie);
		panelInfos.add(bar);
		panelInfos.setBackground(new Color(169, 138, 96));
		panelEst.add(panelInfos);

		// Creation du panel SUD
		panelSud = new JPanel();
		panelSud.setPreferredSize(new Dimension(800, 600));
		panelSud.setBackground(transparent);
		
		
		//Set les stats du personnage (en haut à droite)
		JPanel panelNord = new JPanel(new GridLayout(1,8));
		JMenuBar menup = new JMenuBar();
		//menup.setBackground(transparent);
		JMenu menu = new JMenu("Statistiques");
		menu.setBackground(transparent);
		adresse = new JMenuItem("Adresse : " + personnage.getAdresse());
		adresse.setBackground(transparent);
		intel = new JMenuItem("Intelligence : " + personnage.getIntelligence());
		intel.setBackground(transparent);
		force = new JMenuItem("Force : " + personnage.getForce());
		force.setBackground(transparent);
		menup.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JButton boutonParam = new JButton(iconEngrenage);
		boutonParam.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				parametreOn = !parametreOn;
				if (parametreOn) {
					stockWhileParametre = panelSud.getComponent(0);
					panelSud.remove(stockWhileParametre);
					panelSud.add(panelParametres, BorderLayout.SOUTH);
				} else {
					panelSud.remove(panelParametres);
					panelSud.add(stockWhileParametre, BorderLayout.SOUTH);
				}
				// actualisation de l'affichage
				actualiserFrame();
			}
		}); 
		menup.add(boutonParam);
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu.add(adresse);
		menu.add(force);
		menu.add(intel);
		menup.add(menu);

		JButton boutonActualiser = new JButton("Actualiser l'affichage de la page");
		boutonActualiser.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				actualiserFrame();
			}
		});  
		boutonActualiser.setBackground(transparent);

		menup.add(boutonActualiser);
		panelNord.add(menup);


		JPanel panelOuest = new JPanel(new GridLayout(2,1));
		panelOuest.setBackground(transparent);
		JButton boutonChangement = new JButton(iconBackpack);
		boutonChangement.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				if (!parametreOn) {
					inventaireOn = !inventaireOn;
					if (inventaireOn) {
						stockWhileInventaire = panelSud.getComponent(0);
						panelSud.remove(stockWhileInventaire);
						panelSud.add(panelInventaire, BorderLayout.SOUTH);
					} else {
						panelSud.remove(panelInventaire);
						panelSud.add(stockWhileInventaire, BorderLayout.SOUTH);
					}
					// actualisation de l'affichage
					actualiserFrame();
				}
			}
		});  
		boutonChangement.setPreferredSize(new Dimension(100, 50));
		boutonChangement.setBackground(transparent);

		panelOuest.add(boutonChangement);


		//Set fenêtre
		panelTotal = new ImagePanel(imageVillage);
		this.setBackgroundPanel(imageVillage);
		panelTotal.setBackground(transparent);
		panelTotal.setLayout(new BorderLayout());
		panelTotal.add(panelNord, BorderLayout.NORTH);
		panelTotal.add(panelEst, BorderLayout.EAST);
		panelTotal.add(panelSud, BorderLayout.SOUTH);
		panelTotal.add(panelOuest, BorderLayout.WEST);

		return panelTotal;
	}
	
	public InterfaceGenerale getInt() {
		return this;
	}
	
	public void actualiserFrame() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {

		}
		this.frame.setSize(999, 999);
		this.frame.setSize(1000, 1000);
	}

	public void actualiserStats() {
		adresse.setText("Adresse : " + personnage.getAdresse());
		intel.setText("Intelligence : " + personnage.getIntelligence());
		force.setText("Force : " + personnage.getForce());
		level.setText("Niveau : " + personnage.getNiveau());
		bar.setValue((int) personnage.getVie());
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public void setPanel(ImagePanel panel) {
		System.gc();
		if (panelSud.getComponents().length != 0) {
			panelSud.remove(panelSud.getComponent(0));
		}
		panelSud.add(panel, BorderLayout.SOUTH);
		this.actualiserFrame();
	}
	public InterfaceInventaire getInterfaceInv() {
		return this.interInventaire;
	}

	public void setBackgroundPanel(Image image) {
		this.panelTotal.image = image;
		this.panelTotal.repaint();
		actualiserFrame();
	}
}
