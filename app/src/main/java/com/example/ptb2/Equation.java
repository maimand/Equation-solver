package com.example.ptb2;

import java.io.Serializable;
import java.text.DecimalFormat;


public class Equation implements Serializable {
    private double a, b, c;
    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return this.a+"x^2 + " + this.b + "x +" + this.c + " = 0";
    }

    private double deltaCompute(){
        return this.b*this.b - 4*this.a*this.c;
    }

    public String resultOut(){
        DecimalFormat f = new DecimalFormat("##.00");
        if(this.a == 0){
            double x = - this.c / this.b;
            return "x = " + String.valueOf(f.format(x));
        }
        double delta = this.deltaCompute();
        if(delta < 0 ){
            return "Impossile to solve";
        }else if(delta == 0){
            double x = - this.b / 2 / this.a;
            return "x = " + String.valueOf(f.format(x));
        }else{
            double x1 = ((-this.b + Math.sqrt(delta)) / (2*this.a));
            double x2 = ((-this.b - Math.sqrt(delta)) / (2*this.a));

            return "x1 = " +  String.valueOf(f.format(x1)) + "\t x2 = "+ String.valueOf(f.format(x2));
        }
    }
}
