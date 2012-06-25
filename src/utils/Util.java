package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Util {

	
	/**
	 * Devuelve el nodo principal de un documento xml.
	 * @param path ruta pra el documento xml.
	 * @return Nodo padre del documento.
	 * @throws Exception
	 */
	public static Node getRootNodeFromXml(String path)throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
		Document documento = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
		    documento = builder.parse( new File(path) );
		} catch (ParserConfigurationException e) {
			throw new Exception("Fallo en el parseo del fichero "+ path);
		} catch (SAXException e) {
			throw new Exception("Fallo en el parseo del fichero "+ path);
		} catch (IOException e) {
			throw new Exception("Fallo en el fichero "+ path);
		}
	
		if(documento!=null){
		// Nos devuelve el nodo raíz del documento XML.
			Node rootNode = documento.getFirstChild();
			return rootNode;
	}
		return null;
}
	
	
	
	 public static void saveXml(Document doc, String path) {
		// Serialisation through Tranform.
		 DOMSource domSource = new DOMSource(doc);
		 StreamResult streamResult = new StreamResult(path);
		 TransformerFactory tf = TransformerFactory.newInstance();
		 Transformer serializer;
		try {
			serializer = tf.newTransformer();
			serializer.transform(domSource, streamResult);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 }
}
