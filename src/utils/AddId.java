package utils;

import org.dom4j.Document;
import org.dom4j.Element;

import java.text.NumberFormat;
import java.util.List;

public class AddId {
    public static String formatId() {
        Document doc = XmlUtil.getDocument("user.xml");
        Element root = doc.getRootElement();
        List<Element> childElements = root.elements();

        String id = childElements.get(childElements.size() - 1).attributeValue("id");

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(3);
        numberFormat.setGroupingUsed(false);

        id = numberFormat.format(Integer.valueOf(id) + 1);

        return id;
    }

    public static String formatId(int id) {

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(3);
        numberFormat.setGroupingUsed(false);

        String s = numberFormat.format(id);

        return s;
    }

}
