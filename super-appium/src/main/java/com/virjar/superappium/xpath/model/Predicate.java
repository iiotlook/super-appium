package com.virjar.superappium.xpath.model;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.util.Lists;
import com.virjar.superappium.xpath.function.FunctionEnv;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import lombok.Getter;

public class Predicate {
    private SyntaxNode syntaxNode;
    @Getter
    private String predicateStr;


    public Predicate(String predicateStr, SyntaxNode syntaxNode) {
        this.predicateStr = predicateStr;
        this.syntaxNode = syntaxNode;
    }

    boolean isValid(ViewImage element) {
        return (Boolean) FunctionEnv.getFilterFunction("sipSoupPredictJudge").call(element,
                Lists.newArrayList(syntaxNode));
    }
}
