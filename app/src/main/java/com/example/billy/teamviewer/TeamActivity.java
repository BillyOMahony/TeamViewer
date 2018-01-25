package com.example.billy.teamviewer;

import android.app.Application;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class TeamActivity extends AppCompatActivity implements View.OnClickListener{

    private Button pitchButton;
    private Button listButton;
    private View pitchScrollView;
    private View listView;

    private ListView playerList;
    private ArrayAdapter<Player> adapter;
    private XMLParserPlayer parser;

    private Player[] players;
    private String[] LastNames;

    private ImageButton GKButton;
    private ImageButton DLButton;
    private ImageButton DCLButton;
    private ImageButton DCRButton;
    private ImageButton DRButton;
    private ImageButton MLButton;
    private ImageButton MCLButton;
    private ImageButton MCRButton;
    private ImageButton MRButton;
    private ImageButton STRLButton;
    private ImageButton STRRButton;

    private ImageButton Sub1;
    private ImageButton Sub2;
    private ImageButton Sub3;
    private ImageButton Sub4;
    private ImageButton Sub5;
    private ImageButton Sub6;
    private ImageButton Sub7;


    // This will be the default onClick, used by PitchView buttons
    @Override
    public void onClick(View view) {
        ImageButton clickedButton = null;
        clickedButton = (ImageButton) findViewById(view.getId());

        // The button's tag contains the name of the player
        String name = (String) clickedButton.getTag();

        Player playerData = parser.getData(name);

        // make an intent and a bundle
        Intent myIntent = new Intent(TeamActivity.this, BasicInfoActivity.class);
        Bundle bundle = new Bundle();

        // populate the bundle with data
        bundle.putSerializable("data", playerData);
        myIntent.putExtras(bundle);

        // start activity
        startActivity(myIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Log.i("A", "B");

        /*
        //////////////////LIST VIEW CODE//////////////////
        */
        // Set up switch buttons and views
        pitchButton = (Button) findViewById(R.id.pitchButton);
        listButton = (Button) findViewById(R.id.listButton);
        pitchScrollView = (View) findViewById(R.id.pitchScrollView);
        listView = (View) findViewById(R.id.listView);


        // Set up XML parser
        playerList = (ListView) findViewById(R.id.playerList);

        parser = new XMLParserPlayer(getApplicationContext());

        LastNames = parser.getLastNames();
        players = parser.getData();

        Log.i("players Length", players.length + "");

        adapter = new CustomListAdapter(this, parser.getData());
        playerList.setAdapter(adapter);

        //Do a thing when a list value is clicked
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // make an intent and a bundle
                Intent myIntent = new Intent(TeamActivity.this, BasicInfoActivity.class);
                Bundle bundle = new Bundle();

                //Get Data for the bundle using XMLParserPlayer
                Player playerData = parser.getData(i);

                // populate the bundle with data
                bundle.putSerializable("data", playerData);
                myIntent.putExtras(bundle);

                // start activity
                startActivity(myIntent);
            }
        });

        //Display List View initially
        listEnabled(true);

        /*
        ///////////////////PITCH VIEW CODE//////////////////
         */
        pitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listEnabled(false);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listEnabled(true);
            }
        });

        // Wire ImageButton widgets
        GKButton = (ImageButton) findViewById(R.id.gkButton);
        DLButton = (ImageButton) findViewById(R.id.dlButton);
        DCLButton = (ImageButton) findViewById(R.id.dclButton);
        DCRButton = (ImageButton) findViewById(R.id.dcrButton);
        DRButton = (ImageButton) findViewById(R.id.drButton);
        MLButton = (ImageButton) findViewById(R.id.mlButton);
        MCLButton = (ImageButton) findViewById(R.id.mclButton);
        MCRButton = (ImageButton) findViewById(R.id.mcrButton);
        MRButton = (ImageButton) findViewById(R.id.mrButton);
        STRLButton = (ImageButton) findViewById(R.id.strlButton);
        STRRButton = (ImageButton) findViewById(R.id.strrButton);

        Sub1 = (ImageButton) findViewById(R.id.subOneButton);
        Sub2 = (ImageButton) findViewById(R.id.subTwoButton);
        Sub3 = (ImageButton) findViewById(R.id.subThreeButton);
        Sub4 = (ImageButton) findViewById(R.id.subFourButton);
        Sub5 = (ImageButton) findViewById(R.id.subFiveButton);
        Sub6 = (ImageButton) findViewById(R.id.subSixButton);
        Sub7 = (ImageButton) findViewById(R.id.subSevenButton);

        SetUpImageButtons();

        // create array of ImageButtons and assign Listener to them
        ImageButton[] imgButtons = {GKButton, DLButton, DCLButton, DCRButton, DRButton, MLButton, MCLButton, MCRButton, MRButton,
                        STRLButton, STRRButton, Sub1, Sub2, Sub3, Sub4, Sub5, Sub6, Sub7};

        for(ImageButton button : imgButtons){
            button.setOnClickListener(this);
        }
    }


    /*
    if listViewActive = true enable list view and disable pitch view
    if listViewActive = false disable list view and enable pitch view

    Also sets text and bg colour, red bg + white text represents active screen
     */
    void listEnabled(boolean listViewActive){
        if(listViewActive){
            pitchButton.setTextColor(getResources().getColor(R.color.liverpoolRed));
            pitchButton.setBackgroundColor(getResources().getColor(R.color.white));

            listButton.setTextColor(getResources().getColor(R.color.white));
            listButton.setBackgroundColor(getResources().getColor(R.color.liverpoolRed));

            pitchScrollView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
        else{
            listButton.setTextColor(getResources().getColor(R.color.liverpoolRed));
            listButton.setBackgroundColor(getResources().getColor(R.color.white));

            pitchButton.setTextColor(getResources().getColor(R.color.white));
            pitchButton.setBackgroundColor(getResources().getColor(R.color.liverpoolRed));

            pitchScrollView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }


    /*
    Assigns tag and image to each image button
    GKButton and Sub1 will be assigned goalkeepers
    D-Buttons and Sub2/Sub3 will be assigned defenders
    M-Buttons and Sub4/5 will be assigned midfielders
    STR-Buttons and Sub 6/7 will be assigned forwards
     */
    void SetUpImageButtons(){

        Player player = null;

        // Deal with GKs
        ImageButton[] gkArr = {GKButton, Sub1};
        SetupPosition("Goalkeeper", gkArr);

        // Deal with Defenders
        ImageButton[] defArr = {DLButton, DCLButton, DCRButton, DRButton, Sub2, Sub3};
        SetupPosition("Defender", defArr);

        // Deal with Midfielders
        ImageButton[] midArr = {MLButton, MCLButton, MCRButton, MRButton, Sub4, Sub5};
        SetupPosition("Midfielder", midArr);

        //Deal with Forwards
        ImageButton[] fwdArr = {STRLButton, STRRButton, Sub6, Sub7};
        SetupPosition("Forward", fwdArr);
    }

    private void SetupPosition(String position, ImageButton[] buttons){
        for(int x = 0; x < buttons.length; x++){
            for(int i = 0; i < players.length; i++){
                if(players[i] != null && players[i].getPosition().equals(position)){
                    Player player = players[i];
                    //this player can no longer be used.
                    players[i] = null;

                    int resID = getResources().getIdentifier(player.getImage() + "button", "drawable", getPackageName());
                    buttons[x].setImageResource(resID);
                    buttons[x].setTag(player.getLastName());
                    break;
                }
            }
        }
    }
}