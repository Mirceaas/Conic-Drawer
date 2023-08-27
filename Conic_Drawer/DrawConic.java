import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class DrawConic extends JFrame {
    public JPanel contentPane;

    public JFrame frame;
    public InfoPanel infoPanel;
    public DetailsPanel detailsPanel;
    public DrawPanel drawPanel;
    public DrawConic() {
        // frame = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1023, 540);

        JLabel background;

//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.setLayout(null);

        ImageIcon im = new ImageIcon("images/backg2.jpg");

        background = new JLabel("", im, JLabel.CENTER);
        background.setBounds(0, 0, 1023, 540);
        this.getContentPane().add(background);

        setResizable(false);
        setTitle("Desenarea unei conice pe baza ecuatiei generale");
        // setIconImage(tool.getImage(GetResources("images/ico.webp")));
        // loadImage();

        infoPanel = new InfoPanel();
        // frame.add(infoPanel.contentPane);
        background.add(infoPanel.contentPane);

        detailsPanel = new DetailsPanel(infoPanel);
        // frame.add(detailsPanel.contentPane);
        background.add(detailsPanel.contentPane);

        drawPanel = new DrawPanel(detailsPanel, infoPanel);
        // frame.add(drawPanel);
        background.add(drawPanel.contentPane);

        setVisible(true);

        infoPanel.drawButton.addActionListener(e -> {
            if(e.getSource() == infoPanel.drawButton) {
                try {
                    drawPanel.initApp = true;
                    detailsPanel.convert();
                    detailsPanel.computeInvariants();
                    detailsPanel.classifyConic();
                    drawPanel.reduceToCanonicalForm();
                    repaint();
                    // System.out.println(drawPanel.initApp);
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, "Some error occurred!");
                }
            }
        });
    }

//    public java.net.URL GetResources(String s) {
//        return this.getClass().getResource(s);
//    }

    public static void main(String[] args) {
        new DrawConic();
    }

//    public void loadImage() {
//        try {
//            MediaTracker mediaTracker = new MediaTracker(this);
//            backg = tool.getImage("images/backg.jpg");
//            mediaTracker.addImage(backg, 0);
//            mediaTracker.waitForAll();
//        } catch (Throwable throwable) {
//
//        }
//    }
}

class InfoPanel extends JFrame {
    public JTextField a11_txt;
    public JTextField a12_txt;
    public JTextField a22_txt;
    public JTextField a13_txt;
    public JTextField a23_txt;
    public JTextField a33_txt;
    public JTextField a11_field;
    public JTextField a12_field;
    public JTextField a22_field;
    public JTextField a13_field;
    public JTextField a23_field;
    public JTextField a33_field;
    public JButton drawButton;
    public JPanel contentPane;

    public InfoPanel () {
        contentPane = new JPanel();
        contentPane.setBounds(22, 52, 282, 369);
        contentPane.setLayout(null);

        contentPane.setOpaque(false);

        a11_txt = new JTextField();
        a11_txt.setBounds(88, 10, 47, 28);
        contentPane.add(a11_txt);
        a11_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a11_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a11_txt.setText("a11:");
        a11_txt.setColumns(10);
        a11_txt.setOpaque(false);
        a11_txt.setBorder(null);
        a11_txt.setEditable(false);

        a11_field = new JTextField();
        a11_field.setBounds(135, 10, 47, 28);
        contentPane.add(a11_field);
        a11_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a11_field.setHorizontalAlignment(SwingConstants.LEFT);
        a11_field.setColumns(10);

        a12_txt = new JTextField();
        a12_txt.setBounds(88, 48, 47, 28);
        contentPane.add(a12_txt);
        a12_txt.setText("a12:");
        a12_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a12_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a12_txt.setColumns(10);
        a12_txt.setOpaque(false);
        a12_txt.setBorder(null);
        a12_txt.setEditable(false);

        a12_field = new JTextField();
        a12_field.setBounds(135, 48, 47, 28);
        contentPane.add(a12_field);
        a12_field.setHorizontalAlignment(SwingConstants.LEFT);
        a12_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a12_field.setColumns(10);

        a22_txt = new JTextField();
        a22_txt.setBounds(88, 86, 47, 28);
        contentPane.add(a22_txt);
        a22_txt.setText("a22:");
        a22_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a22_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a22_txt.setColumns(10);
        a22_txt.setOpaque(false);
        a22_txt.setBorder(null);
        a22_txt.setEditable(false);

        a22_field = new JTextField();
        a22_field.setBounds(135, 86, 47, 28);
        contentPane.add(a22_field);
        a22_field.setHorizontalAlignment(SwingConstants.LEFT);
        a22_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a22_field.setColumns(10);

        a13_txt = new JTextField();
        a13_txt.setBounds(88, 124, 47, 28);
        contentPane.add(a13_txt);
        a13_txt.setText("a13:");
        a13_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a13_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a13_txt.setColumns(10);
        a13_txt.setOpaque(false);
        a13_txt.setBorder(null);
        a13_txt.setEditable(false);

        a13_field = new JTextField();
        a13_field.setBounds(135, 124, 47, 28);
        contentPane.add(a13_field);
        a13_field.setHorizontalAlignment(SwingConstants.LEFT);
        a13_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a13_field.setColumns(10);

        a23_txt = new JTextField();
        a23_txt.setBounds(88, 162, 47, 28);
        contentPane.add(a23_txt);
        a23_txt.setText("a23:");
        a23_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a23_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a23_txt.setColumns(10);
        a23_txt.setOpaque(false);
        a23_txt.setBorder(null);
        a23_txt.setEditable(false);

        a23_field = new JTextField();
        a23_field.setBounds(135, 162, 47, 28);
        contentPane.add(a23_field);
        a23_field.setHorizontalAlignment(SwingConstants.LEFT);
        a23_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a23_field.setColumns(10);

        a33_txt = new JTextField();
        a33_txt.setBounds(88, 200, 47, 28);
        contentPane.add(a33_txt);
        a33_txt.setText("a33:");
        a33_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        a33_txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a33_txt.setColumns(10);
        a33_txt.setOpaque(false);
        a33_txt.setBorder(null);
        a33_txt.setEditable(false);

        a33_field = new JTextField();
        a33_field.setBounds(135, 200, 47, 28);
        contentPane.add(a33_field);
        a33_field.setHorizontalAlignment(SwingConstants.LEFT);
        a33_field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        a33_field.setColumns(10);

        drawButton = new JButton("Deseneaza");
        drawButton.setBounds(69, 264, 137, 48);
        contentPane.add(drawButton);
        drawButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
    }

}
class DetailsPanel extends JFrame {

    public JTextField tipulConicei_txt;
    public JTextField genulConicei_txt;
    public JTextField denumireaConicei_txt;
    public JTextField tipulConicei_value;
    public JTextField genulConicei_value;
    public JTextField denumireaConicei_value;
    public JTextField invariant1_txt;
    public JTextField invariant2_txt;
    public JTextField invariant3_txt;
    public JTextField invariant4_txt;
    public JTextField invariant1_value;
    public JTextField invariant2_value;
    public JTextField invariant3_value;
    public JTextField invariant4_value;
    public JPanel contentPane;

    public InfoPanel infoPanel;

    public int invariant1, invariant2, invariant3, invariant4;
    public int a11, a12, a22, a13, a23, a33;

    public DetailsPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;

        contentPane = new JPanel();
        contentPane.setBounds(340, 52, 282, 369);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        tipulConicei_txt = new JTextField();
        tipulConicei_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        tipulConicei_txt.setText("Tipul conicei:");
        tipulConicei_txt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tipulConicei_txt.setBounds(10, 10, 118, 29);
        contentPane.add(tipulConicei_txt);
        tipulConicei_txt.setColumns(10);
        tipulConicei_txt.setOpaque(false);
        tipulConicei_txt.setEditable(false);
        tipulConicei_txt.setBorder(null);

        tipulConicei_value = new JTextField();
        tipulConicei_value.setHorizontalAlignment(SwingConstants.CENTER);
        tipulConicei_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tipulConicei_value.setColumns(10);
        tipulConicei_value.setBounds(128, 10, 144, 29);
        contentPane.add(tipulConicei_value);

        invariant1_txt = new JTextField();
        invariant1_txt.setText("δ:");
        invariant1_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        invariant1_txt.setFont(new Font("Times New Roman", Font.BOLD, 18));
        invariant1_txt.setColumns(10);
        invariant1_txt.setBounds(35, 213, 42, 29);
        contentPane.add(invariant1_txt);
        invariant1_txt.setEditable(false);
        invariant1_txt.setOpaque(false);
        invariant1_txt.setBorder(null);

        invariant2_txt = new JTextField();
        invariant2_txt.setText("∆:");
        invariant2_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        invariant2_txt.setFont(new Font("Times New Roman", Font.BOLD, 18));
        invariant2_txt.setColumns(10);
        invariant2_txt.setBounds(35, 252, 42, 29);
        contentPane.add(invariant2_txt);
        invariant2_txt.setBorder(null);
        invariant2_txt.setOpaque(false);
        invariant2_txt.setEditable(false);

        invariant3_txt = new JTextField();
        invariant3_txt.setText("I:");
        invariant3_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        invariant3_txt.setFont(new Font("Times New Roman", Font.BOLD, 18));
        invariant3_txt.setColumns(10);
        invariant3_txt.setBounds(35, 291, 42, 29);
        contentPane.add(invariant3_txt);
        invariant3_txt.setEditable(false);
        invariant3_txt.setOpaque(false);
        invariant3_txt.setBorder(null);

        invariant4_txt = new JTextField();
        invariant4_txt.setText("I∆:");
        invariant4_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        invariant4_txt.setFont(new Font("Times New Roman", Font.BOLD, 18));
        invariant4_txt.setColumns(10);
        invariant4_txt.setBounds(35, 330, 42, 29);
        contentPane.add(invariant4_txt);
        invariant4_txt.setBorder(null);
        invariant4_txt.setOpaque(false);
        invariant4_txt.setEditable(false);

        invariant1_value = new JTextField();
        invariant1_value.setHorizontalAlignment(SwingConstants.CENTER);
        invariant1_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        invariant1_value.setColumns(10);
        invariant1_value.setBounds(75, 213, 144, 29);
        contentPane.add(invariant1_value);

        invariant2_value = new JTextField();
        invariant2_value.setHorizontalAlignment(SwingConstants.CENTER);
        invariant2_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        invariant2_value.setBounds(75, 252, 144, 29);
        contentPane.add(invariant2_value);
        invariant2_value.setColumns(10);

        invariant3_value = new JTextField();
        invariant3_value.setColumns(10);
        invariant3_value.setHorizontalAlignment(SwingConstants.CENTER);
        invariant3_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        invariant3_value.setBounds(75, 291, 144, 29);
        contentPane.add(invariant3_value);

        invariant4_value = new JTextField();
        invariant4_value.setColumns(10);
        invariant4_value.setHorizontalAlignment(SwingConstants.CENTER);
        invariant4_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        invariant4_value.setBounds(75, 330, 144, 29);
        contentPane.add(invariant4_value);

        genulConicei_txt = new JTextField();
        genulConicei_txt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        genulConicei_txt.setHorizontalAlignment(SwingConstants.RIGHT);
        genulConicei_txt.setText("Genul conicei:");
        genulConicei_txt.setBounds(10, 49, 118, 29);
        contentPane.add(genulConicei_txt);
        genulConicei_txt.setColumns(10);
        genulConicei_txt.setEditable(false);
        genulConicei_txt.setOpaque(false);
        genulConicei_txt.setBorder(null);

        denumireaConicei_txt = new JTextField();
        denumireaConicei_txt.setText("Denumirea conicei:");
        denumireaConicei_txt.setHorizontalAlignment(SwingConstants.CENTER);
        denumireaConicei_txt.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        denumireaConicei_txt.setColumns(10);
        denumireaConicei_txt.setBounds(10, 90, 262, 60);
        contentPane.add(denumireaConicei_txt);
        denumireaConicei_txt.setBorder(null);
        denumireaConicei_txt.setOpaque(false);
        denumireaConicei_txt.setEditable(false);

        denumireaConicei_value = new JTextField();
        denumireaConicei_value.setHorizontalAlignment(SwingConstants.CENTER);
        denumireaConicei_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        denumireaConicei_value.setBounds(83, 160, 109, 29);
        contentPane.add(denumireaConicei_value);
        denumireaConicei_value.setColumns(10);

        genulConicei_value = new JTextField();
        genulConicei_value.setHorizontalAlignment(SwingConstants.CENTER);
        genulConicei_value.setFont(new Font("Times New Roman", Font.BOLD, 14));
        genulConicei_value.setColumns(10);
        genulConicei_value.setBounds(128, 49, 144, 29);
        contentPane.add(genulConicei_value);
    }

    public void convert() {
        a11 = Integer.parseInt(infoPanel.a11_field.getText());
        a12 = Integer.parseInt(infoPanel.a12_field.getText()) / 2;
        a22 = Integer.parseInt(infoPanel.a22_field.getText());
        a13 = Integer.parseInt(infoPanel.a13_field.getText()) / 2;
        a23 = Integer.parseInt(infoPanel.a23_field.getText()) / 2;
        a33 = Integer.parseInt(infoPanel.a33_field.getText());

//        System.out.println(a11);
//        System.out.println(a12);
//        System.out.println(a22);
//        System.out.println(a13);
//        System.out.println(a23);
//        System.out.println(a33);
    }

    public void computeInvariants() {
        // δ
        invariant1 = a11*a22 - a12*a12;

        /*
        a11 a12 a13
        a12 a22 a23
        a13 a23 a33
         */
        // ∆
        invariant2 = a11 * a22 * a33 + a13 * a12 * a23 + a12 * a23 * a13
                - (a13 * a22 * a13 + a11 * a23 * a23 + a12 * a33 * a12);

        // I
        invariant3 = a11 + a22;

        // I∆
        invariant4 = invariant3 * invariant2;

        invariant1_value.setText(String.valueOf(invariant1));
        invariant2_value.setText(String.valueOf(invariant2));
        invariant3_value.setText(String.valueOf(invariant3));
        invariant4_value.setText(String.valueOf(invariant4));
    }

    public void classifyConic() {
        if(invariant1 > 0 && invariant2 != 0 && invariant4 < 0) {
            genulConicei_value.setText("eliptic");
            tipulConicei_value.setText("nedegenerat");
            denumireaConicei_value.setText("elipsa");
        } else if(invariant1 > 0 && invariant2 != 0 && invariant4 > 0) {
            genulConicei_value.setText("eliptic");
            tipulConicei_value.setText("nedegenerat");
            denumireaConicei_value.setText("elipsa imaginara");
        } else if(invariant1 > 0 && invariant2 == 0) {
            genulConicei_value.setText("eliptic");
            tipulConicei_value.setText("degenerat");
            denumireaConicei_value.setText("punct dublu");
        } else if(invariant1 < 0 && invariant2 != 0) {
            genulConicei_value.setText("hiperbolic");
            tipulConicei_value.setText("nedegenerat");
            denumireaConicei_value.setText("hiperbola");
        } else if(invariant1 < 0) {
            genulConicei_value.setText("hiperbolic");
            tipulConicei_value.setText("degenerat");
            denumireaConicei_value.setText("drepte concurent");
        } else if(invariant1 == 0 && invariant2 != 0) {
            genulConicei_value.setText("parabolic");
            tipulConicei_value.setText("nedegenerat");
            denumireaConicei_value.setText("parabola");
        }
    }
}

class DrawPanel extends JPanel {
    public JPanel contentPane;
    public DetailsPanel detailsPanel;

    public InfoPanel infoPanel;

    boolean initApp;

    public DrawPanel(DetailsPanel detailsPanel, InfoPanel infoPanel) {
        this.detailsPanel = detailsPanel;
        this.infoPanel = infoPanel;
        contentPane = new JPanel();
        initApp = false;
        contentPane.setBounds(664, 52, 345, 269);
        contentPane.setOpaque(false);
    }

    public ArrayList<Double> reduceToCanonicalForm() {
        // a11x^2+2a12xy+a22y^2+2a10x+2a20y+a00 = 0
        /*
                 a11 a12  x                   x
          x y *              + 2 (a10 a20) +     + a00 = 0
                 a21 a22  y                   y
          l^2 - (a11+a22) * l + a11*a22-a12^2 = 0
         */
        // int delta = (detailsPanel.a11+detailsPanel.a22)*(detailsPanel.a11+detailsPanel.a22) - 4 * (detailsPanel.a11*detailsPanel.a22 - detailsPanel.a12 * detailsPanel.a12);
        // double l1 = ((detailsPanel.a11+detailsPanel.a22) + Math.sqrt(delta)) / 2;
        // double l2 = ((detailsPanel.a11+detailsPanel.a22) - Math.sqrt(delta)) / 2;

        // int a = -(detailsPanel.a11+detailsPanel.a22);
        // System.out.println(a);
        // System.out.println(l1);
        // System.out.println(l2);
        // System.out.println(delta);

        // avem sistemul urmator pt a determina centrul unei conice
        /*
        a11*x0 + a12*y0 + a13 = 0
        a21*x0 + a22*y0 + a23 = 0
         */

        // verificam daca conica admite centru sau nu
        if (detailsPanel.invariant1 != 0) {
            // δ != 0 -> sistemul are sol unica si punctul C0(x0, yo) este centrul conicei
            // coordonatele centrului obtinute din sistemul de mai sus
            double y0 = (double) (-detailsPanel.a11 * detailsPanel.a23 + detailsPanel.a12 * detailsPanel.a13) / (detailsPanel.a11 * detailsPanel.a22 - detailsPanel.a12 * detailsPanel.a12);
            double x0 = (-detailsPanel.a13 - detailsPanel.a12 * y0) / detailsPanel.a11;

//            System.out.println(x0);
//            System.out.println(y0);

            // ecuatia canonica a conicei
            // lambda1*x''^2 + lambda2*y''^2 + ∆/δ = 0

            if (detailsPanel.invariant2 != 0) {
                // elipsa
                if (detailsPanel.invariant1 > 0) {
                    // lambda^2 - I*lambda + δ = 0
                    int delta = detailsPanel.invariant3 * detailsPanel.invariant3 - 4 * detailsPanel.invariant1;

                    double lambda1 = (detailsPanel.invariant3 + Math.sqrt(delta)) / 2;
                    double lambda2 = (detailsPanel.invariant3 - Math.sqrt(delta)) / 2;

                    // System.out.println(lambda1);
                    // System.out.println(lambda2);

                    // din ecuatie, scoatem a si b
                    // a^2 = δ/lambda1*∆
                    // b^2 = δ/lambda2*∆

                    double a = Math.sqrt(Math.abs(detailsPanel.invariant2 / (lambda1 * detailsPanel.invariant1)));
                    double b = Math.sqrt(Math.abs(detailsPanel.invariant2 / (lambda2 * detailsPanel.invariant1)));

//                    System.out.println(a);
//                    System.out.println(b);

                    double major_axis = Double.max(2 * a, 2 * b);
                    double minor_axis = Double.min(2 * a, 2 * b);

//                    System.out.println(major_axis);
//                    System.out.println(minor_axis);

                    ArrayList<Double> coords = new ArrayList<>();
                    coords.add(x0);
                    coords.add(y0);
                    coords.add(major_axis);
                    coords.add(minor_axis);

                    // for (Double c : coords)
                        // System.out.println(c);

                    return coords;
                }
            }
        }
        return null;
    }

    public void paintComponent(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(10, 10, 100, 100);
        super.paintComponent(g);
        ArrayList<Double> coords = new ArrayList<>();
        // System.out.println("Here");
        if(initApp) {
            coords = reduceToCanonicalForm();
            // System.out.println("Got here!");
            // g.setColor(Color.red);
            // g.fillRect(10, 10, 100, 100);

            g.setColor(Color.red);

            double px=0, py=0;

            for(int i=0; i<=360; i++) {
                double x, y;
                x = coords.get(2) * Math.sin(Math.toRadians(i));
                y = coords.get(3) * Math.cos(Math.toRadians(i));

                if(i != 0) {
                    g.drawLine((int) (px + coords.get(0)), (int) (py + coords.get(1)),
                            (int) (x + coords.get(0)), (int)(y + coords.get(1)));
                }

                px = x;
                py = y;
            }

        }

    }
}