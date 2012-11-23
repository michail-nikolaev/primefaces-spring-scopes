package org.nkey.primefaces.scopes.test.spring.scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;
import java.util.Map;

/**
 * @author m.nikolaev Date: 21.11.12 Time: 0:37
 */
public class ViewScope implements Scope {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewScope.class);

    @Override
    public Object get(String name, ObjectFactory objectFactory) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (viewMap) {
            if (viewMap.containsKey(name)) {
                return viewMap.get(name);
            } else {
                LOGGER.debug("Creating bean {}", name);
                Object object = objectFactory.getObject();
                viewMap.put(name, object);

                return object;
            }
        }
    }

    @Override
    public Object remove(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(final String name, final Runnable callback) {
        LOGGER.debug("registerDestructionCallback for bean {}", name);
        final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        ViewMapListener listener = new ViewMapListener() {
            @Override
            public void processEvent(SystemEvent event) throws AbortProcessingException {
                LOGGER.debug("destroyed view bean {}", name);
                callback.run();
                viewRoot.unsubscribeFromViewEvent(PreDestroyViewMapEvent.class, this);
            }

            @Override
            public boolean isListenerForSource(Object source) {
                return source == viewRoot;
            }
        };

        viewRoot.subscribeToViewEvent(PreDestroyViewMapEvent.class, listener);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
}
