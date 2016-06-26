package com.prodeveloper.jerome.bataillenavale;

import android.graphics.Color;
import android.opengl.Visibility;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Observable;
import java.util.Observer;

public class GameActivity extends AppCompatActivity implements Observer {
    private BoardTableLayout m_board_j1;
    private BoardTableLayout m_board_j2;

    private GameState m_gameState;
    private ViewSwitcher switcher;
    private TextView m_infoTxtView;

    private Button button3;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button3 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button2);

        m_board_j1 = new BoardTableLayout((TableLayout) findViewById(R.id.game_table_row_j1), true, button3);
        m_board_j1.register(GameActivity.this);
        m_board_j2 = new BoardTableLayout((TableLayout) findViewById(R.id.game_table_row_j2), false, button2);
        m_board_j2.register(GameActivity.this);

        m_gameState = GameState.DROP_BOAT_J1;
        m_board_j1.setDropBoatListener(4);
        m_board_j2.setDropBoatListener(4);

        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        switcher.setInAnimation(getApplicationContext(), R.anim.slide_down);
        switcher.setOutAnimation(getApplicationContext(), R.anim.slide_up);
        switcher.setKeepScreenOn(true);
        findViewById(R.id.transition_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.showNext();
            }
        });

        m_infoTxtView = (TextView) findViewById(R.id.textView);

        ActionBar mActionBar = getSupportActionBar();
        //mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setLogo(R.drawable.carrot_icon);
        //mActionBar.setDisplayUseLogoEnabled(true);
        mActionBar.setTitle(R.string.app_name);
        //mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void onResume() {
        super.onResume();

        switch(m_gameState) {
            case DROP_BOAT_J1 :
                m_infoTxtView.setText("Le joueur 1 place ses fruits\n"
                        + "Le joueur 2 ne doit pas regarder !\n"
                        + "carotte  x 4          pomme  x 3\n"
                        + "citron    x 2          fraise      x 1");
                button3.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.GONE);
                m_board_j1.getTable().setVisibility(View.VISIBLE);
                break;
            case DROP_BOAT_J2:
                button3.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                m_board_j1.getTable().setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.VISIBLE);
                break;
            case J1_PLAYING:
                // lancer la vue de la partie

                break;
            case J2_PLAYING:

                break;
            case GAME_ENDED:
                // Lancer la vue fin de partie

                break;
            default:
                // NE DOIT PAS PASSER LA !
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        Object[] dataT = null;
        if(data instanceof Object[]){
            dataT = ((Object[]) data);

            if(((Object[]) data)[1] != null
                    && ((Object[]) data)[1] instanceof GameState) {
                m_gameState = (GameState) ((Object[]) data)[1];
            }
        }

        switch(m_gameState) {
            case DROP_BOAT_J1:
                m_infoTxtView.setText("Le joueur 1 place ses fruits\n"
                                + "Le joueur 2 ne doit pas regarder !\n"
                        + "carotte  x 4          pomme  x 3\n"
                        + "citron    x 2          fraise      x 1");
                switcher.showNext();
                button3.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.GONE);
                m_board_j1.getTable().setVisibility(View.VISIBLE);
                break;
            case DROP_BOAT_J2:
                m_infoTxtView.setText("Le joueur 2 place ses fruits\n"
                        + "Le joueur 1 ne doit pas regarder !\n"
                        + "carotte  x 4          pomme  x 3\n"
                        + "citron    x 2          fraise      x 1");
                switcher.showNext();
                button3.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                m_board_j1.getTable().setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.VISIBLE);
                break;
            case J1_PLAYING:
                m_infoTxtView.setText("Au tour du joueur 1 de trouver un fruit\n"
                        + "carotte  x 4          pomme  x 3\n"
                        + "citron    x 2          fraise      x 1");
                switcher.showNext();
                button3.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                if((boolean)dataT[0]) {
                    Log.d("GameActivity", "Game Started");
                    m_board_j1.setStartGameListener();
                    m_board_j2.setStartGameListener();
                }
                Log.d("GameActivity", "Passe dans le J1_PLAYING");
                m_board_j1.getTable().setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.VISIBLE);
                m_board_j2.reactiveButton();
                m_board_j1.reactiveButton();
                // Changer les images des boutons et les listeners
                break;
            case J2_PLAYING:
                m_infoTxtView.setText("Au tour du joueur 2 de trouver un fruit\n"
                        + "carotte  x 4          pomme  x 3\n"
                        + "citron    x 2          fraise      x 1");
                switcher.showNext();
                Log.d("GameActivity", "Passe dans le J2_PLAYING");
                button3.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                m_board_j2.getTable().setVisibility(View.GONE);
                m_board_j1.getTable().setVisibility(View.VISIBLE);
                m_board_j1.reactiveButton();
                m_board_j2.reactiveButton();
                break;
            case GAME_ENDED:
                ImageView imageVictory = (ImageView) findViewById(R.id.transition_image);
                if((boolean)dataT[0]) {
                    imageVictory.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.victoryj1));
                } else {
                    imageVictory.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.victoryj2));
                }
                switcher.showNext();
            default:
                break;
        }
    }
}
