import java.io.Serializable;


/*  
 	Abstrakte Klasse Message
 
	Enthält: 
			- ein Attribut --> String(=Inhalt der übertragen werden soll)
			- einen Getter --> um auf den Inhalt zuzugreifen
			- einen Setter --> um den Inhalt zu setzen
			
	Implementiert:
			- Serializable --> ermöglicht:
											- Objekte über das Netzwerk schicken
											- Objekte in ein Byte-Feld schreiben
		
*/

public abstract class Message implements Serializable {

    String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


}