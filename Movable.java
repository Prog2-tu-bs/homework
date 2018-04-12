/**
 * In diesem Interface werden die Methoden canMove() und move() ueberwacht.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public interface Movable {
	
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
	boolean canMove(Player player, Alien[] aliens, int moveX, int moveY);
	
	

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
	char[][] move(String direction, char[][] map, char icon, Player player, Alien[] aliens);
}