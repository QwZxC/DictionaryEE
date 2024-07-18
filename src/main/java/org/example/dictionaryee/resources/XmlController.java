package org.example.dictionaryee.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
