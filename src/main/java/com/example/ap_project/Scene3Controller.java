package com.example.ap_project;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.*;
import java. util. Random;
import java.io.File;

public class Scene3Controller{

    List<Snake> snake_arr = new ArrayList<>();
    List<Ladder> ladder_arr = new ArrayList<>();

    @FXML Label player1,player2,game_status,label_final;
    @FXML private Button diceButton,button_final;
    @FXML private ImageView dice, Board, p1, p2,img_final,snakeladderimg;
    @FXML private Stage stage2;
    @FXML private Parent root2;
    @FXML private Scene scene2;
    @FXML private Label pturn;
    private int player_turn = 2;
    private static int dice_num;
    private String  direction_p1 = "right";
    private String  direction_p2 = "right";
    Player user1 = new Player(-1,-1,0);
    Player user2 = new Player(-1,-1,0);


    public void display(String a, String b){
        player1.setText(a);
        player2.setText(b);
        pturn.setText(player1.getText()+"'s turn");
    }
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root2 = loader.load();
        stage2 = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene2 = new Scene(root2);
        stage2.setScene(scene2);
        stage2.show();
    }

    public void move(int turn, int num){
        if(turn==1){
            if(user1.pos_board==0){
                if(num==1){
                    user1.pos_board =1;
                    user1.x_pos = 0;
                    user1.y_pos = -50;
                    p1.setTranslateY(user1.y_pos);
                    p1.setTranslateX(user1.x_pos);
                    direction_p1 ="right";
                }
            }else{
                if(user1.pos_board + num >100){
                    return;
                }
                Thread thread1 = new Thread(){
                    public void run(){
                        try {

                            for(int i=0;i<num;i++){
                                if(user1.x_pos==450) {
                                    if(direction_p1.equals("upleft")){
                                        user1.y_pos -= 50;
                                        direction_p1 = "left";
                                    }else if(direction_p1.equals("left")){
                                        user1.x_pos -= 50;
                                        direction_p1 ="left";
                                    }
                                }else if(user1.x_pos == 0 && user1.pos_board!=1){
                                    if(direction_p1.equals("upright")){
                                        user1.y_pos -= 50;
                                        direction_p1 = "right";
                                    }else if(direction_p1.equals("right")){
                                        user1.x_pos += 50;
                                        direction_p1 ="right";
                                    }
                                }else{
                                    if(direction_p1.equals("right")){
                                        user1.x_pos+=50;
                                        if(user1.x_pos==450){
                                            direction_p1 = "upleft";
                                        }else{
                                            direction_p1="right";
                                        }
                                    }else if(direction_p1.equals("left")){
                                        user1.x_pos -= 50;
                                        if(user1.x_pos==0){
                                            direction_p1 = "upright";
                                        }else{
                                            direction_p1="left";
                                        }
                                    }
                                }
                                TranslateTransition ani = new TranslateTransition(Duration.millis(300),p1);
                                ani.setToX(user1.x_pos);
                                ani.setToY(user1.y_pos);
                                ani.setAutoReverse(false);
                                ani.play();
                                user1.pos_board+=1;
                                Thread.sleep(300);
                            }

                            for(Snake item: snake_arr){
                                if(item.init_pos == user1.pos_board){
                                    user1.x_pos = item.finX+50;
                                    user1.y_pos = item.finY;
                                    user1.pos_board = item.fin_pos;
                                    direction_p1 = item.dir;
                                    TranslateTransition animation = new TranslateTransition(Duration.millis(400),p1);
                                    animation.setToX(user1.x_pos);
                                    animation.setToY(user1.y_pos);
                                    animation.setAutoReverse(false);
                                    animation.play();
                                    Thread.sleep(400);
                                    break;
                                }
                            }
                            for(Ladder item: ladder_arr){
                                if(item.init_pos == user1.pos_board){
                                    user1.x_pos = item.finX;
                                    user1.y_pos = item.finY;
                                    user1.pos_board = item.fin_pos;
                                    direction_p1 = item.dir;
                                    TranslateTransition animation = new TranslateTransition(Duration.millis(400),p1);
                                    animation.setToX(user1.x_pos);
                                    animation.setToY(user1.y_pos);
                                    animation.setAutoReverse(false);
                                    animation.play();
                                    Thread.sleep(400);
                                    break;
                                }
                            }
                            Platform.runLater( () ->{
                                if(user1.pos_board == 100){
                                    game_status.setText(player1.getText()+" has won the game!!");
                                    diceButton.setDisable(true);
                                    snakeladderimg.setOpacity(0.55);
                                    label_final.setOpacity(1);
                                    img_final.setOpacity(1);
                                    button_final.setOnAction(event -> {
                                        try {
                                            back(event);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                thread1.start();
            }
        }
        else{
            if(user2.pos_board==0){
                if(num==1){
                    user2.pos_board =1;
                    user2.x_pos = -50;
                    user2.y_pos = -50;
                    p2.setTranslateY(user2.y_pos);
                    p2.setTranslateX(user2.x_pos);
                    direction_p2 = "right";
                }
            }else{
                if(user2.pos_board + num >100){
                    return;
                }
                Thread thread2 = new Thread(() -> {
                    try {
                        for(int i=0;i<num;i++){
                            if(user2.x_pos==400) {
                                if(direction_p2.equals("upleft")){
                                    user2.y_pos -= 50;
                                    direction_p2 = "left";
                                }else if(direction_p2.equals("left")){
                                    user2.x_pos -= 50;
                                    direction_p2 ="left";
                                }
                            }else if(user2.x_pos == -50 && user2.pos_board!=1){
                                if(direction_p2.equals("upright")){
                                    user2.y_pos -= 50;
                                    direction_p2 = "right";
                                }else if(direction_p2.equals("right")){
                                    user2.x_pos += 50;
                                    direction_p2 ="right";
                                }
                            }else{
                                if(direction_p2.equals("right")){
                                    user2.x_pos+=50;
                                    if(user2.x_pos==400){
                                        direction_p2 = "upleft";
                                    }else{
                                        direction_p2="right";
                                    }
                                }else if(direction_p2.equals("left")){
                                    user2.x_pos -= 50;
                                    if(user2.x_pos==-50){
                                        direction_p2 = "upright";
                                    }else{
                                        direction_p2="left";
                                    }
                                }
                            }
                            TranslateTransition animate = new TranslateTransition(Duration.millis(300),p2);
                            animate.setToX(user2.x_pos);
                            animate.setToY(user2.y_pos);
                            animate.setAutoReverse(false);
                            animate.play();
                            user2.pos_board+=1;
                            Thread.sleep(300);
                        }
                        for(Snake item: snake_arr){
                            if(item.init_pos == user2.pos_board){
                                user2.x_pos = item.finX;
                                user2.y_pos = item.finY;
                                user2.pos_board = item.fin_pos;
                                direction_p2 = item.dir;
                                TranslateTransition animation = new TranslateTransition(Duration.millis(400),p2);
                                animation.setToX(user2.x_pos);
                                animation.setToY(user2.y_pos);
                                animation.setAutoReverse(false);
                                animation.play();
                                Thread.sleep(400);
                                break;
                            }
                        }

                        for(Ladder item: ladder_arr){
                            if(item.init_pos == user2.pos_board){
                                user2.x_pos = item.finX -50;
                                user2.y_pos = item.finY;
                                user2.pos_board = item.fin_pos;
                                direction_p2 = item.dir;
                                TranslateTransition animation = new TranslateTransition(Duration.millis(400),p2);
                                animation.setToX(user2.x_pos);
                                animation.setToY(user2.y_pos);
                                animation.setAutoReverse(false);
                                animation.play();
                                Thread.sleep(400);
                                break;
                            }
                        }
                        Platform.runLater( () ->{
                            if(user2.pos_board == 100){
                                game_status.setText(player2.getText()+" has won the game!!");
                                diceButton.setDisable(true);
                                snakeladderimg.setOpacity(0.55);
                                label_final.setOpacity(1);
                                img_final.setOpacity(1);
                                button_final.setOnAction(event -> {
                                    try {
                                        back(event);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                thread2.start();
            }
        }
    }

    public void roll(ActionEvent event) throws InterruptedException {
        diceButton.setDisable(false);
        Thread thread = new Thread(){
            public void run(){
                try{
                    Random temp = new Random();
                    int k=0;
//                    RotateTransition obj = new RotateTransition();
//                    obj.setNode(dice);
                    while(k<17){
                        k++;
                        dice_num = 1+temp.nextInt(6);
//                        obj.setByAngle(90);
//                        obj.play();
                        File file = new File("src/main/resources/com/example/ap_project/Dice/dice"+dice_num+".png");
                        Image img = new Image(file.toURI().toString());
                        dice.setImage(img);
                        Thread.sleep(75);
                    }
                    diceButton.setDisable(true);
                    move(player_turn,dice_num);
                    Platform.runLater( () ->{
                        if(player_turn==1){
                            pturn.setText(player2.getText()+"'s turn");
                        }else{
                            pturn.setText(player1.getText()+"'s turn");
                        }
                    });
                    diceButton.setDisable(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        if(player_turn==1){
            player_turn=2;
        }else{
            player_turn=1;
        }
    }

    public void addSnakeLadder(){
        Ladder l1 = new Ladder(5,35,250,-200,"left");
        Ladder l2 = new Ladder(9,51,450,-300,"left");
        Ladder l3 = new Ladder(23,42,50,-250,"right");
        Ladder l4 = new Ladder(48,86,250,-450,"right");
        Ladder l5 = new Ladder(62,83,100,-450,"right");
        Ladder l6 = new Ladder(69,91,450,-500,"left");

        ladder_arr.add(l1);
        ladder_arr.add(l2);
        ladder_arr.add(l3);
        ladder_arr.add(l4);
        ladder_arr.add(l5);
        ladder_arr.add(l6);


        Snake s1 = new Snake(36,5,150,-50,"right");
        Snake s2 = new Snake(49,7,250,-50,"right");
        Snake s3 = new Snake(56,8,300,-50,"right");
        Snake s4 = new Snake(82,20,-50,-100,"upright");
        Snake s5 = new Snake(87,66,200,-350,"right");
        Snake s6 = new Snake(95,38,50,-200,"left");

        snake_arr.add(s1);
        snake_arr.add(s2);
        snake_arr.add(s3);
        snake_arr.add(s4);
        snake_arr.add(s5);
        snake_arr.add(s6);
    }
}