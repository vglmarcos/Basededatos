import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class BuscarProducto extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {

    private String pathIcon = "images/codigo.png";
    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;
    private JButton regresar;
    private String path;
    private String url;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public BuscarProducto(String title) {
        this.setLayout(null);
        this.setBounds(0, 0, 500, 325);
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
            rs = st.executeQuery("SELECT * FROM Producto");
            System.out.println("Se ha conectado con la base de datos.");
        } catch(SQLException e) {
            System.out.println(e);
        }

        modelo = new DefaultTableModel(null, new String[]{"Id", "Nombre", "Categoria", "Precio"});
        tabla = new JTable(modelo){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 65, 460, 200);
        this.add(scroll);

        try {
            while(rs.next()) {
                String[] row = {rs.getString("co_prod"), rs.getString("nom_prod"), rs.getString("cat_prod"), rs.getString("pre_prod")};
                modelo.addRow(row);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

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
            try {
                st.close();
                System.out.println("Se ha cerrado sesi\u00F3n con la base de datos.");
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