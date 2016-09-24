package cris.hackathon.cosanostra.services.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import cris.hackathon.cosanostra.services.firebase.models.FirebaseNotif
import cris.hackathon.cosanostra.services.firebase.models.FirebaseRef
import rx.Observable
import rx.lang.kotlin.observable

/**
 * Created by CristianErik on 18/09/2016.
 */
class FirebaseRepository(ref: String) {

    val ref: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        this.ref = database.getReference(ref)
        this.ref.setValue()
    }

    fun save(something: Any): Observable<Void> {
        return observable { subscriber ->
            ref.setValue(something)
                    .addOnCompleteListener {
                        subscriber.onNext(null)
                        subscriber.onCompleted()
                    }
                    .addOnFailureListener { subscriber.onError(throw Error("Error al escribir en Firebase")) }
        }
    }

    fun read(): Observable<DataSnapshot> {
        return observable { subscriber ->
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    subscriber.onNext(dataSnapshot)
                    subscriber.onCompleted()
                }
                override fun onCancelled(error: DatabaseError) {
                    subscriber.onError(throw Error("Error al hacer read en Firebase"))
                }
            })
        }
    }

    fun subscribeToEvents(): Observable<DataSnapshot> {
        return observable { subscriber ->
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    subscriber.onNext(dataSnapshot)
                }
                override fun onCancelled(error: DatabaseError) {
                    subscriber.onError(throw Error("Error al hacer subscribe en Firebase"))
                }
            })
        }
    }

    fun parent() = FirebaseRepository(ref.parent.key)

    fun child(newRef: String) = FirebaseRepository("${ref.key}/$newRef")

}

