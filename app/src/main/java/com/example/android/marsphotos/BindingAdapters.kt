package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

/**
 * 対象：grid_view_item.xml内のImageView
 * 第一引数のViewアイテムに"imageUrl属性(今回の独自定義)"がある場合、第二引数を属性値として設定
 * 今回は、imgUrl属性値にURLを設定
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    // オブジェクトがnullでないときにのみ.let{ }内の処理が実行
    imgUrl?.let {
        // URIオブジェクト生成,画像をimgViewに読み込ませる
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * 対象：fragment_overview.xml内のRecyclerView
 * MarsPhotoのリストに変更があるか監視
 * 変更があればPhotoGridAdapterを初期化する。
 *
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    // RecyclerViewに新しいリストが利用可能なことを通知
    adapter.submitList(data)
}

/**
 * 対象：fragment_overview.xml内の取得ステータスを表すImageViewに追加する属性
 * viewModelのステータスが変更されたときにこのメソッドが呼ばれ、ステータスに応じた画像を表示
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}