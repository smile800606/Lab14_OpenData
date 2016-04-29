package com.example.student.lab14_opendata.MyApp;

import android.app.Application;
import android.content.Context;

import com.example.student.lab14_opendata.model.TaipeiAttractions;

public class MyApp extends Application{

    private static Context context;
    private static TaipeiAttractions taipeiAttractions;

    public static Context getContext() {
        return context;
    }

    public static TaipeiAttractions getTaipeiAttractions() {
        return taipeiAttractions;
    }
    public static void setTaipeiAttractions(TaipeiAttractions taipeiAttractions){
        MyApp.taipeiAttractions = taipeiAttractions;
    }

    public MyApp(){
        context = this;
    }
}

