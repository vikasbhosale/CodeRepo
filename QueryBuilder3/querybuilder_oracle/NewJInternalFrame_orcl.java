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
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;


/**
 *
 * @author vikas
 */
public class NewJInternalFrame_orcl extends javax.swing.JInternalFrame {

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
    private String schemaname,tablename;
    public NewJInternalFrame_orcl() {
        initComponents();
        try 
        {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            setConn(QBPanel1.getConn());
        } 
        catch (Exception e) 
        {
            
        }
        TableColumn tc1=jointable.getColumnModel().getColumn(0);
        TableColumn tc2=jointable.getColumnModel().getColumn(2);
        tc1.setPreferredWidth(5);
        tc2.setPreferredWidth(5);
    }
    public void setTableContent(String tablename)
    {
        this.tablename=tablename;
        System.out.println("Internal"+tablename);
        this.setTitle(tablename);
        try 
        {
            DefaultTableModel tbmod=(DefaultTableModel) getJointable().getModel();
//            String table_name=table.getSelectedItem().toString();
//            String schema_name=schema.getSelectedItem().toString();
            //add all table name to tablecomb combobox from vikas schema
            pstmt_field=getConn().prepareStatement("Select * from "+tablename);
            ResultSet rs_field=pstmt_field.executeQuery();
            rsmd=rs_field.getMetaData();
            column_count=rsmd.getColumnCount();
            System.out.println(rsmd.getColumnCount());   
            for (int i = 1; i <= column_count; i++)
            {
                System.out.println(rsmd.getColumnName(i).toString());
                tbmod.addRow(new Object[]{false,rsmd.getColumnName(i).toString(),false});
                //add value to field
                //field.addItem(rsmd.getColumnName(i));
            }
            getJointable().setModel(tbmod);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jointable = new javax.swing.JTable();
        selectAll = new javax.swing.JButton();
        removeAll = new javax.swing.JButton();

        setClosable(true);
        setVisible(true);

        jointable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "Field", "Conn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jointable);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(selectAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeAll))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectAll)
                    .addComponent(removeAll)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) getJointable().getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(true, i-1, 0);
            
        }
    }//GEN-LAST:event_selectAllActionPerformed

    private void removeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel) getJointable().getModel();
        System.out.println(tbmod.getRowCount());
        for (int i = 1; i <= tbmod.getRowCount(); i++)
        {
            tbmod.setValueAt(false, i-1, 0);
        }
    }//GEN-LAST:event_removeAllActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jointable;
    private javax.swing.JButton removeAll;
    private javax.swing.JButton selectAll;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jointable
     */
    public javax.swing.JTable getJointable() {
        return jointable;
    }
}
