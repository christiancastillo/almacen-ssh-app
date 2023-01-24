package mx.com.mundodafne.ssh.almacen.app.dto;

import java.io.Serializable;
import java.util.List;
import static mx.com.mundodafne.ssh.almacen.app.utils.AlmSSHConstants.UNIDAD_AGREGADA_EXITO;

public class UnidadesSSHAlmacenDTO implements Serializable {

    private Integer id;
    private String municipio;
    private String nombreCS;
    private String claveCLUES;
    private List<String> listaMensajes;

    public void agregarMensaje(String mensaje) {
        if (mensaje != null && !mensaje.equals("")) {
            listaMensajes.add(UNIDAD_AGREGADA_EXITO);
        }
    }

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

    public List<String> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<String> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
