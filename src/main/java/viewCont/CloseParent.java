package viewCont;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 *    Class ParentClosing
 * 		Description: Closes the parent frame.
 *
 */
public class CloseParent extends WindowAdapter
{        
	/**
	    *  Method:         windowClosing
	    *
	    *  Description:    Exists the system()
	    *  
	    *  @param			e : WindowEvent 
	    */		
   public void windowClosing(WindowEvent e)
   {
      System.exit(1);
   }
}