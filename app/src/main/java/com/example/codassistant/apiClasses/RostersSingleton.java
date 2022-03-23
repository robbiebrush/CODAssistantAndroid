package com.example.codassistant.apiClasses;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RostersSingleton {

    public static RostersSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private RostersSingleton(Context context){
        this.context = context;
    }

    public static RostersSingleton getInstance(Context context){
        if(instance == null){
            instance = new RostersSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

}