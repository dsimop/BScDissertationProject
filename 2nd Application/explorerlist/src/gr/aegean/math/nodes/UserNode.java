package gr.aegean.math.nodes;

import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import gr.aegean.math.sn.User;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

public class UserNode extends BeanNode<User> {

    public UserNode(User user) throws IntrospectionException {
        super(user, Children.LEAF, Lookups.singleton(user));
        setName(user.getUsername());
    }
}
