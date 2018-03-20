/*  class:        StaticMethods
 * 
 */
package util;
import javax.swing.*;
import java.awt.*;

public class StaticMethods
{ 

	
/**
 * Method: PositionAndShow : this method positions the frame in the centre of the monitor screen and makes it visible.  
 * @param frame : The frame to position
 */
   public static void positionAndShow(JFrame frame)
   {
      Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
      Rectangle sizeOfFrame = frame.getBounds();
      
      Double dX = (new Double(point.getX() - (sizeOfFrame.getWidth()/2)));
      Double dY = (new Double(point.getY() - (sizeOfFrame.getHeight()/2)));
  
      frame.setLocation(dX.intValue(),dY.intValue());
      frame.setVisible(true);
      
   } // positionAndShow

//======================================================================
   
   public static String checkIntInput(String input)
   {
      String warning = "";
	   try
      {
         if (input.equals(""))
        	 warning += "Please enter a value\n";
         else
            Double.valueOf(input);
      }
      catch (Exception e)
      {
    	  warning += "Please enter number format!\n";
      }
      return warning;
   }
   
   public static boolean checkError(String toBeChecked)
   {
	   if (toBeChecked.equals(""))
		   return true;
	   else
		   return false;
   }
      
   public static void alertMessage (String warning)
   {
      JOptionPane.showMessageDialog(null, warning, "There are errors", JOptionPane.ERROR_MESSAGE);	
   }

   public static void outputMessage (String toScreen)
   {
      JOptionPane.showMessageDialog(null, toScreen, "Result", JOptionPane.INFORMATION_MESSAGE);	
   }
   

} 