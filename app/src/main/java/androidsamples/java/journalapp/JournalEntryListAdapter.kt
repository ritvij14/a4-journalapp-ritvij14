package androidsamples.java.journalapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class JournalEntryListAdapter : RecyclerView.Adapter<JournalEntryListAdapter.EntryViewHolder> {

    private var mInflater: LayoutInflater? = null
    private var mEntries: List<JournalEntry>? = null

    constructor(context: Context?) {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntryViewHolder {
        val itemView: View = mInflater!!.inflate(
            R.layout.journal_entry,
            parent,
            false
        )
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: EntryViewHolder,
        position: Int
    ) {
        if (mEntries != null) {
            val current = mEntries!![position]
            holder.mTxtTitle.text = current.title
            holder.mDate.text = current.date
            holder.mStartTime.text = current.startTime
            holder.mEndTime.text = current.endTime
        }
    }

    override fun getItemCount(): Int {
        return mEntries?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEntries(entries: List<JournalEntry>) {
        mEntries = entries
        notifyDataSetChanged()
    }

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTxtTitle: TextView
        val mDate: TextView
        val mStartTime: TextView
        val mEndTime: TextView

        init {
            mTxtTitle = itemView.findViewById(R.id.item_title)
            mDate = itemView.findViewById(R.id.item_date)
            mStartTime = itemView.findViewById(R.id.item_start_time)
            mEndTime = itemView.findViewById(R.id.item_end_time)
        }
    }
}