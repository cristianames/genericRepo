package cris.hackathon.cosanostra.services.firebase.models

/**
 * Created by CristianErik on 18/09/2016.
 */
enum class FirebaseNotif {
    WakeUpThieves,
    WakeUpMedic,
    WakeUpAll,
    ThievesChoise,
    MedicChoise,
    AllChoise,
    Killed,
    //TODO: Eliminar case default
    NotHandled;


    companion object {

        fun From(value: String): FirebaseNotif {
            when (value) {
                "WakeUpThieves" -> return FirebaseNotif.WakeUpThieves
                "WakeUpMedic" -> return FirebaseNotif.WakeUpMedic
                "WakeUpAll" -> return FirebaseNotif.WakeUpAll
                "ThievesChoise" -> return FirebaseNotif.ThievesChoise
                "MedicChoise" -> return FirebaseNotif.MedicChoise
                "AllChoise" -> return FirebaseNotif.AllChoise
                "Killed" -> return FirebaseNotif.Killed
                else -> return FirebaseNotif.NotHandled
            }
        }
    }
}
