package com.virjar.superappium.xpath.model;

import com.virjar.superappium.ViewModel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class XpathEvaluator {
    public abstract XNodes evaluate(XNodes elements);

    public XpathEvaluator wrap(XpathEvaluator newRule) {
        throw new UnsupportedOperationException();
    }

    public static class AnanyseStartEvaluator extends XpathEvaluator {

        @Override
        public XNodes evaluate(XNodes elements) {
            throw new UnsupportedOperationException();
        }


        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            return newRule;
        }
    }

    public static class ChainEvaluator extends XpathEvaluator {
        // @Getter
        // for xsoup
        private LinkedList<XpathNode> xpathNodeList = new LinkedList<>();

        public ChainEvaluator(LinkedList<XpathNode> xpathNodeList) {
            this.xpathNodeList = xpathNodeList;
        }

        private List<XNode> handleNode(List<XNode> input, final XpathNode xpathNode) {

            // 目前只支持对element元素进行抽取,如果中途抽取到了文本,则会断节
            List<ViewModel> elements = XpathUtil.transformToElement(input);
            List<ViewModel> contextElements;

            // 轴
            AxisFunction axis = xpathNode.getAxis();
            if (axis != null) {
                contextElements = Lists.newLinkedList();
                for (ViewModel element : elements) {
                    ViewModel call = axis.call(element, xpathNode.getAxisParams());
                    if (call != null) {
                        contextElements.addAll(call);
                    }
                }
            } else {
                contextElements = elements;
            }

            // 调用抽取函数
            List<XNode> SIPNodes = xpathNode.getSelectFunction().call(xpathNode.getScopeEm(),
                    new Elements(contextElements), xpathNode.getSelectParams());

            // 谓语过滤
            if (xpathNode.getPredicate() == null) {
                return SIPNodes;
            }

            // 谓语只支持对元素过滤,非元素节点直接被过滤
            return Lists.newLinkedList(Iterables.filter(SIPNodes, new Predicate<XNode>() {
                @Override
                public boolean apply(XNode input) {
                    return xpathNode.getPredicate().isValid(input.getElement());
                }
            }));
        }

        @Override
        public XNodes evaluate(XNodes elements) {
            for (XpathNode xpathNode : xpathNodeList) {// 对xpath语法树上面每个节点进行抽取
                elements = new XNodes(handleNode(elements, xpathNode));
                if (elements.isEmpty()) {// 如果已经为空,终止抽取链
                    return elements;
                }
            }
            return elements;
        }

    }

    public static class OrEvaluator extends XpathEvaluator {
        private List<XpathEvaluator> subEvaluators = new LinkedList<>();

        public OrEvaluator() {
        }

        @Override
        public XNodes evaluate(XNodes elements) {
            Iterator<XpathEvaluator> iterator = subEvaluators.iterator();
            XNodes evaluate = iterator.next().evaluate(elements);
            while (iterator.hasNext()) {
                evaluate.addAll(iterator.next().evaluate(elements));
            }
            return evaluate;
        }


        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            subEvaluators.add(newRule);
            return this;
        }
    }

    public static class AndEvaluator extends XpathEvaluator {

        private List<XpathEvaluator> subEvaluators = new LinkedList<>();

        public AndEvaluator() {
        }

        @Override
        public XNodes evaluate(XNodes elements) {
            Iterator<XpathEvaluator> iterator = subEvaluators.iterator();
            XNodes evaluate = iterator.next().evaluate(elements);
            while (iterator.hasNext()) {
                evaluate.retainAll(iterator.next().evaluate(elements));
            }
            return evaluate;
        }


        @Override
        public XpathEvaluator wrap(XpathEvaluator newRule) {
            subEvaluators.add(newRule);
            return this;
        }
    }
}
