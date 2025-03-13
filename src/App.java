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

    /**ArrayList Containing Verbs -- Red */
    private final ArrayList<String> verbs;
    
    /**ArrayList Containing Nouns -- Yellow*/
    private final ArrayList<String> nouns;

    /**ArrayList Containing Adjectives -- Blue*/
    private final ArrayList<String> adjectives;

    /**Delimiters */

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.printWithColor();
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
        return "In the town of Techville, a group of friends—Maya, Leo, and Jay—were known as The Code Squad. They loved coding and solving problems using their computers.\n" + //
                        "\n" + //
                        "One afternoon, while they were in the school's coding club, the lights flickered. Then—BOOM!—the entire town lost power!\n" + //
                        "\n" + //
                        "The teacher checked her phone. \"It looks like a virus has shut down the city's power grid,\" she said. \"Without electricity, hospitals, traffic lights, and even the water pumps won't work!\"\n" + //
                        "\n" + //
                        "Maya's eyes widened. \"We have to do something!\"\n" + //
                        "\n" + //
                        "Leo pulled out his laptop. \"If the virus is in the power grid, we might be able to write a program to track it down.\"\n" + //
                        "\n" + //
                        "Jay grinned. \"And if we find it, we can create an antivirus to stop it!\"\n" + //
                        "\n" + //
                        "The Code Squad got to work. They connected to the emergency backup server at the school and used their programming skills to scan the system.\n" + //
                        "\n" + //
                        "\"There it is!\" Maya pointed at the screen. \"The virus is hiding in the main control code.\"\n" + //
                        "\n" + //
                        "Leo typed fast. \"I'm writing a program to isolate the virus.\"\n" + //
                        "\n" + //
                        "Jay added, \"And I'll program a firewall to keep it from coming back!\"\n" + //
                        "\n" + //
                        "After a few tense minutes, they uploaded their code.\n" + //
                        "\n" + //
                        "BEEP! BEEP! BEEP!\n" + //
                        "\n" + //
                        "The system flickered to life. Streetlights turned on. The fans in the school whirred. The town's power was back!\n" + //
                        "\n" + //
                        "The mayor called to thank them. \"You kids saved Techville!\"\n" + //
                        "\n" + //
                        "Maya, Leo, and Jay high-fived. \"Just another mission for The Code Squad!\"\n" + //
                        "\n" + //
                        "And from that day on, they knew that with computer science, they could solve any problem—even saving the world.";
    }

    public ArrayList<String> getMadLibArrayList() {
        String madLib = getMadLib();
        ArrayList<String> madLibArray = new ArrayList<String>();
    
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < madLib.length(); i++) {
            char currentChar = madLib.charAt(i);
    
            // Check if the character is a letter or number (part of a word)
            if (!Character.isWhitespace(currentChar)) {
                currentWord.append(currentChar);  // Add letter to the current word
            } else {
                // If it's a punctuation mark, we add it as a separate item
                if (!Character.isWhitespace(currentChar)) {
                    currentWord.append(currentChar);
                }

                // If we find a space or punctuation mark, we treat it as a delimiter
                if (currentWord.length() > 0) {
                    madLibArray.add(currentWord.toString());  // Add the word to the list
                    currentWord.setLength(0);  // Clear the current word
                }
            }
        }
    
        // Add any remaining word at the end (if the madlib doesn't end with punctuation)
        if (currentWord.length() > 0) {
            madLibArray.add(currentWord.toString());
        }
    
        return madLibArray;
    }

    public void printWithColor() {
        var madLib = getMadLibArrayList();

        for (int i = 0; i < madLib.size(); i++) {
            var word = madLib.get(i);
            
            String wordNoPunctuation = "";

            for (int j = 0; j < word.length(); j++) {
                String edDetect = "";

                if (word.length() - j > 2) {
                    edDetect += word.charAt(j);
                    edDetect += word.charAt(j+1);
                }

                if (Character.isLetterOrDigit(word.charAt(j)) && edDetect != "ed") {
                    wordNoPunctuation += word.charAt(j);
                } else {
                    break;
                }
            }

            boolean found = false;
            for (var noun : nouns) {
                if (wordNoPunctuation.toLowerCase().equals(noun) && !found) {
                    System.out.print(ANSI_YELLOW + word);
                    found = true;
                }
            }
            for (var verb : verbs) {
                if (wordNoPunctuation.toLowerCase().equals(verb) && !found) {
                    System.out.print(ANSI_RED + word);
                    found = true;
                }
            }
            for (var adjective : adjectives) {
                if (wordNoPunctuation.toLowerCase().equals(adjective) && !found) {
                    System.out.print(ANSI_BLUE + word);
                    found = true;
                }
            }
            if (!found) System.out.print(ANSI_GREEN + word);

            System.out.print(" ");
        }

        System.out.println();
    }
}
