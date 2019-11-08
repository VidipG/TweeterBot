import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FactGeneration {

  public static void getFacts() {
    try {
      //URL of the API from where random facts are generates.
      URL url = new URL("https://uselessfacts.jsph.pl/random.txt?language=en");

      //Initializes a connection to the URL.
      HttpURLConnection APIConnection = (HttpURLConnection) url.openConnection();

      //Type of HTTPRequest being made to the URL.
      APIConnection.setRequestMethod("GET");
      BufferedReader in = new BufferedReader(
          new InputStreamReader(APIConnection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();

      //Builds the string that is tweeted.
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }

      //Cleans up the formatting of the string to be tweeted.
      content.replace(0, 2, "");

      //Creates a BufferedWriter with the source at the filepath- fileName.
      BufferedWriter writer = new BufferedWriter(
          new FileWriter(TwitterAutoBot.fileName, true));
      PrintWriter out = new PrintWriter(writer);

      //Writes the string to the File.
      out.write(content.toString());
      out.write('\n');
      out.close();
      in.close();
      APIConnection.disconnect();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}