package studios.kundby.fitnessapp.ui.exercises;
/**
 * @author Anders & Osvald
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import studios.kundby.fitnessapp.YoutubePlayerConfig;
import studios.kundby.fitnessapp.R;
import studios.kundby.fitnessapp.adapter.ExerciseAdapter;
import studios.kundby.fitnessapp.adapter.TrainingProgramAdapter;

public class DetailedExercisesActivity extends YouTubeBaseActivity {

    private TextView headlineView, descriptionView, repsSetsView;
    private YouTubePlayerView youTubePlayerView;

    YouTubePlayer.OnInitializedListener onInitializedListener;

    private String headline, description, videolink, reps, sets;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detailed_exercise);

        headlineView = findViewById(R.id.headline_detailed_exercise);
        descriptionView = findViewById(R.id.description_detailed_exercise);
        repsSetsView = findViewById(R.id.textView_reps_sets);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            headline = intent.getExtras().getString(ExerciseAdapter.headlineKey);
            description = intent.getExtras().getString(ExerciseAdapter.descriptionKey);
            videolink = intent.getExtras().getString(ExerciseAdapter.videolinkKey);
            reps = intent.getExtras().getString(TrainingProgramAdapter.repsKey);
            sets = intent.getExtras().getString(TrainingProgramAdapter.setsKey);
            if (sets != null) {

                repsSetsView.setText(sets + " / " + reps);
            }
            headlineView.setText(headline);
            descriptionView.setText(description);
        }

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(videolink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(YoutubePlayerConfig.API_KEY,onInitializedListener);

    }
}
