package practicafinal_estructuradatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPanel;

public class panelesPrincipales {

    panelesSecundarios pSec = new panelesSecundarios();
    panelesSecundarios.panelArribaDer 
            panelArribaDer = pSec.new panelArribaDer();
    panelesSecundarios.panelAbajoDer 
            panelAbajoDer = pSec.new panelAbajoDer();
    Dimension screenSize = Toolkit.
            getDefaultToolkit().getScreenSize();

    class panelAba extends JPanel {

        

        public panelAba() {
            setPreferredSize(new Dimension(0, 150));
        }

        public void paint(Graphics g) {
            Dimension tam = getSize();
            ImageIcon imagen= new ImageIcon(getClass().
                    getResource("/Images/pasto.png"));
            g.drawImage(imagen.
                    getImage(), 0, 0, tam.width,
                    tam.height, null);
            setOpaque(false);
            super.paint(g);
        }

    }

    class panelIzq extends JPanel implements ActionListener,
            MouseListener {

        /*se definen para guardar las coordenadas y 
         *posteriormente pintar las lineas*/
        Map<String, Integer> coorX
                = new HashMap<String, Integer>();
        Map<String, Integer> coorY
                = new HashMap<String, Integer>();
        /*controla la accion de unir dos nodos
         *con una linea*/
        boolean estado = false;

        String bt1 = "", bt2 = "";
        int coorMouseX = 0;
        int coorMouseY = 0;
        int nameBoton = 1;
        int[][] matrizAdy;
        int numN;
        int maximo;

        public panelIzq() {
            /*Agrega al panel derecho el boton que
             *esta en panelAbajoDer*/
            panelAbajoDer.result.addActionListener(this);
            setPreferredSize(new Dimension(
                    (screenSize.width) / 2, 0));
            addMouseListener(this);
            setBackground(Color.white);
        }

        
        
        public void pedirNodos() {
            try {
                String result = JOptionPane.
                        showInputDialog("Dijite la Cantidad de Nodos");

                numN = Integer.parseInt(result);
                matrizAdy = new int[numN + 1][numN + 1];

            } catch (Exception ex) {
                if (ex.getMessage().equals("null")) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(
                            null, "No se reconoce el valor",
                            "Atención", WARNING_MESSAGE);
                    pedirNodos();
                }
            }
        }

        public void llenarMatriz(String bt1, String bt2, int capacidad) {
            int b1 = Integer.parseInt(bt1);
            int b2 = Integer.parseInt(bt2);
            matrizAdy[b1][b2] = capacidad;
        }

        public void imprimirMatriz() {
            panelArribaDer.textarea.setText("");
            for (int i = 1; i <= numN; i++) {
                for (int j = 1; j <= numN; j++) {
                    panelArribaDer.textarea.
                            append(" " + matrizAdy[i][j] + " ");
                }
                panelArribaDer.textarea.append("\n");
            }
        }

        public void pedirDatos() {
            String inicio = JOptionPane.
                    showInputDialog("Digite Nodo Inicial Del Procesamiento");
            String fin = JOptionPane.
                    showInputDialog("Digite Nodo Final Donde Se Ubica El "
                            + "Deposito");
            int begin = Integer.parseInt(inicio);
            int end = Integer.parseInt(fin);
            maximo = new ford_Fulkerson(numN).
                    ford(matrizAdy, numN, begin, end);
            String valEnvio = JOptionPane.
                    showInputDialog("Cuanta Es La Cantidad De Material Para"
                            + " Procesar En Kilogramos");
            int val = Integer.parseInt(valEnvio);
            if (val <= maximo) {
                panelAbajoDer.textResult.setText("");
                panelAbajoDer.textResult.append("Se reciclo " + val+" Kg");
            } else {
                int total = val - maximo;
                val = val - total;
                panelAbajoDer.textResult.setText("");
                panelAbajoDer.textResult.
                        append("Se ha excedido el valor máximo \n"
                                + "se han enviado " + val+" Kg");

            }
        }

        public void actionPerformed(ActionEvent e) {
            
            if (e.getActionCommand().equals("Resultado")
                    && nameBoton == numN + 1) {
                try {
                    imprimirMatriz();
                    pedirDatos();
                } catch (Exception ex) {
                    if(ex.getMessage().equals("null")){
                        System.exit(0);
                    }else{
                    JOptionPane.showMessageDialog(null, "Uno de los"
                            + " datos no fue ingresado"
                            + " correctamente", "Atención", WARNING_MESSAGE);
                    }
                }
            }else if (nameBoton != numN + 1) {
                JOptionPane.showMessageDialog(null, "Establesca"
                        + " todos los nodos", "Atención", WARNING_MESSAGE);
            } else {
                if (!estado) {
                    bt1 = e.getActionCommand();
                    estado = true;
                } else if (estado) {
                    bt2 = e.getActionCommand();
                    if (!bt1.equals(bt2)) {
                        try {
                            estado = false;
                            int r = (coorX.get(bt1) + coorX.get(bt2)) / 2;
                            int w = (coorY.get(bt1) + coorY.get(bt2)) / 2;
                            String result = JOptionPane.
                                    showInputDialog("Dijite Capacidad");
                            int change = Integer.parseInt(result);
                            int k = 20;
                            if (!(change < 0)) {
                                int nbt1 = Integer.parseInt(bt1);
                                int nbt2 = Integer.parseInt(bt2);
                                if(matrizAdy[nbt1][nbt2] != 0){
                                   JOptionPane.showMessageDialog(null, "No"
                                           + " se puede sobreescribir"
                                           + " el valor",
                                            "Atención", WARNING_MESSAGE);
                                    
                                }else if (matrizAdy[nbt2][nbt1] == 0) {
                                    getGraphics().drawString(
                                            result,r+10, w);
                                    getGraphics().drawLine(
                                            coorX.get(bt1) + k, coorY.
                                            get(bt1) + k,
                                            coorX.get(bt2) + k,
                                            coorY.get(bt2) + k);
                                    llenarMatriz(bt1, bt2, change);

                                } else {
                                    JOptionPane.showMessageDialog(null, "No se"
                                            + " puede devolver"
                                            + " desechos",
                                            "Atención", WARNING_MESSAGE);
                                }

                            }
                        } catch (Exception ex) {
                            if(!ex.getMessage().equals("null")){
                            JOptionPane.showMessageDialog(null, "No se "
                                    + "reconoce el valor",
                                            "Atención", WARNING_MESSAGE);
                           }
                        }

                    } else if(bt1.equals(bt2)){
                        JOptionPane.showMessageDialog(null, "no se pueden"
                                + " crear loops",
                                "Atención", WARNING_MESSAGE);
                    }
                }
            }

        }

        public void mouseClicked(MouseEvent me) {
            if (nameBoton != numN + 1) {
                coorMouseX = me.getX();
                coorMouseY = me.getY();
                JButton b = new JButton("" + nameBoton);
                b.setBounds(coorMouseX, coorMouseY, 50, 50);
                b.setVisible(true);
                b.addActionListener(this);
                add(b);
                b.repaint();
                double f = b.getBounds().getX();
                double g = b.getBounds().getY();
                int f1 = (int) Math.floor(f);
                int g1 = (int) Math.floor(g);
                String name1 = "" + nameBoton;

                coorX.put(name1, f1);
                coorY.put(name1, g1);
                nameBoton++;
            }
        }

        public void mouseReleased(MouseEvent me) {
        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }

        public void mousePressed(MouseEvent ev) {
        }

        public void setListener(MouseEvent me) {

        }

    }

    class panelDer extends JPanel {

        public panelDer() {
            setLayout(new BorderLayout());
            add(panelArribaDer, BorderLayout.NORTH);
            add(panelAbajoDer, BorderLayout.SOUTH);
            setPreferredSize(new Dimension(
                    (screenSize.width) / 2, 0));
        }
    }

}
