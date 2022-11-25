package androidsamples.java.journalapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*


class EntryDetailsViewModel : ViewModel() {

    companion object {
        private const val VM_TAG = "ENTRY_DETAILS_VIEWMODEL"
    }

    private var mRepository: JournalRepository? = null
    private var isStartTime = false
    private var hours = 0
    private var minutes = 0
    private val entryIdLiveData: MutableLiveData<UUID> = MutableLiveData<UUID>()

    init {
        mRepository = JournalRepository.getInstance()
    }

    fun getHours(): Int {
        return hours
    }

    fun setHours(hours: Int) {
        this.hours = hours
    }

    fun getMinutes(): Int {
        return minutes
    }

    fun setMinutes(minutes: Int) {
        this.minutes = minutes
    }

    fun getEntryLiveData(): LiveData<JournalEntry?>? {
        Log.d(VM_TAG, "getEntryLiveData called")
        return mRepository!!::getEntry?.let { Transformations.switchMap(entryIdLiveData, it) }
    }

    fun loadEntry(entryId: UUID) {
        Log.d(VM_TAG, "loading entry: $entryId")
        entryIdLiveData.value = entryId
    }

    fun setIsStartTime(num: Boolean) {
        isStartTime = num
    }

    fun getIsStartTime(): Boolean {
        return isStartTime
    }

    fun saveEntry(entry: JournalEntry) {
        Log.d(VM_TAG, "Saving entry: " + entry.uid)
        mRepository!!.update(entry)
    }

    fun deleteEntry(entry: JournalEntry) {
        Log.d(VM_TAG, "DEleting entry: " + entry.uid)
        mRepository!!.delete(entry)
    }
}