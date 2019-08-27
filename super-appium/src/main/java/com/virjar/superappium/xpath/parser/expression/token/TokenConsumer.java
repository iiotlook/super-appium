package com.virjar.superappium.xpath.parser.expression.token;

import com.virjar.superappium.xpath.parser.TokenQueue;

public interface TokenConsumer {
    String consume(TokenQueue tokenQueue);

    int order();

    String tokenType();
}
