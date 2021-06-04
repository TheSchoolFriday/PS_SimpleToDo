package sg.edu.rp.c346.id20046797.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTodo;
    Spinner spin;

    ArrayList<String> alTodo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.editTextInput);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        btnDelete.setEnabled(false);

        lvTodo = findViewById(R.id.listViewItems);

        spin = findViewById(R.id.spinner);

        ArrayAdapter aaTodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etInput.setHint("Type in a new task here");
                        etInput.setText(null);
                        etInput.setInputType(InputType.TYPE_CLASS_TEXT);

                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etInput.setHint("Index of Task (Human Form, 1 - X)");
                        etInput.setText(null);
                        etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etInput.getText().toString().isEmpty())) {
                    String newItem = etInput.getText().toString();
                    alTodo.add(newItem);
                    aaTodo.notifyDataSetChanged();
                    etInput.setText(null);
                    Toast.makeText(MainActivity.this, "Checklist added!", Toast.LENGTH_SHORT).show();
                } else {
                    etInput.setText(null);
                    Toast.makeText(MainActivity.this, "Checklist not added because Field is Empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(alTodo.isEmpty())) {
                    if (!(etInput.getText().toString().isEmpty())) {
                        int position = Integer.parseInt(etInput.getText().toString()) - 1;
                        if (!(position >= alTodo.size())) {
                            Toast.makeText(MainActivity.this, "Removed: " + alTodo.get(position), Toast.LENGTH_SHORT).show();
                            alTodo.remove(position);
                            aaTodo.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(alTodo.isEmpty())) {
                    alTodo.clear();
                    aaTodo.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}