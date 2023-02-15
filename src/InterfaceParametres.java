import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceParametres {
	
	public final static Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	
	public InterfaceParametres() {
	}
	
	public ImagePanel creerParametres() {
		
		// Importation de l'image du parchemin
		ImageIcon iconParchemin = new ImageIcon("images/parchemin.png");
				
		// Redimension de l'image du parchemin
		Image imageParchemin = iconParchemin.getImage().getScaledInstance(10000, 10000, Image.SCALE_DEFAULT);
				
		// Creation du panel Parametres
		ImagePanel panelParametres = new ImagePanel(imageParchemin); // panel avec un parchemin en fond 
		panelParametres.setBackground(transparent);
		panelParametres.setPreferredSize(new Dimension(800, 500));
		panelParametres.setLayout(new GridLayout(3,1));
		
		//Add les boutons 
		JButton  outils = new JButton("Outils");
		JButton quitter = new JButton("Quitter");
		outils.setBackground(Color.LIGHT_GRAY);
		quitter.setBackground(Color.LIGHT_GRAY);
		quitter.addMouseListener(new MouseAdapter()
				{@Override 
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}}
				);
		
		JPanel panelParam = new JPanel();
		panelParam.setBackground(transparent);
		panelParam.add(outils);
		panelParam.add(quitter);
		panelParametres.add(new JLabel());
		panelParametres.add(panelParam);
	
		
		return panelParametres;
	}
	
}
