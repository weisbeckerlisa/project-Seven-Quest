import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel {

  private Image backgroundImage;

  public Background(String fileName) throws IOException {
	  //URL file = getClass().getResource("/src/src/"+fileName);
	  backgroundImage = new ImageIcon(fileName).getImage();
	  this.setBorder(BorderFactory.createEmptyBorder());
	  this.setOpaque(false);
  }
  
  public Image getBGImage() {
	  return this.backgroundImage;
  }
  
  public void setBGImage(String fileName) {
	  //URL file = getClass().getResource("/src/src/"+fileName);
	  backgroundImage = new ImageIcon(fileName).getImage();
	  stretchImage();
  }
  
  public void stretchImage() {
	  short w = (short) super.getWidth();
	  short h = (short) super.getHeight();
	  this.backgroundImage = this.backgroundImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
  }

  public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  g.drawImage(backgroundImage, 0, 0, this);
}
}