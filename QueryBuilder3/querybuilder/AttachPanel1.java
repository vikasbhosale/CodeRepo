/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
/**
 *
 * @author VIKAS
 */
public class AttachPanel1 extends JPanel{
    private JLabel label;
    private JTextField textfield;
    private JButton pathbtn;
    public AttachPanel1() 
    {
        label = new JLabel();
        textfield = new JTextField();
        pathbtn=new JButton();
//        setBackground(new java.awt.Color(0, 102, 102));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
//        label.setForeground(new java.awt.Color(255, 255, 255));        
        label.setText("jLabel1");
        label.setPreferredSize(new Dimension(120, 25));
        textfield.setPreferredSize(new Dimension(160, 25));
        pathbtn.setPreferredSize(new Dimension(40, 30));
        pathbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/open-file-icon.png")));
        add(label);
        add(textfield);
        add(pathbtn);
        //add action listener
        pathbtn.addActionListener(new ActionWork());        
    }



    
    /**
     * @return the pathbtn
     */
    public JButton getPathbtn() {
        return pathbtn;
    }

    /**
     * @param pathbtn the pathbtn to set
     */
    public void setPathbtn(JButton pathbtn) {
        this.pathbtn = pathbtn;
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
     * @return the textfield
     */
    public JTextField getTextfield() {
        return textfield;
    }

    /**
     * @param textfield the textfield to set
     */
    public void setTextfield(JTextField textfield) {
        this.textfield = textfield;
    }
    public class ActionWork implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File f=fileChooser.getSelectedFile();
        String filename=f.getAbsolutePath();
            getTextfield().setText(filename);
        }
        
    }
    
}
