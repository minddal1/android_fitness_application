package studios.kundby.fitnessapp.adapter;
/**
 * @author Anders & Osvald
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import studios.kundby.fitnessapp.R;
import studios.kundby.fitnessapp.model.ExercisesModel;
import studios.kundby.fitnessapp.storage.Exercises_storage;
import studios.kundby.fitnessapp.storage.Training_storage;
import studios.kundby.fitnessapp.ui.exercises.DetailedExercisesActivity;
import studios.kundby.fitnessapp.ui.exercises.ExercisesViewHolder;

public class ExerciseAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {


    private final ArrayList<ExercisesModel> list;

    public static final String headlineKey = "HEADLINE_KEY";
    public static final String descriptionKey = "DESCRIPTION_KEY";
    public static final String videolinkKey = "VIDEOLINK_KEY";
    public static final String repsKey = "REPS_KEY";
    public static final String setsKey = "SETS_KEY";


    public ExerciseAdapter() {
        this.list = Exercises_storage.getDisplayedList();
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);

        return new ExercisesViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedExercisesActivity.class);
                intent.putExtra(headlineKey, Exercises_storage.getDisplayedList().get(position).getHeadLine());
                intent.putExtra(descriptionKey, Exercises_storage.getDisplayedList().get(position).getDescription());
                intent.putExtra(videolinkKey, Exercises_storage.getDisplayedList().get(position).getVideoLink());
                intent.putExtra(repsKey, Exercises_storage.getDisplayedList().get(position).getReps());
                intent.putExtra(setsKey, Exercises_storage.getDisplayedList().get(position).getSets());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Exercises_storage.getDisplayedList().size();
    }


}
