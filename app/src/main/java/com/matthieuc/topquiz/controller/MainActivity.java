package com.matthieuc.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.matthieuc.topquiz.R;
import com.matthieuc.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetinText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;

    public static final int GAME_ACTIVITY_REQUEST_CODE =42;
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User();
        mPreferences = getPreferences(MODE_PRIVATE);
        mGreetinText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);

        joueurConnu();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mPlayButton.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Récupération du prénom après le clic
                String firstname = mNameInput.getText().toString();
                mUser.setFirstName(firstname);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();

                //Démarrage de l'activité game
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            joueurConnu();
        }
    }

    public void joueurConnu() {
        String fstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

        if (fstname != null) {
            int points = mPreferences.getInt("PREF_KEY_SCORE", 0);
            mGreetinText.setText("Bonjour " + fstname + " votre précédent score est de " + points + " points");
            mNameInput.setText(fstname);
            mNameInput.setSelection(fstname.length());
            mPlayButton.setEnabled(true);
        }
    }
}
