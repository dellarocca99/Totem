package comunicacion;

import modeloInfo.InfoPeticion;
import modeloInfo.InfoServerFuncional;
import modeloInfo.Informable;
import modeloInfo.InfoTiempoAtencion;
import modeloUtil.TiempoAtencion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Observable;

public class ControllerServer extends Observable implements Runnable{
    private Socket socket=null;
    private TiempoAtencion tiempoAtencion=null;
    private String host;
    private int puerto;

    @Override
    public void run() {
        try {
            this.consultaDNS(); // pido un puerto al DNS para empezar a trabajar
            while (true){
                try{
                    this.enviarPaquete(new InfoPeticion());
                    ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                    Informable paquete = (Informable) ois.readObject();
                    InfoTiempoAtencion paqueteTiempoAtencion= (InfoTiempoAtencion) paquete;
                    tiempoAtencion=paqueteTiempoAtencion.getTiempoAtencion();
                    setChanged();
                    notifyObservers();
                    this.socket.close();
                    Thread.sleep(2000);
                } catch (ConnectException e){ // si se cae el server, consulto al DNS para que me de un nuevo puerto
                    consultaDNS();
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void enviarPaquete(Informable paquete) {
        try {
            this.socket=new Socket(host,puerto);
            ObjectOutputStream oos2= new ObjectOutputStream(socket.getOutputStream());
            oos2.writeObject(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TiempoAtencion getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void consultaDNS(){
        try {
            this.socket=new Socket("localhost",9999);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Object());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            InfoServerFuncional info=(InfoServerFuncional)ois.readObject();
            puerto=info.getPuertoServer();
            host=info.getIpServer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
