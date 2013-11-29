package okisum.example.xmlRest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLHelper extends DefaultHandler {
	/** 
	 * The URL to be parsed
	 */
	private String URL_MAIN = "http://192.168.196.56/test/public/api/users.xml";
	String TAG = "XMLHelper";
	
	Boolean currTag = false;
	String currTagVal = "";
	public PostValue post = null;
	public ArrayList<PostValue> posts = new ArrayList<PostValue>();
	
	/**
	 * Method to read XML from {@link XMLHelper#URL_MAIN}
	 * */
	public void get() {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser mSaxParser = factory.newSAXParser();
			XMLReader mXmlReader = mSaxParser.getXMLReader();
			mXmlReader.setContentHandler(this);
			InputStream mInputStream = new URL(URL_MAIN).openStream();
			mXmlReader.parse(new InputSource(mInputStream));
		} catch(SAXException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		}
	}

	// This method receives notification character data inside an element
	// e.g. <post_title>Add EditText Programmatically - Android</post_title>
	// It will be called to read "Add EditText Programmatically - Android"
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currTag) {
			currTagVal = currTagVal + new String(ch, start, length);
			currTag = false;
		}
	}

	// Receives notification of end of element
	// e.g. <post_title>Add EditText Programmatically - Android</post_title>
	// It will be called when </post_title> is encountered
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currTag = false;
		
	
		
		if(localName.equalsIgnoreCase("user")){
			posts.add(new PostValue(post.getTitle(), post.getPass(), post.getId()));
		}
		else if(localName.equalsIgnoreCase("id"))
			post.setId(currTagVal);
		else if(localName.equalsIgnoreCase("username"))
			post.setTitle(currTagVal);
		
		else if(localName.equalsIgnoreCase("password"))
			post.setPass(currTagVal);
	}

	// Receives notification of start of an element
	// e.g. <post_title>Add EditText Programmatically - Android</post_title>
	// It will be called when <post_title> is encountered
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		Log.i(TAG, "TAG: " + localName);
		
		if(localName.equalsIgnoreCase("user")){
			post = new PostValue("", "","");
		}
		currTag = true;
		currTagVal = "";
		// Whenever <post> element is encountered it will create new object of PostValue
	}
}
