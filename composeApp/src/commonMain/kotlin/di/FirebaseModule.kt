package di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import org.koin.dsl.module

val firebaseModule = module {
    single {
        Firebase.database("https://diplomathesiskmp-default-rtdb.europe-west1.firebasedatabase.app")
    }
}