package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.MEDICAMENTO_DTO_PARAM;
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
    private TextInputEditText descripcionTextInputEditText;

    protected void switchActivity(){
        Intent cambiaActivity = new Intent(this, BuscarMedicamentoActivity.class);
        super.startActivity(cambiaActivity);
    }

    protected void setupGUI(){
        cantidadEditText = (TextInputEditText) findViewById(R.id.textinput_et_cantidad);
        DigitsKeyListener dkll = DigitsKeyListener.getInstance("0123456789");
        cantidadEditText.setKeyListener(dkll);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busqueda_medicamento);
            claveMedicamentoEditText = (TextInputEditText) findViewById(R.id.textinput_et_clave);
            unidadDeMedidaEditText = (TextInputEditText) findViewById(R.id.textinput_et_presentacion);
            descripcionTextInputEditText = (TextInputEditText) findViewById(R.id.textinput_et_descripcion);

            if (getIntent().getSerializableExtra(MEDICAMENTO_DTO_PARAM) != null) {
                buscarMedicamentoDTO = (BuscarMedicamentoDTO) getIntent().getSerializableExtra("medicamentoDTO");
                claveMedicamentoEditText.setText(buscarMedicamentoDTO.getClaveMedicamento());
                unidadDeMedidaEditText.setText(buscarMedicamentoDTO.getUnidadDeMedida());
                descripcionTextInputEditText.setText(buscarMedicamentoDTO.getDescripcionMedicamento());
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