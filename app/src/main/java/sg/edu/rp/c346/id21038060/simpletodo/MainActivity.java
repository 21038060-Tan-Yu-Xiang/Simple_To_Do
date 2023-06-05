package sg.edu.rp.c346.id21038060.simpletodo;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnTask;
    EditText etTask;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTask = findViewById(R.id.spnTask);
        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.lvTask);

        alTasks = new ArrayList<>();

        ArrayAdapter aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTasks);

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setHint("Type in a new task here");
                        etTask.setText("");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        etTask.setText("");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = etTask.getText().toString();

                alTasks.add(alTasks.size(), task);
                aaTasks.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alTasks.size() == 0){
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else if (etTask.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Text field is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    int pos = Integer.parseInt(etTask.getText().toString());

                    if (pos < alTasks.size()){
                        alTasks.remove(pos);
                        aaTasks.notifyDataSetChanged();
                        etTask.setText("");
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
                etTask.setText("");
            }
        });
    }
}