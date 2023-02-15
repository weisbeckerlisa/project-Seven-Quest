import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioColline {

	InterfaceGenerale intGene;
	Personnage perso;
	Image image; 
	
	public ScenarioColline (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		

		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/colline.png");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;
		intGene.setBackgroundPanel(imageFond);

		String texte1 = "Après une vingtaine de minutes de marche en direction de la colline, vous apercevez l'entrée d'une grotte\n"+"Vous apercevez également un bout de tissu semblale à la robe que vous avez décrit le chef du village";
		Choix choix1 = new Choix(texte1, "Rentrer dans la grotte", "faire le tour de la colline");
		int reponse1 = demanderReponseChoix(choix1);
		if (reponse1 == 1) {
			new ScenarioGrotte(intGene,perso);
		} else {
			new ScenarioReperage(intGene, perso);
		}
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}


