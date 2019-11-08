import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;

public class TwitterAutoBot {

  //Path to file that contains tweets
  public static String fileName =
      "/Users/Vidip/Everything/CS/Java/Intellij/TwitterBot1/src/main/resources/tweets.txt";

  //Calls static method tweetLines().
  public static void main(String[] args) {
    tweetLines();
  }

  //Reads a locally saved text file, and tweets them.
  private static void tweetLines() {

    //Text that is going to be tweeted.
    String tweet;

    //Count of tweets tweeted in this session.
    int count = 0;

    while (count < 10) {
      try {
        try (InputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("Cp1252"));
            BufferedReader br = new BufferedReader(isr)) {

          //Calls static method GetFacts from the FactGeneration class.
          FactGeneration.getFacts();
          tweet = br.readLine();
          while (tweet != null) {

            //sendTweet accesses the TwitterAPI and posts the tweet.
            sendTweet(tweet);
            System.out.println("tweeting:" + tweet);
            try {
              //Pauses the thread for the given amount of time.
              int sleepTime = 18000; //in milliseconds.
              System.out.printf("Sleeping for %d seconds", sleepTime / 1000);
              Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
            //moves to the next line in the file.
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

  //Calls the TwitterAPI and posts the given string as a tweet.
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