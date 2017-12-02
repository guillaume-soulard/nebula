package com.nebula.core.formatter.xml;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.formatter.ValueFormatter;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.util.Collection;
import java.util.List;

class XmlFormatter implements Formatter {

    private ValueFormatter valueFormatter;
    private Entity entity;

    XmlFormatter(ValueFormatter valueFormatter) {
        this.valueFormatter = valueFormatter;
    }

    @Override
    public String formatHeader(Entity entity) {
        this.entity = entity;
        return null;
    }

    @Override
    public String formatGeneratedObject(GeneratedObject generatedObject) {
        Element root = new Element(entity.getName());

        addElementsTo(root, generatedObject.getGeneratedProperties());

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        return  xmlOutputter.outputString(root);
    }

    private void addElementsTo(Element elementToAppend, List<GeneratedProperty> generatedProperties) {
        for (GeneratedProperty generatedProperty : generatedProperties) {
            Element subElement = new Element(generatedProperty.getPropertyName());
            Object value = generatedProperty.getPropertyValue().getObject();
            if (value != null) {
                if (isArray(generatedProperty.getPropertyValue())) {
                    Collection<GeneratedObject> list = (Collection<GeneratedObject>) value;
                    for (GeneratedObject generatedObject : list) {
                        Element item = new Element("item");
                        addElementsTo(item, generatedObject.getGeneratedProperties());
                        subElement.addContent(item);
                    }
                } else {
                    subElement.setText(valueFormatter.formatValue(value));
                }
            } else {
                addElementsTo(subElement, generatedProperty.getPropertyValue().getGeneratedProperties());
            }
            elementToAppend.addContent(subElement);
        }
    }

    private boolean isArray(GeneratedObject generatedObject) {
        return generatedObject.getObject() != null
                && generatedObject.getObject() instanceof Collection;
    }

    @Override
    public String formatFooter(Entity entity) {
        return null;
    }
}
