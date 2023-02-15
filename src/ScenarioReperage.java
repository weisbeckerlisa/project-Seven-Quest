import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioReperage {
	
	InterfaceGenerale intGene;
	Personnage perso;
	Image image;
	
	public ScenarioReperage (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		
		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/foret.png");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;

		intGene.setBackgroundPanel(imageFond);
		String texte1 = "<html><CENTER>Après quelques minutes de marche, vous arrivez dans la forêt.<br> Vous apercevez alors un Bandit sur votre chemin.<br> Sans hésitation, il vous saute dessus et débute un combat.</CENTER></html>";
		Choix choix = new Choix(texte1);
		demanderReponseChoix(choix);

		int statinit[] = {8, 7, 10};
		PersonnageBandit mechant = new PersonnageBandit("Bandit", 50, statinit, 4, 3, 100, 100);


		Combat combat = new Combat(perso,mechant, intGene);
		combat.combattre();
		if (perso.estEnVie()) {
			// Donner points d'experience après le combat
			this.perso.ajoutExp(10);
					
			//Equipement
			String texte2 = "<html><CENTER>Le grand druide du village voisin a laissé tomber <br>une fiole de son étrange potion. <br> Voulez-vous la prendre ? </CENTER></html>";
			Choix choix2 = new Choix(texte2, "Prendre la potion", "Passer son chemin");
			int reponse = demanderReponseChoix(choix2);
			if (reponse == 1) {
				int[] statsAjout = {3, 0, 3};
				int[] atqSpe = {3, 2};
				Equipement potion = new Equipement("Potion", 100, statsAjout, 1, atqSpe, 1, "main gauche", "images/epees/potion.png");
				perso.equiperEquipement(potion);
			}
			new ScenarioGrotte(intGene, perso);
		}
		Choix choixDece = new Choix("Vous êtes mort. Fin de la partie.", "Suivant");
		demanderReponseChoix(choixDece);
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}
