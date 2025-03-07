package com.example.inventory.repository

import com.example.inventory.data.Item
import kotlinx.coroutines.flow.Flow

/**
 *
 */
interface ItemsRepository {

    /**
     * DBからすべてのitemsを取得する
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * DBから指定したidのitemを取得する
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * DBに新しいitemを追加する
     */
    suspend fun insertItem(item: Item)

    /**
     * DBのitemを削除する
     */
    suspend fun deleteItem(item: Item)

    /**
     * DBのitemを更新する
     */
    suspend fun updateItem(item: Item)
}