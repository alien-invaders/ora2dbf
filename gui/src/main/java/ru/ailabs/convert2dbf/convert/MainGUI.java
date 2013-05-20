package ru.ailabs.convert2dbf.convert;

import ru.ailabs.convert2dbf.*;
import nl.knaw.dans.common.dbflib.DbfLibException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MainGUI extends javax.swing.JFrame {

    private static Logger logger = Logger.getLogger(MainGUI.class);

    private javax.swing.JButton executeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField dbUrl;
    private javax.swing.JTextField dbUsername;
    private javax.swing.JTextField path;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private File file;

    public MainGUI() throws IOException {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() throws IOException {

        jPanel1 = new javax.swing.JPanel();
        dbUrl = new javax.swing.JTextField();
        dbUsername = new javax.swing.JTextField();
        path = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        executeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        file = new File("gui\\src\\main\\resources\\properties.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        dbUrl.setText(properties.getProperty("URL"));
        dbUsername.setText(properties.getProperty("Username"));
        path.setText(properties.getProperty("Path"));
        password.setText(properties.getProperty("Password"));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        executeButton.setText("Выполнить запрос");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    executeQuery(evt);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DbfLibException e) {
                    e.printStackTrace();
                }
            }
        });
        saveButton.setText("Сохранить");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    save(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        label1.setFont(new java.awt.Font("Tahoma", 0, 10));
        label1.setText("Пароль");

        label2.setFont(new java.awt.Font("Tahoma", 0, 10));
        label2.setText("Текст запроса");

        label3.setFont(new java.awt.Font("Tahoma", 0, 10));
        label3.setText("Имя пользователя");

        label4.setFont(new java.awt.Font("Tahoma", 0, 10));
        label4.setMinimumSize(new java.awt.Dimension(38, 10));
        label4.setName("URL базы данных");
        label4.setText("URL базы данных");

        label5.setFont(new java.awt.Font("Tahoma", 0, 10));
        label5.setText("Путь к выгружаемому файлу");

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dbUsername)
                                        .addComponent(dbUrl)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 338, Short.MAX_VALUE)
                                                .addComponent(saveButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(executeButton))
                                        .addComponent(path)
                                        .addComponent(password, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveButton)
                                        .addComponent(executeButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void executeQuery(java.awt.event.ActionEvent evt) throws SQLException, IOException, DbfLibException {
        String pass = password.getText();
        String jdbcString = dbUrl.getText();
        String username = dbUsername.getText();
        String sql = jTextArea1.getText();
        String dbffile = path.getText();
        /*if (dbffile == null) {
            dbffile = sqlfile + ".convert2dbf";
        }*/

        //StringBuilder query = ResourceStringReader.readToBuilder(new FileInputStream(sqlfile), "UTF-8");

        Connection conn = DriverManager.getConnection(jdbcString, username, pass);

        QueryRunner run = new QueryRunner();

        class Handler extends MapListHandler {

            public List<DbfFieldDescription> fields = null;

            @Override
            protected Map<String, Object> handleRow(ResultSet rs) throws SQLException {
                if (fields == null) {
                    fields = new ArrayList<DbfFieldDescription>();
                    ResultSetMetaData meta = rs.getMetaData();
                    int cols = meta.getColumnCount();

                    for (int i = 0; i < cols; i++) {

                        String name = meta.getColumnName(i + 1);
                        String type = meta.getColumnTypeName(i + 1);
                        int precision = meta.getPrecision(i + 1);
                        precision = precision > 160 ? 160 : precision;
                        precision = precision == 0 ? 10 : precision;
                        int scale = meta.getScale(i + 1);
                        scale = scale < 0 ? 0 : scale;

                        logger.debug("Field Name " + name + ", Type: " + type + "(" + precision + "," + scale + ")");

                        if (type.equals("CHAR")) {
                            fields.add(new DbfFieldDescription(name, nl.knaw.dans.common.dbflib.Type.CHARACTER, precision, scale));
                        } else if (type.equals("VARCHAR")) {
                            fields.add(new DbfFieldDescription(name, nl.knaw.dans.common.dbflib.Type.CHARACTER, precision, scale));
                        } else if (type.equals("VARCHAR2")) {
                            fields.add(new DbfFieldDescription(name, nl.knaw.dans.common.dbflib.Type.CHARACTER, precision, scale));
                        } else if (type.equals("NUMBER")) {
                            precision = precision > 20 ? 20 : precision;
                            fields.add(new DbfFieldDescription(name, nl.knaw.dans.common.dbflib.Type.NUMBER, precision, scale));
                        } else if (type.equals("DATE")) {
                            fields.add(new DbfFieldDescription(name, nl.knaw.dans.common.dbflib.Type.DATE, precision, scale));
                        } else {
                            logger.warn("No DBF field for " + name + " type: " + type);
                        }
                    }
                }
                return super.handleRow(rs);
            }
        }

        Handler h = new Handler();

        List<Map<String, Object>> result = run.query(
                conn, sql, h);

        if (result != null) {

            DbfTable dbfTable = new Table(h.fields);
            dbfTable.createTable(dbffile);

            try {
                for (Map<String, Object> row : result) {
                    dbfTable.recordRow(row);
                }
            } finally {
                dbfTable.close();
                DbUtils.close(conn);
            }
        }
    }


    private void save(java.awt.event.ActionEvent evt) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        properties.setProperty("URL", dbUrl.getText());
        properties.setProperty("Username", dbUsername.getText());
        properties.setProperty("Path", path.getText());
        properties.setProperty("Password", password.getText());
        properties.store(new FileOutputStream(file), "");
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainGUI().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class Table extends DbfTable {

        public Table(List<DbfFieldDescription> fields) {
            super(fields);
        }
    }

}

