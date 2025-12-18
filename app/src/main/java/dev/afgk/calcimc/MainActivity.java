package dev.afgk.calcimc;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.afgk.calcimc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.calcularButton.setOnClickListener(v -> {
            Log.d("MainActivity", "Botão clicado");

            var pesoStr = binding.peso.getText().toString();
            var alturaStr = binding.altura.getText().toString();

            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                Toast toast = new Toast(this);
                toast.setText("Preencha todos os campos");
                toast.show();

                return;
            }

            var peso = Float.parseFloat(pesoStr);
            var altura = Float.parseFloat(alturaStr);

            var imc = peso / (altura * altura);
            var resultado = "";

            if (imc < 18.5f) {
                resultado = "Magreza 0";
            } else if (imc > 18.5f && imc < 24.9f) {
                resultado = "Normal 0";
            } else if (imc > 25f && imc < 29.9f) {
                resultado = "Sobrepeso I";
            } else if (imc > 30f && imc < 39.9f) {
                resultado = "Obesidade II";
            } else if (imc > 40f) {
                resultado = "Obesidade Grave III";
            }

            binding.resultado.setText(resultado + " ("  + imc + ")");
        });

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}