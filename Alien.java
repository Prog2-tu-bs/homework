/**
 * In dieser Klasse werden die Aliens mit ihren Koordinaten und dem Zustand (tod/lebendig) erstellt. Es ist ausserdem die Methode
 * zum Angreifen der Aliens enthalten und die noetigen Getter und Setter.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public final class Alien extends Character {
	
    private boolean alife;
    
    private static int maxMove = 2;
	
    /**
     * In diesem Konstruktor werden Aliens erstellt. Ihnen wird eine x- und y-Koordinate
     * und ein Zustand, dass das Alien lebt zugeordnet.
     * 
     * @param xalien Die x-Koordinate des Aliens
     * @param yalien Die y-Koordinate des Aliens
     */
    public Alien(int xalien, int yalien) {
	    super(xalien, yalien);
	    
	    this.alife = true;
	    this.setAccuracy(5);
    }
	

	/**
	 * Methode mit der abgefragt wird, ob das Alien noch lebt.
	 *
	 * @return Lebenszustand des Aliens (lebendig/tod)
	 */
	public boolean getAlife() {
	    return this.alife;
    } //end getAlife
	
	
	/**
	 * In der Methode wird die maximale Laufdistanz abgefragt.
	 * 
	 * @return maxMove Maximale Distanz, die das Alien gehen kann
	 */
	public static int getMaxMove() {
		return maxMove;
	}
	
	
	/**
	 * In der Methode wird gesetzt, ob das Alien lebt oder tod ist.
	 *
	 * @param alife ein wahr / flasch, ob das Alien lebt 
	 */
	public void setAlife(boolean alife) {
	    this.alife = alife;
    } //end setAlife
	
	
	
	/**
	 * Die Methode wandelt eine Zufaellige Nummer in eine Bewegungsrichtung um. Das wird so oft wiederholt, bis 
	 * die alle Schritte umgewandelt sind.
	 * 
	 * @param steps Anzahl an Schritten, die das Alien gehen soll
	 * @return card die aktualisierte Map
	 */
	
	public static String path(int steps) {
		String path = "";
		
		int randomNumber;
		
		
		for (int i = 0; i < steps; i++) {
			
			randomNumber = (int) Math.round((Math.random() * 3));
			
			switch (randomNumber) {
		    	case 0 : {
		    		path = path.concat("w");
		    		break;
		    	}
		
		    	case 1 : {
		    		path = path.concat("s");
		    		break;
		    	}
		    
		    	case 2 : {
		    		path = path.concat("a");
		    		break;
		    	}
		    
		    	case 3 : {
		    		path = path.concat("d");
		    		break;
		    	}
		    	
		    	default : {
		    		break;		    		
		    	}
		    
			} //end switch
		
		} //end for
		return path;
	} //end path()
	
	

} // end class Alien
