package com.virjar.superappium.xpath.parser.expression.token.handler;

import com.virjar.superappium.xpath.parser.expression.SyntaxNode;
import com.virjar.superappium.xpath.parser.expression.token.Token;
import com.virjar.superappium.xpath.parser.expression.token.TokenHandler;

public class AttributeHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) {
        return element -> element.attribute(tokenStr);
    }

    @Override
    public String typeName() {
        return Token.ATTRIBUTE_ACTION;
    }
}
