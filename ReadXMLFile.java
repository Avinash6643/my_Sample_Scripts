import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {
    public static void main(String[] args) {
        String location = "<add location>";
        try {
            //  for two or more files uncomment and add the files based on time <New to Old>
            List<File> files = new ArrayList<>();
	    //creating a constructor of file class and parsing an XML file
            File file1 = new File(location + "file1.xml");
            files.add(file1);
            File file2 = new File(location + "file2.xml");
            files.add(file2);
            File file3 = new File(location + "file3.xml");
            files.add(file3);
            File file4 = new File(location + "file4.xml");
            files.add(file4);
            File file5 = new File(location + "file5.xml");
            files.add(file5);

            List<String> list = new ArrayList<>();
            StringBuilder line = new StringBuilder();

            files.forEach(file -> {
				//an instance of factory that gives a document builder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				//an instance of builder to parse the specified xml file
                DocumentBuilder db = null;
                try {
                    db = dbf.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                }
                Document doc = null;
                try {
                    doc = db.parse(file);
                } catch (SAXException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                doc.getDocumentElement().normalize();

                //price

//				NodeList nodeList = doc.getElementsByTagName("PriceChangeQualification");
//
//				for (int itr = 0; itr < nodeList.getLength(); itr++)
//				{
//					Node node = nodeList.item(itr);
//					if (node.getNodeType() == Node.ELEMENT_NODE)
//					{
//						Element eElement = (Element) node;
//						if(!list.contains(eElement.getElementsByTagName("ItemID").item(0).getTextContent()))
//						{
//							list.add(eElement.getElementsByTagName("ItemID").item(0).getTextContent());
//							line.append(eElement.getElementsByTagName("ItemID").item(0).getTextContent()).append(",")
//									.append(eElement.getElementsByTagName("PriceInclTax").item(0).getTextContent()).append(",")
//									.append(eElement.getElementsByTagName("PriceExclTax").item(0).getTextContent()).append(",")
//									.append(file.getName()).append("\n");
//						}
//					}
//				}

                // inv

                NodeList nodeList = doc.getElementsByTagName("Item");

                for (int itr = 0; itr < nodeList.getLength(); itr++) {
                    Node node = nodeList.item(itr);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        if (!list.contains(eElement.getElementsByTagName("ItemID").item(0).getTextContent())) {
                            list.add(eElement.getElementsByTagName("ItemID").item(0).getTextContent());
                            line.append(eElement.getElementsByTagName("ItemID").item(0).getTextContent()).append(",").append(eElement.getElementsByTagName("InvValue").item(0).getTextContent()).append(",").append(file.getName()).append("\n");
                        }
                    }
                }

            });

            File csvFile = new File(location + "inv1.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            fileWriter.write(line.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
