package com.asalavei.simulation.factory;

import com.asalavei.simulation.actions.*;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private static final List<Action> initActions = new ArrayList<>();
    private static final List<Action> turnActions = new ArrayList<>();

    static {
        initActions.add(new InitEntitiesSpawn());
        turnActions.add(new MoveCreatures());
        turnActions.add(new ResourceSpawn());
    }

    private ActionFactory() {
    }

    public static List<Action> getInitAction() {
        return initActions;
    }

    public static List<Action> getTurnActions() {
        return turnActions;
    }
}
