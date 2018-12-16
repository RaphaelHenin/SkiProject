import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Interface  extends JFrame
{
    private JTextArea textArea1;
    private JPanel rootPanel;

    public Interface()
    {
        add(rootPanel);
        setTitle("ABOUT YOU");
        setSize(400, 500);
        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                JOptionPane.showMessageDialog(rootPanel, "Please complete your information");
            }
        });
    }
}
