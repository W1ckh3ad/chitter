package de.fhdw.chitter.utils;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.fhdw.chitter.models.userconfig.*;

public class UserConfigParser {

    public static int getMaxUserCount() {
        return read().getMaxUsers();
    }

    public static ArrayList<CharacterVal> getPasswordRules() {
        return read().getRules();
    }

    private static UserConfig read() {
        var docBuildingFactory = DocumentBuilderFactory.newInstance();
        Integer maxUsers = null;
        var rules = new ArrayList<CharacterVal>();
        try {
            var docBuilder = docBuildingFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("userConfig.xml");
            var xmlMaxUserNode = doc.getElementsByTagName("maxUser").item(0);
            if (xmlMaxUserNode.getNodeType() == Node.ELEMENT_NODE) {
                var xmlMaxUserElement = (Element) xmlMaxUserNode;
                maxUsers = Integer.parseInt(xmlMaxUserElement.getAttribute("value"));
            }
            var passwordRulesList = doc.getElementsByTagName("passwordRules").item(0).getChildNodes();
            for (int i = 0; i < passwordRulesList.getLength(); i++) {
                var rule = passwordRulesList.item(i);

                Integer min = null, max = null;
                String chars = null, ruleName = null;
                if (rule.getNodeType() == Node.ELEMENT_NODE) {
                    var ruleElement = (Element) rule;
                    ruleName = ruleElement.getTagName();
                }
                var ruleChildNodes = rule.getChildNodes();
                for (int j = 0; j < ruleChildNodes.getLength(); j++) {
                    var xmlChildNode = ruleChildNodes.item(j);
                    if (xmlChildNode.getNodeType() == Node.ELEMENT_NODE) {
                        var xmlChild = (Element) xmlChildNode;
                        switch (xmlChild.getTagName()) {
                        case "min":
                            min = Integer.parseInt(xmlChild.getAttribute("value"));
                            break;
                        case "max":
                            max = Integer.parseInt(xmlChild.getAttribute("value"));
                            break;
                        case "characters":
                            chars = xmlChild.getAttribute("value");
                            break;

                        default:
                            break;
                        }
                    }
                }
                if (min != null || max != null || chars != null) {
                    rules.add(new CharacterVal(ruleName, min, max, chars));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserConfig(maxUsers, rules);
    }

}
