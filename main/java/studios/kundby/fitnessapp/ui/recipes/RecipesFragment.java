package studios.kundby.fitnessapp.ui.recipes;
/**
 * @author Nikolaj
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import studios.kundby.fitnessapp.R;
import studios.kundby.fitnessapp.adapter.FoodCategoriesAdapter;
import studios.kundby.fitnessapp.adapter.RecipesAdapter;
import studios.kundby.fitnessapp.storage.Recipes_storage;

public class RecipesFragment extends Fragment {

    private final Recipes_storage recipes_storage = new Recipes_storage();
    private View v;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipesAdapter adapter;

    private String chosenFoodCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipes_storage.sortList();

        recyclerView = v.findViewById(R.id.recipes_main_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RecipesAdapter(getContext());
        recyclerView.setAdapter(adapter);

        Bundle data = getArguments();
        if (data != null) {
            chosenFoodCategory = data.getString(FoodCategoriesAdapter.foodCategoryKey);
        }
        return v;
    }

    public void onResume() {
        super.onResume();

        // Sets toolbar title to following string
        getActivity().setTitle("Recipes - " + chosenFoodCategory);
    }
}