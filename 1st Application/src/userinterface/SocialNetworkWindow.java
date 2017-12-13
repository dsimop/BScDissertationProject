package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import social.SocialNetwork;
import social.User;

public class SocialNetworkWindow extends JFrame implements ListSelectionListener, MouseListener, ActionListener {

    SocialNetwork network;
    JList userlist;
    JDialog newdialog;
    JTextField txtUser;
    JTextField mailUser;
    JTextField dateUser;
    JList lstFriends;
    User currentUser;
    User otherUser;
    JPanel userPanel;
    JScrollPane listScroller;
    JScrollPane lstScroller;
    DefaultListModel<User> model;
    JButton button2 = new JButton("Update");
    JButton button3 = new JButton("Delete");

    public SocialNetworkWindow(SocialNetwork sn) {
        setTitle("Social Network");
        this.setResizable(false);
        network = sn;
        JLabel userLabel;
        JLabel dateLabel;
        JLabel mailLabel;
        JLabel friendsLabel;
        model = new DefaultListModel<>();
        userLabel = new JLabel("Name:");
        userLabel.setBounds(10, 10, 100, 20);
        mailLabel = new JLabel("E-Mail:");
        mailLabel.setBounds(10, 70, 100, 20);
        dateLabel = new JLabel("Birthday:");
        dateLabel.setBounds(10, 40, 100, 20);
        txtUser = new JTextField();
        txtUser.setBounds(130, 10, 150, 20);
        mailUser = new JTextField();
        mailUser.setBounds(130, 70, 150, 20);
        dateUser = new JTextField();
        dateUser.setBounds(130, 40, 150, 20);
        friendsLabel = new JLabel("Friends:");
        friendsLabel.setBounds(10, 100, 100, 20);
        lstFriends = new JList();
        lstFriends.addMouseListener(this);
        lstFriends.setBounds(130, 100, 150, 80);
        JButton addbutton = new JButton("+");
        JButton removebutton = new JButton("-");
        addbutton.setBounds(300, 100, 50, 20);
        removebutton.setBounds(300, 125, 50, 20);
        txtUser.setEditable(false);
        mailUser.setEditable(false);
        dateUser.setEditable(false);
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                addfriendActionPerformed(event);
            }
        });
        removebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                RemoveUserAction(event);
            }
        });

        //The Panel
        userPanel = new JPanel();
        userPanel.setLayout(null);
        userPanel.add(userLabel);
        userPanel.add(txtUser);
        userPanel.add(mailLabel);
        userPanel.add(mailUser);
        userPanel.add(dateUser);
        userPanel.add(dateLabel);
        userPanel.add(friendsLabel);
        userPanel.add(lstFriends);
        userPanel.add(addbutton);
        userPanel.add(removebutton);
        userPanel.setPreferredSize(new Dimension(400, 200));
        add(userPanel, BorderLayout.CENTER);
        for (User allusers : sn.getUsers()) {
            model.addElement(allusers);
        }
        userlist = new JList(model);
        userlist.addListSelectionListener(this);
        userlist.setFixedCellHeight(20);
        userPanel.add(userlist);

        //JToolbar and JButtons Added
        JToolBar ToolBar = new JToolBar();
        ToolBar.setFloatable(false);
        add(ToolBar, BorderLayout.NORTH);
        ToolBar.setPreferredSize(new Dimension(300, 30));
        JButton button1 = new JButton("Create");
        button1.addActionListener(new NewUserAction());
        ToolBar.add(button1);
        button2.setEnabled(false);
        ToolBar.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                UpdateUserAction(event);
            }
        });
        button3.setEnabled(false);
        ToolBar.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DeleteUserAction(event);
            }
        });

        //JScrollpane Added
        listScroller = new JScrollPane();
        listScroller.setViewportView(userlist);
        listScroller.setPreferredSize(new Dimension(70, 50));
        add(listScroller, BorderLayout.LINE_START);
        lstScroller = new JScrollPane();
        lstScroller.setViewportView(lstFriends);
        lstScroller.setBounds(130, 100, 150, 80);
        userPanel.add(lstScroller);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int index = userlist.getSelectedIndex(); // !!!
        if (index > -1) {
            button2.setEnabled(true);
            button3.setEnabled(true);
            txtUser.setEditable(true);
            mailUser.setEditable(true);
            dateUser.setEditable(true);
            currentUser = network.getUsers().elementAt(index);
            txtUser.setText(currentUser.getName());
            dateUser.setText(currentUser.getDate());
            mailUser.setText(currentUser.getMail());
            lstFriends.setListData(currentUser.getFriends());
        }
    }

    // Double Click Task
    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            int index = lstFriends.getSelectedIndex();
            currentUser = currentUser.getFriends().elementAt(index);
            dateUser.setText(currentUser.getDate());
            mailUser.setText(currentUser.getMail());
            txtUser.setText(currentUser.getName());
            lstFriends.setListData(currentUser.getFriends());
            int index2 = model.indexOf(currentUser);
            userlist.setSelectedIndex(index2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void RemoveUserAction(ActionEvent event) {
        int index = lstFriends.getSelectedIndex();
        if (index >= 0) {
            otherUser = currentUser.getFriends().elementAt(index);
            otherUser.getFriends().remove(currentUser);
            currentUser.getFriends().remove(index);
            userPanel.updateUI();
        } else {
            JOptionPane.showMessageDialog(userPanel, "Choose a User First!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void UpdateUserAction(ActionEvent event) {
        int index = userlist.getSelectedIndex();
        if (index >= 0) {
            currentUser = network.getUsers().elementAt(index);
            String v1 = txtUser.getText();
            String v2 = dateUser.getText();
            String v3 = mailUser.getText();
            User newuser = new User(v1, v2, v3);
            currentUser.setName(newuser);
            currentUser.setDate(newuser);
            currentUser.setEMail(newuser);
            model.set(index, currentUser);
        } else {
            JOptionPane.showMessageDialog(userPanel, "Choose a User First!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void DeleteUserAction(ActionEvent event) {
        int index = userlist.getSelectedIndex();
        if (index >= 0) {
            currentUser = network.getUsers().elementAt(index);
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialresult = JOptionPane.showConfirmDialog(null, "Would You Like to Delete the Selected User?", "Warning", dialogButton);
            if (dialresult == 0) {
                model.removeElementAt(index);
                for (User allfriends : currentUser.getFriends()) {
                    allfriends.getFriends().remove(currentUser);
                }
                network.removeUser(currentUser);
                txtUser.setEditable(false);
                mailUser.setEditable(false);
                dateUser.setEditable(false);
                txtUser.setText(null);
                Vector<String> v = new Vector<String>();
                lstFriends.setListData(v);
                mailUser.setText(null);
                dateUser.setText(null);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(userPanel, "Choose a User First!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addfriendActionPerformed(ActionEvent event) {
        JDialog addusdialog = new JDialog();
        DefaultListModel<User> tempmodel;
        tempmodel = new DefaultListModel<>();
        tempmodel.removeAllElements();
        int index = userlist.getSelectedIndex();
        if (index >= 0) {
            JList newlist = new JList();
            for (User allusers : network.getUsers()) {
                tempmodel.addElement(allusers);
            }
            newlist.setModel(tempmodel);
            tempmodel.remove(index);
            JScrollPane scrollPane1 = new JScrollPane(newlist);
            for (User allfriends : currentUser.getFriends()) {
                tempmodel.removeElement(allfriends);
            }
            if (tempmodel.isEmpty()) {
                JOptionPane.showMessageDialog(addusdialog, "You cannot add more friends!", "Warning",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                addusdialog.setVisible(true);
                addusdialog.setTitle("Add Friends");
                JLabel txt = new JLabel("Choose a Friend and Click the <<Add>> Button.");
                JButton buttaddf = new JButton("Add");
                JButton buttclose = new JButton("Close");
                addusdialog.add(txt, BorderLayout.PAGE_START);
                addusdialog.add(scrollPane1, BorderLayout.CENTER);
                addusdialog.add(buttclose, BorderLayout.SOUTH);
                addusdialog.add(buttaddf, BorderLayout.WEST);
                addusdialog.setResizable(false);
                addusdialog.pack();
                buttaddf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        int indexf = newlist.getSelectedIndex();
                        if (indexf >= 0) {
                            User o = (User) newlist.getModel().getElementAt(indexf);
                            currentUser.addFriend(o);
                            tempmodel.remove(indexf);
                            lstFriends.updateUI();
                            if (tempmodel.isEmpty()) {
                                JOptionPane.showMessageDialog(addusdialog, "You cannot add more friends!", "Warning",
                                        JOptionPane.INFORMATION_MESSAGE);
                                addusdialog.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(addusdialog, "Choose a Friend first!", "Warning",
                                    JOptionPane.INFORMATION_MESSAGE);
                            setDefaultCloseOperation(addusdialog.DISPOSE_ON_CLOSE);
                        }
                    }
                });
                buttclose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addusdialog.dispose();
                    }
                });
            }
            setDefaultCloseOperation(addusdialog.DISPOSE_ON_CLOSE);
        } else {
            JOptionPane.showMessageDialog(userPanel, "Choose a User First!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public class NewUserAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NewUserDialog addnewuser = new NewUserDialog(SocialNetworkWindow.this, rootPaneCheckingEnabled);
            addnewuser.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
