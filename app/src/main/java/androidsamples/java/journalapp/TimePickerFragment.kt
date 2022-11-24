package androidsamples.java.journalapp

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO implement the method
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // TODO implement the method
        return TimePickerDialog(
            requireContext(),
            { tp: TimePicker?, hm: Int, m: Int -> },
            0,
            0,
            false
        )
    }

    companion object {
        fun newInstance(time: Date?): TimePickerFragment? {
            // TODO implement the method
            return null
        }
    }
}