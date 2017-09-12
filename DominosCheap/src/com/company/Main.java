package com.company;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application
{
    int handCount = 1;
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();



    ArrayList<Domino> playhand = new ArrayList<Domino>();
    ArrayList<Domino> comphand = new ArrayList<Domino>();
    ArrayList<Domino> boneyard = new ArrayList<Domino>();
    public static void main(String[] args)
    {

        launch(args);
    }


   private  void fillBoneyard()
   {
    int l = 0;
    Domino tmp;
    for(int i=0; i <= 6; i++)
    {
        for (int j = 6; j >= l; j--)
        {
            tmp = new Domino(i,j);
            boneyard.add(tmp);

        }
        l++;
    }
  }

   public void fillhands(ArrayList hand)
   {
       Random rand = new Random();
       for (int i = 0; i <= 6; i++) {
           int index = rand.nextInt(boneyard.size());
           Domino tmp = boneyard.get(index);
           boneyard.remove(index);
       }
   }
   public boolean checkTiles(Domino d1, Domino d2)
   {
       if(d1.getS1() == d2.getS1() || d1.getS1() == d2.getS2() || d1.getS2() == d2.getS2() || d1.getS2() == d2.getS1())
       {
           return true;
       }
       else
           return false;

   }
    public void startingTiles()
    {
        fillhands(playhand);
        fillhands(comphand);
     }
    @Override
    public void start(Stage primaryStage)
    {
        HBox buttonBox = new HBox();
        VBox handBox   = new VBox();
        fillBoneyard();
        startingTiles();
        primaryStage.setTitle("Dominos!!!!");
        screen = Screen.getPrimary();
        bounds = screen.getVisualBounds();
        StackPane menuRoot = new StackPane();

        Button startgame = new Button();
        startgame.setText("Play Game");
        startgame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println(boneyard.size());
                System.out.println(playhand.size());
            }
        });

        Button drawdom = new Button();
        drawdom.setText("Draw");
        drawdom.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Random rand = new Random();
                int index = rand.nextInt(boneyard.size());
                Domino tmp = boneyard.get(index);
                playhand.add(tmp);
                boneyard.remove(tmp);
                System.out.println(playhand.size());
            }

        });

        Button[] barray = new Button[28];
        for(int i =0; i < playhand.size(); i++)
        {
            DomButton tmp = new DomButton(playhand.get(i));
            tmp.setText("help");
            handBox.getChildren().addAll(tmp);

        }

        Button test = new Button();
        test.setText("testing");
        handBox.getChildren().add(test);
        handBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.getChildren().add(drawdom);
        buttonBox.getChildren().add(startgame);
        menuRoot.getChildren().add(buttonBox);
        menuRoot.getChildren().add(handBox);
        Scene gameScene = new Scene (menuRoot,bounds.getWidth(),bounds.getHeight());
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }







private class Hand extends Parent
{
    public Hand()
    {
        HBox hand = new HBox(10);
        hand.setTranslateY(bounds.getHeight()-10);
        hand.setTranslateX(bounds.getWidth()/playhand.size());
        ArrayList<DomButton> barray = new ArrayList<DomButton>();
        DomButton temp;
        for(int i = 0; i < playhand.size(); i++)
        {
            temp = new DomButton(playhand.get(i));
            barray.add(temp);
            getChildren().add(temp);
        }

        System.out.println(barray.size());
    }
}


    public class DomButton extends Button
    {
        private Text text;

        public DomButton(Domino d)
        {
            javafx.scene.shape.Rectangle frm = new Rectangle(100,150);

            frm.setFill(javafx.scene.paint.Color.PURPLE);
            frm.setEffect(new GaussianBlur((3.5)));

            setAlignment(Pos.CENTER);
            getChildren().addAll(frm);

            this.setOnMouseDragEntered(event ->{
                frm.setTranslateY(10);
                frm.setFill(javafx.scene.paint.Color.BLACK);
            });
            this.setOnMouseDragExited(event ->{
                frm.setTranslateY(-10);
                frm.setFill(Color.PURPLE);
            });

            this.setOnMousePressed(event -> {
                //effect
            });

            this.setOnMouseReleased(event -> {
                //set effect to be released
            });
        }
    }
}

