import java.util.Scanner;

/**
 * In dieser Klasse wird die Karte erstellt und mit Spielern und Gegnern gefuellt. Ausserdem sind die Methoden fuer die Runde,
 * das Ende und die Aussgabe des Feldes.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public class Map {

    private char[][] map;
    private int width;
    private int height;
    private Alien[] aliens;
    private Player player;
    private static Wall[][] wall;
    private Neighbour[] neighbour = new Neighbour[4];
    private Scanner scan1 = new Scanner(System.in);    
    
    /**
	 * In diesem Konstruktor wird eine Karte mit einer mitgegebenen Hoehe und Breite, die aber begrenzt wird, erstellt. Ausserdem wird
	 * die Karte mit dem Spieler und den Aliens gefuellt und gleichzeitig beide als Objekte angelegt. Die Anzahl der Aliens wird von
	 * Nutzer gewaehlt und die Positionen aller werden zufaellig verteilt. 
	 *
	 * @param height Hoehe des Spielfeldes
	 * @param width Breite des Spielfeldes
	 * @param numberOfAliens Anzahl der Gegner
	 */
    public Map(int height, int width, int numberOfAliens) {
    		
    	//Erstellen des Karten-Arrays
        this.map = new char[width][height];
        
        Map.wall = new Wall[width][height];
        
        //Hoehe und Breite
        this.width = width;
        this.height = height;
       
        //Begrenzung der Hoehe und Breite
        if (this.width > 10) {
        	this.width = 10;
        }
        
        if (this.height > 10) {
        	this.height = 10;
        }
        
        //Fuellen der Karte
        //this.fillMap(this.width - 1, this.height - 1);
        

    	int positionX = Map.randomNumber(this.height);
    	int positionY = Map.randomNumber(this.width);
    	
    	//this.generateMatchfield(positionX, positionY);
	
        
        //Erstellen des Spieler
        this.createPlayer();	
        
           

        //Erstellen des Alien-Arrays (Objekt der Klasse Alien)
        this.aliens = new Alien[numberOfAliens];
        
        this.createAliens();
        
       
    } //end Constructor


	/**
	 * Hier wird eine Zufallszahl erzeugt.
	 *
	 * @param max ein Maximalwert
	 * @return randomNumber eine Zufallszahl zwischen 0 und max
	 */
	public static int randomNumber(int max) {
		double rn = Math.round(Math.random() * (max - 1));
		int randomNumber = (int) rn;
		return randomNumber;
	}
    
    
    /**
     * Erstellen der Gegner mit Beruecksichtigung der Kartengrenze
     * und Ueberpruefung ob der Platz belegt ist. 
     * 
     */
    public void createAliens() {
    	
    	//Koordinaten und Zaehlervariable fuer Aliens
        int xalien;
        int yalien;
        int createdAliens = 0;
    	
    	
    	//Aliens erstellen
        while (createdAliens < aliens.length) {
        	//Zufallszahlen generieren
        	xalien = randomNumber(width);
        	yalien = randomNumber(height);
        	
        	while (!this.checkPosition(xalien, yalien)) {
        		xalien = randomNumber(width);
        		yalien = randomNumber(height);
        	}

        	//Objekt Alien erstellen
        	this.aliens[createdAliens] = new Alien(xalien, yalien);
        	//In die Karte eintragen
        	map[xalien][yalien] = 'A';
        	//Hochzaehlen
        	createdAliens++;  
        	
        } //end while
    	
    } //end createAliens()


    /**
     * In dieser Klasse wird das Objekt Spieler erstellt. Welcher Spielertyp ausgewaehlt wird, wird durch Zufall bestimmt.
     * Ausserdem wird die Position des Spielers geprueft und auf der Karte eingetragen.
     * 
     */
    public void createPlayer() {
    	 //Erstellen von Zufallszahlen fuer den Spieler
        int xplayer = randomNumber(width);
        int yplayer = randomNumber(height);
    	
    	while (!this.checkPosition(xplayer, yplayer)) {
    		xplayer = randomNumber(width);
    		yplayer = randomNumber(height);
    	}
    	
    	
    	int choose = randomNumber(100);
    	
    	//Auswahl des Spielertyps
    	if (choose <= 33) {
    		player = new Sniper(xplayer, yplayer);
    	} else if (choose > 33 && choose <= 66) {
    		player = new Tank(xplayer, yplayer);
        } else if (choose > 66) {
        	player = new Player(xplayer, yplayer);
        } //end of-else
    	
    	//Spieler in die Karte eintragen
        this.map[xplayer][yplayer] = 'P';  
    } //end createPlayer()




	public boolean checkDirection(String direction) {
    	return player.checkPath(direction);
	} //end checkDirection()


    public void movePlayer(String direction) {
        player.move(direction, map, 'P', this.getPlayer(), this.getAliens());
    } //end movePlayer()


    public boolean attacking(int xattack, int yattack) {
        return  player.attack(xattack, yattack);
    } //end attacking()

    public void fightedAlien(int xattack, int yattack) {
        //Suchen nach dem bekaempften Alien
        int xalienAttacked, yalienAttacked;

        for (int i = 0; i < aliens.length; i++) {

            xalienAttacked = aliens[i].getX();
            yalienAttacked = aliens[i].getY();

            //Wenn Alien gefunden wurde
            if (xattack == xalienAttacked && yattack == yalienAttacked) {
                //Leben des Aliens auf tod (false) setzen + Makierung auf der Karte
                aliens[i].setAlife(false);
                map[xattack][yattack] = 'X';
            } //end if

        } //end for
    } //end fightedAlien()

    public String alienTurn() {
        String alienAttacks = "";

        //Angriffsversuch von jedem lebenden Alien
        for (int i = 0; i < aliens.length; i++) {

            //Pruefen ob Alien noch lebt
            if (aliens[i].getAlife()) {
                //Bewegung des Aliens
                map = aliens[i].move(Alien.path(1), map, 'A', this.getPlayer(), this.getAliens());

                //System.out.println(this.toString());
                //Angriffsversuch des Aliens
                alienAttacks = alienAttacks + "\nDas Alien bei (" + aliens[i].getX() + "," + aliens[i].getY() + ") greift den Spieler an";
                boolean succsessAlien = aliens[i].attack(player.getX(), player.getY());

                if (succsessAlien) {
                    //Wenn der Angriff erfolgreich war: ein Leben des Spielers abziehen
                    player.removeHitpoint();
                    alienAttacks = alienAttacks + "\nDas Alien hat den Spieler getroffen!";
                } else {
                    //Wenn nicht er nicht erfolgreich war
                    alienAttacks = alienAttacks + "\nDas Alien hat den Spieler verfehlt!";
                } // end if-else
            } //end if
        } //end for

        return alienAttacks;
    } //end alienTurn()


    /**
	 * In dieser Methode wird geprueft, ob alle Aliens tod sind oder der Spieler maximal 0 Leben verbleibend hat,
	 * weil das die Schlussbedingungen sind.
	 *
	 * @return end Ob das Spiel vorbei ist in Form einer Aussage (String)
	 */
    public String roundEnd() {
    	String end = "";
    	int aliensAlife = 0;
    	
    	//Zaehlen wieviele Aliens noch am Leben sind
    	for (int i = 0; i < aliens.length; i++) {
    		if (aliens[i].getAlife()) {
    			//Alienzaehler hochzaehlen
    			aliensAlife += 1;
    		} //end if
    	} //end for
    	
    	//Pruefen ob der Spieler maximal 0 leben hat (tod ist)
    	if (player.getHitpoints() <= 0) {
    		end = "\nDer Spieler hat keine Leben mehr!";
    		//Pruefen ob kein Alien mehr am leben ist
    	} else if (aliensAlife == 0) {
    		end = "\nDer Spieler hat alle Aliens besiegt!";
    	} //end if-else
    	return end;
    } //end roundEnd()


    /**
	 * In dieser Methode wird geprueft, ob an der angegriffenen Stelle ein Alien ist.
	 *
	 * @param xattack x-Koordinate des anzugreifenden Aliens
	 * @param yattack y-Koordinate des anzugreifenden Aliens
	 * @return check Ob an der Stelle ein Alien ist (wahr/falsch)
	 */
    public boolean checkCoordinates(int xattack, int yattack) {
    	boolean check = false;
    	
    	//Pruefen ob eines der Aliens an der Stelle ist
    	for (int i = 0; i < aliens.length; i++) {
			
			int xalien = aliens[i].getX();
			int yalien = aliens[i].getY();
    		if (xalien == xattack && yalien == yattack) {
    			check = true;
    			break;
    		} //end if
    	} //end for
    	
    	return check;
    } //end checkCoordinates()
    
    
    /**
	 * In dieser Methode wird die Karte mit den oberen, seitlichen und unteren Begrenzungen ausgegeben. Ausserdem wird
	 * die Karte mit Koordinaten am jeweiligen Rand versehen.
	 *
	 * @return output Ob das Spiel vorbei ist in Form einer Aussage (String)
	 */
    public String toString() {
    	String output = "  ";
    	String output2 = "\n  ";
    	
    	//Erstellung der oberen Koordinaten und Begrenzung
    	for (int top = 0; top < width; top++) {
    		output = output + top;
    		output2 = output2 + "-";
    	} //end for
    	
    	//Zusammenfuegen der beiden Teile
    	output = output + output2 + "\n";
    	
    	//Einfuegen der bereits erstellten Karte in den Ausgabestring
    	for (int y = 0; y < height; y++) {
    		
    		//Seitennummerierung und linke Begrenzung erstellen
    		output = output + y + "|";

    		for (int x = 0; x < width; x++) {
    			
    			//Karte einfuegen
    			output = output + map[x][y];
    		} //end for
    		
    		//rechte Begrenzung erstellen
    		output = output + "|\n";
        } //end for
    	
    	output = output + "  ";
    	output2 = "\n  ";
    	
    	//Untere Begrenzung und Nummerierung erstellen
    	for (int bottom = 0; bottom < width; bottom++) {
    	
    		output = output + "-";	
    		
    		output2 = output2 + bottom;
    	} //end for
    	
    	//Endgueltig die beiden Ausgabevariablen zu einer zusammenfuegen
    	output = output + output2;
    	
    	return output;
    } //end toString()
    
    
    /**
     * Gibt die Lebenspunkte des Spielers zurueck.
     * 
     * @return hitpoints Die Lebenspunkte des Spielers
     */
    public int getHitpoints() {
    	return player.getHitpoints();
    } //end getHitpoints()
    
    
    /**
     * Gibt den Namen der Spielerklasse zurueck.
     * 
     * @return name Name der Spielerklasse
     */
    public String playerName() {
    	return player.getName();
    } //end playerName()
    
    /**
     * Gibt den Spieler als Objekt zurueck.
     * 
     * @return player Das Objekt Spieler
     */
    public Player getPlayer() {
    	return this.player;
    }
    
    /**
     * Gibt das Alien als Objekt zurueck.
     * 
     * @return Aliem Das Objekt Alien
     */
    public Alien[] getAliens() {
    	return this.aliens;
    }

    
    

    /**
     * Generiert in dem mit Waenden gefuellten Spielfeld die Wege.
     * Es wird darauf geachtet, im Feld zu bleiben und keine Position doppelt zu
     * besuchen.
     * 
     * @param positionX X-Startposition, wo die Generierung anfaengt
     * @param positionY Y-Startposition, wo die Generierung anfaengt
     */
    public void  generateMatchfield(int positionX, int positionY) {
        //checkMap initialisieren
        boolean [][] checkMap = new boolean [this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; i++) {
                checkMap [i][j] = true;
            }
        }


    }
    
    
    
    /**
     * Die Methode Prueft, ob der Nachbar noch im Spielfeld ist.     
     * 
     * @param x zu pruefende X-Koordinate
     * @param y zu pruefende Y-Koordinate
     * @return check true wenn die Stelle innerhalb der Map liegt, false wenn nicht
     */
    public boolean checkNeighbour(int x, int y) {
    	boolean check = false;
    	
    	if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
    		check = true;
    	}
    	
    	return check;
    }
    
    
    
    /**
     * Es wird geprueft, ob an der Stelle schon ein Player, Alien oder eine Wand ist.
     * 
     * @param x zu pruefende X-Koordinate
     * @param y zu pruefende Y-Koordinate
     * @return true wenn Position frei, false wenn nicht
     */
    public boolean checkPosition(int x, int y) {
    	
    	boolean check = false;
    	
    	if (map[x][y] == ' ' && Character.checkWalls(x, y, wall)) {
    		check = true;
    	}
    	
    	return check;
    	
    }
    
    
    
    /**
     * Diese Methode fuellt die komplette Karte mit # fuer Waende
     * und O fuer Tueren.
     * @param width Breite der Karte
     * @param height Hoehe der Karte
     */
    public void fillMap(int width, int height) {
    	
    	if (width >= 1) {
    		this.fillMap(width - 1, height);	
    	}
    	
    	if (height >= 1) {
    		this.fillMap(width, height - 1);	
    	}    
    
    	wall[width][height] = new Wall();
    	this.map[width][height] = wall[width][height].toString().charAt(0);
    
    }
    
    
    
    /**
     * Gibt die Waende zurueck
     * @return Wall die Objekte, die die Waende dastellen
     */
    public static Wall[][] getWall() {
    	return Map.wall;
    }
} //end Class Map
