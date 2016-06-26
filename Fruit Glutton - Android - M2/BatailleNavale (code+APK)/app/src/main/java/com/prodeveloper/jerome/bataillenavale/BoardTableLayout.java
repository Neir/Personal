package com.prodeveloper.jerome.bataillenavale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jerome on 27/10/2015.
 */
public class BoardTableLayout extends Observable{
    public static final int row = 6, column = 6;
    private TableLayout m_tableButtons;
    private ImageButton m_buttons[][];
    private Button m_okButton;
    private boolean isJ1;
    private int fruitCpt;
    private boolean gameStarted;

    List<Observer> obs = new ArrayList<Observer>();

    private GameCells cells[][];

    public BoardTableLayout(Context context) {
        this.m_tableButtons = new TableLayout(context);
        initTable();
        fruitCpt = 0;
    }

    public BoardTableLayout(Context context, AttributeSet attrs) {
        this.m_tableButtons = new TableLayout(context);
        initTable();
        fruitCpt = 0;
    }

    public BoardTableLayout(TableLayout table, boolean isJ1, Button okButton) {
        this.m_tableButtons = table;
        this.isJ1 = isJ1;
        initTable();
        fruitCpt = 0;
        this.m_okButton = okButton;
        gameStarted = false;

        class OkListener implements View.OnClickListener{
            private boolean isJ1;
            public OkListener(boolean isJ1) { super(); this.isJ1 = isJ1; }
            @Override
            public void onClick(View v) {
                if(!gameStarted) {
                    if (isJ1) {
                        notifyObs(null, GameState.DROP_BOAT_J2);
                        gameStarted = true;
                    } else {
                        notifyObs(true, GameState.J1_PLAYING);
                        gameStarted = true;
                    }
                } else {
                    if (isJ1) {
                        notifyObs(false, GameState.J1_PLAYING);
                    } else {
                        notifyObs(false, GameState.J2_PLAYING);
                    }
                }
                m_okButton.setEnabled(false);
            }
        }
        m_okButton.setOnClickListener(new OkListener(isJ1));
        m_okButton.setEnabled(false);
    }

    public void initTable() {
        m_tableButtons.removeAllViewsInLayout();
        m_buttons = new ImageButton[row][column];
        cells = new GameCells[row][column];

        for(int i = 0; i<row; i++) {

            TableRow tr = new TableRow(m_tableButtons.getContext());

            TableRow.LayoutParams trParam = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            trParam.height = m_tableButtons.getLayoutParams().height / row;
            //trParam.weight = 1;
            tr.setLayoutParams(trParam);

            for (int j = 0; j < column; j++) {
                m_buttons[i][j] = new ImageButton(m_tableButtons.getContext());
                m_buttons[i][j].setPadding(0, 0, 0, 0);
                Drawable drawable = ContextCompat.getDrawable(m_tableButtons.getContext(), R.drawable.texture_empty);
                //Bitmap srcBmp = BitmapFactory.decodeResource(m_tableButtons.getResources(), R.drawable.clouds_texture);
                //int size = m_tableButtons.getLayoutParams().height / row;
                //Bitmap mybitmap = Bitmap.createBitmap(srcBmp, i*size, j*size, size, size);
                m_buttons[i][j].setImageDrawable(drawable);
                TableRow.LayoutParams l_param = new TableRow.LayoutParams();
                l_param.weight = 1;
                m_buttons[i][j].setBackgroundColor(Color.TRANSPARENT);
                m_buttons[i][j].setAdjustViewBounds(true);
                m_buttons[i][j].setScaleType(ImageView.ScaleType.FIT_CENTER);
                m_buttons[i][j].setLayoutParams(l_param);
                tr.addView(m_buttons[i][j]);
            }


            m_tableButtons.addView(tr);
        }
    }

    public void setDropBoatListener(int boatSize) {
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j] = GameCells.EMPTY;
                DropBoatListener dbLi = new DropBoatListener(boatSize, i, j);
                m_buttons[i][j].setOnClickListener(dbLi);
            }
        }
    }

    public void setStartGameListener() {
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Drawable drawable = ContextCompat.getDrawable(m_tableButtons.getContext(), R.drawable.clouds_big);
                m_buttons[i][j].setImageDrawable(drawable);
                m_buttons[i][j].setClickable(true);
                if (cells[i][j] == GameCells.EMPTY) {
                    cells[i][j] = GameCells.MYSTERY;
                }
                StartGameListener sgLi = new StartGameListener(i, j);
                m_buttons[i][j].setOnClickListener(sgLi);
            }
        }
    }

    public boolean incrAndCheckAllFound(){
        fruitCpt++;

        return (fruitCpt == 4+3+2+1);
    }

    public void register(Observer obs) {
        this.obs.add(obs);
    }

    public void notifyObs(Object data, GameState state) {
        for(Observer o : obs) {
            Object[] dataSent = new Object[2];
            dataSent[0] = data;
            dataSent[1] = state;
            o.update(this, dataSent);
        }
    }

    public TableLayout getTable() {
        return m_tableButtons;
    }

    public void setTable(TableLayout table) {
        this.m_tableButtons = table;
    }

    public ImageButton getButton(int row, int column) {
        return m_buttons[row][column];
    }

    private class DropBoatListener implements View.OnClickListener {
        private int boatSize = 1;
        private int myRow = -1;
        private int myColumn = -1;

        public DropBoatListener(int boatSize, int myRow, int myColumn) {
            super();
            this.boatSize = boatSize;
            this.myRow = myRow;
            this.myColumn = myColumn;
        }

        @Override
        public void onClick(View v) {
            if(boatSize <= 0) {
                m_okButton.setEnabled(true);
                return;
            }

            ImageButton ib = ((ImageButton) v);

            Drawable drawable = ContextCompat.getDrawable(m_tableButtons.getContext(), R.drawable.egidium_icone_big);
            ib.setImageDrawable(drawable);

            //Toast toast = Toast.makeText(m_tableButtons.getContext(), "Tu as cliqué !", Toast.LENGTH_SHORT);
            //toast.show();

            ib.startAnimation(AnimationUtils.loadAnimation(m_tableButtons.getContext(), R.anim.rotate_in));
            ib.setClickable(false);
            for(int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    m_buttons[i][j].setClickable(false);
                    // Rajouter une image grisée.
                }
            }

            int ok;

            Drawable newDrawable = LoadFruit.getDrawable(m_tableButtons.getContext(), boatSize);
            ImageButton newIB;
            if((ok = (myRow+(boatSize-1))) < row && cells[ok][myColumn] == GameCells.EMPTY) {
                newIB = m_buttons[ok][myColumn];
                newIB.setClickable(true);
                newIB.setImageDrawable(newDrawable);
                newIB.setOnClickListener(new DropBoatListener2(boatSize, ok, myColumn, myRow, myColumn));
            }
            if((ok = (myRow-(boatSize-1))) >= 0 && cells[ok][myColumn] == GameCells.EMPTY) {
                newIB = m_buttons[ok][myColumn];
                newIB.setClickable(true);
                newIB.setImageDrawable(newDrawable);
                newIB.setOnClickListener(new DropBoatListener2(boatSize, ok, myColumn, myRow, myColumn));
            }
            if((ok = (myColumn+(boatSize-1))) < column && cells[myRow][ok] == GameCells.EMPTY) {
                newIB = m_buttons[myRow][ok];
                newIB.setClickable(true);
                newIB.setImageDrawable(newDrawable);
                newIB.setOnClickListener(new DropBoatListener2(boatSize, myRow, ok, myRow, myColumn));
            }
            if((ok = (myColumn-(boatSize-1))) >= 0 && cells[myRow][ok] == GameCells.EMPTY) {
                newIB = m_buttons[myRow][ok];
                newIB.setClickable(true);
                newIB.setImageDrawable(newDrawable);
                newIB.setOnClickListener(new DropBoatListener2(boatSize, myRow, ok, myRow, myColumn));
            }
        }
    }


    private class DropBoatListener2 implements View.OnClickListener {
        private int boatSize = 1;
        private int myRow = -1;
        private int myColumn = -1;
        private int startRow = -1;
        private int startColumn = -1;

        public DropBoatListener2(int boatSize, int myRow, int myColumn, int startRow, int startColumn) {
            super();
            this.boatSize = boatSize;
            this.myRow = myRow;
            this.myColumn = myColumn;
            this.startRow = startRow;
            this.startColumn = startColumn;
        }

        @Override
        public void onClick(View v) {
            ImageButton ib = ((ImageButton) v);

            Drawable drawable = LoadFruit.getDrawable(m_tableButtons.getContext(), boatSize);

            if (myRow == startRow) {
                for (int i = Math.min(startColumn, myColumn); i <= Math.max(startColumn, myColumn); i++) {
                    m_buttons[myRow][i].setImageDrawable(drawable);
                    m_buttons[myRow][i].startAnimation(AnimationUtils.loadAnimation(m_tableButtons.getContext(), R.anim.rotate_in));

                    cells[myRow][i] = LoadFruit.getCell(boatSize, true);
                }
            }


            if (myColumn == startColumn) {
                for (int i = Math.min(startRow, myRow); i <= Math.max(startRow, myRow); i++) {
                    m_buttons[i][startColumn].setImageDrawable(drawable);
                    m_buttons[i][startColumn].startAnimation(AnimationUtils.loadAnimation(m_tableButtons.getContext(), R.anim.rotate_in));

                    cells[i][startColumn] = LoadFruit.getCell(boatSize, true);
                }
            }

            if(boatSize-1 <=0) {
                m_okButton.setEnabled(true);
                ib.setClickable(false);
                return;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (cells[i][j] == GameCells.EMPTY) {
                        m_buttons[i][j].setClickable(true);
                        m_buttons[i][j].setOnClickListener(new DropBoatListener(boatSize-1, i, j));
                        m_buttons[i][j].setImageDrawable(ContextCompat.getDrawable(m_tableButtons.getContext(), R.drawable.texture_empty));
                    } else {
                        m_buttons[i][j].setClickable(false);
                    }
                }
            }
        }
    }


    class StartGameListener implements View.OnClickListener {
        private int myRow = -1;
        private int myColumn = -1;

        public StartGameListener(int myRow, int myColumn) {
            super();
            this.myRow = myRow;
            this.myColumn = myColumn;
        }

        @Override
        public void onClick(View v) {

            ImageButton ib = ((ImageButton) v);

            switch (cells[myRow][myColumn]) {
                case MYSTERY:
                    cells[myRow][myColumn] = GameCells.EMPTY;
                    //m_buttons[myRow][myColumn].resolveSize(0,0);//setClickable(false);
                    ib.setImageDrawable(LoadFruit.getDrawable(m_tableButtons.getContext(), 0));
                    Toast.makeText(m_tableButtons.getContext(), "Rien à manger ici !", Toast.LENGTH_SHORT)
                            .show();

                    break;
                case MYST_CARROT:
                    cells[myRow][myColumn] = GameCells.CARROT;
                    ib.setImageDrawable(LoadFruit.getDrawable(m_tableButtons.getContext(), 4));

                    Toast.makeText(m_tableButtons.getContext(), "Tu as trouvé !", Toast.LENGTH_SHORT)
                            .show();

                    if(incrAndCheckAllFound()) {
                        notifyObs(isJ1, GameState.GAME_ENDED);
                        return;
                    }
                    break;
                case MYST_APPLE:
                    cells[myRow][myColumn] = GameCells.APPLE;
                    ib.setImageDrawable(LoadFruit.getDrawable(m_tableButtons.getContext(), 3));

                    Toast.makeText(m_tableButtons.getContext(), "Tu as trouvé !", Toast.LENGTH_SHORT)
                            .show();

                    if(incrAndCheckAllFound()) {
                        notifyObs(isJ1, GameState.GAME_ENDED);
                        return;
                    }
                    break;
                case MYST_LEMON:
                    cells[myRow][myColumn] = GameCells.LEMON;
                    ib.setImageDrawable(LoadFruit.getDrawable(m_tableButtons.getContext(), 2));

                    Toast.makeText(m_tableButtons.getContext(), "Tu as trouvé !", Toast.LENGTH_SHORT)
                            .show();

                    if(incrAndCheckAllFound()) {
                        notifyObs(isJ1, GameState.GAME_ENDED);
                        return;
                    }
                    break;
                case MYST_STRAW:
                    cells[myRow][myColumn] = GameCells.STRAW;
                    ib.setImageDrawable(LoadFruit.getDrawable(m_tableButtons.getContext(), 1));

                    Toast.makeText(m_tableButtons.getContext(), "Tu as trouvé !", Toast.LENGTH_SHORT)
                            .show();

                    if(incrAndCheckAllFound()) {
                        notifyObs(isJ1, GameState.GAME_ENDED);
                        return;
                    }
                    break;
                default:
                    break;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    m_buttons[i][j].setClickable(false);
                }
            }
            ib.setClickable(false);

            m_okButton.setEnabled(true);
        }
    }

    public void reactiveButton() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(cells[i][j] == GameCells.MYSTERY
                        || cells[i][j] == GameCells.MYST_CARROT
                        || cells[i][j] == GameCells.MYST_APPLE
                        || cells[i][j] == GameCells.MYST_LEMON
                        || cells[i][j] == GameCells.MYST_STRAW) {
                    m_buttons[i][j].setClickable(true);
                }
            }
        }
    }
}
