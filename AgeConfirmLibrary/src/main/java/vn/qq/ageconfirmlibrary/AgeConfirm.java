package vn.qq.ageconfirmlibrary;

import android.app.AlertDialog;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgeConfirm {
    public static final int AGE_UNKNOWN = -1;
    public static final int AGE_UNDER_13 = 1;
    public static final int AGE_13_TO_16 = 2;
    public static final int AGE_OVER_16 = 3;

    interface AgeConfirmListener {
        void onAgeConfirm(int ageGroup);
    }

    public static void showAgeConfirm(AppCompatActivity activity, AgeConfirmListener listener) {
        AlertDialog.Builder builder
                = new AlertDialog.Builder(activity);
        // set the custom layout
        final View customLayout
                = activity.getLayoutInflater()
                .inflate(R.layout.dialog_age_confirm,
                        null);
        View btnConfirm = customLayout.findViewById(R.id.btnConfirm);
        RadioGroup ageRadGroup = customLayout.findViewById(R.id.radGroupAge);
        builder.setView(customLayout);
        builder.setCancelable(false);
        AlertDialog dialog
                = builder.create();
        dialog.show();
        btnConfirm.setOnClickListener(v -> {
            int selected = ageRadGroup.getCheckedRadioButtonId();
            if (selected == R.id.radUnder13) {
                listener.onAgeConfirm(AGE_UNDER_13);
                dialog.dismiss();
            } else if (selected == R.id.rad13to16) {
                listener.onAgeConfirm(AGE_13_TO_16);
                dialog.dismiss();
            } else if (selected == R.id.radOver16) {
                listener.onAgeConfirm(AGE_OVER_16);
                dialog.dismiss();
            } else {
                Toast.makeText(activity, R.string.toast_choose_age, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
