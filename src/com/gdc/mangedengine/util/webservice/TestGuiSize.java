package com.gdc.mangedengine.util.webservice;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class TestGuiSize {

    public static void addButtonToPanel(JPanel panel, String label) {
        JButton button = new JButton(label);
//        button.setMargin(new Insets(1,1,1,1));
        panel.add(button);
    }

    	 public static void main(String args[]) {
    		    JFrame frame = new JFrame("Tabbed Pane Sample");
    		    Panel panel = new Panel();
    		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		panel.setLayout( new GridLayout() );
    		    JLabel label = new JLabel("Label");
    		    label.setPreferredSize(new Dimension(1000, 1000));
    		    JScrollPane jScrollPane = new JScrollPane(panel);

    		    JButton jButton1 = new JButton("Hello");
    		     JButton jButton2 = new JButton("He");

    		   // jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    		    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    		    jScrollPane.setViewportBorder(new LineBorder(Color.RED));
    		   // jScrollPane.getViewport().add(panel);
    		    jButton1.setContentAreaFilled(false);
    		    frame.add(jScrollPane, BorderLayout.NORTH);
    		    panel.add(jButton1);
//    		     panel.add(jButton2);
    		    frame.setSize(400, 150);
    		    frame.setVisible(true);
    	  }

}