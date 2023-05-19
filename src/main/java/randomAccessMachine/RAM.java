package randomAccessMachine;

import java.util.ArrayList;

/**
 * Die Klasse RAM stellt eine einfache Implementation einer Random Access Machine dar.
 * Sie besteht aus einem Speicher, einem Akkumulator und einem Programmzähler.
 */
public class RAM {
    private ArrayList<Integer> memory; // Der Speicher der RAM
    private int accumulator; 			// Der Akkumulator der RAM
    private int programCounter;			// Der Programmzähler der RAM
    private boolean halted;				// Der Status, welcher überprüfen sollte, ob Programm angehalten wurde oder nicht
    private Instruction[] program;		// Das auszuführende Programm

    /**
     * Der Konstruktor initialisiert die RAM mit den angegebenen Speicherwerten.
     * Der Akkumulator und der Programmzähler werden auf Null gesetzt, der Status wird auf nicht angehalten gesetzt.
     *
     * @param memoryValues die initialen Werte, die in den Speicher geladen werden sollen
     */
    public RAM(int... memoryValues) {
        program = new Instruction[0]; // vorerst leer, bis loadProgram aufgerufen wird
        accumulator = 0;
        programCounter = 0;
        halted = false;
        memory = new ArrayList<>();
        for(int value : memoryValues) {
            memory.add(value);
        }
    }

    /**
     * Diese Methode lädt ein Programm in die RAM. Das Programm besteht aus einer Reihe von Anweisungen.
     *
     * @param program das zu ladende Programm
     */
    public void loadProgram(Instruction[] program) {
        this.program = program;
    }
    
    /**
     * Diese Methode führt das geladene Programm aus. Sie liest und führt jede Anweisung nacheinander aus, 
     * bis das Programm angehalten wird oder keine Anweisungen mehr vorhanden sind.
     */
    public void execute() {
        while (!halted && programCounter < program.length) {
            Instruction currentInstruction = program[programCounter];
            System.out.println("Ausführung der Instruktion: " + currentInstruction.operation);
            currentInstruction.execute(this);
            System.out.println("Aktueller Wert des Akkumulators: " + accumulator);
            System.out.println("Programmzähler: " + programCounter);
        }
        System.out.println("Programm beendet.");
    }


    /**
     * Gibt den aktuellen Wert des Akkumulators zurück.
     *
     * @return der aktuelle Wert des Akkumulators.
     */
    public int getAccumulator() {
        return accumulator;
    }

    /**
     * Setzt den Wert des Akkumulators.
     *
     * @param accumulator der neue Wert des Akkumulators.
     */
    public void setAccumulator(int accumulator) {
        this.accumulator = accumulator;
    }
    
    /**
     * Gibt die Länge des aktuellen Programms zurück.
     *
     * @return die Anzahl der Anweisungen im aktuellen Programm.
     */
    public int getProgramLength() {
        return program.length;
    }

    /**
     * Gibt den aktuellen Wert des Programmzählers zurück.
     *
     * @return der aktuelle Wert des Programmzählers.
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Setzt den Wert des Programmzählers.
     *
     * @param programCounter der neue Wert des Programmzählers.
     */
    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    /**
     * Gibt zurück, ob die Maschine angehalten wurde.
     *
     * @return true, wenn die Maschine angehalten wurde, false sonst.
     */
    public boolean isHalted() {
        return halted;
    }

    /**
     * Setzt den Status der Maschine auf angehalten oder nicht angehalten.
     *
     * @param halted true, um die Maschine anzuhalten, false, um sie fortzusetzen.
     */
    public void setHalted(boolean halted) {
        this.halted = halted;
    }

    /**
     * Gibt den Wert an der angegebenen Speicheradresse zurück.
     *
     * @param address die Speicheradresse.
     * @return der Wert an der angegebenen Speicheradresse.
     * @throws IllegalArgumentException wenn die Adresse außerhalb des gültigen Bereichs liegt.
     */
    public int getMemoryValue(int address) {
        if (address >= 0 && address < memory.size()) {
            return memory.get(address);
        }
        throw new IllegalArgumentException("Ungültige Speicheradresse: " + address);
    }

    /**
     * Setzt den Wert an der angegebenen Speicheradresse.
     *
     * @param address die Speicheradresse.
     * @param value der zu setzende Wert.
     * @throws IllegalArgumentException wenn die Adresse außerhalb des gültigen Bereichs liegt.
     */
    public void setMemoryValue(int address, int value) {
        if (address >= 0 && address < memory.size()) {
            memory.set(address, value);
        } else {
            throw new IllegalArgumentException("Ungültige Speicheradresse: " + address);
        }
    }

    /**
     * Erhöht den Programmzähler um eins. Wenn der Programmzähler größer oder gleich der Programmlänge ist,
     * wird die Maschine angehalten.
     */
    public void incrementProgramCounter() {
        programCounter++;
        if (programCounter >= program.length) {
        	halted = true;
        }
    }
}