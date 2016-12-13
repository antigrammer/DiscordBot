import java.util.HashMap;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;


public abstract class Game {

	private BotManager manager;
	private HashMap<IUser, IChannel> players;
	private boolean gameOver;
	
	public Game(BotManager m, HashMap<IUser, IChannel> p) {
		manager = m;
		players = p;
		gameOver = false;
	}
	
	public BotManager getManager() {
		return manager;
	}
	public boolean gameGoing() {
		if(gameOver)
			return false;
		return true;
	}	
	public void gameOver() {
		gameOver = true;
	}
	public HashMap<IUser, IChannel> getPlayers() {
		return players;
	}
	public MessageBot getBot() {
		return getManager().getBot();
	}
	
	public abstract void start();
	
	public abstract void parse(IMessage message);
	
}
