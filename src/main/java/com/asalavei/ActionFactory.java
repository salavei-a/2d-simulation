package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.actions.InitAction;
import com.asalavei.actions.MoveCreature;
import com.asalavei.actions.SpawnResource;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private static final List<Action> initActions = new ArrayList<>();
    private static final List<Action> turnActions = new ArrayList<>();

    static {
        initActions.add(new InitAction());
        turnActions.add(new MoveCreature());
        turnActions.add(new SpawnResource());
    }

    private ActionFactory() {
    }

    public static List<Action> getInitActions() {
        return initActions;
    }

    public static List<Action> getTurnActions() {
        return turnActions;
    }
}
