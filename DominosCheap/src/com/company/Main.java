package com.company;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;



public class Main extends Application {



    public static void main(String[] args) {



        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                Domino domino = new Domino(i,j);
                if(!tiles.contains(domino)){
                    tiles.add(domino);



                    
        //Create an array list of tiles
        ArrayList<Domino> playhand = new ArrayList<Domino>();
        ArrayList<Domino> comphand = new ArrayList<Domino>();
        ArrayList<Domino> boneyard = new ArrayList<Domino>();
        Button dombtns[] = new Button[28];
        for (int i = 0; i < 6; i++) {
            for (int j = 6; j > 0; j--) {
                Domino tmp = new Domino(j, i);
                if (boneyard.contains(tmp)) {
                    boneyard.remove(tmp);
                }
                boneyard.add(new Domino(i, j));
                System.out.println(Domino.getS1() + " " + Domino.getS2());
            }
        }
        launch(args);
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


        StackPane root = new StackPane();
        root.getChildren().add(btn);
        window.setScene(new Scene(root, 700, 700));
        window.show();
    }
}

