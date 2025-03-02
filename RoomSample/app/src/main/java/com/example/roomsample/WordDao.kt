package com.example.roomsample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    /**
     * すべての単語をアルファベット順に並べて取得
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): List<Word>

    /**
     * 単語を挿入する
     *
     * onConflict はすでにリストにある単語を挿入しようとした場合は無視される
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /**
     * 指定した単語を削除する
     */
    @Delete()
    suspend fun deleteWord(word: String)

    /**
     * すべての単語を削除する
     */
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}