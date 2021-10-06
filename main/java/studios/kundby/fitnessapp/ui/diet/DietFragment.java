package studios.kundby.fitnessapp.ui.diet;
/**
 * @author Nikolaj
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import studios.kundby.fitnessapp.R;

public class DietFragment extends Fragment {

    private View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_diet, container, false);

        return v;
    }


    public void onResume() {
        super.onResume();

        getActivity().setTitle("Diet");
    }

}