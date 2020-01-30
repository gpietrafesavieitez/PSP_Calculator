package app.data;

import java.io.Serializable;

public class Operation implements Serializable {

    private char type;

    private double firstNumber;

    private double secondNumber;

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public String toString() {
        return firstNumber + "" + type + "" + secondNumber;
    }

}