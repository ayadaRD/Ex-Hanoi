package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {



    @FXML
    private Button pre;

    @FXML
    private Pane pane;

    @FXML
    private MenuButton menu;

    @FXML
    private Canvas can;

    @FXML
    private Button end;

    @FXML
    private Button play_pause;

    @FXML
    private Button restart;

    @FXML
    private Polyline left;

    @FXML
    private Polyline right;

    @FXML
    public Button next;

    @FXML
    public Rectangle StackA;

    @FXML
    public Rectangle StackB;

    @FXML
    public Rectangle StackC;

    @FXML
    private Label choose;

    @FXML
    private TextField count;

    @FXML
    private ProgressIndicator pro;

/////////////////////////////////////////////////////////////////////introducing variables

    //this bool is kind of status for play/pause button
        public static boolean play = false;

    //this three integers are some values for the 3 tower
        private static final int a = 0;     //stack A value
        private static final int b = 1;     //stack B value
        private static final int c = 2;     //stack C value

    //the moves that we need to solve the ex hanoi problem would save in moves arrayList
        ArrayList<HanoiMove> moves = new ArrayList<>();

    //the width and coordinate of 9 disks would save in this array of disk
        ArrayList<Disk> disks = new ArrayList<>();

    //this amount would be chosen by user and shows the number of disks in every stack
        public static int x;

    //the disks in every tower would save in these arraylists
        static ArrayList <Disk> A = new ArrayList<>();
        static ArrayList <Disk> B = new ArrayList<>();
        static ArrayList <Disk> C = new ArrayList<>();

    //this is for UI drawing disk
       GraphicsContext g;

    //this are for the delay between moves
        int time = 0;
        int delay = 1000;
        Timer timer;
    //this count the number of moves while moving
        public static int counter = 0;


//////////////////////////////////////////////////////////////////functions

//this two functions produce moves and save them to an array
    public void hanoi (int A, int B, int C,int n){

        if(n == 1) {
            moves.add(new HanoiMove(findStack(A), findTower(A), findStack(C), findTower(C)));
        }
        else{
            hanoi(A,C,B,n-1);
            moves.add(new HanoiMove(findStack(A),findTower(A),findStack(C),findTower(C)));
            hanoi(B,A,C,n-1);
        }

    }
    public void ExHanoi(int A, int B, int C, int n){

        if(n==1){
            moves.add(new HanoiMove(findStack(C),findTower(C),findStack(B),findTower(B)));
            moves.add(new HanoiMove(findStack(A),findTower(A),findStack(C),findTower(C)));
            moves.add(new HanoiMove(findStack(B),findTower(B),findStack(A),findTower(A)));
            moves.add(new HanoiMove(findStack(B),findTower(B),findStack(C),findTower(C)));
            moves.add(new HanoiMove(findStack(A),findTower(A),findStack(C),findTower(C)));
        }

        else{
            ExHanoi(A,B,C,n-1);
            hanoi(C,A,B,3*n-2);
            moves.add(new HanoiMove(findStack(A),findTower(A),findStack(C),findTower(C)));
            hanoi(B,A,C,3*n-1);
        }
    }
//also this two functions returns true value of stacks
    public ArrayList <Disk> findTower(int s){

        if(s==0)
            return A;
        if(s==1)
            return B;
        return C;
    }

    public Rectangle findStack(int s){

        if(s==0)
            return StackA;
        if(s==1)
            return StackB;
        return StackC;
    }
//--------------------------------------------------------
//this function set the first appearance of problem (based on what user chooses)

    public void drawDisks(){

        prepareDisk();  //determine the width of 9 disks and save it to an arraylist

        g = can.getGraphicsContext2D();    //preparing our pencil
        g.clearRect(0,0,970,740);   //clear every extra staff in the screen

        //determine the coordinate of disks in the first of after restarting
        disks.get(0).setX(StackA.getX()-120);
        disks.get(0).setY(502);

        disks.get(1).setX(StackB.getX()-105);
        disks.get(1).setY(502);

        disks.get(2).setX(StackC.getX()-92);
        disks.get(2).setY(502);

        disks.get(3).setX(StackA.getX()-80);
        disks.get(3).setY(473);

        disks.get(4).setX(StackB.getX()-65);
        disks.get(4).setY(473);

        disks.get(5).setX(StackC.getX()-55);
        disks.get(5).setY(473);

        disks.get(6).setX(StackA.getX()-43);
        disks.get(6).setY(444);

        disks.get(7).setX(StackB.getX()-33);
        disks.get(7).setY(444);

        disks.get(8).setX(StackC.getX()-21);
        disks.get(8).setY(444);

        //g.clearRect(0,0,970,740);

        //adding disks to towers
        A.add(disks.get(0));
        B.add(disks.get(1));
        C.add(disks.get(2));
        A.add(disks.get(3));
        B.add(disks.get(4));
        C.add(disks.get(5));
        A.add(disks.get(6));
        B.add(disks.get(7));
        C.add(disks.get(8));

        //drawing in the scene

        if(x==1){
            A.remove(disks.get(3));
            B.remove(disks.get(4));
            C.remove(disks.get(5));
            A.remove(disks.get(6));
            B.remove(disks.get(7));
            C.remove(disks.get(8));

            for (int i = 0; i < 3; i++) {
                g.setFill(Color.web("#ff009d"));
                g.fillRoundRect(disks.get(i).X,disks.get(i).Y,disks.get(i).width,27,50,50);
            }
        }
        else if(x==2){
            A.remove(disks.get(6));
            B.remove(disks.get(7));
            C.remove(disks.get(8));

            for (int i=0 ; i<6 ; i++) {
                g.setFill(Color.web("#ff009d"));
                g.fillRoundRect(disks.get(i).X,disks.get(i).Y,disks.get(i).width,27,50,50);
            }
        }
        else if(x==3){
            for (int i = 0; i < 9; i++) {
                g.setFill(Color.web("#ff009d"));
                g.fillRoundRect(disks.get(i).X,disks.get(i).Y,disks.get(i).width,27,50,50);
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------
//determine the width of 9 disks
    public void prepareDisk(){
        disks.add(new Disk(250));
        disks.add(new Disk(225));
        disks.add(new Disk(200));
        disks.add(new Disk(175));
        disks.add(new Disk(150));
        disks.add(new Disk(125));
        disks.add(new Disk(100));
        disks.add(new Disk(75));
        disks.add(new Disk(50));
    }
//----------------------------------------------------
//this function moves a disk from first stack to second stack
    public void moveOneDisk(Rectangle first,ArrayList<Disk> from,Rectangle second,ArrayList<Disk> to) throws InterruptedException, CloneNotSupportedException {
        if(nextBtn)
        counter++;  //this is the number of moves
        else
            counter--;

        //last disk in the first stack would copy to an object
        Disk moveDisk = (Disk) from.get(from.size()-1).clone();
        //then removes from first stack
        from.remove(from.size()-1);
        g.clearRect(moveDisk.X, moveDisk.Y, moveDisk.width, 27);

        //now let's move it to second stack
        moveDisk.setX(second.getX()-0.5*moveDisk.width+8);
        moveDisk.setY(-to.size()*27+502-2*to.size());
        g.fillRoundRect( moveDisk.X,moveDisk.Y , moveDisk.width, 27, 50, 50);
        to.add(moveDisk);

        count.setText(String.valueOf(counter));
        pro.setProgress((double)counter/moves.size());
    }
//////////////////////////////////////////////////////////////////////Action events
//when user clicks on play/pause button:
    public void onPlayPauseClick(ActionEvent ac){

        play = !play;

        if(play ) {
            next.setDisable(true);
            pre.setDisable(true);
            restart.setDisable(true);
            end.setDisable(true);
            menu.setDisable(true);
            play_pause.setText("⏸");
            timer = new Timer();
            time = 0;
            delay = 1000;

            nextBtn = true;

            int i = counter;

            while( i < moves.size() ){
                time += delay;
                timer.schedule(new myTask(moves.get(i).first,moves.get(i).fromStack,moves.get(i).second,moves.get(i).toStack),time);
                i++;
            }
        }

        else {
            if(counter == moves.size()) {
                next.setDisable(true);
                play_pause.setDisable(true);
            }
            pre.setDisable(false);
            menu.setDisable(false);
            restart.setDisable(false);
            next.setDisable(false);
            end.setDisable(false);
            play_pause.setText("▶");
            timer.cancel();
        }
    }
//--------------------------------------------------------
//when user clicks on next step button:
    synchronized public void onNextClick(ActionEvent ac) throws InterruptedException, CloneNotSupportedException {
        nextBtn = true;
        moveOneDisk(moves.get(counter).first,moves.get(counter).fromStack,moves.get(counter).second,moves.get(counter).toStack);
        if(counter == moves.size()) {
            next.setDisable(true);
            play_pause.setDisable(true);
        }
        restart.setDisable(false);
                end.setDisable(false);
                menu.setDisable(false);
                pre.setDisable(false);
    }
boolean nextBtn;
    public void onPreClick() throws CloneNotSupportedException, InterruptedException {
        if(counter == 1) {
            pre.setDisable(true);
        }
        restart.setDisable(false);
        end.setDisable(false);
        menu.setDisable(false);
        play_pause.setDisable(false);
        next.setDisable(false);

        nextBtn = false;
        int i =counter-1;
        moveOneDisk(moves.get(i).second,moves.get(i).toStack,moves.get(i).first,moves.get(i).fromStack);


    }
//---------------------------------------------------------
//when user clicks on final result:
    public void onFinalClick(ActionEvent ac){
        play_pause.setDisable(true);
        next.setDisable(true);
        restart.setDisable(false);
        menu.setDisable(false);

        A.clear();
        B.clear();
        C.clear();

        count.setText(String.valueOf(moves.size()));
        pro.setProgress(1.0);

        for (int i = 0; i < 3*x; i++) {
            C.add(disks.get(i));
        }

        //disks.clear();
        //prepareDisk();

        counter = moves.size();
        pro.setProgress(1);

        for(int i = 0 ; i <disks.size() ; i++){
            disks.get(i).setX(StackC.getX()-0.5*disks.get(i).width+8);
            disks.get(i).setY(502-27*i-3*i);
        }

        g.clearRect(0,0,970,740);


        for (int i = 0; i <3*x ; i++) {
            g.fillRoundRect( disks.get(i).X,disks.get(i).Y , disks.get(i).width, 27, 50, 50);
        }

    }
//---------------------------------------------------------
    synchronized public void onRestartClick(ActionEvent ac){
        play_pause.setDisable(false);
        restart.setDisable(true);
        next.setDisable(false);
        menu.setDisable(false);
        pre.setDisable(false);

        count.setText("0");
        pro.setProgress(0);

        moves.clear();
        A.clear();
        B.clear();
        C.clear();
        counter = 0;
        ExHanoi(a,b,c,x);
        drawDisks();
    }

    public void on1Disck(ActionEvent event) {

        choose.setVisible(false);
        right.setVisible(true);
        left.setVisible(true);
        next.setVisible(true);
        pre.setVisible(true);
        end.setVisible(true);
        play_pause.setVisible(true);
        restart.setVisible(true);

        restart.setDisable(false);
        play_pause.setDisable(false);
        next.setDisable(false);
        menu.setDisable(false);
        end.setDisable(false);

        count.setText("0");
        pro.setProgress(0);

        moves.clear();
        A.clear();
        B.clear();
        C.clear();
        counter=0;
        x=1;
        ExHanoi(a,b,c,x);
        menu.setText("1");
        drawDisks();

    }
    public void on2Disck(ActionEvent ac){
        choose.setVisible(false);
        right.setVisible(true);
        left.setVisible(true);
        next.setVisible(true);
        pre.setVisible(true);
        end.setVisible(true);
        play_pause.setVisible(true);
        restart.setVisible(true);

        restart.setDisable(false);
        play_pause.setDisable(false);
        next.setDisable(false);
        menu.setDisable(false);
        end.setDisable(false);

        count.setText("0");
        pro.setProgress(0);

        moves.clear();
        A.clear();
        B.clear();
        C.clear();
        counter = 0;
        menu.setText("2");
        x=2;
        drawDisks();
        ExHanoi(a,b,c,x);
    }

    public void on3Disck(ActionEvent ac){
        choose.setVisible(false);
        right.setVisible(true);
        left.setVisible(true);
        next.setVisible(true);
        pre.setVisible(true);
        end.setVisible(true);
        play_pause.setVisible(true);
        restart.setVisible(true);

        restart.setDisable(false);
        play_pause.setDisable(false);
        next.setDisable(false);
        menu.setDisable(false);
        end.setDisable(false);

        count.setText("0");
        pro.setProgress(0);

        moves.clear();
        A.clear();
        B.clear();
        C.clear();
        counter = 0;
        menu.setText("3");
        x=3;
        ExHanoi(a,b,c,x);
        drawDisks();
    }

    private class myTask extends TimerTask{

        Rectangle one;
        Rectangle two;
        ArrayList<Disk> first;
        ArrayList<Disk> second;

        public myTask(Rectangle one, ArrayList<Disk> first, Rectangle two, ArrayList<Disk> second){
            this.one = one;
            this.first = first;
            this.two = two;
            this.second = second;
        }
        @Override
        public void run() {
            try {
                moveOneDisk(one,first,two,second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}