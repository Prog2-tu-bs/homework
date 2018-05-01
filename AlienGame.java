import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * In dieser Klasse wird die Interaktion mit dem User durch die Methode Scanner gesteuert. 
 * Ausserdem wird hier der Ablauf einer Runde gestartet und das Spiel wenn der Spieler keine Leben mehr hat oder
 * keine Aliens mehr vorhanden sind beendet.
 * 
 * @author Nathalie Peine 4902166 Gruppe 6b
 * @author Marcel Fricke 4879684 Gruppe 6b 
 */
public class AlienGame {
	
	/**
	 * In dieser Main wird das Spiel ausgefuehrt, beendet und die Interaktion mit dem
	 * Spieler gesteuert.
	 * 
	 * @param args Die Hoehe und Breite des Spielfeldes und die Anzahl der Gegner wird eingelesen
	 */
	public static void main(String[] args) {
		
		//Pruefen der Startparameter (Zahl, drei Werte)
        try {
        	
        	if (args.length < 3) {
        		System.exit(0);
        	}
        
        	for (int i = 0; i < 3; i++) {
        		int control = Integer.parseInt(args[i]);
        		if (control <= 0) {
        			System.out.println("Zu niedrige Werte! Bitte Werte > 0 eingeben!");
        			System.exit(0);
        		}
        	}
		} catch (NumberFormatException e) {
			System.out.println("Unzulaessige oder zu wenige Startparameter!");
			System.exit(0);
		}
        
        
        
		//Hoehe des Spielfeldes einlesen
		int y = Integer.parseInt(args[0]);
		
		//Breite des Spielfeldes einlesen
		int x = Integer.parseInt(args[1]);
		
		//Anzahl der Gegner einlesen
		int numberOfAliens = Integer.parseInt(args[2]);
		
		//Erstellen des Objektes Spielfeld
		Map map1 = new Map(y, x, numberOfAliens);
		
		//Scanner erstellen
		Scanner scan1 = new Scanner(System.in);
		
		int xattack;
		int yattack;
		boolean endGame = false;
		
		System.out.println("Die Klasse " + map1.playerName() + " wurde ausgewaehlt!\n");
		
 
		while (!endGame) {
			
			//Karte ausgeben
			System.out.println(map1.toString());
				
			try {

                System.out.println("\nDer Spieler hat noch " + map1.getHitpoints() + " Leben!");

                // Abfrage fuer die Bewegung des Spielers   map1.checkDirection(direction
                System.out.println("Wohin soll der Spieler sich bewegen?");
                String direction = scan1.next();


                // Bewegung des Spielers
                if(map1.checkDirection(direction)) {
                   map1.movePlayer(direction);
                } else {
                    System.out.println("Bewegung nicht moeglich!");
                }


                //x-Koordinate fuer den Angriff des Spielers einlesen
                System.out.println("Wo soll der Spieler angreifen? (X-Koordinate)");
                xattack = scan1.nextInt();


                //y-Koordinate fuer den Angriff des Spielers einlesen
                System.out.println("Wo soll der Spieler angreifen? (Y-Koordinate)");
                yattack = scan1.nextInt();


                //Angabe der Koordinaten des angegriffenen Aliens
                System.out.println("\nDer Spieler greift das Alien bei (" + xattack + "," + yattack + ") an.");



                //Pruefen ob ein Alien bei den angegebenen Koordinaten ist.
                if (map1.checkCoordinates(xattack, yattack)) {
                    //Angriffsversuch des Spielers
                    boolean successPlayer = map1.attacking(xattack, yattack);

                    if (successPlayer) {
                        //getroffenes Alien finden / anpassen
                        map1.fightedAlien(xattack, yattack);

                        //Ausgabe des Ergebnisses
                        System.out.println("\nDer Spieler hat das Alien getroffen!");

                    } else {
                        //Wenn der Angriff nicht erfolgreich war
                        System.out.println("\nDer Spieler hat das Alien verfehlt!");

                    } //end if-else

                    System.out.println(map1.alienTurn());
                } else {
                    //Falls sich kein Alien bei den Koordinaten befindet
                    System.out.println("Bitte korrekte Koordinaten angeben!");
                }

			} catch (InputMismatchException e) {
			    //Falls eine falsche Eingabe gemacht wurde
			    System.out.println("Die Angaben waren nicht zulaessig! Bitte geben Sie eine X-Koordinate und eine Y-Koordinate ein.\n");
			    scan1.next();   

			} //end try-catch


			//Pruefen ob ein Kriterium zum Beenden des Spieles erfuellt ist
		    String end = map1.roundEnd();
		    if (!end.equals("")) {
		    	//Beenden mit Ausgabe des Grundes (ende der While-Schleife)
		    	System.out.println("\n" + map1.toString());
		    	System.out.println(end);
		    	endGame = true;
		    } //end if
		} //end while
		
		//Beenden des Scanners
		scan1.close();
	} //end main
} //end class AlienGame
