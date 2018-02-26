package viewCont;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/*  class:        ChildClosing.java
 *  Description:  This class closes the child window and re enables the parent window. 
 */
public class CloseChild extends WindowAdapter
{
   private JFrame childFrame;
	
	/**
    *  Method:         ChildClosing (Constructor)
    *
    *  Description:    Creates the ChildClosing class
    *  
    *  @param			f : The parent window
    */		
   public CloseChild(JFrame f)
   {
	   childFrame = f;
   }
	/**
    *  Method:         windowClosing
    *
    *  Description:    Enables the parent window, and calls requestFocus()
    *  
    *  @param			e : WindowEvent 
    */		
   public void windowClosing(WindowEvent e)
   {
	   childFrame.setEnabled(true);
	   childFrame.requestFocus();
   }	
}