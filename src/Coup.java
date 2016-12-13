import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.math.NumberUtils;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class Coup extends Game{

	//Game parameters
	private int numPlayers;
	private Deck deck;
	private boolean inquisitorOverAmbassador;
	
	//Associated with players
	private ArrayList<IUser> playerList;
	private HashMap<IUser, Deck> playerHands;
	private HashMap<IUser, Integer> coins;
	private ArrayList<PersonalListener> privates;
	private ArrayList<PersonalListener> listeners;
	
	//Input handling
	private IUser listeningTo;
	private IChannel listeningFrom;
	private boolean listenToAll;
	
	private ArrayList<IMessage> responses;
	private boolean writingToQ;
	private boolean clearingQ;
	
	//Logic flags
	private boolean actionDone;
	private boolean gotExpectedResponse;
	
	public Coup(BotManager m, HashMap<IUser, IChannel> p, boolean iOverA) {
		super(m, p);	
		
		numPlayers = getPlayers().size();
		System.out.println("[game status] " + numPlayers + " players in game");
		
		deck = new Deck();
		inquisitorOverAmbassador = iOverA;
		
		playerList = new ArrayList<IUser>(numPlayers);
		Iterator<IUser> iterator = getPlayers().keySet().iterator();
		for(int i = 0; i < numPlayers; i++)
			playerList.add(iterator.next());
		coins = new HashMap<IUser, Integer>();
		
		privates = new ArrayList<PersonalListener>(numPlayers);
		listeners = new ArrayList<PersonalListener>(numPlayers);
		
		listenToAll = false;
		writingToQ = false;
		
		responses = new ArrayList<IMessage>();
		
		start();
	}

	@Override
	public void start() {
		Card c = new Card(new File("Coup Pictures/Duke.PNG"), "Duke");
		deck.add(c,3);
		c = new Card(new File("Coup Pictures/Captain.PNG"), "Captain");
		deck.add(c,3);
		c = new Card(new File("Coup Pictures/Assassin.PNG"), "Assassin");
		deck.add(c,3);
		c = new Card(new File("Coup Pictures/Contessa.PNG"), "Contessa");
		deck.add(c,3);
		if (inquisitorOverAmbassador) {
			c = new Card(new File("Coup Pictures/Inquisitor.PNG"), "Inquisitor");
			deck.add(c,3);
		}
		else {
			c = new Card(new File("Coup Pictures/Ambassador.PNG"), "Ambassador");
			deck.add(c,3);
		}
		deck.shuffle();
		
		HashMap<IUser, Integer> cardNumbers = new HashMap<IUser, Integer>();
		for(int i = 0; i < numPlayers; i++) {
			IUser user = playerList.get(i);
			cardNumbers.put(user, 2);
			coins.put(user, 2);
			PersonalListener pl = new PersonalListener(getBot(), user, getPlayers().get(user), this);
			getBot().registerListener(pl);
			privates.add(pl);
			PersonalListener plg = new PersonalListener(getBot(), user, getManager().getMainChannelOut(), this);
			getBot().registerListener(plg);
			listeners.add(plg);
		}
		playerHands = deck.deal(cardNumbers);
		
		for (IUser player : playerList) {
			ArrayList<Card> d = playerHands.get(player).literal();
			try {
				giveStatus(player);
			} catch (RateLimitException | MissingPermissionsException | DiscordException | IOException e) {
				e.printStackTrace();
			}
		}
				
		while(gameGoing()) {
			IUser player = playerList.get(0);
			playerList.add(playerList.remove(0));
			try {
				sendMessage("It is " + player.getName() + "'s turn.");
				gameState();
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				e.printStackTrace();
			}
			try {
				action(player);
			} catch (RateLimitException | MissingPermissionsException | DiscordException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (!actionDone) {System.out.print("");}
		}		
		
		try {
			sendMessage("The game is over. The winner is " + playerList.get(0).getName());
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}
		
		for (PersonalListener pl : privates) {
			pl.unregister();
		}
		for (PersonalListener pl : listeners) {
			pl.unregister();
		}
	}
	
	//Inputs
	@Override
	public void parse(IMessage message) {

		IChannel channel = message.getChannel();
		IUser source = message.getAuthor();
		String content = message.getContent();
		
		System.out.println("[personal listener] " + source.getName() + ": " + content);
		if(!clearingQ) {
			if (listeningFrom.equals(channel)) {
				if(!listenToAll) {
					if (listeningTo.equals(source)) {
						writingToQ = true;
						responses.add(message);
						writingToQ = false;
					}
				}
				else {
					writingToQ = true;
					responses.add(message);
					writingToQ = false;
				}
			}
		}
		else {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis()-time > 1000) {System.out.print("");}
			parse(message);
		}
	}
	public void emptyQueue() {
		clearingQ = true;
		for(int i = 0; i < responses.size(); i++)
			responses.remove(0);
		clearingQ = false;
		System.out.println("[Game status] Queue cleared.");
	}
	
	//action
	//if object1able, send to object1	//if not, send to processPlay
	//wait 3 seconds for objection	//if no objection,send to processPlay
	//if objection appears.. is the player correct?
	//if so, objector loses a card, replace player's card, send to processPlay
	//if not, player loses a card, end
	
	//processPlay
	//if object2able, send to object2	//if not, send to play (only target can object2)
	//wait 3 seconds for objection
	//if objection appears.. claim a role
	//object3
	//wait 3 seconds for object3 //if no, end
	//if objection appears.. is the target correct?
	//if so, objector loses a card, replace target's card, end
	//if not, target loses a card, play
	
	//play
	//takes in the full input
	//plays should be
	//take 1						(1)
	//take 2						(2)
	//duke take 3					(3)
	//captain steal 2				(4)
	//inquisitor look player/deck	(5)
	//ambassador look player		(6)
	//assassin kill player			(7)
	//coup player					(8)
	
	//--------------------------------------------------------------------

	//action
	//listens from main
	//listens to player
	//waits until input is of the form (1-8) and valid
	//(1, 2, 8) send to processPlay
	//(3-7) send to object1
	
	//object1
	//IUser player, String claim
	//listens from main
	//listens to all \ {player}
	//asks people if they want to object to the claim of player as <role>
	//waits 3 seconds
	//if nothing happens, send all information to processPlay
	//if a legal objection happens, see if player is actually the role they claim
	//if it is a lie loseCard(player) --> end
	//if it is true, loseCard(objector) --> replaceCard(player, <role>) --> processPlay(player, claim)
	
	//processPlay
	//IUser player, String claim
	//(1, 3, 5, 6, 8) sent to Play
	//(2, 4, 7) sent to object2
	
	//object2
	//IUser player, String claim
	//listens from main
	//if (2), listen to everyone... if (4, 7), listen to target
	//asks people if they want to object by claiming a role
	//waits 3 seconds
	//if nothing happens, send all information to Play
	//if a legal objection happens, send all information to object3
	
	//object3
	//IUser player, String claim, IUser target, String counterclaim
	//listens from main
	//listens to everyone \ {target}
	//asks people if they want to object to the claim of target as <role>
	//wait 3 seconds
	//if nothing happens, end
	//if a legal objection , see if the target is actually the role they claim
	//if it is a lie loseCard(target) --> Play
	//if it is true, loseCard(player) --> replaceCard(target, <role>) --> end
	
	//----------------------------------------------------------------------
	//we have a list of players
	//while there is more than one player	//else, end the game and name winner
	//take the first player from the list											//the remaining player the victor
	//add them to the back of the queue
	//designate them as "player"
	//action(player)
	//once the action is done //NEEDS A BOOLEAN ACTIONDONE
	//repeat
	
	//action(player)
	//ACTIONDONE = false
	//listeningFrom = main
	//listeningTo = player
	//while no valid input has been heard, keep listening //NEEDS A BOOLEAN GOTEXPECTEDRESPONSE
	//while there are responses to read, read them
	//once one of the responses is valid, send to processPlay or Object
	
	//ACTIONDONE = true; should be done only at the end of play or at end()
	
	
	//FINISHED
	public void action(IUser player) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		actionDone = false;
		
		listenToAll = false;
		listeningFrom = getManager().getMainChannelOut();
		listeningTo = player;
		
		gotExpectedResponse = false;
		
		emptyQueue();
		while (!gotExpectedResponse) {
			while (!writingToQ) {
				System.out.print("");
				if(!responses.isEmpty()) {
					IMessage message = responses.remove(0);
					String response = message.getContent();
					String[] brokenResponse = response.split(" ");
					switch(brokenResponse.length) {
					case 2:
						if(brokenResponse[0].equals("take")) {
							if(brokenResponse[1].equals("1")) {
								processPlay(player, response);
								gotExpectedResponse = true;
							}
							if (brokenResponse[1].equals("2")) {
								processPlay(player, response);
								gotExpectedResponse = true;
							}
						}
						if(brokenResponse[0].equals("coup"))
							if(isValidOther(brokenResponse[1], player)) {
								processPlay(player, response);
								gotExpectedResponse = true;
							}
						break;
					case 3:
						switch(brokenResponse[0]) {
						case "duke":
							if (brokenResponse[1].equals("take") && brokenResponse[2].equals("3")) {
								object(player, response);
								gotExpectedResponse = true;
							}
							break;
						case "inquisitor":
							if (inquisitorOverAmbassador) {
								if (brokenResponse[1].equals("look")) {
									if (brokenResponse[2].equals("deck")) {
										object(player, response);
										gotExpectedResponse = true;
									}
									if (isValidOther(brokenResponse[2], player)) {
										object(player, response);
										gotExpectedResponse = true;
									}
								}
								break;
							}
						case "ambassador":
							if (!inquisitorOverAmbassador) {
								if (brokenResponse[1].equals("look")) {
									if (isValidOther(brokenResponse[2], player)) {
										object(player, response);
										gotExpectedResponse = true;
									}
								}
								break;
							}
						case "captain":
							if (brokenResponse[1].equals("steal")) {
								if (isValidOther(brokenResponse[2], player)) {
									object(player, response);
									gotExpectedResponse = true;
								}
							}
							break;
						case "assassin":
							if (brokenResponse[1].equals("kill")) {
								if (isValidOther(brokenResponse[2], player)) {
									object(player, response);
									gotExpectedResponse = true;
								}
							}
							break;
						}
						break;
					}
				}
				else {
					long t1 = System.currentTimeMillis();
					while (System.currentTimeMillis() - t1 < 1000) {}
				}
				if (gotExpectedResponse)
					break;
			}
		}
	}
	
	//NEED TO FIGURE OUT HOW TO END THE GAME
	public void object(IUser player, String claim) throws MissingPermissionsException, DiscordException, RateLimitException, IOException {
		String role = claim.split(" ")[0];
		gotExpectedResponse = false;
		listenToAll = true;
		listeningFrom = getManager().getMainChannelOut();
		emptyQueue();
		String baseMessage = "Objections to " + player.getName() + " claiming role: " + role + ": ";
		long time = System.currentTimeMillis();
		System.out.println("[game status] objection timer has started.");
		IMessage sendMessage = sendMessage(baseMessage + "(" + 5 + ")");
		while (!gotExpectedResponse) {
			String timeLeft = ((time - System.currentTimeMillis()) / 1000 + 5) + "";
			if (System.currentTimeMillis() - time > 5000) {
				timeLeft = "0";
				gotExpectedResponse = true;
				System.out.println("[game status] objection timer has timed out.");
			}
			else {
				if (!writingToQ) {
					System.out.print("");
					if(!responses.isEmpty()) {
						IMessage message = responses.remove(0);
						String response = message.getContent();
						if(response.equals("object")) {
							IUser objector = message.getAuthor();
							if (isValidOther(message.getAuthor().getName(), player)) {
								boolean truth = false;
								Card replaceCard;
								for(Card c : playerHands.get(player).literal()) {
									if(c.getName().equals(role)) {
										replaceCard = c;
										truth = true;
									}
								}
								if(truth) {
									sendMessage(player.getName() + " 's claim of being " + role + " is the truth.\n" + objector.getName() + " must now give up a card.");
									loseCard(objector);
									replaceCard(player, role);
									processPlay(player, claim);
								}
								else {
									sendMessage(player.getName() + " 's claim of being " + role + " is a lie.\n" + player.getName() + " must now give up a card.");
									loseCard(player);
									//end the game
								}
								gotExpectedResponse = true;
							}
						}
					}
					else {
						long t1 = System.currentTimeMillis();
						while (System.currentTimeMillis() - t1 < 1000) {}
					}
					if (gotExpectedResponse)
						break;
				}
			}
			sendMessage.edit(baseMessage + "(" + timeLeft + ")");
		}
	}
	
	//FINISHED
	public void processPlay(IUser player, String claim) throws RateLimitException, MissingPermissionsException, DiscordException {
		String[] brokenClaim = claim.split(" ");
		switch(brokenClaim[0]) {
		case "duke":
		case "inquisitor":
		case "ambassador":
		case "coup":
			play(player, claim);
			break;
		case "take":
			if(brokenClaim[1].equals("1"))
				play(player, claim);
			else
				object2(player, claim);
			break;
		default:
			object2(player, claim);
		}
	}
	
	//NEEDS TO BE FINISHED
	public void play(IUser player, String claim) throws RateLimitException, MissingPermissionsException, DiscordException {
		String[] brokenClaim = claim.split(" ");
		switch(brokenClaim[0]) {
		case "duke":
			coins.put(player, coins.get(player)+3);
			sendMessage(player.getName() + " has taken 3 coins.");
			actionDone = true;
			break;
		case "inquisitor":
		case "ambassador":
		case "coup":
			play(player, claim);
			break;
		case "take":
			if(brokenClaim[1].equals("1"))
				play(player, claim);
			else
				object2(player, claim);
			break;
		default:
			object2(player, claim);
		}
	}
	
	public void object2(IUser player, String claim) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Game actions
	public boolean loseCard(IUser user) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		if(playerHands.get(user).size() > 1) {
			playerHands.get(user);
			actionDone = false;
			listeningTo = user;
			listeningFrom = getPlayers().get(user);
			Card c = null;
			while (!actionDone) {
				sendPersonalMessage(user, "You will have to give up a card.");
				c = selectCard(user);
				sendPersonalMessage(user, "You will now lose " + c.getName() + ".");
				playerHands.get(user).remove(c);
				giveStatus(user);
			}
			sendCard(c);
			sendMessage(user.getName() + " had the role of " + c.getName());
		}
		else {
			Card c = playerHands.get(user).draw();
			sendCard(c);
			sendMessage(user.getName() + " had the role of " + c.getName());
			sendMessage(user.getName() + " had the role of " + c.getName());
			sendMessage(user.getName() + " has no cards left and is out of the game.");
			removeUser(user);
		}
		return actionDone;
	}
	public void replaceCard(IUser user, String role) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		if(playerHands.get(user).size() > 1) {
			playerHands.get(user);
			actionDone = false;
			listeningTo = user;
			listeningFrom = getPlayers().get(user);
			Card c = null;
			while (!actionDone) {
				sendPersonalMessage(user, "You will have to give up a card.");
				c = selectCard(user);
				sendPersonalMessage(user, "You will now lose " + c.getName() + ".");
				playerHands.get(user).remove(c);
				giveStatus(user);
			}
			sendCard(c);
			sendMessage(user.getName() + " had the role of " + c.getName());
		}
		else {
			Card c = playerHands.get(user).draw();
			sendCard(c);
			sendMessage(user.getName() + " had the role of " + c.getName());
			sendMessage(user.getName() + " had the role of " + c.getName());
			sendMessage(user.getName() + " has no cards left and is out of the game.");
			removeUser(user);
		}
	}
	public Card selectCard(IUser user) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		Card choice = null;
		sendPersonalMessage(user, "Select a card:");
		for(int i = 0; i < playerHands.get(user).size(); i++) {
			Card c = playerHands.get(user).literal().get(i);
			sendPersonalCard(user, c);
			sendPersonalMessage(user, i + 1 + ") " + c.getName());
		}
		gotExpectedResponse = false;
		while (!gotExpectedResponse) {
			while (!writingToQ) {
				System.out.print("");
				if(!responses.isEmpty()) {
					IMessage message = responses.remove(0);
					String response = message.getContent();
					if (NumberUtils.isNumber(response)) {
						emptyQueue();
						int select = Integer.parseInt(response);
						if (0 < select && select < playerHands.get(user).size()) {
							choice = playerHands.get(user).literal().get(select-1);
							sendPersonalMessage(user, "Choice successful, you chose " + choice.getName());
							gotExpectedResponse = true;
							break;
						}
					}
				}
				else {
					long t1 = System.currentTimeMillis();
					while (System.currentTimeMillis() - t1 < 1000) {}
				}
				if (gotExpectedResponse)
					break;
			}
		}
		actionDone = true;
		return choice;
	}
	public void removeUser(IUser user) {
		for(int i = 0; i < privates.size(); i++) {
			if(privates.get(i).getUser().equals(user))
				privates.get(i).unregister();
			if(listeners.get(i).getUser().equals(user))
				listeners.get(i).unregister();
		}
		playerHands.remove(user);
		coins.remove(user);
		playerList.remove(user);
	}
	//NEED TO WORK ON THIS IN THE FUTURE AND WRITE WHAT PEOPLES' KNOWN CARDS ARE
	public void gameState() throws RateLimitException, MissingPermissionsException, DiscordException {
		String s = "**GAME STATUS**\n";
		for(int i = 0; i < playerList.size(); i++) {
			IUser player = playerList.get(i);
			s += player.getName() + " (" + coins.get(player) + " coins): \n";
		}
		s+= "**GAME STATUS**";
		sendMessage(s);
	}
	@Override
	public boolean gameGoing() {
		return playerList.size() > 1;
	}

	//Messaging
	public void sendCard(Card c) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		getManager().sendPicture(c);
	}
	public void sendPersonalCard(IUser i, Card c) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		getManager().sendPicture(c, getPlayers().get(i));
	}
	public IMessage sendMessage(String message) throws RateLimitException, MissingPermissionsException, DiscordException {
		return getManager().sendMessage(message);
	}
	public IMessage sendPersonalMessage(IUser i, String message) throws RateLimitException, MissingPermissionsException, DiscordException {
		return getManager().sendMessage(message, getPlayers().get(i));
	}
	public void giveStatus(IUser player) throws RateLimitException, MissingPermissionsException, DiscordException, IOException {
		Deck d = playerHands.get(player);
		sendPersonalMessage(player, "Your roles:");
		for(int i = 0; i < d.literal().size(); i++) {
			sendPersonalCard(player, d.literal().get(i));
			sendPersonalMessage(player, d.literal().get(i).getName());
		}
		sendPersonalMessage(player, "You have " + coins.get(player) + " coins.");
	}
	
	//Users
	public boolean isPlaying(String name) {
		boolean playing = false;
		for(int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getName().equals(name))
				playing = true;
		}
		return playing;
	}
	public boolean isValidOther(String name, IUser source) {
		boolean playing = false;
		for(int i = 0; i < playerList.size(); i++) {
			String s = playerList.get(i).getName();
			if(s.equals(name) && !s.equals(source.getName()))
				playing = true;
		}
		return playing;
	}
}
