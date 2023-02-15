import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class InterfaceLevelUp {

	private Personnage perso;

	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	public Dimension dimFenetre = new Dimension(1000, 1000);
	public Font police = new Font(Font.SERIF, Font.BOLD, 12);
	public Font fontNonApparent = new Font(Font.SERIF, Font.BOLD, 0);

	private InterfaceGenerale intGene;

	public InterfaceLevelUp(InterfaceGenerale intGene) {
		this.intGene = intGene;
	}
		
	public void panelLevelUp(int pts) {
		this.perso = InterfaceGenerale.personnage;

		while (pts > 0) {
			String texte = "<html><CENTER>Vous avez " + pts + " points à répartir<br> Adresse : " + this.perso.getAdresse() + "<br> Force : " + this.perso.getForce() + "<br> Intelligence : " + this.perso.getIntelligence() + "</CENTER></html>";
			Choix choix = new Choix (texte, "Adresse + 1", "Force + 1", "Intelligence + 1");
			InterfaceChoix intChoix = new InterfaceChoix(choix, this.intGene);
			int reponseLvlUp = intChoix.afficherDemanderReponse();
			
			if (reponseLvlUp == 1) {
				this.perso.addAdresse(1);
			} else if (reponseLvlUp == 2) {
				this.perso.addForce(1);
			} else {
				this.perso.addIntelligence(1);
			}
			pts -= 1;

			this.intGene.actualiserStats();
		}
	}
}
