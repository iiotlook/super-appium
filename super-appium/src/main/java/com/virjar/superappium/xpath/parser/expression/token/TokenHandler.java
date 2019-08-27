package com.virjar.superappium.xpath.parser.expression.token;

import com.virjar.superappium.xpath.exception.XpathSyntaxErrorException;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

public interface TokenHandler {
    SyntaxNode parseToken(String tokenStr) throws XpathSyntaxErrorException;

    String typeName();
}
