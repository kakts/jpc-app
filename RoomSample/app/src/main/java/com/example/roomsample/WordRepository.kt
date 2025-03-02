package com.example.roomsample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * リポジトリコンストラクタにはDaoがわたされる
 * DaoにはDBに対する操作のメソッドが含まれるため、これにアクセスするd楓良い
 * DB全体をリポジトリに公開する必要はない
 *
 */
class WordRepository(private val wordDao: WordDao) {

    /**
     * Roomはすべてのクエリを分割したスレッドで実行する
     * 監視されたFlowは、監視元にデータの変更を通知する
     */
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /**
     * デフォルトでは、Roomはメインスレッドでsuspendクエリを実行する
     *
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}