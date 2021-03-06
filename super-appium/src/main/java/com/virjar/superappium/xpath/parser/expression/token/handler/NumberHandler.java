package com.virjar.superappium.xpath.parser.expression.token.handler;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.util.NumberUtils;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;
import com.virjar.superappium.xpath.parser.expression.token.Token;
import com.virjar.superappium.xpath.parser.expression.token.TokenHandler;

public class NumberHandler implements TokenHandler {
    @Override
    public SyntaxNode parseToken(final String tokenStr) {
        return new SyntaxNode() {
            @Override
            public Object calc(ViewImage element) {
                if (tokenStr.contains(".")) {
                    return NumberUtils.toDouble(tokenStr);
                } else {
                    return NumberUtils.toInt(tokenStr);
                }
            }
        };
    }

    @Override
    public String typeName() {
        return Token.NUMBER;
    }
}
