import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
/**
 * Classe des tests
 */
public class MyTest
{
	/**
	 * Méthode testant la victoire
	 */
	@Test
	public void win()
	{
		//Grille de 2x2 avec une bombe placé en (0.0)
		Game g=new Game(2,2,1);
		g.emptyBoardForLoading();
		g.getBoard()[0][0].setBomb(g.getBoard());
		fakeHome h= new fakeHome();
		g.initialisation(h);
		
		//Marquage de la bombe (remplissage de la condition de victoire)
		g.wantMark(0,0);
		
		//test du boolean representant la victoire
		Assert.assertTrue(h.b);
	}
	/**
	 * Méthode testant la defaite
	 */
	@Test
	public void lose()
	{
		//Grille de 2x2 avec une bombe placé en (0.0)
		Game g=new Game(2,2,1);
		g.emptyBoardForLoading();
		g.getBoard()[0][0].setBomb(g.getBoard());
		fakeHome h= new fakeHome();
		g.initialisation(h);
		
		//Reveal de la case contenant la bombe qui provoque la defaite
		g.wantReveal(0,0);
		
		//test du boolean representant la defaite
		Assert.assertFalse(h.b);
	}
	/**
	 * Méthode testant la non defaite et la non victoire
	 */
	@Test
	public void notWinNotLose()
	{
		//Grille de 10x10 avec deux bombes placé en (0.0) et (0.1)
		Game g=new Game(10,10,2);
		g.emptyBoardForLoading();
		g.getBoard()[0][0].setBomb(g.getBoard());
		g.getBoard()[0][1].setBomb(g.getBoard());
		fakeHome h= new fakeHome();
		g.initialisation(h);
		
		//Marquage d'une des deux bombes
		g.wantMark(0,0);

		//test du boolean representant null tant que la partie n'est pas terminée
		Assert.assertNull(h.b);
	}
	public class fakeHome implements IHome
	{
		public Boolean b;
		public void adWinLose(boolean b,Game g){this.b = b;}
		public void removeAll(){}
		public void revalidate(){}
		public void repaint(){}
		public Component add(Component c){return c;}
		public String getMode(){return "1";}
	}
}