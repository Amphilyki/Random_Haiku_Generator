import org.json.JSONArray;
import org.json.JSONObject;


public class WordClusterByNumberOfSyllables {

    WordClusterByNumberOfSyllables(JSONArray words) {
        JSONArray oneSyllable = new JSONArray();
        JSONArray twoSyllables = new JSONArray();
        JSONArray threeSyllables = new JSONArray();
        JSONArray fourSyllables = new JSONArray();

        for (int i = 0; i < words.length(); i++) {
            JSONObject word = words.getJSONObject(i);
            int numberOfSyllables = word.getInt("numSyllables");
            if (numberOfSyllables==1){ oneSyllable.put(word);}
            if (numberOfSyllables==2){ twoSyllables.put(word);}
            if (numberOfSyllables==3){ threeSyllables.put(word);}
            if (numberOfSyllables==4){ fourSyllables.put(word);}

        }
        System.out.println(oneSyllable.toString());
        System.out.println(twoSyllables.toString());
        System.out.println(threeSyllables.toString());
        System.out.println(fourSyllables.toString());

    }
}
