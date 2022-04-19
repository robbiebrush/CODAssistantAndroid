package com.example.codassistant.ViewPagers.RosterPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codassistant.MainActivity;
import com.example.codassistant.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPager2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPager2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PIC = "pic";
    private static final String ARG_NAME = "name";
    private static final String ARG_ROSTER = "roster";

    // TODO: Rename and change types of parameters
    private String pic;
    private String name;
    private String roster;
    /*Bitmap picB;*/

    /**
     * Instantiates a new View page fragment.
     */
    public ViewPager2Fragment() {
        // Required empty public constructor
    }

    /**
     * New instance view page fragment.
     *
     * @param pic  the pic
     * @param name the name
     * @return the view page fragment
     */
// TODO: Rename and change types and number of parameters
    public static ViewPager2Fragment newInstance(String pic, String name, String roster) {
        ViewPager2Fragment fragment = new ViewPager2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PIC, pic);
        args.putString(ARG_NAME, name);
        args.putString(ARG_ROSTER, roster);
        fragment.setArguments(args);
        return fragment;
    }

    /*
    public static ViewPager2Fragment newInstance(Bitmap picIn, String name, String roster) {
        ViewPager2Fragment fragment = new ViewPager2Fragment();
        Bundle args = new Bundle();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        picIn.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] picBytes = stream.toByteArray();

        args.putByteArray(ARG_PIC, picBytes);
        args.putString(ARG_NAME, name);
        args.putString(ARG_ROSTER, roster);
        fragment.setArguments(args);
        return fragment;
    }
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*
            byte[] picIn = getArguments().getByteArray(ARG_PIC);
            Bitmap image = BitmapFactory.decodeByteArray(picIn, 0, picIn.length);

            picB = image;
             */
            pic = getArguments().getString(ARG_PIC);
            name = getArguments().getString(ARG_NAME);
            roster = getArguments().getString(ARG_ROSTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager2, container, false);
        ImageView picView = (ImageView) view.findViewById(R.id.teamPic);
        TextView nameTextView = (TextView) view.findViewById(R.id.teamName);
        TextView rosterTextView = (TextView) view.findViewById(R.id.teamRoster);

        ImageView urlView = (ImageView) view.findViewById(R.id.webURL);
        /*
        if (picB == null) {
            picView.setImageResource(R.drawable.ic_baseline_error_outline_24);
        } else {
            picView.setImageBitmap(picB);
        }
         */
        Picasso.get().load(pic).into(picView);
        nameTextView.setText(name);
        rosterTextView.setText(roster);

        urlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage;
                switch(name) {
                    case "Atlanta FaZe":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Atlanta_FaZe");
                        break;
                    case "Boston Breach":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Boston_Breach");
                        break;
                    case "Florida Mutineers":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Florida_Mutineers");
                        break;
                    case "London Royal Ravens":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/London_Royal_Ravens");
                        break;
                    case "Los Angeles Guerillas":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Los_Angeles_Guerrillas");
                        break;
                    case "Los Angeles Thieves":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Los_Angeles_Thieves");
                        break;
                    case "Minnesota RØKKR":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Minnesota_R%C3%98KKR");
                        break;
                    case "New York Subliners":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/New_York_Subliners");
                        break;
                    case "OpTic Texas":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/OpTic_Texas");
                        break;
                    case "Paris Legion":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Paris_Legion");
                        break;
                    case "Seattle Surge":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Seattle_Surge");
                        break;
                    case "Toronto Ultra":
                        webpage = Uri.parse("https://cod-esports.fandom.com/wiki/Toronto_Ultra");
                        break;
                    default:
                        webpage = Uri.parse("https://callofduty.fandom.com/wiki/Call_of_Duty:_Vanguard");
                        break;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Snackbar.make(getView(), "No app installed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    /*
    public static Bitmap convertImageBack(byte[] logo) throws IOException {
        return BitmapFactory.decodeByteArray(logo,0, logo.length);
    }
     */
}