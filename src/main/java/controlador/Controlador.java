package controlador;

import comunicacion.ControllerServer;
import modeloInfo.FactoryInfoCliente;
import vista.IVista;
import vista.VentanaTotem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Controlador implements ActionListener, Observer {
    private IVista vista;
    private ControllerServer controllerServer;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar")){
            String dni= vista.getTextFieldDNI().getText();
            if (dni.length()>=7){
                vista.getAceptarButton().setEnabled(false);
                controllerServer.enviarPaquete(FactoryInfoCliente.getPaqueteNuevoCliente(Integer.parseInt(dni)));
                vista.getTextFieldDNI().setText("");
                vista.getAceptarButton().setEnabled(true);
            }
        }
    }

    public Controlador(){
        controllerServer =new ControllerServer();
        controllerServer.addObserver(this);
        this.vista=new VentanaTotem(this);
        Thread t=new Thread(controllerServer);
        t.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        int horas= ((ControllerServer) o).getTiempoAtencion().getHoras();
        int minutos= ((ControllerServer) o).getTiempoAtencion().getMinutos();
        int segundos= ((ControllerServer) o).getTiempoAtencion().getSegundos();
        if (horas!=0)
            if (horas==1)
                vista.getTiempo().setText(horas+" hora, "+minutos+" minutos, "+segundos+" segundos");
            else
                vista.getTiempo().setText(horas+" horas, "+minutos+" minutos, "+segundos+" segundos");
        else
            if (minutos!=0)
                if (minutos==1)
                    vista.getTiempo().setText(minutos+" minuto, "+segundos+" segundos");
                else
                    vista.getTiempo().setText(minutos+" minutos, "+segundos+" segundos");
            else
                if (segundos==1)
                    vista.getTiempo().setText(segundos+" segundo");
                else
                    vista.getTiempo().setText(segundos+" segundos");

    }
}
