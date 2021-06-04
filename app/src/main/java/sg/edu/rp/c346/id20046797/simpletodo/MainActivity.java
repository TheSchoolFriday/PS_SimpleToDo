package sg.edu.rp.c346.id20046797.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnAdd, btnClear;
    ListView lvTodo;

    ArrayList<String> alTodo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.editTextInput);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);

        lvTodo = findViewById(R.id.listViewItems);

        ArrayAdapter aaTodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = etInput.getText().toString();
                alTodo.add(newItem);
                aaTodo.notifyDataSetChanged();
                etInput.setText(null);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });
    }
}