package com.theoryinpractise.halbuilder.xml;

import com.theoryinpractise.halbuilder.Reader;
import com.theoryinpractise.halbuilder.resources.MutableResource;
import com.theoryinpractise.halbuilder.ReadableResource;
import com.theoryinpractise.halbuilder.Resource;
import com.theoryinpractise.halbuilder.ResourceException;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class XmlReader implements Reader {
    public ReadableResource read(String source) {
        try {
            Document d = new SAXBuilder().build(new StringReader(source));
            Element root = d.getRootElement();
            return readResource(root).asImmutableResource();
        } catch (JDOMException e) {
            throw new ResourceException(e);
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    private Resource readResource(Element root) {
        MutableResource resource = new MutableResource(null, "/");

        resource.withHref(root.getAttributeValue("href"));
        readNamespaces(resource, root);
        readLinks(resource, root);
        readProperties(resource, root);
        readResources(resource, root);

        return resource;
    }

    private void readNamespaces(Resource resource, Element element) {
        List<Namespace> namespaces = element.getAdditionalNamespaces();
        for (Namespace ns : namespaces) {
            resource.withNamespace(ns.getPrefix(), ns.getURI());
        }
    }

    private void readLinks(Resource resource, Element element) {

        List<Element> links = element.getChildren("link");
        for (Element link : links) {
            resource.withLink(link.getAttributeValue("rel"), link.getAttributeValue("href"));
        }

    }

    private void readProperties(Resource resource, Element element) {
        List<Element> properties = element.getChildren();
        for (Element property : properties) {
            if (!property.getName().matches("(link|resource)")) {
                resource.withProperty(property.getName(), property.getValue());
            }
        }
    }

    private void readResources(Resource halResource, Element element) {
        List<Element> resources = element.getChildren("resource");
        for (Element resource : resources) {
            String rel = resource.getAttributeValue("rel");
            ReadableResource subResource = readResource(resource);
            halResource.withSubresource(rel, subResource);
        }
    }
}
