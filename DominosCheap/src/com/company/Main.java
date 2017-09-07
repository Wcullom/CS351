package com.company;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application
{
    ArrayList<Domino> playhand = new ArrayList<Domino>();
    ArrayList<Domino> comphand = new ArrayList<Domino>();
    ArrayList<Domino> boneyard = new ArrayList<Domino>();
    public static void main(String[] args)
    {
        launch(args);
    }

   public  void fillBoneyard()
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
       for(int i = 0; i <= 6; i++)
       {
           int index = rand.nextInt(boneyard.size());
           Domino tmp = boneyard.get(index);
           System.out.println(tmp.getS1() + "  " + tmp.getS2());
           hand.add(tmp);
           boneyard.remove(index);
       }
   }



    public void startingTiles()
    {
        fillhands(playhand);
        fillhands(comphand);
        System.out.println(boneyard.size());
        System.out.println(playhand.size());
        System.out.println(comphand.size());
    }


    @Override
    public void start(Stage window) {
        window.setTitle("Dominos!!!!");
        Button btn = new Button();
        btn.setText("Play Dominos");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Dominos coming soon to a terminal near you");
            }
        });
        fillBoneyard();
        startingTiles();
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        window.setScene(new Scene(root, 700, 700));
        window.show();
    }
}

