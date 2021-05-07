package modeloPaqueteInfo;

import java.io.Serializable;

import modeloUtil.TiempoAtencion;

public class PaqueteTiempoAtencion implements IPaquete,Serializable{
    private final int idOperacion = 2;
    private TiempoAtencion tiempoAtencion;

    public void setTiempoAtencion(TiempoAtencion tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public TiempoAtencion getTiempoAtencion() {
        return tiempoAtencion;
    }

    @Override
    public int getIdOperacion() {
        return idOperacion;
    }
}
