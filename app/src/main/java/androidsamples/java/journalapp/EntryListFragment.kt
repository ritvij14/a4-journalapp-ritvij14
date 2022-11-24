package androidsamples.java.journalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidsamples.java.journalapp.R
import androidsamples.java.journalapp.EntryListFragment
import androidx.fragment.app.Fragment

/**
 * A fragment representing a list of Items.
 */
class EntryListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entry_list, container, false)
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