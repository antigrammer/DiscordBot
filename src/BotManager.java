import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class BotManager {
	
	//Fields
	private MessageBot bot;
	String token;
	private HashMap<String, String> channels; //Name, ID
	private String mainChannelOut;
	private IChannel mainChannel;
	private HashMap<IUser, IChannel> players; //Player, Channel they sent !join from
	
	//Constructors
	public BotManager(String t) throws DiscordException {
		token = t;
		bot = new MessageBot(token, this);
		channels = new HashMap<String, String>();
		players = new HashMap<IUser, IChannel>();
	}
	
	//ChannelData
	public void addChannel(IChannel ic) {
		channels.put(ic.getName(), ic.getID());
	}
	public void removeChannel(IChannel ic) {
		channels.remove(ic.getName());
	}
	public void updateChannels() {
		Collection<IChannel> c = bot.getClient().getChannels(true);
		Iterator<IChannel> iterator = c.iterator();
		System.out.println("[manager status] number of channels: " + c.size());
		for(int i = 0; i < c.size(); i++) {
			IChannel ic = iterator.next();
			addChannel(ic);
		}
	}
	public String retrieveChannels() {
		String s = "[manager status]\n<CHANNELS>\n";
		String c = "";
		Iterator<String> channelNames = channels.keySet().iterator();
		for(int i = 0; i < channels.size(); i++) {
			c = channelNames.next();
			s += c + ": " + channels.get(c);
			if (i+1 != channels.size())
				s += "\n";
			else
				s += "\n<END OF CHANNELS>";
		}
		return s;
	}
	public void setMainChannelOut(String s) throws Exception {
		if (channels.containsKey(s)) {
			mainChannelOut = channels.get(s);
			mainChannel = getChannelByName(s);
			System.out.println("[manager status] main output channel is now " + s + ".");
			sendMessage("This is now the main output channel.");
		}
		else throw new Exception("Channel <" + s + "> does not exist.");
	}
	public IChannel getMainChannelOut() {
		return bot.getClient().getChannelByID(mainChannelOut);
	}
	public IChannel getChannelByName(String name) {
		return bot.getClient().getChannelByID(channels.get(name));
	}
	
	//PlayerData
	public String retrievePlayers() {
		String s = "[manager status]\n<PLAYERS>\n";
		Iterator<IUser> playerNames = players.keySet().iterator();
		for(int i = 0; i < players.size(); i++) {
			s += playerNames.next().getName();
			if (i+1 != players.size())
				s += "\n";
			else
				s += "\n<END OF PLAYERS>";
		}
		return s;
	}
	public IUser getUserByName(String name) {
		Set<IUser> p = players.keySet();
		for (IUser i : p) {
			if (i.getName().equals(name))
				return i;
		}
		return null;
	}
	
	//Messaging
	public IMessage sendMessage(String s) throws MissingPermissionsException, DiscordException {
		try {
			return mainChannel.sendMessage(s);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendMessage(String s)");
			return sendMessage(s);
		}
	}
	public IMessage sendMessage(String s, IChannel c) throws MissingPermissionsException, DiscordException {
		try {
			return c.sendMessage(s);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendMessage(String s, IChannel c)");
			return sendMessage(s, c);
		}
	}
	public IMessage talkMessage(String s) throws MissingPermissionsException, DiscordException {
		try {
			return mainChannel.sendMessage(s, true);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying talkMessage(String s)");
			return talkMessage(s);
		}
	}
	public IMessage talkMessage(String s, IChannel c) throws MissingPermissionsException, DiscordException {
		try {
			return c.sendMessage(s, true);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying talkMessage(String s, IChannel c)");
			return talkMessage(s, c);
		}
	}
	public void sendPicture(Card c) throws IOException, MissingPermissionsException, DiscordException {
		try {
			mainChannel.sendFile(c.getPicture());
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendFile(Card c)");
			sendPicture(c);
		}
	}
	public void sendPicture(Card c, IChannel channel) throws IOException, MissingPermissionsException, DiscordException {
		try {
			channel.sendFile(c.getPicture());
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendFile(Card c, IChannel channel)");
			sendPicture(c, channel);
		}
	}
	public void sendPicture(Card c, String caption) throws IOException, MissingPermissionsException, DiscordException {
		try {
			mainChannel.sendFile(c.getPicture(), caption);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendFile(Card c, String caption)");
			sendPicture(c, caption);
		}
	}
	public void sendPicture(Card c, String caption, IChannel channel) throws IOException, MissingPermissionsException, DiscordException {
		try {
			channel.sendFile(c.getPicture(), caption);
		} catch (RateLimitException e) {
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {}
			System.out.println("[bot status] rate limtted, retrying sendFile(Card c, String caption, IChannel channel)");
			sendPicture(c, caption, channel);
		}
	}
	
	//BotActions
	public MessageBot getBot() {
		return bot;
	}
	public boolean isReady() {
		return bot.isReady();
	}
	public Handler addHandler(String s) {
		switch(s) {
		case "message handler":
			MessageListener h = new MessageListener(bot);
			bot.registerListener(h);
			return h;
		default:
			return null;
		}
	}
	public void parseCommand(String command, String content, IChannel channel, IUser author) throws RateLimitException, MissingPermissionsException, DiscordException {
		System.out.println("[command] <command: " + command + "||" + "content: " + content + ">");
		switch(command) {
		//Basic
		case "echo":
			sendMessage(content, channel);
			break;
		case "echotalk":
			talkMessage(content, channel);
			break;
		//Stupid
		case "annoying":
			bot.setAnnoying(true);
			break;
		case "stopit":
			bot.setAnnoying(false);
			break;
		case "fuckyou":
			//TODO fuckyou to a specific person
			talkMessage("What the fuck did you just fucking say about me, you little bitch? I’ll have you know I graduated top of my class in the Navy Seals, and I’ve been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I’m the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You’re fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that’s just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little “clever” comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn’t, you didn’t, and now you’re paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You’re fucking dead, kiddo.", channel);
			break;
		case "hello":
			sendMessage("hello world", channel);
			break;
		//Information
		case "join":
			players.put(author, channel);
			String sJoin = "[manager status] " + author.getName() + " has joined the player list";
			sendMessage(sJoin);
			break;
		case "leave":
			players.remove(author);
			String sLeave = "[manager status] " + author.getName() + " has left the player list";
			sendMessage(sLeave);
			break;
		case "players":
			sendMessage(retrievePlayers());
			break;
		case "joinchannel":
			addChannel(getChannelByName(content));
			break;
		case "leavechannel":
			removeChannel(getChannelByName(content));
			break;
		case "channels":
			sendMessage(retrieveChannels());
			break;
		case "main":
			try {
				setMainChannelOut(channel.getName());
			} catch (Exception e) {}
			break;
		//Actions
		case "start":
			if(players.size() != 0) {
				switch(content) {
				case "pokemon":
					new RandomBattleDraft(this, players);
					break;
				case "coup":
					if (command.equals("inquisitor")) 
						new Coup(this, players, true);
					else if (command.equals("ambassador")) {
						new Coup(this, players, false);
					}
					break;
				}
			}
			break;
		case "exit":
			if(channel.equals(getMainChannelOut())) {
				System.out.println("[bot status] bot exiting.");
				sendMessage("Bot will now exit.");
				System.exit(0);
			}
		}
	}

}
