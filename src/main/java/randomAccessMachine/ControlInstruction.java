package randomAccessMachine;

/**
 * Die Klasse ControlInstruction stellt eine Steueranweisung in einer Random Access Machine dar.
 * Sie erbt von der abstrakten Klasse Instruction und implementiert die Methode execute().
 * Eine Steueranweisung steuert den Programmfluss der Maschine durch Bedingungssprünge oder das Anhalten der Ausführung.
 */
public class ControlInstruction extends Instruction {

    /**
     * Erstellt eine neue Steueranweisung.
     *
     * @param operation die Art der Operation (JMP, JMZ oder HLT).
     * @param parameter die Anweisungsposition im Programm, zu der gesprungen werden soll.
     */
    public ControlInstruction(String operation, int parameter) {
        super(operation, parameter);
    }

    /**
     * Erstellt eine neue Steueranweisung ohne Parameter.
     *
     * @param operation die Art der Operation (JMP, JMZ oder HLT).
     */
    public ControlInstruction(String operation) {
        super(operation, 0);
    }

    /**
     * Führt die Steueranweisung auf der gegebenen RAM-Maschine aus.
     * Wenn die Operation JMP ist, springt das Programm zur Anweisung an der durch den Parameter repräsentierten Position.
     * Wenn die Operation JMZ ist, springt das Programm zur Anweisung an der durch den Parameter repräsentierten Position, wenn der Akkumulator 0 ist.
     * Wenn die Operation HLT ist, wird das Programm angehalten.
     * Wenn die Operation ungültig ist, wird das Programm angehalten und eine Fehlermeldung wird ausgegeben.
     *
     * @param ram die RAM-Maschine, auf der die Anweisung ausgeführt wird.
     */
    @Override
    public void execute(RAM ram) {
        switch (operation) {
            case "JMP":
            	// Springe zur parameter markierten Instruktion im Programm
                if (parameter >= 0 && parameter < ram.getProgramLength()) {
                    ram.setProgramCounter(parameter);
                } else {
                    System.out.println("Ungültiger Sprungindex: " + parameter);
                    ram.setHalted(true);
                }
                break;

            case "JMZ":
                // Springe zur parameter markierten Instruktion im Programm, falls der Akkumulator den Wert 0 enthält
                if (ram.getAccumulator() == 0) {
                    if (parameter >= 0 && parameter < ram.getProgramLength()) {
                        ram.setProgramCounter(parameter);
                    } else {
                        System.out.println("Ungültiger Sprungindex: " + parameter);
                        ram.setHalted(true);
                    }
                } else {
                    ram.incrementProgramCounter();
                }
                break;

            case "HLT":
                // Beende das Programm
                ram.setHalted(true);
                break;

            default:
                System.out.println("Ungültige Steuerinstruktion: " + operation);
                ram.setHalted(true);
                break;
        }
    }
}

