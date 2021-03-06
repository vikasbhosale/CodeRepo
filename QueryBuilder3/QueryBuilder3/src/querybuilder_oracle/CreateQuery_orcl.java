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
public class CreateQuery_orcl extends javax.swing.JPanel {

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
    public CreateQuery_orcl()
    {
        initComponents();
        table.setRowHeight(20);        
        //combobox in table cell
        setConn(QBPanel1.getConn());
        String data[]={"VARCHAR2(45)","NUMBER(10)","BLOB","DATE"};
        JComboBox<String> com=new JComboBox<>(data);
        com.setEditable(true);
        TableColumn tc=table.getColumnModel().getColumn(1);           
        TableCellEditor tce=new DefaultCellEditor(com);
        tc.setCellEditor(tce);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        addColbtn = new javax.swing.JButton();
        deleteColbtn = new javax.swing.JButton();
        crreateBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tablename = new javax.swing.JTextField();

        table1.setBackground(new java.awt.Color(153, 153, 0));
        table1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Datatype", "Auto-Inc", "Not-Null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table1.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table1);

        setBackground(new java.awt.Color(0, 153, 153));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Query", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16), new java.awt.Color(255, 51, 51))); // NOI18N
        setAutoscrolls(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table.setBackground(new java.awt.Color(153, 153, 0));
        table.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 0, 51), new java.awt.Color(153, 204, 255)));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Datatype", "Auto-Inc", "Not-Null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setSelectionBackground(new java.awt.Color(51, 255, 255));
        table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addColbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteColbtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addColbtn)
                    .addComponent(deleteColbtn))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        crreateBtn.setText("Create Table");
        crreateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crreateBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel1.setText("Table Name");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crreateBtn, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)
                        .addComponent(tablename, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tablename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crreateBtn)
                .addContainerGap())
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void addColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table.getModel();
        tbmod.addRow(new Object[]{"","",false,false});

    }//GEN-LAST:event_addColbtnActionPerformed

    private void deleteColbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteColbtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table.getModel();
        if(table.getSelectedRow()==-1)
        {
            if(table.getColumnCount()==0)
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
            tbmod.removeRow(table.getSelectedRow());
        }
    }//GEN-LAST:event_deleteColbtnActionPerformed

    private void crreateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crreateBtnActionPerformed
        DefaultTableModel tbmod=(DefaultTableModel)table.getModel();
        if(table.getSelectedRow()==-1)
        {
            if(table.getColumnCount()==0)
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
            try {
                String col_datatype="";

                for(int i=0;i<table.getRowCount();i++)
                {
                    col_datatype=col_datatype+tbmod.getValueAt(i, 0)+" "+tbmod.getValueAt(i, 1)+",";
                }
                col_datatype=col_datatype.substring(0, col_datatype.length()-1);
                System.out.print(col_datatype);
                System.out.print("create table "+tablename.getText()+"("+col_datatype+")");
                pstmt=getConn().prepareStatement("create table "+tablename.getText()+"("+col_datatype+")");                
                int status=pstmt.executeUpdate();
                if(status!=1)
                {
//                    err_msg.setText("Table is create successfully ....");                    
                    JOptionPane.showMessageDialog(null, "Table is create successfully ....");
                }
                else
                {
//                    err_msg.setText("Fail to create Table ....");
                    JOptionPane.showMessageDialog(null, "Fail to create Table ....");
                }
            } catch (Exception e2)
            {
//                err_msg.setText(e.getMessage());
                JOptionPane.showMessageDialog(null, e2.getMessage());
                e2.printStackTrace();
            }

        }
    }//GEN-LAST:event_crreateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addColbtn;
    private javax.swing.JButton crreateBtn;
    private javax.swing.JButton deleteColbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextField tablename;
    // End of variables declaration//GEN-END:variables
}
