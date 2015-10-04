package com.github.hackruixfirstly.firstly.models;

import java.util.Date;

/**
 * Created by trevor on 10/3/15.
 */
public class Experience extends BaseModel {

    public String text;
    public Date   dateCreated;
    public String facebookId;

    public Experience(String text) {
        this.text = text;
    }

}
