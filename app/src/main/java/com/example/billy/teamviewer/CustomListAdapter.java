package com.example.billy.teamviewer;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Billy on 20/11/2017.
 */
public class CustomListAdapter extends ArrayAdapter<Player> {

    Player[] players;
    Activity context;

    public CustomListAdapter(Activity context, Player[] players){
        super(context, R.layout.customliststyle, players);

        this.context = context;
        this.players = players;

        Log.i("PLAYERS LENGTH", players.length + "");

    }

    public View getView(int i, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.customliststyle, null, true);

        TextView titleTV = (TextView) rowView.findViewById(R.id.titleList);
        TextView subTV = (TextView) rowView.findViewById(R.id.subTitleList);
        ImageView IV = (ImageView) rowView.findViewById(R.id.imageViewList);

        titleTV.setText(players[i].getLastName());
        subTV.setText(players[i].getPosition());

        int resID = context.getResources().getIdentifier(players[i].getImage(), "drawable", context.getPackageName());

        IV.setImageResource(resID);

        return rowView;
    }
}
