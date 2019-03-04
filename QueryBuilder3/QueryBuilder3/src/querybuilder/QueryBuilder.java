/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package querybuilder;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author vikas
 */
public class QueryBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       /*
         for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
       {
           if("SeaGlassLookAndFeel".equals(info.getName()))
           {
               try {
                   UIManager.setLookAndFeel(info.getClassName());
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
               } catch (InstantiationException ex) {
                   Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IllegalAccessException ex) {
                   Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
               } catch (UnsupportedLookAndFeelException ex) {
                   Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
               }
               break;
           }
       }
        com.alee.laf.WebLookAndFeel
        com.birosoft.liquid.LiquidLookAndFeel
        com.seaglasslookandfeel.SeaGlassLookAndFeel
        org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel
        com.jgoodies.looks.plastic.Plastic3DLookAndFeel
        ch.randelshofer.quaqua.QuaquaLookAndFeel
        com.jtattoo.plaf.hifi.HiFiLookAndFeel
        com.jtattoo.plaf.noire.NoireLookAndFeel
        com.jtattoo.plaf.mint.MintLookAndFeel
        com.jtattoo.plaf.graphite.GraphiteLookAndFeel
        com.jtattoo.plaf.Luna.LunaLookAndFeel
        com.jtattoo.plaf.texture.TextureLookAndFeel
        com.jtattoo.plaf.mcwin.McWinLookAndFeel
        com.jtattoo.plaf.aero.AeroLookAndFeel
        com.jtattoo.plaf.acryl.AcrylLookAndFeel
        com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
        com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
        com.jtattoo.plaf.fast.FastLookAndFeel
        */
        try
        {
                UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                new StartWindow().setVisible(true);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        System.out.print("I'm in home");
        //SelectQuery ap=new SelectQuery();
        //UpdateQuery uq=new UpdateQuery();
        //DeleteQuery dq=new DeleteQuery();
        //CreateQuery cq=new CreateQuery();
        //InsertQuery iq=new InsertQuery();
//        JFrame f=new JFrame();
//        f.setVisible(true);
//        //f.add(ap);
//        //f.add(uq);
//        //f.add(dq);
//        //f.add(cq);
//        //f.add(iq);
//        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//        f.setSize(400, 400);
//        f.pack();       
//       new QBPanel1().setVisible(true);
//        new StartWindow().setVisible(true);
//        new FlashScreen().setVisible(true);
//        new Thread(){
//            public void run(){
//                for(int i=0 ;i<=100 ;i++)
//                {
//                    try {
//                            sleep(100);                        
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(FlashScreen.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                qb.setVisible(true);
//            }
//        }.start();
        //new Table2().setVisible(true);
        //new DragDropDemo().setVisible(true);
    }
    
}
