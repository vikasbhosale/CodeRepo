/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author VIKAS
 */
public class TextPanel extends JPanel{

    /**
     * @return the number
     */
    public static String getNumber() {
        return number;
    }

    /**
     * @param aNumber the number to set
     */
    public static void setNumber(String aNumber) {
        number = aNumber;
    }
    private javax.swing.JLabel label;
    private javax.swing.JTextField textfield;
    private static String number;
    public TextPanel() 
    {
        label = new javax.swing.JLabel();
        textfield = new javax.swing.JTextField();

//        setBackground(new java.awt.Color(0, 102, 102));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
//        label.setForeground(new java.awt.Color(255, 255, 255));        
        label.setText("jLabel1");
        label.setPreferredSize(new Dimension(120, 25));
        textfield.setPreferredSize(new Dimension(200, 25));
        add(label);
        add(textfield);
//        textfield.addKeyListener(new KeyWork());
    }
    /**
     * @return the label
     */
    public javax.swing.JLabel getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(javax.swing.JLabel label) {
        this.label = label;
    }

    /**
     * @return the textfield
     */
    public javax.swing.JTextField getTextfield() {
        return textfield;
    }

    /**
     * @param textfield the textfield to set
     */
    public void setTextfield(javax.swing.JTextField textfield) {
        this.textfield = textfield;
    }

//    private class KeyWork extends KeyAdapter {
//        
//        @Override
//        public void keyTyped(KeyEvent e) 
//        {
//            char c=e.getKeyChar();
//            if(!Character.isDigit(c))
//            {
//                getToolkit().beep();
//                e.consume();
//            }
//        }
//    }

}
