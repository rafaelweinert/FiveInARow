package com.example.fiveinarow.ui.GameTwo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.fiveinarow.R;

import static android.content.ContentValues.TAG;

public class GameTwoFragment extends Fragment {


    public interface GTInterface {
        void buttonClicked(int row, int column);

        void closeClicked();

        void backClicked();
    }

    GTInterface gtInterface;

    ImageButton[][] buttons;
    int buttonIndex = 0;
    GridLayout gridLayout;

    LayoutParams layoutParams;

    ImageView backButton;
    ImageView closeButton;
    ImageView arrowLeft;
    ImageView arrowRight;

    float scale;
    int pixels;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gametwo, container, false);

        scale = getContext().getResources().getDisplayMetrics().density;
        pixels = (int) (28.46 * scale + 0.5f);

        arrowLeft = root.findViewById(R.id.arrowleft);
        arrowRight = root.findViewById(R.id.arrowright);
        backButton = root.findViewById(R.id.back);
        closeButton = root.findViewById(R.id.close);


        gridLayout = root.findViewById(R.id.gridLayout);

        layoutParams = new LayoutParams();
        layoutParams.width = pixels;
        layoutParams.height = pixels;

        buttons = new ImageButton[13][13];

        //create Buttons in double for-loop
        for (int row = 0; row < buttons.length; row++) {
            for (int column = 0; column < buttons.length; column++) {

                buttons[row][column] = new ImageButton(getContext());
                buttons[row][column].setMinimumHeight(pixels);
                buttons[row][column].setMinimumHeight(pixels);
                buttons[row][column].setMaxWidth(pixels);
                buttons[row][column].setMinimumWidth(pixels);

                //buttons[row][column].setLayoutParams(layoutParams);
                buttons[row][column].setBackgroundColor(getResources().getColor(R.color.transparent));

                Log.d(TAG, "button[" + column + "][" + row + "] created");

                if (buttons[row][column].getParent() != null) {
                    ((ViewGroup) buttons[row][column].getParent()).removeView(buttons[row][column]);

                }
                gridLayout.addView(buttons[row][column], buttonIndex);
                Log.d(TAG, "button[" + column + "][" + row + "] added to grid");
                buttonIndex++;

                buttons[row][column].setOnClickListener(new ButtonOnClickListener(row, column));

                closeButton.setOnClickListener(view -> gtInterface.closeClicked());
                backButton.setOnClickListener(view -> gtInterface.backClicked());

                backButton.setVisibility(View.GONE);
            }
        }

        changeTurn(false);
        return root;
    }

    public boolean addBall(boolean color, int row, int column) {

        //color = 1 -> red = boy
        if (color) {
            //buttons[row][column].setScaleType(ImageView.ScaleType.FIT_CENTER);
            //buttons[row][column].setAdjustViewBounds(true);
            buttons[row][column].setBackgroundResource(R.drawable.red_dot);
            //buttons[row][column].setScaleType(ImageView.ScaleType.FIT_XY);

        }
        //color = 0 -> black = girl
        else {
            buttons[row][column].setBackgroundResource(R.drawable.black_dot);
        }
        //buttons[row][column].setVisibility(View.VISIBLE);
        buttons[row][column].getLayoutParams().height = pixels;
        buttons[row][column].getLayoutParams().width = pixels;
        return true;
    }

    public boolean changeTurn(boolean b) {

        //if b -> boys turn
        if (b) {
            arrowLeft.setVisibility(View.INVISIBLE);
            arrowRight.setVisibility(View.VISIBLE);
        }
        //not b -> girls turn
        else {
            arrowLeft.setVisibility(View.VISIBLE);
            arrowRight.setVisibility(View.INVISIBLE);
        }
        return true;
    }

    public void finishGame(int winner) {
        for (int row = 0; row < buttons.length; row++) {
            for (int column = 0; column < buttons.length; column++) {
                buttons[row][column].setClickable(false);
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        gtInterface = (GTInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        gtInterface = null;
    }

    public class ButtonOnClickListener implements OnClickListener {

        int column;
        int row;

        public ButtonOnClickListener(int row, int column) {
            this.column = column;
            this.row = row;
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Button clicked");
            buttons[row][column].setClickable(false);
            gtInterface.buttonClicked(row, column);
        }
    }

}

