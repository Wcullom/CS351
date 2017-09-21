import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Boggle extends Application {


  private Stage window;
  private String playword;
  private ArrayList<String> words;
  private TextField wordSearch;

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

    HBox searchBox = new HBox();
    wordSearch = new TextField();
    Button submit = new Button("Submit");

    wordSearch.setPromptText("Enter Word to Search For");
    submit.setOnAction(e -> checkWord());
    words = new ArrayList<>();

    String[][] board = new String[traySize][traySize];
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

    for (int i = 0; i < traySize; i++){
      for (int j = 0; j < traySize; j++) {
        String letter = Character.toString((char)(65+i+j)); //to fill the buttons with a letter
        ToggleButton button = new ToggleButton(letter);
        button.setPrefSize(45,45);
        button.setFont(Font.font(20));
        gameTray.add(button,i,j);
      }
    }

    searchBox.getChildren().addAll(wordSearch,submit);
    searchBox.setAlignment(Pos.CENTER);
    rootPane.setBottom(searchBox);

    gameTray.setAlignment(Pos.CENTER);
    rootPane.setCenter(gameTray);

    gameScene = new Scene(rootPane, 800, 800);
    window.setScene(gameScene);
    window.show();
  }

  private void checkWord() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Checked the Word");
    alert.setHeaderText(null);

    ButtonType buttonTypeOne = new ButtonType("OK");
    playword = wordSearch.getText();

    if(words.contains(playword))
    {
      alert.setContentText("The word '" + playword + "' does exist in the dictionary");

    }
    else
    {
      alert.setContentText("The word '" + playword + "' does NOT exist in the dictionary");
    }

    alert.getButtonTypes().setAll(buttonTypeOne);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == buttonTypeOne) {
      alert.close();
    }
  }
}
