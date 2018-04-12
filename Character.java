import java.util.Scanner;

/**
 * Diese Klasse dient als Oberklasse fuer Aliens und die Verschiedenen Spieler. Hier werden die Koordinaten
 * fuer die Objekte und die Methode zum Angreifen festgelegt. 
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public abstract class Character implements Movable {

    private int xcoordinate;
    private int ycoordinate;
    private int accuracy;
    private Wall[][] wall = Map.getWall();
    
    
    /**
     * Erstellung eines Charakters, der als Oberklasse fuer alle Spieler und Aliens gilt. Es werden die Koordinaten des 
     * jeweiligen Charkters festgelegt.
     * 
     * @param xcoordinate X-Koordinate des Characters
     * @param ycoordinate Y-Koordinate des Characters
     */
    public Character(int xcoordinate, int ycoordinate) {
        this.xcoordinate = xcoordinate;
	    this.ycoordinate = ycoordinate;
    }
    
    /**
     * Setzt die angegebene Genauigkeit fuer eine Klasse
     * 
     * @param accuracy Genauigkeit der Klasse
     */
    public void setAccuracy(int accuracy) {
    	this.accuracy = accuracy;
    }
    
    
    /**
     * Die Methode wird genutzt um die Angriffe des Spieles und der Aliens zu vollziehen,
     * indem man prueft ob der Angriff mit einer bestimmten Wahrscheinlichkeit geklappt hat.
     * 
     * @param xattack x-Koordinate des anzugreifenden Ziels
     * @param yattack y-Koordinate des anzugreifenden Ziels
     * @return success ob der Charakter das Ziel getroffen hat.
     */
    public boolean attack(int xattack, int yattack) {
	    boolean success = false;
	 
	    int distance = Math.abs(this.xcoordinate - xattack) + Math.abs(this.ycoordinate - yattack);
	    
	    //Wahrscheinlichkeit berechnen
	    int probability = 75 - (distance - 1) * this.accuracy;
	
	    //Mindestwahrscheinlichkeit
	    if (probability <= 20) {
	    	probability = 20;
	    } //end if
		
	    //Testen ob getroffen wurde
	    if (probability >= (int) (Math.random() * 100)) {
	    	success = true;
	    } //end if
		
        return success;
    } //end attack()


    /**
     * Methode mit der die x-Koordinate abgefragt werden kann. 
     *
     * @return Die x-Koordinate des Objektes
     */
    public int getX() {
    	return this.xcoordinate;
    } //end getXplayer(),

	
    /**
     * Methode mit der die y-Koordinate abgefragt werden kann. 
     *
     * @return Die y-Koordinate des Objektes
     */
    public int getY() {
    	return this.ycoordinate;
    } //end getYplayer()
    
    
    //Schnittstellen

    
    
    /**
     * Diese Methode laesst Aliens und Spieler laufen (Aliens zufaellig und Player nach Eingabe). Ausserdem wird
     * durch aufrufen der Methode canMove() gecheckt, ob es moeglich ist an die Stelle zu laufen. 
     * 
     * @param direction der zu gehenden Weg
     * @param map das aktuelle Spielfeld
     * @param icon Der Buchstabe fuer den Character
     * @param player das Objekt Player
     * @param aliens das Objektarray fuer die Aliens
     * @return card die Aktualisierte map
     */
    public char[][] move(String direction, char[][] map, char icon, Player player, Alien[] aliens) {
    	char path;

    	int steps = (int) (Math.round(Math.random() * (Alien.getMaxMove() - 1)) + 1);
    	
    	int oldPlayerX = player.getX();
    	int oldPlayerY = player.getY();
    	
    	for (int i = 0; i < direction.length(); i++) {
    		
    		if (icon == 'A') {
    			direction = Alien.path(steps);
    		}
    		    		
    		path = direction.charAt(i);
    		
    		switch (path) {
    			
    			case 'w': {
    				if (!this.canMove(player, aliens, 0, 0 - 1)) {
    				    i--;

    				    if (icon == 'P') {
    				    	direction = this.playerMove(map, oldPlayerX, oldPlayerY);		
    				    	i = -1;
    				    }
					
    				    break;
    				}
    				
    				map[this.getX()][this.getY()] = ' ';
    				this.ycoordinate = this.ycoordinate - 1;
    				map[this.getX()][this.getY()] = icon;
    				break;
    			}
    			
    			case 's': {
    				
    			
    				if (!this.canMove(player, aliens, 0, 1)) {
    				    i--;
    				
    				    if (icon == 'P') {
    					    
    				    	direction = this.playerMove(map, oldPlayerX, oldPlayerY);		
    				    	i = -1;    					
    					}
    				    
    				    break;	
    				}
					
				    
    				
    				
    				map[this.getX()][this.getY()] = ' ';
    				this.ycoordinate = this.ycoordinate + 1;
    				map[this.getX()][this.getY()] = icon;
    				break;
    			}
    			
    			case 'a': {
    				if (!this.canMove(player, aliens, 0 - 1, 0)) {
    				    i--;

    				    if (icon == 'P') {
    				    	direction = this.playerMove(map, oldPlayerX, oldPlayerY);		
    				    	i = -1;
    				    }
					
    				    break;
    				}
    				
    				map[this.getX()][this.getY()] = ' ';
    				this.xcoordinate = this.xcoordinate - 1;
    				map[this.getX()][this.getY()] = icon;
    				break;
    			}
    			
    			case 'd': {
    				    				
    				if (!this.canMove(player, aliens, 1, 0)) {
    				    i--;

    				    if (icon == 'P') {
    				    	direction = this.playerMove(map, oldPlayerX, oldPlayerY);		
    				    	i = -1;
    				    }
					
    				    break;
    				}
    				
    				map[this.getX()][this.getY()] = ' ';
    				this.xcoordinate = this.xcoordinate + 1;
    				map[this.getX()][this.getY()] = icon;
    				break;
    			}
    			
    			default: {
    				break;
    			}
    		
    		} //end switch
    		
    	} //end for
    	
    	return map;
    } //end move()

    


    /**
     * Diese Methode wird aufgrerufen, wenn der Spieler die Bewegung nicht ausfueheren kann.
     * Sie setzt den Spieler zurueck und laesst den Spieler eine neue Bewegung machen.
     * Diese neue Bewegung wird erstmal nach den Kriterien abgesucht und dann uebergeben. 
     * 
     * @param map das Spielfeld
     * @param oldPlayerX Der X - Anfangsstandort des Spielers
     * @param oldPlayerY Der Y - Anfangsstandort des Spielers
     * @return direction Eine neue Richtung, in die der Spieler gehen soll
     */
    public String playerMove(char[][] map, int oldPlayerX, int oldPlayerY) {
    	String direction = "";
    	boolean possible = false;
    	Scanner scan2 = new Scanner(System.in);
		
	
    	map[this.xcoordinate][this.ycoordinate] = ' ';
    	this.xcoordinate = oldPlayerX;
    	this.ycoordinate = oldPlayerY;
    	map[this.xcoordinate][this.ycoordinate] = 'P';
    	System.out.println("Die Bewegung ist nicht moeglich! Bitte neue Richtung angeben!\n");
    	
    	while (!possible) {
    		direction = scan2.next();
    		System.out.println(direction);
    		if (this.checkPath(direction)) {
    			possible = true;
    		}
	    
    	}
	
    	return direction;
    }
    
    /**
     * Diese Methode prueft, ob das Feld, auf das der Character gehen moechte im Spielfeld liegt
     * und von nichts geblockt wird.     * 
     * 
     * @param player das Objekt player
     * @param aliens das Objektarray fuer die Aliens
     * @param moveX Verschiebung in x-Richtung
     * @param moveY Verschiebung in y-Richtung
     * 
     * @return canMove gibt zurueck, ob die Stelle frei ist.
     */
    public boolean canMove(Player player, Alien[] aliens, int moveX, int moveY) {
    	boolean canMove = false;
    	
    	boolean checkCoordinates = true;
    	
    	int xcoordinate = this.getX() + moveX;
    	
    	int ycoordinate = this.getY() + moveY;
    	
    	
    	
    	if (xcoordinate >= 0 && xcoordinate <=  9 && ycoordinate >= 0 && ycoordinate <= 9) {

    		//Pruefen ob eines der Aliens an der Stelle ist
        	for (int i = 0; i < aliens.length; i++) {
    			int xalien = aliens[i].getX();
    			int yalien = aliens[i].getY();
    		    if (xalien == xcoordinate && yalien == ycoordinate) {
    		    	checkCoordinates = false;
    		    } //end if
    		} //end for
    		if (checkCoordinates) {
    			
    			if (player.getX() != xcoordinate || player.getY() != ycoordinate) {
    				
    				if (Character.checkWalls(xcoordinate, ycoordinate, wall)) {
    					canMove = true;	 
    				} //end if    				   				
    			} //end if    			
    		} //end if
    	} //end if
    	
    	return canMove;

    }




    /**
     * Diese Methode checkt ob der Weg fuer den Spieler den Kriterien entspricht.
     * Das heisst ob es zwischen 1 und 3 Schritten sind und ob nur w a s d benutzt
     * wurde. 
     * 
     * @param path der vom Spieler angegebene Weg
     * @return check ob die Kriterien eingehalten sind
     */
    public boolean checkPath(String path) {
    	boolean check = false;
    	
    	if (path.length() <= Player.getMaxMove() && path.length() > 0) {
    	    for (int i = 0; i < path.length(); i++) {
    	    	switch (path.charAt(i)) {
    		    	case 'w' : {
    		    		check = true;
    		    		break;
    		    	}
    				
    		    	case 's' : {
    		    		check = true;
    		    		break;
    		    	}
    				
    		    	case 'a' : {
    		    		check = true;
    		    		break;
    		    	}
    				
    		    	case 'd' : {
    		    		check = true;
    		    		break;
    		    	}
    				
    		    	default : {
    		    		check = false;
    		    		i = path.length();
    		    		break;
    		    	}
    			
    	    	} //end switch
    	    } //end for
    	} //end if
    	
    	return check;
    } //end checkPath()
    
    
    
    /**
     * Es wird geprueft, ob an der Stelle eine Wand, Tuer oder nichts ist.
     * 
     * @param xCheck zu pruefende X-Koordinate
     * @param yCheck zu pruefende Y-Koordinate
     * @param wall das Objekt Wall in dem alle Waende gespeichert sind
     * @return true wenn Position frei, false wenn nicht
     */
    public static boolean checkWalls(int xCheck, int yCheck, Wall[][] wall) {
    	boolean checkPos = false;
    	
    	if (!wall[xCheck][yCheck].getSolid() || !wall[xCheck][yCheck].getExist()) {
    		checkPos = true;
    	}
    	
    	return checkPos;
    }
}



