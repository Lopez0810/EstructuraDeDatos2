package practicafinal_estructuradatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class panelesSecundarios {

    Dimension screenSize = Toolkit.
            getDefaultToolkit().getScreenSize();

    class panelArribaDer extends JPanel {

        JTextArea textarea;

        public panelArribaDer() {
            setPreferredSize(new Dimension(
                    0, (screenSize.height) / 2));
            textarea = new JTextArea();
            textarea.setEditable(false);
            textarea.setFont(new Font("Serif", Font.ITALIC, 30));
            textarea.setVisible(true);
            textarea.setText("");
            JScrollPane scrollPane = new JScrollPane(textarea);
            scrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(
                    screenSize.width / 2 - 100, screenSize.height / 2 - 30));
            add(scrollPane);
        }

        public void paint(Graphics g) {
            Dimension tam = getSize();
            ImageIcon imagen = new ImageIcon(getClass().
                    getResource("/Images/grafico.jpg"));
            g.drawImage(imagen.
                    getImage(), 0, 0, tam.width,
                    tam.height, null);
            setOpaque(false);
            super.paint(g);
        }
    }

    class panelAbajoDer extends JPanel {

        JTextArea textResult;
        public JButton result;

        public panelAbajoDer() {
            setPreferredSize(new Dimension(0, (screenSize.height) / 2 - 200));
            setLayout(new BorderLayout());
            JPanel panelText = new JPanel();
            panelText.setPreferredSize(new Dimension(
                    (screenSize.width) / 2 - (screenSize.width) / 5, 0));

            textResult = new JTextArea();
            textResult.setEditable(false);
            textResult.setFont(new Font("Serif", Font.ITALIC, 20));
            textResult.setVisible(true);
            textResult.setText("");
            JScrollPane scroll = new JScrollPane(textResult);
            scroll.setPreferredSize(new Dimension(
                    (screenSize.width) / 2 - (screenSize.width) / 5 - 80,
                    (screenSize.height) / 2 - 230));
            panelText.add(scroll);

            class panelBoton extends JPanel {

                public panelBoton() {
                    setPreferredSize(new Dimension(
                            (screenSize.width) / 5, 0));
                    result = new JButton("Resultado");
                    result.setPreferredSize(new Dimension(130, 50));

                    add(result);

                }

                public void paint(Graphics g) {
                    Dimension tam = getSize();
                    ImageIcon imagen = new ImageIcon(getClass().
                            getResource("/Images/grafo.png"));
                    g.drawImage(imagen.
                            getImage(), 20, 60, tam.width-50,
                            tam.height-60, null);
                    setOpaque(false);
                    super.paint(g);
                }

            }
            add(new panelBoton(), BorderLayout.EAST);
            add(panelText, BorderLayout.WEST);

        }

    }
}
