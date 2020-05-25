package schoolManegementSystem;

import javax.swing.*;
import java.awt.*;
public class Randerer extends DefaultListCellRenderer implements ListCellRenderer<Object>
{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       ImgNtext is = (ImgNtext) value;
       setIcon(is.getImage());
        setText(is.getName());
       setIconTextGap(10);

//
       setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(240,240,240)));

       if(isSelected){
           setBackground(list.getSelectionBackground());
           setForeground(list.getSelectionForeground());

       }
       else {
           setBackground(list.getBackground());
           setForeground(list.getForeground());
       }
       setEnabled(true);
       setFont(list.getFont());
       return this;
    }
}