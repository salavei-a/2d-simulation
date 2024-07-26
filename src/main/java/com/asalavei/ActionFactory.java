package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.actions.InitAction;
import com.asalavei.actions.MoveCreature;
import com.asalavei.actions.SpawnResource;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private static final List<Action> turnActions = new ArrayList<>();

    static {
        turnActions.add(new MoveCreature());
        turnActions.add(new SpawnResource());
    }

    private ActionFactory() {
    }

    public static Action getInitAction() {
        return new InitAction();
    }

    public static List<Action> getTurnActions() {
        return turnActions;
    }
}
