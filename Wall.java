/**
 * Diese Klasse dient als Sammlung fuer die Waende. hier wird ihr derzeitiger Status festgehalten
 * Nun auch mit GIT
 *
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public class Wall {

	private char icon;
	private boolean visited;
	private boolean exist;
	private boolean solid;
	
	
	/**
	 * In dem Konsturktor werden die Waende erstellt, wobei direkt ausgewaehlt wird,
	 * ob es eine Wand oder Tuer ist.
	 */
	public Wall() {
		
		if (Wall.randomNumber(100) < 90) {
			icon = '#';
			solid = true;
		} else {
			icon = 'O';
			solid = false;
		}
		
		this.exist = true;
		
	}
	
	/**
	 * Hier wird eine Zufallszahl generiert
	 * 
	 * @param max die hoechstmoegliche Zahl
	 * @return rng Zufallszahl
	 */
	public static int randomNumber(int max) {
		int rng;
		
		rng = (int) Math.round(Math.random() * max);
		
		return rng;
	}
	
	/**
	 * Das Zeichen der Wand/Tuer wird zurueckgegeben
	 * 
	 * @return icon eine # fuer eine Wand und ein O fuer eine Tuer
	 */
	public String toString() {
		
		return "" + this.icon;
		
	}
	
	
	/**
	 * Visied wird auf true oder false gesetzt
	 * 
	 * @param visited der Status (true/false) auf den visited gesetzt wird
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;	
	}
	
	/**
	 * Die Methode gibt Visited zurueck.
	 * 
	 * @return visited true/false wird zurueckgegeben
	 */
	public boolean getVisited() {
		return this.visited;
	}
	
	/**
	 * Exist wird auf true oder false gesetzt
	 * 
	 * @param exist der Status (true/false) auf den exist gesetzt wird
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	/**
	 * Die Methode gibt exist zurueck.
	 * 
	 * @return exist true/false wird zurueckgegeben
	 */
	public boolean getExist() {
		return this.exist;		
	}
	
	/**
	 * Die Methode gibt solid zurueck.
	 * 
	 * @return solid true/false wird zurueckgegeben
	 */
	public boolean getSolid() {
		return this.solid;
	}
}
