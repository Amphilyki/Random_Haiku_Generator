import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class TheHaiku {

    String lastWordString = "";
    public void setLastWordString(String newlastWordOfLine ) {
        lastWordString = newlastWordOfLine;
    }

    JSONObject lastWordObject = null;
    public void setLastWordObject(JSONObject word) {
        lastWordObject =word;
    }

    boolean lastWordChanged = true;
    public void  setLastWordChanged(boolean changed){
        lastWordChanged=changed;
    }

    String [] notLastWords = {"which", "who", "whom", "whose", "although", "what", "where", "why",
            "when", "how", "whereas", "because", "as", "furthermore", "additionally", "moreover",
            "but", "although", "while", "on", "in", "at", "since", "for", "before", "to", "till",
            "until", "by", "towards", "from", "unto", "across", "about", "of", "off", "into", "through",
            "above", "over", "below", "under", "past", "with", "the", "a", "an", "and", "so", "or", "I", "he",
            "she", "we", "they", "your", "my", "his", "her", "its", "your", "our", "their"};

    public String composeALine (int allowedNumber, JSONObject word, boolean firstLine) throws IOException {
        String line= "";
        int currentLineSyllables = 0;

        if (firstLine) {
            int numberOfSyllables = word.getInt("numSyllables");
            currentLineSyllables += numberOfSyllables;
            setLastWordString(word.getString("word")) ;
            line += lastWordString;
        }
        RelatedWords relatedWords = new RelatedWords();
        JSONArray nextWords= null;
        JSONObject nextWord;
        int wordSyllables = 0;

        while (currentLineSyllables < allowedNumber) {

            if (lastWordChanged ) {
                   nextWords = relatedWords.getRelatedWords(lastWordString);
            }

            nextWord = relatedWords.pickRandomRelatedWord(nextWords);
            System.out.println(nextWord);
            wordSyllables = relatedWords.checkNumberOfSyllables(nextWord);

            if (wordSyllables + currentLineSyllables <= allowedNumber) {
                if (checkLastWord(allowedNumber, currentLineSyllables, wordSyllables, nextWord)) continue;
                setLastWordString(nextWord.getString("word"));
                line += " " + lastWordString;
                currentLineSyllables += wordSyllables;
                setLastWordObject(nextWord);
                setLastWordChanged(true);
            } else setLastWordChanged(false);

        }
            return line;

    }

    private boolean checkLastWord(int allowedNumber, int currentLineSyllables, int wordSyllables, JSONObject pickedword) {
        if (currentLineSyllables + wordSyllables == allowedNumber) {
            for (String notLastWord : notLastWords) {
                if (pickedword.getString("word").equals(notLastWord)) {
                    System.out.println("Last word was not fitting.");
                    setLastWordChanged(false);
                    return true;
                }
            }
        }
        return false;
    }


}
