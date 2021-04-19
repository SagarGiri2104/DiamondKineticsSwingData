package com.diamond.kinetics.swing;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SwingData {
    private final int timeStamp;
    private final double ax;
    private final double ay;
    private final double az;
    private final double wx;
    private final double wy;
    private final double wz;


    public double getColumnData(Column data){
        if(Column.TIMESTAMP.equals(data)){
            return this.timeStamp;
        }
        else if(Column.AX.equals(data)){
            return this.ax;
        }
        else if(Column.AY.equals(data)){
            return this.ay;
        }
        else if(Column.AZ.equals(data)){
            return this.az;
        }
        else if(Column.WX.equals(data)){
            return this.wx;
        }
        else if(Column.WY.equals(data)){
            return this.wy;
        }
        else if(Column.WZ.equals(data)){
            return this.wz;
        }
        else {
            return 0;
        }
    }
}


