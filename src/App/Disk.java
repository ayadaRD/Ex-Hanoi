package App;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Disk implements Cloneable{

    double width;
    double X;
    double Y;
    int ID;

    public Disk(double width) {
        this.width = width;
    }

    public Object clone() throws CloneNotSupportedException   //for special cases
    {                                                         //when we want copy this object into another object
        return super.clone();
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
