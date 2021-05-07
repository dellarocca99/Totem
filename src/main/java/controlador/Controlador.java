package controlador;

import comunicacion.Emisor;
import comunicacion.Receptor;
import modeloPaqueteInfo.FactoryPaqueteNuevoCliente;
import vista.IVista;
import vista.VentanaTotem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Controlador implements ActionListener, Observer {
    private IVista vista;
    private Receptor receptor;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar")){
            String dni= vista.getTextFieldDNI().getText();
            if (dni.length()>=7){
                vista.getAceptarButton().setEnabled(false);
                Emisor.getInstance().enviarPaquete(FactoryPaqueteNuevoCliente.getPaqueteNuevoCliente(Integer.parseInt(dni)));
                vista.getTextFieldDNI().setText("");
                vista.getAceptarButton().setEnabled(true);
            }
        }
    }

    public Controlador(){
        receptor=Receptor.getInstance();
        receptor.addObserver(this);
        this.vista=new VentanaTotem(this);
        receptor.run();
    }

    @Override
    public void update(Observable o, Object arg) {
        int horas= ((Receptor) o).getTiempoAtencion().getHoras();
        int minutos= ((Receptor) o).getTiempoAtencion().getMinutos();
        int segundos= ((Receptor) o).getTiempoAtencion().getSegundos();
        vista.getTiempo().setText(horas+" horas, "+minutos+" minutos, "+segundos+" segundos");
    }
}
