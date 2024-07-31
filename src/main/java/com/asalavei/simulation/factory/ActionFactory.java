package com.asalavei.simulation.factory;

import com.asalavei.simulation.actions.*;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private static final List<Action> turnActions = new ArrayList<>();

    static {
        turnActions.add(new MoveCreatures());
        turnActions.add(new ResourceSpawn());
    }

    private ActionFactory() {
    }

    public static Action getInitAction() {
        return new InitEntitiesSpawn();
    }

    public static List<Action> getTurnActions() {
        return turnActions;
    }
}
