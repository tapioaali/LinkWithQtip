package com.vaadin.linkwithqtip;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@Theme("reindeer")
@SuppressWarnings("serial")
public class LinkWithQTipTestUI extends UI {

    private VerticalLayout layout = new VerticalLayout();
    private LinkWithQTip link = new LinkWithQTip("https://www.vaadin.com/",
            "vaadin.com");;
    private Label qTipOpenCountLabel = new Label();
    private int qTipOpenCount = 0;
    private Button button = new Button("Change link text to \"Hover me\"",
            new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    link.setText("Hover me!");
                }
            });
    private Button toggleButton = new Button("Toggle QTip",
            new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    link.toggleQTip();
                }
            });

    @Override
    protected void init(VaadinRequest request) {
        layout.setMargin(true);
        setContent(layout);
        layout.addComponent(link);
        layout.addComponent(button);
        layout.addComponent(toggleButton);
        link.addQTipOpenedListener(new LinkQTipOpenedListener() {
            @Override
            public void qTipOpened() {
                qTipOpenCount++;
                qTipOpenCountLabel.setValue("QTip opened " + qTipOpenCount
                        + " times");
                if (qTipOpenCountLabel.getParent() == null) {
                    layout.addComponent(qTipOpenCountLabel);
                }
            }
        });
    }
}