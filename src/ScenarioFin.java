public class ScenarioFin {
	
    InterfaceGenerale intGene;
	Personnage perso;
	
	public ScenarioFin (InterfaceGenerale intGene, Personnage personnage) {
		this.intGene = intGene;
		this.perso = personnage;

		String texte1 = "<html><CENTER>De retour au villlage, la jeunne femme court dans les bras de son père.<br> Pour vous remercier, le chef du village vous offre une belle armure en cuir <br> ainsi qu'une épée longue.<br> De plus, vous êtes l'invité d'honneur du banquet de ce soir <br> et vous serez hebergé</CENTER></html>";
		Choix choix1 = new Choix(texte1, "Quitter");
        demanderReponseChoix(choix1);
		personnage.ajoutExp(10);
    }

    private int demanderReponseChoix(Choix choix) {
		InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
		return intChoix.afficherDemanderReponse();
	}
    
}
