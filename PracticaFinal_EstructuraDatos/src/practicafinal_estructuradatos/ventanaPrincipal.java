package practicafinal_estructuradatos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class ventanaPrincipal {

    
    JFrame jframe = new JFrame();
    panelesPrincipales pPrin= new panelesPrincipales();
    panelesPrincipales.panelAba panelAba = pPrin.new panelAba();
    panelesPrincipales.panelDer panelDer = pPrin.new panelDer();
    panelesPrincipales.panelIzq panelIzq = pPrin.new panelIzq();
    
    
    Dimension screenSize = Toolkit.
                getDefaultToolkit().getScreenSize();
    
    
    
    
    public ventanaPrincipal() {
        
        jframe.getContentPane();
        jframe.setTitle("PROBLEMA CLIQUE MÁXIMO");
        jframe.setSize(screenSize);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        
        jframe.setLayout(new BorderLayout());
        jframe.add(panelIzq, BorderLayout.WEST);
        jframe.add(panelDer, BorderLayout.EAST);
        jframe.add(panelAba, BorderLayout.SOUTH);
        jframe.setVisible(true);
        jframe.repaint();
    }
}
