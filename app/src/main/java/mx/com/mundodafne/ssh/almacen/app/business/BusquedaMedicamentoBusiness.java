package mx.com.mundodafne.ssh.almacen.app.business;

import mx.com.mundodafne.ssh.almacen.app.ui.BusquedaMedicamentoActivity;

public interface BusquedaMedicamentoBusiness {
    void cargaBusquedaMedicamentoActivity(BusquedaMedicamentoActivity activity);
    void iniciaActividad(Object llama, Class clazz);
    void setupGUI();

}
