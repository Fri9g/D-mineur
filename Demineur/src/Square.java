public class Square
{
	private int posH;
	private int posW;
	private boolean isBomb;
	private int bombNear;
	private Game game;
	private boolean visited;
	private boolean marked;
	/**
	 * Constructeur d'une case du board
	 * 
	 * @param m Coordonnée en hauteur de la case
	 * @param n Coordonnée en largeur de la case
	 * @param g Partie à la quelle appartient le board contenant la case
	 */
	public Square(int m,int n,Game g)
	{
		this.posH=m;
		this.posW=n;
		this.game=g;
		this.visited=false;
		this.marked=false;
	}
	/**
	 * Méthode transformant la case en bombe
	 * 
	 * @param b Le board contenant la case dans laquelle nous sommes
	 */
	public void setBomb(Square[][] b)
	{
		this.isBomb=true;
		if(this.posH==0)
		{
			if(this.posW==0)
			{
				b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
				b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
				b[this.posH+1][this.posW+1].bombNear=b[this.posH+1][this.posW+1].bombNear+1;
			}
			else if(this.posW==b[this.posH].length-1)
			{
				b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
				b[this.posH+1][this.posW-1].bombNear=b[this.posH+1][this.posW-1].bombNear+1;
				b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
			}
			else
			{
				b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
				b[this.posH+1][this.posW-1].bombNear=b[this.posH+1][this.posW-1].bombNear+1;
				b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
				b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
				b[this.posH+1][this.posW+1].bombNear=b[this.posH+1][this.posW+1].bombNear+1;
			}
		}
		else if(this.posH==b.length-1)
		{
			if(this.posW==0)
			{
				b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
				b[this.posH-1][this.posW+1].bombNear=b[this.posH-1][this.posW+1].bombNear+1;
				b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
			}
			else if(this.posW==b[this.posH].length-1)
			{
				b[this.posH-1][this.posW-1].bombNear=b[this.posH-1][this.posW-1].bombNear+1;
				b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
				b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
			}
			else
			{
				b[this.posH-1][this.posW-1].bombNear=b[this.posH-1][this.posW-1].bombNear+1;
				b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
				b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
				b[this.posH-1][this.posW+1].bombNear=b[this.posH-1][this.posW+1].bombNear+1;
				b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
			}
		}
		else if((this.posW==0)&&(this.posH!=0)&&(this.posH!=b.length-1))
		{
			b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
			b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
			b[this.posH-1][this.posW+1].bombNear=b[this.posH-1][this.posW+1].bombNear+1;
			b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
			b[this.posH+1][this.posW+1].bombNear=b[this.posH+1][this.posW+1].bombNear+1;
		}
		else if((this.posW==b[this.posH].length-1)&&(this.posH!=0)&&(this.posH!=b.length-1))
		{
			b[this.posH-1][this.posW-1].bombNear=b[this.posH-1][this.posW-1].bombNear+1;
			b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
			b[this.posH+1][this.posW-1].bombNear=b[this.posH+1][this.posW-1].bombNear+1;
			b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
			b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
		}
		else
		{
			b[this.posH-1][this.posW-1].bombNear=b[this.posH-1][this.posW-1].bombNear+1;
			b[this.posH][this.posW-1].bombNear=b[this.posH][this.posW-1].bombNear+1;
			b[this.posH+1][this.posW-1].bombNear=b[this.posH+1][this.posW-1].bombNear+1;
			b[this.posH-1][this.posW].bombNear=b[this.posH-1][this.posW].bombNear+1;
			b[this.posH+1][this.posW].bombNear=b[this.posH+1][this.posW].bombNear+1;
			b[this.posH-1][this.posW+1].bombNear=b[this.posH-1][this.posW+1].bombNear+1;
			b[this.posH][this.posW+1].bombNear=b[this.posH][this.posW+1].bombNear+1;
			b[this.posH+1][this.posW+1].bombNear=b[this.posH+1][this.posW+1].bombNear+1;
		}
	}
	/**
	 * Accesseur pour obtenir la position en hauteur de la case
	 * 
	 * @return Hauteur de la case
	 */
	public int getPosH()
	{
		return this.posH;
	}
	/**
	 * Accesseur pour obtenir la position en largeur de la case
	 * 
	 * @return Lauteur de la case
	 */
	public int getPosW()
	{
		return this.posW;
	}
	/**
	 * Méthode afin de révéler la case sur le plateau
	 * 
	 * @param b Board contenant la case
	 */
	public void reveal(Square [][] b)
	{
		if(this.marked)
		{
		}
		else if(this.isBomb==true)
		{
			game.lose();
		}
		else
		{
			this.visited=true;
			if(this.howManyBombNear()==0)
			{
				if(this.posH==0)
				{
					if(this.posW==0)
					{	
						if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
						if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
						if(b[this.posH+1][this.posW+1].isVisited()==false){b[this.posH+1][this.posW+1].reveal(b);}
					}
					else if(this.posW==b[this.posH].length-1)
					{
						if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
						if(b[this.posH+1][this.posW-1].isVisited()==false){b[this.posH+1][this.posW-1].reveal(b);}
						if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
					}
					else
					{
						if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
						if(b[this.posH+1][this.posW-1].isVisited()==false){b[this.posH+1][this.posW-1].reveal(b);}
						if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
						if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
						if(b[this.posH+1][this.posW+1].isVisited()==false){b[this.posH+1][this.posW+1].reveal(b);}
					}
				}
				else if(this.posH==b.length-1)
				{
					if(this.posW==0)
					{
						if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
						if(b[this.posH-1][this.posW+1].isVisited()==false){b[this.posH-1][this.posW+1].reveal(b);}
						if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
					}
					else if(this.posW==b[this.posH].length-1)
					{
						if(b[this.posH-1][this.posW-1].isVisited()==false){b[this.posH-1][this.posW-1].reveal(b);}
						if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
						if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
					}
					else
					{
						if(b[this.posH-1][this.posW-1].isVisited()==false){b[this.posH-1][this.posW-1].reveal(b);}
						if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
						if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
						if(b[this.posH-1][this.posW+1].isVisited()==false){b[this.posH-1][this.posW+1].reveal(b);}
						if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
					}
				}
				else if((this.posW==0)&&(this.posH!=0)&&(this.posH!=b.length-1))
				{
					if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
					if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
					if(b[this.posH-1][this.posW+1].isVisited()==false){b[this.posH-1][this.posW+1].reveal(b);}
					if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
					if(b[this.posH+1][this.posW+1].isVisited()==false){b[this.posH+1][this.posW+1].reveal(b);}
				}
				else if((this.posW==b[this.posH].length-1)&&(this.posH!=0)&&(this.posH!=b.length-1))
				{
					if(b[this.posH-1][this.posW-1].isVisited()==false){b[this.posH-1][this.posW-1].reveal(b);}
					if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
					if(b[this.posH+1][this.posW-1].isVisited()==false){b[this.posH+1][this.posW-1].reveal(b);}
					if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
					if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
				}
				else
				{
					if(b[this.posH-1][this.posW-1].isVisited()==false){b[this.posH-1][this.posW-1].reveal(b);}
					if(b[this.posH][this.posW-1].isVisited()==false){b[this.posH][this.posW-1].reveal(b);}
					if(b[this.posH+1][this.posW-1].isVisited()==false){b[this.posH+1][this.posW-1].reveal(b);}
					if(b[this.posH-1][this.posW].isVisited()==false){b[this.posH-1][this.posW].reveal(b);}
					if(b[this.posH+1][this.posW].isVisited()==false){b[this.posH+1][this.posW].reveal(b);}
					if(b[this.posH-1][this.posW+1].isVisited()==false){b[this.posH-1][this.posW+1].reveal(b);}
					if(b[this.posH][this.posW+1].isVisited()==false){b[this.posH][this.posW+1].reveal(b);}
					if(b[this.posH+1][this.posW+1].isVisited()==false){b[this.posH+1][this.posW+1].reveal(b);}
				}
			}
			game.repaintBoard();
		}
	}
	/**
	 * Méthode permettant de marquer/demarquer la case
	 */
	public void markBomb()
	{
		if(this.marked==true)
		{
			this.marked=false;
		}
		else if (this.marked==false)
		{
			this.marked=true;
		}
	}
	/**
	 * Méthode permettant de connaitre le nombre de bombe se trouvant à proximité
	 * 
	 * @return Nombre de bombes présentes sur les cases adjacentes
	 */
	public int howManyBombNear()
	{
		return this.bombNear;
	}
	/**
	 * Accesseur permettant de savoir si la case a déjà été visitée
	 * 
	 * @return Boolean indiquant le marqueur de visite (true si déjà visitée)
	 */
	public boolean isVisited()
	{
		return this.visited;
	}
	/**
	 * Accesseur permettant de savoir si la case est marquée
	 * 
	 * @return Boolean indiquant si la case est marquée
	 */
	public boolean isMarked()
	{
		return this.marked;
	}
	/**
	 * Accesseur permettant de savoir si la case est une bombe
	 * 
	 * @return Boolean indiquant si la case est une bombe
	 */
	public boolean isHereBomb()
	{
		return this.isBomb;
	}
}