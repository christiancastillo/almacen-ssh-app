package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.business.BusquedaMedicamentoBusinessImpl;
import mx.com.mundodafne.ssh.almacen.app.dto.BuscarMedicamentoDTO;

public class BusquedaMedicamentoActivity extends AppCompatActivity {

    private Button buttonBusquedaMedicamento = null;
    private BusquedaMedicamentoBusinessImpl busquedaMedicamentoBusiness = new BusquedaMedicamentoBusinessImpl();
    private BuscarMedicamentoDTO buscarMedicamentoDTO = null;
    private Button cambiarActivity = null;

    protected void switchActivity(){
        Intent cambiaActivity = new Intent(this, BuscarMedicamentoActivity.class);
        super.startActivity(cambiaActivity);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busqueda_medicamento);
            BusquedaMedicamentoActivity.this.startActivity(new Intent(BusquedaMedicamentoActivity.this, BuscarMedicamentoActivity.class));

            buttonBusquedaMedicamento = (Button) findViewById(R.id.buscar_medicamento_button);
            cambiarActivity = (Button) findViewById(R.id.cambiar_activity_button);
            buttonBusquedaMedicamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("DEBUG_BUTTON","CLIC AL BOTON");
                    //BusquedaMedicamentoActivity.this.startActivity(new Intent(BusquedaMedicamentoActivity.this, BuscarMedicamentoActivity.class));
                    startActivity(new Intent(BusquedaMedicamentoActivity.this,BuscarMedicamentoActivity.class));
                    //switchActivity();
                }
            });

            cambiarActivity.setOnClickListener((View v) -> {
                BusquedaMedicamentoActivity.this.startActivity(new Intent(BusquedaMedicamentoActivity.this, TerceraActivity.class));
            });

            //buscarMedicamentoDTO.setClaveMedicamento("valor recuperado de otra activity");
            //buscarMedicamentoDTO.getClaveMedicamento();
        } catch (Exception e) {
            Log.e("ERROR_TRY","Error en metodo onCreate: ",e);
            Toast.makeText(getApplicationContext(),"ERROR GENERAL...",Toast.LENGTH_SHORT).show();
        }
    }
}