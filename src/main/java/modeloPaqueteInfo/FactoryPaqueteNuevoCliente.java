package modeloPaqueteInfo;

public class FactoryPaqueteNuevoCliente {
    public static PaqueteNuevoCliente getPaqueteNuevoCliente(int dni){
        return new PaqueteNuevoCliente(dni);
    }
}
