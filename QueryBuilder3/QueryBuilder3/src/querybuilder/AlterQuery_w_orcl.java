/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import static querybuilder.SelectQuery_w_orcl.getConn;
import sun.util.calendar.JulianCalendar;

/**
 *
 * @author vikas
 */
public class AlterQuery_w_orcl extends javax.swing.JPanel {

    private static Connection conn;

    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * @param aConn the conn to set
     */
    public static void setConn(Connection aConn) {
        conn = aConn;
    }
    private ResultSet rs;
    private PreparedStatement pstmt,pstmt_schema,pstmt_table,pstmt_field;
    private int column_count;
    private String queryTest="";
    private int justonce=1;
    private Vector vector,vector2;
    private String logic="";//for AND , OR condition
    private String op="";
    private String queryText="";
    private String orderText="";
    private JComboBox<String> col;
    public AlterQuery_w_orcl() {
        initComponents();
        
        try {
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                setConn(QBPanel1.getConn());
                buttonGroup1.add(addBtn);
                buttonGroup1.add(modifyBtn);
                buttonGroup1.add(dropBtn);
                buttonGroup1.add(renameBtn);
                //add value to schema combobox
                pstmt_schema=conn.prepareStatement("show schemas");
                ResultSet rs_schema=pstmt_schema.executeQuery();
                while(rs_schema.next())
                {
                    schema.addItem(rs_schema.getString(1));
                    System.out.println(rs_schema.getString(1));
                }
                //combobox in table cell
                String data[]={"VARCHAR(45)","INTEGER(45)","DOUBLE","LONGBLOB","LONGTEXT"};
                JComboBox<String> com=new JComboBox<>(data);
                TableColumn tc1=table_add.getColumnModel().getColumn(1);
                TableColumn tc2=table_modify.getColumnModel().getColumn(1);
                TableCellEditor tce=new DefaultCellEditor(com);
                tc1.setCellEditor(tce);
                tc2.setCellEditor(tce);
                //combobox in table cell           
                col=new JComboBox<>();
                //make disable component before select radio button
                table_modify.setEnabled(false);            
                table_add.setEnabled(false);
                rest_field.setEnabled(false);
                rest_field_rename.setEnabled(false);
                rename_txt.setEnabled(false);
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE"))
            {
                schemalb.setVisible(false);
                schema.setVisible(false);
                setConn(QBPanel1.getConn());
                buttonGroup1.add(addBtn);
                buttonGroup1.add(modifyBtn);
                buttonGroup1.add(dropBtn);
                buttonGroup1.add(renameBtn);
                //add value to schema combobox
                pstmt_table=conn.prepareStatement("select table_name from user_tables where tablespace_name='SYSTEM' AND table_name not like '%$%' AND table_name not like '%$' AND table_name not like '$%'  AND table_name!='HELP' AND table_name!='SQLPLUS_PRODUCT_PROFILE'");
                ResultSet rs_schema=pstmt_table.executeQuery();
                while(rs_schema.next())
                {
                  	table.addItem(rs_schema.getString(1));
                	System.out.println(rs_schema.getString(1));
                }
                //combobox in table cell
                String data[]={"VARCHAR2(45)","NUMBER(10)","BLOB","DATE"};
                JComboBox<String> com=new JComboBox<>(data);
                TableColumn tc1=table_add.getColumnModel().getColumn(1);
                TableColumn tc2=table_modify.getColumnModel().getColumn(1);
                TableCellEditor tce=new DefaultCellEditor(com);
                tc1.setCellEditor(tce);
                tc2.setCellEditor(tce);
                
                //make disable component before select radio button
                table_modify.setEnabled(false);            
                table_add.setEnabled(false);
                rest_field.setEnabled(false);
                rest_field_rename.setEnabled(false);
                rename_txt.setEnabled(false);
            }            
            else if(StartWindow.getDatabase_string().equals("MS-Access"))
            {
                schemalb.setVisible(false);
                schema.setVisible(false);
                setConn(QBPanel1.getConn());
                buttonGroup1.add(addBtn);
                buttonGroup1.add(modifyBtn);
                buttonGroup1.add(dropBtn);
                buttonGroup1.add(renameBtn);
                DatabaseMetaData md=getConn().getMetaData();
                ResultSet rs_schema=md.getTables(null, null, "%", null);
                String temp="";
                while(rs_schema.next())
                {
                    temp = rs_schema.getString(3);
                    if(!temp.contains("MSys"))
                    {
                        table.addItem(temp);                
                        System.out.println(temp);
                    }                                        
		}
                //combobox in table cell
                String data[]={"Number","Text","CHAR(45)"};
//                String data[]={"double","char(45)"};
                JComboBox<String> com=new JComboBox<>(data);
                TableColumn tc1=table_add.getColumnModel().getColumn(1);
                TableColumn tc2=table_modify.getColumnModel().getColumn(1);
                TableCellEditor tce=new DefaultCellEditor(com);
                tc1.setCellEditor(tce);
                tc2.setCellEditor(tce);
                
                //make disable component before select radio button
                table_modify.setEnabled(false);            
                table_add.setEnabled(false);
                rest_field.setEnabled(false);
                rest_field_rename.setEnabled(false);
                rename_txt.setEnabled(false);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        schemalb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        table = new javax.swing.JComboBox();
        schema = new javax.swing.JComboBox();
        renameBtn = new javax.swing.JRadioButton();
        addBtn = new javax.swing.JRadioButton();
        dropBtn = new javax.swing.JRadioButton();
        modifyBtn = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_add = new javax.swing.JTable();
        addColbtn = new javax.swing.JButton();
        deleteColbtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_modify = new javax.swing.JTable();
        addColbtn1 = new javax.swing.JButton();
        deleteColbtn1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        rest_field = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        rest_field_rename = new javax.swing.JComboBox();
        rename_txt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alterBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 153));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alert Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        schemalb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        schemalb.setText("Select Schema");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Select Table");

        table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableActionPerformed(evt);
            }
        });

        schema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemaActionPerformed(evt);
            }
        });

        renameBtn.setBackground(new java.awt.Color(153, 153, 153));
        renameBtn.setText("RENAME");
        renameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(153, 153, 153));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        dropBtn.setBackground(new java.awt.Color(153, 153, 153));
        dropBtn.setText("DROP");
        dropBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropBtnActionPerformed(evt);
            }
        });

        modifyBtn.setBackground(new java.awt.Color(153, 153, 153));
        modifyBtn.setText("MODIFY");
        modifyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyBtnActionPerformed(evt);
            }
        });

        table_add.setBackground(new java.awt.Color(153, 153, 0));
        table_add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table_add.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Datatype"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_add.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table_add.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_add.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_add);

        addColbtn.setText("Add Column");
        addColbtn.setAutoscrolls(true);
        addColbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColbtnActionPerformed(evt);
            }
        });

        deleteColbtn.setText("Delete Column");
        deleteColbtn.setAutoscrolls(true);
        deleteColbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteColbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteColbtn)
                    .addComponent(addColbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addColbtn)
                .addGap(18, 18, 18)
                .addComponent(deleteColbtn)
                .addContainerGap(38, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ADD", jPanel3);

        table_modify.setBackground(new java.awt.Color(153, 153, 0));
        table_modify.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table_modify.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Datatype", "Not-Null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_modify.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table_modify.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_modify.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_modify);

        addColbtn1.setText("Add Column");
        addColbtn1.setAutoscrolls(true);
        addColbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColbtn1ActionPerformed(evt);
            }
        });

        deleteColbtn1.setText("Delete Column");
        deleteColbtn1.setAutoscrolls(true);
        deleteColbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteColbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteColbtn1)
                    .addComponent(addColbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addColbtn1)
                .addGap(18, 18, 18)
                .addComponent(deleteColbtn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MODIFY", jPanel4);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setText("Select Column");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DROP", jPanel5);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel19.setText("OLD Column Name");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel30.setText("New Column Name");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("=");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(rest_field_rename, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(rename_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel30)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rename_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rest_field_rename, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RENAME", jPanel6);

        alterBtn.setText("Alter");
        alterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(addBtn)
                        .addGap(18, 18, 18)
                        .addComponent(modifyBtn)
                        .addGap(18, 18, 18)
                        .addComponent(dropBtn)
                        .addGap(18, 18, 18)
                        .addComponent(renameBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(schemalb, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(alterBtn)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(schemalb))
                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2))
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renameBtn)
                    .addComponent(modifyBtn)
                    .addComponent(dropBtn)
                    .addComponent(addBtn))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alterBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableActionPerformed
        try {           
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                rest_field.removeAllItems();
                rest_field_rename.removeAllItems();           
                col.removeAllItems();
//              String table_name=table.getSelectedItem().toString();
//              String schema_name=schema.getSelectedItem().toString();
                String table_full_name="";
                table_full_name=schema.getSelectedItem().toString()+"."+table.getSelectedItem().toString();
                System.out.println(table_full_name);
                //add all table name to tablecomb combobox from vikas schema
                pstmt_field=getConn().prepareStatement("Select * from "+table_full_name);
                ResultSet rs_field=pstmt_field.executeQuery();
                ResultSetMetaData rsmd=rs_field.getMetaData();
                column_count=rsmd.getColumnCount();            
                for(int i=1;i<=column_count;i++)
                {                
                    rest_field.addItem(rsmd.getColumnName(i));
                    rest_field_rename.addItem(rsmd.getColumnName(i));
                    col.addItem(rsmd.getColumnName(i));
                    System.out.println(rsmd.getColumnName(i));
                }           
                //TableColumn tc1=table_add.getColumnModel().getColumn(0);
                TableColumn tc2=table_modify.getColumnModel().getColumn(0);
                TableCellEditor tce2=new DefaultCellEditor(col);
                //tc1.setCellEditor(tce2);
                tc2.setCellEditor(tce2); 
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                rest_field.removeAllItems();
                rest_field_rename.removeAllItems();
                //combobox in table cell           
                col=new JComboBox<>();
                col.removeAllItems();
//              String table_name=table.getSelectedItem().toString();
//              String schema_name=schema.getSelectedItem().toString();
                String table_full_name="";
                table_full_name=table.getSelectedItem().toString();
                System.out.println(table_full_name);
                //add all table name to tablecomb combobox from vikas schema
                pstmt_field=getConn().prepareStatement("Select * from "+table_full_name);
                ResultSet rs_field=pstmt_field.executeQuery();
                ResultSetMetaData rsmd=rs_field.getMetaData();
                column_count=rsmd.getColumnCount();            
                for(int i=1;i<=column_count;i++)
                {                
                    rest_field.addItem(rsmd.getColumnName(i));
                    rest_field_rename.addItem(rsmd.getColumnName(i));
                    col.addItem(rsmd.getColumnName(i));
                    System.out.println(rsmd.getColumnName(i));
                }           
                //TableColumn tc1=table_add.getColumnModel().getColumn(0);
                TableColumn tc2=table_modify.getColumnModel().getColumn(0);
                TableCellEditor tce2=new DefaultCellEditor(col);
                //tc1.setCellEditor(tce2);
                tc2.setCellEditor(tce2); 
            }                       
        } catch (SQLException ex) {
            Logger.getLogger(SelectQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableActionPerformed

    private void schemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schemaActionPerformed
        try {

            System.out.println("schema combo");
            String schema_name=schema.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_table=getConn().prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA=?");
            pstmt_table.setString(1, schema_name);
            ResultSet rs_table=pstmt_table.executeQuery();
            //remove all element before add
            table.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
            /*
            System.out.println("schema combo");
            String schema1=schema.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_table=conn.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA=?");
            pstmt_table.setString(1, schema1);
            ResultSet rs_table=pstmt_table.executeQuery();
            //remove all element before add
            table.removeAllItems();//this will remove all item from table
            field.removeAll();
            while(rs_table.next())
            {
                table.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }*/
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schemaActionPerformed

    private void modifyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyBtnActionPerformed
        addColbtn1.setEnabled(true);
        table_modify.setEnabled(true);
        jTabbedPane1.setSelectedIndex(1);
        table_add.setEnabled(false);
        addColbtn.setEnabled(false);
        rest_field.setEnabled(false);
        rest_field_rename.setEnabled(false);
        rename_txt.setEnabled(false);
    }//GEN-LAST:event_modifyBtnActionPerformed

    private void addColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_add.getModel();
        tbmod.addRow(new Object[]{"","",false,false});
    }//GEN-LAST:event_addColbtnActionPerformed

    private void deleteColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_add.getModel();
        if(table_add.getSelectedRow()==-1)
        {
            if(table_add.getColumnCount()==0)
            {
//                err_msg.setText("Table is Empty ....");
            }
            else
            {
//                err_msg.setText("You must select one row ....");
            }
        }
        else
        {
            tbmod.removeRow(table_add.getSelectedRow());
        }
    }//GEN-LAST:event_deleteColbtnActionPerformed

    private void addColbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColbtn1ActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_modify.getModel();
        tbmod.addRow(new Object[]{"","",false,false});
    }//GEN-LAST:event_addColbtn1ActionPerformed

    private void deleteColbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteColbtn1ActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_modify.getModel();
        if(table_modify.getSelectedRow()==-1)
        {
            if(table_modify.getColumnCount()==0)
            {
//                err_msg.setText("Table is Empty ....");
            }
            else
            {
//                err_msg.setText("You must select one row ....");
            }
        }
        else
        {
            tbmod.removeRow(table_modify.getSelectedRow());
        }
    }//GEN-LAST:event_deleteColbtn1ActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        table_add.setEnabled(true);
        addColbtn.setEnabled(true);
        jTabbedPane1.setSelectedIndex(0);
        table_modify.setEnabled(false);
        addColbtn1.setEnabled(false);       
        rest_field.setEnabled(false);
        rest_field_rename.setEnabled(false);
        rename_txt.setEnabled(false);
    }//GEN-LAST:event_addBtnActionPerformed

    private void dropBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropBtnActionPerformed
        rest_field.setEnabled(true);
        jTabbedPane1.setSelectedIndex(2);
        table_add.setEnabled(false);
        table_modify.setEnabled(false);        
        rest_field_rename.setEnabled(false);
        rename_txt.setEnabled(false);
    }//GEN-LAST:event_dropBtnActionPerformed

    private void renameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameBtnActionPerformed
        rest_field_rename.setEnabled(true);
        rename_txt.setEnabled(true);
        jTabbedPane1.setSelectedIndex(3);
        table_add.setEnabled(false);
        table_modify.setEnabled(false);        
        rest_field.setEnabled(false);       
    }//GEN-LAST:event_renameBtnActionPerformed

    private void alterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterBtnActionPerformed
        try 
        {            
            String addAlterData="";
            String modifyAlterData="";
            String dropAlterData="";
            String renameAlterData="";
            DefaultTableModel tbmod=(DefaultTableModel)table_modify.getModel();
            
            String table_full_name="";
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                table_full_name=schema.getSelectedItem().toString()+"."+table.getSelectedItem().toString();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                table_full_name=table.getSelectedItem().toString();
            }
            System.out.println(table_full_name);
            if(addBtn.isSelected())
            {
                for(int i=0;i<table_add.getRowCount();i++)
                {
                    addAlterData=addAlterData+table_add.getValueAt(i, 0)+" "+table_add.getValueAt(i, 1)+",";
                }
                addAlterData=addAlterData.substring(0, addAlterData.length()-1);
                queryTest="ALTER TABLE "+table_full_name+" ADD ("+addAlterData+")";
            }
            else if(modifyBtn.isSelected())
            {
                for(int i=0;i<table_add.getRowCount();i++)
                {                    
                    if((boolean)tbmod.getValueAt(i, 2))
                    {
                        modifyAlterData=modifyAlterData+table_add.getValueAt(i, 0)+" "+table_add.getValueAt(i, 1)+" NOT NULL,";
                    }
                    else
                    {
                        modifyAlterData=modifyAlterData+table_add.getValueAt(i, 0)+" "+table_add.getValueAt(i, 1)+",";
                    }
                }
                modifyAlterData=modifyAlterData.substring(0, modifyAlterData.length()-1);
                queryTest="ALTER TABLE "+table_full_name+" MODIFY ("+modifyAlterData+")";
            }
            else if(dropBtn.isSelected())
            {
                dropAlterData=rest_field.getSelectedItem().toString();
                queryTest="ALTER TABLE "+table_full_name+" DROP COLUMN "+dropAlterData;
            }
            else if(renameBtn.isSelected())
            {
                renameAlterData=rest_field_rename.getSelectedItem().toString()+" TO "+rename_txt.getText();
                queryTest="ALTER TABLE "+table_full_name+" RENAME COLUMN "+renameAlterData;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Plz Select above button ...");
                return;
            }              
            System.out.println(queryTest);
            pstmt=getConn().prepareStatement(queryTest);
            int i=pstmt.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null, " Column is not insert . . .");
            }
            else
            {
                JOptionPane.showMessageDialog(null, " Column is insert . . .");
                QBPanel1.setQueryString(queryTest);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            queryTest="";
        }
    }//GEN-LAST:event_alterBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton addBtn;
    private javax.swing.JButton addColbtn;
    private javax.swing.JButton addColbtn1;
    private javax.swing.JButton alterBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteColbtn;
    private javax.swing.JButton deleteColbtn1;
    private javax.swing.JRadioButton dropBtn;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton modifyBtn;
    private javax.swing.JRadioButton renameBtn;
    private javax.swing.JTextField rename_txt;
    private javax.swing.JComboBox rest_field;
    private javax.swing.JComboBox rest_field_rename;
    private javax.swing.JComboBox schema;
    private javax.swing.JLabel schemalb;
    private javax.swing.JComboBox table;
    private javax.swing.JTable table_add;
    private javax.swing.JTable table_modify;
    // End of variables declaration//GEN-END:variables
}
