package org.hydra.gui.swing;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hydra.renamer.ClassInfo;

public class ClassInfoPanel extends JPanel {
    private static final long serialVersionUID = -4582279901749032270L;
    private JTextField        className;
    private ClassInfo         classInfo;

    public ClassInfoPanel(ClassInfo info) {
        this.classInfo = info;

        this.setBorder(BorderFactory.createTitledBorder("Class Basic Info"));
        setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblFlags = new JLabel(this.classInfo.getFlagString());
        add(lblFlags);

        className = new JTextField();
        add(className);
        className.setColumns(10);
        className.setText(this.classInfo.getClassName());
        add(new JLabel("Super Class:"));
        if (this.classInfo.getSuperClass() != null) {
            // 范围内的class，可修改
            add(new JButton(this.classInfo.getSuperClassName()));
        } else if (this.classInfo.getSuperClassName() != null) {
            add(new JLabel(this.classInfo.getSuperClassName()));
        }

        if (this.classInfo.getInterfaceNames().size() > 0) {
            JLabel label = new JLabel("Interfaces:");
            label.setVerticalTextPosition(SwingConstants.TOP);
            add(label);

            add(new MultiClassNamePanel(this.classInfo.getInterfaceNames()));
        }
        if (this.classInfo.getChildrenNames().size() > 0) {
            JLabel label = new JLabel("Sub Classes:");
            label.setVerticalTextPosition(SwingConstants.TOP);
            add(label);

            add(new MultiClassNamePanel(this.classInfo.getChildrenNames()));
        }
        revalidate();
    }

    public String getNewClassName() {
        return this.className.getText();
    }

    public String getChangeScript() {
        return String.format("class: %s to %s\n", this.classInfo.getClassName(),
            this.className.getText());
    }

    public void resetChanges() {
        this.className.setText(this.classInfo.getClassName());
    }
}
