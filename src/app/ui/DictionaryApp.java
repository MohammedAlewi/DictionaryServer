package app.ui;

import app.code.ClientManager;
import app.code.Word;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DictionaryApp {
    private JPanel MainPannel;
    private JTextField search_field;
    private JButton searchButton;
    private JTextField word_field;
    private JTextField origin_field;
    private JTextField first_used_field;
    private JTextArea meaning_field;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton addButton;

    //CODE :: Objects
    private ClientManager clientManager;

    public DictionaryApp(ClientManager clientManager) {
        // creating client manager
        this.clientManager=clientManager;
        JOptionPane.showMessageDialog(null,this.clientManager.initialize_connection());
        updateButton.setEnabled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Word word=clientManager.getWord(search_field.getText().toString());
                if(word==null){
                    JOptionPane.showMessageDialog(null,"Unable to find Your word. :"+search_field.getText().toString());
                }else{
                    updateButton.setEnabled(true);
                    addButton.setEnabled(false);
                    word_field.setText(word.getWord());
                    origin_field.setText(word.getOrigin());
                    first_used_field.setText(word.getfirst_used());
                    for(String meanings :word.getMeanings()){
                        meaning_field.append(meanings);
                        meaning_field.append("\n");
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                search_field.setText("");
                word_field.setText("");
                origin_field.setText("");
                first_used_field.setText("");
                meaning_field.setText("");
            }
        });
        search_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                updateButton.setEnabled(false);
                addButton.setEnabled(true);
            }
        });
    }

    public static void main(String[] arg){
        JFrame frame=new JFrame("Dictionary");
        frame.setContentPane(new DictionaryApp(new ClientManager()).MainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
