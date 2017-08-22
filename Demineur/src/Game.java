import java.util.Random;
public class Game
{
	private Square[][] board;
	private int nbBomb;
	private int nbBombMarked;
	private int height;
	private int width;
	private Square[] marked;
	private IHome home;
	/**
	 * Méthode permettant de créer une partie
	 * 
	 * @param m la hauteur du board
	 * @param n la largeur du board
	 * @param b le nombre de bombe contenue par le board
	 */
	public Game(int m,int n,int b)
	{
		this.board=new Square [m][n];
		this.height=m;
        this.width=n;
        this.nbBomb=b;
        this.nbBombMarked=0;
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				this.board[i][j]=new Square(i,j,this);
			}
		}
		this.marked=new Square[b];
		Random r=new Random();
		for(int k=0;k<b;k++)
		{
			int l=r.nextInt(m-1);
			int g=r.nextInt(n-1);
			if (board[l][g].isHereBomb()==false)
			{
				board[l][g].setBomb(board);
			}
			else
			{
				k--;
			}
		}
	}
	/**
	 * Méthode permettant d'initialiser une partie
	 * 
	 * @param home le menu d'accueil
	 */
	public void initialisation(IHome home)
	{
		this.home=home;
		this.repaintBoard();
	}
	/**
	 * Accesseur pour obtenir la hauteur du board
	 * 
	 * @return Hauteur du board
	 */
	public int getHeight()
	{
		return this.height;
	}
	/**
	 * Accesseur pour obtenir la largeur du board
	 * 
	 * @return Largeur du board
	 */
	public int getWidth()
	{
		return this.width;
	}
	/**
	 * Accesseur pour obtenir le nombre de bombes du board
	 * 
	 * @return Nombres de bombes du board
	 */
	public int getNbBomb()
	{
		return this.nbBomb;
	}
	/**
	 * Methode pour finir la partie en défaite
	 */
	public void lose()
	{
		this.finish(false);
	}
	/**
	 * Methode pour finir la partie en Victoire
	 */
	public void win()
	{
		this.finish(true);
	}
	/**
	 * Methode pour tenter de reveler une case
	 * 
	 * @param a Coordonnées en hauteur de la case
	 * @param b Coordonnées en largeur de la case
	 */
	public void wantReveal(int a,int b)
	{
		this.board[a][b].reveal(this.board);
	}
	/**
	 * Methode pour tenter de marquer/demarquer une case
	 * 
	 * @param a Coordonnées en hauteur de la case
	 * @param b Coordonnées en largeur de la case
	 * 
	 * @return boolean indiquant le marquage/demarquage aie réussi
	 */
	public boolean wantMark(int a,int b)
	{
		if(this.board[a][b].isVisited()==true)
		{
			return false;
		}
		if(this.board[a][b].isMarked()==false)
		{
			int i=0;
			while((i<this.nbBomb)&&(marked[i]!=null))
			{
				i++;
			}
			if(i<this.nbBomb)
			{
				this.board[a][b].markBomb();
				this.marked[i]=board[a][b];
				if(this.board[a][b].isHereBomb())
				{
					this.nbBombMarked++;
					System.out.println(nbBombMarked);
				}
				if(this.nbBombMarked==this.nbBomb)
				{
					this.win();
				}
				else
				{
					this.repaintBoard();
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(this.board[a][b].isMarked()==true)
		{
			int i=0;
			while((marked[i]!=board[a][b])&&(i<this.nbBomb))
			{
				i++;
			}
			if(i<this.nbBomb)
			{
				this.board[a][b].markBomb();
				this.marked[i]=null;
				if(this.board[a][b].isHereBomb())
				{
					this.nbBombMarked--;
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	/**
	 * Accesseur pour obtenir le board de la partie
	 * 
	 * @return Board de la partie
	 */
	public Square[][] getBoard()
	{
		return this.board;
	}
	/**
	 * Methode pour mettre fin à la partie
	 * 
	 * @param b boolean indiquant la victoire(true) ou la défaite(false)
	 */
	public void finish(boolean b)
	{
		home.removeAll();
		home.adWinLose(b,this);
		home.revalidate();
		home.repaint();
	}
	/**
	 * Methode utilisée pour repeindre la fenêtre
	 */
	public void repaintBoard()
	{
		home.removeAll();
		GBoard boardG = new GBoard(board,this,false);
		home.add(boardG);
		home.revalidate();
		home.repaint();
	}
	/**
	 * Methode permettant d'obtenir le mode du jeu : "Easy","Medium" ou "Hard"
	 * 
	 * @return Mode de la partie
	 */
	public String getMode()
	{
		return home.getMode();
	}
	/**
	 * Methode nettoyant le board afin de charger une partie
	 */
	public void emptyBoardForLoading()
	{
		this.board = new Square[this.height][this.width];
		for(int i=0;i<this.height;i++)
		{
			for(int j=0;j<this.width;j++)
			{
				this.board[i][j]=new Square(i,j,this);
			}
		}
	}
}