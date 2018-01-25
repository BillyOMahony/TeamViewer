package com.example.billy.teamviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdditionalInfoActivity extends AppCompatActivity {

    TextView a_PlayerName;
    TextView a_PlayerNumber;
    TextView a_PlayerPosition;

    TextView a_DateOfBirth;
    TextView a_ClubJoinDate;
    TextView a_Nationality;

    Button statsButton;

    ImageView a_ImageView;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);

        a_PlayerName = (TextView) findViewById(R.id.a_PlayerName);
        a_PlayerNumber = (TextView) findViewById(R.id.a_PlayerNumber);
        a_PlayerPosition = (TextView) findViewById(R.id.a_PlayerPosition);
        a_DateOfBirth = (TextView) findViewById(R.id.a_DateOfBirth);
        a_ClubJoinDate = (TextView) findViewById(R.id.a_ClubJoinDate);
        a_Nationality = (TextView) findViewById(R.id.a_Nationality);

        a_ImageView = (ImageView) findViewById(R.id.a_ImgView);

        statsButton = (Button) findViewById(R.id.statsButton);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        player = (Player) bundle.getSerializable("data");

        a_PlayerName.setText(player.getFirstName() + " " + player.getLastName());
        a_PlayerNumber.setText(player.getNumber());
        a_PlayerPosition.setText(player.getPosition());
        a_DateOfBirth.setText(player.getDOB());
        a_ClubJoinDate.setText(player.getCJD());
        a_Nationality.setText(player.getNationality());

        String imageName = player.getImage();
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        a_ImageView.setImageResource(resID);

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(AdditionalInfoActivity.this, WikipediaInformationActivity.class);
                Bundle bundle = new Bundle();

                // populate the bundle with data
                bundle.putSerializable("data", player);
                newIntent.putExtras(bundle);

                // start activity
                startActivity(newIntent);
            }
        });

    }
}
