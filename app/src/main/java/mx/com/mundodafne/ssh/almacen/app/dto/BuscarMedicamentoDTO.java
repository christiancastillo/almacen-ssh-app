package mx.com.mundodafne.ssh.almacen.app.dto;

import java.util.List;

public class BuscarMedicamentoDTO {
    private String descripcionMedicamento;
    private String claveMedicamento;
    private String unidadDeMedida;
    private List<String> mensajes;

    public BuscarMedicamentoDTO() {
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensaje) {
        if (mensaje != null && !mensaje.equals("")) {
            this.mensajes.add(mensaje);
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
}
