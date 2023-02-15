
public class Choix {
	
	private int nbChoix; 
	private String texteScenario;
	private String[] choix = new String[4];
	
	public Choix(String texte, String choix1, String choix2, String choix3, String choix4) {
		this.nbChoix = 4;
		this.texteScenario = texte;
		this.choix[0] = choix1; 
		this.choix[1] = choix2;
		this.choix[2] = choix3; 
		this.choix[3] = choix4; 
	}
	
	public Choix(String texte, String choix1, String choix2, String choix3) {
		this(texte, choix1, choix2, choix3, " ");
		this.nbChoix = 3 ;
	}
	
	public Choix(String texte, String choix1, String choix2) {
		this(texte, choix1, choix2, " ", " ");
		this.nbChoix = 2 ;
	}
	
	public Choix(String texte, String choix1) {
		this(texte, choix1, " ", " ", " ");
		this.nbChoix = 1 ;
	}

	public Choix(String texte) {
		this(texte, "Suivant", "", "", "");
		this.nbChoix = 1;
	}
	
	public String getTexte() {
		return this.texteScenario;
	}
	
	public int getNbChoix() {
		return this.nbChoix;
	}
	
	public String getChoix(int indice) {
		switch (indice) {
			case 1 :
				return this.choix[0];
			case 2 : 
				return this.choix[1];
			case 3 :
				return this.choix[2];
			case 4 : 
				return this.choix[3];
			default : 
				return "";
	}
	
	
	}
	
}
