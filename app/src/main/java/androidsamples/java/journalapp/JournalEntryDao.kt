package androidsamples.java.journalapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface JournalEntryDao {
    @Insert
    fun insert(entry: JournalEntry?)

    @get:Query("SELECT * from journal_table ORDER BY title ASC")
    val allEntries: LiveData<List<JournalEntry?>?>?
}