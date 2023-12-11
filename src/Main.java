import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class FirstProgram {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SecondProgram secondProgram = new SecondProgram();

            JFrame frame = new JFrame("First Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JRadioButton radioButton8 = new JRadioButton("Стандартний");
            JRadioButton radioButton16 = new JRadioButton("Червоний");
            JRadioButton radioButton24 = new JRadioButton("Жовтий");

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radioButton8);
            buttonGroup.add(radioButton16);
            buttonGroup.add(radioButton24);

            radioButton8.addActionListener(new ColorListener(null, secondProgram));
            radioButton16.addActionListener(new ColorListener(Color.RED, secondProgram));
            radioButton24.addActionListener(new ColorListener(Color.yellow, secondProgram));

            JCheckBox maximizeCheckBox = new JCheckBox("Максимізоване вікно");
            JCheckBox enableButtonCheckBox = new JCheckBox("Кнопка - доступна");

            maximizeCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondProgram.setMaximize(maximizeCheckBox.isSelected());
                }
            });

            enableButtonCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondProgram.setEnableButton(enableButtonCheckBox.isSelected());
                }
            });

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

            JPanel topBlock = new JPanel();
            topBlock.setLayout(new BoxLayout(topBlock, BoxLayout.Y_AXIS));
            topBlock.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.BLACK)));
            topBlock.add(maximizeCheckBox);
            topBlock.add(enableButtonCheckBox);
            leftPanel.add(topBlock);

            JPanel bottomBlock = new JPanel();
            bottomBlock.setLayout(new BoxLayout(bottomBlock, BoxLayout.X_AXIS));
            bottomBlock.add(new JButton("Кнопка"));
            bottomBlock.add(new JLabel("Мітка"));
            leftPanel.add(bottomBlock);


            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

            JPanel fontSizeBlock = new JPanel();
            fontSizeBlock.setLayout(new BoxLayout(fontSizeBlock, BoxLayout.Y_AXIS));
            fontSizeBlock.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.BLACK)));
            fontSizeBlock.add(new JLabel("Колір"));
            fontSizeBlock.add(radioButton8);
            fontSizeBlock.add(radioButton16);
            fontSizeBlock.add(radioButton24);
            rightPanel.add(fontSizeBlock);

            JPanel mainPanel = new JPanel(new GridLayout(1, 2));
            mainPanel.add(leftPanel);
            mainPanel.add(rightPanel);



            frame.add(mainPanel);
            frame.setSize(500, 200);
            frame.setVisible(true);
        });
    }


     static class ColorListener implements ActionListener {
         private Color color;
         private SecondProgram secondProgram;

         ColorListener(Color color, SecondProgram secondProgram) {
             this.color = color;
             this.secondProgram = secondProgram;
         }

         @Override
         public void actionPerformed(ActionEvent e) {
             secondProgram.setLabelColor(color);
         }
     }

}
class SecondProgram {
    private Color color;
    private boolean maximize = false;
    private boolean enableButton = false;

    private JFrame frame;
    private JButton button;
    private JLabel label;

    public SecondProgram() {
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Second Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JRadioButton radioButton8 = new JRadioButton("Стандартний");
        JRadioButton radioButton16 = new JRadioButton("Червоний");
        JRadioButton radioButton24 = new JRadioButton("Жовтий");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton8);
        buttonGroup.add(radioButton16);
        buttonGroup.add(radioButton24);


        JCheckBox maximizeCheckBox = new JCheckBox("Максимізоване вікно");
        JCheckBox enableButtonCheckBox = new JCheckBox("Кнопка - доступна");


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel topBlock = new JPanel();
        topBlock.setLayout(new BoxLayout(topBlock, BoxLayout.Y_AXIS));
        topBlock.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)));
        topBlock.add(maximizeCheckBox);
        topBlock.add(enableButtonCheckBox);
        leftPanel.add(topBlock);

        JPanel bottomBlock = new JPanel();
        bottomBlock.setLayout(new BoxLayout(bottomBlock, BoxLayout.X_AXIS));
        button = new JButton("Кнопка");
        label = new JLabel("Мітка");
        bottomBlock.add(button);
        bottomBlock.add(label);
        leftPanel.add(bottomBlock);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel fontSizeBlock = new JPanel();
        fontSizeBlock.setLayout(new BoxLayout(fontSizeBlock, BoxLayout.Y_AXIS));
        fontSizeBlock.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)));
        fontSizeBlock.add(new JLabel("Колір"));
        fontSizeBlock.add(radioButton8);
        fontSizeBlock.add(radioButton16);
        fontSizeBlock.add(radioButton24);
        rightPanel.add(fontSizeBlock);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);
        frame.setSize(500, 200);



        frame.setVisible(true);
    }


    public void setLabelColor(Color newColor) {

        color = newColor;
        updateUI();
    }

    public void setMaximize(boolean value) {
        maximize = value;
        updateUI();
    }

    public void setEnableButton(boolean value) {
        enableButton = value;
        updateUI();
    }

    private void updateUI() {
        frame.getContentPane().setBackground(Color.RED);
        frame.repaint();
        frame.setVisible(true);

//        SwingUtilities.invokeLater(() -> {
            label.setForeground(color);
            frame.setExtendedState(maximize ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
            button.setEnabled(enableButton);

//        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SecondProgram());
    }
}
