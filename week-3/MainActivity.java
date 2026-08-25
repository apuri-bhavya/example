package week-3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listViewNames;

    String[] names = {"Alice","Bob","Charlie","David"};

    String[] details = {
        "Alice - Android Developer from NYC",
        "Bob - Data Scientist from LA",
        "Charlie - UX Designer from Chicago",
        "David - QA Engineer from Seattle"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listViewNames = findViewById(R.id.listViewNames);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);

        listViewNames.setAdapter(adapter);

        listViewNames.setOnItemClickListener((parent, view, position, id) -> {

                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    intent.putExtra("details", details[position]);
                    startActivity(intent);
                }
        );
    }
}
