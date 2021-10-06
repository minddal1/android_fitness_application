package studios.kundby.fitnessapp.firebaseHandler;
/**
 * @author Anders, Nikolaj & Osvald
 */
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import studios.kundby.fitnessapp.adapter.TrainingAdapter;
import studios.kundby.fitnessapp.model.CategoriesModel;
import studios.kundby.fitnessapp.model.ExercisesModel;
import studios.kundby.fitnessapp.model.FoodCategories;
import studios.kundby.fitnessapp.model.Recipe;
import studios.kundby.fitnessapp.model.TrainingModel;
import studios.kundby.fitnessapp.storage.Categories_storage;
import studios.kundby.fitnessapp.storage.Exercises_storage;
import studios.kundby.fitnessapp.storage.FoodCategories_storage;
import studios.kundby.fitnessapp.storage.Recipes_storage;
import studios.kundby.fitnessapp.storage.Training_storage;
import studios.kundby.fitnessapp.ui.exercises.ExercisesFragment;
import studios.kundby.fitnessapp.ui.training.TrainingFragment;

public class FirebaseHandler {

    public static List<CategoriesModel> listCat = new ArrayList<>();
    public static List<ExercisesModel> listExe = new ArrayList<>();
    public static List<FoodCategories> listFoo = new ArrayList<>();
    public static List<Recipe> listRec = new ArrayList<>();

    public static List<TrainingModel> listTrain = new ArrayList<>();
    public static List<TrainingModel> listProgram = new ArrayList<>();
    private final static FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final static CollectionReference catRef = mFirestore.collection("Categories");
    private final static CollectionReference exeRef = mFirestore.collection("Exercises");
    private final static CollectionReference trainRef = mFirestore.collection("Training");
    private final static CollectionReference fooRef = mFirestore.collection("FoodCategories");
    private final static CollectionReference recRef = mFirestore.collection("Recipes");

    private static final String TAG = "FirebaseHandler";

    public void loadDB() {
        listCat.clear();
        listExe.clear();
        listFoo.clear();
        listRec.clear();
        listTrain.clear();

        catRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    CategoriesModel categories = documentSnapshot.toObject(CategoriesModel.class);
                        if (!listCat.contains(categories) && !Categories_storage.getList().contains(categories)){
                            listCat.add(categories);
                        }
                }
                Categories_storage.getList().clear();
                Categories_storage.getList().addAll(listCat);
            }
        });

        exeRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    ExercisesModel exercises = documentSnapshot.toObject(ExercisesModel.class);
                        if (!listExe.contains(exercises) && !Exercises_storage.getFullList().contains(exercises)){
                            listExe.add(exercises);
                        }
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    TrainingModel trainingModel = documentSnapshot.toObject(TrainingModel.class);
                        if (!listProgram.contains(trainingModel) && !Training_storage.getTrainingSchemaList().contains(trainingModel)){
                            listProgram.add(trainingModel);
                        }
                }
                Training_storage.getTrainingSchemaList().clear();
                Training_storage.getTrainingSchemaList().addAll(listProgram);
                Exercises_storage.getFullList().clear();
                Exercises_storage.getFullList().addAll(listExe);
            }
        });

        fooRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FoodCategories foodCategories = documentSnapshot.toObject(FoodCategories.class);
                        if (!listFoo.contains(foodCategories) && !FoodCategories_storage.getList().contains(foodCategories)){
                            listFoo.add(foodCategories);
                        }
                }
                FoodCategories_storage.getList().clear();
                FoodCategories_storage.getList().addAll(listFoo);
            }
        });

        recRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Recipe recipe = documentSnapshot.toObject(Recipe.class);
                        if (!listRec.contains(recipe) && !Recipes_storage.getFullList().contains(recipe)){
                            listRec.add(recipe);
                        }
                }
                Recipes_storage.getFullList().clear();
                Recipes_storage.getFullList().addAll(listRec);
            }
        });

        trainRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    TrainingModel trainings = documentSnapshot.toObject(TrainingModel.class);
                        if (!listTrain.contains(trainings) && !Training_storage.getTrainingDayList().contains(trainings)){
                            listTrain.add(trainings);
                        }
                }
                Training_storage.getTrainingDayList().clear();
                Training_storage.getTrainingDayList().addAll(listTrain);
            }
        });
    }
}
