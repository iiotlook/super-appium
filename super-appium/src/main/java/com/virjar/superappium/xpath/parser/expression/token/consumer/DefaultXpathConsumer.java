package com.virjar.superappium.xpath.parser.expression.token.consumer;

import com.virjar.superappium.util.StringUtils;
import com.virjar.superappium.xpath.XpathParser;
import com.virjar.superappium.xpath.exception.XpathSyntaxErrorException;
import com.virjar.superappium.xpath.parser.TokenQueue;
import com.virjar.superappium.xpath.parser.expression.token.Token;
import com.virjar.superappium.xpath.parser.expression.token.TokenConsumer;

public class DefaultXpathConsumer implements TokenConsumer {
    @Override
    public String consume(TokenQueue tokenQueue) {
        String s = tokenQueue.tryConsumeTo(" ");
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        try {
            XpathParser.compile(s);
            return tokenQueue.consumeTo(" ");
        } catch (XpathSyntaxErrorException e) {
           // log.debug("exception when compile xpath:{}", s, e);
            // TODO
            // ignore,根据约定,如果发生异常,则忽略本次调用
            return null;
        }
    }

    @Override
    public int order() {
        return 80;
    }

    @Override
    public String tokenType() {
        return Token.XPATH;
    }
}
