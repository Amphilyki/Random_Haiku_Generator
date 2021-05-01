import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FirstWord {

    final String urlString="https://api.datamuse.com/words?sl=";
    String startingWord = "";

    FirstWord () {
        startingWord= "tyropita";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the word you want the haiku to start with:");
        startingWord= scanner.nextLine();
        while (!stringContainsOnlyLetters(startingWord)){
            System.out.println("Please input a valid word you want the haiku to start with:");
            startingWord= scanner.nextLine();
        }
    }

    public JSONObject getFirstWordMetaData() throws IOException {
        String start = startingWord;
        URL url = new URL(urlString+ start +"&md=s");
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
        Main.numberOfAPIcallsPlusOne();
        System.out.println("getFirstWordMetaData run" + result);
        if (!result.isEmpty()) {
            JSONObject wordObject = result.getJSONObject(0);
            if (stringContainsOnlyLetters(wordObject.get("word").toString())) {
                return result.getJSONObject(0);
            }
        }
        else new FirstWord();
        return null;
    }
    public  boolean stringContainsOnlyLetters(String word){
        return word.matches("[a-zA-Z]+");
    }
}
