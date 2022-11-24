package androidsamples.java.journalapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // TODO implement the method
        return DatePickerDialog(
            requireContext(),
            { dp: DatePicker?, y: Int, m: Int, d: Int -> },
            0,
            0,
            0
        )
    }

    companion object {
        fun newInstance(date: Date?): DatePickerFragment? {
            // TODO implement the method
            return null
        }
    }
}