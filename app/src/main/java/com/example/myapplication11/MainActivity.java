package com.example.myapplication11;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText xInput, aInput, bInput;
    private TextView resultText;
    private CheckBox checkBox;
    private RadioButton radioButton1;
    private Button calculateButton;
    private com.example.myapplication11.GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получение ссылок на элементы интерфейса
        graphView = findViewById(R.id.graph_view);
        xInput = findViewById(R.id.x_input);
        aInput = findViewById(R.id.a_input);
        bInput = findViewById(R.id.b_input);
        resultText = findViewById(R.id.result_text);
        checkBox = findViewById(R.id.check_box);
        radioButton1 = findViewById(R.id.radio_button1);
        calculateButton = findViewById(R.id.calculate_button);
        graphView.setVisibility(View.GONE);

        // Обработка нажатия на кнопку "Calculate"
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                graphView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void calculate() {
        // Получение введенных пользователем значений
        double x = Double.parseDouble(xInput.getText().toString());
        double a = Double.parseDouble(aInput.getText().toString());
        double b = Double.parseDouble(bInput.getText().toString());

        // Проверка на недопустимость ввода данных
        if (x <= 0 || a <= 0 || b <= 0) {
            Toast.makeText(this, "Введите допустимые значения", Toast.LENGTH_SHORT).show();
            return;
        }

        // Вычисление результата функции
        double y = (3 * Math.pow(x, 2) - 7 * a) * b;

        // Проверка на деление на 0
        if (checkBox.isChecked() && y == 0) {
            Toast.makeText(this, "Деление на 0 недопустимо", Toast.LENGTH_SHORT).show();
            return;
        }

         //2Применение выбранной операции

        if (radioButton1.isChecked())
        {
            y = Math.pow(y, 2);
        }

        // Отображение результата
        resultText.setText(String.format("Результат: %.2f", y));

        graphView.updateGraph(x,a,b);

    }

}
