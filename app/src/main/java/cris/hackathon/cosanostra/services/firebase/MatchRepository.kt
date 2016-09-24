package cris.hackathon.cosanostra.services.firebase

import cris.hackathon.cosanostra.models.Match
import cris.hackathon.cosanostra.models.User
import cris.hackathon.cosanostra.services.firebase.models.FirebaseRef
import rx.Observable
import rx.lang.kotlin.observable
import java.util.*

/**
 * Created by CristianErik on 18/09/2016.
 */

class MatchRepository(val repository: FirebaseRepository = FirebaseRepository(FirebaseRef.Matches.refName)) {

    fun createEmptyMatch(match: Match): Observable<Match> {
        return observable { subscriber ->
            if (!match.users.isEmpty()) subscriber.onError(throw Error("El partido no esta vacio"))
            repository.read().subscribe { result ->
                val matches = result as? List<Match>
                if (matches == null) subscriber.onError(throw Error("Error al castear a List de Matches"))

                val matchRepo = repository.child(matches?.size.toString())
                matchRepo.save(match).subscribe {
                    matchRepo.subscribeToEvents().subscribe { result ->
                        val match = result as? Match
                        if (match != null) subscriber.onNext(match)
                        else subscriber.onError(throw Error("Error al castear a Match"))
                    }
                }

            }

        }
    }

    fun joinToMatch(user: User, match: Match): Observable<User> {
        return observable { subscriber ->
            val userRepo = FirebaseRepository("matches/${match.id}/users/${user.googleId}")
            userRepo.save(user).subscribe {
                userRepo.subscribeToEvents().subscribe { result ->
                    val user = result as? User
                    if (user != null) subscriber.onNext(user)
                    else subscriber.onError(throw Error("Error al castear a Match"))
                }
            }
        }
    }

}