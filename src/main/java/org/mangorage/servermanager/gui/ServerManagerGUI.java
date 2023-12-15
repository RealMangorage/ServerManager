package org.mangorage.servermanager.gui;

import org.mangorage.servermanager.core.IServerManager;
import org.mangorage.servermanager.core.process.LazyProcess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ServerManagerGUI extends JFrame {
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler4;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton6;
    private JButton jButton7;
    private JLabel outputStreamLabel;
    private JLabel jLabel2;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private ButtonList serverList;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane6;
    private JScrollPane jScrollPane7;
    private JTextArea outputTextArea;
    private JTextArea jTextArea2;
    private JTextArea jTextArea5;
    private JTextArea jTextArea6;
    private JTextField inputBox;

    private LazyProcess selected = null;
	private JCheckBox jCheckBox1;
	private JCheckBox jCheckBox2;
	private JCheckBox jCheckBox3;
	private JCheckBox jCheckBox4;
	private JLabel jLabel3;
	private JButton jButton8;

    public ServerManagerGUI(IServerManager serverManager) {
        initComponents(serverManager);
    }

    @SuppressWarnings("unchecked")
    private void initComponents(IServerManager serverManager) {
        jScrollPane1 = new JScrollPane();
        serverList = new ButtonList(serverManager.getProcesses());
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jScrollPane2 = new JScrollPane();
        outputTextArea = new JTextArea();
        inputBox = new JTextField();
        outputStreamLabel = new JLabel();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        filler2 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 32767));
        filler4 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        jButton6 = new JButton();
        jScrollPane3 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jLabel2 = new JLabel();
        jLabel5 = new JLabel();
        jScrollPane6 = new JScrollPane();
        jTextArea5 = new JTextArea();
        jLabel6 = new JLabel();
        jScrollPane7 = new JScrollPane();
        jTextArea6 = new JTextArea();
        jButton4 = new JButton();
        jButton7 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Manager");

        jScrollPane1.setViewportView(serverList);

        inputBox.addActionListener(this::inputBoxEvent);

        jButton1.setLabel("Start");
        jButton1.addActionListener(this::startServer);

        jButton2.setLabel("Stop");
        jButton2.addActionListener(this::stopServer);

        jButton3.setLabel("Clear");
        jButton3.addActionListener(this::clearOutput);

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        outputStreamLabel.setText("Output Stream for X");

        jButton6.setLabel("Delete");
        jButton6.addActionListener(this::deleteServer);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jLabel2.setText("Name");

        jLabel5.setText("Directory");

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane6.setViewportView(jTextArea5);

        jLabel6.setText("Startup args");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane7.setViewportView(jTextArea6);

        jButton4.setText("Save");
        jButton4.addActionListener(this::saveConfig);

        jButton7.setText("Clear");
        jButton7.addActionListener(this::clearConfig);

        serverList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                var list = (JList<LazyProcess>) e.getSource();
                var selectedValue = list.getSelectedValue();
                setSelected(selectedValue);
            }
        });

        
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jCheckBox3 = new JCheckBox();
        jCheckBox4 = new JCheckBox();
        jLabel3 = new JLabel();
        jButton8 = new JButton();
        
        jCheckBox1.setText("Auto Start");
        jCheckBox2.setText("Auto Restart");

        jCheckBox3.setText("Auto Start");
        jCheckBox4.setText("Auto Restart");

        jLabel3.setText("Servers");

        jButton8.setText("Deselect Servers");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(12, 12, 12)
                                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addComponent(jScrollPane7)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox1))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox2)
                                        .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(outputStreamLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(filler2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputBox)
                .addContainerGap())
        );

        layout.linkSize(SwingConstants.HORIZONTAL, jButton1, jButton2, jButton3);

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(outputStreamLabel)
                    .addComponent(jLabel3)
                    .addComponent(jButton8))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(filler4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton6))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox3)
                                    .addComponent(jCheckBox4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jButton7))
                                .addGap(0, 29, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );        
        

        pack();
    }

    private void setSelected(LazyProcess process) {
        selected = process;

        if (process != null) {
            outputTextArea.setText(process.getLog());
            outputStreamLabel.setText("Output Stream for: " + process.getLazyID());
            selected.hookOutput(s -> {
                outputTextArea.setText(s);
            });
        } else {
            // Handle this logic
        }
    }

    private void inputBoxEvent(ActionEvent actionEvent) {
        if (selected == null) return;
        selected.printInput(inputBox.getText());
        inputBox.setText("");
    }

    private void clearConfig(ActionEvent actionEvent) {
    }

    private void saveConfig(ActionEvent actionEvent) {
    }

    private void deleteServer(ActionEvent actionEvent) {
    }

    private void clearOutput(ActionEvent actionEvent) {
        outputTextArea.setText("");
    }

    private void stopServer(ActionEvent actionEvent) {
        if (selected == null) return;
        selected.forceStopProcess();
    }

    private void startServer(ActionEvent actionEvent) {
        if (selected == null) return;
        selected.start();
    }
}
