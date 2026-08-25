package week-2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText t1n;
    EditText t2c;
    EditText t3city;

    Button btnSubmit;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        t1n = findViewById(R.id.editTextName);
        t2c = findViewById(R.id.editTextContact);
        t3city = findViewById(R.id.editTextCity);

        btnSubmit = findViewById(R.id.buttonSubmit);
        textViewDisplay = findViewById(R.id.textViewDisplay);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = t1n.getText().toString().trim();
                String contact = t2c.getText().toString().trim();
                String city = t3city.getText().toString().trim();
                String displayText ="Name: " + name +"\nContact: " + contact +"\nCity: " + city;

                textViewDisplay.setText(displayText);
            }
        });
    }
}
