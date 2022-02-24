package haiku;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/haiku")
public class Main {

    static int numberOfAPIcalls = 0;

    public static void numberOfAPIcallsPlusOne() {
        numberOfAPIcalls++;
    }

    @GetMapping(produces = "application/json")
    public String createHaiku(@RequestParam(defaultValue = "Blue") String firstWord) throws IOException {
        FirstWord start = new FirstWord();
        JSONObject firstWordasJson = start.getFirstWordMetaData(firstWord);
        TheHaiku theHaiku = new TheHaiku();
        String firstLine = theHaiku.composeALine(5, firstWordasJson, true);
        String secondLine = theHaiku.composeALine(7, theHaiku.lastWordObject, false);
        String thirdLine = theHaiku.composeALine(5, theHaiku.lastWordObject, false);
        System.out.println(firstLine);
        System.out.println(secondLine);
        System.out.println(thirdLine);
        System.out.println("number Of API calls :" + numberOfAPIcalls);
        return firstLine + " / " + secondLine + " / " +  thirdLine;
    }

}
