package org.example.dictionaryee.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.SneakyThrows;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;

@Path("xml")
public class XmlController {

    @GET
    @SneakyThrows
    @Produces(MediaType.TEXT_PLAIN)
    public Response getXml(@QueryParam("expression") String expression) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("Words.xml"));

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        return Response.ok(xpath.evaluate(expression, document)).build();
    }
}
