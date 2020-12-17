package com.main.damoim.main.category;

import java.util.ArrayList;

public class Position {

    public String position;
    public ArrayList<String> parent_category = new ArrayList<String>();

    public Position(String position) {
        this.position = position;
    }

    public String toString() {
        return position;
    }
}