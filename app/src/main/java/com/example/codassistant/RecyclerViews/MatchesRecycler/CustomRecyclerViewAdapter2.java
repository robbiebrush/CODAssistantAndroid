package com.example.codassistant.RecyclerViews.MatchesRecycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codassistant.Database.CreateUpdateFragment;
import com.example.codassistant.Database.databases.MatchesDatabase;
import com.example.codassistant.Database.pojos.Match;
import com.example.codassistant.MainActivity;
import com.example.codassistant.R;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter2 extends RecyclerView.Adapter<CustomRecyclerViewAdapter2.CustomViewHolder>{
    private ArrayList<Match> matches;
    private Context context;

    /**
     * Instantiates a new Custom recycler view adapter.
     *
     * @param matches the matches
     */
    public CustomRecyclerViewAdapter2(ArrayList<Match> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matches_recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.map.setText(match.getMap());
        holder.mode.setText(match.getMode());
        holder.outcome.setText(match.getOutcome() + " " + match.getTeamScore() + "-" + match.getOppScore());

        String plants = context.getString(R.string.plants);
        String sec = context.getString(R.string.secs);
        if (match.getMode().equals("Search and Destroy")) {
            holder.stats.setText(match.getElims() + "/" + match.getDeaths() + "          " + match.getKdRatio() + "KD          " + match.getObj() + " " + plants);
        } else if (match.getMode().equals("Hardpoint")) {
            holder.stats.setText(match.getElims() + "/" + match.getDeaths() + "          " + match.getKdRatio() + "KD          " + match.getObj() + " " + sec);
        } else if (match.getMode().equals("Control")) {
            holder.stats.setText(match.getElims() + "/" + match.getDeaths() + "          " + match.getKdRatio() + "KD          " + match.getObj() + " obj elims");
        }
        holder.editButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                extra.putInt(CreateUpdateFragment.ACTION_TYPE,
                        CreateUpdateFragment.UPDATE);
                extra.putParcelable(CreateUpdateFragment.MATCH,
                        matches.get(holder.getAdapterPosition()));
                Navigation.findNavController(view).
                        navigate(R.id.nav_create_update, extra);
            }
        });
        holder.deleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                MatchesDatabase db = new MatchesDatabase(view.getContext());
                                db.deleteMatch(matches.get(holder.getAdapterPosition()).getId());
                                Navigation.findNavController(view).
                                        navigate(R.id.nav_matches, null);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this match?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(matches != null){
            return matches.size();
        }
        return 0;
    }

    /**
     * The type Custom view holder.
     */
    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView map;

        protected TextView mode;

        protected TextView outcome;

        protected TextView stats;

        protected ImageView editButt;

        protected ImageView deleteButt;

        public CustomViewHolder(@NonNull View itemView) { //recycle_row
            super(itemView);
            this.map = itemView.findViewById(R.id.map);
            this.mode = itemView.findViewById(R.id.mode);
            this.outcome = itemView.findViewById(R.id.outcome);
            this.stats = itemView.findViewById(R.id.stats);
            this.editButt = itemView.findViewById(R.id.editImg);
            this.deleteButt = itemView.findViewById(R.id.deleteImg);

            if (MainActivity.font == 0) {
                this.map.setTextSize(context.getResources().getDimension(R.dimen.title_text) / context.getResources().getDisplayMetrics().density);
                this.mode.setTextSize(context.getResources().getDimension(R.dimen.subtitle_text) / context.getResources().getDisplayMetrics().density);
                this.outcome.setTextSize(context.getResources().getDimension(R.dimen.subtitle_text) / context.getResources().getDisplayMetrics().density);
                this.stats.setTextSize(context.getResources().getDimension(R.dimen.body_text) / context.getResources().getDisplayMetrics().density);
            } else if (MainActivity.font == 1) {
                this.map.setTextSize(context.getResources().getDimension(R.dimen.title_text_large) / context.getResources().getDisplayMetrics().density);
                this.mode.setTextSize(context.getResources().getDimension(R.dimen.subtitle_text_large) / context.getResources().getDisplayMetrics().density);
                this.outcome.setTextSize(context.getResources().getDimension(R.dimen.subtitle_text_large) / context.getResources().getDisplayMetrics().density);
                this.stats.setTextSize(context.getResources().getDimension(R.dimen.body_text_large) / context.getResources().getDisplayMetrics().density);
            }
        }
    }
}