import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JLabel usuario;
    private JLabel contrasena;
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton ingresar;
    private String pathIcon = "images/codigo.png";

    private String user = "basededatosm6";
    private String password = "basededatosm6";

    public Login(String title){
        this.setLayout(null);
        this.setBounds(0, 0, 500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(title);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setIconImage(new ImageIcon(getClass().getResource(pathIcon)).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usuario = new JLabel("Usuario");
        usuario.setBounds(30, 30, 100, 30);
        usuario.setFont(new Font("Tahoma", 1, 14));
        usuario.setForeground(new Color(252, 186, 3));
        this.add(usuario);

        usuarioField = new JTextField();
        usuarioField.setBounds(140, 35, 200, 25);
        usuarioField.setBackground(new Color(224, 224, 224));
        usuarioField.setFont(new Font("Tahoma", 1, 14));
        usuarioField.setForeground(new Color(252, 186, 3));
        usuarioField.addKeyListener(this);
        usuarioField.addFocusListener(this);
        this.add(usuarioField);

        contrasena = new JLabel("Contrase\u00F1a");
        contrasena.setBounds(30, 70, 100, 30);
        contrasena.setFont(new Font("Tahoma", 1, 14));
        contrasena.setForeground(new Color(252, 186, 3));
        this.add(contrasena);

        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(140, 75, 200, 25);
        contrasenaField.setBackground(new Color(224, 224, 224));
        contrasenaField.setFont(new Font("Tahoma", 1, 14));
        contrasenaField.setForeground(new Color(252, 186, 3));
        contrasenaField.addKeyListener(this);
        contrasenaField.addFocusListener(this);
        this.add(contrasenaField);

        ingresar = new JButton("Ingresar");
        ingresar.setBounds(190, 120, 100, 30);
        ingresar.setBackground(new Color(224, 224, 224));
        ingresar.setForeground(new Color(252, 186, 3));
        ingresar.setFont(new Font("Tahoma", 1, 14));
        ingresar.addActionListener(this);
        ingresar.addKeyListener(this);
        ingresar.addFocusListener(this);
        ingresar.addMouseListener(this);
        this.add(ingresar);
    }

    //Eventos de los botones

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.ingresar) {
            String userField = new String(usuarioField.getText().trim());
            String passField = new String(contrasenaField.getPassword());
            if(userField.isEmpty() || passField.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if(userField.equals(user) && passField.equals(password)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + userField + ".");
                this.setVisible(false);
                Producto producto = new Producto("Agregar producto");
                producto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contrase\u00F1a incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Eventos de focus

    @Override
    public void focusGained(FocusEvent evt) {
        if(evt.getSource() == this.usuarioField) {
            this.usuarioField.setBackground(new Color(252, 186, 3));
            this.usuarioField.setForeground(new Color(255, 255, 255));
        } else if(evt.getSource() == this.contrasenaField) {
            this.contrasenaField.setBackground(new Color(252, 186, 3));
            this.contrasenaField.setForeground(new Color(255, 255, 255));
        } else {
            this.ingresar.setBackground(new Color(252, 186, 3));
            this.ingresar.setForeground(new Color(255, 255, 255));
        }
    }

    @Override
    public void focusLost(FocusEvent evt) {
        if(evt.getSource() == this.usuarioField) {
            this.usuarioField.setBackground(new Color(224, 224, 224));
            this.usuarioField.setForeground(new Color(252, 186, 3));
        } else if(evt.getSource() == this.contrasenaField) {
            this.contrasenaField.setBackground(new Color(224, 224, 224));
            this.contrasenaField.setForeground(new Color(252, 186, 3));
        } else {
            this.ingresar.setBackground(new Color(224, 224, 224));
            this.ingresar.setForeground(new Color(252, 186, 3));
        }
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
        if(evt.getKeyCode() == 10) {
            this.ingresar.setBackground(new Color(252, 186, 3));
            this.ingresar.setForeground(new Color(255, 255, 255));
            String userField = new String(usuarioField.getText().trim());
            String passField = new String(contrasenaField.getPassword());
            if(userField.isEmpty() || passField.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if(userField.equals(user) && passField.equals(password)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + userField + ".");
                this.setVisible(false);
                Producto producto = new Producto("Agregar producto");
                producto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contrase\u00F1a incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent evt) {
        
    }

    @Override
	public void keyReleased(KeyEvent evt) {
        
    }

    public static void main(String args[]) {
        Login login = new Login("Ingresar");
        login.setVisible(true);
    }
}