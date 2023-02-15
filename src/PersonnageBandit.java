import java.util.Random;

public class PersonnageBandit extends Personnage {

	private double mult1 = 1; // multiplicateur attaque 1
	private int cd1 = 0; // cooldown attaque 1
	private double mult2 = 1.5; // multiplicateur attaque 2
	private int cd2 = 3; // cooldown attaque 2
	private int cddb = 3; // cooldown debuff
	private int cdb = 3; // cooldown buff
	private static int[] statBandit = {2,3,2};
	
	/**
	 * Constructeur précis.
	 * 
	 * @param hpinit      hp initiaux
	 * @param attaqueinit attaque initiale
	 */
	public PersonnageBandit(String nom, int hpInit, int[] statsInit, int niveauInit,
			int def, int tailleInv, int pdsMax) {
		super(nom, hpInit, statsInit, niveauInit, def, tailleInv, pdsMax, "", "images/guerrier/guerrier_face.png");
		this.nom = nom;
		this.enVie = true;
		this.hpMax = hpInit;
		this.hp = this.hpMax;
		this.stat = statsInit;
		this.experience = 0;
		this.niveau = niveauInit;
		this.defense = def;
	}

	/**
	 * Constructeur de base pour début de partie.
	 */
	public PersonnageBandit(String nom) {
		this(nom, 7, statBandit, 1, 0, 10, 200);
	}

	@Override
	public String attaque1(Personnage ennemi) {
		Random des = new Random();
		int jet = des.nextInt(20)+1;
		double degats = 0;
		String texte = "<html><CENTER>" + this.getNom() + " lance les dès,";
		texte = texte + "<br>Résultat : " + jet;
		
		if (jet == 1) {
			texte = texte + "<br>Echec critique !";
			degats = 0;
		} else if (jet>1 && jet<7) {
			texte = texte + "<br>Pas très efficace...";
			degats = ((stat[0]+buff[0]-debuff[0])*mult1)*0.5;
		} else if (jet>6 && jet<15) {
			texte = texte + "<br>Succès !";
			degats = (stat[0]+buff[0]-debuff[0])*mult1;
		} else if (jet>14 && jet<20) {
			texte = texte + "<br>Super efficace !";
			degats = ((stat[0]+buff[0]-debuff[0])*mult1)*1.5;
		} else if (jet == 20) {
			texte = texte + "<br>Coup critique !";
			degats = ((stat[0]+buff[0]-debuff[0])*mult1)*2.0;
		}
		if (degats < ennemi.getDefense()+ennemi.buff[3]-ennemi.debuff[3]) {
			texte = texte  + "<br>Vous bloquez l'attaque de" + this.getNom()+ " !</CENTER></html>";
		} else {
			texte = texte + "<br>" + this.nom + " vous inflige "
					+ degats + " dégats.</CENTER></html>";
			ennemi.modifierVie(-degats);
		}
		this.setCooldown1();
		return texte;
	}

	@Override
	public String afficherDescriptionAttaque1() {
		return ("Coup de bâton léger - Dégats : "
				+ (stat[0] + buff[0] - debuff[0]) * mult1 + " - Cooldown : " + cd1);
	}

	@Override
	public String attaque2(Personnage ennemi) {
		Random des = new Random();
		int jet = des.nextInt(20)+1;
		double degats = 0;
		String texte = "<html><CENTER>" + this.getNom() + " lance les dès,";
		texte = texte + "<br>Résultat : " + jet;
		
		if (jet == 1) {
			texte = texte + "<br>Echec critique !";
			degats = 0;
		} else if (jet>1 && jet<7) {
			texte = texte + "<br>Pas très efficace...";
			degats = ((stat[0]+buff[0]-debuff[0])*mult2)*0.5;
		} else if (jet>6 && jet<15) {
			texte = texte + "<br>Succès !";
			degats = (stat[0]+buff[0]-debuff[0])*mult2;
		} else if (jet>14 && jet<20) {
			texte = texte + "<br>Super efficace !";
			degats = ((stat[0]+buff[0]-debuff[0])*mult2)*1.5;
		} else if (jet == 20) {
			texte = texte + "<br>Coup critique !";
			degats = ((stat[0]+buff[0]-debuff[0])*mult2)*2.0;
		}
		if (degats < ennemi.getDefense()+ennemi.buff[3]-ennemi.debuff[3]) {
			texte = texte  + "<br>Vous bloquez l'attaque de" + this.getNom()+ " !</CENTER></html>";
		} else {
			texte = texte + "<br>" + this.nom + " vous inflige "
					+ degats + " dégats.";
			ennemi.modifierVie(-degats);
		}
		this.setCooldown2();
		return texte;
	}

	@Override
	public String attaqueSpe(Personnage ennemi) {
		this.setCooldownSpe();
		return this.equipementActuel[0].attaqueSpe(ennemi);
	}

	@Override
	public String attaqueDebuff(Personnage ennemi) {
		String texte = "Votre attaque est réduite !";
		if (ennemi.stat[0]-(debuff[0]+1)>0) {
			ennemi.debuff[0]++;
		}
		this.setCooldownDebuff();
		return texte;
	}

	@Override
	public String attaqueBuff() {
		String texte = "L'attaque de " + this.getNom() + " a augmenté !";
		this.buff[0]++;
		this.setCooldownBuff();
		return texte;
	}

	@Override
	public String afficherDescriptionAttaque2() {
		return ("Coup de bâton lourd - Dégats : "
				+ (stat[0] + buff[0] - debuff[0]) * mult2 + " - Cooldown : " + cd2);
	}

	@Override
	public void setCooldown1() {
		this.combatCooldown1 = cd1;
	}

	@Override
	public void setCooldown2() {
		this.combatCooldown2 = cd2;
	}

	@Override
	public void setCooldownSpe() {
		this.combatCooldownSpe = this.equipementActuel[0].getAttaqueSpe()[1];
	}

	@Override
	public void setCooldownDebuff() {
		this.combatCooldownDebuff = cddb;
	}

	@Override
	public void setCooldownBuff() {
		this.combatCooldownBuff = cdb;
	}

}
