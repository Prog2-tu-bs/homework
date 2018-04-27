/**
 * Diese Klasse ist der Tank. Er ist eine Variation des Spielers. Hier werden die Werte fuer ihn festgelegt.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */

public final class Tank extends Player {
	

	/**
	 * Der Konstruktor legt eine Spielerklasse (Tank) mit seinen x- und y-Koordinaten und seinem Leben an. Ausserdem wird sein Schaden, der Name und
	 * die Genauigkeit festgelegt.
	 *
	 * @param xtank Die x-Koordinate des Tanks
	 * @param ytank Die y-Koordinate des Tanks
	 */
    public Tank(int xtank, int ytank) {
	    super(xtank, ytank);
	    
	    this.setHitpoints(10);
	    this.setDamage(1);
	    this.setAccuracy(5);
	    this.setName("Tank");
    }

}
