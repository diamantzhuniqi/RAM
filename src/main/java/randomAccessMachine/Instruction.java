package randomAccessMachine;

/**
 * Diese abstrakte Klasse definiert eine generische Instruktion für eine Random Access Machine (RAM).
 * Jede spezifische Instruktion erbt von dieser Klasse und implementiert die execute-Methode entsprechend ihrer Logik.
 */
public abstract class Instruction implements Executable {
    public String operation;
    public int parameter;
    
    /**
     * Konstruktor für eine Instruktion. Erstellt eine neue Instruktion mit der gegebenen Operation und dem Parameter.
     *
     * @param operation die Operation, die von dieser Instruktion ausgeführt wird.
     * @param parameter der Parameter, der für die Ausführung der Operation benötigt wird.
     */
    public Instruction(String operation, int parameter) {
    	this.operation = operation;
    	this.parameter = parameter;
    }
    
    /**
     * Führt die spezifische Operation dieser Instruktion auf der gegebenen RAM aus.
     * Die spezifische Logik der Ausführung wird von den konkreten Unterklassen von Instruction bereitgestellt.
     *
     * @param ram die RAM, auf der die Operation ausgeführt wird.
     */
    public abstract void execute(RAM ram);

}
