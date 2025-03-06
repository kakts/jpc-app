package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Itemテーブルデータ用のDAO
 * interfaceに対して@Daoアノテーションをつけると、Roomがコンパイル時に関連した実装を生成する
 */
@Dao
interface ItemDao {

    /**
     * Itemテーブルに新しいItemを追加する
     * suspendキーワードをつけることで、この関数をコルーチンとして別スレッドで実行できる
     * insert時にデータ競合は想定しないため、競合時の戦略はIGNOREにする
     * IGNOREにすると、新しいアイテムを無視する
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * ItemテーブルのItemを更新する
     * 引数に渡したItemエンティティと同じ主キーを持つエンティティを更新する
     * プロパティの一部又は全部を更新できる
     */
    @Update
    suspend fun update(item: Item)

    /**
     * 指定したエンティティを削除する
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * idが引数に渡したidと一致するItemに対してすべての列を取得する
     *
     * :id = 関数内のidという引数を参照している
     *
     * 戻り値 永続化レイヤではFlowの使用を推奨
     * これによりDB内のデータが変更されるたびに通知が届く
     * RoomがこのFlowを最新状態に維持する
     * つまり、データを明示的に取得する必要があるのは一度だけ
     */
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * ItemテーブルのすべてのItemを取得する
     * 名前昇順でソートされる
     */
    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}