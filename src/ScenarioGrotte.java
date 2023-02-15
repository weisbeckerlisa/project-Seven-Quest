import java.awt.Image;

import javax.swing.ImageIcon;

public class ScenarioGrotte {

	InterfaceGenerale intGene;
	Personnage perso;
	Image image;
	
	public ScenarioGrotte (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;
		
		// Importation de l'image 
		ImageIcon iconFond = new ImageIcon("images/background/grotte.png");
		// Redimension de l'image 
		Image imageFond = iconFond.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
		this.image = imageFond;
		
		intGene.setBackgroundPanel(imageFond);
		
		String texte1 = "<html><CENTER>Vous pénétrez dans une caverne sombre.<br> Vous apercevez au fond 2 bandits en train de discuter.<br> Vous apercevez également de l'autre côté de la caverne : <br> une jeune femme attachée.</CENTER></html>";
		Choix choix1 = new Choix(texte1, "S'approcher discrètement", "Charger les ennemis");
		int reponse1 = demanderReponseChoix(choix1);
		if (reponse1 == 1) {
            String texte1_1 = "<html><CENTER>Vous trébuchez sur un cailloix et faite un peu de bruit.<br> Les deux grades vous chargent.</CENTER></html>";
		    Choix choix1_1 = new Choix(texte1_1, "Débuter le combat");
            demanderReponseChoix(choix1_1);
        } else {}
        int statinit1[] = {4, 5, 9};
        PersonnageBandit mechant1 = new PersonnageBandit("Bandit Aveugle", 20, statinit1, 2, 3, 100, 100);
        int statinit2[] = {4, 5, 9};
        PersonnageBandit mechant2 = new PersonnageBandit("Bandit Boiteux", 20, statinit2, 2, 3, 100, 100);

        Combat combat1 = new Combat(perso,mechant1, intGene);
        combat1.combattre();
        if (perso.estEnVie()) {
            Combat combat2 = new Combat(perso,mechant2, intGene);
            combat2.combattre();
                if (perso.estEnVie()) {
                String texte2 = "<html><CENTER>Après avoir vaincu les 2 bandits,<br> vous vous approchez de la jeune femme <br> qui vous regarde avec inquiétude.</CENTER></html>";
                Choix choix2 = new Choix(texte2, "Vous présentez", "Se dépêcher");
                int reponse2 = demanderReponseChoix(choix2);
                if (reponse2==1){
                    new ScenarioFin(intGene, perso);
                }else{
                    new ScenarioFin(intGene, perso);
                }
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

/*public static Object grotte(Personnage personnage){
        boolean discret = false;
        System.out.println("Vous pénétrez dans une caverne sombre, vous apercevez au fond 2 bandits en train de discuter.");
        System.out.println("Vous apercevez également de l'autre côté de la caverne, une jeune femme attachée.");
        System.out.println("Les bandits ne vous ont pas encore vu, serez-vous assez discret?");
        //Test de discrétion (lancer de dés)
        if(!discret){
            int[] stat = {1, 1, 1}; //A EQUILIBRER
            PersonnageBandit bandit = new PersonnageBandit("Bandit1", 10, stat, 1, 0, 1, 1);
            Combat rencontre = new Combat(personnage, bandit);
            rencontre.combattre();
        }
        System.out.println("Vous vous approchez de la jeune femme qui vous regarde avec inquiétude.");
        System.out.println("1- Vous vous présentez");
        System.out.println("2- Vous la détachez");
        String arg = input.next();
        if(arg.equals("1")){
            return présentation(personnage);
        }else if (arg.equals("2")){
            return libération(personnage);
        }else{
            return erreurChoix;
        }
    }*/
