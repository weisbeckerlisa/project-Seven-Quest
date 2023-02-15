import java.util.Random;

public class PersonnageMagicien extends Personnage {

	private double mult1 = 1.5;
	private int cd1 = 1;
	private double mult2 = 3.0;
	private int cd2 = 3;
	private int cddb = 2;
	private int cdb = 1;
	private static int[] statMagicien = {1,6,5};

	/** Constructeur précis.
	 * @param hpinit hp initiaux
	 * @param attaqueinit attaque initiale
	 */
	public PersonnageMagicien (String nom, int hpInit, int[] statsInit, int niveauInit, int def, int tailleInv, int pdsMax) {
		super(nom, hpInit, statsInit, niveauInit, def, tailleInv, pdsMax,"images/magicien/magicien_dos.png", "images/magicien/magicien_face.png");
	}

	/** Constructeur de base pour début de partie.
	 * @Param String nom
	 */
	public PersonnageMagicien (String nom) {
		this(nom, 10, statMagicien, 1, 0, 11, 200);
	}

	@Override
	public String attaque1(Personnage ennemi) {
		Random des = new Random();
		int jet1 = des.nextInt(6)+1;
		int jet2 = des.nextInt(6)+1;
		int jet= jet1+jet2;
		this.AnimationLancer(jet1,jet2);
		double degats = 0;
		String texte;
		texte = "<html><CENTER>Vous lancez les dès <br> résultat : " + jet;
		
		if (jet == 2) {
			texte = texte + "<br>Echec critique !";
			degats = 0;
		} else if (jet>2 && jet<5) {
			texte = texte + "<br>Pas très efficace...";
			degats = ((stat[1]+buff[1]-debuff[1])*mult1)*0.5;
		} else if (jet>4 && jet<10) {
			texte = texte + "<br>Succès !";
			degats = (stat[1]+buff[1]-debuff[1])*mult1;
		} else if (jet>9 && jet<12) {
			texte = texte + "<br>Super efficace !";
			degats = ((stat[1]+buff[1]-debuff[1])*mult1)*1.5;
		} else if (jet == 12) {
			texte = texte + "<br>Coup critique !";
			degats = ((stat[1]+buff[1]-debuff[1])*mult1)*2.0;
		}
		
		if (degats < ennemi.getDefense()+ennemi.buff[3]-ennemi.debuff[3]) {
			texte = texte  + "<br>Votre attaque est bloquée !</CENTER></html>";
		} else {
			texte = texte + "<br>Vous infligez " + degats + " dégats à " + ennemi.getNom() + " !</CENTER></html>";
			ennemi.modifierVie(-degats);
		}
		this.setCooldown1();
		return texte;
	}

	@Override
	public String afficherDescriptionAttaque1() {
		return ("Attaque légère - Dégats : " + (stat[1]+buff[1]-debuff[1])*mult1 + " - Cooldown : " + cd1);
	}


	@Override
	public String attaque2(Personnage ennemi) {
		Random des = new Random();
		int jet1 = des.nextInt(6)+1;
		int jet2 = des.nextInt(6)+1;
		int jet= jet1+jet2;
		this.AnimationLancer(jet1,jet2);
		double degats = 0;
		String texte = "<html><CENTER>Vous lancez les dès, ";
		texte = texte + "<br>Résultat : " + jet;
		
		if (jet == 2) {
			texte = texte + "<br>Echec critique !";
			degats = 0;
		} else if (jet>2 && jet<5) {
			texte = texte + "<br>Pas très efficace...";
			degats = ((stat[1]+buff[1]-debuff[1])*mult2)*0.5;
		} else if (jet>4 && jet<10) {
			texte = texte + "<br>Succès !";
			degats = (stat[1]+buff[1]-debuff[1])*mult2;
		} else if (jet>9 && jet<12) {
			texte = texte + "<br>Super efficace !";
			degats = ((stat[1]+buff[1]-debuff[1])*mult2)*1.5;
		} else if (jet == 12) {
			texte = texte + "<br>Coup critique !";
			degats = ((stat[1]+buff[1]-debuff[1])*mult2)*2.0;
		}
		if (degats < ennemi.getDefense()+ennemi.buff[3]-ennemi.debuff[3]) {
			texte = texte  + "<br>Votre attaque est bloquée !</CENTER></html>";
		} else {
			texte = texte + "<br>Vous infligez " + degats + " dégats à " + ennemi.getNom() + " !</CENTER></html>";
			ennemi.modifierVie(-degats);
		}
		this.setCooldown2();
		return texte;
	}

	@Override
	public String afficherDescriptionAttaque2() {
		return ("Attaque lourde - Dégats : " + (stat[1]+buff[1]-debuff[1])*mult2 + " - Cooldown : " + cd2);
	}
	
	@Override
	public String attaqueSpe(Personnage ennemi) {
		this.setCooldownSpe();
		return this.equipementActuel[0].attaqueSpe(ennemi);
	}
	
	@Override
	public String attaqueDebuff(Personnage ennemi) {
		String texte = "<html><CENTER>La défense de " + ennemi.getNom() + " est réduite !";
		if ((ennemi.getDefense()+ennemi.buff[3])-(ennemi.debuff[3]+1)>0) {
			ennemi.debuff[3]++;
		} else {
			texte = texte + "<br>Mais cela n'a aucun effet: <br> la défense de " + ennemi.getNom() + " est minimale.";
		}
		this.setCooldownDebuff();
		return texte;
	}
	
	@Override
	public String attaqueBuff() {
		String texte = "Votre défense a augmenté !";
		this.buff[3]++;
		this.setCooldownBuff();
		return texte;
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
