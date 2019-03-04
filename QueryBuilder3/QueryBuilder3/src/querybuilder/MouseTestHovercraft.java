/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class MouseTestHovercraft extends JPanel {
   private static final int PREF_W = 600;
   private static final int PREF_H = PREF_W;
   private static final int MAX_CLR = 5;
   private static final Color CURRENT_LIST_COLOR = new Color(190, 190, 255);
   private List<Color> colors = new ArrayList<Color>();
   private boolean tracking = false;
   private List<Point> currentList = null;
   private BufferedImage bufferedImage = new BufferedImage(PREF_W, PREF_H,
         BufferedImage.TYPE_INT_ARGB);
   private Random random = new Random();

   public MouseTestHovercraft() {
      for (int redIndex = 0; redIndex < MAX_CLR; redIndex++) {
         int r = (redIndex * 256) / (MAX_CLR - 1);
         r = (r == 256) ? 255 : r;
         for (int greenIndex = 0; greenIndex < MAX_CLR; greenIndex++) {
            int g = (greenIndex * 256) / (MAX_CLR - 1);
            g = (g == 256) ? 255 : g;
            for (int blueIndex = 0; blueIndex < MAX_CLR; blueIndex++) {
               int b = (blueIndex * 256) / (MAX_CLR - 1);
               b = (b == 256) ? 255 : b;
               Color c = new Color(r, g, b);
               colors.add(c);
            }
         }
      }

      add(new JToggleButton(new AbstractAction("TrackMouse") {
         public void actionPerformed(ActionEvent ae) {
            trackMouse(ae);
         }
      }));
      add(new JButton(new AbstractAction("Clear Image") {
         public void actionPerformed(ActionEvent e) {
            bufferedImage = new BufferedImage(getWidth(), getHeight(),
                  BufferedImage.TYPE_INT_ARGB);
            repaint();
         }
      }));

      MyMouseAdapter myMA = new MyMouseAdapter();
      addMouseListener(myMA);
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

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(bufferedImage, 0, 0, null);
      if (currentList != null) {
         drawList(g, currentList, CURRENT_LIST_COLOR, 1f);
      }
   }

   private void drawList(Graphics g, List<Point> ptList, Color color,
         float strokeWidth) {
      if (ptList.size() > 1) {
         Graphics2D g2 = (Graphics2D) g.create();
         g2.setColor(color);
         g2.setStroke(new BasicStroke(strokeWidth));
         for (int j = 0; j < ptList.size() - 1; j++) {
            int x1 = ptList.get(j).x;
            int y1 = ptList.get(j).y;
            int x2 = ptList.get(j + 1).x;
            int y2 = ptList.get(j + 1).y;
            g2.drawLine(x1, y1, x2, y2);
         }
         g2.dispose();
      }
   }

   private class MyMouseAdapter extends MouseAdapter {

      @Override
      public void mousePressed(MouseEvent e) {
         if (tracking && e.getButton() == MouseEvent.BUTTON1) {
            currentList = new ArrayList<Point>();
            currentList.add(e.getPoint());
         }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         if (tracking && e.getButton() == MouseEvent.BUTTON1) {
            currentList.add(e.getPoint());
            Graphics2D g2 = bufferedImage.createGraphics();
            Color color = colors.get(random.nextInt(colors.size()));
            drawList(g2, currentList, color, 3f);
            currentList = null;
            repaint();
         }
      }

      @Override
      public void mouseDragged(MouseEvent e) {
         if (tracking && currentList != null) {
            currentList.add(e.getPoint());
            repaint();
         }
      }
   }

   private static void createAndShowGui() {
      MouseTestHovercraft mainPanel = new MouseTestHovercraft();

      JFrame frame = new JFrame("MouseMotion Eg");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.setResizable(false);
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