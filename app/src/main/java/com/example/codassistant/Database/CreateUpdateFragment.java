package com.example.codassistant.Database;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.codassistant.Database.pojos.Match;
import com.example.codassistant.R;

public class CreateUpdateFragment extends Fragment {

    Match match;

    public static final int UPDATE = 1;
    public static final int CREATE = 2;

    public static final String MATCH = "match";
    public static final String ACTION_TYPE = "action_type";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_update, container, false);

        RadioButton tuscan = view.findViewById(R.id.tuscanRadio);
        RadioButton bocage = view.findViewById(R.id.bocageRadio);
        RadioButton ds = view.findViewById(R.id.dsRadio);
        RadioButton gav = view.findViewById(R.id.gavutuRadio);
        RadioButton berlin = view.findViewById(R.id.berlinRadio);

        RadioButton hp = view.findViewById(R.id.hpRadio);
        RadioButton snd = view.findViewById(R.id.sndRadio);
        RadioButton cntrl = view.findViewById(R.id.cntrlRadio);

        EditText teamScore = view.findViewById(R.id.teamPoints);
        EditText oppScore = view.findViewById(R.id.oppPoints);
        EditText elims = view.findViewById(R.id.elimAmt);
        EditText deaths = view.findViewById(R.id.deathAmt);
        EditText obj = view.findViewById(R.id.objAmt);
        Button submit = view.findViewById(R.id.submitButton);
        //if we have a bundle
        if(getArguments() != null){
            //if the user want to update a match
            if(getArguments().getInt(ACTION_TYPE) == UPDATE){
                match = getArguments().getParcelable(MATCH);
                submit.setText("EDIT MATCH");
                if(match != null){
                    if (match.getMap() == "Tuscan") {
                        tuscan.setChecked(true);
                    } else if (match.getMap().equals("Bocage")) {
                        bocage.setChecked(true);
                    } else if (match.getMap().equals("Desert Siege")) {
                        ds.setChecked(true);
                    } else if (match.getMap().equals("Gavutu")) {
                        gav.setChecked(true);
                    } else if (match.getMap().equals("Berlin")) {
                        berlin.setChecked(true);
                    }

                    if (match.getMode().equals("Search and Destroy")) {
                        snd.setChecked(true);
                    } else if (match.getMode().equals("Hardpoint")) {
                        hp.setChecked(true);
                    } else if (match.getMode().equals("Control")) {
                        cntrl.setChecked(true);
                    }

                    teamScore.setText(String.valueOf(match.getTeamScore()));
                    oppScore.setText(String.valueOf(match.getOppScore()));
                    elims.setText(String.valueOf(match.getElims()));
                    deaths.setText(String.valueOf(match.getDeaths()));
                    obj.setText(String.valueOf(match.getObj()));
                }
            }
            else{
                match = new Match();
                submit.setText("ADD MATCH");
            }
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Populate the location object with the values on the screen
                    if (tuscan.isChecked() == true) {
                        match.setMap("Tuscan");
                    } else if (bocage.isChecked() == true) {
                        match.setMap("Bocage");
                    } else if (gav.isChecked() == true) {
                        match.setMap("Gavutu");
                    } else if (ds.isChecked() == true) {
                        match.setMap("Desert Siege");
                    } else if (berlin.isChecked() == true) {
                        match.setMap("Berlin");
                    }

                    if (hp.isChecked() == true) {
                        match.setMode("Hardpoint");
                    } else if (snd.isChecked() == true) {
                        match.setMode("Search and Destroy");
                    } else if (cntrl.isChecked() == true) {
                        match.setMode("Control");
                    }

                    match.setTeamScore(Integer.parseInt(teamScore.getText().toString()));
                    match.setOppScore(Integer.parseInt(oppScore.getText().toString()));
                    match.setElims(Integer.parseInt(elims.getText().toString()));
                    match.setDeaths(Integer.parseInt(deaths.getText().toString()));
                    match.setObj(Integer.parseInt(obj.getText().toString()));
                    //Dismiss the keyboard
                    if (view.requestFocus()) {
                        InputMethodManager imm = (InputMethodManager)
                                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    MatchesDatabase db = new MatchesDatabase(getContext());
                    if(getArguments().getInt(ACTION_TYPE) == UPDATE){
                        db.updateMatch(match);
                    } else if (getArguments().getInt(ACTION_TYPE) == CREATE){
                        db.addMatch(match);
                    }
                    db.close();
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }
        return view;
    }
}