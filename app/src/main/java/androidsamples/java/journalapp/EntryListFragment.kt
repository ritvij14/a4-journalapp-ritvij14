package androidsamples.java.journalapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


/**
 * A fragment representing a list of Items.
 */
class EntryListFragment : Fragment() {

    private var mJournalViewModel: JournalViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_entry_list, container, false)
        val addEntryFab = view.findViewById<FloatingActionButton>(R.id.btn_add_entry)
        mJournalViewModel = ViewModelProvider(this)[JournalViewModel::class.java]

        // recycler view setup
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapter = JournalEntryListAdapter(activity?.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)

        mJournalViewModel!!.allEntries.observe(requireActivity(), adapter::setEntries)

        addEntryFab.setOnClickListener {
            val entry = JournalEntry(UUID.randomUUID(), "", "", "", "")
            val action = EntryListFragmentDirections.addEntryAction()
            mJournalViewModel!!.insert(entry)
            action.entryId = entry.uid.toString()
            findNavController().navigate(action)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.info_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.info_menu) {
            view?.let { Navigation.findNavController(it).navigate(R.id.gotoInfo) }
        }
        return super.onOptionsItemSelected(item)
    }
}