package com.example.roomsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * word_table Tableに対応したエンティティ用データクラス
 * このクラスにより、単語用のエンティティが記述される
 * クラス内のプロパティ: テーブル内の列を表す
 *
 * @Entity SQLiteテーブルを表現する
 * このアノテーションでテーブル名を指定数rことでクラスメイト異なる名前にできる
 *
 * @PrimaryKey: すべてのエンティティにはprimary keyが必要
 * @ColumnInfo テーブル内の列名をメンバー変数と異な名前にする場合に指定
 */
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "word") val word: String
)