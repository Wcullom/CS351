import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Boggle extends Application {

  private Stage window;
  char chararray[][] = new char[4][4];
  char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Boggle");

    startScene();

  }

  private void populate_array4()
  { 
    for(int i = 0; i < 4; i++)
    {
       for(int j = 0; j < 4; j++)
       {
        Random rand = new Random();
        int x = rand.nextInt(alphabet.size());
        



       }
    }

  } 

  private void startScene() {
    FlowPane startLayout = new FlowPane();
    startLayout.setAlignment(Pos.CENTER);

    Button Four_Button = new Button("4x4 Tray");

    Button Five_Button = new Button("5x5 Tray");
    Four_Button.setPrefWidth(150);
    Four_Button.setOnAction(e ->
            mainController(4));

    Five_Button.setPrefWidth(150);
    Five_Button.setOnAction(e ->
            mainController(5));

    startLayout.getChildren().addAll(Five_Button,Four_Button);

    Scene startScene = new Scene(startLayout, 750, 500);

    window.setScene(startScene);
    window.show();
  }

  private void mainController(int traySize) {
    Scene gameScene;
    BorderPane rootPane = new BorderPane();
    GridPane gameTray = new GridPane();

    //Scanner sc = new Scanner(System.in);
    //System.out.println("What word are you checking the dictionary for?");
    //String playword = sc.next();
    ArrayList<String> words = new ArrayList<>();
    String filename = "Dictionary.txt";
    String line;
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
    /*
    if(words.contains(playword))
    {
      System.out.println("The word '" + playword + "' does exist in the dictionary");

    }
    else
    {
      System.out.println("The word '" + playword + "' is not a word in the dictionary");
    }
    */

    for (int i = 0; i < traySize; i++){
      for (int j = 0; j < traySize; j++) {
        String letter = Character.toString((char)(65+i+j)); //to fill the buttons with a letter
        ToggleButton button = new ToggleButton(letter);
        button.setPrefSize(45,45);
        button.setFont(Font.font(20));
        gameTray.add(button,i,j);
      }
    }

    gameTray.setAlignment(Pos.CENTER);
    rootPane.setCenter(gameTray);
    gameScene = new Scene(rootPane, 800, 800);
    window.setScene(gameScene);
    window.show();
  }
}
