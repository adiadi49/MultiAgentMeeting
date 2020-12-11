package jadeproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class AuthenticatorGui extends JFrame {
	
	private Authenticator myAgent;
    private JTextField usernameField;
    private JTextField passwordField;
    private ArrayList<String> active_users = new ArrayList<String>();
    AuthenticatorGui(Authenticator a) {
        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        p.add(usernameField);
        p.add(new JLabel("Password:"));
        passwordField = new JTextField(15);
        p.add(passwordField);
        
        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Submit");
        addButton.addActionListener(ev -> {
            try {
                String Username = usernameField.getText().trim();
                String Password = passwordField.getText().trim();
                
                Boolean validusers = checkusername(Username, Password); // send new meeting day to agent
                
                if (validusers) {
                	System.out.println("valid user found");
                	myAgent.createAgent(Username, "jadeproject.ParticipantAgent");
                }
            }finally {
            	usernameField.setText("");
                passwordField.setText("");
			} 
        });
        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

        setResizable(false);
    }

    public void display() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        setVisible(true);
    }
    
    public boolean checkusername(String username, String password) {
    	
    	if(Settings.users.get(username) != null && !(active_users.contains(username))) {
    		 System.out.println("Username Found!");
    		 String user_password = Settings.users.get(username);
    		 if(user_password.equals(password)) {
    			 System.out.println("Password Matched");
    			 active_users.add(username);
    			 return true;
    		 }
    	}
            

        return false;
    	
		
    	
    }

	
}
