package com.example.roomsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * 通常　アプリ全体で必要なRoomデータベースのインスタンスは1つのみ
 *
 * exportSchema = false は、データベースのバージョン管理に関する警告を非表示にする
 * 実アプリでは現在のスキーマをバージョン管理システムにチェックインできるように、Roomによる
 * スキーマのエクスポートに使用されるディレクトリを指定する
 */
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        /**
         * シングルトンによって同時に複数のデータベースを開かないようにする
         */
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            /**
             * もしINSTANCEがnull出ない場合はそのまま返す
             * nullの場合はそのデータベースを作成する
             */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java, // RoomDatabaseを継承したクラスを指定する
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}