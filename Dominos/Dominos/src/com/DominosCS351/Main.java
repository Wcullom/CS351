package com.DominosCS351;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application
{
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    ArrayList<Domino> playhand = new ArrayList<Domino>();
    ArrayList<Domino> comphand = new ArrayList<Domino>();
    ArrayList<Domino> boneyard = new ArrayList<Domino>();
    ArrayList<Domino> board    = new ArrayList<Domino>();



    public static void main(String[] args)
    {
        launch(args);
    }

   /*   fillBoneyard() doesn't take any parameters. It is simply used to
    *    fill the boneyard.
    *   Method return type is void
    */
    private void fillBoneyard()
    {
        int l = 0;
        Domino tmp;
        for (int i = 0; i <= 6; i++) {
            for (int j = 6; j >= l; j--) {
                tmp = new Domino(i, j);
                boneyard.add(tmp);

            }
            l++;
        }
    }


     /* fillHands takes the input of an array list and
      * it will fill it with 7 tiles for every array list it is passed
      */
     public void fillhands(ArrayList hand)
     {
        Random rand = new Random();
        for (int i = 0; i <= 6; i++)
        {
            int index = rand.nextInt(boneyard.size());
            Domino tmp = boneyard.get(index);
            hand.add(tmp);
            boneyard.remove(index);
        }
    }

  /* Check tiles takes two dominos and returns a true or false if
   * either of the sides of the dominos have a matching side
   */
    public boolean checkTiles(Domino d1, Domino d2)
    {
        if (d1.getS1() == d2.getS1() || d1.getS1() == d2.getS2() || d1.getS2() == d2.getS2() || d1.getS2() == d2.getS1())
        {
            return true;
        } else
            return false;
    }
    /*fills both players hands, as well as the starting board tile. */
    public void startingTiles()
    {
        fillhands(playhand);
        fillhands(comphand);
        Domino tmp = boneyard.get(0);
        board.add(tmp);
        boneyard.remove(tmp);
    }

    /*Simple method that checks to see if the boneyard is empty*/
    public boolean checkEmpty()
    {
        if(boneyard.size() == 0)
        {
            System.out.print("The boneyard is empty, there is no more tiles to take");
            return true;
        }
        else
        {
            return false;
        }

    }


    /*This is where all graphics are done and added to our stackpane*/
    @Override
    public void start(Stage primaryStage)
    {
        /*All the boxes we want to put things in.*/
        HBox buttonBox = new HBox();
        VBox handBox = new VBox();
        handBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox boardv = new VBox();
        boardv.setAlignment(Pos.CENTER);


       /*fill our hands and create pane*/
        fillBoneyard();
        startingTiles();
        primaryStage.setTitle("Dominos CS351");
        screen = Screen.getPrimary();
        bounds = screen.getVisualBounds();
        StackPane menuRoot = new StackPane();
        /*Here we are going to create buttons for each domino in our hand*/
        for (int i = 0; i < playhand.size(); i++)
        {
            Domino tmpd = playhand.get(i);
            Button tmp = new Button();
            tmp.setText(tmpd.getS1() + "  " + tmpd.getS2());
            tmp.setOnMouseEntered(new EventHandler<MouseEvent>()
            {
             @Override
             public void handle(MouseEvent event)
             {
             tmp.setTextFill(Color.CRIMSON);
             }
         });
            tmp.setOnMouseExited(new EventHandler<MouseEvent>()
            {
             @Override
             public void handle(MouseEvent event)
             {
                 tmp.setTextFill(Color.BLACK);
             }
         });
            handBox.getChildren().addAll(tmp);
        }

        Button drawdom = new Button();
        drawdom.setText("Draw");
        drawdom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {

                Random rand = new Random();
                int index = rand.nextInt(boneyard.size());
                Domino drawme = boneyard.get(index);
                playhand.add(drawme);
                boneyard.remove(drawme);

            }
        });

        Domino srt = board.get(0);
        Button startdom = new Button();
        startdom.setText(srt.getS1() +  "   " + srt.getS2());
        boardv.getChildren().add(startdom);
        buttonBox.getChildren().add(drawdom);
        menuRoot.getChildren().add(buttonBox);
        menuRoot.getChildren().add(handBox);
        menuRoot.getChildren().add(boardv);

        /*We check to see if there is anything left in the boneyard,
         * If there is nothing left we are going to remove the draw
         * button and print to the terminal that the boneyard is empty.
         */
        if(checkEmpty() == true)
        {
            buttonBox.getChildren().remove(drawdom);
        }
        Scene gameScene = new Scene(menuRoot, bounds.getWidth(), bounds.getHeight());
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

}
