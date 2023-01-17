package mx.com.mundodafne.ssh.almacen.app.business;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import mx.com.mundodafne.ssh.almacen.app.exceptions.BusinessException;
import mx.com.mundodafne.ssh.almacen.app.ui.BuscarMedicamentoActivity;
import mx.com.mundodafne.ssh.almacen.app.ui.BusquedaMedicamentoActivity;

public class BusquedaMedicamentoBusinessImpl implements BusquedaMedicamentoBusiness {

    @Override
    public void setupGUI() {
//        EditText editText
    }

    @Override
    public void cargaBusquedaMedicamentoActivity(BusquedaMedicamentoActivity activity) {
        try {
            //activity.this.startActivity(new Intent(activity.this, BuscarMedicamentoActivity.class));
        } catch (Exception e) {
            Log.e("Error_BI", "Error en metodo BusquedaMedicamentoBusinessImpl.cargaBusquedaMedicamentoActivity: ", e);
            //throw new BusinessException("Error en metodo BusquedaMedicamentoBusinessImpl.cargaBusquedaMedicamentoActivity: ", e);
        }
    }

    @Override
    public void iniciaActividad(Object llama, Class clazz) {
    //TODO: Buscar forma para realizar llamados de activities desde business
    }
}
