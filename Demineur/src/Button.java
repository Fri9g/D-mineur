import javax.swing.*;
/**
 * Classe des boutons
 */
public class Button extends JButton
{
    final int x;
    final int y;
    /**
     * Constructeur de la classe Button
     * 
     * @param x la coordonnée x du bouton
     * @param y la coordonnée y du bouton
     * @param i l'image de fond du bouton (chiffre,flag,..)
     */
    public Button(int x,int y,ImageIcon i)
    {
	super("",i);
	this.x=x;
	this.y=y;
    }
}
