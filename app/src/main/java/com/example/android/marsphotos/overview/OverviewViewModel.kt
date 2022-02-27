/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

/**
 * 通信ステータス
 */
enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // リクエストステータス（内部で使用）
    private val _status = MutableLiveData<MarsApiStatus>()

    // リクエストステータス（外部から参照）
    val status: LiveData<MarsApiStatus> = _status

    // 火星画像（内部で使用）
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // 火星画像（外部から参照）
    val photos: LiveData<List<MarsPhoto>> = _photos

    /**
     * 初期化時に火星画像取得
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {


        // viewModelScope:コルーチンスコープの一種
        // コルーチンスコープとは、コルーチンが生存していられる仮想領域。このスコープ内でコルーチンを起動できる。
        viewModelScope.launch {

            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitSevice.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                // 通信エラー時は写真のリストを空にする。
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }
}
