package manager;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import rcpdetection.Activator;
import utils.Util;

public class VideoManager {
	
	/**
	 * Ruta del fichero de configuración.
	 */
	private static final String VIDEO_CONFIG_FILE = "xml\\video.xml";
	
//	myCapture.absDiff();                           //  Creates a difference image
//	myCapture.convert(OpenCV.GRAY);                //  Converts to greyscale
//	myCapture.blur(OpenCV.BLUR, 3);
//	myCapture.brightness(value);

//  myCapture.threshold(80);    // set black & white threshold 
	
	
	/**
	 * Brillo, valor entre -128 y 128.
	 */
	private int brightness;
	
	/**
	 * Umbral, valor entre 0 y 255.
	 */
	private int threshold;
	
	/**
	 * Indica si se va a difuminar la imagen. Si es true, se difumina.
	 * Si es false, no se difumina.
	 */
	private boolean blur;
	
	/**
	 * Indica si se convierte a escala de grises o no.
	 */
	private boolean convertGray;
	
	
	/**
	 * Constructor de la clase VideoManager 
	 */
	public VideoManager(){
		this.brightness = 0;
		this.threshold = 0;
		this.blur = true;
		this.convertGray = true;
		
		this.initialize();
	}



	/**
	 * Inicializa los atributos a partir del fichero xml de configuración
	 * de vídeo.
	 */
	private void initialize() {
		try{
			loadDataFromXml();
		}catch(Exception e){
			
		}
		
	}

	/**
	 * Carga los datos almacenados en el xml de configuración.
	 * @throws Exception 
	 */
	private void loadDataFromXml() throws Exception{
		Node rootNode = Util.getRootNodeFromXml(Activator.getPlugInPath()+File.separator+VIDEO_CONFIG_FILE);
		if(rootNode!=null){
			loadVideoSettings(rootNode);
		}
	}




	private void loadVideoSettings(Node rootNode) {
		if(rootNode !=null){
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = "/video/threshold/text()";
			try {
				this.threshold  = Integer.parseInt(((String)xpath.evaluate(expression, rootNode, XPathConstants.STRING)));
			} catch (XPathExpressionException e) {
				//NO se propaga, tenemos un valor por defecto
			}
			
			expression = "/video/brightness/text()";
			try {
				this.brightness  = Integer.parseInt(((String)xpath.evaluate(expression, rootNode, XPathConstants.STRING)));
			} catch (XPathExpressionException e) {
				//No se propaga, tenemos un valor por defecto.
			}
			
			expression = "/video/blur/text()";
			try {
				this.blur  = ((Boolean)xpath.evaluate(expression, rootNode, XPathConstants.BOOLEAN)).booleanValue();
			} catch (XPathExpressionException e) {
				//No se propaga, tenemos un valor por defecto.
			}
			
			expression = "/video/convertGray/text()";
			try {
				this.convertGray  = ((Boolean)xpath.evaluate(expression, rootNode, XPathConstants.BOOLEAN)).booleanValue();
			} catch (XPathExpressionException e) {
				//No se propaga, tenemos un valor por defecto.
			}
		}
		
	}


	/**
	 * Devuelve el valor para el brillo.
	 * @return parámetro de brillo.
	 */
	public int getBrightness() {
		return brightness;
	}


	/**
	 * Establece el valor para el brillo. Su rango es (-128,128)
	 * @param brightness valor para el parámetro brillo.
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}


	/**
	 * Devuelve el valor para el parámetro umbral.
	 * @return  parámetro Umbral.
	 */
	public int getThreshold() {
		return threshold;
	}


	/**
	 * Establece el umbral. Su rango es (0,255).
	 * @param threshold umbral.
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}


	/**
	 * Devuelve si está activado el parámetro de difuminación.
	 * @return true, está activado. false, no está activado.
	 */
	public boolean isBlur() {
		return blur;
	}


	/**
	 * Establece la propiedad de difuminación.
	 * @param blur true para activar, false para desactivar.
	 */
	public void setBlur(boolean blur) {
		this.blur = blur;
	}


	/**
	 * Devuelve el estado del escalado de grises.
	 * @return true, está activado. false, está desactivado.
	 */
	public boolean isConvertGray() {
		return convertGray;
	}


	/**
	 * Establece si está activado o no el escalado de grises.
	 * @param convertGray true lo activa. False lo desactiva.
	 */
	public void setConvertGray(boolean convertGray) {
		this.convertGray = convertGray;
	}

	/**
	 * Guarda los parametros en el xml.
	 */
	public void saveSettings(){
		try {
			Document xmldoc = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			// Document.
			xmldoc = impl.createDocument(null,null, null);
			// Root element.
			Element root = xmldoc.createElement("video");
			xmldoc.appendChild(root);
			
			Element ebrightness = xmldoc.createElement("brightness");
			ebrightness.setTextContent(this.brightness+"");
			root.appendChild(ebrightness);
			
			Element ethreshold = xmldoc.createElement("threshold");
			ethreshold.setTextContent(this.threshold+"");
			root.appendChild(ethreshold);
			
			Element eblur = xmldoc.createElement("blur");
			eblur.setTextContent(Boolean.toString(this.blur));
			root.appendChild(eblur);
			Element econvertGray = xmldoc.createElement("convertGray");
			econvertGray.setTextContent(Boolean.toString(this.convertGray));
			root.appendChild(econvertGray);
			
			Util.saveXml(xmldoc, Activator.getPlugInPath()+File.separator+VIDEO_CONFIG_FILE);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
