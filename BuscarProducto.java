import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BuscarProducto extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {

    private String pathIcon = "images/codigo.png";
    private JButton regresar;

    public BuscarProducto(String title) {
        this.setLayout(null);
        this.setBounds(0, 0, 500, 325);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(title);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setIconImage(new ImageIcon(getClass().getResource(pathIcon)).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        regresar = new JButton("Regresar");
        regresar.setBounds(350, 15, 125, 25);
        regresar.setBackground(new Color (224, 224, 224));
        regresar.setFont(new Font("Tahoma", 1, 14));
        regresar.setForeground(new Color(252, 186, 3));
        regresar.setFont(new Font("Tahoma", 1, 14));
        regresar.addActionListener(this);
        regresar.addKeyListener(this);
        regresar.addFocusListener(this);
        regresar.addMouseListener(this);
        this.add(regresar);
    }

    //Eventos de los componentes

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.regresar) {
            Producto producto = new Producto("Ingresar producto");
            producto.setVisible(true);
            this.setVisible(false);
        }
    }

    //Eventos de focus

    @Override
    public void focusGained(FocusEvent evt) {

    }

    @Override
    public void focusLost(FocusEvent evt) {

    }

    //Eventos del mouse

    @Override
    public void mouseReleased(MouseEvent evt) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {

    }

    @Override
    public void mouseExited(MouseEvent evt) {
        
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        
    }

    //Eventos del teclado

    @Override
    public void keyPressed(KeyEvent evt) {

    }
    
    @Override
    public void keyTyped(KeyEvent evt) {
        
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        
    }
}