import java.io.File;

public class DiscordBotLaunch {

	public static void main(String[] args) throws Exception {
		
		BotManager bm = new BotManager("MTk3OTMxMjczODY2NTc1ODcy.Clb1zA.0fCBRNplcik9Mu6lHN7xST3AD98");
		while (!bm.isReady()) { System.out.print(""); }
		System.out.println("Ready.");
		bm.updateChannels();
		System.out.println(bm.retrieveChannels());
		bm.setMainChannelOut("general");
		Handler h = bm.addHandler("message handler");
		
		//Testing grounds
		/*Card c = new Card(new File("Coup Pictures/Duke.PNG"), "Duke");
		bm.sendPicture(c, "le duke x d", bm.getChannelByName("general"));
		c = new Card(new File("Coup Pictures/Captain.PNG"), "Captain");
		bm.sendPicture(c, "beedle is a bitch", bm.getChannelByName("general"));
		c = new Card(new File("Coup Pictures/Assassin.PNG"), "Assassin");
		bm.sendPicture(c, ":)", bm.getChannelByName("general"));
		c = new Card(new File("Coup Pictures/Inquisitor.PNG"), "Inquisitor");
		bm.sendPicture(c, "<3", bm.getChannelByName("general"));
		c = new Card(new File("Coup Pictures/Contessa.PNG"), "Contessa");
		bm.sendPicture(c, "u suck", bm.getChannelByName("general"));
		c = new Card(new File("Coup Pictures/Ambassador.PNG"), "Ambassador");
		bm.sendPicture(c, "fuck congress", bm.getChannelByName("general"));*/
		
		//Card c = new Card(new File("Coup Pictures/Inquisitor.PNG"), "Inquisitor");
		//bm.sendPicture(c, "<3", bm.getChannelByName("general"));
		
	}
	
}
