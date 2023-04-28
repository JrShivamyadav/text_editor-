
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem openFile, saveFile, closeFile, printFile, cutText, copyText, pasteText;
    JButton saveAndSubmitButton;

    public TextEditor() {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 100,1000,700);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        openFile = new JMenuItem("Open");
        openFile.addActionListener(this);
        fileMenu.add(openFile);
        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);
        fileMenu.add(saveFile);
        closeFile = new JMenuItem("Close");
        closeFile.addActionListener(this);
        fileMenu.add(closeFile);
        printFile = new JMenuItem("Print");
        printFile.addActionListener(this);
        fileMenu.add(printFile);
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        cutText = new JMenuItem("Cut");
        cutText.addActionListener(this);
        editMenu.add(cutText);
        copyText = new JMenuItem("Copy");
        copyText.addActionListener(this);
        editMenu.add(copyText);
        pasteText = new JMenuItem("Paste");
        pasteText.addActionListener(this);
        editMenu.add(pasteText);
        menuBar.add(editMenu);

        saveAndSubmitButton = new JButton("Save and Submit");
        saveAndSubmitButton.addActionListener(this);

        frame.setJMenuBar(menuBar);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(saveAndSubmitButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    textArea.setText("");
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == closeFile) {
            System.exit(0);
        } else if (e.getSource() == printFile) {
            try {
                textArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cutText) {
            textArea.cut();
        } else if (e.getSource() == copyText) {
            textArea.copy();
        } else if (e.getSource() == pasteText){
            textArea.paste();
        }
        }

    public static void main(String[] args) {

        new TextEditor();
    }
}
