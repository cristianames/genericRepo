package cris.hackathon.cosanostra.models;

import android.annotation.TargetApi;
import android.os.Build;

import com.google.firebase.database.DatabaseReference;

import java.util.Optional;

/**
 * Created by Julieta on 18/09/16.
 */
@TargetApi(Build.VERSION_CODES.N)
public class User {

    private String _googleId;
    private String _userName;
    private Optional<DatabaseReference> _ref = Optional.empty();

    public Role role;

    public User(String googleId, String userName) {
        _googleId = googleId;
        _userName = userName;
    }

    public String getGoogleId() {
        return _googleId;
    }

    public String getUserName() {
        return _userName;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setRef(DatabaseReference reference) {
        _ref = Optional.of(reference);
    }
}
