package com.example.roomsample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    /**
     * すべての単語をアルファベット順に並べて取得
     *
     * Flowにより、DBが更新された際に自動的に更新される
     * RoomクエリがFlowを返す場合、クエリは自動的にバックグラウンドスレで非同期に実行される
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    /**
     * 単語を挿入する
     *
     * onConflict はすでにリストにある単語を挿入しようとした場合は無視される
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /**
     * すべての単語を削除する
     */
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}