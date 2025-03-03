package com.example.roomsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    /**
     * LiveDataの利用と、allWordsの戻り値をキャッシュすることにはいくつかの利点がある
     * - オブザーバにデータを追加(変更をポーリングする代わりに)し、データが実際に変更があったときだけUIを更新する
     * - リポジトリはViewModelを介することにより、完全にUIから分離される
     */
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /**
     * ノンブロッキングな方法でデータを挿入するために子ルーチンを起動する
     * これにより、insert()の実装がUIからカプセル化される
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

/**
 * これによりViewModelは構成変更があっても存在し続け、アクティビティが再作成された場合でも常にWordViewModelクラスの適切なインスタンスを取得できる
 */
class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}