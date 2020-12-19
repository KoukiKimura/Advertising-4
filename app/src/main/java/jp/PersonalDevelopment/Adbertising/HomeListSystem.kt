package jp.PersonalDevelopment.Adbertising

import java.io.Serializable

class HomeListSystem(val title: String, val desp: String, val uid: String, val  bytes: ByteArray) : Serializable {
    val imageBytes: ByteArray

    init{
        imageBytes = bytes.clone()
    }
}