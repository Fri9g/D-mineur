import java.awt.*;
import javax.swing.*;
import java.io.*;


/**
 * Classe de création de la fenêtre
 */
public class Window extends JFrame
{
	public GHome home;
	/**
	 * Constructeur de la fenêtre
	 */
    public Window()
    {
    	this.setTitle("Demineur");
    	this.setSize(960, 540);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home = new GHome();
    	this.setContentPane(home);
    	this.setVisible(true);
    	this.pack();
    	this.setDefaultLookAndFeelDecorated(true);
    	this.setExtendedState(this.MAXIMIZED_BOTH);
    	try 
    	{
    		Image image = new ImageIcon("./ressources/icon.png").getImage();
    		this.setIconImage(image);
    	}
    	catch (Exception exc) 
    	{
    	    exc.printStackTrace();
    	}
	}
}
