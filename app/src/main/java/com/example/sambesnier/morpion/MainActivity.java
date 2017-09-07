package com.example.sambesnier.morpion;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean visible;
    ViewGroup transitionsContainer;
    ViewGroup transitionsContainerBg;
    LinearLayout bg;
    TextView result;
    ImageView btn1;
    ImageView btn2;
    ImageView btn3;

    ImageView btn4;
    ImageView btn5;
    ImageView btn6;

    ImageView btn7;
    ImageView btn8;
    ImageView btn9;

    Gameplay gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gm = new Gameplay();

        transitionsContainer = (ViewGroup) findViewById(R.id.main_container);
        transitionsContainerBg = (ViewGroup) findViewById(R.id.backgroundLayout);

        bg = (LinearLayout) transitionsContainerBg.findViewById(R.id.backgroundLayout);
        result = (TextView) transitionsContainer.findViewById(R.id.resultText);

        btn1 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn1);
        btn2 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn2);
        btn3 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn3);

        btn4 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn4);
        btn5 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn5);
        btn6 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn6);

        btn7 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn7);
        btn8 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn8);
        btn9 = (ImageView) transitionsContainer.findViewById(R.id.icon_btn9);

        Typeface pacifico = Typeface.createFromAsset( getAssets(), "Pacifico.ttf" );
        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(pacifico);
    }

    public void newGame(View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);

        btn1.setBackgroundColor(Color.TRANSPARENT); btn1.setLayoutParams(params);
        btn2.setBackgroundColor(Color.TRANSPARENT); btn2.setLayoutParams(params);
        btn3.setBackgroundColor(Color.TRANSPARENT); btn3.setLayoutParams(params);

        btn4.setBackgroundColor(Color.TRANSPARENT); btn4.setLayoutParams(params);
        btn5.setBackgroundColor(Color.TRANSPARENT); btn5.setLayoutParams(params);
        btn6.setBackgroundColor(Color.TRANSPARENT); btn6.setLayoutParams(params);

        btn7.setBackgroundColor(Color.TRANSPARENT); btn7.setLayoutParams(params);
        btn8.setBackgroundColor(Color.TRANSPARENT); btn8.setLayoutParams(params);
        btn9.setBackgroundColor(Color.TRANSPARENT); btn9.setLayoutParams(params);

        ImageView logo = (ImageView) transitionsContainer.findViewById(R.id.logo);
        TextView title = (TextView) transitionsContainer.findViewById(R.id.title);
        Button newBtn = (Button) transitionsContainer.findViewById(R.id.newBtn);
        Button retryBtn = (Button) transitionsContainer.findViewById(R.id.retryBtn);
        GridLayout plateau = (GridLayout) transitionsContainer.findViewById(R.id.plateau);

        TransitionManager.beginDelayedTransition(transitionsContainer);
        visible = !visible;
        logo.setVisibility(visible ? View.GONE : View.VISIBLE);
        title.setVisibility(visible ? View.VISIBLE : View.GONE);
        result.setVisibility(!visible ? View.VISIBLE : View.GONE);
        newBtn.setVisibility(visible ? View.GONE : View.VISIBLE);
        retryBtn.setVisibility(visible ? View.GONE : View.VISIBLE);
        plateau.setVisibility(visible ? View.VISIBLE : View.GONE);
        bg.setBackgroundColor(visible ? getResources().getColor(R.color.colorPlayer1) : getResources().getColor(R.color.colorPlayer2));

        gm.newGame();
    }

    public void addMarker(int btnPos, ImageView btn) {

        int x;
        int y;
        if (btnPos < 3) {
            x = btnPos;
            y = 0;
        } else if (btnPos > 2 && btnPos < 6) {
            x = btnPos - 3;
            y = 1;
        } else {
            x = btnPos - 6;
            y = 2;
        }

        if (!gm.isFinished()) {
            TransitionManager.beginDelayedTransition(transitionsContainerBg, new Recolor());
            TransitionManager.beginDelayedTransition(transitionsContainer);
            if (gm.isPlayerA()) {
                if (!gm.isOccuped(x, y)) {
                    btn.setBackground(getDrawable(R.drawable.ic_circle));
                    btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    bg.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPlayer2)));
                    gm.setValue(x, y);
                    gm.setPlayerA(!gm.isPlayerA());
                }
            } else {
                if (!gm.isOccuped(x, y)) {
                    btn.setBackground(getDrawable(R.drawable.ic_cross));
                    btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    bg.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPlayer1)));
                    gm.setValue(x, y);
                    gm.setPlayerA(!gm.isPlayerA());
                }
            }
            if (gm.checkMap()) {
                if (gm.isPlayerA()) {
                    // Les croix gagnent
                    result.setText("Les croix remportent la partie");
                } else if (!gm.isPlayerA()){
                    // Les cercles gagnent
                    result.setText("Les cercles remportent la partie");
                }
                if (gm.getTours() == 9) {
                    result.setText("Match nul");
                }
                gm.setFinished(true);
            }
        }
        if (gm.isFinished()) {
            //minimizeMap();
            reinitDisplay();
        }
    }

    private void minimizeMap() {
        RelativeLayout case1 = (RelativeLayout) findViewById(R.id.case1);
        RelativeLayout case2 = (RelativeLayout) findViewById(R.id.case2);
        RelativeLayout case3 = (RelativeLayout) findViewById(R.id.case3);

        RelativeLayout case4 = (RelativeLayout) findViewById(R.id.case4);
        RelativeLayout case5 = (RelativeLayout) findViewById(R.id.case5);
        RelativeLayout case6 = (RelativeLayout) findViewById(R.id.case6);

        RelativeLayout case7 = (RelativeLayout) findViewById(R.id.case7);
        RelativeLayout case8 = (RelativeLayout) findViewById(R.id.case8);
        RelativeLayout case9 = (RelativeLayout) findViewById(R.id.case9);

        GridLayout plateau = (GridLayout) findViewById(R.id.plateau);
        plateau.setPivotX(0);
        plateau.setPivotY(0);
        Log.d("rtest", "x : " + plateau.getPivotX() + " y : " + plateau.getPivotY());

        ArrayList<View> cases = new ArrayList<>();
        /*cases.add(case1);
        cases.add(case2);
        cases.add(case3);
        cases.add(case4);
        cases.add(case5);
        cases.add(case6);
        cases.add(case7);
        cases.add(case8);
        cases.add(case9);*/
        cases.add(plateau);

        scaleView(cases, 1.0f, 0.7f);
    }

    public void scaleView(ArrayList<View> v, float startScale, float endScale) {
        ScaleAnimation scale = new ScaleAnimation((float)startScale, (float)endScale, (float)startScale, (float)endScale);
        scale.setFillAfter(true);
        scale.setDuration(1000);
        for (int i = 0; i < v.size(); i++) {
            v.get(i).startAnimation(scale);
        }
    }

    private void reinitDisplay() {
        ImageView logo = (ImageView) transitionsContainer.findViewById(R.id.logo);
        TextView title = (TextView) transitionsContainer.findViewById(R.id.title);
        Button newBtn = (Button) transitionsContainer.findViewById(R.id.retryBtn);
        GridLayout plateau = (GridLayout) transitionsContainer.findViewById(R.id.plateau);

        TransitionManager.beginDelayedTransition(transitionsContainer);

        visible = !visible;

        //title.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
        newBtn.setVisibility(View.VISIBLE);
        //plateau.setVisibility(View.GONE);
        bg.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void btn1(View v) {
        addMarker(0, btn1);
    }

    public void btn2(View v) {
        addMarker(1, btn2);
    }

    public void btn3(View v) {
        addMarker(2, btn3);
    }

    public void btn4(View v) {
        addMarker(3, btn4);
    }

    public void btn5(View v) {
        addMarker(4, btn5);
    }

    public void btn6(View v) {
        addMarker(5, btn6);
    }

    public void btn7(View v) {
        addMarker(6, btn7);
    }

    public void btn8(View v) {
        addMarker(7, btn8);
    }

    public void btn9(View v) {
        addMarker(8, btn9);
    }
}
