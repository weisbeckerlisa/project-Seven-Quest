import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;	

import javax.swing.JFrame;
public class Jouer {

	static JFrame frame;
	
	/** Lancer une partie. En argument sont donnés le type du personnage et son nom.
	 * @param args description du personnage
	 */
	public static void main(String[] args) throws Exception{

		// Création de la frame
		frame = new JFrame("Seven Quest");
		Dimension dimFenetre = new Dimension(1000, 1000);
		frame.setSize(dimFenetre); // Définition de la taille de la fenêtre
		frame.setLocationRelativeTo(null); // Fenêtre centrée par rapport à l'écran
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setVisible(true);
		
		
		BackImage menu = new BackImage(frame);
		ImagePanel panelCool = menu.MiseEnPlace();
		ImagePanel panelGeneral;

		boolean choose = false;
		Personnage perso = new PersonnageMagicien("c");
		while (true) {
			while(!choose) {
				frame.add(panelCool);
				actualiserFrame(frame);

				menu.attendreReponse();
				frame.remove(panelCool);
				

				InterfacePersonnage intPerso = new InterfacePersonnage();
				ImagePanel panelPerso = intPerso.demanderPanel();
				frame.add(panelPerso);
				actualiserFrame(frame);
				int reponsePersonnage = intPerso.reponsePersonnage(); // Magicien = 1, Guerrier = 2
				String nomPersonnage = intPerso.nomPerso;

				if (reponsePersonnage == 1) {
					perso = new PersonnageMagicien(nomPersonnage);
					choose = true;
				} else if (reponsePersonnage == 2) {
					perso = new PersonnageGuerrier(nomPersonnage);
					choose = true;
				} 
				frame.remove(panelPerso);
			}
			int[] statsAjout = {1, 1, 1};
			int[] atqSpe = {3, 2};
			Equipement hache = new Equipement("Hâche de papy Michell", 10000, statsAjout, 0, atqSpe, 1, "Bras Droit", "images/epees/hache.png");

			perso.equiperEquipement(hache);

			InterfaceGenerale interfaceGenerale = new InterfaceGenerale(perso, frame);
			perso.setIntGene(interfaceGenerale);
			panelGeneral = interfaceGenerale.getInterfaceGene();
			frame.add(panelGeneral);
			new ScenarioDebut(interfaceGenerale, perso);
			choose = false;
			frame.remove(panelGeneral);
		}
	}

	//actualiser frame
	public static void actualiserFrame(JFrame frame) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {

		}
		frame.setSize(999, 999);
		frame.setSize(1000, 1000);
	}
}
