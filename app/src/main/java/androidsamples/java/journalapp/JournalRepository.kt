package androidsamples.java.journalapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*
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
        Log.d("JOURNAL_REPO_CONSTR", "creating db")
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

    fun delete(entry: JournalEntry?) {
        mExecutor.execute { mJournalEntryDao!!.delete(entry) }
    }

    fun update(entry: JournalEntry?) {
        mExecutor.execute { mJournalEntryDao!!.update(entry) }
    }

    fun getAllEntries(): LiveData<List<JournalEntry>> {
        return mJournalEntryDao!!.allEntries
    }

    fun getEntry(id: UUID): LiveData<JournalEntry?>? {
        return mJournalEntryDao!!.getEntry(id)
    }
}