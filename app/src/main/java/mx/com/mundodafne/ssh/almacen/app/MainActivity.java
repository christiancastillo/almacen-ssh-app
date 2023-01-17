package mx.com.mundodafne.ssh.almacen.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.com.mundodafne.ssh.almacen.app.ui.BusquedaMedicamentoActivity;

public class MainActivity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_medicamento);

        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener((View v) -> {
            MainActivity.this.startActivity(new Intent(MainActivity.this, BusquedaMedicamentoActivity.class));
        });
    }
}