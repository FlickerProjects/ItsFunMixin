package io.github.itsflicker.itsfunmixin.fakes;

import io.github.itsflicker.itsfunmixin.helpers.EntityPlayerActionPack;

public interface ServerPlayerEntityInterface
{
    EntityPlayerActionPack getActionPack();
    void invalidateEntityObjectReference();
    boolean isInvalidEntityObject();
    String getLanguage();
}
