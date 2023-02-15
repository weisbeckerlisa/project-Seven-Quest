
// Scénario de TEST qui regroupe tout ce qu'on peut utiliser dans un scénario.

public class Scenario1 {

	InterfaceGenerale intGene;
	Personnage perso;

	public Scenario1 (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;


		Choix choix1 = new Choix("Bonjour aventurier, comment allez vous ?", "bonjour, plutôt bien et vous ?", "ok cool");
		int reponse1 = demanderReponseChoix(choix1);
		if (reponse1 == 1) {
			Choix choix1_1 = new Choix("Très bien merci ! Voici de l'argent", "Prendre l'argent");
			int reponse1_1 = demanderReponseChoix(choix1_1);
			if (reponse1_1 == 1) {
				System.out.println("Fin *aventurier simplet* du jeu, merci d'avoir joué !");
			}
		} else {
			Choix choix1_2 = new Choix("Comment ça vous vous foutez de ce que je vous dit ??", "Complètement je suis juste là pour prendre mon argent.", "L'assomer et prendre l'argent qu'il devait.");
			int reponse1_2 = demanderReponseChoix(choix1_2);
			if (reponse1_2 == 1) {
				Choix choix1_2_1 = new Choix("Vous allez me le payer cher !", "Combattre", "Fuir");
				int reponse1_2_1 = demanderReponseChoix(choix1_2_1);
				if (reponse1_2_1 == 1) {
					Personnage bandit = new PersonnageBandit("Marchand malhonnête");
					Combat combat1_2_1_1 = new Combat(this.perso, bandit, this.intGene);
					combat1_2_1_1.combattre();
					this.perso.ajoutExp(10);
					
					Choix choix1_2_1_1 = new Choix("Vous appercevez la bourse du marchand malhonnête.", "Prendre l'argent", "Lui laisser");
					int reponse1_2_1_1 = demanderReponseChoix(choix1_2_1_1);
					if (reponse1_2_1_1 == 1) {
						System.out.println("Fin *aventurier criminel* du jeu, merci d'avoir joué !");
					} else {
						System.out.println("Fin *aventurier avec de la pitié* du jeu, merci d'avoir joué !");
					}
				} else {
					System.out.println("Fin *aventurier lâche* du jeu, merci d'avoir joué !");
				}
			} else {
				System.out.println("Fin *aventurier BADASS* du jeu, merci d'avoir joué !");
			}
		}
	}

	private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
}
