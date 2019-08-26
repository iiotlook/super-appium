package com.virjar.superappium;

import android.util.Log;
import android.util.Xml;

import com.virjar.superappium.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

public class SuperAppiumDumper {
    public static String dumpToXml(ViewModel viewModel) {
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
        String tag = String.valueOf(node.attribute(Constants.className));
        serializer.startTag("", tag);
        for (String attrKey : node.attributeKeys()) {
            if (attrKey.equals(Constants.className)) {
                continue;
            }
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
        serializer.endTag("", tag);
    }

    public static String dumpToJson(ViewModel viewModel) {
        JSONObject jsonObject = new JSONObject();
        dumpNodeRec(viewModel, jsonObject);
        return jsonObject.toString();
    }

    private static void dumpNodeRec(ViewModel node, JSONObject container) {
        for (String attrKey : node.attributeKeys()) {
            Object value = node.attribute(attrKey);
            if (value == null) {
                continue;
            }
            try {
                container.putOpt(attrKey, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        int count = node.childCount();
        if (count <= 0) {
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < count; i++) {
            ViewModel child = node.childAt(i);
            if (child == null) {
                jsonArray.put((Object) null);
            } else {
                JSONObject childContainer = new JSONObject();
                dumpNodeRec(child, childContainer);
                jsonArray.put(childContainer);
            }
        }
        try {
            container.put("children",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
