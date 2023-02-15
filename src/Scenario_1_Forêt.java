public class Scenario_1_Forêt {
    //static final String erreurChoix = "Erreur dans le choix";
    //private static Scanner input = new Scanner(System.in);

    public Scenario_1_Forêt(){
    }

    /*public static Object DescriptionForet(Personnage personnage){
        System.out.println("Après quelques minutes de marche, vous arrivez dans la forêt.");
        System.out.println("Vous apercevez alors un Bandit sur votre chemin.");
        System.out.println("Sans hésitation, il vous saute dessus et débute un combat.\n");
        return Combat1(personnage);
    }

    public static Object Combat1(Personnage personnage){
        //Premier Combat
        int[] stat = {1, 1, 1};
        PersonnageBandit bandit = new PersonnageBandit("Bandit1", 10, stat, 1, 0, 1, 1);
        Combat rencontre = new Combat(personnage, bandit);
        rencontre.combattre();
        
        //Loot
        int[] statsAjout = {2,0};
        int[] atqSpe = {2, 2};
        Equipement épée_courte = new Equipement("épée_courte", 100, statsAjout, 0, atqSpe, 1, "main droite","images.epees/epeePierre.png");
        System.out.println();
        System.out.println("Félicitations, vous trouvez sur la dépouille du bandit une épée courte !");
        épée_courte.afficherEquipement();
        System.out.println("1- Récupérer l'épée");
        System.out.println("2- Garder votre équipement");
        String arg = input.next();
        if (arg.equals("1")){
            personnage.equiperEquipement(épée_courte);
            personnage.afficherEquipementsActuels();
            System.out.println("Vous équipez épée courte");
            return cheminForet(personnage);
        }else if(arg.equals("2")){
            return cheminForet(personnage);
        }else{
            return erreurChoix;
        }
    }

    public static Object cheminForet(Personnage personnage){
        System.out.println("Après avoir vaincu votre ennemi, vous prenez le temps de regarder autour de vous");
        System.out.println("Le chemin qui s'offre à vous se divise en deux");
        System.out.println(("1- Une route qui s'enfonce dans la forêt qui devient si dense que la lumière a du mal à pénétrer"));
        System.out.println(("2- Une route qui emmène vers une colline"));
        String arg = input.next();
        if(arg.equals("1")){
            return forêt(personnage);
        }else if (arg.equals("2")){
            return colline(personnage);
        }else{
            return erreurChoix;
        }
    }

    public static Object colline(Personnage personnage){
        System.out.println("Après une vingtaine de minutes de marche en direction de la colline, vous apercevez l'entrée d'une grotte");
        System.out.println("Vous apercevez également un bout de tissu semblale à la robe que vous avez décrit le chef du village");
        System.out.println("1- Vous décidez de rentrer dans la grotte");
        System.out.println("2- Vous décidez de faire le tour de la colline");
        String arg = input.next();
        if(arg.equals("1")){
            return grotte(personnage);
        }else if (arg.equals("2")){
            return reperage(personnage);
        }else{
            return erreurChoix;
        }
    }

    public static Object forêt(Personnage personnage){
        System.out.println("Après une vingtaine de minutes de marche, vous apercevez l'entrée d'une grotte");
        System.out.println("Vous apercevez également un bout de tissu semblale à la robe que vous avez décrit le chef du village");
        System.out.println("1- Vous décidez de rentrer dans la grotte");
        System.out.println("2- Vous décidez d'explorer les alentours");
        String arg = input.next();
        if(arg.equals("1")){
            return grotte(personnage);
        }else if (arg.equals("2")){
            return reperage(personnage);
        }else{
            return erreurChoix;
        }
    }
    public static Object grotte(Personnage personnage){
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
    }
    public static Object reperage(Personnage personnage){
        System.out.println("En faisant le tour, vous surprenez un bandit de retour de la chasse.");
        System.out.println("Au moment où il vous aperçoit, il n'a aucune hésitation et vous saute dessus");
        int[] stat = {1, 1, 1}; //A EQUILIBRER
        PersonnageBandit bandit = new PersonnageBandit("Bandit1", 10, stat, 1, 0, 1, 1);
        Combat rencontre = new Combat(personnage, bandit);
        rencontre.combattre();
        System.out.println("Vous devez toucher au but, les bandits ne sont pas courant dans la région.");
        System.out.println("Vous vous rendez donc en direction de la grotte");
        return grotte(personnage);
    }
    public static Object présentation(Personnage personnage){
        System.out.println("Vous vous présentez à la jeune femme");
        System.out.println("'Vous êtes là pour me sauver?'");
        System.out.println("Après l'avoir détaché, elle vous demande de la guider jusqu'à chez elle pour voir son père, le chef du village");
        return fin(personnage);
    }

    public static Object libération(Personnage personnage){
        System.out.println("Vous vous empressez de détacher la jeune femme, elle semble terrorisée");
        System.out.println("Elle vous demande de la ramener chez elle, chez son père, le chef du village");
        return fin(personnage);
    }

    public static Object fin(Personnage personnage){
        System.out.println("De retour au village, la jeune femme court dans les bras de son père.");
        System.out.println("Pour vous remercier, le chef du village vous offre une belle armure en cuir ainsi qu'une épée longue.");
        System.out.println("De plus, vous êtes l'invité d'honneur du banquet de ce soir et vous serez hebergé");
        System.out.println("Félicitations, vous gagnez un niveau");
        System.out.println("Merci d'avoir jouée à notre scénario de démonstration");
        return null;
    }
    */
}