package randomAccessMachine;

/**
 * Dies ist die Hauptklasse, die verwendet wird, um eine Random Access Machine (RAM) zu simulieren.
 * Sie erstellt eine Reihe von Instruktionen, lädt sie in die RAM und führt sie aus.
 * Danach werden die Werte des Akkumulators und der Speicheradressen ausgegeben.
 */
public class Main {
    /**
     * Der Einstiegspunkt für die Anwendung. 
     * 
     * @param args Die Argumente der Kommandozeile. Wird in diesem Kontext nicht verwendet.
     */
    public static void main(String[] args) {
    	// Initialisiere Speicherwerte.
        int[] memoryValues = {7, 8};

     // Definiere ein Programm mit einer Reihe von Instruktionen.
        Instruction[] program = {
        	    new MemoryInstruction("LDA", 0),      
        	    new ArithmeticInstruction("ADS", 1),     
        	    new MemoryInstruction("STA", 0),         
        	    new ControlInstruction("HLT")         
        	};

        // Erzeuge eine neue RAM mit den initialisierten Speicherwerten.
        RAM ram = new RAM(memoryValues);
        // Lade das definierte Programm in die RAM.
        ram.loadProgram(program);
        // Führe das Programm in der RAM aus.
        ram.execute();

        // Holen Sie sich den aktuellen Wert des Akkumulators und der Speicheradressen.
        int accumulator = ram.getAccumulator();
        int memoryValue = ram.getMemoryValue(0);
        int memoryValue1 = ram.getMemoryValue(1);

        // Ausgabe der aktuellen Werte des Akkumulators und der Speicheradressen.
        System.out.println("Inhalt des Akkumulators: " + accumulator);
        System.out.println("Inhalt von Speicheradresse 0: " + memoryValue);
        System.out.println("Inhalt von Speicheradresse 1: " + memoryValue1);


    }
}
