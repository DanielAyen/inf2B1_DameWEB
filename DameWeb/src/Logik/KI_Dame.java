package Logik;

import java.io.Serializable;

/**
 * 
 * @author Baris, Daniel, Simon, Hannes
 *
 */
public class KI_Dame extends KI  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1632008787864445195L;
	

	public KI_Dame(){
		super();
	}
	
	public KI_Dame(Spieler spieler, Spielbrett brett) {
		super(spieler, brett);
		}
}
