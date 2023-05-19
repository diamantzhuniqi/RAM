package randomAccessMachine;

/**
 * Die Klasse ArithmeticInstruction stellt eine arithmetische Anweisung in einer Random Access Machine dar.
 * Sie erbt von der abstrakten Klasse Instruction und implementiert die Methode execute().
 * Eine arithmetische Anweisung führt eine mathematische Operation (ADD oder SUB) auf dem Akkumulator und dem Wert an einer Speicheradresse aus.
 */
public class ArithmeticInstruction extends Instruction {

    /**
     * Erstellt eine neue arithmetische Anweisung.
     *
     * @param operation die Art der Operation (ADD oder SUB).
     * @param parameter die Speicheradresse, die als Operand für die Operation verwendet wird.
     */
    public ArithmeticInstruction(String operation, int parameter) {
        super(operation, parameter);
    }

    /**
     * Führt die arithmetische Anweisung auf der gegebenen RAM-Maschine aus.
     * Wenn die Operation ADD ist, wird der Wert an der Speicheradresse, die durch den Parameter repräsentiert wird, zum Akkumulator addiert.
     * Wenn die Operation SUB ist, wird der Wert an der Speicheradresse, die durch den Parameter repräsentiert wird, vom Akkumulator subtrahiert.
     * Wenn die Operation ungültig ist, wird die RAM-Maschine angehalten und eine Fehlermeldung wird ausgegeben.
     *
     * @param ram die RAM-Maschine, auf der die Anweisung ausgeführt wird.
     */
    @Override
    public void execute(RAM ram) {
        switch (operation) {
            case "ADD":
                // Addiere den Inhalt des Speichers an Adresse parameter zum Inhalt im Akkumulator
                ram.setAccumulator(ram.getAccumulator() + ram.getMemoryValue(parameter));
                ram.incrementProgramCounter();
                break;
            case "SUB":
                // Subtrahiere den Inhalt des Speichers an Adresse parameter vom Inhalt im Akkumulator
                ram.setAccumulator(ram.getAccumulator() - ram.getMemoryValue(parameter));
                ram.incrementProgramCounter();
                break;
            default:
                System.out.println("Ungültige arithmetische Instruktion: " + operation);
                ram.setHalted(true);
                break;
        }
    }
}
