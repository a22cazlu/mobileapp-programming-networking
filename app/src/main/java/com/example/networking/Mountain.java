package com.example.networking;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public class Mountain {

    private String title;
    private String ID;
    private String name;
    private String type;
    private String company;
    private String size;
    private String cost;
    private Auxdata auxdata;

    public Mountain(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}