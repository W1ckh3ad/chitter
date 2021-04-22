package de.fhdw.chitter.receivers.interfaces;

import de.fhdw.chitter.models.NewsMessage;

public interface Receiver {
    void receiveMessage(NewsMessage newsMessage);
}
