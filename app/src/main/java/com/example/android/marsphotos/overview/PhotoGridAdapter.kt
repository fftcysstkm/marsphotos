package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    /*
    * ビューホルダ
    * レイアウトとdataクラスを対応させる。そのため、bindingクラスが引数となっている。
    * 基底クラスはビューが必要。そのため、バインディングルートビューが渡される。
    * */
    class MarsPhotoViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // データバインディングのphoto変数にMarsPhotoを設定
        // executePendingBindings()で更新が直ぐ行われる。
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {

        // 新しいMarsPhotoオブジェクトと古いMarsPhotoオブジェクトが同じかどうかを判定する
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            // 今回のケースではMarsPhotoは一意のIDを持つ
            return oldItem.id == newItem.id
        }

        // 新しいMarsPhotoオブジェクトと古いMarsPhotoオブジェクトが同じデータを持つかどうかを判定する
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

    // ビューホルダーを生成し、RecyclerViewにわたす。
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    // ビューホルダーを生成し、RecyclerViewにわたす。
    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }


}