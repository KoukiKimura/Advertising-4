package jp.PersonalDevelopment.Adbertising

import java.io.Serializable

class HomeImageListSystem(val homeTitle: String, val  bytes: ByteArray) : Serializable {
    val imageBytes: ByteArray

    init{
        imageBytes = bytes.clone()
    }
}