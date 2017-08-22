import java.awt.*;
public interface IHome
{
	public void adWinLose(boolean b,Game g);
	public void removeAll();
	public void revalidate();
	public void repaint();
	public Component add(Component c);
	public String getMode();
}