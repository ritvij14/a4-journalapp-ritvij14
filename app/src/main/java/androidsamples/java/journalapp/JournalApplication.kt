package androidsamples.java.journalapp

import android.app.Application

class JournalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JournalRepository.init(this)
    }
}