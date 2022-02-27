# MarsPhotos - Starter Code

Starter code for [Android Basics in Kotlin](https://developer.android.com/courses/android-basics-kotlin/course).

## Introduction

Using this stater code you will create MarsPhotos is a demo app that shows actual images of Mar's surface. These images are
real-life photos from Mars captured by NASA's Mars rovers. The data is stored on a Web server
as a REST web service. The solution app will demonstrate the use of [Retrofit](https://square.github.io/retrofit/) to make REST requests to the web service, [Moshi](https://github.com/square/moshi) to
handle the deserialization of the returned JSON to Kotlin data objects, and [Coil](https://coil-kt.github.io/coil/) to load images by URL.

The app also leverages [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
[LiveData](https://developer.android.com/topic/libraries/architecture/livedata), and
[Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding
adapters.

# メモ

- Coil ライブラリによる多数の画像の取得（画像のダウンロード、バッファリング、デコード：Android が読み込める形式に変換、キャッシュ保存をしてくれる）
- RecyclerView で画像をグリッド表示
- 通信失敗時のエラーハンドリング、通信中のプレースホルダ、通信中のステータスを enum で表す。
- グリッドで並べるときのマテリアルデザインのガイドライン
- バインディングアダプター：ビューにカスタマイズした属性値を設定するクラス。
- RecyclerView のアダプターの使い方が発展的で難しい。レイアウトファイルからビューホルダーを生成するのではなく、バインディングクラスからインフレートしていたりする。
- `[ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter?hl=ja)[RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter?hl=ja)`のサブクラス。
   RecyclerView にリストデータを表示するために使用。DiffUtil 実装でリストの変更部分のみの更新が可能になる。
