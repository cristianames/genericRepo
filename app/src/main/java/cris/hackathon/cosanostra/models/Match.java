package cris.hackathon.cosanostra.models;

import android.annotation.TargetApi;
import android.os.Build;

import com.google.android.gms.common.api.Api;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cris.hackathon.cosanostra.services.firebase.FirebaseRepository;

/**
 * Created by Julieta on 18/09/16.
 */
@TargetApi(Build.VERSION_CODES.N)
public class Match {
    private String _id;
    private String _name;
    private Optional<DatabaseReference> _ref = Optional.empty();

    public List<User> users = new ArrayList<>();

    public Match(String id, String name) {
        _id = id;
        _name = name;
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public void add(User user) {
        users.add(user);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setRef(DatabaseReference reference) {
        _ref = Optional.of( reference);
    }

}
