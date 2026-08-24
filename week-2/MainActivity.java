package week-2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextContact;
    EditText editTextCity;

    Button buttonSubmit;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        editTextCity = findViewById(R.id.editTextCity);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewDisplay = findViewById(R.id.textViewDisplay);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String contact = editTextContact.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();
                String displayText ="Name: " + name +"\nContact: " + contact +"\nCity: " + city;

                textViewDisplay.setText(displayText);
            }
        });
    }
}
