package comunicacion;

import modeloPaqueteInfo.IPaquete;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Emisor {
    private static Emisor instance = null;
    private Socket socket=null;

    private Emisor() {

    }

    public static Emisor getInstance() {
        if (instance == null)
            instance = new Emisor();
        return instance;
    }


    public void enviarPaquete(IPaquete paquete){
        if (socket == null)
            socket=Receptor.getInstance().getSocket();
        try {
            ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
