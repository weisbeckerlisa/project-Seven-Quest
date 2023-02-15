import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioForet {
	
	InterfaceGenerale intGene;
	Personnage perso;
	Image image;

	public ScenarioForet (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		

		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/foret_sombre.png");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
		this.image = imageFond;
		
		intGene.setBackgroundPanel(imageFond);
		String texte1 = "<html><CENTER>Après quelques minutes de marche, vous arrivez dans la forêt. <br>Vous apercevez alors un Bandit sur votre chemin. <br> Sans hésitation, il vous saute dessus et débute un combat.</CENTER></html>";
		Choix choix1 = new Choix(texte1, "Démarrer le combat");
		demanderReponseChoix(choix1);
		PersonnageBandit mechant = new PersonnageBandit("Bandit");
		Combat combat = new Combat(perso,mechant, intGene);
		combat.combattre();
		intGene.actualiserStats();
		if (perso.estEnVie()) {
			// Donner points d'experience après le combat
			this.perso.ajoutExp(10);
			
			//Equipement
			String texte2 = "<html><CENTER>En repartant de votre combat, vous trouvez par terre <br> une épée de niveau 1 !<br> Vous la rajoutée tout de suite dans votre inventaire.</CENTER></html>";
			Choix choix2 = new Choix(texte2, "Prendre l'épée");
			demanderReponseChoix(choix2);
			int[] statsAjout = {1, 2, 1};
			int[] atqSpe = {3, 2};
			Equipement epee = new Equipement("Epee niveau1", 100, statsAjout, 1, atqSpe, 1, "sac", "images/epees/epeePierre.png");
			perso.equiperEquipement(epee);
			
			//Actualiser vie perso après combat
			
			
			String texte1_1 = "<html><CENTER>Après avoir vaincu votre ennemi, vous prenez le temps<br> de regarder autour de vous. <br>Le chemin qui s'offre à vous se divise en deux.</CENTER></html>";
			Choix choix1_1 = new Choix(texte1_1,"<html><CENTER>Une route qui s'enfonce dans la forêt qui devient si dense <br> que la lumière a du mal à pénétrer</CENTER></html>","Une route qui emmène vers une colline" );
			int reponse1_1 = demanderReponseChoix(choix1_1);
			if (reponse1_1 == 1) {
				new ScenarioForet2(intGene,perso);
			} else {
				new ScenarioColline(intGene,perso);
			}
		} else {
			Choix choixDece = new Choix("Vous êtes mort. Fin de la partie.", "Suivant");
			demanderReponseChoix(choixDece);
		}		
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}
