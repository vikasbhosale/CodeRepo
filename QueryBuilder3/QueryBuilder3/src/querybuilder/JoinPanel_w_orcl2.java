/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author vikas
 */
public class JoinPanel_w_orcl2 extends javax.swing.JPanel {

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
    private String logic="";//for AND , OR condition
    private String op="";
    private Vector<NewJInternalFrame_w_orcl> arr;
    private String selectedField="";
    private String fromData="";
    private Vector<String> tablearr;
    //private String setvalue="";//for update query
    private JComboBox<String> rCombo,lCombo;
    private boolean entryOnce=true;
    private Map map;
    private String orderText="";
    
    public JoinPanel_w_orcl2() {
        initComponents();
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vikas", "root", "root");            
            setConn(QBPanel1.getConn());
            arr=new Vector<>();
            tablearr=new Vector<>();
            rCombo=new JComboBox(tablearr);
            lCombo=new JComboBox(tablearr);
            map=new HashMap();
            
            TableColumn tc=connTable.getColumnModel().getColumn(0);
            TableCellEditor tce=new DefaultCellEditor(lCombo);
            tc.setCellEditor(tce);
        
            TableColumn tc2=connTable.getColumnModel().getColumn(2);
            TableCellEditor tce2=new DefaultCellEditor(lCombo);
            tc2.setCellEditor(tce2);
            if(StartWindow.getDatabase_string().equals("MySQL"))
            {
                //add value to schema combobox
            pstmt_schema=conn.prepareStatement("show schemas");
            ResultSet rs_schema=pstmt_schema.executeQuery();
            while(rs_schema.next())
            {
                schema.addItem(rs_schema.getString(1));
                //subschemacombo.addItem(rs_schema.getString(1));
                System.out.println(rs_schema.getString(1));
            }
            }
            else if(StartWindow.getDatabase_string().equals("ORACLE"))
            {
                schemalb.setVisible(false);
                schema.setVisible(false);
                //add value to schema combobox
                pstmt_schema=conn.prepareStatement("select table_name from user_tables where tablespace_name='SYSTEM' AND table_name not like '%$%' AND table_name not like '%$' AND table_name not like '$%'  AND table_name!='HELP' AND table_name!='SQLPLUS_PRODUCT_PROFILE'");
		ResultSet rs_schema=pstmt_schema.executeQuery();
		while(rs_schema.next())
		{
                    table.addItem(rs_schema.getString(1));
		    //subschemacombo.addItem(rs_schema.getString(1));
                    System.out.println(rs_schema.getString(1));
		}
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
            }             
            TableColumn tc1=connTable.getColumnModel().getColumn(0);        
            tc1.setPreferredWidth(5);
        
            //combobox in table cell
            String data[]={"INNER JOIN","LEFT OUTER JOIN","RIGHT OUTER JOIN","FULL OUTER JOIN","CROSS JOIN"};
            JComboBox<String> com=new JComboBox<>(data);
            TableColumn tc3=connTable.getColumnModel().getColumn(1);
            TableCellEditor tce3=new DefaultCellEditor(com);
            tc3.setCellEditor(tce3);
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

        internelPane = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        schemalb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        schema = new javax.swing.JComboBox();
        table = new javax.swing.JComboBox();
        joinExc = new javax.swing.JButton();
        addColbtn = new javax.swing.JButton();
        deleteColbtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        connTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        orderByTable = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        orderSelect = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderTextArea = new javax.swing.JTextArea();
        selectSqu = new javax.swing.JRadioButton();
        sequenceOfOrder = new javax.swing.JComboBox();
        orderByField = new javax.swing.JComboBox();

        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(300, 300));

        internelPane.setBackground(new java.awt.Color(0, 51, 51));
        internelPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout internelPaneLayout = new javax.swing.GroupLayout(internelPane);
        internelPane.setLayout(internelPaneLayout);
        internelPaneLayout.setHorizontalGroup(
            internelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        internelPaneLayout.setVerticalGroup(
            internelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        add.setText("add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        schemalb.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        schemalb.setText("Select Schema");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setText("Select Table");

        schema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemaActionPerformed(evt);
            }
        });

        table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableActionPerformed(evt);
            }
        });

        joinExc.setText("Run");
        joinExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinExcActionPerformed(evt);
            }
        });

        addColbtn.setText("Add");
        addColbtn.setAutoscrolls(true);
        addColbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColbtnActionPerformed(evt);
            }
        });

        deleteColbtn.setText("Delete");
        deleteColbtn.setAutoscrolls(true);
        deleteColbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteColbtnActionPerformed(evt);
            }
        });

        connTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "L-Column", "Join Type", "R-Column"
            }
        ));
        connTable.setDragEnabled(true);
        connTable.setSurrendersFocusOnKeystroke(true);
        connTable.getTableHeader().setReorderingAllowed(false);
        connTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connTableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connTableMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                connTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                connTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(connTable);

        jTabbedPane1.addTab("Mapping", jScrollPane1);

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));

        orderByTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderByTableActionPerformed(evt);
            }
        });

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
        jScrollPane3.setViewportView(orderTextArea);

        selectSqu.setBackground(new java.awt.Color(153, 204, 255));

        sequenceOfOrder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASC", "DESC" }));

        orderByField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderByFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(orderSelect)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(sequenceOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(selectSqu))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(orderByTable, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(orderByField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(101, 101, 101)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(orderByTable, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(orderByField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selectSqu, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(sequenceOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(orderSelect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order By", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(internelPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(schemalb, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(schema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(add)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteColbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addColbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(joinExc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schemalb)
                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addComponent(add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(internelPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(addColbtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteColbtn))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(joinExc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        NewJInternalFrame_w_orcl ip=new NewJInternalFrame_w_orcl();             
        if(StartWindow.getDatabase_string().equals("MySQL"))
        {
            for(int i=0;i<arr.size();i++)
            {
                if((schema.getSelectedItem().toString()+"."+table.getSelectedItem().toString()).equals(tablearr.get(i)))
                {
                    JOptionPane.showMessageDialog(null, "Table is already selected ...");
                    return;
                }
            }
            arr.addElement(ip);
            tablearr.addElement(schema.getSelectedItem().toString()+"."+table.getSelectedItem().toString());;  
            ip.setTableContent(schema.getSelectedItem().toString() ,table.getSelectedItem().toString());
        }
        else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
        {
            for(int i=0;i<arr.size();i++)
            {
                if(table.getSelectedItem().toString().equals(tablearr.get(i)))
                {
                    JOptionPane.showMessageDialog(null, "Table is already selected ...");
                    return;
                }
            }
            arr.addElement(ip);
            tablearr.addElement(table.getSelectedItem().toString());  
            ip.setTableContent_orcl(table.getSelectedItem().toString());
        }             
        internelPane.add(ip);            
    }//GEN-LAST:event_addActionPerformed

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
            DefaultTableModel tbmod=(DefaultTableModel) table.getModel();
            String table_name=table.getSelectedItem().toString();
            String schema_name=schema.getSelectedItem().toString();
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
            //field.removeAllItems();//remove all element 
            
            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString(),""});
                //add value to field
                //field.addItem(rsmd.getColumnName(i));
            }
            store_col_count=column_count;
            System.out.println("Store = "+store_col_count);
            //fieldtable.setModel(tbmod);
        }
        catch (Exception e) 
        {
        }
    }//GEN-LAST:event_tableActionPerformed

    private void joinExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinExcActionPerformed
        for(int j=0,k=0;j<arr.size() || k<tablearr.size();j++,k++)
        {
        NewJInternalFrame_w_orcl ip=arr.elementAt(j);
        System.out.println(ip.isVisible()+"nnn");
        for(int l=0;l<arr.size();l++)
        {
            if(!arr.elementAt(l).isVisible())
            {
                arr.remove(l);
                tablearr.remove(l);
            }
        }
        System.out.println(arr.size()+" "+tablearr.size());
        //for internel panel table
        DefaultTableModel tbmod=(DefaultTableModel) ip.getJointable().getModel();System.out.println(ip.getJointable().getName());
        boolean selected[]=new boolean[tbmod.getRowCount()];
        for (int i = 0; i < tbmod.getRowCount(); i++)
        {
            selected[i]=(boolean) tbmod.getValueAt(i, 0);
            //System.out.println(selected[i]);
        }
         for (int i = 0; i < selected.length; i++)
        {
            if(selected[i]==true)
           {
               selectedField=selectedField+tablearr.get(k)+"."+tbmod.getValueAt(i, 1)+",";
               //for mapping & after form statement
               if(entryOnce)
                {
                    map.put(tablearr.get(k), tablearr.get(k)+"."+tbmod.getValueAt(0, 1));
                    System.out.println(map.get(tablearr.get(k)));
               }
                //System.out.println(selectedField);
                entryOnce=false;
            }
       } 
        entryOnce=true;
        }
        for(int i=0;i<map.size();i++)
       {
            System.out.println("Map ==="+map.get(tablearr.get(i)));
        }
       if(selectedField.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Plz Select Field from Table ...");
            return;
        }            
       else
        {
           selectedField=selectedField.substring(0, selectedField.length()-1);
        }
        //after from clause
        int j=1;
       DefaultTableModel tbmod2=(DefaultTableModel) connTable.getModel();
        for(int i=0;i< tbmod2.getRowCount();i++)
       {
            if(j==1)
            {
               fromData=fromData+tbmod2.getValueAt(i, 0)+" "+tbmod2.getValueAt(i, 1)+" "+tbmod2.getValueAt(i, 2)+"\n ON \n\t"+map.get(tbmod2.getValueAt(i, 0))+" = "+map.get(tbmod2.getValueAt(i, 2))+" ";
            }
           else
            {
                fromData=fromData+tbmod2.getValueAt(i, 1)+" "+tbmod2.getValueAt(i, 2)+"\n ON \n\t"+map.get(tbmod2.getValueAt(i, 0))+" = "+map.get(tbmod2.getValueAt(i, 2))+" ";
           }
            j++;
        }
       System.out.println(fromData);
        System.out.println(selectedField);
        try 
       {
           String queryTest="";
            if(!orderTextArea.getText().equals(""))
           {
                pstmt=getConn().prepareStatement("SELECT "+selectedField+" FROM "+fromData+" ORDER BY "+orderTextArea.getText());
               System.out.println("SELECT "+selectedField+" FROM "+fromData+" ORDER BY "+orderTextArea.getText());
               queryTest="SELECT \n\t"+selectedField+"\n FROM \n\t"+fromData+"\n ORDER BY \n\t"+orderTextArea.getText();
            }
            else
            {
               pstmt=getConn().prepareStatement("SELECT "+selectedField+" FROM "+fromData);
                System.out.println("SELECT "+selectedField+" FROM "+fromData);
                queryTest="SELECT \n\t"+selectedField+"\n FROM \n\t"+fromData;
           }
            ResultSet rs_field=pstmt.executeQuery();
            QBPanel1.setQueryString(queryTest);
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
       }
        catch (Exception e) 
       {
            e.printStackTrace();
        }
       selectedField="";
        fromData="";
        map.clear();
    }//GEN-LAST:event_joinExcActionPerformed
    private void addColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)connTable.getModel();
        tbmod.addRow(new Object[]{"","",""});
    }//GEN-LAST:event_addColbtnActionPerformed

    private void deleteColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)connTable.getModel();
        if(connTable.getRowCount()==0)
        {
            JOptionPane.showMessageDialog(null, "Table is Empty ....");
            return;            
        }
        else
       {
            tbmod.removeRow(tbmod.getRowCount()-1);
        }
    }//GEN-LAST:event_deleteColbtnActionPerformed
    private void connTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connTableMouseClicked
        for(int l=0;l<arr.size();l++)
        {
            if(!arr.elementAt(l).isVisible())
            {
                arr.remove(l);
                tablearr.remove(l);
            }
       }
//       System.out.println("mouse listener");
       rCombo.removeAll();
       lCombo.removeAll();
       rCombo=new JComboBox(tablearr);
       lCombo=new JComboBox(tablearr);
       //for order By
       orderByTable.removeAllItems();
       for(int i=0;i<tablearr.size();i++)
       {
          orderByTable.addItem(tablearr.get(i));
       }
    }//GEN-LAST:event_connTableMouseClicked
    private void connTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connTableMouseEntered
       for(int l=0;l<arr.size();l++)
        {
            if(!arr.elementAt(l).isVisible())
           {
                arr.remove(l);
                tablearr.remove(l);
           }
        }
//       System.out.println("mouse listener");
       rCombo.removeAll();
      lCombo.removeAll();
       rCombo=new JComboBox(tablearr);
      lCombo=new JComboBox(tablearr);
       //for order By
       orderByTable.removeAllItems();
       for(int i=0;i<tablearr.size();i++)
      {
           orderByTable.addItem(tablearr.get(i));
      }
    }//GEN-LAST:event_connTableMouseEntered
    private void connTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connTableMouseExited
       for(int l=0;l<arr.size();l++)
       {
            if(!arr.elementAt(l).isVisible())
           {
                arr.remove(l);
               tablearr.remove(l);
            }
        }
//       System.out.println("mouse listener");
      rCombo.removeAll();
       lCombo.removeAll();
      rCombo=new JComboBox(tablearr);
       lCombo=new JComboBox(tablearr);
       //for order By
      orderByTable.removeAllItems();
       for(int i=0;i<tablearr.size();i++)
       {
           orderByTable.addItem(tablearr.get(i));
      }
    }//GEN-LAST:event_connTableMouseExited
    private void connTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connTableMousePressed
       for(int l=0;l<arr.size();l++)
        {
           if(!arr.elementAt(l).isVisible())
            {
                arr.remove(l);
                tablearr.remove(l);
            }
       }
//       System.out.println("mouse listener");
       rCombo.removeAll();
      lCombo.removeAll();
       rCombo=new JComboBox(tablearr);
       lCombo=new JComboBox(tablearr);
       //for order By
      orderByTable.removeAllItems();
       for(int i=0;i<tablearr.size();i++)
      {
           orderByTable.addItem(tablearr.get(i));
      }
    }//GEN-LAST:event_connTableMousePressed
    private void connTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connTableMouseReleased
       for(int l=0;l<arr.size();l++)
       {
            if(!arr.elementAt(l).isVisible())
            {
                arr.remove(l);
                tablearr.remove(l);
           }
        }
//       System.out.println("mouse listener");
       rCombo.removeAll();
       lCombo.removeAll();
       rCombo=new JComboBox(tablearr);
       lCombo=new JComboBox(tablearr);
       //for order By
       orderByTable.removeAllItems();
       for(int i=0;i<tablearr.size();i++)
       {
           orderByTable.addItem(tablearr.get(i));
       }
    }//GEN-LAST:event_connTableMouseReleased
    private void orderByTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderByTableActionPerformed
       try 
        {
            pstmt_field=getConn().prepareStatement("select * from "+orderByTable.getSelectedItem().toString());
            rs=pstmt_field.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            column_count=rsmd.getColumnCount();
           System.out.println("tablecombo");
            orderByField.removeAllItems();//remove all element before select another table from tablecombo
            for(int i=1;i<=column_count;i++)
            {
            	orderByField.addItem(rsmd.getColumnName(i));
            }
        }
        catch (Exception e) 
        {
            
        }
    }//GEN-LAST:event_orderByTableActionPerformed
    private void orderSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderSelectActionPerformed
       if(selectSqu.isSelected())
        {
            orderText=orderText+orderByTable.getSelectedItem().toString()+"."+orderByField.getSelectedItem().toString()+" "+sequenceOfOrder.getSelectedItem().toString()+",";
        }
        else
        {
            orderText=orderText+orderByTable.getSelectedItem().toString()+"."+orderByField.getSelectedItem().toString()+",";
        }
       //orderText=orderText.substring(0,orderText.length()-1);
        orderTextArea.setText(orderText.substring(0,orderText.length()-1));
    }//GEN-LAST:event_orderSelectActionPerformed
    private void orderByFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderByFieldActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_orderByFieldActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton addColbtn;
    private javax.swing.JTable connTable;
    private javax.swing.JButton deleteColbtn;
    private javax.swing.JPanel internelPane;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton joinExc;
    private javax.swing.JComboBox orderByField;
    private javax.swing.JComboBox orderByTable;
    private javax.swing.JButton orderSelect;
    private javax.swing.JTextArea orderTextArea;
    private javax.swing.JComboBox schema;
    private javax.swing.JLabel schemalb;
    private javax.swing.JRadioButton selectSqu;
    private javax.swing.JComboBox sequenceOfOrder;
    private javax.swing.JComboBox table;
    // End of variables declaration//GEN-END:variables
}
