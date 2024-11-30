package kr.mrstudio;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseFrame extends JFrame {

    public BaseFrame(String title) {
        super(title);

        setResizable(false);
        requestFocusInWindow();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(840,540);
        setVisible(true);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        // 암호화 복호화 instance 로드
        EncryptionManager encryptionManager = new EncryptionManager();

        // UI 구성
        JLabel label1 = new JLabel("암호화할 문자를 입력해 주세요:");
        label1.setBounds(70, 100, 500, 30);
        add(label1);

        // UI 구성
        JLabel label2 = new JLabel("문자 기본 암호화");
        label2.setBounds(70, 170, 500, 30);
        add(label2);

        // UI 구성
        JTextArea baseEncrypt = new JTextArea();
        baseEncrypt.setLineWrap(true);
        baseEncrypt.setWrapStyleWord(true);
        baseEncrypt.setEditable(false);
        baseEncrypt.setFocusable(true);
        baseEncrypt.setBounds(70, 200, 300, 100);
        add(baseEncrypt);

        // UI 구성
        JTextArea irrationEncrypt = new JTextArea();
        irrationEncrypt.setLineWrap(true);
        irrationEncrypt.setWrapStyleWord(true);
        irrationEncrypt.setEditable(false);
        irrationEncrypt.setFocusable(true);
        irrationEncrypt.setBounds(70, 380, 300, 30);
        add(irrationEncrypt);

        // UI 구성
        JLabel label3 = new JLabel("무리함수로 암호화된 데이터");
        label3.setBounds(70, 350, 500, 30);
        add(label3);

        // 암호화할 데이터 입력 공간
        JTextField textField = new JTextField();
        textField.setBounds(70, 130, 300, 30);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TextField가 비어있을 때는 계산하지 않도록 방지
                if (textField.getText().isEmpty()) {
                    baseEncrypt.setText(null);
                    irrationEncrypt.setText(null);
                    return;
                }

                // 암호화
                baseEncrypt.setText(encryptionManager.encrypt(textField.getText()));
                irrationEncrypt.setText(encryptionManager.encryptIrrationalFunction(Double.parseDouble(baseEncrypt.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TextField가 비어있을 때는 계산하지 않도록 방지
                if (textField.getText().isEmpty()) {
                    baseEncrypt.setText(null);
                    irrationEncrypt.setText(null);
                    return;
                }

                // 암호화
                baseEncrypt.setText(encryptionManager.encrypt(textField.getText()));
                irrationEncrypt.setText(encryptionManager.encryptIrrationalFunction(Double.parseDouble(baseEncrypt.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TextField가 비어있을 때는 계산하지 않도록 방지
                if (textField.getText().isEmpty()) {
                    baseEncrypt.setText(null);
                    irrationEncrypt.setText(null);
                    return;
                }

                // 암호화
                baseEncrypt.setText(encryptionManager.encrypt(textField.getText()));
                irrationEncrypt.setText(encryptionManager.encryptIrrationalFunction(Double.parseDouble(baseEncrypt.getText())));
            }
        });
        textField.setVisible(true);
        add(textField);
        textField.requestFocusInWindow();

        // UI 구성
        JTextArea irrationDecrypt = new JTextArea();
        irrationDecrypt.setLineWrap(true);
        irrationDecrypt.setWrapStyleWord(true);
        irrationDecrypt.setEditable(false);
        irrationDecrypt.setFocusable(true);
        irrationDecrypt.setBounds(450, 200, 300, 30);
        add(irrationDecrypt);

        // UI 구성
        JLabel label4 = new JLabel("무리함수로 암호화된 데이터 복호화");
        label4.setBounds(450, 100, 500, 30);
        add(label4);
        label4.repaint();

        // 복호화 input
        JTextField textField2 = new JTextField();
        textField2.setVisible(true);
        textField2.setBounds(450, 130, 300, 30);
        add(textField2);
        textField2.repaint();

        // 복호화 UI
        JButton button = new JButton("암호 복호화");
        button.setBounds(650, 100, 99, 30);
        add(button);
        button.repaint();

        // UI 클릭 시 복호화 진행
        button.addActionListener(e -> {
            if (textField2.getText().isEmpty()) {
                irrationDecrypt.setText(null);
                return;
            }

            irrationDecrypt.setText(encryptionManager.decryptIrrationalFunction(Double.parseDouble(textField2.getText())));
        });
    }
}
