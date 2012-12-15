package ui.subframe;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 */
public class InfoFrame extends JFrame {

    private String infoText;

    //Frame that shows needed information in special "Information panel"
    public InfoFrame(String frameName, String _infoText) {
        super(frameName);
        infoText = _infoText;
        initFrame();
    }

    private void initFrame() {
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(600, 400);
        initFrameComponents();
        this.setVisible(true);
    }

    private void initFrameComponents() {
        JTextArea textInfoArea = new JTextArea(infoText);
        textInfoArea.setEditable(false);
        textInfoArea.setLineWrap(true);
        textInfoArea.setWrapStyleWord(true);
        JScrollPane textScroller = new JScrollPane(textInfoArea);
        this.add(textScroller);
        textScroller.setLocation(20, 20);
        textScroller.setSize(this.getWidth() - textScroller.getLocation().x * 2 - 5, this.getHeight() - textScroller.getLocation().y * 2 - 25);
    }

}
