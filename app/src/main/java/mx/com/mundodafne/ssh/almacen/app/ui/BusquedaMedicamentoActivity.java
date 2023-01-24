package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.MEDICAMENTO_DTO_PARAM;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.business.BusquedaMedicamentoBusinessImpl;
import mx.com.mundodafne.ssh.almacen.app.dto.BuscarMedicamentoDTO;
import mx.com.mundodafne.ssh.almacen.app.dto.UnidadesSSHAlmacenDTO;
import mx.com.mundodafne.ssh.almacen.app.pojo.Medicamento;
import mx.com.mundodafne.ssh.almacen.app.pojo.UnidadesSSHAlmacen;
import mx.com.mundodafne.ssh.almacen.app.utils.Utils;

public class BusquedaMedicamentoActivity extends AppCompatActivity {

    private Button buttonBusquedaMedicamento;
    private BusquedaMedicamentoBusinessImpl busquedaMedicamentoBusiness = new BusquedaMedicamentoBusinessImpl();
    private BuscarMedicamentoDTO buscarMedicamentoDTO = null;
    private TextInputEditText cantidadEditText;
    private TextInputEditText unidadDeMedidaEditText;
    private TextInputEditText claveMedicamentoEditText;
    private TextInputEditText descripcionTextInputEditText;
    private Map<String, Object> hmUnidadesSSH = null;
    private List<Map<String,Object>> listaHmUnidadesSSH = null;
    private UnidadesSSHAlmacenDTO unidadesSSHAlmacenDTO = null;

    protected void setupGUI(){
        cantidadEditText = (TextInputEditText) findViewById(R.id.textinput_et_cantidad);
        DigitsKeyListener dkll = DigitsKeyListener.getInstance("0123456789");
        cantidadEditText.setKeyListener(dkll);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busqueda_medicamento);
            AutoCompleteTextView actvUnidadesSSHAlmacen = (AutoCompleteTextView) findViewById(R.id.actv_unidades_ssh_almacen);
            String json = Utils.parseJson(getApplicationContext(),R.raw.unidades_ssh_almacen_app);
            List<UnidadesSSHAlmacen> listaUnidadesJson = new ArrayList();
            Type listUnidadesSSHAlmacenType = new TypeToken<List<Medicamento>>() { }.getType();
            listaUnidadesJson = new Gson().fromJson(json, listUnidadesSSHAlmacenType);
            String arrayUnidades [] = new String[listaUnidadesJson.size()];
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrayUnidades);
            actvUnidadesSSHAlmacen.setThreshold(1);
            actvUnidadesSSHAlmacen.setAdapter(adapter);
            int i = 0;
            listaHmUnidadesSSH = new ArrayList();
            for (UnidadesSSHAlmacen unidadSSHAlmacen : listaUnidadesJson) {
                hmUnidadesSSH = new HashMap();
                arrayUnidades[i++] = unidadSSHAlmacen.getNombreCS();
                hmUnidadesSSH.put("claveClues",unidadSSHAlmacen.getClaveCLUES());
                hmUnidadesSSH.put("municipio",unidadSSHAlmacen.getMunicipio());
                hmUnidadesSSH.put("nombreCS",unidadSSHAlmacen.getNombreCS());
                listaHmUnidadesSSH.add(hmUnidadesSSH);

            }

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