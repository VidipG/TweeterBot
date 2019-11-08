import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.nio.charset.Charset;

public class TwitterAutoBot {

  public static void main(String[] args) {
    tweetLines();
  }

  private static void tweetLines() {
    String tweet;
    int count = 0;
    while (count < 10) {
      try {
        try (InputStream fis = new FileInputStream(
            "/Users/Vidip/Everything/CS/Java/Intellij/TwitterBot1/src/main/resources/tweets.txt");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("Cp1252"));
            BufferedReader br = new BufferedReader(isr);) {
          FactGeneration.getFacts();
          tweet=br.readLine();
          while (tweet != null) {
            sendTweet(tweet);
            System.out.println(tweet);
            try {
              int sleepTime = 18000;
              System.out.printf("Sleeping for %d seconds", sleepTime / 1000);
              Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
            tweet = br.readLine();
          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      count += 1;
    }
  }

  private static void sendTweet(String line) {
    Twitter twitter = TwitterFactory.getSingleton();
    Status status;
    try {
      status = twitter.updateStatus(line);
      System.out.println(status);
    }
    catch (TwitterException e) {
      e.printStackTrace();
    }
  }
}
