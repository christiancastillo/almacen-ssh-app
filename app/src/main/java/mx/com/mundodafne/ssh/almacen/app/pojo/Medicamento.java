package mx.com.mundodafne.ssh.almacen.app.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Clase para parsear Json
 * */
public class Medicamento {
    @SerializedName(value = "descripcion")
    private String descripcionMedicamento;
    @SerializedName(value = "clave")
    private String claveMedicamento;
    private String unidadDeMedida;

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
