package randomAccessMachine;

/**
 * Das Interface Executable definiert eine Schnittstelle für ausführbare Objekte in einer Random Access Machine (RAM).
 * Jedes ausführbare Objekt muss die execute-Methode implementieren, die eine Aktion auf der gegebenen RAM ausführt.
 */
public interface Executable {
	
    /**
     * Führt eine Aktion auf der gegebenen Random Access Machine (RAM) aus.
     * Die spezifische Aktion hängt von der konkreten Implementierung des ausführbaren Objekts ab.
     *
     * @param ram die RAM, auf der die Aktion ausgeführt wird.
     */
	void execute (RAM ram);

}
