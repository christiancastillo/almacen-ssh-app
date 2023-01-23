package mx.com.mundodafne.ssh.almacen.app.dto;

import java.io.Serializable;
import java.util.List;

public class BuscarMedicamentoDTO implements Serializable {
    private String descripcionMedicamento;
    private String claveMedicamento;
    private String unidadDeMedida;
    private List<String> mensajes;

    public void agregaMensaje(String mensaje) {
        if (mensaje != null && !mensaje.equals("")) {
            mensajes.add(mensaje);
            setMensajes(mensajes);
        }
    }

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    public String getClaveMedicamento() {
        return claveMedicamento;
    }

    public void setClaveMedicamento(String claveMedicamento) {
        this.claveMedicamento = claveMedicamento;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
}
