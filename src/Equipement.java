import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Equipement {

	private String nom;
	private int durabiliteMax;
	private int durabilite;
	private int[] statsAjout = new int[Personnage.nbStat + 1]; // la dernière stat est la defense.
	private int[] attaqueSpe = new int[2]; // {multiplicateur, cooldown}
	private int poids;
	private String emplacement;
	private String pathImage;

	/** Constructeur complet.
	 * @param nom : int
	 * @param durabMax : int
	 * @param durab : int
	 * @param statsAjout : int[]
	 * @param atq : int
	 * @param def : int
	 * @param atqSpe : int[]
	 * @param pds : int
	 * @param emplacement : String
	 */
	public Equipement(String nom, int durabMax, int durab, int[] statsAjout,
			int def, int[] atqSpe, int pds, String emplacement, String pathImg) {
		this.nom = nom;
		this.durabiliteMax = durabMax;
		this.durabilite = durab;
		this.statsAjout[3] = def;
		for (int i = 0 ; i < statsAjout.length ; i++) {
			this.statsAjout[i] = statsAjout[i];
		}
		this.attaqueSpe = atqSpe; // {multiplicateur, cooldown}
		this.poids = pds;
		this.emplacement = emplacement;
		this.pathImage = pathImg;
	}

	/** Constructeur sans durabilité max, ici = durabilité.
	 * @param nom : string
	 * @param durab : int
	 * @param statsAjout : int[]
	 * @param atq : int
	 * @param def : int
	 * @param atqSpe : int[]
	 * @param pds : int
	 * @param emplacement : String
	 */
	public Equipement(String nom, int durab, int[] statsAjout, int def, int[] atqSpe, int pds, String emplacement, String pathImage) {
		this(nom, durab, durab, statsAjout, def, atqSpe, pds, emplacement, pathImage);
	}

	public String attaqueSpe(Personnage ennemi) {
		Random des = new Random();
		int jet1 = des.nextInt(6)+1;
		int jet2 = des.nextInt(6)+1;
		int jet= jet1+jet2;
		ennemi.AnimationLancer(jet1,jet2);
		double degats = 0;
		String texte = "<html><CENTER>Vous lancez les dés...<br>Résultat : " + jet;
		
		if (jet == 2) {
			texte = texte + "<br>Echec critique !";
			degats = 0;
		} else if (jet>2 && jet<5) {
			texte = texte + "<br>Pas très efficace...";
			degats = (this.statsAjout[0]*this.attaqueSpe[0])*0.5;
		} else if (jet>4 && jet<10) {
			texte = texte + "<br>Succès !";
			degats = (this.statsAjout[0]*this.attaqueSpe[0]);
		} else if (jet>9 && jet<12) {
			texte = texte + "<br>Super efficace !";
			degats = (this.statsAjout[0]*this.attaqueSpe[0])*1.5;
		} else if (jet == 12) {
			texte = texte + "<br>Coup critique !";
			degats = (this.statsAjout[0]*this.attaqueSpe[0])*2.0;
		}
		
		texte = texte + "<br>Vous infligez " + -degats + " dégats à " + ennemi.getNom() + " !</CENTER></html>";
		ennemi.modifierVie(-degats);

		return texte;
	}

	public String afficherDescriptionAttaqueSpe() {
		return ("Attaque spéciale de l'équipement : " + this.getNom() + " - Dégats : " + this.statsAjout[0]*this.attaqueSpe[0] + " - Cooldown : " + this.attaqueSpe[1]);
	}

	/** Obtenir les stats de l'attaque spéciale.
	 * @return attaqueSpe
	 */
	public int[] getAttaqueSpe() {
		return this.attaqueSpe;
	}

	/** Obtenir le nom de l'équipement.
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** Obtenir la durabilité max de l'équipement.
	 * @return durabiliteMax
	 */
	public int getDurabiliteMax() {
		return this.durabiliteMax;
	}

	/** Obtenir la durabilité de l'équipement.
	 * @return durabilite
	 */
	public int getDurabilite() {
		return this.durabilite;
	}

	/** Obtenir les stats qu'ajoute de l'équipement.
	 * @return statsAjout
	 */
	public int[] getStatsAjout() {
		return this.statsAjout;
	}

	/** Obtenir la défense de l'équipement.
	 * @return defense
	 */
	public int getDefense() {
		return this.statsAjout[3];
	}

	/** Obtenir le poids de l'équipement.
	 * @return poids
	 */
	public int getPoids() {
		return this.poids;
	}

	/** Obtenir l'emplacement de l'équipement.
	 * @return emplacement
	 */
	public String getEmplacement() {
		return this.emplacement;
	}

	/** Afficher un équipement avec son nom et ses stats.
	 * @param eq
	 */
	public void afficherEquipement() {
		System.out.println(this.nom);
		for (int k = 0 ; k < Personnage.nbStat ; k++) {
			System.out.println("\t\t" + Personnage.statNom[k] + " : (+" + this.statsAjout[k] + ")");
		}
		System.out.println("\t\tDEFENSE : (+" + this.statsAjout[3] + ")");

		System.out.print("ATTAQUE SPE : ");
		if (this.attaqueSpe[0] == 0 & this.attaqueSpe[1] == 0) {
			System.out.println("\taucune attaque spéciale.");
		} else {
			System.out.println("\tPUISSANCE : " + this.attaqueSpe[0]);
			System.out.println("\t\tCOOLDOWN : " + this.attaqueSpe[1]);
		}
	}

	public ImageIcon getIcon(int dimx, int dimy) {
			// Importation de l'image de l'équipement
			ImageIcon iconEquipement = new ImageIcon(this.pathImage);
			// Redimension de l'image de l'équipement
			Image imageEquipement = iconEquipement.getImage().getScaledInstance(dimx, dimy, Image.SCALE_SMOOTH);
			return (new ImageIcon(imageEquipement));
	}
}
