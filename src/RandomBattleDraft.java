import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class RandomBattleDraft extends Game{

	private int numPlayers;
	private int round;
	private ArrayList<IUser> playerIndex;
	private ArrayList<PersonalListener> listeners;
	private ArrayList<ArrayList<Pokemon>> teams;
	private boolean[] ready;
	private ArrayList<ArrayList<Pokemon>> generated;
	
	public RandomBattleDraft(BotManager m, HashMap<IUser, IChannel> p) {
		super(m, p);	
		
		numPlayers = getPlayers().size();
		System.out.println("[game status] " + numPlayers + " players in game");
		
		try {
			PrintWriter writer = new PrintWriter("Pokemon Tiers.txt", "UTF-8");
			writer.print(allTiers());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		
		playerIndex = new ArrayList<IUser>(numPlayers);
		Iterator<IUser> iterator = getPlayers().keySet().iterator();
		for(int i = 0; i < numPlayers; i++)
			playerIndex.add(iterator.next());
		listeners = new ArrayList<PersonalListener>(numPlayers);
		teams = new ArrayList<ArrayList<Pokemon>>(numPlayers);
		ready = new boolean[numPlayers];
		generated = new ArrayList<ArrayList<Pokemon>>(numPlayers);
		start();
	}

	@Override
	public void start() {
		ArrayList<Pokemon> allPokemon = Pokemon.pokemonPlayers(numPlayers);
		
		Scanner in;
		try {
			for (Pokemon p : allPokemon) {
				
				in = new Scanner(new File("Pokemon Tiers.txt"));				
				System.out.println("Pokemon : " + p.name());
				Pattern pattern = Pattern.compile(".*" + p.name().toLowerCase() + ".*" + "\\: " + "(.+)");
				Matcher m;
				
				while(in.hasNextLine()) {
					String s = in.nextLine();
					m = pattern.matcher(s);
					if(m.matches()) {
						String tier = m.group(1);
						if(tier.charAt(0) == '(')
							tier = tier.substring(1, tier.length()-1);
						switch (tier) {
						case "LC":
							p.tier("LC 95");
							break;
						case "NFE":
							p.tier("NFE 90");
							break;
						case "LC Uber":
							p.tier("LC Uber 86");
							break;
						case "NU":
							p.tier("NU 86");
							break;
						case "BL3":
							p.tier("BL3 84");
							break;
						case "RU":
							p.tier("RU 82");
							break;
						case "BL2":
							p.tier("BL2 80");
							break;
						case "UU":
							p.tier("UU 78");
							break;
						case "BL":
							p.tier("BL 76");
							break;
						case "OU":
							p.tier("OU 74");
							break;
						case "CAP":
							p.tier("CAP 74");
							break;
						case "Unreleased":
							p.tier("Unreleased 70");
							break;
						case "Uber":
							p.tier("Uber 70");
							break;
						case "PU":
							p.tier("PU 88");
							break;
						case "BL4":
							p.tier("BL4 88");
							break;
						}
						System.out.println(s);
					}
				}
				in.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		for(int i = 0; i < numPlayers; i++) {
			ArrayList<Pokemon> team = new ArrayList<Pokemon>();
			for(int j = 0; j < 6; j++)
				team.add(allPokemon.remove(0));
			PersonalListener pl = new PersonalListener(getBot(), playerIndex.get(i), getPlayers().get(playerIndex.get(i)), this);
			getBot().registerListener(pl);
			listeners.add(pl);
			teams.add(new ArrayList<Pokemon>(6));
			generated.add(team);
			ready[i] = false;
		}
		for(round = 1; round <= 6; round++) {
			for (IUser player : playerIndex) {
				try {
					int tIndex = getIndex(player);
					int gIndex = (tIndex + round)%numPlayers;
					sendMessage(player, "Round " + round + "\nCurrent Team\n" + currentTeam(player) + "\n\nChoose from the following pokemon:\n" + pokemonLeft(gIndex));
				} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
					e.printStackTrace();
				}
			}
			while(!allReady()) {}
			unready();
		}
		for (IUser player : playerIndex) {
			try {
				sendMessage(player, "[game status] draft complete" + "\nFinal Team\n" + currentTeam(player));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				e.printStackTrace();
			}
		}
		try {
			getManager().sendMessage("[game status] draft complete.");
		} catch (MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}
		for (PersonalListener pl : listeners) {
			pl.unregister();
		}		
		
	}

	@Override
	public void parse(IMessage message) {
		
		IUser source = message.getAuthor();
		String content = message.getContent();
		
		System.out.println("[personal listener] " + source.getName() + ": " + content);
		/*if (content.equals("ready")) {
			ready[getIndex(source)] = true;
			System.out.println("[personal listener] " + source.getName() + " is ready.");
		}*/
		String[] c = content.split(" ");
		if (c[0].equals("choose")) {
			if(c.length == 2) {
				String s = c[1];
				if(NumberUtils.isNumber(s)) {
					int index = Integer.parseInt(s);
					if (index > 0 && index < 8-round) {
						index -= 1;
						int tIndex = getIndex(source);
						int gIndex = (tIndex + round)%numPlayers;
						teams.get(tIndex).add(generated.get(gIndex).remove(index));
						System.out.println("[personal listener] " + source.getName() + " has chosen a pokemon in round " + round + ".");
						try {
							sendMessage(source, "[game status] choice confirmed, waiting for other players");
						} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
							e.printStackTrace();
						}
						ready[tIndex] = true;
					}
				}
			}
		}
	}
	
	public void unready() {
		for(int i = 0; i < numPlayers; i++)
			ready[i] = false;
	}
	public boolean allReady() {
		boolean b = true;
		for(int i = 0; i < numPlayers; i++)
			b &= ready[i];
		return b;
	}
	public void sendMessage(IUser i, String message) throws RateLimitException, MissingPermissionsException, DiscordException {
		getManager().sendMessage(message, getPlayers().get(i));
	}
	public int getIndex(IUser i) {
		int index = 0;
		for(int j = 0; j < numPlayers; j++) {
			if (playerIndex.get(j).equals(i))
				index = j;
		}
		return index;
	}
	public String currentTeam(IUser i) {
		String s = "";
		int index = getIndex(i);
		ArrayList<Pokemon> team = teams.get(index);
		for(int j = 0; j < team.size(); j++) {
			s += "Pokemon " + (j+1) + ": " + team.get(j);
			if(j+1 != team.size())
				s += "\n";
		}
		if (s.equals(""))
			return "NO TEAM";
		return s;
	}
	public String pokemonLeft(int index) {
		String s = "";
		ArrayList<Pokemon> team = generated.get(index);
		for(int j = 0; j < team.size(); j++) {
			s += "Pokemon " + (j+1) + ": " + team.get(j);
			if(j+1 != team.size())
				s += "\n";
		}
		if (s.equals(""))
			return "NO POKEMON LEFT";
		return s;
	}
	
	public String allTiers() {
		
		String t = "";
		String content = null;
		URLConnection connection = null;
		try {
			connection = new URL("https://raw.githubusercontent.com/Zarel/Pokemon-Showdown/master/data/formats-data.js").openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			boolean level = false;
			String total = "";
			String pokemon = "";
			String tier = "";
			while(scanner.hasNext()) {
				String s = scanner.nextLine();
				Pattern p;
				Matcher m;
				if (!level) {
					p = Pattern.compile("\\t(.+)\\: \\{");
					m = p.matcher(s);
					if (m.matches()) {
						level = true;
						pokemon = m.group(1);
					}
				}
				else {
					p = Pattern.compile("\\t\\ttier\\: (.+),");
					m = p.matcher(s);
					if(m.matches()) {
						level = false;
						tier = m.group(1);
						tier = tier.substring(1, tier.length()-1);
						total = pokemon + ": " + tier;
					}
				}
				if(!total.equals("")) {
					t += total + "\n";
					total = "";
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

}
