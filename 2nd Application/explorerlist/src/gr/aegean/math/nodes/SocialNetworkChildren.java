package gr.aegean.math.nodes;

import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import gr.aegean.math.sn.User;
import java.beans.IntrospectionException;
import org.openide.util.Exceptions;

public class SocialNetworkChildren extends Children.Keys<User> {

//    public SocialNetworkChildren(SocialNetwork sn){
//       refreshList(sn.getUsers());
//    }
    @Override
    protected Node[] createNodes(User user) {
        try {
            return new Node[]{new UserNode(user)};
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        } 
    }

    public void refreshList(List<User> lst) {
        setKeys(lst);
    }
}
