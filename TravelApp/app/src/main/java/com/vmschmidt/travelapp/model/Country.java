package com.vmschmidt.travelapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Country {

    private String name;
    private String code;
    private int flagResource;

    public Country(JSONObject jsonObject){
        try {
            this.name = jsonObject.getString("name");
            this.code = jsonObject.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
