package cris.hackathon.cosanostra.services.firebase.models

/**
 * Created by CristianErik on 18/09/2016.
 */
enum class FirebaseRef constructor(val refName: String) {
    Users("users"),
    Matches("matches")
}
