import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Producto extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {

    private String pathIcon = "images/codigo.png";
    private JButton regresar, ingresar;
    private JLabel id_prod, nom_prod, categoria, pre_prod;
    private JTextField id_prodField, nom_prodField, pre_prodField;
    private JComboBox<String> categoriasCombo;
    private String path;
    private String url;
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private Integer id;
    
    public Producto(String title) {
        this.setLayout(null);
        this.setBounds(0, 0, 500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(title);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setIconImage(new ImageIcon(getClass().getResource(pathIcon)).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        path = "C:\\Users\\Marcos\\Desktop\\Base de datos\\database\\Tienda.accdb";
        url = "jdbc:ucanaccess://" + path;

        try {
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(co_prod) FROM Producto"); //obtener id maxima de los productos
            rs.next();
            id = rs.getInt(1) + 1;
        } catch(SQLException e) {
            System.out.println(e);
        }

        id_prod = new JLabel("C\u00F3digo del producto: ");
        id_prod.setBounds(30, 40, 150, 25);
        id_prod.setFont(new Font("Tahoma", 1, 14));
        id_prod.setForeground(new Color(252, 186, 3));
        this.add(id_prod);

        id_prodField = new JTextField(new String(id.toString()));
        id_prodField.setBounds(195, 45, 50, 25);
        id_prodField.setBackground(new Color(224, 224, 224));
        id_prodField.setForeground(new Color(252, 186, 3));
        id_prodField.setFont(new Font("Tahoma", 1, 14));
        id_prodField.setHorizontalAlignment(JTextField.CENTER);
        id_prodField.setEditable(false);
        this.add(id_prodField);

        nom_prod = new JLabel("Nombre del producto: ");
        nom_prod.setBounds(30, 80, 160, 30);
        nom_prod.setFont(new Font("Tahoma", 1, 14));
        nom_prod.setForeground(new Color(252, 186, 3));
        this.add(nom_prod);

        nom_prodField = new JTextField();
        nom_prodField.setBounds(205, 85, 150, 25);
        nom_prodField.setBackground(new Color(224, 224, 224));
        nom_prodField.setForeground(new Color(252, 186, 3));
        nom_prodField.setFont(new Font("Tahoma", 1, 14));
        this.add(nom_prodField);

        categoria = new JLabel("Categoria: ");
        categoria.setBounds(30, 120, 120, 30);
        categoria.setFont(new Font("Tahoma", 1, 14));
        categoria.setForeground(new Color(252, 186, 3));
        this.add(categoria);

        categoriasCombo = new JComboBox<>();

        try {
            rs = st.executeQuery("SELECT DISTINCT cat_prod FROM Producto");
            while(rs.next()) {
                String item = rs.getString("cat_prod");
                categoriasCombo.addItem(item);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        categoriasCombo.setBounds(125, 125, 100, 25);
        categoriasCombo.setFont(new Font("Tahoma", 1, 14));
        categoriasCombo.setBackground(new Color(224, 224, 224));
        categoriasCombo.setForeground(new Color(252, 186, 3));
        this.add(categoriasCombo);

        pre_prod = new JLabel("Precio: ");
        pre_prod.setBounds(30, 160, 60, 30);
        pre_prod.setFont(new Font("Tahoma", 1, 14));
        pre_prod.setForeground(new Color(252, 186, 3));
        this.add(pre_prod);

        pre_prodField = new JTextField();
        pre_prodField.setBounds(100, 165, 80, 25);
        pre_prodField.setBackground(new Color(224, 224, 224));
        pre_prodField.setForeground(new Color(252, 186, 3));
        pre_prodField.setFont(new Font("Tahoma", 1, 14));
        this.add(pre_prodField);

        regresar = new JButton("Cerrar sesi\u00F3n");
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

        ingresar = new JButton("Ingresar producto");
        ingresar.setBounds(305, 220, 170, 25);
        ingresar.setBackground(new Color (224, 224, 224));
        ingresar.setFont(new Font("Tahoma", 1, 14));
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
            if(evt.getSource() == this.regresar) {
                Login login = new Login("Ingresar");
                login.setVisible(true);
                this.setVisible(false);
                System.out.println("Se ha cerrado sesi\u00F3n");
                try {
                    st.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }
            } else if(evt.getSource() == this.ingresar) {
                String nombre = nom_prodField.getText();
                String precio = pre_prodField.getText();
                String cat = categoriasCombo.getSelectedItem().toString();
                try {
                    st.executeUpdate("INSERT INTO Producto (co_prod, cat_prod, nom_prod, pre_prod) VALUES ('" + id + "', '" + cat + "', '" + nombre + "', '" + precio + "')");
                    System.out.println("Se ha agregado correctamente el producto.");
                    id = id + 1;
                    id_prodField.setEditable(true);
                    id_prodField.setText(id.toString());
                    id_prodField.setEditable(false);
                    nom_prodField.setText("");
                    pre_prodField.setText("");
                } catch(SQLException e) {
                    System.out.println(e);
                }
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