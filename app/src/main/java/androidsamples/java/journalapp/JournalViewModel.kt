package androidsamples.java.journalapp

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel


class JournalViewModel : ViewModel() {
    private val mRepository: JournalRepository = JournalRepository.getInstance()

    val allEntries: LiveData<List<JournalEntry?>?>?
        get() = mRepository.getAllEntries()

    fun insert(entry: JournalEntry?) {
        mRepository.insert(entry)
    }
}