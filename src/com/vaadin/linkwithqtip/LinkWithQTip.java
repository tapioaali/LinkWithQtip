package com.vaadin.linkwithqtip;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.linkwithqtip.client.LinkWithQTipState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

@JavaScript({ "client/jquery-1.10.1.min.js", "client/jquery.linkqtip.js",
        "client/linkwithqtip_connector.js" })
@StyleSheet("vaadin://jquery.linkqtip-styles.css")
public class LinkWithQTip extends AbstractJavaScriptComponent {

    private ArrayList<LinkQTipOpenedListener> listeners = new ArrayList<LinkQTipOpenedListener>();

    public LinkWithQTip(String url, String text) {
        getState().url = url;
        getState().text = text;
        addFunction("onLinkQTipOpened", new JavaScriptFunction() {
            @Override
            public void call(JSONArray arguments) throws JSONException {
                for (LinkQTipOpenedListener listener : listeners) {
                    listener.qTipOpened();
                }
            }
        });
    }

    @Override
    protected LinkWithQTipState getState() {
        return (LinkWithQTipState) super.getState();
    }

    public void setText(String text) {
        getState().text = text;
    }

    public void addQTipOpenedListener(LinkQTipOpenedListener listener) {
        listeners.add(listener);
    }

    public void toggleQTip() {
        callFunction("toggle");
    }
}
