import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FactGeneration {

  public static void getFacts() {
    try {
      URL url = new URL("https://uselessfacts.jsph.pl/random.txt?language=en");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
//      JSONObject obj = new JSONObject(in);
//      String content = obj.getString("data");
      StringBuilder content = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
//        content += inputLine;
      }

      System.out.println(content);
      String fileName = "/Users/Vidip/Everything/CS/Java/Intellij/TwitterBot1/src/main/resources/tweets.txt";
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
      writer.append(content);
      writer.append('\n');
      writer.close();
      in.close();
      con.disconnect();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
