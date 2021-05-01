import org.json.JSONArray;
import org.json.JSONObject;

public class PartsOfSpeech {
    PartsOfSpeech(JSONArray words) {
        JSONArray nouns = new JSONArray();
        JSONArray adjectives = new JSONArray();
        JSONArray verbs = new JSONArray();

        for (int i = 0; i < words.length(); i++) {
            JSONObject word = words.getJSONObject(i);
            JSONArray whatPartIsit = word.getJSONArray("tags");
            for (int j = 0; j <whatPartIsit.length(); j++) {
                if (whatPartIsit.getString(j).equals("n")) {
                    nouns.put(word);
                }
                if (whatPartIsit.getString(j).equals("v")) {
                    verbs.put(word);
                }
                if (whatPartIsit.getString(j).equals("adj")) {
                    adjectives.put(word);
                }
            }
        }
        System.out.println(nouns.toString());
        System.out.println(adjectives.toString());
        System.out.println(verbs.toString());

    }
}
