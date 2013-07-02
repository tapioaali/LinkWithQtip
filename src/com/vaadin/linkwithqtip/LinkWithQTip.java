package com.vaadin.linkwithqtip;

import java.util.ArrayList;

import com.vaadin.annotations.JavaScript;
import com.vaadin.linkwithqtip.client.LinkQTipOpenedRpc;
import com.vaadin.linkwithqtip.client.LinkWithQTipState;
import com.vaadin.linkwithqtip.client.ToggleQTipRpc;
import com.vaadin.linkwithqtip.events.LinkQTipOpenedListener;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({ "client/jquery-1.10.1.min.js", "client/jquery.linkqtip.js",
        "client/linkwithqtip_connector.js" })
public class LinkWithQTip extends AbstractJavaScriptComponent {

    private ArrayList<LinkQTipOpenedListener> listeners = new ArrayList<LinkQTipOpenedListener>();

    public LinkWithQTip(String url, String text) {
        getState().url = url;
        getState().text = text;
        registerRpc(new LinkQTipOpenedRpc() {
            @Override
            public void onLinkQTipOpened() {
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
        getRpcProxy(ToggleQTipRpc.class).toggle();
    }
}
