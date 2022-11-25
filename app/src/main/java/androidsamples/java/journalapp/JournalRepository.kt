package androidsamples.java.journalapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class JournalRepository(context: Context) {

    private val DATABASE_NAME = "journal_table"
    private var mJournalEntryDao: JournalEntryDao? = null
    private val mExecutor: Executor = Executors.newSingleThreadExecutor()

    companion object {
        private var sInstance: JournalRepository? = null

        fun getInstance(): JournalRepository {
            if (sInstance == null)
                throw IllegalStateException("Repo must be initialised")
            else
                return sInstance!!
        }

        fun init(context: Context) {
            if (sInstance == null) sInstance = JournalRepository(context)
        }
    }

    init {
        val db = Room.databaseBuilder(
            context.applicationContext,
            JournalRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
        mJournalEntryDao = db.journalEntryDao()
    }

    fun insert(entry: JournalEntry?) {
        mExecutor.execute { mJournalEntryDao!!.insert(entry) }
    }

    fun getAllEntries(): LiveData<List<JournalEntry?>?>? {
        return mJournalEntryDao!!.allEntries
    }
}