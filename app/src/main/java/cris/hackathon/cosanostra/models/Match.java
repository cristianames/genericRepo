package cris.hackathon.cosanostra.models;

import android.annotation.TargetApi;
import android.os.Build;

import com.google.android.gms.common.api.Api;
import com.google.firebase.database.DatabaseReference;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cris.hackathon.cosanostra.services.firebase.FirebaseRepository;

/**
 * Created by Julieta on 18/09/16.
 */
public class Match {
    public String id;
    public String name;

    public List<User> users = new ArrayList<>();

    public Match(String name) {
        id = new Long(new java.util.Date().getTime()).toString();
        this.name = name;
    }

    public void add(User user) {
        users.add(user);
    }

}
