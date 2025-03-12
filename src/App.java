import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";

    private ArrayList<String> delimiters = new ArrayList<String>() {{
        add(",");
        add("!");
        add(".");
        add("?");
    }};

    private ArrayList<String> madlib = new ArrayList<String>();

    /**ArrayList Containing Verbs */
    private ArrayList<String> verbs;

    /**ArrayList Containing Nouns */
    private ArrayList<String> nouns;

    /**ArrayList Containing Adjectives */
    private ArrayList<String> adjectives;

    /**Delimiters */

    public static void main(String[] args) throws Exception {
        App app = new App();
        
        var madlib = app.getMadLibArrayList();
        for (var word : madlib) {
            System.out.println(word);
        }

        System.out.println(ANSI_RED + "TEST");
    }

    public App() throws IOException {
        verbs = new ArrayList<String>();
        nouns = new ArrayList<String>();
        adjectives = new ArrayList<String>();

        // This sets up the ArrayLists for the different parts of speech
        // Verbs - Red
        Files.lines(Paths.get("src/verbs.txt")).forEach(s -> verbs.add(s.trim().toLowerCase()));

        // Nouns - Green
        Files.lines(Paths.get("src/nouns.txt")).forEach(s -> nouns.add(s.trim().toLowerCase()));

        // Adjectives - Yellow
        Files.lines(Paths.get("src/adjectives.txt")).forEach(s -> adjectives.add(s.trim().toLowerCase()));

    }


    /**Gets the madlib as a String
     * 
     * @return String containing the 
     */
    public String getMadLib() {
        return "In the town of Techville, a group of friends—Maya, Leo, and Jay—were known as The Code Squad. They loved coding and solving problems using their computers. One afternoon, while they were in the school's coding club, the lights flickered. Then—BOOM!—the entire town lost power! The teacher checked her phone. “It looks like a virus has shut down the city's power grid,” she said. “Without electricity, hospitals, traffic lights, and even the water pumps won't work!” Maya's eyes widened. “We have to do something!” Leo pulled out his laptop. “If the virus is in the power grid, we might be able to write a program to track it down.” Jay grinned. “And if we find it, we can create an antivirus to stop it!” The Code Squad got to work. They connected to the emergency backup server at the school and used their programming skills to scan the system. “There it is!” Maya pointed at the screen. “The virus is hiding in the main control code.” Leo typed fast. “I'm writing a program to isolate the virus.” Jay added, “And I'll program a firewall to keep it from coming back!” After a few tense minutes, they uploaded their code. BEEP! BEEP! BEEP! The system flickered to life. Streetlights turned on. The fans in the school whirred. The town's power was back! The mayor called to thank them. “You kids saved Techville!” Maya, Leo, and Jay high-fived. “Just another mission for The Code Squad!” And from that day on, they knew that with computer science, they could solve any problem—even saving the world.";
    }

    public ArrayList<String> getMadLibArrayList() {
        String madlib = getMadLib();

        System.out.println(madlib);
        
        ArrayList<String> madlibArray = new ArrayList<String>();

        while (true) {
            int nextSpace = madlib.indexOf(" ");
            int next = nextSpace;
            for (String delimiter : delimiters) {
                int nextDelimiter = madlib.indexOf(delimiter);
                if (nextDelimiter != -1) {
                    if (nextDelimiter + 1 == nextSpace) next = nextDelimiter;
                }
            }

            madlibArray.add(madlib.substring(0, next));
            System.out.println(madlibArray);
            madlib = madlib.substring(next);

            if (madlib.length() == 0) {
                break;
            }
        }


        return madlibArray;
    }
}
