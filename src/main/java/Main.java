import org.json.JSONObject;

import java.io.IOException;

public class Main {
    static int numberOfAPIcalls = 0;
    public static void numberOfAPIcallsPlusOne(){
        numberOfAPIcalls++;
    }
    public static  void main (String ...args) throws IOException {
    FirstWord firstWord = new FirstWord();
    JSONObject firstWordasJson = firstWord.getFirstWordMetaData();
    TheHaiku theHaiku = new TheHaiku();
    String firstLine = theHaiku.composeALine(5, firstWordasJson, true);
    String secondLine = theHaiku.composeALine(7, theHaiku.lastWordObject, false);
    String thirdLine = theHaiku.composeALine(5, theHaiku.lastWordObject, false);
    System.out.println(firstLine);
    System.out.println(secondLine);
    System.out.println(thirdLine);
    System.out.println("number Of API calls :" + numberOfAPIcalls);

    }
}
