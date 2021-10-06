package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.LearningCard.STATUS;

public class LearningCards {

	public static List<LearningCard> itemsJavaBasics = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("What components can a class body contain?",
	    			"\n* fields\n* methods\n* constructors\n* initializer blocks\n* nested datatypes",
	    			"Welche Komponenten kann ein Klassenrumpf enthalten?"),
		    		new LearningCard("What is the scope of a class variable, a.k.a. static field?",
		        			"A static field is available to any object of the class it is defined in.",
		        			"Was ist der Gültigkeitsbereich einer Klassenvariablen, auch bekannt als statisches Feld?"),
		    		
		    		new LearningCard("Which variables require the programmer to initialize\r\n"
		    				+ "them before use: class, local or instance?", 
		    				"Local.",
		    				"Welche Variablen müssen vom Programmierer initialisiert werden\r\n"
		    				+ "vor der Verwendung: Klasse, lokal oder Instanz?"),
		    		new LearningCard("What statement is used to gain access to datatypes\r\n"
		    				+ "outside the current package?",
		    				"import",
		    				"Welche Anweisung wird verwendet, um Zugriff auf Datentypen zu erhalten\r\n"
		    				+ "außerhalb des aktuellen Pakets?"),
		    		new LearningCard("What character is used to import all datatypes\r\n"
		    				+ "contained within a package?", 
		    				"The wildcard character *.",
		    				"Welches Zeichen wird verwendet, um alle Datentypen zu importieren\r\n"
		    				+ "in einem Paket enthalten?"),
		    		new LearningCard("Which variables are in scope for the entire program:\r\n"
		    				+ "class, local or instance?", 
		    				"Class variables.",
		    				"Welche Variablen gelten für das gesamte Programm:\r\n"
		    				+ "Klasse, lokal oder Instanz?"),
		    		new LearningCard("True or false: An instance method is allowed\r\n"
		    				+ "to reference a static variable.", 
		    				"True.",
		    				"Wahr oder falsch: Eine Instanzmethode ist zulässig\r\n"
		    				+ "um auf eine statische Variable zu verweisen."),
		    		new LearningCard("Which Java feature makes use of access modifiers?",
		    				"Encapsulation.",
		    				"Welche Java-Funktion verwendet Zugriffsmodifizierer?"),
		    		new LearningCard("Suggest all possible ways to import the static variable\r\n"
		    				+ "NAME declared in a.b.C.",
		    				"import static a.b.C.NAME;\r\n"
		    				+ "or\r\n"
		    				+ "import static a.b.C.*;",
		    				"Schlagen Sie alle Möglichkeiten vor, um die statische Variable zu importieren\r\n"
		    				+ "NAME deklariert in a.b.C."),
		    		new LearningCard("Is this a constructor or a method?\r\n"
		    				+ "public void MyClass(String name) {\r\n"
		    				+ "this.name = name;\r\n"
		    				+ "}",
		    				"It is not a constructor because it defines a return type.",
		    				""),
		    		
		    		new LearningCard("What statement must a non-void method contain?", 
		    				"return",
		    				"Welche Aussage muss eine nicht-void-Methode enthalten?"),
		    		new LearningCard("What is the scope of local variables?", 
		    				"The current code block.",
		    				"Welchen Gültigkeitsbereich haben lokale Variablen?"),
		    		new LearningCard("How can we get the first command-line argument?",
		    				"E.g., args[0]",
		    				"Wie können wir das erste Befehlszeilenargument erhalten?"),
		    		new LearningCard("What is the scope of instant variables, a.k.a.\r\n"
		    				+ "non-static fields?",
		    				"Non-static fields are available within the current object only.",
		    				"Was ist der Umfang von Instanz-Variablen, auch bekannt als\r\n"
		    				+ "nicht statische Felder?"),
		    		new LearningCard("What is most typical signature for the main() method\r\n"
		    				+ "when it is being used as the program’s entry point?", 
		    				"public static void main(String[] args)",
		    				"Was ist die typischste Signatur für die Methode main()?\r\n"
		    				+ "wenn es als Einstiegspunkt des Programms verwendet wird?"),
		    		new LearningCard("What platform-independent format is any Java\r\n"
		    				+ "program compiled to?",
		    				"Bytecode.",
		    				"Welches plattformunabhängige Format ist ein Java\r\n"
		    				+ "Programm kompiliert?"),
		    		new LearningCard("Which variables may have a scope smaller\r\n"
		    				+ "than a method: class, local or instance?",
		    				"Local variables.",
		    				"Welche Variablen können einen kleineren Umfang haben\r\n"
		    				+ "als eine Methode: Klasse, lokal oder Instanz?"),
		    		new LearningCard("Which ones are subject to implicit initialization:\r\n"
		    				+ "fields or local variables or both?",
		    				"Fields.",
		    				"Welche unterliegen der impliziten Initialisierung:\r\n"
		    				+ "Felder oder lokale Variablen oder beides?"),
		    		new LearningCard("What is the name of the method that must be invoked to\r\n"
		    				+ "guarantee that the garbage collector will run?", 
		    				"Trick question! The System.gc() method (which is effectively\r\n"
		    				+ "equivalent to the call Runtime.getRuntime().gc()) merely\r\n"
		    				+ "suggests that the GC should run but makes no guarantees.",
		    				"Wie heißt die Methode, die aufgerufen werden muss?\r\n"
		    				+ "garantieren, dass der Garbage Collector ausgeführt wird?"),
		    		new LearningCard("What Java feature uses objects to model\r\n"
		    				+ "real-world scenarios?",
		    				"Object orientation.",
		    				"Welche Java-Funktion verwendet Objekte zum Modellieren?\r\n"
		    				+ "reale Szenarien?"),
		    		new LearningCard("What packages are imported by default?",
		    				"Only one package is imported by default, and it’s java.lang.",
		    				"Welche Pakete werden standardmäßig importiert?"),
		    		new LearningCard("What statement provides access to constants\r\n"
		    				+ "and static methods of a class?", 
		    				"import static",
		    				"Welche Anweisung bietet Zugriff auf Konstanten\r\n"
		    				+ "und statische Methoden einer Klasse?"),
		    		new LearningCard("What is the scope of method parameters?",
		    				"Method parameters are available with the current method only.",
		    				"Welchen Umfang haben Methodenparameter?"),
		    		new LearningCard("True or false: The finalize() method of an Object can be\r\n"
		    				+ "invoked multiple times.", 
		    				"False: the finalize() method is never invoked more than once by\r\n"
		    				+ "a Java virtual machine for any given object.",
		    				"Wahr oder falsch: Die Methode finalize() eines Objekts kann\r\n"
		    				+ "mehrfach aufgerufen."),
		    		new LearningCard("What is the length of the String array that contains the\r\n"
		    				+ "command-line arguments in the following scenario?\r\n"
		    				+ "java Test arg1,arg2", 
		    				"One.",
		    				"Wie lang ist das String-Array, das die enthält\r\n"
		    				+ "Befehlszeilenargumente im folgenden Szenario?\r\n"
		    				+ "Java-Test arg1,arg2"),
		    		new LearningCard("Given the following packages a.A, a.b.B, a.b.c.C ,\r\n"
		    				+ "which of them does the statement import a.b.*\r\n"
		    				+ "actually import?", 
		    				"Only a.b.B, because the import wildcard * does not extend to\r\n"
		    				+ "subpackages.",
		    				"Bei folgenden Paketen a.A, a.b.B, a.b.c.C ,\r\n"
		    				+ "Welche davon importiert die Anweisung a.b.*\r\n"
		    				+ "eigentlich importieren?"),
		    		
		    		new LearningCard("Given that an executable public class named HelloWorld\r\n"
		    				+ "is defined in the a.b.c package, what should be the pair of\r\n"
		    				+ "commands to compile the class without the –d switch and\r\n"
		    				+ "then run it?",
		    				"javac .\\a\\b\\c\\HelloWorld.java (where the .\\ part is optional)\r\n"
		    				+ "(or just javac HelloWorld.java, depending on the location)\r\n"
		    				+ "java a.b.c.HelloWorld",
		    				"Da eine ausführbare öffentliche Klasse namens HelloWorld\r\n"
		    				+ "ist im a.b.c-Paket definiert, was sollte das Paar von\r\n"
		    				+ "Befehlen zum Kompilieren der Klasse ohne den Schalter –d und\r\n"
				    				+ "dann ausführen?"),
		    		new LearningCard("In what order must the class declaration(s), import\r\n"
		    				+ "statement(s) and package declaration be placed\r\n"
		    				+ "within a .java file?",
		    				"package declaration (optional)\r\n"
		    				+ "import statement(s) (optional)\r\n"
		    				+ "class declaration(s)",
		    				"In welcher Reihenfolge müssen die Klassendeklarationen importiert werden?\r\n"
		    				+ "Anweisung(en) und Paketdeklaration platziert werden\r\n"
		    				+ "innerhalb einer .java-Datei?")
			));

	public static List<LearningCard> itemsWorkingWithJavaDataTypes = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("\"What are the eight primitive types in Java?",
	    			"byte, short, int, long, float, double, char, and boolean."),
		    		new LearningCard("What’s the name of the process when the compiler\\r\\nautomatically converts an int to an Integer?",
		        			"Autoboxing."),
		    		new LearningCard("What are the default values for these instance variables?\r\n"
		    				+ "String str; int num; double d; boolean bool;", 
		    				"str = null;\r\n"
		    				+ "num = 0;\r\n"
		    				+ "d = 0.0;\r\n"
		    				+ "bool = false;",
		    				""),
		    		new LearningCard("What type would be used for a method parameter\r\n"
		    				+ "if it’s meant to support any variable?",
		    			"java.lang.Object",
		    			"Welcher Typ würde für einen Methodenparameter verwendet werden?\r\n"
		    			+ "wenn es irgendeine Variable unterstützen soll?"),
		    		new LearningCard("Which of the following variables can null be assigned to?\r\n"
		    				+ "Object obj; String str; int num; boolean bool;",
		    				"Object obj and String str can be assigned null because they\r\n"
		    				+ "are reference types. As for int and boolean, they are primitives\r\n"
		    				+ "and so cannot be assigned null.",
		    				"Which of the following variables can null be assigned to?\r\n"
		    				+ "Object obj; String str; int num; boolean bool;"),
		    		new LearningCard("What characters can a variable name start with?",
		    				"For the exam: any Latin letter, dollar sign $, or underscore _.",
		    				"Mit welchen Zeichen kann ein Variablenname beginnen?"),
		    		new LearningCard("What characters may a variable name contain?",
		    				"For the exam: a Latin letter, number, dollar sign, or underscore",
		    				"Welche Zeichen darf ein Variablenname enthalten?"),
		    		new LearningCard("Which primitive type uses four bytes to represent a\r\n"
		    				+ "signed fractional value with 23 bits for the precision\r\n"
		    				+ "and 8 bits for the scale?",
		    				"float",
		    				"Welcher primitive Typ verwendet vier Bytes, um a darzustellen?\r\n"
		    				+ "vorzeichenbehafteter Bruchwert mit 23 Bit für die Genauigkeit\r\n"
		    				+ "und 8 Bit für die Skala?"),
		    		
		    		
		    		new LearningCard("True or false: When passing an ArrayList named list to a\r\n"
		    				+ "method, calls to list.add() are reflected in the caller.", 
		    				"True.",
		    				"Wahr oder falsch: Beim Übergeben einer ArrayList-Namensliste an a\r\n"
		    				+ "-Methode werden Aufrufe von list.add() im Aufrufer reflektiert."),
		    		
		    		new LearningCard("To which purpose would you place underscore(s)\r\n"
		    				+ "in a numeric literal?", 
		    				"To increase readability.\r\n\r\n" + "Um die Lesbarkeit zu erhöhen"),
		    		
		    		new LearningCard("True or false: An abstract class that implements\r\n"
		    				+ "an interface is not required to implement any of\r\n"
		    				+ "the interface's abstract methods.",
		    				"True.",
		    				"Richtig oder falsch: Eine abstrakte Klasse, die . implementiert\r\n"
		    				+ "Eine Schnittstelle ist nicht erforderlich, um eine der folgenden Funktionen zu implementieren\r\n"
		    				+ "die abstrakten Methoden der Schnittstelle.\r\n"
		    				+ "\r\n"
		    				+ "Symbol Von der Community überprüft"),
		    		new LearningCard("What are the only two possible values valid\r\n"
		    				+ "for a boolean variable?",
		    				"true and false.",
		    				"Was sind die einzigen zwei möglichen Werte, die gültig sind?\r\n"
		    				+ "für eine boolesche Variable?"),

		    		new LearningCard("Which primitive type uses eight bytes to represent\r\n"
		    				+ "a signed fractional value with 53 bits for the\r\n"
		    				+ "precision and 11 bits for the scale?",
		    				"double",
		    				"Welcher primitive Typ verwendet acht Bytes zur Darstellung?\r\n"
		    				+ "ein vorzeichenbehafteter Bruchwert mit 53 Bit für die\r\n"
		    				+ "Präzision und 11 Bit für die Skala?"),
		    		new LearningCard("What keyword is required to instantiate a class?",
		    				"new",
		    				"Welches Schlüsselwort ist erforderlich, um eine Klasse zu instanziieren?"),
		    		new LearningCard("Which primitive type represents a signed integer\r\n"
		    				+ "within the range of –32,768 to 32,767?", "short"),
		  
		      		new LearningCard("What exception is thrown when you use the\r\n"
		    				+ "parseInt() method with an unparsable argument?",
		    				"NumberFormatException\r\nRuntimeException\r\n"
		    				+ " -IllegalArgumentException\r\n"
		    				+ " -NumberFormat Exception\r\n"
		    				+ "IAE: Thrown to indicate that a method has been passed an\r\n"
		    				+ "illegal or inappropriate argument.\r\n"
		    				+ "NFE: Thrown to indicate that the application has attempted\r\n"
		    				+ "to convert a string to one of the numeric types, but\r\n"
		    				+ "that the string does not have the appropriate format.",
		    				"Welche Ausnahme wird ausgelöst, wenn Sie das verwenden?\r\n"
		    				+ "parseInt()-Methode mit einem nicht analysierbaren Argument?"),
		    		
		    		
		    		
		    		
		    		new LearningCard("Given the non-abstract class Test and the instance\r\n"
		    				+ "field fieldA, what expression will retrieve the value of\r\n"
		    				+ "fieldA?", 
		    				"new Test().fieldA;",
		    				"Gegeben die nicht abstrakte Klasse Test und die Instanz\r\n"
		    				+ "field fieldA, welcher Ausdruck wird den Wert von abrufen\r\n"
		    				+ "FeldA?"),
	
		    		new LearningCard("ls 0xCAFE_BABE a valid numerical literal in Java 8?", 
		    				"Yes, it is."),
		    		new LearningCard("Which primitive type represents a signed\r\n"
		    				+ "integer within the range of –128 to 127?", "byte"),
		    		new LearningCard("What is the decimal value for the numerical literal 0xA0?", "160"),
		    		new LearningCard("Which type of variable, primitive or reference, stores\r\n"
		    				+ "a pointer to an object?",
		    				"Reference type.",
		    				"Welche Art von Variable, Primitiv oder Referenz, speichert\r\n"
		    				+ "ein Zeiger auf ein Objekt?"),
		    		new LearningCard("What is the decimal value for the numerical literal\r\n"
		    				+ "0b1111_1111?", "255"),
		    		new LearningCard("Which primitive type represents a character\r\n"
		    				+ "encoded in UTF-16?", "char"),
		    		new LearningCard("Which exception is thrown when you try to get a null\r\n"
		    				+ "value unboxed by Java?", "NullPointerException"),
		    		new LearningCard("ls 1.0_F a valid numerical literal in Java 8?", "No, it isn’t."),
		    		new LearningCard("Choose the method that can be used to remove 3\r\n"
		    				+ "from the array declared as int[] arr = {1,2,3};\r\n"
		    				+ "java.util.Arrays.remove(2)\r\n"
		    				+ "or\r\n"
		    				+ "java.util.Arrays.delete(3)?",
		    				"Trick question! Arrays are structurally immutable in the first place\r\n"
		    				+ "and, besides, the class java.util.Arrays defines neither remove()\r\n"
		    				+ "nor delete().\r\n\r\nArrays sind in erster Linie strukturell unveränderlich\r\n"
		    				+ "und außerdem definiert die Klasse java.util.Arrays weder remove()\r\n"
		    				+ "noch delete().",
		    				"Wählen Sie die Methode, mit der 3 . entfernt werden können\r\n"
		    				+ "aus dem als int[] deklarierten Array arr = {1,2,3};\r\n"
		    				+ "java.util.Arrays.remove(2)\r\n"
		    				+ "oder\r\n"
		    				+ "java.util.Arrays.delete(3)?"),
		    		new LearningCard("Which prefix in a numerical literal indicates a\r\n"
		    				+ "hexadecimal number?", "0x"),
		    		new LearningCard("Which action must be performed first to access an\r\n"
		    				+ "instance member in a class?", 
		    				"The class must be instantiated.",
		    				"Welche Aktion muss zuerst ausgeführt werden, um auf eine\r\n"
		    				+ "Instanzmitglied in einer Klasse?"),
		    		new LearningCard("ls 123_456L a valid numerical literal in Java 8?", "Yes, it is."),
		    		new LearningCard("Which type of variable, primitive or reference, stores\r\n"
		    				+ "only its underlying value?", 
		    				"The primitive type.",
		    				"Welche Art von Variable, Primitiv oder Referenz, speichert\r\n"
		    				+ "nur der zugrunde liegende Wert?"),
		    		new LearningCard("In what phase is an object when the GC is about to\r\n"
		    				+ "remove it from memory?",
		    				"The object is finalized.",
		    				"In welcher Phase befindet sich ein Objekt, wenn der GC im Begriff ist,\r\n"
		    				+ "aus dem Speicher entfernen?"),
		    		new LearningCard("Which primitive type is default for a signed integer?", 
		    				"int\r\n\r\nJLS, §4.2, Primitive Types and Values:\r\n"
		    				+ "The integral types are byte, short, int, and long, whose\r\n"
		    				+ "values are 8-bit, 16-bit, 32-bit and 64-bit signed two'scomplement\r\n"
		    				+ "integers, respectively, and char, whose values are\r\n"
		    				+ "16-bit unsigned integers representing UTF-16 code units.\r\n"
		    				+ "Official Oracle Tutorial:\r\n"
		    				+ "In Java SE 8 and later, you can use the int data type to\r\n"
		    				+ "represent an unsigned 32-bit integer, which has a minimum value\r\n"
		    				+ "of 0 and a maximum value of 232-1. Use the Integer class to use\r\n"
		    				+ "int data type as an unsigned integer."),
		    		new LearningCard("Which type of variable, primitive or reference, shares a\r\n"
		    				+ "copy of an object pointer in an assignment operation?",
		    				"The reference type",
		    				"Welche Art von Variable, Primitiv oder Referenz, teilt a\r\n"
		    				+ "Kopie eines Objektzeigers in einer Zuweisungsoperation?"),
		    		new LearningCard("Which suffix specifies that a literal value should be\r\n"
		    				+ "stored as a long?", "L"),
		    		new LearningCard("Which specialized operation is invoked like a method\r\n"
		    				+ "to initialize an object?",
		    				"A constructor.,"
		    				+ "Welche spezialisierte Operation wird wie eine Methode aufgerufen\r\n"
		    				+ "ein Objekt initialisieren?"),
		    		new LearningCard("Which three locations are invalid when placing\r\n"
		    				+ "underscores in a numeric literal?",
		    				"Beginning or end of the number, contiguous to decimal point, or\r\n"
		    				+ "contiguous to F / L suffixes.\r\n\r\nAnfang oder Ende der Zahl, an das Komma angrenzend, oder\r\n"
		    				+ "angrenzend an F / L-Suffixe.",
		    				"Welche drei Orte sind beim Platzieren ungültig\r\n"
		    				+ "Unterstriche in einem numerischen Literal?"),
		    		new LearningCard("Which suffix specifies a literal value should be stored\r\n"
		    				+ "as a float?", "f"),
		    		new LearningCard("List<double> list = new ArrayList<>();\r\n"
		    				+ "list.add(1.0); // Line X\r\n"
		    				+ "Which operation is used on Line X:\r\n"
		    				+ "autoboxing or unboxing?",
		    				"Neither: the code fails compilation as Collections accept reference\r\n"
		    				+ "types only.\r\n"
		    				+ "Changing to List<Double> would mean that Line X uses autoboxing."),
		    		new LearningCard("In what phase is an object when it goes out of scope\r\n"
		    				+ "and has no remaining live pointers?", 
		    				"The object is dereferenced.",
		    				"In welcher Phase befindet sich ein Objekt, wenn es den Geltungsbereich verlässt?\r\n"
		    				+ "und hat keine verbleibenden Live-Zeiger?"),
		    		new LearningCard("True or false: When passing an ArrayList named list\r\n"
		    				+ "to a method, re-assigning\r\n"
		    				+ "list = new ArrayList()\r\n"
		    				+ "inside the method affects the caller.", 
		    				"False.",
		    				"Wahr oder falsch: Beim Übergeben einer ArrayList namens list\r\n"
		    				+ "zu einer Methode, Neuzuweisung\r\n"
		    				+ "list = neue ArrayList()\r\n"
		    				+ "innerhalb der Methode wirkt sich auf den Aufrufer aus.")
			));
	
	public static List<LearningCard> usingOperatorsAndDecisionConstructs = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 3.1?", "Answer 3.1"),
		    		new LearningCard("Ask 3.2", "Answer 3.2"),
		    		new LearningCard("Ask 3.3?", "Answer 3.3"),
		    		new LearningCard("Ask 3.4", "Answer 3.4"),
		    		new LearningCard("Ask 3.5?", "Answer 3.5"),
		    		new LearningCard("Ask 3.6?", "Answer 3.6"),
		    		new LearningCard("Ask 3.7?", "Answer 3.7"),
		    		new LearningCard("Ask 3.8?", "Answer 3.8"),
		    		new LearningCard("Ask 3.9?", "Answer 3.9"),
		    		new LearningCard("Ask 3.10", "Answer 3.10"),
		    		new LearningCard("Ask 3.1?", "Answer 3.11"),
		    		new LearningCard("Ask 3.2", "Answer 3.12"),
		    		new LearningCard("Ask 3.3?", "Answer 3.13"),
		    		new LearningCard("Ask 3.4", "Answer 3.14"),
		    		new LearningCard("Ask 3.5?", "Answer 3.15"),
		    		new LearningCard("Ask 3.6?", "Answer 3.16"),
		    		new LearningCard("Ask 3.7?", "Answer 3.17"),
		    		new LearningCard("Ask 3.8?", "Answer 3.18"),
		    		new LearningCard("Ask 3.9?", "Answer 3.19"),
		    		new LearningCard("Ask 3.10", "Answer 3.20")
			));
	
	public static List<LearningCard> creatingAndUsingArrays = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 4.1?", "Answer 4.1"),
		    		new LearningCard("Ask 4.2", "Answer 4.2"),
		    		new LearningCard("Ask 4.3?", "Answer 4.3"),
		    		new LearningCard("Ask 4.4", "Answer 4.4"),
		    		new LearningCard("Ask 4.5?", "Answer 4.5"),
		    		new LearningCard("Ask 4.6?", "Answer 4.6"),
		    		new LearningCard("Ask 4.7?", "Answer 4.7"),
		    		new LearningCard("Ask 4.8?", "Answer 4.8"),
		    		new LearningCard("Ask 4.9?", "Answer 4.9"),
		    		new LearningCard("Ask 4.10", "Answer 4.10")
			));
	
	public static List<LearningCard> usingLoopConstructs = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 5.1?", "Answer 5.1"),
		    		new LearningCard("Ask 5.2", "Answer 5.2"),
		    		new LearningCard("Ask 5.3?", "Answer 5.3"),
		    		new LearningCard("Ask 5.4", "Answer 5.4"),
		    		new LearningCard("Ask 5.5?", "Answer 5.5"),
		    		new LearningCard("Ask 5.6?", "Answer 5.6"),
		    		new LearningCard("Ask 5.7?", "Answer 5.7"),
		    		new LearningCard("Ask 5.8?", "Answer 5.8"),
		    		new LearningCard("Ask 5.9?", "Answer 5.9"),
		    		new LearningCard("Ask 5.10", "Answer 5.10")
			));
	
	public static List<LearningCard> workingWithMethodAndEncapsulation = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 6.1?", "Answer 6.1"),
		    		new LearningCard("Ask 6.2", "Answer 6.2"),
		    		new LearningCard("Ask 6.3?", "Answer 6.3"),
		    		new LearningCard("Ask 6.4", "Answer 6.4"),
		    		new LearningCard("Ask 6.5?", "Answer 6.5"),
		    		new LearningCard("Ask 6.6?", "Answer 6.6"),
		    		new LearningCard("Ask 6.7?", "Answer 6.7"),
		    		new LearningCard("Ask 6.8?", "Answer 6.8"),
		    		new LearningCard("Ask 6.9?", "Answer 6.9"),
		    		new LearningCard("Ask 6.10", "Answer 6.10")
			));
	
	public static List<LearningCard> workingWithInheritance = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 7.1?", "Answer 7.1"),
		    		new LearningCard("Ask 7.2", "Answer 7.2"),
		    		new LearningCard("Ask 7.3?", "Answer 7.3"),
		    		new LearningCard("Ask 7.4", "Answer 7.4"),
		    		new LearningCard("Ask 7.5?", "Answer 7.5"),
		    		new LearningCard("Ask 7.6?", "Answer 7.6"),
		    		new LearningCard("Ask 7.7?", "Answer 7.7"),
		    		new LearningCard("Ask 7.8?", "Answer 7.8"),
		    		new LearningCard("Ask 7.9?", "Answer 7.9"),
		    		new LearningCard("Ask 7.10", "Answer 7.10")
			));
	
	public static List<LearningCard> handlingExceptions = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 8.1?", "Answer 8.1"),
		    		new LearningCard("Ask 8.2", "Answer 8.2"),
		    		new LearningCard("Ask 8.3?", "Answer 8.3"),
		    		new LearningCard("Ask 8.4", "Answer 8.4"),
		    		new LearningCard("Ask 8.5?", "Answer 8.5"),
		    		new LearningCard("Ask 8.6?", "Answer 8.6"),
		    		new LearningCard("Ask 8.7?", "Answer 8.7"),
		    		new LearningCard("Ask 8.8?", "Answer 8.8"),
		    		new LearningCard("Ask 8.9?", "Answer 8.9"),
		    		new LearningCard("Ask 8.10", "Answer 8.10")
			));
	
	public static List<LearningCard> workingWithSelectedClassesFromAPI = new ArrayList<LearningCard>(
		    Arrays.asList(
		    		new LearningCard("Ask 9.1?", "Answer 9.1"),
		    		new LearningCard("Ask 9.2", "Answer 9.2"),
		    		new LearningCard("Ask 9.3?", "Answer 9.3"),
		    		new LearningCard("Ask 9.4", "Answer 9.4"),
		    		new LearningCard("Ask 9.5?", "Answer 9.5"),
		    		new LearningCard("Ask 9.6?", "Answer 9.6"),
		    		new LearningCard("Ask 9.7?", "Answer 9.7"),
		    		new LearningCard("Ask 9.8?", "Answer 9.8"),
		    		new LearningCard("Ask 9.9?", "Answer 9.9"),
		    		new LearningCard("Ask 9.10", "Answer 9.10")
			));


	/*public static int index = 0;
	private static int correctCardCount = 0;
	private static int learningCardsCount = 0;
	private static int wrongCardCount = 0;
	private static boolean frontSide = true;
	private static List<LearningCard> currentLearningCards;
	private static LearningCard currentLearningCard;
	

	public static void SetCurrentLearningCards(List<LearningCard> learningCards) {
		currentLearningCards = learningCards;
	}
	public static int getCorrectCardCount() {
		return correctCardCount;
	}
	public static void setCorrectCardCount(int correctCardCount) {
		LearningCards.correctCardCount = correctCardCount;
	}
	public static int getLearningCardsCount() {
		return learningCardsCount;
	}
	public static void setLearningCardsCount(int learningCardsCount) {
		LearningCards.learningCardsCount = learningCardsCount;
	}
	public static int getWrongCardCount() {
		return wrongCardCount;
	}
	public static void setWrongCardCount(int wrongCount) {
		LearningCards.wrongCardCount = wrongCount;
	}
	public static void setFrontSide(boolean frontSide) {
		LearningCards.frontSide = frontSide;
	}
	public static boolean isFrontSide() {
		return LearningCards.frontSide;
	}*/
	
	/**
	 * updateLearningCardStatus
	 * */
	/*public static void updateLearningCardStatus(LearningCard.STATUS status) {
		
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return;
		
		if (status == LearningCard.STATUS.CORRECT) {
			// Karten zum nächsten Level verschieben
			currentLearningCard.goToNextLevel();	
			currentLearningCard.setStatus(LearningCard.STATUS.CORRECT);
			correctCardCount ++;
			learningCardsCount--;
			
		}
		else if (status == LearningCard.STATUS.WRONG) {
			currentLearningCard.setStatus(LearningCard.STATUS.WRONG);
			wrongCardCount ++;
			learningCardsCount--;
		}
	}*/
	
	/**
	 * toNextLearningCard
	 * */
	/*public static void toNextLearningCard() {
		if (index < 0)
			index = 0;
		else if (index < currentLearningCards.size()-1) {
			index ++;
		}
		
		currentLearningCard = LearningCards.currentLearningCards.get(LearningCards.index);
	}*/
	
	/**
	 * changeTheme
	 * */
	/*public static void initialize() {
	
		index = currentLearningCards.size() > 0 ? 0 : -1;
		
		// Karte auswählen
		toNextLearningCard();
		// Vorderseite abzeigen
		setFrontSide(true);
		
		// Nichtgelernte-Kartenanzahl auf 'max' setzen
		setLearningCardsCount(currentLearningCards.size());
		// Richtigbeantworte-Kartenanzahl auf '0' setzen
		setCorrectCardCount(0);
		// Falschbeantwortete-Kartenanzahl auf '0' setzen
		setWrongCardCount(0);
	}*/
	
	/**
	 * changeCardSide
	 * */
	/*public static void changeCardSide() {
		LearningCards.frontSide = !LearningCards.frontSide;
	}*/
	
	/**
	 * LearningCard: get text
	 * */
	/*public static String getLearningCardText() {
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return "not defined";
		
		return isFrontSide() 
				? currentLearningCard.getAsk()
				: currentLearningCard.getAnswer();
	}*/
	
	/**
	 * LearningCard: get status
	 * */
	/*public static LearningCard.STATUS getLearningCardStatus() {
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return LearningCard.STATUS.NOT_TESTED;
		
		return currentLearningCard.getStatus();
	}*/
	
	/**
	 * LearningCard: get style
	 * */
	/*public static String getLearningCardStyle() {
		// todo
		final String ASK_DEFAULT_STYLE = "-fx-border-color: rgba(130, 130, 130, 0.5);-fx-border-width: 10px";
		final String ask_wrong_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String ask_correct_style = "-fx-border-color: rgba(0, 255, 0, 0.5);-fx-border-width: 10px";
		final String answer_style = "-fx-border-color: rgba(255, 255, 0, 0.5);-fx-border-width: 10px";
		
		assert (currentLearningCard != null);
		if (currentLearningCard == null)
			return ASK_DEFAULT_STYLE;
	
		if (isFrontSide()) {
		
			if(getLearningCardStatus() == LearningCard.STATUS.WRONG) {
				return ask_wrong_style;
			}
			else if (getLearningCardStatus() == LearningCard.STATUS.CORRECT) {
				return ask_correct_style;
			}
			else {
				return ASK_DEFAULT_STYLE;
			}
		}
		else {
			return answer_style;
		}
	}*/
	
}
