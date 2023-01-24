package mx.com.mundodafne.ssh.almacen.app.pojo;

import com.google.gson.annotations.SerializedName;

public class UnidadesSSHAlmacen {
    @SerializedName(value = "descripcion")
    private Integer id;
    private String municipio;
    @SerializedName(value = "nombre_cs")
    private String nombreCS;
    @SerializedName(value = "CLUES")
    private String claveCLUES;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombreCS() {
        return nombreCS;
    }

    public void setNombreCS(String nombreCS) {
        this.nombreCS = nombreCS;
    }

    public String getClaveCLUES() {
        return claveCLUES;
    }

    public void setClaveCLUES(String claveCLUES) {
        this.claveCLUES = claveCLUES;
    }
}
