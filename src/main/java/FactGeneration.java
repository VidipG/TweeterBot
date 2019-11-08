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
      StringBuilder content = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      content.replace(0, 2, "");

      System.out.println(content);
      String fileName = "/Users/Vidip/Everything/CS/Java/Intellij/TwitterBot1/src/main/resources/tweets.txt";
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
      PrintWriter out = new PrintWriter(writer);
      out.write(content.toString());
      out.write('\n');
      out.close();
      in.close();
      con.disconnect();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
