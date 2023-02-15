import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioDebut {
	
	static final String nomVille = "Laelith";
	InterfaceGenerale intGene;
	Personnage perso;
	Image image;
	
	public ScenarioDebut (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		
		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/village.jpg");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;
		
		intGene.setBackgroundPanel(imageFond);

		String texte1 = "<html><CENTER>Bienvenue dans la ville de " + nomVille + "<br>Le chef du village vous attend veuillez le rejoindre dans sa maison.<br>Comment comptez-vous trouver votre chemin ?</CENTER></html>";
		Choix choix1 = new Choix(texte1, "Demander votre chemin", "Chercher seul");
		int reponse1 = demanderReponseChoix(choix1);
		if (reponse1 == 1) {
			String texte1_1 = "<html><CENTER>Vous trouvez facilement un habitant du village<br> qui vous indique où se trouve la maison du chef du village.</CENTER></html>";
			Choix choix1_1 = new Choix(texte1_1,"Parler au chef");
			demanderReponseChoix(choix1_1);
		} else {
			String texte1_2 = "<html><CENTER>Après avoir tourné dans le village pendant 15 mins,<br> vous arrivez devant une maison qui se démarque des autres.<br> Aucun doute, le chef habite ici.</CENTER></html>";
			Choix choix1_2 = new Choix(texte1_2, "Parler au chef");
			demanderReponseChoix(choix1_2);
		}
		new ScenarioMaisonChef(intGene,personnage);
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}
