/**
 * Diese Klasse ist der Sniper. Er ist eine Variation des Spielers. Hier werden die Werte fuer ihn festgelegt.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */

public final class Sniper extends Player {

	/**
	 * Der Konstruktor legt eine Spielerklasse (Sniper) mit seinen x- und y-Koordinaten und seinem Leben an. Ausserdem wird sein Schaden, der Name und
	 * die Genauigkeit festgelegt.
	 *
	 * @param xsniper Die x-Koordinate des Sniper
	 * @param ysniper Die y-Koordinate des Sniper
	 */
    public Sniper(int xsniper, int ysniper) {
	    super(xsniper, ysniper);

	    this.setHitpoints(5);
	    this.setDamage(1);
	    this.setAccuracy(2);
	    this.setName("Sniper");
    }

}
