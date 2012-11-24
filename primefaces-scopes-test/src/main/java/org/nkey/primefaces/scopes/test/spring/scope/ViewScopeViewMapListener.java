package org.nkey.primefaces.scopes.test.spring.scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

/**
 * @author Michail Nikolaev ate: 21.11.12 Time: 0:37
 */
public class ViewScopeViewMapListener implements ViewMapListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewScopeViewMapListener.class);
    private String name;
    private Runnable callback;
    private boolean callbackCalled = false;
    private UIViewRoot root;
    private ViewScope viewScope;

    public ViewScopeViewMapListener(UIViewRoot root, String name, Runnable callback, ViewScope viewScope) {
        this.name = name;
        this.callback = callback;
        this.root = root;
        this.viewScope = viewScope;
    }

    @Override
    synchronized public void processEvent(SystemEvent event) throws AbortProcessingException {
        if (event instanceof PostConstructViewMapEvent) {
            LOGGER.debug("Going call callback for bean {}", name);
            doCallback();
            viewScope.clearFromListener(this);
        }
    }

    public void doCallback() {
        LOGGER.debug("Going call callback for bean {}", name);
        if (!callbackCalled) {
            try {
                callback.run();
            } finally {
                callbackCalled = true;
            }
        }
    }

    @Override
    public boolean isListenerForSource(Object source) {
        return (source == root);
    }
}
