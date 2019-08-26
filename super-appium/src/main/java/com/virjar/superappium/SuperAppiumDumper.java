package com.virjar.superappium;

import android.util.Log;
import android.util.Xml;

import com.virjar.superappium.util.Constants;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

public class SuperAppiumDumper {
    public static String dumpToString(ViewModel viewModel) {
        try {
            XmlSerializer serializer = Xml.newSerializer();
            StringWriter stringWriter = new StringWriter();
            serializer.setOutput(stringWriter);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "hierarchy");
            serializer.attribute("", "comment", "dumped by super-appium, notice this not compatible with uiautomator");
            dumpNodeRec(viewModel, serializer);
            serializer.endTag("", "hierarchy");
            serializer.endDocument();
            return stringWriter.toString();
        } catch (IOException e) {
            Log.e(Constants.TAG, "failed to dump window to file", e);
        }
        return null;
    }

    private static void dumpNodeRec(ViewModel node, XmlSerializer serializer) throws IOException {
        serializer.startTag("", "node");
        for (String attrKey : node.attributeKeys()) {
            Object value = node.attribute(attrKey);
            if (value == null) {
                continue;
            }
            serializer.attribute("", attrKey, String.valueOf(value));
        }

        int count = node.childCount();
        for (int i = 0; i < count; i++) {
            ViewModel child = node.childAt(i);
            if (child != null) {
                dumpNodeRec(child, serializer);
            } else {
                Log.i(Constants.TAG, String.format("Null child %d/%d, parent: %s",
                        i, count, node.toString()));
            }
        }
        serializer.endTag("", "node");
    }
}
