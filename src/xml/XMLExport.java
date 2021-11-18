package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.LearningCard;
import models.LearningTheme;


public class XMLExport {


	
	public static void exportTheme(String filename, LearningTheme theme) {
		try {

			Element rootElement = new Element("theme");
			Document doc = new Document(rootElement);

			rootElement.setAttribute("id", Integer.toString(theme.getId()));
			rootElement.setAttribute("title", theme.getTitle());
			
			for (LearningCard card : theme.getAllCards()) {
				rootElement.addContent(export(card)); 
			}

			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream out = new FileOutputStream(filename);
			outputter.output(doc, out);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Element export(LearningCard card) {
		Element element = new Element("card");
			
		element.setAttribute("id", Integer.toString(card.getId()));
		
		Element question = new Element("question");
		question.setText(card.getAsk());
		element.addContent(question);

		Element qDesc = new Element("question_description");
		qDesc.setText(card.getAskDesc());
		element.addContent(qDesc);
		
		Element answer = new Element("answer");
		answer.setText(card.getAnswer());
		element.addContent(answer);
		
	    return element;
	}
	

	public static LearningTheme importTheme(String filename) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(filename));
			
			Element rootElement = doc.getRootElement();
			String title = rootElement.getAttributeValue("title");
			int id = Integer.parseInt(rootElement.getAttributeValue("id"));
		
			List<LearningCard> cards = new ArrayList<LearningCard>();
			for (Element cardElement: rootElement.getChildren()) {
				LearningCard card = importCard(cardElement);
				card.setId(0);
				cards.add(card);
			}
			
			LearningTheme theme = new LearningTheme(0/*id*/, title, cards);
			return theme;
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}
	
	public static LearningCard importCard(Element cardElement) {
			
		int cardId = Integer.parseInt(cardElement.getAttributeValue("id"));
		
		String  question = cardElement.getChildText("question");
		String  questionDescription = cardElement.getChildText("question_description");
		String  answer = cardElement.getChildText("answer");
	
	    return new LearningCard(cardId, question, answer, questionDescription);
	}

}
