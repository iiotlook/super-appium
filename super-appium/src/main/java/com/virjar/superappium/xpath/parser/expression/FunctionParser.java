package com.virjar.superappium.xpath.parser.expression;


import com.virjar.superappium.util.Lists;
import com.virjar.superappium.util.StringUtils;
import com.virjar.superappium.xpath.exception.NoSuchFunctionException;
import com.virjar.superappium.xpath.exception.XpathSyntaxErrorException;
import com.virjar.superappium.xpath.function.FunctionEnv;
import com.virjar.superappium.xpath.function.filter.FilterFunction;
import com.virjar.superappium.xpath.parser.TokenQueue;
import com.virjar.superappium.xpath.parser.expression.node.FunctionNode;
import com.virjar.superappium.xpath.parser.expression.token.Token;
import com.virjar.superappium.xpath.parser.expression.token.TokenAnalysisRegistry;
import com.virjar.superappium.xpath.parser.expression.token.TokenConsumer;

import java.util.List;

/**
 * Created by virjar on 17/6/11. 对于函数,解析函数名字,参数列表,决定参数类型
 *
 * @author virjar
 * @since 0.0.1
 */
public class FunctionParser {

    private static final List<String> paramExcludeTypes = Lists.newArrayList(Token.OPERATOR);
    private TokenQueue tokenQueue;

    public FunctionParser(TokenQueue tokenQueue) {
        this.tokenQueue = tokenQueue;
    }

    public FunctionNode parse() throws XpathSyntaxErrorException {
        tokenQueue.consumeWhitespace();
        String functionName = tokenQueue.consumeTo("(");
        String params = tokenQueue.chompBalanced('(', ')');
        FilterFunction filterFunction = FunctionEnv.getFilterFunction(functionName);
        if (filterFunction == null) {
            throw new NoSuchFunctionException(0, "not such function:" + functionName);
        }

        List<SyntaxNode> paramList = Lists.newLinkedList();

        TokenQueue paramTokenQueue = new TokenQueue(StringUtils.trimToEmpty(params));
        while ((paramTokenQueue.consumeWhitespace() && !paramTokenQueue.consumeWhitespace())
                || !paramTokenQueue.isEmpty()) {

            boolean hint = false;
            for (TokenConsumer tokenConsumer : TokenAnalysisRegistry.consumerIterable()) {
                if (excludeForParam(tokenConsumer.tokenType())) {
                    continue;
                }

                String consume = tokenConsumer.consume(paramTokenQueue);
                if (consume == null) {
                    continue;
                }
                hint = true;
                paramList.add(TokenAnalysisRegistry.findHandler(tokenConsumer.tokenType()).parseToken(consume));
                break;
            }

            if (!hint) {
                throw new XpathSyntaxErrorException(paramTokenQueue.nowPosition(), "can not parse param list: "
                        + paramTokenQueue.getQueue() + "  ,for token " + paramTokenQueue.remainder());
            }

            paramTokenQueue.consumeWhitespace();
            if (!paramTokenQueue.isEmpty()) {
                if (paramTokenQueue.peek() != ',') {
                    throw new XpathSyntaxErrorException(paramTokenQueue.nowPosition(), "can not parse param list: \""
                            + paramTokenQueue.getQueue() + "\"  ,for token " + paramTokenQueue.remainder());
                }
                paramTokenQueue.advance();
            }

        }

        return new FunctionNode(filterFunction, paramList);
    }

    private boolean excludeForParam(String tokenType) {
        return paramExcludeTypes.contains(tokenType);
    }
}
