package com.akavrt.rotten;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    int year;
    Ratings ratings;
    Posters posters;
    @SerializedName("abridged_cast")
    List<Member> cast = new ArrayList<>();
    String synopsis;
}
