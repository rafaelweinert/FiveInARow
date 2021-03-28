package com.example.fiveinarow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class GameFinishedFragment extends Fragment {


    public interface FinishInterface {
        void newGameClicked();

        void homeClicked();
    }

    ImageView winnerImage;
    ImageView newGame;
    ImageView home;
    FinishInterface finishInterface;
    int winner;

    public GameFinishedFragment(int winner) {
        this.winner = winner;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_game_finished, container, false);


        newGame = root.findViewById(R.id.newGame);
        home = root.findViewById(R.id.home);
        winnerImage = root.findViewById(R.id.winner);

        if (winnerImage != null) Log.d("TAG", "winner != null");

        newGame.setOnClickListener(view -> {
            finishInterface.newGameClicked();
        });

        home.setOnClickListener(view -> {
            finishInterface.homeClicked();
        });


        showWinner(winner);
        return root;
    }

    public void showWinner(int winner) {
        Log.d("TAG", "showWinner");
        switch (winner) {
            case 0:
                break;
            case 1:
                if (winnerImage != null) winnerImage.setBackgroundResource(R.drawable.boy);
                break;
            case 2:
                if (winnerImage != null) winnerImage.setBackgroundResource(R.drawable.girl);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        finishInterface = (FinishInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        finishInterface = null;
    }
}