package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.actions.InitAction;
import com.asalavei.actions.TurnAction;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("initAction", new InitAction());
        actions.put("turnAction", new TurnAction());
    }

    private ActionFactory() {
    }

    public static Map<String, Action> getAction() {
        return actions;
    }
}