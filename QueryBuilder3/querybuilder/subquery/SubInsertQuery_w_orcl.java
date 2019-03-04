/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder.subquery;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
import querybuilder.QBPanel1;
import querybuilder.StartWindow;

/**
 *
 * @author vikas
 */
public class SubInsertQuery_w_orcl extends javax.swing.JPanel {

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
    //for outter select sub query
    private int first_tim_in_out=1;
    private int store_col_count_out;
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
    public SubInsertQuery_w_orcl() {
        initComponents();        
        TableColumn tc1=fieldTable_in.getColumnModel().getColumn(0);        
        tc1.setPreferredWidth(5);
        TableColumn tc2=fieldTable.getColumnModel().getColumn(0);        
        tc2.setPreferredWidth(5);
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vikas", "root", "root");
//            setConn(QBPanel1.getConn());
            buttonGroup1.add(allBtn);
            buttonGroup1.add(anyBtn);
//            buttonGroup2.add(existsBtn);
//            buttonGroup2.add(nexistsBtn);  
            allBtn.setVisible(false);
            anyBtn.setVisible(false);
            allAnyPanel.setVisible(false);
//            jTabbedPane4.remove(3);
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                setConn(QBPanel1.getConn());
                //add value to schema combobox
                pstmt_schema=conn.prepareStatement("show schemas");
		ResultSet rs_schema=pstmt_schema.executeQuery();
		while(rs_schema.next())
		{
                    schema.addItem(rs_schema.getString(1));
                    schema_in.addItem(rs_schema.getString(1));
                    schema_outter.addItem(rs_schema.getString(1));
                    System.out.println(rs_schema.getString(1));
		}
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE"))
            {
                schemalb_outter.setVisible(false);
                schema_outter.setVisible(false);
                schemalb.setVisible(false);
                schema.setVisible(false);
                schemalb_in.setVisible(false);
                schema_in.setVisible(false);
                setConn(QBPanel1.getConn());
                //add value to schema combobox
                pstmt_schema=conn.prepareStatement("select table_name from user_tables where tablespace_name='SYSTEM' AND table_name not like '%$%' AND table_name not like '%$' AND table_name not like '$%'  AND table_name!='HELP' AND table_name!='SQLPLUS_PRODUCT_PROFILE'");
		ResultSet rs_schema=pstmt_schema.executeQuery();
		while(rs_schema.next())
		{
                    table.addItem(rs_schema.getString(1));
		    table_in.addItem(rs_schema.getString(1));
                    table_outter.addItem(rs_schema.getString(1));
                    System.out.println(rs_schema.getString(1));
		}
            }  
            else if(StartWindow.getDatabase_string().equals("MS-Access"))
            {
                schemalb_outter.setVisible(false);
                schema_outter.setVisible(false);
                schemalb.setVisible(false);
                schema.setVisible(false);
                schemalb_in.setVisible(false);
                schema_in.setVisible(false);
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
                        table_in.addItem(temp);
                        table_outter.addItem(temp);
                        System.out.println(temp);
                    }                                        
		}
            }   
            //combobox in table cell
            String data[]={"AVG( )","COUNT( )","SUM( )","MAX( )","MIN( )"};
            JComboBox<String> fun=new JComboBox<>(data);
            TableColumn tc=table1.getColumnModel().getColumn(0);
            TableCellEditor tce=new DefaultCellEditor(fun);
            tc.setCellEditor(tce);            
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
        jPanel23 = new javax.swing.JPanel();
        err_msg = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        andBtn2 = new javax.swing.JButton();
        oprator_in = new javax.swing.JComboBox();
        orBtn2 = new javax.swing.JButton();
        rest_txt_in = new javax.swing.JTextField();
        rest_field_in = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
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
        schemalb_in = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        table_in = new javax.swing.JComboBox();
        schema_in = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fieldTable_in = new javax.swing.JTable();
        selectAll = new javax.swing.JButton();
        removeAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        addColumn = new javax.swing.JButton();
        removeColumn = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        rest_field = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        oprator = new javax.swing.JComboBox();
        allAnyPanel = new javax.swing.JPanel();
        allBtn = new javax.swing.JRadioButton();
        anyBtn = new javax.swing.JRadioButton();
        allOrAnyEnable = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        existsBtn = new javax.swing.JRadioButton();
        nexistsBtn = new javax.swing.JRadioButton();
        sub_DelExe = new javax.swing.JButton();
        schema_panel = new javax.swing.JPanel();
        table = new javax.swing.JComboBox();
        schemalb = new javax.swing.JLabel();
        schema = new javax.swing.JComboBox();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        fieldTable = new javax.swing.JTable();
        selectAll_out = new javax.swing.JButton();
        removeAll_out = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_out = new javax.swing.JTable();
        addColumn_out = new javax.swing.JButton();
        removeColumn_out = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        schema_outter = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        schemalb_outter = new javax.swing.JLabel();
        table_outter = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sub-Insert Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        err_msg.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        err_msg.setForeground(new java.awt.Color(51, 51, 255));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Where Clause", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inner Query", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 153, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restriction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16))); // NOI18N

        andBtn2.setText("AND");
        andBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andBtnActionPerformed(evt);
            }
        });

        oprator_in.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        oprator_in.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=", "IN" }));
        oprator_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oprator_inActionPerformed(evt);
            }
        });

        orBtn2.setText("OR");
        orBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orBtnActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setText("Field Name");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setText("OP");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setText("Value");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rest_field_in, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(orBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(andBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(oprator_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rest_txt_in, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rest_txt_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oprator_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rest_field_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(andBtn2)
                    .addComponent(orBtn2))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Condition", jPanel13);

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

        jTabbedPane7.addTab("Group By", jPanel16);

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

        jTabbedPane7.addTab("Order By", jPanel18);

        schemalb_in.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        schemalb_in.setText("Select Schema");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel20.setText("Select Table");

        table_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table_inActionPerformed(evt);
            }
        });

        schema_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schema_inActionPerformed(evt);
            }
        });

        fieldTable_in.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(fieldTable_in);

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
                .addContainerGap(19, Short.MAX_VALUE)
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
                .addContainerGap(67, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Function", jPanel2);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(schema_in, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(table_in, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(schemalb_in, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(115, 115, 115))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(schemalb_in)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schema_in, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setText("Select Column");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel28.setText("Select Oprator");

        oprator.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        oprator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=", "IN" }));
        oprator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opratorActionPerformed(evt);
            }
        });

        allBtn.setText("ALL");

        anyBtn.setText("ANY");

        javax.swing.GroupLayout allAnyPanelLayout = new javax.swing.GroupLayout(allAnyPanel);
        allAnyPanel.setLayout(allAnyPanelLayout);
        allAnyPanelLayout.setHorizontalGroup(
            allAnyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allAnyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(allBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(anyBtn)
                .addContainerGap())
        );
        allAnyPanelLayout.setVerticalGroup(
            allAnyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allAnyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(allBtn)
                .addComponent(anyBtn))
        );

        allOrAnyEnable.setText("ALL or ANY");
        allOrAnyEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allOrAnyEnableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allAnyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(allOrAnyEnable)
                        .addGap(12, 12, 12)))
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rest_field, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(allOrAnyEnable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(allAnyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Condition", jPanel6);

        existsBtn.setText("EXISTS");
        existsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existsBtnActionPerformed(evt);
            }
        });

        nexistsBtn.setText("NOT EXISTS");
        nexistsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nexistsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(existsBtn)
                .addGap(18, 18, 18)
                .addComponent(nexistsBtn)
                .addGap(159, 159, 159))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existsBtn)
                    .addComponent(nexistsBtn))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("EXISTS", jPanel5);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sub_DelExe.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        sub_DelExe.setText("Run");
        sub_DelExe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_DelExeActionPerformed(evt);
            }
        });

        schema_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "From", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableActionPerformed(evt);
            }
        });

        schemalb.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        schemalb.setText("Select Schema");

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
        jScrollPane6.setViewportView(fieldTable);

        selectAll_out.setText("Select All");
        selectAll_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAll_outActionPerformed(evt);
            }
        });

        removeAll_out.setText("De-select All");
        removeAll_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAll_outActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectAll_out, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeAll_out))
                .addGap(58, 58, 58))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectAll_out)
                .addGap(18, 18, 18)
                .addComponent(removeAll_out)
                .addGap(59, 59, 59))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Field", jPanel3);

        table_out.setBackground(new java.awt.Color(153, 153, 0));
        table_out.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table_out.setModel(new javax.swing.table.DefaultTableModel(
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
        table_out.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table_out.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table_out.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(table_out);

        addColumn_out.setText("Add Column");
        addColumn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColumn_outActionPerformed(evt);
            }
        });

        removeColumn_out.setText("Remove");
        removeColumn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeColumn_outActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addColumn_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeColumn_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addColumn_out)
                .addGap(18, 18, 18)
                .addComponent(removeColumn_out)
                .addContainerGap(59, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Function", jPanel4);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setText("Select Table");

        javax.swing.GroupLayout schema_panelLayout = new javax.swing.GroupLayout(schema_panel);
        schema_panel.setLayout(schema_panelLayout);
        schema_panelLayout.setHorizontalGroup(
            schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(schema_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(schema_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(schema_panelLayout.createSequentialGroup()
                                .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(schema_panelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(schemalb, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166)
                                .addComponent(jLabel10))))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        schema_panelLayout.setVerticalGroup(
            schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(schema_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(schema_panelLayout.createSequentialGroup()
                        .addComponent(schemalb)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, schema_panelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(schema_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Insert Into", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        schema_outter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schema_outterActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Select Table");

        schemalb_outter.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        schemalb_outter.setText("Select Schema");

        table_outter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table_outterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(schemalb_outter, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(schema_outter, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(table_outter, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schemalb_outter)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(table_outter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schema_outter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sub_DelExe, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(schema_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(schema_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sub_DelExe))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel23);
    }// </editor-fold>//GEN-END:initComponents

    private void table_outterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table_outterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_table_outterActionPerformed

    private void schema_outterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schema_outterActionPerformed
        try {
            System.out.println("schema combo");
            table_outter.removeAllItems();
            String schema_name=schema_outter.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_table=getConn().prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA=?");
            pstmt_table.setString(1, schema_name);
            ResultSet rs_table=pstmt_table.executeQuery();
            //remove all element before add
            table_outter.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table_outter.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schema_outterActionPerformed

    private void removeColumn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumn_outActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_out.getModel();
        if(table_out.getSelectedRow()==-1)
        {
            if(table_out.getColumnCount()==0)
            {
                err_msg.setText("Table is Empty ....");
            }
            else
            {
                err_msg.setText("You must select one row ....");
            }
        }
        else
        {
            tbmod.removeRow(table_out.getSelectedRow());
        }
    }//GEN-LAST:event_removeColumn_outActionPerformed

    private void addColumn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumn_outActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table_out.getModel();
        tbmod.addRow(new Object[]{"","","",false});
    }//GEN-LAST:event_addColumn_outActionPerformed

    private void removeAll_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAll_outActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(false, i-1, 0);
        }
    }//GEN-LAST:event_removeAll_outActionPerformed

    private void selectAll_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAll_outActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(true, i-1, 0);

        }
    }//GEN-LAST:event_selectAll_outActionPerformed

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
            table.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schemaActionPerformed

    private void tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableActionPerformed
        try
        {
            //remove data from function combo table
            //combobox in table cell           
            col=new JComboBox<>();
            col.removeAllItems();
            DefaultTableModel tbmod=(DefaultTableModel) fieldTable.getModel();
//            String table_name=table.getSelectedItem().toString();
//            String schema_name=schema.getSelectedItem().toString();
//            //            innertable.setText(table_name);
//            //add all table name to tablecomb combobox from vikas schema
//            pstmt_field=getConn().prepareStatement("Select * from "+schema_name+"."+table_name);
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                pstmt_field=getConn().prepareStatement("select * from "+schema.getSelectedItem()+"."+table.getSelectedItem());
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                pstmt_field=getConn().prepareStatement("select * from "+table.getSelectedItem().toString());
            }
            ResultSet rs_field=pstmt_field.executeQuery();
            rsmd=rs_field.getMetaData();
            column_count=rsmd.getColumnCount();
            System.out.println(rsmd.getColumnCount());
            if(first_tim_in_out >= 2)
            {
                for (int i = 1; i <= store_col_count_out; i++)
                {
                    System.out.println(" I am  in"+i);
                    tbmod.removeRow(0);
                }
            }
            first_tim_in_out++;
            rest_field.removeAllItems();//remove all element

            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString()});
                //add value to field
                rest_field.addItem(rsmd.getColumnName(i));
                col.addItem(rsmd.getColumnName(i));
            }
            store_col_count_out=column_count;
            System.out.println("Store = "+store_col_count_out);
            //fieldtable.setModel(tbmod);
            TableColumn tc2=table_out.getColumnModel().getColumn(1);
            TableCellEditor tce2=new DefaultCellEditor(col);
            tc2.setCellEditor(tce2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableActionPerformed

    private void sub_DelExeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_DelExeActionPerformed
        try {
            String table_full_name_outter="";
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                table_full_name_outter=schema_outter.getSelectedItem()+"."+table_outter.getSelectedItem();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                table_full_name_outter=table_outter.getSelectedItem().toString();
            }
            String table_full_name="";
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                table_full_name=schema.getSelectedItem()+"."+table.getSelectedItem();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                table_full_name=table.getSelectedItem().toString();
            }
            String table_full_name_in="";
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                table_full_name_in=schema_in.getSelectedItem()+"."+table_in.getSelectedItem();
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                table_full_name_in=table_in.getSelectedItem().toString();
            }
            //for exists & not exists
            if(existsBtn.isSelected() || nexistsBtn.isSelected())
            {
                existsTextCond=table_full_name+"."+rest_field.getItemAt(0)+" = "+table_full_name_in+"."+rest_field_in.getItemAt(0)+" AND ";
            }
            //outer query
            String outerQuery="";
            String selectedfield_data_out="";
            DefaultTableModel tbmod_out=(DefaultTableModel) fieldTable.getModel();
            boolean selected_out[]=new boolean[tbmod_out.getRowCount()];
            for (int i = 0; i < tbmod_out.getRowCount(); i++)
            {
                selected_out[i]=(boolean) tbmod_out.getValueAt(i, 0);
                //System.out.println(selected[i]);
            }
            for(int i = 0; i < selected_out.length; i++)
            {
                if(selected_out[i]==true)
                {
                    selectedfield_data_out=selectedfield_data_out+tbmod_out.getValueAt(i, 1)+",";
                }
            }
            if(selectedfield_data_out.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Plz Select Field from Table ...");
                return;
            }
            else
            {
                selectedfield_data_out=selectedfield_data_out.substring(0, selectedfield_data_out.length()-1);
            }
            System.out.println(selectedfield_data_out);
            //for using Function
            String fun_string_out="";
            DefaultTableModel tbmod_fun=(DefaultTableModel)table_out.getModel();
            for(int i=0;i<table_out.getRowCount();i++)
            {
                String fun=tbmod_fun.getValueAt(i, 0).toString();
                fun=fun.substring(0, fun.length()-3);
                if((boolean)tbmod_fun.getValueAt(i, 3))
                {
                    fun_string_out=fun_string_out+fun+"( DISTINCT "+tbmod_fun.getValueAt(i, 1)+" ) AS "+tbmod_fun.getValueAt(i, 2)+",";
                }
                else
                {
                    fun_string_out=fun_string_out+fun+"( "+tbmod_fun.getValueAt(i, 1)+" ) AS "+tbmod_fun.getValueAt(i, 2)+",";
                }
            }
            if(!fun_string_out.equals(""))
            fun_string_out=fun_string_out.substring(0, fun_string_out.length()-1);
            System.out.println(fun_string_out);
            //for outter query string

            if(fun_string_out.equals(""))
            {
                outerQuery="Insert Into "+table_full_name_outter+" Select "+selectedfield_data_out+" from "+table_full_name;
                System.out.println("Insert Into "+table_full_name_outter+" Select "+selectedfield_data_out+" from "+table_full_name);
            }
            else if(selectedfield_data_out.equals(""))
            {
                outerQuery="Insert Into "+table_full_name_outter+" Select "+fun_string_out+" from "+table_full_name;
                System.out.println("Insert Into "+table_full_name_outter+" Select "+fun_string_out+" from "+table_full_name);
            }
            else
            {
                outerQuery="Insert Into "+table_full_name_outter+" Select "+selectedfield_data_out+","+fun_string_out+" from "+table_full_name;
                System.out.println("Insert Into "+table_full_name_outter+" Select "+selectedfield_data_out+","+fun_string_out+" from "+table_full_name);
            }

            if(existsBtn.isSelected() || nexistsBtn.isSelected())
            {
                outerQuery=outerQuery+" where "+existsText+" ";
                System.out.println(outerQuery);
            }
            else
            {
                outerQuery=outerQuery+" where "+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" ";
                System.out.println(outerQuery);
            }
            if(allOrAnyEnable.isSelected())
            {
                if(allBtn.isSelected())
                {
                    outerQuery=outerQuery+" where "+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" ALL ";
                    System.out.println(outerQuery);
                }
                if(anyBtn.isSelected())
                {
                    outerQuery=outerQuery+" where "+rest_field.getSelectedItem().toString()+" "+oprator.getSelectedItem().toString()+" ANY ";
                    System.out.println(outerQuery);
                }
            }
            //for inner Query
            String innerQuery="";
            String selectedfield_data="";
            DefaultTableModel tbmod1=(DefaultTableModel) fieldTable_in.getModel();
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
            if((rest_txt_in.getText().equals("")) && (!orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("order by");
                //for exists & not exists
                if(existsBtn.isSelected() || nexistsBtn.isSelected())
                {
                    existsTextCond=" WHERE "+existsTextCond.substring(0, existsTextCond.length()-5);
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt_in.getText().equals("")) && (!orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("order by and condition");
                op="";
                logic=logic+rest_field_in.getSelectedItem().toString()+" "+oprator_in.getSelectedItem().toString()+" "+rest_txt_in.getText();
                System.out.println(logic);
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt_in.getText().equals("")) && (orderTextArea.getText().equals("")) && !selectGroupBy.isSelected())
            {
                System.out.println("condition");
                op="";
                logic=logic+rest_field_in.getSelectedItem().toString()+" "+oprator_in.getSelectedItem().toString()+" "+rest_txt_in.getText();
                System.out.println(logic);
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic;
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic;
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic);
                }
            }
            else if((rest_txt_in.getText().equals("")) && (!orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("order by & Group By");
                //for exists & not exists
                if(existsBtn.isSelected() || nexistsBtn.isSelected())
                {
                    existsTextCond=" WHERE "+existsTextCond.substring(0, existsTextCond.length()-5);
                }
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt_in.getText().equals("")) && (!orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("order by and condition & Group By");
                op="";
                logic=logic+rest_field_in.getSelectedItem().toString()+" "+oprator_in.getSelectedItem().toString()+" "+rest_txt_in.getText();
                System.out.println(logic);
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText();
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText+" ORDER BY "+orderTextArea.getText());
                }
            }
            else if((!rest_txt_in.getText().equals("")) && (orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println("condition & Group By");
                op="";
                logic=logic+rest_field_in.getSelectedItem().toString()+" "+oprator_in.getSelectedItem().toString()+" "+rest_txt_in.getText();
                System.out.println(logic);
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+" where "+existsTextCond+logic+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
            }
            else if((rest_txt_in.getText().equals("")) && (orderTextArea.getText().equals("")) && selectGroupBy.isSelected())
            {
                System.out.println(" Group By");
                //for exists & not exists
                if(existsBtn.isSelected() || nexistsBtn.isSelected())
                {
                    existsTextCond=" WHERE "+existsTextCond.substring(0, existsTextCond.length()-5);
                }
                //for group By
                String havingText="";
                if(!havingValue.getText().equals(""))
                {
                    havingText=functionCombo.getSelectedItem()+"("+fun_field.getSelectedItem()+")"+havingOp.getSelectedItem()+havingValue.getText();
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond+" GROUP BY "+groupByField.getSelectedItem()+" "+havingText);
                }
            }
            else
            {
                if(existsBtn.isSelected() || nexistsBtn.isSelected())
                {
                    existsTextCond=" WHERE "+existsTextCond.substring(0, existsTextCond.length()-5);
                }
                if(fun_string.equals(""))
                {
                    innerQuery="Select "+selectedfield_data+" from "+table_full_name_in+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name_in+existsTextCond);
                }
                else if(selectedfield_data.equals(""))
                {
                    innerQuery="Select "+fun_string+" from "+table_full_name_in+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name_in+existsTextCond);
                }
                else
                {
                    innerQuery="Select "+selectedfield_data+","+fun_string+" from "+table_full_name_in+existsTextCond;
                    System.out.println("Select "+selectedfield_data+","+fun_string+" form "+table_full_name_in+existsTextCond);
                }
            }
            System.out.println(outerQuery+"("+innerQuery+")");
            pstmt=getConn().prepareStatement(outerQuery+"("+innerQuery+")");
            int i=pstmt.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null, " row is not inserted . . .");
                QBPanel1.setQueryString(outerQuery+"\n\t(\n\t\t"+innerQuery+"\n\t)");
            }
            else
            {
                QBPanel1.playSound("D:\\MyProject\\Adv QB\\QueryBuilder3\\src\\Audio\\subrowinsert.wav");
                JOptionPane.showMessageDialog(null, " row is inserted . . .");
//                err_msg.setText(" row is Insert . . .");
                QBPanel1.setQueryString(outerQuery+"\n\t(\n\t\t"+innerQuery+"\n\t)");                
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            logic="";
            existsTextCond="";
            existsBtn.setSelected(false);
            nexistsBtn.setSelected(false);
            jTabbedPane1.setSelectedIndex(0);
            orderTextArea.setText("");
            rest_field.enable();
            oprator.enable();
        }
    }//GEN-LAST:event_sub_DelExeActionPerformed

    private void nexistsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nexistsBtnActionPerformed
        if(existsBtn.isSelected())
        {
            nexistsBtn.setSelected(false);
            JOptionPane.showMessageDialog(null, "EXISTS already selected ...");
            return;
        }
        if(nexistsBtn.isSelected())
        {
            existsText="NOT EXISTS";
            System.out.println(existsText);
            rest_field.disable();
            oprator.disable();
            allBtn.setVisible(false);
            anyBtn.setVisible(false);
            //            oprator_all_any.disable();
            //            jTabbedPane4.setSelectedIndex(3);
            //            jTabbedPane4.addTab("Equality",jPanel3);
        }
        else
        {
            existsText="";
            rest_field.enable();
            oprator.enable();
            allBtn.setVisible(true);
            anyBtn.setVisible(true);
            //            oprator_all_any.enable();
            //            jTabbedPane4.setSelectedIndex(0);
            //            jTabbedPane4.remove(3);
        }
    }//GEN-LAST:event_nexistsBtnActionPerformed

    private void existsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existsBtnActionPerformed
        if(nexistsBtn.isSelected())
        {
            existsBtn.setSelected(false);
            JOptionPane.showMessageDialog(null, "NOT EXISTS already selected ...");
            return;
        }
        if(existsBtn.isSelected())
        {
            existsText="EXISTS";
            System.out.println(existsText);
            rest_field.disable();
            oprator.disable();
            allBtn.setVisible(false);
            anyBtn.setVisible(false);
            //            oprator_all_any.disable();
            //            jTabbedPane4.setSelectedIndex(3);
            //            jTabbedPane4.addTab("Equality",jPanel3);
        }
        else
        {
            existsText="";
            rest_field.enable();
            oprator.enable();
            allBtn.setVisible(true);
            anyBtn.setVisible(true);
            //            oprator_all_any.enable();
            //            jTabbedPane4.setSelectedIndex(0);
            //            jTabbedPane4.remove(3);
        }
    }//GEN-LAST:event_existsBtnActionPerformed

    private void allOrAnyEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allOrAnyEnableActionPerformed
        if(allOrAnyEnable.isSelected())
        {
            allBtn.setVisible(true);
            anyBtn.setVisible(true);
            allAnyPanel.setVisible(true);
            jTabbedPane1.remove(1);
        }
        else
        {
            allBtn.setVisible(false);
            anyBtn.setVisible(false);
            allAnyPanel.setVisible(false);
            jTabbedPane1.addTab("Function", jPanel2);
        }
    }//GEN-LAST:event_allOrAnyEnableActionPerformed

    private void opratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opratorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opratorActionPerformed

    private void removeColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table1.getModel();
        if(table1.getSelectedRow()==-1)
        {
            if(table1.getColumnCount()==0)
            {
                err_msg.setText("Table is Empty ....");
            }
            else
            {
                err_msg.setText("You must select one row ....");
            }
        }
        else
        {
            tbmod.removeRow(table1.getSelectedRow());
        }
    }//GEN-LAST:event_removeColumnActionPerformed

    private void addColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table1.getModel();
        tbmod.addRow(new Object[]{"","","",false});
    }//GEN-LAST:event_addColumnActionPerformed

    private void removeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable_in.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(false, i-1, 0);
        }
    }//GEN-LAST:event_removeAllActionPerformed

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) fieldTable_in.getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(true, i-1, 0);

        }
    }//GEN-LAST:event_selectAllActionPerformed

    private void schema_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schema_inActionPerformed
        try {
            System.out.println("schema combo");
            table_in.removeAllItems();
            String schema_name=schema_in.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_table=getConn().prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA=?");
            pstmt_table.setString(1, schema_name);
            ResultSet rs_table=pstmt_table.executeQuery();
            //remove all element before add
            //table.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table_in.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schema_inActionPerformed

    private void table_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table_inActionPerformed
        try
        {
            orderByField.removeAllItems();
            groupByField.removeAllItems();
            //combobox in table cell           
            col=new JComboBox<>();
            col.removeAllItems();
            DefaultTableModel tbmod=(DefaultTableModel) fieldTable_in.getModel();
//            String table_name=table_in.getSelectedItem().toString();
//            String schema_name=schema_in.getSelectedItem().toString();
//            //            innertable.setText(table_name);
//            //add all table name to tablecomb combobox from vikas schema
//            pstmt_field=getConn().prepareStatement("Select * from "+schema_name+"."+table_name);
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                pstmt_field=getConn().prepareStatement("select * from "+schema_in.getSelectedItem()+"."+table_in.getSelectedItem());
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
            {
                pstmt_field=getConn().prepareStatement("select * from "+table_in.getSelectedItem().toString());
            }
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
            rest_field_in.removeAllItems();//remove all element

            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString()});
                //add value to field
                rest_field_in.addItem(rsmd.getColumnName(i));
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_table_inActionPerformed

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

    private void havingOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_havingOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_havingOpActionPerformed

    private void orBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orBtnActionPerformed
        if(rest_field_in.getSelectedItem()==null || rest_txt_in.getText().equals(""))
        {
            err_msg.setText("Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+rest_field_in.getSelectedItem().toString()+oprator_in.getSelectedItem().toString()+rest_txt_in.getText();
        op=" OR ";
        logic=logic+op;
        rest_txt_in.setText("");
        rest_field_in.setSelectedIndex(0);
    }//GEN-LAST:event_orBtnActionPerformed

    private void oprator_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oprator_inActionPerformed

    }//GEN-LAST:event_oprator_inActionPerformed

    private void andBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andBtnActionPerformed
        if(rest_field_in.getSelectedItem()==null || rest_txt_in.getText().equals(""))
        {
            err_msg.setText("Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+rest_field_in.getSelectedItem().toString()+oprator_in.getSelectedItem().toString()+rest_txt_in.getText();
        op=" AND ";
        logic=logic+op;
        rest_txt_in.setText("");
        rest_field_in.setSelectedIndex(0);
    }//GEN-LAST:event_andBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addColumn;
    private javax.swing.JButton addColumn_out;
    private javax.swing.JPanel allAnyPanel;
    private javax.swing.JRadioButton allBtn;
    private javax.swing.JRadioButton allOrAnyEnable;
    private javax.swing.JButton andBtn2;
    private javax.swing.JRadioButton anyBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel err_msg;
    private javax.swing.JRadioButton existsBtn;
    private javax.swing.JTable fieldTable;
    private javax.swing.JTable fieldTable_in;
    private javax.swing.JComboBox fun_field;
    private javax.swing.JComboBox functionCombo;
    private javax.swing.JComboBox groupByField;
    private javax.swing.JComboBox havingOp;
    private javax.swing.JTextField havingValue;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JRadioButton nexistsBtn;
    private javax.swing.JComboBox oprator;
    private javax.swing.JComboBox oprator_in;
    private javax.swing.JButton orBtn2;
    private javax.swing.JComboBox orderByField;
    private javax.swing.JButton orderSelect;
    private javax.swing.JTextArea orderTextArea;
    private javax.swing.JButton removeAll;
    private javax.swing.JButton removeAll_out;
    private javax.swing.JButton removeColumn;
    private javax.swing.JButton removeColumn_out;
    private javax.swing.JComboBox rest_field;
    private javax.swing.JComboBox rest_field_in;
    private javax.swing.JTextField rest_txt_in;
    private javax.swing.JComboBox schema;
    private javax.swing.JComboBox schema_in;
    private javax.swing.JComboBox schema_outter;
    private javax.swing.JPanel schema_panel;
    private javax.swing.JLabel schemalb;
    private javax.swing.JLabel schemalb_in;
    private javax.swing.JLabel schemalb_outter;
    private javax.swing.JButton selectAll;
    private javax.swing.JButton selectAll_out;
    private javax.swing.JRadioButton selectGroupBy;
    private javax.swing.JRadioButton selectSqu;
    private javax.swing.JComboBox sequenceOfOrder;
    private javax.swing.JButton sub_DelExe;
    private javax.swing.JComboBox table;
    private javax.swing.JTable table1;
    private javax.swing.JComboBox table_in;
    private javax.swing.JTable table_out;
    private javax.swing.JComboBox table_outter;
    // End of variables declaration//GEN-END:variables
}
