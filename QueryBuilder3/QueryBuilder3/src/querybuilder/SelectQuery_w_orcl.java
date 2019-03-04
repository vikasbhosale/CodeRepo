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
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author VIKAS
 */
public class SelectQuery_w_orcl extends javax.swing.JPanel {

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
    private ResultSetMetaData rsmd;
    //private DefaultTableModel tbmod;
    private int first_tim_in=1;
    private int store_col_count;
    private DefaultListModel dim,dim2;
    private int justonce=1;
    private Vector vector,vector2;
    private String logic="";//for AND , OR condition
    private String op="";
    private String queryText="";
    private String orderText="";
    private JComboBox<String> col;
    private String existsText="";
    private String existsTextCond="";
    public SelectQuery_w_orcl() {
        initComponents();
        TableColumn tc1=fieldTable.getColumnModel().getColumn(0);        
        tc1.setPreferredWidth(5);
        try {
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
//              Class.forName("com.mysql.jdbc.Driver");
//              conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vikas", "root", "root");
                setConn(QBPanel1.getConn());
                //add value to schema combobox
                pstmt_schema=conn.prepareStatement("show schemas");
                ResultSet rs_schema=pstmt_schema.executeQuery();
                while(rs_schema.next())
                {
                    schema.addItem(rs_schema.getString(1));                
                    System.out.println(rs_schema.getString(1));
                }  
                //combobox in table cell
                String data[]={"AVG( )","COUNT( )","SUM( )","MAX( )","MIN( )"};
                JComboBox<String> fun=new JComboBox<>(data);
                TableColumn tc=table1.getColumnModel().getColumn(0);
                TableCellEditor tce=new DefaultCellEditor(fun);
                tc.setCellEditor(tce);
                //combobox in table cell           
                col=new JComboBox<>();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE"))
            {
                schemalb.setVisible(false);
                schema.setVisible(false);
                setConn(QBPanel1.getConn());
                
                pstmt_table=conn.prepareStatement("select table_name from user_tables where tablespace_name='SYSTEM' AND table_name not like '%$%' AND table_name not like '%$' AND table_name not like '$%'  AND table_name!='HELP' AND table_name!='SQLPLUS_PRODUCT_PROFILE'");
//                pstmt_table=conn.prepareStatement("select TABNAME from syscat.tables where tabschema!='VIKAS' AND tabschema!='SYSTOOLS' AND tabschema!='SYSSTAT' AND tabschema!='SYSIBM' AND tabschema!='SYSIBMADM' AND tabschema!='SYSPUBLIC' AND tabschema!='SYSCAT'");
                ResultSet rs_schema=pstmt_table.executeQuery();
                while(rs_schema.next())
                {
                    table.addItem(rs_schema.getString(1));                
                    System.out.println(rs_schema.getString(1));
                }
                //combobox in table cell
                String data[]={"AVG( )","COUNT( )","SUM( )","MAX( )","MIN( )"};
                JComboBox<String> fun=new JComboBox<>(data);
                TableColumn tc=table1.getColumnModel().getColumn(0);
                TableCellEditor tce=new DefaultCellEditor(fun);
                tc.setCellEditor(tce);
                //combobox in table cell           
                col=new JComboBox<>();
            }
            else if(StartWindow.getDatabase_string().equals("MS-Access"))
            {
                schemalb.setVisible(false);
                schema.setVisible(false);
                setConn(QBPanel1.getConn());
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
                String data[]={"AVG( )","COUNT( )","SUM( )","MAX( )","MIN( )"};
                JComboBox<String> fun=new JComboBox<>(data);
                TableColumn tc=table1.getColumnModel().getColumn(0);
                TableCellEditor tce=new DefaultCellEditor(fun);
                tc.setCellEditor(tce);                  
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

        jPanel15 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        andBtn = new javax.swing.JButton();
        oprator = new javax.swing.JComboBox();
        orBtn = new javax.swing.JButton();
        rest_txt = new javax.swing.JTextField();
        rest_field = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        groupByField = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        fun_field = new javax.swing.JComboBox();
        functionCombo = new javax.swing.JComboBox();
        havingOp = new javax.swing.JComboBox();
        havingValue = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        selectGroupBy = new javax.swing.JRadioButton();
        jPanel18 = new javax.swing.JPanel();
        orderByField = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        orderSelect = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderTextArea = new javax.swing.JTextArea();
        selectSqu = new javax.swing.JRadioButton();
        sequenceOfOrder = new javax.swing.JComboBox();
        schemalb = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        table = new javax.swing.JComboBox();
        schema = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fieldTable = new javax.swing.JTable();
        selectAll = new javax.swing.JButton();
        removeAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        addColumn = new javax.swing.JButton();
        removeColumn = new javax.swing.JButton();
        sub_DelExe = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(102, 102, 0));
        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel9.setBackground(new java.awt.Color(255, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restriction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16))); // NOI18N

        andBtn.setText("AND");
        andBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andBtnActionPerformed(evt);
            }
        });

        oprator.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        oprator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=", "IN" }));
        oprator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opratorActionPerformed(evt);
            }
        });

        orBtn.setText("OR");
        orBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orBtnActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Field Name");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("OP");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Value");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(orBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(andBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rest_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rest_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(andBtn)
                    .addComponent(orBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Condition", jPanel9);

        jPanel16.setBackground(new java.awt.Color(255, 204, 102));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Group By");

        jPanel17.setBackground(new java.awt.Color(153, 204, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Having Clause", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        functionCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AVG", "COUNT", "SUM", "MIN", "MAX" }));

        havingOp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        havingOp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=" }));
        havingOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                havingOpActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Value");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("OP");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Function");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Field Name");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText(")");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("(");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(functionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(fun_field, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(havingOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(havingValue)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fun_field, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(functionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(havingValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(havingOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        selectGroupBy.setBackground(new java.awt.Color(0, 204, 204));
        selectGroupBy.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        selectGroupBy.setForeground(new java.awt.Color(102, 0, 102));
        selectGroupBy.setText("OFF");
        selectGroupBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectGroupByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupByField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(selectGroupBy)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(groupByField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectGroupBy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Group By", jPanel16);

        jPanel18.setBackground(new java.awt.Color(153, 204, 255));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Order By");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Sequence");

        orderSelect.setText("Select");
        orderSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderSelectActionPerformed(evt);
            }
        });

        orderTextArea.setColumns(20);
        orderTextArea.setRows(5);
        jScrollPane4.setViewportView(orderTextArea);

        selectSqu.setBackground(new java.awt.Color(153, 204, 255));

        sequenceOfOrder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASC", "DESC" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(sequenceOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectSqu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orderSelect))
                            .addComponent(orderByField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addComponent(orderByField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectSqu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(orderSelect)
                        .addComponent(sequenceOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Order By", jPanel18);

        schemalb.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        schemalb.setText("Select Schema");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel20.setText("Select Table");

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

        fieldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Field Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(fieldTable);

        selectAll.setText("Select All");
        selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllActionPerformed(evt);
            }
        });

        removeAll.setText("De-select All");
        removeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeAll))
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectAll)
                .addGap(18, 18, 18)
                .addComponent(removeAll)
                .addGap(59, 59, 59))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Field", jPanel1);

        table1.setBackground(new java.awt.Color(153, 153, 0));
        table1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FUNCTION ", "Column Name", "Aliase Name", "DISTINCT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table1.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(table1);

        addColumn.setText("Add Column");
        addColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColumnActionPerformed(evt);
            }
        });

        removeColumn.setText("Remove");
        removeColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeColumnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addColumn)
                .addGap(18, 18, 18)
                .addComponent(removeColumn)
                .addContainerGap(59, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Function", jPanel2);

        sub_DelExe.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        sub_DelExe.setText("Run");
        sub_DelExe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_DelExeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(schemalb, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sub_DelExe)
                .addGap(41, 41, 41))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schemalb)
                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sub_DelExe)
                .addContainerGap())
        );

        add(jPanel15);
    }// </editor-fold>//GEN-END:initComponents

    private void andBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andBtnActionPerformed
        if(rest_field.getSelectedItem()==null || rest_txt.getText().equals(""))
        {
//            err_msg.setText("Plz Select Field & Text Field ...");
            JOptionPane.showMessageDialog(null, "Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+rest_field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
        op=" AND ";
        logic=logic+op;
        rest_txt.setText("");
        rest_field.setSelectedIndex(0);
    }//GEN-LAST:event_andBtnActionPerformed

    private void opratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opratorActionPerformed

    }//GEN-LAST:event_opratorActionPerformed

    private void orBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orBtnActionPerformed
        if(rest_field.getSelectedItem()==null || rest_txt.getText().equals(""))
        {
//            err_msg.setText("Plz Select Field & Text Field ...");
            JOptionPane.showMessageDialog(null, "Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+rest_field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
        op=" OR ";
        logic=logic+op;
        rest_txt.setText("");
        rest_field.setSelectedIndex(0);
    }//GEN-LAST:event_orBtnActionPerformed

    private void havingOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_havingOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_havingOpActionPerformed

    private void selectGroupByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectGroupByActionPerformed
        if(selectGroupBy.isSelected())
        {
            System.out.println("I am ON");
            selectGroupBy.setText(" ON ");
        }
        else
        {
            selectGroupBy.setText("OFF");
        }
    }//GEN-LAST:event_selectGroupByActionPerformed

    private void orderSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderSelectActionPerformed

        if(selectSqu.isSelected())
        {
            orderText=orderText+orderByField.getSelectedItem().toString()+" "+sequenceOfOrder.getSelectedItem().toString()+",";
        }
        else
        {
            orderText=orderText+orderByField.getSelectedItem().toString()+",";
        }
        //orderText=orderText.substring(0,orderText.length()-1);
        orderTextArea.setText(orderText.substring(0,orderText.length()-1));
    }//GEN-LAST:event_orderSelectActionPerformed

    private void tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableActionPerformed
        try
        {
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                orderByField.removeAllItems();
            groupByField.removeAllItems();
            col.removeAllItems();
            DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
            String table_name=table.getSelectedItem().toString();           
            String schema_name=schema.getSelectedItem().toString();
            //            innertable.setText(table_name);
            //add all table name to tablecomb combobox from vikas schema
            pstmt_field=getConn().prepareStatement("Select * from "+schema_name+"."+table_name);
            ResultSet rs_field=pstmt_field.executeQuery();
            rsmd=rs_field.getMetaData();
            column_count=rsmd.getColumnCount();
            System.out.println(rsmd.getColumnCount());
            if(first_tim_in >= 2)
            {
                for (int i = 1; i <= store_col_count; i++)
                {
                    System.out.println(" I am  in"+i);
                    tbmod.removeRow(0);
                }
            }
            first_tim_in++;
            rest_field.removeAllItems();//remove all element

            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString()});
                //add value to field
                rest_field.addItem(rsmd.getColumnName(i));
                orderByField.addItem(rsmd.getColumnName(i));
                groupByField.addItem(rsmd.getColumnName(i));
                col.addItem(rsmd.getColumnName(i));
            }
            store_col_count=column_count;
            System.out.println("Store = "+store_col_count);
            //fieldtable.setModel(tbmod);
            TableColumn tc2=table1.getColumnModel().getColumn(1);
            TableCellEditor tce2=new DefaultCellEditor(col);
            tc2.setCellEditor(tce2);
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                orderByField.removeAllItems();
            groupByField.removeAllItems();
            //combobox in table cell           
            col=new JComboBox<>();
            col.removeAllItems();
            DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
            String table_name=table.getSelectedItem().toString();                      
            //            innertable.setText(table_name);
            //add all table name to tablecomb combobox from vikas schema
            pstmt_field=getConn().prepareStatement("Select * from "+table_name);
            ResultSet rs_field=pstmt_field.executeQuery();
            rsmd=rs_field.getMetaData();
            column_count=rsmd.getColumnCount();
            System.out.println(rsmd.getColumnCount());
            if(first_tim_in >= 2)
            {
                for (int i = 1; i <= store_col_count; i++)
                {
                    System.out.println(" I am  in"+i);
                    tbmod.removeRow(0);
                }
            }
            first_tim_in++;
            rest_field.removeAllItems();//remove all element

            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString()});
                //add value to field
                rest_field.addItem(rsmd.getColumnName(i));
                orderByField.addItem(rsmd.getColumnName(i));
                groupByField.addItem(rsmd.getColumnName(i));
                col.addItem(rsmd.getColumnName(i));
            }
            store_col_count=column_count;
            System.out.println("Store = "+store_col_count);
            //fieldtable.setModel(tbmod);
            TableColumn tc2=table1.getColumnModel().getColumn(1);
            TableCellEditor tce2=new DefaultCellEditor(col);
            tc2.setCellEditor(tce2);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableActionPerformed

    private void schemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schemaActionPerformed
        try {
            System.out.println("schema combo");
            table.removeAllItems();
            String schema_name=schema.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_table=getConn().prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA=?");
            pstmt_table.setString(1, schema_name);
            ResultSet rs_table=pstmt_table.executeQuery();
            //remove all element before add
            //table.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schemaActionPerformed

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(true, i-1, 0);

        }
    }//GEN-LAST:event_selectAllActionPerformed

    private void removeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(false, i-1, 0);
        }
    }//GEN-LAST:event_removeAllActionPerformed

    private void addColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table1.getModel();
        tbmod.addRow(new Object[]{"","","",false});
    }//GEN-LAST:event_addColumnActionPerformed

    private void removeColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table1.getModel();
        if(table1.getSelectedRow()==-1)
        {
            if(table1.getColumnCount()==0)
            {
//                err_msg.setText("Table is Empty ....");
                JOptionPane.showMessageDialog(null, "Table is Empty ....");
            }
            else
            {
//                err_msg.setText("You must select one row ....");
                JOptionPane.showMessageDialog(null, "You must select one row ....");
            }
        }
        else
        {
            tbmod.removeRow(table1.getSelectedRow());
        }
    }//GEN-LAST:event_removeColumnActionPerformed

    private void sub_DelExeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_DelExeActionPerformed
        try {                        
            //for inner Query
            String innerQuery="";
            String selectedfield_data="";
            DefaultTableModel tbmod1=(DefaultTableModel) fieldTable.getModel();
            boolean selected[]=new boolean[tbmod1.getRowCount()];
            for (int i = 0; i < tbmod1.getRowCount(); i++)
            {
                selected[i]=(boolean) tbmod1.getValueAt(i, 0);
                //System.out.println(selected[i]);
            }
            for(int i = 0; i < selected.length; i++)
            {
                if(selected[i]==true)
                {
                    selectedfield_data=selectedfield_data+tbmod1.getValueAt(i, 1)+",";                    
                }
            } 
            if(selectedfield_data.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Plz Select Field from Table ...");
                return;
            }            
            else
            {
                selectedfield_data=selectedfield_data.substring(0, selectedfield_data.length()-1);
            }
            System.out.println(selectedfield_data);
            //for using Function
            String fun_string="";
            DefaultTableModel tbmod=(DefaultTableModel)table1.getModel();
            for(int i=0;i<table1.getRowCount();i++)
            {
                String fun=tbmod.getValueAt(i, 0).toString();
                fun=fun.substring(0, fun.length()-3);
                if((boolean)tbmod.getValueAt(i, 3))
                {
                    fun_string=fun_string+fun+"( DISTINCT "+tbmod.getValueAt(i, 1)+" ) AS "+tbmod.getValueAt(i, 2)+",";
                }
                else
                {
                    fun_string=fun_string+fun+"( "+tbmod.getValueAt(i, 1)+" ) AS "+tbmod.getValueAt(i, 2)+",";
                }
            }
            if(!fun_string.equals(""))
            fun_string=fun_string.substring(0, fun_string.length()-1);
            System.out.println(fun_string);
            String table_full_name="";
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                table_full_name=schema.getSelectedItem()+"."+table.getSelectedItem();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                table_full_name=table.getSelectedItem().toString();
            }
            if((rest_txt.getText().equals("")) && (!orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("order by");                
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt.getText().equals("")) && (!orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("order by and condition");
                op="";
                logic=logic+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" "+rest_txt.getText();
                System.out.println(logic);                
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt.getText().equals("")) && (orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("condition");
                op="";
                logic=logic+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" "+rest_txt.getText();
                System.out.println(logic);                
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic;
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic;
                    System.out.println("Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic);
                }
            }
            else if((rest_txt.getText().equals("")) && (!orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("order by & Group By");                
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt.getText().equals("")) && (!orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("order by and condition & Group By");
                op="";
                logic=logic+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" "+rest_txt.getText();
                System.out.println(logic);
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt.getText().equals("")) && (orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("condition & Group By");
                op="";
                logic=logic+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" "+rest_txt.getText();
                System.out.println(logic);
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;                    
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
            }
            else if((rest_txt.getText().equals("")) && (orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println(" Group By");                  
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;                   
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;                  
                    System.out.println("Select "+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;                   
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
            }
            else
            {                              
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name+existsTextCond);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name+existsTextCond);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name+existsTextCond);
                }
            }
            System.out.println(innerQuery);
            pstmt=getConn().prepareStatement(innerQuery);  
//            pstmt=getConn().prepareStatement("select tabschema, TABNAME from syscat.tables");  
            ResultSet rs_field=pstmt.executeQuery();
            //print Query on Query Pane in QBPanel1
            QBPanel1.setQueryString(innerQuery);
            ResultSetMetaData rsmd=rs_field.getMetaData();
            int no_of_column=rsmd.getColumnCount();
            //for row & column data
            Vector<String> column=new Vector<String>();
            for(int i=1;i<=no_of_column;i++)					
            {
                column.addElement(rsmd.getColumnLabel(i));
            }
            
            Vector<Object> row=new Vector<Object>();
            int row_count=0;
            while (rs_field.next())
            {
                row_count++;
                Vector<Object> data=new Vector<Object>(no_of_column);
                for(int i=1;i<=no_of_column;i++)
                {
                    data.addElement(rs_field.getObject(i));
                }
                row.addElement(data);
            }
            //table construction
            DefaultTableModel tm=new DefaultTableModel(row, column)
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    //all cells false
                    return false;
                }
            };
            TableShow ts=new TableShow();
            ts.setVisible(true);
            ts.createTable(tm);         
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            logic="";
            rest_field.enable();
            oprator.enable();
        }
    }//GEN-LAST:event_sub_DelExeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addColumn;
    private javax.swing.JButton andBtn;
    private javax.swing.JTable fieldTable;
    private javax.swing.JComboBox fun_field;
    private javax.swing.JComboBox functionCombo;
    private javax.swing.JComboBox groupByField;
    private javax.swing.JComboBox havingOp;
    private javax.swing.JTextField havingValue;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JComboBox oprator;
    private javax.swing.JButton orBtn;
    private javax.swing.JComboBox orderByField;
    private javax.swing.JButton orderSelect;
    private javax.swing.JTextArea orderTextArea;
    private javax.swing.JButton removeAll;
    private javax.swing.JButton removeColumn;
    private javax.swing.JComboBox rest_field;
    private javax.swing.JTextField rest_txt;
    private javax.swing.JComboBox schema;
    private javax.swing.JLabel schemalb;
    private javax.swing.JButton selectAll;
    private javax.swing.JRadioButton selectGroupBy;
    private javax.swing.JRadioButton selectSqu;
    private javax.swing.JComboBox sequenceOfOrder;
    private javax.swing.JButton sub_DelExe;
    private javax.swing.JComboBox table;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
