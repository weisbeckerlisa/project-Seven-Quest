import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioMaisonChef {
	
	InterfaceGenerale intGene;
	Personnage perso;
	Image image; 
	
	public ScenarioMaisonChef (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		
		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/village.jpg");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;
		intGene.setBackgroundPanel(imageFond);
		
		String texte1 = "<html><CENTER>Chef du village :<br> Aventurier, je suis heureux d'enfin vous rencontrer. <br> Malheuresement, je n'ai pas le temps pour les politesses.<br> Ma fille Gwen a disparu depuis hier soir, <br> elle était partie chercher du bois mais n'est jamais revenue...<br>Pouvez-vous m'aider ?</CENTER></html>";
		Choix choix1 = new Choix(texte1, "Oui bien sûr !", "<html><CENTER>Je n'ai pas le temps pour ces broutilles.<br>Je préfère aller pêcher.</CENTER></html>");
		int reponse1 = demanderReponseChoix(choix1);
		if (reponse1 == 1) {
			String texte1_1 = "<html><CENTER>Vous partez en direction de la forêt <br>pour aller chercher la fille du chef disparue.</CENTER></html>";
			Choix choix1_1 = new Choix(texte1_1,"S'aventurer dans la fôret");
			demanderReponseChoix(choix1_1);
			new ScenarioForet(intGene, personnage);
		} else {
			String texte1_2 = "Vous partez pêcher sur votre barque. Merci d'avoir joué !";
			Choix choix1_2 = new Choix(texte1_2, "Quitter");
			demanderReponseChoix(choix1_2);
		}
		
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}
