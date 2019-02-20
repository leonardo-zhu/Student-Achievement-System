package dao;

import entity.User;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.Translation;
import utils.XmlUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public boolean checkUser(User user) {
        Element root = XmlUtil.getDocument("user.xml").getRootElement();
        List<Element> childElements = root.elements();
        for (Element child :
                childElements) {

            String user_name = child.elementText("user_name");
            String password = child.elementText("password");

            if (user_name.equals(user.getUser_name()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String user_name) {
        Element root = XmlUtil.getDocument("user.xml").getRootElement();
        List<Element> childElements = root.elements();
        for (Element child :
                childElements) {
            if (user_name.equals(child.elementText("user_name"))) {
                return true;
            }
        }
        return false;
    }

    public void save(User user) {
        Document document = XmlUtil.getDocument("user.xml");
        Translation.getBeanToXml(document,user);
        XmlUtil.writeXML(document,"user.xml");
    }
}
