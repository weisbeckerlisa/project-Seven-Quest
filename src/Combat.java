import java.util.Random;
import java.util.Scanner;

public class Combat {

	private Personnage joueur;
	private Personnage ennemi;
	private InterfaceCombat intCombat;

	static Scanner sc = new Scanner(System.in);

	public Combat(Personnage joueur1, Personnage pnj, InterfaceGenerale intGene) {
		this.joueur = joueur1;
		this.ennemi = pnj;
		this.intCombat = new InterfaceCombat(intGene, this);
		ImagePanel panel = intCombat.creerPanelCombat();
		intGene.setPanel(panel);
	}
	

	public void combattre() {
		Random generateur = new Random();

		int priorite = generateur.nextInt(2); // Decide de façon aléatoire qui commence à
												// attaquer

		boolean joueurTour = true;
		Personnage attaquant;
		Personnage defenseur;

		intCombat.actualiserStatistiques();
		intCombat.attendreSuivant();

		if (priorite == 1) {
			joueurTour = false;
			intCombat.setTexte(this.ennemi.getNom() + " prend l'initiative.");
		} else {
			intCombat.setTexte("Vous prennez l'initiative.");
		}
		
		intCombat.actualiserStatistiques();
		intCombat.attendreSuivant();

		int chargeJoueur = 0;
		int chargeEnnemi = 0;
		boolean valide = false;
		int choix = 0;

		while (this.joueur.estEnVie() && this.ennemi.estEnVie()) {
			valide = false;
			while (!valide) {
				if (joueurTour) {
					attaquant = this.joueur;
					defenseur = this.ennemi;
					if (chargeJoueur == 1) {
						intCombat.setTexte(attaquant.attaque2(ennemi));
						attaquant.reduireCooldown1();
						--chargeJoueur;
						valide = true;
						intCombat.actualiserStatistiques();
						intCombat.attendreSuivant();
					} else {
						intCombat.setTexte("Que voulez-vous faire ?\n");
						/*if (this.joueur.equipementActuel[1] != null) {
							System.out.println("3 - Attaque spéciale ("
									+ this.joueur.equipementActuel[1]
											.getAttaqueSpe()[1]
									+ " tours de charge)");
						}*/
						choix = intCombat.attendreReponseAttaque();
					}
				} else {
					attaquant = this.ennemi;
					defenseur = this.joueur;

					if (chargeEnnemi == 1) {
						intCombat.setTexte(
								this.ennemi.getNom() + " utilise une attaque lourde.");
						intCombat.attendreSuivant();
						intCombat.setTexte(this.ennemi.attaque2(this.joueur));
						this.ennemi.reduireCooldown1();
						--chargeEnnemi;
						valide = true;
						intCombat.actualiserStatistiques();
						intCombat.attendreSuivant();
					} else {
						int hazard = generateur.nextInt(20);
						if (hazard == 0) {
							choix = 0;
						} else if (hazard>0 && hazard<7) {
							choix = 1;
						} else if (hazard>6 && hazard<11) {
							choix = 2;
						} else if (hazard>10 && hazard<14) {
							choix = 3;
						} else if (hazard>13 && hazard<17) {
							choix = 4;
						} else if (hazard>16 && hazard<20) {
							choix = 5;
						}
						choix = generateur.nextInt(6);
					}
				}
				if (!valide) {
					switch (choix) {
					case 0:
						intCombat.setTexte(attaquant.getNom() + " ne fait rien.");
						attaquant.reduireCooldown1();
						attaquant.reduireCooldown2();
						attaquant.reduireCooldownSpe();
						attaquant.reduireCooldownBuff();
						attaquant.reduireCooldownDebuff();
						valide = true;
						intCombat.actualiserStatistiques();
						intCombat.attendreSuivant();
						break;
					case 1:
						if (attaquant.enCooldown1()) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
									"Impossible, cette attaque est en cooldown.");
								intCombat.attendreSuivant();
							}
							
						} else {
							intCombat.setTexte(
									attaquant.getNom() + " utilise une attaque légère");
							intCombat.attendreSuivant();
							intCombat.setTexte(attaquant.attaque1(defenseur));
							attaquant.reduireCooldown2();
							attaquant.reduireCooldownSpe();
							attaquant.reduireCooldownBuff();
							attaquant.reduireCooldownDebuff();
							valide = true;
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;

					case 2:
						if (attaquant.enCooldown2()) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
									"Impossible, cette attaque est en cooldown.");
								intCombat.attendreSuivant();
							}
						} else {
							intCombat.setTexte(
									attaquant.getNom() + " prépare une attaque lourde");
							if (joueurTour) {
								chargeJoueur = 1;
							} else {
								chargeEnnemi = 1;
							}
							attaquant.reduireCooldown1();
							attaquant.reduireCooldownSpe();
							attaquant.reduireCooldownBuff();
							attaquant.reduireCooldownDebuff();
							valide = true;
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;

					case 3:
						if (attaquant.enCooldownSpe()) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
									"Impossible, cette attaque est en cooldown.");
								intCombat.actualiserStatistiques();
								intCombat.attendreSuivant();
							}
						} else if (attaquant.equipementActuel[0] == null) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
										"Vous ne possédez pas d'équipement spécial.");
								intCombat.actualiserStatistiques();		
								intCombat.attendreSuivant();
							}
						} else {
							intCombat.setTexte(attaquant.getNom()
									+ " utilise une attaque spéciale !");
							intCombat.attendreSuivant();
							intCombat.setTexte(attaquant.attaqueSpe(defenseur));
							attaquant.reduireCooldown2();
							attaquant.reduireCooldown1();
							attaquant.reduireCooldownBuff();
							attaquant.reduireCooldownDebuff();
							valide = true;
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;
					case 4:
						if (attaquant.enCooldownDebuff()) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
									"Impossible, cette attaque est en cooldown.");
								intCombat.actualiserStatistiques();
								intCombat.attendreSuivant();
							}
						} else {
							intCombat.setTexte(attaquant.getNom()
									+ " réduit les stats de son adversaire. ");
							intCombat.attendreSuivant();
							intCombat.setTexte(attaquant.attaqueDebuff(defenseur));
							attaquant.reduireCooldown2();
							attaquant.reduireCooldown1();
							attaquant.reduireCooldownBuff();
							attaquant.reduireCooldownSpe();
							valide = true;
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;
					case 5:
						if (attaquant.enCooldownBuff()) {
							if (attaquant == this.joueur) {
								intCombat.setTexte(
									"Impossible, cette attaque est en cooldown.");
								intCombat.actualiserStatistiques();
								intCombat.attendreSuivant();	
							}
						} else {
							intCombat.setTexte(
									attaquant.getNom() + " augmente ses stats. ");
							intCombat.attendreSuivant();
							intCombat.setTexte(attaquant.attaqueBuff());
							attaquant.reduireCooldown2();
							attaquant.reduireCooldown1();
							attaquant.reduireCooldownDebuff();
							attaquant.reduireCooldownSpe();
							valide = true;
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;
					default:
						if (attaquant == this.joueur) {
							intCombat.setTexte(
									"Veuillez choisir une attaque valide (entre 0 et 10).");
							intCombat.actualiserStatistiques();
							intCombat.attendreSuivant();
						}
						break;
					}
				}
			}
			joueurTour = !joueurTour;
		}
		this.joueur.reinitialiserBuffDebuff();
		this.ennemi.reinitialiserBuffDebuff();
		if (this.joueur.estEnVie()) {
			intCombat.setTexte(this.joueur.getNom() + " sort victorieux du combat");
			intCombat.actualiserStatistiques();
			intCombat.attendreSuivant();
		}
		
	}

	public Personnage getJoueur() {
		return this.joueur;
	}

	public Personnage getEnnemi() {
		return this.ennemi;
	}

}
