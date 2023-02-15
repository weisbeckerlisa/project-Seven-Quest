import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Image;
public abstract class Personnage {

	protected String nom;
	protected boolean enVie;
	protected int hpMax;
	protected double hp;
	protected int[] stat = new int[3]; // force, intelligence, adresse
	protected static final String[] statNom = { "FORCE", "INTELLIGENCE", "ADRESSE" };
	protected static final int nbStat = 3;
	protected final int expMax = 10;
	protected int experience;
	protected int niveau;
	protected int combatCooldown1;
	protected int combatCooldown2;
	protected int combatCooldownSpe;
	protected int combatCooldownDebuff;
	protected int combatCooldownBuff;
	protected int[] debuff;
	protected int[] buff;
	protected int defense;
	protected int tailleInventaire;
	protected int emplacementsLibres;
	public Equipement[] inventaire; // A MODIFIER
	protected int poidsMax;
	protected int poidsTotal;
	protected InterfaceGenerale intGene;
	public Equipement[] equipementActuel = new Equipement[4]; // Main D, Main G,
																	// Casque, Armure
	protected static final String[] emplacementNom = { "main droite", "main gauche",
			"casque", "armure" };
	public String cheminImageDos;
	public String cheminImageFace;
	private Image imageDos; 
	private Image imageFace;
	
	static Scanner sc = new Scanner(System.in);


	/**
	 * Constructeur précis.
	 * 
	 * @param hpinit      hp initiaux
	 * @param attaqueinit attaque initiale
	 */
	public Personnage(String nom, int hpInit, int[] statsInit, int niveauInit, int def,
			int tailleInv, int pdsMax, String cheminDos, String cheminFace) {
		this.nom = nom;
		this.enVie = true;
		this.hpMax = hpInit;
		this.hp = this.hpMax;
		this.stat = statsInit;
		this.experience = 0;
		this.niveau = niveauInit;
		this.defense = def;
		this.tailleInventaire = tailleInv;
		this.emplacementsLibres = tailleInv;
		this.inventaire = new Equipement[tailleInv];
		this.poidsMax = pdsMax;
		this.poidsTotal = 0;
		this.buff = new int[] { 0, 0, 0, 0 };
		this.debuff = new int[] { 0, 0, 0, 0 };
		this.cheminImageDos = cheminDos;
		this.cheminImageFace = cheminFace;
	}

	/**
	 * Modifier la vie du personnage. hpAjout > 0 pour du soin hpAjout < 0 pour des
	 * dégats
	 * 
	 * @param int hpAjout
	 */
	public void modifierVie(double hpAjout) {
		if (this.hp + hpAjout > this.hpMax) {
			this.hp = this.hpMax;
		} else if (this.hp + hpAjout <= 0) {
			this.personnageDecede();
		} else {
			this.hp = this.hp + hpAjout;
		}
	}
	
	/**
	 * Obtenir l'image de dos du personnage.
	 * 
	 * @return Image image de dos du personnage
	 */
	public Image getImageDos() {
		// Importation de l'image 
		ImageIcon iconImageDos = new ImageIcon(this.cheminImageDos);
		// Redimension de l'image 
		Image imageDos = iconImageDos.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		return imageDos;
	}
	
	/**
	 * Obtenir l'image de face du personnage.
	 * 
	 * @return Image image de face du personnage
	 */
	public Image getImageFace() {
		// Importation de l'image 
		ImageIcon iconImageFace = new ImageIcon(this.cheminImageFace);
		// Redimension de l'image 
		Image imageFace = iconImageFace.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		return imageFace;
	}
	
	/**
	 * Obtenir l'attaque du personnage.
	 * 
	 * @return int vie du perso
	 */
	public int getForce() {
		return this.stat[0] + statEquipements(0);
	}

	/**
	 * Obtenir les points de vie du personnage.
	 * 
	 * @return
	 */
	public double getVie() {
		return this.hp;
	}

	public void addHpMax(int add) {
		this.hpMax += add;
		this.hp = this.hpMax;
	}

	/**
	 * Obtenir la vie maximum du personnage.
	 * @return
	 */
	public int getHpMax() {
		return this.hpMax;
	}

	/**
	 * Obtenir le nom du personnage.
	 * 
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Obtenir l'intelligence du personnage.
	 * 
	 * @return
	 */
	public int getIntelligence() {
		return this.stat[1] + statEquipements(1);
	}

	public void addIntelligence(int add) {
		this.stat[1] += add;
	}

	public void addForce(int add) {
		this.stat[0] += add;
	}

	public void addAdresse(int add) {
		this.stat[2] += add;
	}

	/**
	 * Obtenir l'adresse du personnage.
	 * 
	 * @return
	 */
	public int getAdresse() {
		return this.stat[2] + statEquipements(2);
	}

	/**
	 * Obtenir l'expérience du personnage.
	 * 
	 * @return
	 */
	public int getExp() {
		return this.experience;
	}

	/**
	 * Obtenir le niveau du personnage.
	 * 
	 * @return
	 */
	public int getNiveau() {
		return this.niveau;
	}

	/**
	 * Savoir si le personnage est en vie.
	 * 
	 * @return boolean enVie
	 */
	public boolean estEnVie() {
		return this.hp > 0;
	}

	/**
	 * Obtenir la défense du personnage.
	 * 
	 * @return int defense
	 */
	public int getDefense() {
		return this.defense + statEquipements(3);
	}

	/**
	 * Ajouter de l'expérience.
	 * 
	 * @param int exp
	 */
	public void ajoutExp(int exp) {
		if (this.experience + exp >= this.expMax * this.niveau) {
			this.niveauPlusUn();
			this.ajoutExp(exp - (this.expMax * (this.niveau - 1)));
		} else {
			this.experience += exp;
		}
	}

	/**
	 * Ajoute un niveau et fait évoluer les stats.
	 * 
	 */
	private void niveauPlusUn() {
		this.hpMax += 10;
		this.hp = this.hpMax;
		this.niveau++;
		this.intGene.actualiserStats();
		int ptCompetenceAjouter = this.niveau;
		
		InterfaceLevelUp intLvlUp = new InterfaceLevelUp(this.intGene);
		intLvlUp.panelLevelUp(ptCompetenceAjouter);
	}

	/**
	 * Obtenir la totalité du boost d'une stat grâce aux équipements.
	 * 
	 * @param stat
	 * @return statPlusEquip
	 */
	private int statEquipements(int stat) {
		int statPlusEquip = 0;
		for (int k = 0; k < Personnage.nbStat; k++) {
			if (this.equipementActuel[k] != null) {
				statPlusEquip += this.equipementActuel[k].getStatsAjout()[stat];
			}
		}
		return statPlusEquip;
	}

	/**
	 * Envoie un message pour prévenir de la mort du personnage.
	 */
	private void personnageDecede() {
		this.enVie = false;
		this.hp = 0;
	}

	/**
	 * Equiper un équipement si il n'est pas trop lourd.
	 * 
	 * @param eq
	 */
	public void equiperEquipement(Equipement eq) {
		boolean trouve = false;
		int i = 0;
		while (!trouve && i < this.tailleInventaire) {
			if (this.inventaire[i] == null) {
				this.inventaire[i] = eq;
				trouve = true;
			} 
			i++;
		}
		if (i == tailleInventaire) {
			System.out.println("Vous ne pouvez pas porter cela en plus.");
		}
		if ((this.intGene != null) && (this.intGene.getInterfaceInv() != null)) {
			this.intGene.getInterfaceInv().actualiserInventaire();
		}

	}

	/**
	 * Afficher l'inventaire du personnage.
	 */
	public void afficherInventaire() {
		System.out.println("Inventaire de " + this.nom + " : ");
		for (int i = 0; i < this.tailleInventaire; i++) {
			System.out.print("Emplacement " + (i + 1) + " : ");
			if (this.inventaire[i] != null) {
				this.inventaire[i].afficherEquipement();
			} else {
				System.out.println("VIDE");
			}
		}
		System.out.println();
	}

	/** Jeter un equipement.
	 * @param emplacement
	 */
	public void jeterEquipement(int emplacement) {
		if (emplacement < 4) {
			this.equipementActuel[emplacement] = null;
		} else {
			this.inventaire[emplacement - 4] = null;
		}
	}

	/** Echanger deux équipements dans l'inventaire et ce que porte le personnage.
	 * Si un des emplacement est inférieur à 4, alors il s'agit des objets équipés.
	 * Sinon il s'agit des objets dans l'inventaire.
	 * @param emplacement1
	 * @param emplacement2
	 * @return boolean true si fait, false sinon
	 */
	public boolean echangeEquipement(int emplacement1, int emplacement2) {
		if (emplacement1 < 4 & emplacement2 < 4) {
			if (convient(this.equipementActuel[emplacement1], emplacement2) && convient(this.equipementActuel[emplacement2], emplacement1)) {
				Equipement temp = this.equipementActuel[emplacement1];
				this.equipementActuel[emplacement1] = this.equipementActuel[emplacement2];
				this.equipementActuel[emplacement2] = temp;
				return(true);
			} else {
				System.out.println("Déplacement impossible à réaliser.");
				return(false);
			}
		} else if ((emplacement1 < 4 & emplacement2 >= 4) | (emplacement1 >= 4 & emplacement2 < 4)) {
			if (emplacement1 > emplacement2) {
				int tempInt = emplacement1;
				emplacement1 = emplacement2;
				emplacement2 = tempInt;
			}
			if (convient(this.inventaire[emplacement2 - 4], emplacement1)) {
				Equipement temp = this.equipementActuel[emplacement1];
				this.equipementActuel[emplacement1] = this.inventaire[emplacement2 - 4];
				this.inventaire[emplacement2 - 4] = temp;
				return(true);
			} else {
				System.out.println("Déplacement impossible à réaliser.");
				return(false);
			}
		} else {
			Equipement temp = this.inventaire[emplacement1 - 4];
			this.inventaire[emplacement1 - 4] = this.inventaire[emplacement2 - 4];
			this.inventaire[emplacement2 - 4] = temp;
			return(true);
		}
	}

	private boolean convient(Equipement eq, int emplacement) {
		int i;
		if (eq == null) {
			return true;
		} else {
			switch (eq.getEmplacement()) {
			case "main droite":
				i = 0;
				break;
			case "main gauche":
				i = 1;
				break;
			case "casque":
				i = 2;
				break;
			case "armure":
				i = 3;
				break;
			default:
				i = 0;
			}
			if (i == emplacement) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void reinitialiserBuffDebuff() {
		for (int i = 0; i < 3; i++) {
			this.buff[i] = 0;
			this.debuff[i] = 0;
		}
	}

	/**
	 * Attaquer l'ennemi avec l'attaque 1.
	 * 
	 * @param ennemi
	 */
	abstract String attaque1(Personnage ennemi);

	/**
	 * Afficher la décription de l'attaque 1.
	 */
	abstract String afficherDescriptionAttaque1();

	/**
	 * Attaquer l'ennemi avec l'attaque 2.
	 * 
	 * @param ennemi
	 */
	abstract String attaque2(Personnage ennemi);

	/**
	 * Afficher la décription de l'attaque 2.
	 */
	abstract String afficherDescriptionAttaque2();

	/**
	 * Attaquer l'ennemi avec l'attaque spéciale.
	 * 
	 * @param ennemi
	 */
	abstract String attaqueSpe(Personnage ennemi);

	/**
	 * Afficher la décription de l'attaque spéciale de l'arme équipée.
	 */
	public String afficherDescriptionAttaqueSpe() {
		return this.equipementActuel[1].afficherDescriptionAttaqueSpe();
	}

	abstract String attaqueDebuff(Personnage ennemi);

	abstract String attaqueBuff();

	public boolean enCooldown2() {
		return this.combatCooldown2 != 0;
	}

	public boolean enCooldown1() {
		return this.combatCooldown1 != 0;
	}

	public boolean enCooldownSpe() {
		return this.combatCooldownSpe != 0;
	}

	public boolean enCooldownDebuff() {
		return this.combatCooldownDebuff != 0;
	}

	public boolean enCooldownBuff() {
		return this.combatCooldownBuff != 0;
	}

	abstract void setCooldown1();

	abstract void setCooldown2();

	abstract void setCooldownSpe();

	abstract void setCooldownDebuff();

	abstract void setCooldownBuff();

	public void reduireCooldown1() {
		if (this.combatCooldown1 > 0) {
			this.combatCooldown1--;
		}
	}

	public void reduireCooldown2() {
		if (this.combatCooldown2 > 0) {
			this.combatCooldown2--;
		}
	}

	public void reduireCooldownSpe() {
		if (this.combatCooldownSpe > 0) {
			this.combatCooldownSpe--;
		}
	}

	public void reduireCooldownDebuff() {
		if (this.combatCooldownDebuff > 0) {
			this.combatCooldownDebuff--;
		}
	}

	public void reduireCooldownBuff() {
		if (this.combatCooldownBuff > 0) {
			this.combatCooldownBuff--;
		}
	}

	public void setIntGene(InterfaceGenerale intGene) {
		this.intGene = intGene;
	}
	
	public void AnimationLancer(int a,int b) {
		
		JFrame frame = Jouer.frame;
		
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
