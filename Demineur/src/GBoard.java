import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.event.*;

/**
 * Classe du panel de jeu
 */
public class GBoard extends JPanel implements ActionListener
{
	public static ImageIcon marked = new ImageIcon("ressources/flag.png");
	public static ImageIcon notVisited = new ImageIcon("ressources/NotVisited.png");
	public static ImageIcon visited = new ImageIcon("ressources/Visited.png");
	public static ImageIcon bomb = new ImageIcon("ressources/bombe.png");
	public static ImageIcon un = new ImageIcon("ressources/1.png");
	public static ImageIcon deux = new ImageIcon("ressources/2.png");
	public static ImageIcon trois = new ImageIcon("ressources/3.png");
	public static ImageIcon quatre = new ImageIcon("ressources/4.png");
	public static ImageIcon cinq = new ImageIcon("ressources/5.png");
	public static ImageIcon six = new ImageIcon("ressources/6.png");
	public static ImageIcon sept = new ImageIcon("ressources/7.png");
	public static ImageIcon huit = new ImageIcon("ressources/8.png");
	private Game g;
	private JButton bSave = new JButton("Sauvegarder", GHome.icon2);
	private JButton bLeave = new JButton("Quitter", GHome.icon2);
	private GridBagConstraints gc;
	/**
	 * Constructeur de la classe GBoard
	 * 
	 * @param board le plateau
	 * @param g l'instance du jeu
	 * @param lose boolean indiquant si le board doit être entièrement révélé ainsi que les bombes (utilisé uniquement lors de l'affichage de la défaite)
	 */
    public GBoard(Square[][] board,Game g,boolean lose)
    {
    	this.g=g;
        this.setLayout(new GridBagLayout());
	    gc = new GridBagConstraints();	    
	    bSave.setRolloverIcon(GHome.icon1);
        bSave.setBorderPainted(false);
        bSave.setHorizontalTextPosition(SwingConstants.CENTER);
        Font font = new Font ("Old English Text MT", Font.BOLD, 22);
        bSave.setFont(font);
        bSave.setForeground(Color.black);
	    gc.gridx = board.length+1;
	    gc.gridy = 0;
	    gc.insets = new Insets(0, 50, 0, 10);
	    bSave.setPreferredSize (new Dimension (200,50));
	    bSave.addActionListener(this);
	    this.add(bSave,gc);    
	    bLeave.setRolloverIcon(GHome.icon1);
	    bLeave.setBorderPainted(false);
	    bLeave.setHorizontalTextPosition(SwingConstants.CENTER);
        bLeave.setFont(font);
        bLeave.setForeground(Color.black);
	    gc.gridy = 1;
	    gc.insets = new Insets(0, 50, 0, 10);
	    bLeave.setPreferredSize (new Dimension (200,50));
	    bLeave.addActionListener(this);
	    this.add(bLeave,gc);
	    if(lose==false)
	    {
	    	Button ij;
	    	for (int i=0;i<board.length;i++)
	    	{
	    		for (int j=0;j<board[0].length;j++)
	    		{    
	    			if(board[i][j].isVisited()==false)
	    			{
	    				if(board[i][j].isMarked()==false)
	    				{
	    					ij = new Button(i,j,notVisited);
	    				}
	    				else
	    				{
	    					ij = new Button(i,j,marked);
	    				}
	    			}
	        		else
	        		{
	        			if(board[i][j].howManyBombNear()==1)
	        			{
	        				ij = new Button(i,j,un);
	        			}
	        			else if(board[i][j].howManyBombNear()==2)
	        			{
	        				ij = new Button(i,j,deux);
	        			}
	        			else if(board[i][j].howManyBombNear()==3)
	        			{
	        				ij = new Button(i,j,trois);
	        			}
	        			else if(board[i][j].howManyBombNear()==4)
	        			{
	        				ij = new Button(i,j,quatre);
	        			}
	        			else if(board[i][j].howManyBombNear()==5)
	        			{
	        				ij = new Button(i,j,cinq);
	        			}
	        			else if(board[i][j].howManyBombNear()==6)
	        			{
	        				ij = new Button(i,j,six);
	        			}
	        			else if(board[i][j].howManyBombNear()==7)
	        			{
	        				ij = new Button(i,j,sept);
	        			}
	        			else if(board[i][j].howManyBombNear()==8)
	        			{
	        				ij = new Button(i,j,huit);
	        			}
	        			else
	        			{
	        				ij = new Button(i,j,visited);
	        			}
	        		}
	    			gc.gridx = i;
	    			gc.gridy = j;
	    			ij.setPreferredSize(new Dimension(50,50));
	    			gc.insets = new Insets(0, 0, 0, 0);
	    			ij.addMouseListener(new MouseEventListener());
	    			this.add(ij, gc);
	    		}
            }    
        }
	    else
	    {
			System.out.println("lose");
	    	for (int i=0;i<board.length;i++)
	    	{
	    		for (int j=0;j<board[0].length;j++)
	    		{    
	    			Button ij = new Button (i,j,bomb);
	    			if(board[i][j].isHereBomb())
	    			{
	    				ij = new Button (i,j,bomb);
	    			}
	        		else
	        		{
	        			ij = new Button (i,j,visited);
	        		}
	    			gc.gridx = i;
	    			gc.gridy = j;
	    			ij.setPreferredSize(new Dimension(50,50));
	    			gc.insets = new Insets(0, 0, 0, 0);
	    			this.add(ij, gc);
	    		}
            }
	    }
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
            Image img = ImageIO.read(new File("ressources/board.jpg"));
            g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), this);
            
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
    }
    /**
     * Classe permettant d'écouter la souris lors d'un clic sur un bouton et de différencer le clic gauche du clic droit
     */
    private class MouseEventListener implements MouseListener
    {
    	/**
    	 * Méthode permettant d'intercepter les événements de la souris sur les boutons
    	 * 
    	 * @param a l'événement
    	 */
    	public void mouseClicked(MouseEvent e)
    	{
    		Button b=(Button) e.getSource();
    		if(e.getButton() == MouseEvent.BUTTON3)
    		{
    			g.wantMark(b.x,b.y);
    		}
    		else if(e.getButton() == MouseEvent.BUTTON1)
    		{
    			g.wantReveal(b.x,b.y);
    		}
    	}
    	public void mouseEntered(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseExited(MouseEvent e){}
    }
    /**
     * Méthode permettant d'intercepter les événements des boutons de sauvegardes et d'exit
     * 
     * @param e l'événement
     */
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource() == bSave)
    	{
    		JOptionPane jop1 = new JOptionPane();
            String pseudo = jop1.showInputDialog(null,"Nom de la sauvegarde :", "Sauvegarde", JOptionPane.PLAIN_MESSAGE);

            try 
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("saves/"+pseudo+".txt")));
                writer.write(this.gamedata());
                writer.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
    	}
    	else if(e.getSource() == bLeave)
    	{
    		JOptionPane jop2 = new JOptionPane();
            int option = jop2.showConfirmDialog(null,"Voulez-vous vraiment quitter ?\n Attention toute progression non-sauvegardee sera perdue !", "Quitter", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION)
            {
            	DemineurStart.fen.home.removeAll();
    			GHome home2 = new GHome();
    			DemineurStart.fen.setContentPane(home2);
    			DemineurStart.fen.repaint();
    			DemineurStart.fen.revalidate();
            }
    	}
    }
    /**
     * Méthode permettant de créer un string contenant tout les paramètres de jeu
     * 
     * @return le string contenant les paramètres de jeu
     */
    public String gamedata()
    {   
        String data = g.getMode()+"\r\n";
        Square[][] b= g.getBoard();
        for (int i=0;i<b.length;i++)
        {
        	for (int j=0;j<b[0].length;j++)
            {
                data = data + i +" "+ j+" "+ b[i][j].isHereBomb() +" "+ b[i][j].isVisited() +" "+ b[i][j].isMarked()+ "\r\n";
            }
        }
    return data;
    }
}

                
        
