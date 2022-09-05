/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

Gui class creates the GUI
 * @author Harrison Chen
 * @version 9/4/22

public class GUI extends JPanel implements ActionListener{

    private JFrame frame = new JFrame();
    private JTextArea mainText = new JTextArea(5, 20);
    private JTextField input = new JTextField(10);

    public GUI()
    {  
        super(new GridBagLayout());
        initialize();
        /*frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Regicide Solo");
        frame.setBounds(0, 0, 400, 300);
        frame.setVisible(true);
    }
    private void initialize()
    {
        input.addActionListener(new ActionListener("return ") 
        {
                public String actionPerformed(ActionEvent evt) {return this.getText();}
        });

        mainText.setEditable(false);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(mainText, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(input, c);    
    }
    public void setMainText(String text)
    {
        mainText.setText(text);
    }
}
private void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("Regicide Solo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Add contents to the window.
    frame.add(g);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
}*/