/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aegean.deleteuser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "gr.aegean.deleteuser.DeleteUserActionListener"
)
@ActionRegistration(
        iconBase = "gr/aegean/deleteuser/delete.png",
        displayName = "#CTL_DeleteUserActionListener"
)
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 100),
    @ActionReference(path = "Toolbars/File", position = 300)
})
@Messages("CTL_DeleteUserActionListener=Delete User")
public final class DeleteUserActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
