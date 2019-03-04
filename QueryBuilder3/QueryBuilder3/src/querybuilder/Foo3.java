/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Foo3 extends JPanel {
   private static final int PREF_W = 400;
   private static final int PREF_H = PREF_W;
   private boolean tracking = false;
   private int x, y;

   public Foo3() {
      add(new JToggleButton(new AbstractAction("TrackMouse") {
         public void actionPerformed(ActionEvent ae) {
            trackMouse(ae);
         }
      }));

      MyMouseAdapter myMA = new MyMouseAdapter();
      addMouseMotionListener(myMA);
   }

   private void trackMouse(ActionEvent ae) {
      JToggleButton toggleBtn = (JToggleButton) ae.getSource();
      tracking = toggleBtn.isSelected();
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   public void msg(String message) {
      System.out.println(message);
   }

   private class MyMouseAdapter extends MouseAdapter {
      @Override
      public void mouseMoved(MouseEvent e) {
         if (tracking) { 
            x = e.getX();
            y = e.getY();
            msg("(" + x + ", " + y + ")");
         }
      }
   }

   private static void createAndShowGui() {
      Foo3 mainPanel = new Foo3();

      JFrame frame = new JFrame("MouseMotion Eg");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}
