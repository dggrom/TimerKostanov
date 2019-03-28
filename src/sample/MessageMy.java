package sample;

import javax.swing.*;

public class MessageMy {

    static String TitelMEssage;
    String TextMessage;
    JLabel TextLabel;

    public MessageMy(String  TitelM, String TextM){

        TitelMEssage = TitelM;
        TextMessage = TextM;
    }

    public void main (){

        JFrame MyMessage = new JFrame(TitelMEssage);
        MyMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MyMessage.setSize(300,100);
        MyMessage.setLocationRelativeTo(null);

        TextLabel = new JLabel(TextMessage);

        MyMessage.add(TextLabel);

        MyMessage.setVisible(true);
    }

}
