import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import salil.resources.util.Random;

public enum Pokemon {

	BULBASAUR ("Grass","Poison"), IVYSAUR ("Grass","Poison"), VENUSAUR ("Grass","Poison"), CHARMANDER ("Fire",""), CHARMELEON ("Fire",""), CHARIZARD ("Fire","Flying"), SQUIRTLE ("Water",""), WARTORTLE ("Water",""), BLASTOISE ("Water",""), CATERPIE ("Bug",""), 
	METAPOD ("Bug",""), BUTTERFREE ("Bug","Flying"), WEEDLE ("Bug","Poison"), KAKUNA ("Bug","Poison"), BEEDRILL ("Bug","Poison"), PIDGEY ("Normal","Flying"), PIDGEOTTO ("Normal","Flying"), PIDGEOT ("Normal","Flying"), RATTATA ("Normal",""), RATICATE ("Normal",""), 
	SPEAROW ("Normal","Flying"), FEAROW ("Normal","Flying"), EKANS ("Poison",""), ARBOK ("Poison",""), PIKACHU ("Electric",""), RAICHU ("Electric",""), SANDSHREW ("Ground",""), SANDSLASH ("Ground",""), NIDORANF ("Poison",""), NIDORINA ("Poison",""), 
	NIDOQUEEN ("Poison","Ground"), NIDORANM ("Poison",""), NIDORINO ("Poison",""), NIDOKING ("Poison","Ground"), CLEFAIRY ("Fairy",""), CLEFABLE ("Fairy",""), VULPIX ("Fire",""), NINETALES ("Fire",""), JIGGLYPUFF ("Normal","Fairy"), WIGGLYTUFF ("Normal","Fairy"), 
	ZUBAT ("Poison","Flying"), GOLBAT ("Poison","Flying"), ODDISH ("Grass","Poison"), GLOOM ("Grass","Poison"), VILEPLUME ("Grass","Poison"), PARAS ("Bug","Grass"), PARASECT ("Bug","Grass"), VENONAT ("Bug","Poison"), VENOMOTH ("Bug","Poison"), DIGLETT ("Ground",""), 
	DUGTRIO ("Ground",""), MEOWTH ("Normal",""), PERSIAN ("Normal",""), PSYDUCK ("Water",""), GOLDUCK ("Water",""), MANKEY ("Fighting",""), PRIMEAPE ("Fighting",""), GROWLITHE ("Fire",""), ARCANINE ("Fire",""), POLIWAG ("Water",""), 
	POLIWHIRL ("Water",""), POLIWRATH ("Water","Fighting"), ABRA ("Psychic",""), KADABRA ("Psychic",""), ALAKAZAM ("Psychic",""), MACHOP ("Fighting",""), MACHOKE ("Fighting",""), MACHAMP ("Fighting",""), BELLSPROUT ("Grass","Poison"), WEEPINBELL ("Grass","Poison"), 
	VICTREEBEL ("Grass","Poison"), TENTACOOL ("Water","Poison"), TENTACRUEL ("Water","Poison"), GEODUDE ("Rock","Ground"), GRAVELER ("Rock","Ground"), GOLEM ("Rock","Ground"), PONYTA ("Fire",""), RAPIDASH ("Fire",""), SLOWPOKE ("Water","Psychic"), SLOWBRO ("Water","Psychic"), 
	MAGNEMITE ("Electric","Steel"), MAGNETON ("Electric","Steel"), FARFETCHD ("Normal","Flying"), DODUO ("Normal","Flying"), DODRIO ("Normal","Flying"), SEEL ("Water",""), DEWGONG ("Water","Ice"), GRIMER ("Poison",""), MUK ("Poison",""), SHELLDER ("Water",""), 
	CLOYSTER ("Water","Ice"), GASTLY ("Ghost","Poison"), HAUNTER ("Ghost","Poison"), GENGAR ("Ghost","Poison"), ONIX ("Rock","Ground"), DROWZEE ("Psychic",""), HYPNO ("Psychic",""), KRABBY ("Water",""), KINGLER ("Water",""), VOLTORB ("Electric",""), 
	ELECTRODE ("Electric",""), EXEGGCUTE ("Grass","Psychic"), EXEGGUTOR ("Grass","Psychic"), CUBONE ("Ground",""), MAROWAK ("Ground",""), HITMONLEE ("Fighting",""), HITMONCHAN ("Fighting",""), LICKITUNG ("Normal",""), KOFFING ("Poison",""), WEEZING ("Poison",""), 
	RHYHORN ("Ground","Rock"), RHYDON ("Ground","Rock"), CHANSEY ("Normal",""), TANGELA ("Grass",""), KANGASKHAN ("Normal",""), HORSEA ("Water",""), SEADRA ("Water",""), GOLDEEN ("Water",""), SEAKING ("Water",""), STARYU ("Water",""), 
	STARMIE ("Water","Psychic"), MRMIME ("Psychic","Fairy"), SCYTHER ("Bug","Flying"), JYNX ("Ice","Psychic"), ELECTABUZZ ("Electric",""), MAGMAR ("Fire",""), PINSIR ("Bug",""), TAUROS ("Normal",""), MAGIKARP ("Water",""), GYARADOS ("Water","Flying"), 
	LAPRAS ("Water","Ice"), DITTO ("Normal",""), EEVEE ("Normal",""), VAPOREON ("Water",""), JOLTEON ("Electric",""), FLAREON ("Fire",""), PORYGON ("Normal",""), OMANYTE ("Rock","Water"), OMASTAR ("Rock","Water"), KABUTO ("Rock","Water"), 
	KABUTOPS ("Rock","Water"), AERODACTYL ("Rock","Flying"), SNORLAX ("Normal",""), ARTICUNO ("Ice","Flying"), ZAPDOS ("Electric","Flying"), MOLTRES ("Fire","Flying"), DRATINI ("Dragon",""), DRAGONAIR ("Dragon",""), DRAGONITE ("Dragon","Flying"), MEWTWO ("Psychic",""), 
	MEW ("Psychic",""), CHIKORITA ("Grass",""), BAYLEEF ("Grass",""), MEGANIUM ("Grass",""), CYNDAQUIL ("Fire",""), QUILAVA ("Fire",""), TYPHLOSION ("Fire",""), TOTODILE ("Water",""), CROCONAW ("Water",""), FERALIGATR ("Water",""), 
	SENTRET ("Normal",""), FURRET ("Normal",""), HOOTHOOT ("Normal","Flying"), NOCTOWL ("Normal","Flying"), LEDYBA ("Bug","Flying"), LEDIAN ("Bug","Flying"), SPINARAK ("Bug","Poison"), ARIADOS ("Bug","Poison"), CROBAT ("Poison","Flying"), CHINCHOU ("Water","Electric"), 
	LANTURN ("Water","Electric"), PICHU ("Electric",""), CLEFFA ("Fairy",""), IGGLYBUFF ("Normal","Fairy"), TOGEPI ("Fairy",""), TOGETIC ("Fairy","Flying"), NATU ("Psychic","Flying"), XATU ("Psychic","Flying"), MAREEP ("Electric",""), FLAAFFY ("Electric",""), 
	AMPHAROS ("Electric",""), BELLOSSOM ("Grass",""), MARILL ("Water","Fairy"), AZUMARILL ("Water","Fairy"), SUDOWOODO ("Rock",""), POLITOED ("Water",""), HOPPIP ("Grass","Flying"), SKIPLOOM ("Grass","Flying"), JUMPLUFF ("Grass","Flying"), AIPOM ("Normal",""), 
	SUNKERN ("Grass",""), SUNFLORA ("Grass",""), YANMA ("Bug","Flying"), WOOPER ("Water","Ground"), QUAGSIRE ("Water","Ground"), ESPEON ("Psychic",""), UMBREON ("Dark",""), MURKROW ("Dark","Flying"), SLOWKING ("Water","Psychic"), MISDREAVUS ("Ghost",""), 
	UNOWN ("Psychic",""), WOBBUFFET ("Psychic",""), GIRAFARIG ("Normal","Psychic"), PINECO ("Bug",""), FORRETRESS ("Bug","Steel"), DUNSPARCE ("Normal",""), GLIGAR ("Ground","Flying"), STEELIX ("Steel","Ground"), SNUBBULL ("Fairy",""), GRANBULL ("Fairy",""), 
	QWILFISH ("Water","Poison"), SCIZOR ("Bug","Steel"), SHUCKLE ("Bug","Rock"), HERACROSS ("Bug","Fighting"), SNEASEL ("Dark","Ice"), TEDDIURSA ("Normal",""), URSARING ("Normal",""), SLUGMA ("Fire",""), MAGCARGO ("Fire","Rock"), SWINUB ("Ice","Ground"), 
	PILOSWINE ("Ice","Ground"), CORSOLA ("Water","Rock"), REMORAID ("Water",""), OCTILLERY ("Water",""), DELIBIRD ("Ice","Flying"), MANTINE ("Water","Flying"), SKARMORY ("Steel","Flying"), HOUNDOUR ("Dark","Fire"), HOUNDOOM ("Dark","Fire"), KINGDRA ("Water","Dragon"), 
	PHANPY ("Ground",""), DONPHAN ("Ground",""), PORYGON2 ("Normal",""), STANTLER ("Normal",""), SMEARGLE ("Normal",""), TYROGUE ("Fighting",""), HITMONTOP ("Fighting",""), SMOOCHUM ("Ice","Psychic"), ELEKID ("Electric",""), MAGBY ("Fire",""), 
	MILTANK ("Normal",""), BLISSEY ("Normal",""), RAIKOU ("Electric",""), ENTEI ("Fire",""), SUICUNE ("Water",""), LARVITAR ("Rock","Ground"), PUPITAR ("Rock","Ground"), TYRANITAR ("Rock","Dark"), LUGIA ("Psychic","Flying"), HOOH ("Fire","Flying"), 
	CELEBI ("Psychic","Grass"), TREECKO ("Grass",""), GROVYLE ("Grass",""), SCEPTILE ("Grass",""), TORCHIC ("Fire",""), COMBUSKEN ("Fire","Fighting"), BLAZIKEN ("Fire","Fighting"), MUDKIP ("Water",""), MARSHTOMP ("Water","Ground"), SWAMPERT ("Water","Ground"), 
	POOCHYENA ("Dark",""), MIGHTYENA ("Dark",""), ZIGZAGOON ("Normal",""), LINOONE ("Normal",""), WURMPLE ("Bug",""), SILCOON ("Bug",""), BEAUTIFLY ("Bug","Flying"), CASCOON ("Bug",""), DUSTOX ("Bug","Poison"), LOTAD ("Water","Grass"), 
	LOMBRE ("Water","Grass"), LUDICOLO ("Water","Grass"), SEEDOT ("Grass",""), NUZLEAF ("Grass","Dark"), SHIFTRY ("Grass","Dark"), TAILLOW ("Normal","Flying"), SWELLOW ("Normal","Flying"), WINGULL ("Water","Flying"), PELIPPER ("Water","Flying"), RALTS ("Psychic","Fairy"), 
	KIRLIA ("Psychic","Fairy"), GARDEVOIR ("Psychic","Fairy"), SURSKIT ("Bug","Water"), MASQUERAIN ("Bug","Flying"), SHROOMISH ("Grass",""), BRELOOM ("Grass","Fighting"), SLAKOTH ("Normal",""), VIGOROTH ("Normal",""), SLAKING ("Normal",""), NINCADA ("Bug","Ground"), 
	NINJASK ("Bug","Flying"), SHEDINJA ("Bug","Ghost"), WHISMUR ("Normal",""), LOUDRED ("Normal",""), EXPLOUD ("Normal",""), MAKUHITA ("Fighting",""), HARIYAMA ("Fighting",""), AZURILL ("Normal","Fairy"), NOSEPASS ("Rock",""), SKITTY ("Normal",""), 
	DELCATTY ("Normal",""), SABLEYE ("Dark","Ghost"), MAWILE ("Steel","Fairy"), ARON ("Steel","Rock"), LAIRON ("Steel","Rock"), AGGRON ("Steel","Rock"), MEDITITE ("Fighting","Psychic"), MEDICHAM ("Fighting","Psychic"), ELECTRIKE ("Electric",""), MANECTRIC ("Electric",""), 
	PLUSLE ("Electric",""), MINUN ("Electric",""), VOLBEAT ("Bug",""), ILLUMISE ("Bug",""), ROSELIA ("Grass","Poison"), GULPIN ("Poison",""), SWALOT ("Poison",""), CARVANHA ("Water","Dark"), SHARPEDO ("Water","Dark"), WAILMER ("Water",""), 
	WAILORD ("Water",""), NUMEL ("Fire","Ground"), CAMERUPT ("Fire","Ground"), TORKOAL ("Fire",""), SPOINK ("Psychic",""), GRUMPIG ("Psychic",""), SPINDA ("Normal",""), TRAPINCH ("Ground",""), VIBRAVA ("Ground","Dragon"), FLYGON ("Ground","Dragon"), 
	CACNEA ("Grass",""), CACTURNE ("Grass","Dark"), SWABLU ("Normal","Flying"), ALTARIA ("Dragon","Flying"), ZANGOOSE ("Normal",""), SEVIPER ("Poison",""), LUNATONE ("Rock","Psychic"), SOLROCK ("Rock","Psychic"), BARBOACH ("Water","Ground"), WHISCASH ("Water","Ground"), 
	CORPHISH ("Water",""), CRAWDAUNT ("Water","Dark"), BALTOY ("Ground","Psychic"), CLAYDOL ("Ground","Psychic"), LILEEP ("Rock","Grass"), CRADILY ("Rock","Grass"), ANORITH ("Rock","Bug"), ARMALDO ("Rock","Bug"), FEEBAS ("Water",""), MILOTIC ("Water",""), 
	CASTFORM ("Normal",""), KECLEON ("Normal",""), SHUPPET ("Ghost",""), BANETTE ("Ghost",""), DUSKULL ("Ghost",""), DUSCLOPS ("Ghost",""), TROPIUS ("Grass","Flying"), CHIMECHO ("Psychic",""), ABSOL ("Dark",""), WYNAUT ("Psychic",""), 
	SNORUNT ("Ice",""), GLALIE ("Ice",""), SPHEAL ("Ice","Water"), SEALEO ("Ice","Water"), WALREIN ("Ice","Water"), CLAMPERL ("Water",""), HUNTAIL ("Water",""), GOREBYSS ("Water",""), RELICANTH ("Water","Rock"), LUVDISC ("Water",""), 
	BAGON ("Dragon",""), SHELGON ("Dragon",""), SALAMENCE ("Dragon","Flying"), BELDUM ("Steel","Psychic"), METANG ("Steel","Psychic"), METAGROSS ("Steel","Psychic"), REGIROCK ("Rock",""), REGICE ("Ice",""), REGISTEEL ("Steel",""), LATIAS ("Dragon","Psychic"), 
	LATIOS ("Dragon","Psychic"), KYOGRE ("Water",""), GROUDON ("Ground",""), RAYQUAZA ("Dragon","Flying"), JIRACHI ("Steel","Psychic"), DEOXYS ("Psychic",""), TURTWIG ("Grass",""), GROTLE ("Grass",""), TORTERRA ("Grass","Ground"), CHIMCHAR ("Fire",""), 
	MONFERNO ("Fire","Fighting"), INFERNAPE ("Fire","Fighting"), PIPLUP ("Water",""), PRINPLUP ("Water",""), EMPOLEON ("Water","Steel"), STARLY ("Normal","Flying"), STARAVIA ("Normal","Flying"), STARAPTOR ("Normal","Flying"), BIDOOF ("Normal",""), BIBAREL ("Normal","Water"), 
	KRICKETOT ("Bug",""), KRICKETUNE ("Bug",""), SHINX ("Electric",""), LUXIO ("Electric",""), LUXRAY ("Electric",""), BUDEW ("Grass","Poison"), ROSERADE ("Grass","Poison"), CRANIDOS ("Rock",""), RAMPARDOS ("Rock",""), SHIELDON ("Rock","Steel"), 
	BASTIODON ("Rock","Steel"), BURMY ("Bug",""), WORMADAM ("Bug","Steel"), MOTHIM ("Bug","Flying"), COMBEE ("Bug","Flying"), VESPIQUEN ("Bug","Flying"), PACHIRISU ("Electric",""), BUIZEL ("Water",""), FLOATZEL ("Water",""), CHERUBI ("Grass",""), 
	CHERRIM ("Grass",""), SHELLOS ("Water",""), GASTRODON ("Water","Ground"), AMBIPOM ("Normal",""), DRIFLOON ("Ghost","Flying"), DRIFBLIM ("Ghost","Flying"), BUNEARY ("Normal",""), LOPUNNY ("Normal",""), MISMAGIUS ("Ghost",""), HONCHKROW ("Dark","Flying"), 
	GLAMEOW ("Normal",""), PURUGLY ("Normal",""), CHINGLING ("Psychic",""), STUNKY ("Poison","Dark"), SKUNTANK ("Poison","Dark"), BRONZOR ("Steel","Psychic"), BRONZONG ("Steel","Psychic"), BONSLY ("Rock",""), MIMEJR ("Psychic","Fairy"), HAPPINY ("Normal",""), 
	CHATOT ("Normal","Flying"), SPIRITOMB ("Ghost","Dark"), GIBLE ("Dragon","Ground"), GABITE ("Dragon","Ground"), GARCHOMP ("Dragon","Ground"), MUNCHLAX ("Normal",""), RIOLU ("Fighting",""), LUCARIO ("Fighting","Steel"), HIPPOPOTAS ("Ground",""), HIPPOWDON ("Ground",""), 
	SKORUPI ("Poison","Bug"), DRAPION ("Poison","Dark"), CROAGUNK ("Poison","Fighting"), TOXICROAK ("Poison","Fighting"), CARNIVINE ("Grass",""), FINNEON ("Water",""), LUMINEON ("Water",""), MANTYKE ("Water","Flying"), SNOVER ("Grass","Ice"), ABOMASNOW ("Grass","Ice"), 
	WEAVILE ("Dark","Ice"), MAGNEZONE ("Electric","Steel"), LICKILICKY ("Normal",""), RHYPERIOR ("Ground","Rock"), TANGROWTH ("Grass",""), ELECTIVIRE ("Electric",""), MAGMORTAR ("Fire",""), TOGEKISS ("Fairy","Flying"), YANMEGA ("Bug","Flying"), LEAFEON ("Grass",""), 
	GLACEON ("Ice",""), GLISCOR ("Ground","Flying"), MAMOSWINE ("Ice","Ground"), PORYGONZ ("Normal",""), GALLADE ("Psychic","Fighting"), PROBOPASS ("Rock","Steel"), DUSKNOIR ("Ghost",""), FROSLASS ("Ice","Ghost"), ROTOM ("Electric","Ghost"), UXIE ("Psychic",""), 
	MESPRIT ("Psychic",""), AZELF ("Psychic",""), DIALGA ("Steel","Dragon"), PALKIA ("Water","Dragon"), HEATRAN ("Fire","Steel"), REGIGIGAS ("Normal",""), GIRATINA ("Ghost","Dragon"), CRESSELIA ("Psychic",""), PHIONE ("Water",""), MANAPHY ("Water",""), 
	DARKRAI ("Dark",""), SHAYMIN ("Grass","Flying"), ARCEUS ("Normal",""), VICTINI ("Psychic","Fire"), SNIVY ("Grass",""), SERVINE ("Grass",""), SERPERIOR ("Grass",""), TEPIG ("Fire",""), PIGNITE ("Fire","Fighting"), EMBOAR ("Fire","Fighting"), 
	OSHAWOTT ("Water",""), DEWOTT ("Water",""), SAMUROTT ("Water",""), PATRAT ("Normal",""), WATCHOG ("Normal",""), LILLIPUP ("Normal",""), HERDIER ("Normal",""), STOUTLAND ("Normal",""), PURRLOIN ("Dark",""), LIEPARD ("Dark",""), 
	PANSAGE ("Grass",""), SIMISAGE ("Grass",""), PANSEAR ("Fire",""), SIMISEAR ("Fire",""), PANPOUR ("Water",""), SIMIPOUR ("Water",""), MUNNA ("Psychic",""), MUSHARNA ("Psychic",""), PIDOVE ("Normal","Flying"), TRANQUILL ("Normal","Flying"), 
	UNFEZANT ("Normal","Flying"), BLITZLE ("Electric",""), ZEBSTRIKA ("Electric",""), ROGGENROLA ("Rock",""), BOLDORE ("Rock",""), GIGALITH ("Rock",""), WOOBAT ("Psychic","Flying"), SWOOBAT ("Psychic","Flying"), DRILBUR ("Ground",""), EXCADRILL ("Ground","Steel"), 
	AUDINO ("Normal",""), TIMBURR ("Fighting",""), GURDURR ("Fighting",""), CONKELDURR ("Fighting",""), TYMPOLE ("Water",""), PALPITOAD ("Water","Ground"), SEISMITOAD ("Water","Ground"), THROH ("Fighting",""), SAWK ("Fighting",""), SEWADDLE ("Bug","Grass"), 
	SWADLOON ("Bug","Grass"), LEAVANNY ("Bug","Grass"), VENIPEDE ("Bug","Poison"), WHIRLIPEDE ("Bug","Poison"), SCOLIPEDE ("Bug","Poison"), COTTONEE ("Grass",""), WHIMSICOTT ("Grass","Fairy"), PETILIL ("Grass",""), LILLIGANT ("Grass",""), BASCULIN ("Water",""), 
	SANDILE ("Ground","Dark"), KROKOROK ("Ground","Dark"), KROOKODILE ("Ground","Dark"), DARUMAKA ("Fire",""), DARMANITAN ("Fire",""), MARACTUS ("Grass",""), DWEBBLE ("Bug","Rock"), CRUSTLE ("Bug","Rock"), SCRAGGY ("Dark","Fighting"), SCRAFTY ("Dark","Fighting"), 
	SIGILYPH ("Psychic","Flying"), YAMASK ("Ghost",""), COFAGRIGUS ("Ghost",""), TIRTOUGA ("Water","Rock"), CARRACOSTA ("Water","Rock"), ARCHEN ("Rock","Flying"), ARCHEOPS ("Rock","Flying"), TRUBBISH ("Poison",""), GARBODOR ("Poison",""), ZORUA ("Dark",""), 
	ZOROARK ("Dark",""), MINCCINO ("Normal",""), CINCCINO ("Normal",""), GOTHITA ("Psychic",""), GOTHORITA ("Psychic",""), GOTHITELLE ("Psychic",""), SOLOSIS ("Psychic",""), DUOSION ("Psychic",""), REUNICLUS ("Psychic",""), DUCKLETT ("Water","Flying"), 
	SWANNA ("Water","Flying"), VANILLITE ("Ice",""), VANILLISH ("Ice",""), VANILLUXE ("Ice",""), DEERLING ("Normal","Grass"), SAWSBUCK ("Normal","Grass"), EMOLGA ("Electric","Flying"), KARRABLAST ("Bug",""), ESCAVALIER ("Bug","Steel"), FOONGUS ("Grass","Poison"), 
	AMOONGUSS ("Grass","Poison"), FRILLISH ("Water","Ghost"), JELLICENT ("Water","Ghost"), ALOMOMOLA ("Water",""), JOLTIK ("Bug","Electric"), GALVANTULA ("Bug","Electric"), FERROSEED ("Grass","Steel"), FERROTHORN ("Grass","Steel"), KLINK ("Steel",""), KLANG ("Steel",""), 
	KLINKLANG ("Steel",""), TYNAMO ("Electric",""), EELEKTRIK ("Electric",""), EELEKTROSS ("Electric",""), ELGYEM ("Psychic",""), BEHEEYEM ("Psychic",""), LITWICK ("Ghost","Fire"), LAMPENT ("Ghost","Fire"), CHANDELURE ("Ghost","Fire"), AXEW ("Dragon",""), 
	FRAXURE ("Dragon",""), HAXORUS ("Dragon",""), CUBCHOO ("Ice",""), BEARTIC ("Ice",""), CRYOGONAL ("Ice",""), SHELMET ("Bug",""), ACCELGOR ("Bug",""), STUNFISK ("Ground","Electric"), MIENFOO ("Fighting",""), MIENSHAO ("Fighting",""), 
	DRUDDIGON ("Dragon",""), GOLETT ("Ground","Ghost"), GOLURK ("Ground","Ghost"), PAWNIARD ("Dark","Steel"), BISHARP ("Dark","Steel"), BOUFFALANT ("Normal",""), RUFFLET ("Normal","Flying"), BRAVIARY ("Normal","Flying"), VULLABY ("Dark","Flying"), MANDIBUZZ ("Dark","Flying"), 
	HEATMOR ("Fire",""), DURANT ("Bug","Steel"), DEINO ("Dark","Dragon"), ZWEILOUS ("Dark","Dragon"), HYDREIGON ("Dark","Dragon"), LARVESTA ("Bug","Fire"), VOLCARONA ("Bug","Fire"), COBALION ("Steel","Fighting"), TERRAKION ("Rock","Fighting"), VIRIZION ("Grass","Fighting"), 
	TORNADUS ("Flying",""), THUNDURUS ("Electric","Flying"), RESHIRAM ("Dragon","Fire"), ZEKROM ("Dragon","Electric"), LANDORUS ("Ground","Flying"), KYUREM ("Dragon","Ice"), KELDEO ("Water","Fighting"), MELOETTA ("Normal","Psychic"), GENESECT ("Bug","Steel"), CHESPIN ("Grass",""), 
	QUILLADIN ("Grass",""), CHESNAUGHT ("Grass","Fighting"), FENNEKIN ("Fire",""), BRAIXEN ("Fire",""), DELPHOX ("Fire","Psychic"), FROAKIE ("Water",""), FROGADIER ("Water",""), GRENINJA ("Water","Dark"), BUNNELBY ("Normal",""), DIGGERSBY ("Normal","Ground"), 
	FLETCHLING ("Normal","Flying"), FLETCHINDER ("Fire","Flying"), TALONFLAME ("Fire","Flying"), SCATTERBUG ("Bug",""), SPEWPA ("Bug",""), VIVILLON ("Bug","Flying"), LITLEO ("Fire","Normal"), PYROAR ("Fire","Normal"), FLAB�B� ("Fairy",""), FLOETTE ("Fairy",""), 
	FLORGES ("Fairy",""), SKIDDO ("Grass",""), GOGOAT ("Grass",""), PANCHAM ("Fighting",""), PANGORO ("Fighting","Dark"), FURFROU ("Normal",""), ESPURR ("Psychic",""), MEOWSTIC ("Psychic",""), HONEDGE ("Steel","Ghost"), DOUBLADE ("Steel","Ghost"), 
	AEGISLASH ("Steel","Ghost"), SPRITZEE ("Fairy",""), AROMATISSE ("Fairy",""), SWIRLIX ("Fairy",""), SLURPUFF ("Fairy",""), INKAY ("Dark","Psychic"), MALAMAR ("Dark","Psychic"), BINACLE ("Rock","Water"), BARBARACLE ("Rock","Water"), SKRELP ("Poison","Water"), 
	HAWLUCHA ("Fighting","Flying"), DEDENNE ("Electric","Fairy"), CARBINK ("Rock","Fairy"), GOOMY ("Dragon",""), SLIGGOO ("Dragon",""), GOODRA ("Dragon",""), KLEFKI ("Steel","Fairy"), PHANTUMP ("Ghost","Grass"), TREVENANT ("Ghost","Grass"), PUMPKABOO ("Ghost","Grass"), 
	GOURGEIST ("Ghost","Grass"), BERGMITE ("Ice",""), AVALUGG ("Ice",""), NOIBAT ("Flying","Dragon"), NOIVERN ("Flying","Dragon"), XERNEAS ("Fairy",""), YVELTAL ("Dark","Flying"), ZYGARDE ("Dragon","Ground"), DIANCIE ("Rock","Fairy"), HOOPA ("Psychic","Ghost"), 
	VOLCANION ("Fire","Water"), ROWLET ("Grass","Flying"), DARTRIX ("Grass","Flying"), DECIDUEYE ("Grass","Ghost"), LITTEN ("Fire",""), TORRACAT ("Fire",""), INCINEROAR ("Fire","Dark"), POPPLIO ("Water",""), BRIONNE ("Water",""), PRIMARINA ("Water","Fairy"), 
	PIKIPEK ("Normal","Flying"), TRUMBEAK ("Normal","Flying"), TOUCANNON ("Normal","Flying"), YUNGOOS ("Normal",""), GUMSHOOS ("Normal",""), GRUBBIN ("Bug",""), CHARJABUG ("Bug","Electric"), VIKAVOLT ("Bug","Electric"), CRABRAWLER ("Fighting",""), CRABOMINABLE ("Fighting","Ice"), 
	ORICORIO ("Fire","Flying"), CUTIEFLY ("Bug","Fairy"), RIBOMBEE ("Bug","Fairy"), ROCKRUFF ("Rock",""), LYCANROC ("Rock",""), WISHIWASHI ("Water",""), MAREANIE ("Poison","Water"), TOXAPEX ("Poison","Water"), MUDBRAY ("Ground",""), MUDSDALE ("Ground",""), 
	DEWPIDER ("Water","Bug"), ARAQUANID ("Water","Bug"), FOMANTIS ("Grass",""), LURANTIS ("Grass",""), MORELULL ("Grass","Fairy"), SHIINOTIC ("Grass","Fairy"), SALANDIT ("Poison","Fire"), SALAZZLE ("Poison","Fire"), STUFFUL ("Normal","Fighting"), BEWEAR ("Normal","Fighting"), 
	BOUNSWEET ("Grass",""), STEENEE ("Grass",""), TSAREENA ("Grass",""), COMFEY ("Fairy",""), ORANGURU ("Normal","Psychic"), PASSIMIAN ("Fighting",""), WIMPOD ("Bug","Water"), GOLISOPOD ("Bug","Water"), SANDYGAST ("Ghost","Ground"), PALOSSAND ("Ghost","Ground"), 
	PYUKUMUKU ("Water",""), TYPENULL ("Normal",""), SILVALLY ("Normal",""), MINIOR ("Rock","Flying"), KOMALA ("Normal",""), TURTONATOR ("Fire","Dragon"), TOGEDEMARU ("Electric","Steel"), MIMIKYU ("Ghost","Fairy"), BRUXISH ("Water","Psychic"), DRAMPA ("Normal","Dragon"), 
	DHELMISE ("Ghost","Grass"), JANGMOO ("Dragon",""), HAKAMOO ("Dragon","Fighting"), KOMMOO ("Dragon","Fighting"), TAPUKOKO ("Electric","Fairy"), TAPULELE ("Psychic","Fairy"), TAPUBULU ("Grass","Fairy"), TAPUFINI ("Water","Fairy"), COSMOG ("Psychic",""), COSMOEM ("Psychic",""), 
	SOLGALEO ("Psychic","Steel"), LUNALA ("Psychic","Ghost"), NIHILEGO ("Rock","Poison"), BUZZWOLE ("Bug","Fighting"), PHEROMOSA ("Bug","Fighting"), XURKITREE ("Electric",""), CELESTEELA ("Steel","Flying"), KARTANA ("Grass","Steel"), GUZZLORD ("Dark","Dragon"), NECROZMA ("Psychic",""), 
	MAGEARNA ("Steel","Fairy"), MARSHADOW ("Fighting","Ghost");


	private static final ArrayList<Pokemon> POKEMON = new ArrayList<Pokemon>(Collections.unmodifiableList(Arrays.asList(values())));
	private String type1, type2, tiers;
	
	Pokemon(String t1, String t2) {
		type1 = t1;
		type2 = t2;
		tiers = "";
	}
	public String typing() {
		if (type2.equals(""))
			return type1;
		else
			return type1 + "/" + type2;
	}
	public void tier(String s) {
		if(tiers.equals(""))
			tiers = s;
		else {
			tiers += ", " + s;
		}
	}
	@Override
	public String toString() {
		
		return name() + " " + typing() + " (" + tiers + ")";
	}
	
	public static Pokemon randomPokemon() {
		return POKEMON.get(Random.genRandInt(0,802));
	}
	public static ArrayList<Pokemon> sixPokemon() {
		return Random.selectRandsNoRepeat(POKEMON, 6);
	}
	public static ArrayList<Pokemon> pokemonPlayers(int numPlayers) {
		return Random.selectRandsNoRepeat(POKEMON, 6*numPlayers);
	}
	
}
