package comunicacion;

import modeloPaqueteInfo.IPaquete;
import modeloPaqueteInfo.PaqueteTiempoAtencion;
import modeloUtil.TiempoAtencion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;

public class Receptor extends Observable implements Runnable{
    private Socket socket=null;
    private static Receptor instance=null;
    private static String HOST="190.189.40.162";
    private TiempoAtencion tiempoAtencion=null;

    private Receptor(){

    }

    public static Receptor getInstance(){
        if (instance==null)
            instance=new Receptor();
        return instance;
    }

    @Override
    public void run() {
        try {
            socket=new Socket(HOST,9797);
            System.out.println("Client connected");
            while (true){
                //el unico paquete que recibo del Server es el PaqueteTiempoAtencion
                IPaquete paquete= (IPaquete) new ObjectInputStream(socket.getInputStream());
                PaqueteTiempoAtencion paqueteTiempoAtencion= (PaqueteTiempoAtencion) paquete;
                tiempoAtencion=paqueteTiempoAtencion.getTiempoAtencion();
                setChanged();
                notifyObservers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public TiempoAtencion getTiempoAtencion() {
        return tiempoAtencion;
    }
}
