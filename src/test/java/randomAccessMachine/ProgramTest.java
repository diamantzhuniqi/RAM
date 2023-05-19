package randomAccessMachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {

    /**
     * Testet eine einfache Addition von zwei Werten.
     */
    @Test
    public void testSimpleAddition() {
        int[] memoryValues = {0, 2, 3}; // Speicheradresse 0: Ergebnis, Speicheradresse 1: Wert 1, Speicheradresse 2: Wert 2
        Instruction[] program = {
                new MemoryInstruction("LDA", 1),    // Lade Wert 1 in den Akkumulator
                new ArithmeticInstruction("ADD", 2),    // Addiere Wert 2 zum Akkumulator
                new MemoryInstruction("STA", 0)    // Speichere das Ergebnis in Speicheradresse 0
        };

        RAM ram = new RAM(memoryValues);
        ram.loadProgram(program);
        ram.execute();

        int result = ram.getMemoryValue(0);
        assertEquals(5, result);
    }

	/**
	 * Testet eine bedingte Multiplikation.
	 * Falls Wert 1 größer als Wert 2 ist, wird das Ergebnis der Multiplikation in Speicheradresse 0 gespeichert.
	 * Andernfalls wird der Akkumulator zurückgesetzt.
	 */
	@Test
	public void testConditionalMultiplication() {
	    int[] memoryValues = {0, 4, 3, 1}; // Speicheradresse 0: Ergebnis, Speicheradresse 1: Wert 1, Speicheradresse 2: Wert 2, Speicheradresse 3: Wert 1
	    Instruction[] program = {
	        new MemoryInstruction("LDA", 1),             // Lade Wert 1 in den Akkumulator
	        new ArithmeticInstruction("SUB", 3),         // Subtrahiere 1 vom Akkumulator
	        new ControlInstruction("JMZ", 8),            // Springe zur Instruktion 9, wenn der Akkumulator den Wert 0 enthält
	        new MemoryInstruction("STA", 1),             // Speichere das aktualisierte Wert 1
	        new MemoryInstruction("LDA", 0),             // Lade das bisherige Ergebnis in den Akkumulator
	        new ArithmeticInstruction("ADD", 2),         // Addiere Wert 2 zum Akkumulator
	        new MemoryInstruction("STA", 0),             // Speichere das aktualisierte Ergebnis in Speicheradresse 0
	        new ControlInstruction("JMP", 0),            // Springe zurück zur Instruktion 1
	        new ControlInstruction("HLT")                // Beende das Programm
	    };


        RAM ram = new RAM(memoryValues);
        ram.loadProgram(program);
        ram.execute();

        int result = ram.getMemoryValue(0);
        assertEquals(12, result);
    }
	

    /**
     * Dieser Test überprüft die Implementierung der Fibonacci-Instruktion.
     * Das Programm verwendet die Speicheradressen 0, 1, 2, 3 und 4, um das Ergebnis,
     * die vorherige Fibonacci-Zahl, die aktuelle Fibonacci-Zahl, einen temporären Wert
     * und den Zähler zu speichern.
     */
    @Test
    public void testFibonacciInstruction() {
    	int[] memoryValues = {7, 1, 1, 0, 0};
    	Instruction[] program = {
                new MemoryInstruction("LDA", 2),                // Lade Wert 2 in den Akkumulator
                new ArithmeticInstruction("ADD", 3),            // Addiere Wert 3 zum Akkumulator
                new MemoryInstruction("STA", 4),                // Speichere den Akkumulatorwert in Speicheradresse 4
                new MemoryInstruction("LDA", 3),                // Lade Wert 3 in den Akkumulator
                new MemoryInstruction("STA", 2),                // Speichere den Akkumulatorwert in Speicheradresse 2
                new MemoryInstruction("LDA", 4),                // Lade Wert 4 in den Akkumulator
                new MemoryInstruction("STA", 3),                // Speichere den Akkumulatorwert in Speicheradresse 3
                new MemoryInstruction("LDA", 0),                // Lade Wert 0 in den Akkumulator
                new ArithmeticInstruction("SUB", 1),            // Subtrahiere Wert 1 vom Akkumulator
                new MemoryInstruction("STA", 0),                // Speichere den Akkumulatorwert in Speicheradresse 0
                new ControlInstruction("JMZ", 12),              // Springe zur Instruktion 12, wenn der Akkumulator den Wert 0 enthält
                new ControlInstruction("JMP", 0),               // Springe zur Instruktion 0
                new ControlInstruction("HLT")                    // Beende das Programm
            };
    	
    	RAM ram = new RAM(memoryValues);
        ram.loadProgram(program);
        ram.execute();

        int result = ram.getMemoryValue(0);
        assertEquals(13, result);
    }
}


