/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder_oracle;

import querybuilder.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vikas
 */
public class UpdateQuery_orcl extends javax.swing.JPanel {

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
    private int first_tim_in=1;
    private int store_col_count;
    private String logic="";//for AND , OR condition
    private String op="";   
    
    public UpdateQuery_orcl() {
        initComponents();
        try {
            setConn(QBPanel1.getConn());
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
        catch (Exception e) 
        {
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        table = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        andBtn2 = new javax.swing.JButton();
        oprator = new javax.swing.JComboBox();
        orBtn2 = new javax.swing.JButton();
        rest_txt = new javax.swing.JTextField();
        field = new javax.swing.JComboBox();
        updateBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieldtable = new javax.swing.JTable();
        err_msg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 0));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setText("Select Table");

        table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restriction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16))); // NOI18N

        andBtn2.setText("AND");
        andBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andBtnActionPerformed(evt);
            }
        });

        oprator.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        oprator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=" }));
        oprator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opratorActionPerformed(evt);
            }
        });

        orBtn2.setBackground(new java.awt.Color(255, 255, 255));
        orBtn2.setText("OR");
        orBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(orBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(andBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rest_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rest_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orBtn2)
                    .addComponent(andBtn2))
                .addContainerGap())
        );

        updateBtn.setText("Update Table");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        fieldtable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        fieldtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Set Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        fieldtable.setSelectionBackground(new java.awt.Color(0, 204, 255));
        fieldtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(fieldtable);

        err_msg.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        err_msg.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(updateBtn)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(58, 58, 58)
                                        .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(5, 5, 5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateBtn)
                .addGap(18, 18, 18)
                .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try
        {

            DefaultTableModel tbmod=(DefaultTableModel)fieldtable.getModel();
            fieldtable.tableChanged(null);

            String setvalue="";

            for(int i=0;i<fieldtable.getRowCount();i++)
            {
                if(!tbmod.getValueAt(i, 1).equals(""))
                {
                    setvalue=setvalue+tbmod.getValueAt(i, 0).toString()+" = "+tbmod.getValueAt(i, 1).toString()+",";
                }
            }
            setvalue=setvalue.substring(0, setvalue.length()-1);
            System.out.print(setvalue);

            op="";
            logic=logic+field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
            System.out.println(logic);            
            System.out.println("Update "+table.getSelectedItem().toString()+" set "+setvalue+" where "+logic);
            pstmt=getConn().prepareStatement("Update "+table.getSelectedItem().toString()+" set "+setvalue+" where "+logic);
            int i=pstmt.executeUpdate();
            if(i==1)
            {             
                JOptionPane.showMessageDialog(null, " row is update . . .");
                QBPanel1.setQueryString("Update "+table.getSelectedItem().toString()+" set "+setvalue+" where "+logic);
            }
            else
            {
                JOptionPane.showMessageDialog(null, " row is not update . . .");
            }
            logic="";//this will clear previous data to  added with new data (Exception)
            //fname='vikas'fname='onkar' this error
            //Reset All Component
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void orBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orBtnActionPerformed
        if(field.getSelectedItem()==null || rest_txt.getText().equals(""))
        {
            err_msg.setText("Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
        op=" OR ";
        logic=logic+op;
        rest_txt.setText("");
        field.setSelectedIndex(0);
    }//GEN-LAST:event_orBtnActionPerformed

    private void opratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opratorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opratorActionPerformed

    private void andBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andBtnActionPerformed
        if(field.getSelectedItem()==null || rest_txt.getText().equals(""))
        {
            err_msg.setText("Plz Select Field & Text Field ...");
            return;
        }
        logic=logic+field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
        op=" AND ";
        logic=logic+op;
        rest_txt.setText("");
        field.setSelectedIndex(0);
    }//GEN-LAST:event_andBtnActionPerformed

    private void tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableActionPerformed

        try
        {
            DefaultTableModel tbmod=(DefaultTableModel) fieldtable.getModel();
            String table_name=table.getSelectedItem().toString();            
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
            field.removeAllItems();//remove all element

            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{rsmd.getColumnName(i).toString(),""});
                //add value to field
                field.addItem(rsmd.getColumnName(i));
            }
            store_col_count=column_count;
            System.out.println("Store = "+store_col_count);
            //fieldtable.setModel(tbmod);
        }
        catch (Exception e)
        {
        }
    }//GEN-LAST:event_tableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton andBtn2;
    private javax.swing.JLabel err_msg;
    private javax.swing.JComboBox field;
    private javax.swing.JTable fieldtable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox oprator;
    private javax.swing.JButton orBtn2;
    private javax.swing.JTextField rest_txt;
    private javax.swing.JComboBox table;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
