package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * データベース作成用抽象クラス
 *
 * @Databaseアノテーションでデータベースのエンティティとバージョンを指定
 *
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase() {

    /**
     * これを定義することにより、データベースがDAOを認識できるようになる
     */
    abstract fun itemDao(): ItemDao

    companion object {

        /**
         * volatile変数の値はキャッシュに保存されない
         * 読み書きはすべてメインメモリとの間で行われる
         * これにより、Instanceの値は常に最新となり、すべての実行スレッドで同じとなる
         * つまり、あるスレッドで行われた変更が、すぐに他のスレッドに反映される
         */
        @Volatile
        private var Instance: InventoryDatabase? = null


        /**
         * データベースインスタンスを取得する
         * 複数のスレッドがDBインスタンスを同時に要求し、結果として2つのDBが作成される可能性がある
         * これを競合状態と呼ぶ。
         * synchronizedブロックで囲むと、このコードブロックには一度に1つの実行スレッドしか入れず
         * DBは一度だけ初期化されることになる
         */
        fun getDatabase(context: Context): InventoryDatabase {
            // 競合状態回避のためsyncronizedブロックで囲む
            return Instance ?: synchronized(this) {
                /**
                 * データベースビルダーを使用してデータベースを取得する
                 */
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        /**
                         * 作成されたDBインスタンスへの参照を保持
                         */
                        Instance = it
                    }
            }
        }
    }
}