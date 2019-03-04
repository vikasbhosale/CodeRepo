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
public class QueryBuilder 
{    
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
        SyntheticaAluOxideLookAndFeel
        SyntheticaMauveMetallicLookAndFeel
        SyntheticaBlackStarLookAndFeel
        SyntheticaOrangeMetallicLookAndFeel
        SyntheticaBlueLightLookAndFeel
        SyntheticaBlueMoonLookAndFeel
        SyntheticaClassyLookAndFeel
        SyntheticaGreenDreamLookAndFeel
        */
        try
        {
//                UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            Thread.sleep(4500);
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel");            
                new StartWindow().setVisible(true);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        System.out.print("I'm in home");       
    }
    
}
