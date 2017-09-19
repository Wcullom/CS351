import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Boggle extends Application {

  private Stage window;
  private ArrayList<String> words;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Boggle");

    startScene();

  }

  private void startScene() {
    StackPane startLayout = new StackPane();
    startLayout.setAlignment(Pos.CENTER);

    Button startButton = new Button("New Game");
    startButton.setPrefWidth(150);
    startButton.setOnAction(e -> gameScene());

    startLayout.getChildren().add(startButton);

    Scene startScene = new Scene(startLayout, 750, 500);

    window.setScene(startScene);
    window.show();
  }

  private void gameScene() {
    mainController();
  }
  public void mainController()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("What word are you checking the dictionary for?");
    String playword = sc.next();
    words = new ArrayList<>();
    String filename = "Dictionary.txt";
    String line = null;
    try
    {
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null)
      {
        words.add(line);
      }
      bufferedReader.close();
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file " + filename + " ");
    }
    catch (IOException ex)
    {
      System.out.println("Error reading file " + filename + " ");
    }

    if(words.contains(playword))
    {
      System.out.println("The word '" + playword + "' does exist in the dictionary");

    }
    else
    {
      System.out.println("The word '" + playword + "' is not a word in the dictionary");
    }
  }
}