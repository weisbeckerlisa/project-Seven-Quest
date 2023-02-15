//package animation_d;

import java.awt.BorderLayout;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
 
public class TestImage extends JFrame {
	private static final long serialVersionUID = 1L;
 
	public TestImage() {
		super("TestImage");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("animation_d/anima_4.gif"))), BorderLayout.SOUTH);
 
	}
	
	public static void lancer() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new TestImage();
				frame.setSize(634, 422);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}