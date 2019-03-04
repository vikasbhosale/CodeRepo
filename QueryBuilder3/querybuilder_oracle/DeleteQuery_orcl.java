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
import static querybuilder.DeleteQuery_w_orcl.setConn;

/**
 *
 * @author vikas
 */
public class DeleteQuery_orcl extends javax.swing.JPanel {

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
    private String logic="";//for AND , OR condition
    private String op="";
    
    public DeleteQuery_orcl() {
        initComponents();
         try 
         {
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        schema = new javax.swing.JComboBox();
        table = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        andBtn = new javax.swing.JButton();
        oprator = new javax.swing.JComboBox();
        orBtn = new javax.swing.JButton();
        rest_txt = new javax.swing.JTextField();
        field = new javax.swing.JComboBox();
        deleteBtn = new javax.swing.JButton();
        err_msg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 0));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Delete Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16))); // NOI18N
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Select Schema");

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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setText("Select Table");

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restriction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 16))); // NOI18N

        andBtn.setText("AND");
        andBtn.addActionListener(new java.awt.event.ActionListener() {
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

        orBtn.setBackground(new java.awt.Color(255, 255, 255));
        orBtn.setText("OR");
        orBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orBtn)
                .addGap(27, 27, 27)
                .addComponent(andBtn)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rest_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oprator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rest_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orBtn)
                    .addComponent(andBtn))
                .addContainerGap())
        );

        deleteBtn.setText("Delete Table");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        err_msg.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        err_msg.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(deleteBtn)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(schema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(err_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        try
        {
            if(field.getSelectedItem()==null || rest_txt.getText().equals(""))
            {
                err_msg.setText("Plz Select Field & Text Field ...");
                return;
            }
            System.out.println("delete");
            op="";
            logic=logic+field.getSelectedItem().toString()+oprator.getSelectedItem().toString()+rest_txt.getText();
            System.out.println(logic);
            System.out.println("delete from "+table.getSelectedItem().toString()+" where "+logic);
            pstmt=getConn().prepareStatement("delete from "+table.getSelectedItem().toString()+" where "+logic);
            int i=pstmt.executeUpdate();
            if(i==1)
            {
                //              err_msg.setText(" row is deleted . . .");
                JOptionPane.showMessageDialog(null, " row is delete . . .");
                QBPanel1.setQueryString("delete from "+table.getSelectedItem().toString()+" where "+logic);
            }
            else
            {
                //               err_msg.setText(" row is not deleted . . .");
                JOptionPane.showMessageDialog(null, " row is not delete . . .");
            }
            logic="";//this will clear previous data to  added with new data (Exception)
            //fname='vikas'fname='onkar' this error
            //Reset All Component
            schema.setSelectedIndex(0);
            table.setSelectedIndex(0);
        }
        catch (Exception e)
        {

        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void orBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orBtnActionPerformed
        if(field.getSelectedItem()==null || rest_txt.getText()=="")
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
        if(field.getSelectedItem()==null || rest_txt.getText()=="")
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
            pstmt_field=getConn().prepareStatement("select * from "+table.getSelectedItem().toString());
            rs=pstmt_field.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            column_count=rsmd.getColumnCount();
            System.out.println("tablecombo");
            field.removeAllItems();//remove all element before select another table from tablecombo
            for(int i=1;i<=column_count;i++)
            {
                field.addItem(rsmd.getColumnName(i));
            }
        }
        catch (Exception e)
        {

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
            table.removeAllItems();//this will remove all item from table

            while(rs_table.next())
            {
                table.addItem(rs_table.getString(1));
                System.out.println(rs_table.getString(1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_schemaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton andBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel err_msg;
    private javax.swing.JComboBox field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox oprator;
    private javax.swing.JButton orBtn;
    private javax.swing.JTextField rest_txt;
    private javax.swing.JComboBox schema;
    private javax.swing.JComboBox table;
    // End of variables declaration//GEN-END:variables
}
