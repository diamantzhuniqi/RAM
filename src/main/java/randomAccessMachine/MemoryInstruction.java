package randomAccessMachine;

/**
 * Die Klasse MemoryInstruction stellt die Speicherinstruktionen für den RAM (Random Access Machine) dar.
 * Sie erbt von der abstrakten Klasse Instruction und implementiert die Methode execute().
 * Zu den möglichen Speicheroperationen gehören LDA, STA, LDI und STI.
 */
public class MemoryInstruction extends Instruction {

    /**
     * Konstruktor für eine MemoryInstruction.
     * 
     * @param operation  der Name der Operation (z.B. "LDA", "STA", "LDI", "STI").
     * @param parameter  der Parameter für die Operation, meistens eine Speicheradresse.
     */
    public MemoryInstruction(String operation, int parameter) {
        super(operation, parameter);
    }
    
    /**
     * Führt die spezifische Speicherinstruktion auf dem gegebenen RAM aus.
     * Die genaue Ausführung hängt von der Operation ab.
     * "LDA" lädt den Akkumulator mit dem Inhalt der Speicheradresse, die im Parameter angegeben ist.
     * "STA" speichert den Inhalt des Akkumulators an der im Parameter angegebenen Speicheradresse.
     * "LDI" lädt den Akkumulator indirekt mit dem Inhalt der Adresse, die im Parameter angegeben ist.
     * "STI" speichert den Inhalt des Akkumulators indirekt an der im Parameter angegebenen Speicheradresse.
     * Bei einer ungültigen Operation wird eine Meldung ausgegeben und der RAM wird angehalten.
     * 
     * @param ram  der RAM, auf dem die Instruktion ausgeführt wird.
     */
    @Override
    public void execute(RAM ram) {
        switch (operation) {
            case "LDA":
                // Lade den Akkumulator mit dem Inhalt von Speicheradresse parameter
                ram.setAccumulator(ram.getMemoryValue(parameter));
                ram.incrementProgramCounter();
                break;

            case "STA":
                // Speichere den Inhalt des Akkumulators an Speicheradresse parameter
                ram.setMemoryValue(parameter, ram.getAccumulator());
                ram.incrementProgramCounter();
                break;

            case "LDI":
                // Lade den Akkumulator indirekt mit dem Inhalt von Adresse parameter
                int indirectAddress = ram.getMemoryValue(parameter);
                ram.setAccumulator(ram.getMemoryValue(indirectAddress));
                ram.incrementProgramCounter();
                break;

            case "STI":
                // Speichere den Inhalt des Akkumulators indirekt an Speicheradresse parameter
                int indirectAddress2 = ram.getMemoryValue(parameter);
                ram.setMemoryValue(indirectAddress2, ram.getAccumulator());
                ram.incrementProgramCounter();
                break;

            default:
                System.out.println("Ungültige Speicherinstruktion: " + operation);
                ram.setHalted(true);
                break;
        }
    }
}

