package week-5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editName, editAge, editGender;
    Button btnSave;
    TextView textDisplay;

    SharedPreferences sharedPreferences;

    public static final String PREF_NAME = "UserInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editGender = findViewById(R.id.editGender);
        btnSave = findViewById(R.id.btnSave);
        textDisplay = findViewById(R.id.textDisplay);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        loadUserData();

        btnSave.setOnClickListener(v -> {
            saveUserData();
        });
    }

    private void saveUserData() {

        String name = editName.getText().toString();
        String age = editAge.getText().toString();
        String gender = editGender.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Name", name);
        editor.putString("Age", age);
        editor.putString("Gender", gender);

        editor.apply();

        textDisplay.setText(
                "Saved: " + name + ", " + age + ", " + gender
        );
    }

    private void loadUserData() {

        String name = sharedPreferences.getString("Name", "Not set");
        String age = sharedPreferences.getString("Age", "Not set");
        String gender = sharedPreferences.getString("Gender", "Not set");

        textDisplay.setText(
                "Loaded: " + name + ", " + age + ", " + gender
        );
    }
}
