package com.example.android.marsphotos.network

import com.squareup.moshi.Json

// Jsonデータを受け取るdataクラス
data class MarsPhoto(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String// Jsonのキー名と異なるプロパティ名をつける場合
)