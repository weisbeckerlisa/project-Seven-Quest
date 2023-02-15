import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioForet2 {
	
	InterfaceGenerale intGene;
	Personnage perso;
	Image image;
	
	public ScenarioForet2 (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		

		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/foret.png");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;

		intGene.setBackgroundPanel(imageFond);
		String texte1 = "<html><CENTER>Après quelques minutes de marche, vous arrivez dans la forêt. <br> Vous apercevez alors un Bandit sur votre chemin. <br>Sans hésitation, il vous saute dessus et débute un combat.</CENTER></html>";
		Choix choix1 = new Choix(texte1, "Démarrer le combat");
		demanderReponseChoix(choix1);

		int statinit[] = {6, 9, 1};
		PersonnageBandit mechant = new PersonnageBandit("Tarzan", 30, statinit, 3, 3, 100, 100);
		Combat combat = new Combat(perso,mechant, intGene);
		combat.combattre();
		if (perso.estEnVie()) {
			// Donner points d'experience après le combat
			this.perso.ajoutExp(20);
					
			//Equipement
			String texte2 = "<html><CENTER>Coup de chance ! Vous trouvez une nouvelle arme cachée sous un tronc : <br> une nouvelle épée. </CENTER></html>";
			Choix choix2 = new Choix(texte2, "Prendre l'épée");
			demanderReponseChoix(choix2);
			int[] statsAjout = {2, 2, 2};
			int[] atqSpe = {3, 3};
			Equipement epee = new Equipement("Epee niveau2", 100, statsAjout, 1, atqSpe, 1, "sac", "images/epees/epeeSombre.png");
			perso.equiperEquipement(epee);
			
			String texte1_1 = "<html><CENTER>Après une vingtaine de minutes de marche,<br> vous apercevez l'entrée d'une grotte <br> Vous apercevez également un bout de tissu semblale à la robe <br> que vous avez décrit le chef du village</CENTER></html>";
			Choix choix1_1 = new Choix(texte1_1,"Rentrer dans la grotte","Explorer les alentours" );
			int reponse1_1 = demanderReponseChoix(choix1_1);
			if (reponse1_1 == 1) {
				new ScenarioGrotte(intGene,perso);
			} else {
				new ScenarioReperage(intGene,perso);
			}
		}else {
			Choix choixDece = new Choix("Vous êtes mort. Fin de la partie.", "Suivant");
			demanderReponseChoix(choixDece);
		}
	}
	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}

