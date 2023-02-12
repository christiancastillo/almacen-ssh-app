package mx.com.mundodafne.ssh.almacen.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.MEDICAMENTO_DTO_PARAM;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.business.BusquedaMedicamentoBusinessImpl;
import mx.com.mundodafne.ssh.almacen.app.dto.BuscarMedicamentoDTO;
import mx.com.mundodafne.ssh.almacen.app.dto.MedicamentoAgregarDTO;
import mx.com.mundodafne.ssh.almacen.app.dto.UnidadesSSHAlmacenDTO;
import mx.com.mundodafne.ssh.almacen.app.pojo.Medicamento;
import mx.com.mundodafne.ssh.almacen.app.pojo.UnidadesSSHAlmacen;
import mx.com.mundodafne.ssh.almacen.app.ui.components.TableViewAdapter;
import mx.com.mundodafne.ssh.almacen.app.utils.Utils;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.STATE_CENTRO_SALUD;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.STATE_CENTRO_SALUD_STORE;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.STATE_DESCRIPCION_MEDICAMENTO;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.FORMATO_FECHA;



public class BusquedaMedicamentoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button buttonBusquedaMedicamento;
    private Button buttonAgregarMedicamento;
    private BusquedaMedicamentoBusinessImpl busquedaMedicamentoBusiness = new BusquedaMedicamentoBusinessImpl();
    private BuscarMedicamentoDTO buscarMedicamentoDTO = null;
    private TextInputEditText cantidadEditText;
    private TextInputEditText unidadDeMedidaEditText;
    private TextInputEditText claveMedicamentoEditText;
    private TextInputEditText descripcionTextInputEditText;
    private TextInputEditText textinputEtCLUES;
    private TextInputEditText textinputEtMunicipio;
    private TextInputEditText textInputETFechaCaducidad;
    private AutoCompleteTextView actvUnidadesSSHAlmacen;
    private RecyclerView recyclerViewLstaMedicamentos;
    private Map<String, Object> hmUnidadesSSH = null;
    private List<Map<String,Object>> listaHmUnidadesSSH = null;
    private UnidadesSSHAlmacenDTO unidadesSSHAlmacenDTO = null;
    private MedicamentoAgregarDTO obj = null;


    protected void setupGUI(){
        cantidadEditText = findViewById(R.id.textinput_et_cantidad);
        DigitsKeyListener dkll = DigitsKeyListener.getInstance("0123456789");
        DigitsKeyListener dkd = DigitsKeyListener.getInstance("0123456789/");
        cantidadEditText.setKeyListener(dkll);
        textInputETFechaCaducidad.setKeyListener(dkd);
    }

    protected void inicializarObjetos(){
        String fechaHoy = new SimpleDateFormat(FORMATO_FECHA).format(new Date());
        textinputEtCLUES =  findViewById(R.id.textinput_et_clues);
        textinputEtMunicipio = findViewById(R.id.textinput_et_municipio);
        claveMedicamentoEditText = findViewById(R.id.textinput_et_clave);
        unidadDeMedidaEditText = findViewById(R.id.textinput_et_presentacion);
        descripcionTextInputEditText = findViewById(R.id.textinput_et_descripcion);
        textInputETFechaCaducidad = findViewById(R.id.textinput_et_fecha_caducidad);
        actvUnidadesSSHAlmacen = findViewById(R.id.actv_unidades_ssh_almacen);
        buttonBusquedaMedicamento = findViewById(R.id.buscar_medicamento_button);
        buttonAgregarMedicamento = findViewById(R.id.agregar_medicamento_button);
        recyclerViewLstaMedicamentos = findViewById(R.id.recyclerViewMovieList);
        textInputETFechaCaducidad.setText(fechaHoy);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR,i);
        mCalendar.set(Calendar.MONTH,i1);
        mCalendar.set(Calendar.DAY_OF_MONTH,i2);
        String fecha = new SimpleDateFormat(FORMATO_FECHA).format(mCalendar.getTime());
        textInputETFechaCaducidad.setText(fecha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busqueda_medicamento);
            inicializarObjetos();
            setupGUI();
            String json = Utils.parseJson(getApplicationContext(),R.raw.unidades_ssh_almacen_app);
            List<UnidadesSSHAlmacen> listaUnidadesJson = new ArrayList();
            List<MedicamentoAgregarDTO> ltReporteAgregados = new ArrayList();
            List<MedicamentoAgregarDTO> listaMedicamentosAgregar = new ArrayList();
            Type listUnidadesSSHAlmacenType = new TypeToken<List<UnidadesSSHAlmacen>>() { }.getType();
            listaUnidadesJson = new Gson().fromJson(json, listUnidadesSSHAlmacenType);
            String arrayUnidades [] = new String[listaUnidadesJson.size()];
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrayUnidades);

            actvUnidadesSSHAlmacen.setHint("Escriba una unidad.");
            actvUnidadesSSHAlmacen.setThreshold(1);
            actvUnidadesSSHAlmacen.setAdapter(adapter);
            UnidadesSSHAlmacenDTO unidadesDTO = new UnidadesSSHAlmacenDTO();
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

            if (getIntent().getSerializableExtra(MEDICAMENTO_DTO_PARAM) != null) {
                buscarMedicamentoDTO = (BuscarMedicamentoDTO) getIntent().getSerializableExtra("medicamentoDTO");
                claveMedicamentoEditText.setText(buscarMedicamentoDTO.getClaveMedicamento());
                unidadDeMedidaEditText.setText(buscarMedicamentoDTO.getUnidadDeMedida());
                descripcionTextInputEditText.setText(buscarMedicamentoDTO.getDescripcionMedicamento());
            }
            buttonBusquedaMedicamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("DEBUG_BUTTON","CLIC AL BOTON");
                    BusquedaMedicamentoActivity.this.startActivity(new Intent(BusquedaMedicamentoActivity.this, BuscarMedicamentoActivity.class));
                }
            });
            actvUnidadesSSHAlmacen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final String unidadSeleccionada = adapterView.getItemAtPosition(i).toString();
                    for (Map<String, Object> hmUnidades : listaHmUnidadesSSH) {
                        if (hmUnidades.containsValue(unidadSeleccionada)) {
                            unidadesDTO.setClaveCLUES((String)hmUnidades.get("claveClues"));
                            unidadesDTO.setMunicipio((String)hmUnidades.get("municipio"));
                            break;
                        }
                    }
                    textinputEtCLUES.setText(unidadesDTO.getClaveCLUES());
                    textinputEtMunicipio.setText(unidadesDTO.getMunicipio());
                }
            });
            textInputETFechaCaducidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mx.com.mundodafne.ssh.almacen.app.ui.components.DatePicker datePickerFragment;
                    datePickerFragment = new mx.com.mundodafne.ssh.almacen.app.ui.components.DatePicker();
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            });
        buttonAgregarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    obj = new MedicamentoAgregarDTO();
                    obj.setClaveMedicamento(claveMedicamentoEditText.getText().toString());
                } finally {
                    ltReporteAgregados.add(obj);
                }
            }
        });
        ltReporteAgregados.add(new MedicamentoAgregarDTO());
        recyclerViewLstaMedicamentos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLstaMedicamentos.setAdapter(new TableViewAdapter(ltReporteAgregados));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (buscarMedicamentoDTO != null && unidadesSSHAlmacenDTO != null) {
            outState.putString(STATE_DESCRIPCION_MEDICAMENTO,descripcionTextInputEditText.getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        descripcionTextInputEditText.setText(savedInstanceState.getString(STATE_DESCRIPCION_MEDICAMENTO));
    }
}