package ru.sfedu.bideet;

import java.awt.*;
import javax.swing.border.Border;

/**
 *
 * @author Osebi
 */
public class RoundedBorder implements Border{
    private final int radius;
    
    public RoundedBorder(int radius){
        this.radius = radius;
    }
    
    @Override
    public Insets getBorderInsets(Component c){
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
    
    @Override
    public boolean isBorderOpaque(){
        return true;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}