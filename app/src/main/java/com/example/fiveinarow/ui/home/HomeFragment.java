package com.example.fiveinarow.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiveinarow.R;


public class HomeFragment extends Fragment {

    private final String TAG = this.getClass().toString();

    public interface StartGame {
        void startGame(int i);
    }

    private HomeViewModel homeViewModel;

    private StartGame startGame;

    private ImageView onePlayer;
    private ImageView twoPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        onePlayer = root.findViewById(R.id.circle1);
        twoPlayer = root.findViewById(R.id.circle2);

        onePlayer.setOnClickListener((view) -> {
            Log.d(TAG, "onePlayerClicked");
            //startGame.startGame(1);
            Toast.makeText(getContext(), "not yet implemented!", Toast.LENGTH_SHORT).show();
        });

        twoPlayer.setOnClickListener(view -> {
            Log.d(TAG, "twoPlayerClicked");
            startGame.startGame(2);
        });
        return root;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        startGame = (StartGame) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        startGame = null;
    }
}