package vista;

import controlador.Controlador;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaTotem extends JFrame implements IVista{
    private JTextField textFieldDNI;
    private JButton aceptarButton;
    private JPanel PanelPrincipal;
    private JLabel tiempo;
    private Controlador controlador;

    public VentanaTotem(Controlador c){
        this.controlador=c;
        aceptarButton.addActionListener(controlador);
        textFieldDNI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                    e.consume();  // ignorar el evento de teclado
                }

                if(textFieldDNI.getText().length() == 8){ // verificar que no sean mas de 8 digitos
                    e.consume(); // ignorar el evento de teclado
                }
            }
        });

        setContentPane(this.PanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getTextFieldDNI() {
        return textFieldDNI;
    }

    public void setTextFieldDNI(JTextField textFieldDNI) {
        this.textFieldDNI = textFieldDNI;
    }

    public JButton getAceptarButton() {
        return aceptarButton;
    }

    public void setAceptarButton(JButton aceptarButton) {
        this.aceptarButton = aceptarButton;
    }

    public JLabel getTiempo() {
        return tiempo;
    }

    public void setTiempo(JLabel tiempo) {
        this.tiempo = tiempo;
    }
}
