import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.event.*;
import java.text.*;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
/**
 * Classe du panel accueil
 */
public class GHome extends JPanel implements IHome, ActionListener
{
	public static ImageIcon icon1 = new ImageIcon("ressources/briques1.jpg");
	public static ImageIcon icon2 = new ImageIcon("ressources/briques2.jpg");
	private JButton bNew = new JButton ("Nouvelle partie",icon2 );
	private JButton bLoad = new JButton("Charger une partie",icon2);
	private JButton bHome = new JButton ("Retour a l'ecran d'acceuil",icon2);
	private JComboBox<String> bMod = new JComboBox<>(new String[]{"Easy","Medium","Hard"});
	private String mode;
    private GridBagConstraints gc;
	
    /**
     * Constructeur de la classe GHome
     */
    public GHome()
    {
        this.setLayout(new GridBagLayout());
    	gc = new GridBagConstraints();
        bMod.setPreferredSize(new Dimension(140, 30));
        this.add(bMod);
        
        gc.insets = new Insets(5, 5, 5, 5);
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        Font font = new Font ("Old English Text MT", Font.BOLD, 22);
        
        bNew.setBorderPainted(false);
        bNew.setHorizontalTextPosition(SwingConstants.CENTER);
        bNew.setFont(font);
        bNew.setForeground(Color.black);
        bNew.setRolloverIcon(icon1);
        bNew.setPreferredSize(new Dimension(280,60));
        bNew.addActionListener( this );
        this.add(bNew, gc);

        
        gc.gridx = 2;
        gc.gridy = 1;
        bLoad.setBorderPainted(false);
        bLoad.setHorizontalTextPosition(SwingConstants.CENTER);
        bLoad.setFont(font);
        bLoad.setForeground(Color.black);
        bLoad.setRolloverIcon(icon1);
        bLoad.setPreferredSize(new Dimension(280, 60));
        bLoad.addActionListener( this );
        this.add(bLoad, gc);
    }

    /**
     * Méthode pour peindre un composant graphique
     * 
     * @param g le composant graphique
     */
    public void paintComponent(Graphics g)
    {
        try
        {
            //Fond d'ecran de l'accueil
            Image img = ImageIO.read(new File("ressources/home.jpg"));
            g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), this);
            
            //Titre
            Font font = new Font ("Old English Text MT", Font.BOLD, 100);
            g.setFont(font);
            g.setColor(Color.black);
            int stringLen = (int)g.getFontMetrics().getStringBounds("Demineur", g).getWidth(); //Permet d'obtenir la longueur du mot
            
            g.drawString("Demineur", (this.getWidth()/2)-(stringLen/2),(this.getHeight()/6) );
            
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
    }
    /**
     * Méthode permettant d'intercepter les événement du panel
     * 
     * @param e l'événement
     */
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource() == bNew)
    	{
    		mode = bMod.getSelectedItem().toString();
    		Game g=new Game(10,10,20);
    		if(mode=="Easy")
    		{
    			g=new Game(10,10,20);
    		}
    		else if(mode=="Medium")
            {
            	g= new Game(15,15,50);
            }
    		else if(mode=="Hard")
    		{
    			g= new Game(20,20,100);
    		}
    		g.initialisation(this);
    	}
    	else if(e.getSource() == bHome)
    	{
        	DemineurStart.fen.home.removeAll();
			GHome home2 = new GHome();
			DemineurStart.fen.setContentPane(home2);
			DemineurStart.fen.repaint();
			DemineurStart.fen.revalidate();

    	}
    	else
    	{
    		JOptionPane jop = new JOptionPane();
            String[] listfich;
            listfich = (new File("./saves")).list();
            String choice = (String)jop.showInputDialog(null, "Choissisez une sauvegarde :","Charger une partie",JOptionPane.QUESTION_MESSAGE,null,listfich, null);
            try
            {
            	BufferedReader reader = new BufferedReader(new FileReader("./saves/"+choice));
                mode = reader.readLine();
                Game game=null;
            	System.out.println("#"+mode+"#");
                if(mode.equals("Easy"))
                {
                	game = new Game(10,10,20);
                }
                else if(mode.equals("Medium"))
                {
                	game = new Game(15,15,50);
                }
                else
        		{
        			game= new Game(20,20,100);
        		}
                String thisLine;
                game.emptyBoardForLoading();
                game.initialisation(this);
                int[] x=new int[400];
                int[] y=new int[400];
                String[] visit=new String[400];
                int i=0;
                while ((thisLine = reader.readLine()) != null)
                {
                	String[] elem = thisLine.split(" ");
                	System.out.println("#"+elem[2]+" "+elem[3]+" "+elem[4]+"#");
                	if(elem[2].equals("true"))
                	{
                    	game.getBoard()[Integer.parseInt(elem[0])][Integer.parseInt(elem[1])].setBomb(game.getBoard());
                	}
                	if(elem[4].equals("true"))
                	{
                		game.wantMark(Integer.parseInt(elem[0]),Integer.parseInt(elem[1]));
                	}
                	x[i]=Integer.parseInt(elem[0]);
                	y[i]=Integer.parseInt(elem[1]);
                	visit[i]=elem[3];
                	i++;
                }
                int j=0;
                while(visit[j]!=null)
                {
                	if(visit[j].equals("true"))
            		{
            			game.getBoard()[x[j]][y[j]].reveal(game.getBoard());
            		}
                	j++;
                }
                reader.close();
            }
            catch(IOException ex)
            {
            	ex.printStackTrace();
            }
    	}	
    }
    /**
     * Accesseur permettant de donner le mode de la partie
     * 
     * @return String contenant le mode de la partie
     */
    public String getMode()
	{
		return mode;
	}
    /**
     * Méthode permettant d'afficher à l'utilisateur le résultats de fin de partie : Message de victoire ou le board révélant l'emplacement des bombes
     * 
     * @param b boolean indiquant si la partie se finit sur une victoire ou une défaite
     * @param g partie qui se termine
     */
    public void adWinLose(boolean b,Game g)
    {
    	this.removeAll();
    	String s;
    	if(b)
    	{
    		s = "vous avez gagné, Bravo";
    	}
    	else
    	{
			GBoard boardG=new GBoard(g.getBoard(),g,true);
			this.add(boardG);
    		s = "Perdu, réessayez une prochaine fois...";
    	}
    	JLabel label=new JLabel(s);
    	label.setForeground(Color.BLACK);
    	label.setFont(new Font ("Old English Text MT", Font.BOLD, 30));
    	gc.gridx=0;
    	gc.gridy=1;
    	gc.insets=new Insets(40,40,40,40);
    	this.add(label,gc);
    	
    	gc.gridy=2;
    	bHome.setBorderPainted(false);
        bHome.setHorizontalTextPosition(SwingConstants.CENTER);
        Font font = new Font ("Old English Text MT", Font.BOLD, 22);
        bHome.setFont(font);
        bHome.setForeground(Color.black);
        bHome.setRolloverIcon(icon1);
        bHome.setPreferredSize(new Dimension(280,60));
        bHome.addActionListener( this );
        this.add(bHome, gc);
        this.revalidate();
		this.repaint();
		System.out.println("fini2");
    }
}
    
        
