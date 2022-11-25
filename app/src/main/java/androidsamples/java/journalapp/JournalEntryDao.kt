package androidsamples.java.journalapp

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*


@Dao
interface JournalEntryDao {
    @Insert
    fun insert(entry: JournalEntry?)

    @Delete
    fun delete(entry: JournalEntry?)

    @Update
    fun update(entry: JournalEntry?)

    @get:Query("SELECT * from journal_table ORDER BY title ASC")
    val allEntries: LiveData<List<JournalEntry>>

    @Query("SELECT * from journal_table WHERE id=(:id)")
    fun getEntry(id: UUID?): LiveData<JournalEntry?>?
}