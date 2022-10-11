package abdn.scnu.cs;

public interface GameControls {

	public void playRound (String input);
	
	public boolean checkVictory ();
	
	public void exitGame (String input);
	
	public AbstractGameGrid getPlayersGrid ();
	
	public AbstractGameGrid getOpponentssGrid ();
}
