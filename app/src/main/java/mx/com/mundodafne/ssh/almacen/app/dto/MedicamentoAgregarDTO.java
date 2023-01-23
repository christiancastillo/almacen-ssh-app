package mx.com.mundodafne.ssh.almacen.app.dto;

import java.io.Serializable;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.MENSAJE_MEDICAMENTO_AGREGADO;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.ERROR_MEDICAMENTO_VACIO;
import java.util.List;

public class MedicamentoAgregarDTO extends BuscarMedicamentoDTO implements Serializable {

    private Integer cantidad;
    private String destino;
    private String recibe;
    private String entrega;
    private List<MedicamentoAgregarDTO> listaMedicamentosAgregar;

    public void agregarMedicamentoALista(MedicamentoAgregarDTO objetoAgregado){
        if (objetoAgregado != null) {
            this.listaMedicamentosAgregar.add(objetoAgregado);
            agregaMensaje(MENSAJE_MEDICAMENTO_AGREGADO);
        } else {
            agregaMensaje(ERROR_MEDICAMENTO_VACIO);
        }
    }

    public List<MedicamentoAgregarDTO> getListaMedicamentosAgregar() {
        return listaMedicamentosAgregar;
    }

    public void setListaMedicamentosAgregar(List<MedicamentoAgregarDTO> listaMedicamentosAgregar) {
        this.listaMedicamentosAgregar = listaMedicamentosAgregar;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRecibe() {
        return recibe;
    }

    public void setRecibe(String recibe) {
        this.recibe = recibe;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
}
