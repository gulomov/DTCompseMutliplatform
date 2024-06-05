package org.dtcm.work.repository.firebasehelper

import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.firstOrNull

inline fun <reified T : Any> fetchFromDatabase(
    path: String,
    database: FirebaseDatabase,
): Flow<T?> = callbackFlow {
    try {
        val reference: DatabaseReference = database.reference(path)
        val dataSnapshot = reference.valueEvents.firstOrNull()
        if (dataSnapshot?.exists == true) {
            dataSnapshot.value<T>().also {
                println("Data fetched successfully: $it")
                trySend(it)
            }
        } else {
            println("No repository.data found")
        }
    } catch (e: Exception) {
        println("Error fetching repository.data from Firebase: ${e.message}")
        close(e)
    }

    awaitClose {
        println("Closing the fetchFromDatabase flow")
    }
}