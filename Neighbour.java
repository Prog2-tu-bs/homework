/**
 * Diese Klasse enthaelt die Position des Nachbarn und ob er von der Ausgangsposition ereichbar ist.
 * Ausserdem haben die Nachbarn die Position vom Feld zwischen der Ausgangsposition und ihnen.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public class Neighbour extends Character {

	private boolean accessable;
	private int betweenX;
	private int betweenY;
	
	/**
	 * Hier wird jeder Nachbar als Unterklasse von Charackter angelegt. Dadurch hat jeder
	 * seine Koordinaten ausserdem wird die Erreichbarkeit bestimmt.
	 * 
	 * @param x X-Koordinate des Nachbarn
	 * @param y Y-Koordinate des Nachbarn
	 * @param accessable Erreichbarkeit des Nachbarn vom Ausgangspunkt
	 */
	public Neighbour(int x, int y, boolean accessable) {
		super(x, y);
		
		this.accessable = accessable;
	}	
	
	
	/**
	 * Die Methode gibt zurueck, ob der Nachbar erreichbar ist.
	 * 
	 * @return accessable gibt true/falls zurueck 
	 */
	public boolean getAccessable() {
		return this.accessable;
	}
	
	
	
	
	
	/**
	 * Die Methode gibt zurueck, was die X-Position zwischen dem Ursprung und ihm ist.
	 * 
	 * @return betweenX gibt X-Position zwischen beiden zurueck 
	 */
	public int getBetweenX() {
		return this.betweenX;
	}
	
	
	
	/**
	 * Die Methode setzt die x-Koordinate zwischen dem Urspurng und ihm.
	 * 
	 * @param betweenX x-Koordinate zwischen beiden
	 */
	public void setBetweenX(int betweenX) {
		this.betweenX = betweenX;
	}
	
	
	
	
	
	/**
	 * Die Methode gibt zurueck, was die Y-Position zwischen dem Ursprung und ihm ist.
	 * 
	 * @return betweenY gibt Y-Position zwischen beiden zurueck 
	 */
	public int getBetweenY() {
		return this.betweenY;
	}
	
	
	/**
	 * Die Methode setzt die Y-Koordinate zwischen dem Urspurng und ihm.
	 * 
	 * @param betweenY Y-Koordinate zwischen beiden
	 */
	public void setBetweenY(int betweenY) {
		this.betweenY = betweenY;
	}
}
