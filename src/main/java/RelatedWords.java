import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RelatedWords {

    final String urlString="https://api.datamuse.com/words?rel_bga=";


    public JSONArray getRelatedWords(String start) throws IOException{
        URL url = new URL(urlString+start+"&md=s");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONArray result= new JSONArray(content.toString());
        System.out.println("getRelatedWords run");
        Main.numberOfAPIcallsPlusOne();
        return result;
    }

    public JSONObject pickRandomRelatedWord(JSONArray wordsFollowing) {
        if (wordsFollowing.length()==0) {
            System.out.println("Empty JSONArray");
        }
        int min = 0;
        int max = wordsFollowing.length() - 1;
        int r = (int) (Math.random() * (max - min)) + min;
        JSONObject randomWord = wordsFollowing.getJSONObject(r);
        System.out.println("Number of Word: " + r);
        while (!stringContainsOnlyLetters(randomWord.get("word").toString())){
            System.out.println("Word did not contain only letters");
            r = (int) (Math.random() * (max - min)) + min;
            randomWord = wordsFollowing.getJSONObject(r);
        }
        return randomWord;
    }

    public int checkNumberOfSyllables(JSONObject word){
        return word.getInt("numSyllables");
    }

    public  boolean stringContainsOnlyLetters(String word){
        return word.matches("[a-zA-Z]+");
    }
}
