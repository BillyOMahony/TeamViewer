package com.example.billy.teamviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BasicInfoActivity extends AppCompatActivity {

    TextView bNameTextView;
    TextView bPosTextView;
    TextView bNumTextView;
    ImageView bImgView;

    Button bMoreInfoButton;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        // wire widgets
        bNameTextView = (TextView) findViewById(R.id.b_NameTV);
        bNumTextView = (TextView) findViewById(R.id.b_NumTV);
        bPosTextView = (TextView) findViewById(R.id.b_PosTV);
        bImgView = (ImageView) findViewById(R.id.b_ImgView);

        bMoreInfoButton = (Button) findViewById(R.id.b_MoreInfoButton);

        // Get player data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        player = (Player) bundle.getSerializable("data");

        // Get image resource
        String imageName = player.getImage();

        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        // Set data
        bNameTextView.setText(player.getFirstName() + " " + player.getLastName());
        bNumTextView.setText("No. " + player.getNumber());
        bPosTextView.setText(player.getPosition() + " (" + player.getSide() + ")");

        bImgView.setImageResource(resID);

        // Proceed to next screen
        bMoreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(BasicInfoActivity.this, AdditionalInfoActivity.class);
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
