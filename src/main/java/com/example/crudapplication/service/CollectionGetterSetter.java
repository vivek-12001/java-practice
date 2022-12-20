package com.example.crudapplication.service;

import com.example.crudapplication.entity.SimpleGetterAndSetter;

import java.util.ArrayList;
import java.util.List;

public class CollectionGetterSetter {
    public static void main(String[] args) {
        SimpleGetterAndSetter simpleGetterAndSetter = new SimpleGetterAndSetter();

        int[] arr1 = {1, 2, 3, 4};
        simpleGetterAndSetter.setNumber(arr1);
        simpleGetterAndSetter.displayScores();

        arr1[1] = 20;
        simpleGetterAndSetter.displayScores();

        int[] getNum = simpleGetterAndSetter.getNumber();
        getNum[1] = 30;
        simpleGetterAndSetter.displayScores();

        List<String> titles1 = new ArrayList();
        titles1.add("Name");
        titles1.add("Address");
        titles1.add("Email");
        titles1.add("Job");

        simpleGetterAndSetter.setListTitles(titles1);

        List<String> getTitles = simpleGetterAndSetter.getListTitles();
        System.out.println("getTitles = " + getTitles);

        getTitles.add(1, "last name");
        List<String> getTitles1 = simpleGetterAndSetter.getListTitles();
        System.out.println("getTitles = " + getTitles1);
    }
}
