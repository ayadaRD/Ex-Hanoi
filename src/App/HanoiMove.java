package App;

import javafx.scene.shape.Rectangle;

import java.util.*;

public class HanoiMove {

    ArrayList<Disk> fromStack;
    ArrayList <Disk>toStack;
    Rectangle first;
    Rectangle second;

    public HanoiMove(Rectangle first,ArrayList<Disk> fromStack,Rectangle second , ArrayList<Disk> toStack){

        this.fromStack = fromStack;
        this.toStack = toStack;
        this.first =first;
        this.second= second;
    }

}
