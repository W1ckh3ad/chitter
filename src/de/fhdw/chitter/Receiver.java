package de.fhdw.chitter;

import de.fhdw.chitter.models.NewsMessage;

public interface Receiver {
    void receiveMessage(NewsMessage msg);
}
