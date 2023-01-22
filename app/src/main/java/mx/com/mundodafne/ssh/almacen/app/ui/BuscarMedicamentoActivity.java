package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.dto.BuscarMedicamentoDTO;
import mx.com.mundodafne.ssh.almacen.app.pojo.Medicamento;
import mx.com.mundodafne.ssh.almacen.app.utils.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuscarMedicamentoActivity extends AppCompatActivity {
    final String[] lista = {"Dafne", "Christian Yamil", "Aleida"};
    List<Medicamento> temporal = new ArrayList();
    List<Medicamento> medicamentos = new ArrayList();
    BuscarMedicamentoDTO buscarMedicamentoDTO = null;
    Map<String, Object> hmMedicamentoSeleccionado = null;
    ArrayAdapter<String> adapter = null;

    protected List<Medicamento> obtieneListaMedicamento(List<Medicamento> lista){
        temporal = lista;
        return temporal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_medicamento);

        String json = Utils.parseJson(getApplicationContext(),R.raw.listado_medicamentos);
        Gson gson = new Gson();
        Type listMedicamentosType = new TypeToken<List<Medicamento>>() { }.getType();
        medicamentos = gson.fromJson(json, listMedicamentosType);
        List<Map<String, Object>> ltMedicamentosSeleccionados = new ArrayList();
        String[] descripcionMedicamentos = new String[medicamentos.size()];
        String rec;
        Button botonBuscarMedicamento = findViewById(R.id.obtener_medicamento_button);
        int i = 0;
        adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, descripcionMedicamentos);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv_descripcion_medicamento);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        buscarMedicamentoDTO = new BuscarMedicamentoDTO();
        TextInputEditText textinputEtClaveBuscarMedicamento = (TextInputEditText) findViewById(R.id.textinput_et_clave_buscar_medicamento);
        TextInputEditText textinputEtPresentacion = (TextInputEditText) findViewById(R.id.textinput_et_presentacion);

        for (Medicamento medicamento: medicamentos) {
            hmMedicamentoSeleccionado = new HashMap();
            descripcionMedicamentos[i] = medicamento.getDescripcionMedicamento();
            hmMedicamentoSeleccionado.put("claveMedicamento",medicamento.getClaveMedicamento());
            hmMedicamentoSeleccionado.put("descripcionMedicamento",medicamento.getDescripcionMedicamento());
            hmMedicamentoSeleccionado.put("unidadDeMedida",medicamento.getUnidadDeMedida());
            ltMedicamentosSeleccionados.add(hmMedicamentoSeleccionado);
            i++;
        }
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String temp = adapterView.getItemAtPosition(i).toString();
                buscarMedicamentoDTO.setDescripcionMedicamento(temp);
                for (Map<String, Object> mapa : ltMedicamentosSeleccionados) {
                    if (mapa.containsValue(temp)) {
                        buscarMedicamentoDTO.setClaveMedicamento((String)mapa.get("claveMedicamento"));
                        buscarMedicamentoDTO.setUnidadDeMedida((String)mapa.get("unidadDeMedida"));
                        break;
                    }
                }
                textinputEtClaveBuscarMedicamento.setText(buscarMedicamentoDTO.getClaveMedicamento());
                textinputEtPresentacion.setText(buscarMedicamentoDTO.getUnidadDeMedida());
            }
        });

        botonBuscarMedicamento.setOnClickListener(view -> {
            Intent switchActivity = new Intent(this, BusquedaMedicamentoActivity.class);
            switchActivity.putExtra("medicamentoDTO",buscarMedicamentoDTO);
            super.startActivity(switchActivity);
        });
    }
}