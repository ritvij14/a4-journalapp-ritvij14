package androidsamples.java.journalapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [# newInstance][EntryDetailsFragment] factory method to
 * create an instance of this fragment.
 */
class EntryDetailsFragment : Fragment() {

    private var mEntryDetailsViewModel: EntryDetailsViewModel? = null
    private var mEntry: JournalEntry? = null
    private lateinit var entryTitle: EditText
    private lateinit var dateButton: Button
    private lateinit var startTimeButton: Button
    private lateinit var endTimeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val entryId: UUID =
            UUID.fromString(EntryDetailsFragmentArgs.fromBundle(requireArguments()).entryId)
        mEntryDetailsViewModel =
            ViewModelProvider(requireActivity())[EntryDetailsViewModel::class.java]
        mEntryDetailsViewModel!!.loadEntry(entryId)

        val view = inflater.inflate(R.layout.fragment_entry_details, container, false)
        entryTitle = view.findViewById(R.id.edit_title)
        dateButton = view.findViewById(R.id.btn_entry_date)
        startTimeButton = view.findViewById(R.id.btn_start_time)
        endTimeButton = view.findViewById(R.id.btn_end_time)
        val saveButton = view.findViewById<Button>(R.id.btn_save)

        mEntryDetailsViewModel!!.getEntryLiveData()!!.observe(
            requireActivity()
        ) { entry: JournalEntry? ->
            if (entry != null) {
                mEntry = entry
                if (mEntry!!.date != "")
                    dateButton.text = mEntry!!.date
                entryTitle.setText(mEntry!!.title)
                if (mEntry!!.startTime != "")
                    startTimeButton.text = mEntry!!.startTime
                if (mEntry!!.endTime != "")
                    endTimeButton.text = mEntry!!.endTime;
            }
        }

        dateButton.setOnClickListener {
            findNavController().navigate(R.id.datePickerAction)
        }

        startTimeButton.setOnClickListener {
            mEntryDetailsViewModel!!.setIsStartTime(true)
            findNavController().navigate(R.id.timePickerAction)
        }

        endTimeButton.setOnClickListener {
            mEntryDetailsViewModel!!.setIsStartTime(false)
            findNavController().navigate(R.id.timePickerAction)
        }

        saveButton.setOnClickListener {
            if (entryTitle.text.toString()
                    .lowercase() == "title" || entryTitle.text.toString() == ""
            ) {
                Toast.makeText(requireContext(), "Please enter a valid title", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (dateButton.text.toString() == "Date") {
                Toast.makeText(requireContext(), "Please enter a date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (startTimeButton.text.toString() == "Start Time") {
                Toast.makeText(requireContext(), "Please enter a start time", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (endTimeButton.text.toString() == "End Time") {
                Toast.makeText(requireContext(), "Please enter an end time", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            mEntry = JournalEntry(
                entryId,
                entryTitle.text.toString(),
                dateButton.text.toString(),
                startTimeButton.text.toString(),
                endTimeButton.text.toString()
            )
            mEntryDetailsViewModel!!.saveEntry(mEntry!!)
            activity?.onBackPressed()
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.entry_detail_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_entry) {
            AlertDialog.Builder(context).setTitle("Delete?").setMessage("Are you sure?")
                .setPositiveButton(
                    "YES"
                ) { _, _ ->
                    mEntry?.let { mEntryDetailsViewModel!!.deleteEntry(it) }
                    requireActivity().onBackPressed()
                }.setNegativeButton("NO", null).show()
        } else if (item.itemId == R.id.menu_share_entry) {
            if (entryTitle.text.toString()
                    .lowercase() == "title" || entryTitle.text.toString() == ""
            ) {
                Toast.makeText(requireContext(), "Please enter a valid title", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (dateButton.text.toString() == "Date") {
                Toast.makeText(requireContext(), "Please enter a date", Toast.LENGTH_SHORT).show()
                return false
            }
            if (startTimeButton.text.toString() == "Start Time") {
                Toast.makeText(requireContext(), "Please enter a start time", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (endTimeButton.text.toString() == "End Time") {
                Toast.makeText(requireContext(), "Please enter an end time", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            val message =
                "Look what I have been up to: " + entryTitle.text.toString() + " on " + dateButton.text.toString() + ", " + startTimeButton.text.toString() + " to " + endTimeButton.text.toString()
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/*"
            startActivity(shareIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
