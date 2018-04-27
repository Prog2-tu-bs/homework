/**
 * Diese Klasse dient als Standartklasse fuer den Spieler. Hier werden die Werte fuer ihn , die interaktion mit den Lebenspunkten
 * und dem Schaden festgelegt.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public class Player extends Character {
	
	private int hitpoints;
	private int damage;
	private String name;
	private static int maxMove = 3;
	
	
	/**
	 * Der Konstruktor legt einen Spieler mit seinen x- und y-Koordinaten und seinem Leben an. Ausserdem wird sein Schaden, der Name und
	 * die Genauigkeit festgelegt.
	 *
	 * @param xplayer Die x-Koordinate des Spielers
	 * @param yplayer Die y-Koordinate des Spielers
	 */
	public Player(int xplayer, int yplayer) {
	    super(xplayer, yplayer);
	    
	    this.hitpoints = 5;
	    this.damage = 1;
	    this.name = "Player";
	    this.setAccuracy(5);
	} //end Konstruktor


	/**
	 * Methode mit der die Lebenspunkte abgefragt werden koennen. 
	 *
	 * @return Die Lebenspunkte des Spielers 
	 */
	public int getHitpoints() {
	    return this.hitpoints;
	} //end getHitpoints()
	
	
	
	/**
	 * Setzt die Lebenspunkte des Spielers.
	 * 
	 * @param hitpoints Lebenspunkte des Spielers
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	
	/**
	 * Methode mit der das Leben des Spielers um 1 verringert wird. 
	 */
	public void removeHitpoint() {
		this.hitpoints -= this.damage;
	} //end removeHitpoint()
	
	/**
	 * In der Methode wird die maximale Laufdistanz abgefragt.
	 * 
	 * @return maxMove Maximale Distanz, die der Spieler gehen kann
	 */
	public static int getMaxMove() {
		return maxMove;
	}
	
	/**
	 * Setzt die hoehe des Schadens, die die Klasse zufuegen kann.
	 * 
	 * @param damage Schaden des Spielers
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
	/**
	 * Setzt den Namen fuer eine Spielerklasse
	 * 
	 * @param name Name der Klasse
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Gibt den Namen der Spielerklasse zurueck.
	 * 
	 * @return name Name der Klasse
	 */
    public String getName() {
	    return this.name;
    } 


} //end class Player
