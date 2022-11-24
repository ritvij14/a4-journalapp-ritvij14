package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
  @NonNull
  public static DatePickerFragment newInstance(Date date) {
    // TODO implement the method
    return null;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // TODO implement the method

    return new DatePickerDialog(requireContext(), (dp, y, m, d) -> {
    }, 0, 0, 0);
  }
}
