package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.gson.Gson
import java.io.FileInputStream

data class NewPost(val id: Long, val author: String, val content: String)

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val post = NewPost(
        1L,
        "Гуррен-Лаганн",
        "Может это абсурдно, может смешно, но так сражаются мужчины. Мы снесём любую стену, вставшую на пути. Если нет пути — мы его проложим. Магма течёт в наших венах, раскаляя сердца. Безграничное слияние. Гуррен-Лаганн! Вы за кого нас держите?!"
    )

    val message = Message.builder()
        .putData("action", "NEW_POST")
        .putData(
            "content", Gson().toJson(post)
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
