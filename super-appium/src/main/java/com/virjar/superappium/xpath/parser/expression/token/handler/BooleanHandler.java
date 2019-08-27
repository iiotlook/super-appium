package com.virjar.superappium.xpath.parser.expression.token.handler;

import com.virjar.superappium.util.BooleanUtils;
import com.virjar.superappium.util.Lists;
import com.virjar.superappium.xpath.exception.XpathSyntaxErrorException;
import com.virjar.superappium.xpath.function.FunctionEnv;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;
import com.virjar.superappium.xpath.parser.expression.node.FunctionNode;
import com.virjar.superappium.xpath.parser.expression.token.Token;
import com.virjar.superappium.xpath.parser.expression.token.TokenHandler;

public class BooleanHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) throws XpathSyntaxErrorException {
        return new FunctionNode(FunctionEnv.getFilterFunction(BooleanUtils.toBoolean(tokenStr) ? "true" : "false"),
                Lists.<SyntaxNode>newLinkedList());
    }

    @Override
    public String typeName() {
        return Token.BOOLEAN;
    }
}
