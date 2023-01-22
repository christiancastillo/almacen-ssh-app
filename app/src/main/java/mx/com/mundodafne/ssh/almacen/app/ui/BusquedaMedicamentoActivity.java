package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.business.BusquedaMedicamentoBusinessImpl;
import mx.com.mundodafne.ssh.almacen.app.dto.BuscarMedicamentoDTO;

public class BusquedaMedicamentoActivity extends AppCompatActivity {

    private Button buttonBusquedaMedicamento;
    private BusquedaMedicamentoBusinessImpl busquedaMedicamentoBusiness = new BusquedaMedicamentoBusinessImpl();
    private BuscarMedicamentoDTO buscarMedicamentoDTO = null;
    private TextInputEditText cantidadEditText;
    private TextInputEditText unidadDeMedidaEditText;
    private TextInputEditText claveMedicamentoEditText;

    protected void switchActivity(){
        Intent cambiaActivity = new Intent(this, BuscarMedicamentoActivity.class);
        super.startActivity(cambiaActivity);
    }

    protected void setupGUI(){
        cantidadEditText = (TextInputEditText) findViewById(R.id.textinput_et_cantidad);
        DigitsKeyListener dkll = DigitsKeyListener.getInstance("0123456789");
        cantidadEditText.setKeyListener(dkll);//"0123456789,")
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busqueda_medicamento);
            claveMedicamentoEditText = (TextInputEditText) findViewById(R.id.textinput_et_clave);

            if (getIntent().getSerializableExtra("medicamentoDTO") != null) {
                buscarMedicamentoDTO = (BuscarMedicamentoDTO) getIntent().getSerializableExtra("medicamentoDTO");
                claveMedicamentoEditText.setText(buscarMedicamentoDTO.getClaveMedicamento());
            }
            buttonBusquedaMedicamento = (Button) findViewById(R.id.buscar_medicamento_button);
            setupGUI();
            buttonBusquedaMedicamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("DEBUG_BUTTON","CLIC AL BOTON");
                    BusquedaMedicamentoActivity.this.startActivity(new Intent(BusquedaMedicamentoActivity.this, BuscarMedicamentoActivity.class));
                }
            });
    }
}