import java.util.Scanner;

public class Scenario_1_Ville {
    static final String nomVille = "Laelith";
    static final String erreurChoix = "Erreur dans le choix";
    private static Scanner input = new Scanner(System.in);

    public Scenario_1_Ville(){
    }
    
    public static Object Introduction(Personnage personnage){
        System.out.println("\nBienvenue dans la ville de " + nomVille + "\n");
        System.out.println("Le chef du village vous attend veuillez le rejoindre dans sa maison\n");
        System.out.println("Comment comptez-vous trouver votre chemin ?\n");
        System.out.println("1 - Demander votre chemin");
       	System.out.println("2 - Chercher seul.");
        String arg = input.next();
        if (arg.equals("1")){
            return demanderChemin(personnage);
        }else if(arg.equals("2")){
            return chercherSeul(personnage);
        }else{
            return erreurChoix;
        }
    }

    public static Object demanderChemin(Personnage personnage) {
            System.out.println("Vous trouvez facilement un habitant du village qui vous indique \n\toù se trouve la maison du chef du village.\n");
            return maisonChef(personnage);
        }

    public static Object chercherSeul(Personnage personnage) {
        System.out.println("Après avoir tourné dans le village pendant 15 mins, vous arrivez \n\tdevant une maison qui se démarque des autres.");
        System.out.println("Aucun doute, le chef habite ici\n");
        return maisonChef(personnage);
    }

    public static Object maisonChef(Personnage personnage) {
    	System.out.println("Chef du village :");
        System.out.println("\t\"Aventurier, je suis heureux d'enfin vous rencontrer.");
        System.out.println("\tMalheuresement, je n'ai pas le temps pour les politesses.");
        System.out.println("\tMa fille Gwen a disparu depuis hier soir, elle était partie");
        System.out.println("\tchercher du bois mais n'est jamais revenue...\n\tPouvez-vous m'aider ?\"\n");
        System.out.println("Votre choix :\n");
        System.out.println("1 - Oui bien-sûr !");
        System.out.println("2 - Je n'ai pas le temps pour ces broutilles. Je préfère aller pêcher.");
        String arg = input.next();
        if(arg.equals("1")){
            return suite(personnage);
        }else if (arg.equals("2")){
            return fin();
        }else{
            return erreurChoix;
        }
    }

    public static Object  suite(Personnage personnage){
        System.out.println("Vous partez en direction de la forêt pour aller chercher la fille du chef disparue.");
        return "";//Scenario_1_Forêt.DescriptionForet(personnage);
    }
 

    public static Object fin(){
        System.out.println("Merci d'avoir joué");
        return null;
    }
}
