package jeu;

public abstract class Case
{
	private Coordonnees position;
	private boolean visible;
	
	public Case(Coordonnees c)
	{
		this.setPosition(c);
		this.setVisible(true);
	}

	public Coordonnees getPosition()
	{
		return this.position;
	}

	public boolean isVisible()
	{
		return this.visible;
	}

	private void setPosition(Coordonnees position)
	{
		if (position == null)
			throw new NullPointerException();
		
		this.position = position;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
