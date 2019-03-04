/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author VIKAS
 */
public class DatePanel1 extends JPanel{
    private JLabel label;
//    private javax.swing.JTextField jTextField1;
    private JDateChooser date;
    public DatePanel1() 
    {
        label = new JLabel();
//        jTextField1 = new javax.swing.JTextField();
        date=new JDateChooser();
//        setBackground(new java.awt.Color(0, 102, 102));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
//        label.setForeground(new java.awt.Color(255, 255, 255));        
        label.setText("label");
        label.setPreferredSize(new Dimension(120, 25));
        if(StartWindow.getDatabase_string().equals("MySQL"))
        {
            date.setDateFormatString("dd-MM-yyyy");      
        }
        else if(StartWindow.getDatabase_string().equals("ORACLE") || StartWindow.getDatabase_string().equals("MS-Access"))
        {
            date.setDateFormatString("dd/mm/yyyy");      
        }        
        date.setForeground(new Color(255,255,255));
//        jTextField1.setPreferredSize(new Dimension(200, 20));
        date.setPreferredSize(new Dimension(200, 25));
        add(label);
        add(date);
    }

    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * @return the date
     */
    public JDateChooser getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(JDateChooser date) {
        this.date = date;
    }

  
    
}
