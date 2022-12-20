package com.example.crudapplication.entity;

import java.util.ArrayList;
import java.util.List;

public class SimpleGetterAndSetter {
    private int[] number;

    public int[] getNumber() {
        int[] copy = new int[this.number.length];
        System.arraycopy(this.number, 0, copy, 0, this.number.length);
        return copy;
    }

    public void setNumber(int[] num) {
        this.number = new int[num.length];
        System.arraycopy(num, 0, this.number, 0, num.length);
    }

    public void displayScores() {
        for (int i = 0; i < this.number.length; i++) {
            System.out.print(this.number[i] + " ");
        }
        System.out.println();
    }

    private List<String> listTitles;

    public void setListTitles(List<String> titles) {
        this.listTitles = new ArrayList<>(titles);
    }

    public List<String> getListTitles() {
        return new ArrayList<>(this.listTitles);
    }
}
