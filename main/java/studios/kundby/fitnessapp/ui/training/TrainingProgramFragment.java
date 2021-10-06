package studios.kundby.fitnessapp.ui.training;
/**
 * @author Anders & Osvald
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import studios.kundby.fitnessapp.R;
import studios.kundby.fitnessapp.adapter.CategoriesAdapter;
import studios.kundby.fitnessapp.adapter.TrainingProgramAdapter;
import studios.kundby.fitnessapp.storage.Training_storage;


public class TrainingProgramFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TrainingProgramAdapter adapter;
    private Training_storage training_storage = new Training_storage();
    private View v;
    private String chosenCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_training_program, container, false);
        recyclerView = v.findViewById(R.id.programRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TrainingProgramAdapter(getContext());
        recyclerView.setAdapter(adapter);
        Bundle data = getArguments();
        if (data != null) {
            chosenCategory = data.getString(CategoriesAdapter.categoryKey);
        }
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Go hard on your " + chosenCategory + " today");
        training_storage.sortList();
    }
}

