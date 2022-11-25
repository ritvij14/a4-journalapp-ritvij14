package androidsamples.java.journalapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView


class JournalEntryListAdapter(context: Context?) :
    RecyclerView.Adapter<JournalEntryListAdapter.EntryViewHolder>() {

    private var mInflater: LayoutInflater? = null
    private var mEntries: List<JournalEntry>? = null

    init {
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
            if (current.title == "" && current.date == "") JournalRepository.getInstance()
                .delete(current)
            holder.mTxtTitle.text = current.title
            holder.mDate.text = current.date
            holder.mStartTime.text = current.startTime
            holder.mEndTime.text = current.endTime
            holder.mEntry = current
        }
    }

    override fun getItemCount(): Int {
        return mEntries?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEntries(entries: List<JournalEntry>?) {

        mEntries = entries
        notifyDataSetChanged()
    }

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTxtTitle: TextView
        val mDate: TextView
        val mStartTime: TextView
        val mEndTime: TextView
        var mEntry: JournalEntry? = null

        init {
            mTxtTitle = itemView.findViewById(R.id.item_title)
            mDate = itemView.findViewById(R.id.item_date)
            mStartTime = itemView.findViewById(R.id.item_start_time)
            mEndTime = itemView.findViewById(R.id.item_end_time)

            itemView.setOnClickListener {
                val action = EntryListFragmentDirections.addEntryAction()
                action.entryId = mEntry!!.uid.toString()
                NavHostFragment.findNavController(itemView.findFragment()).navigate(action)
            }
        }
    }
}