package androidsamples.java.journalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A fragment representing a list of Items.
 */
class EntryListFragment : Fragment() {

    private val mJournalViewModel: JournalViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entry_list, container, false)
        val addEntryFab = view.findViewById<FloatingActionButton>(R.id.btn_add_entry)

        // recycler view setup
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapter = JournalEntryListAdapter(activity?.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)

        addEntryFab.setOnClickListener {
            findNavController().navigate(R.id.addEntryAction)
        }

        return view
    }

    companion object {
        fun newInstance(): EntryListFragment {
            val fragment = EntryListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}