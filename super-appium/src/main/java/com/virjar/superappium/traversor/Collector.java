package com.virjar.superappium.traversor;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.ViewImages;
import com.virjar.superappium.util.Lists;

import java.util.List;

public class Collector {

    private Collector() {
    }

    /**
     * Build a list of elements, by visiting root and every descendant of root, and testing it against the evaluator.
     *
     * @param eval Evaluator to test elements against
     * @param root root of tree to descend
     * @return list of matches; empty if none
     */
    public static ViewImages collect(Evaluator eval, ViewImage root) {
        ViewImages elements = new ViewImages();
        new NodeTraversor(new Accumulator(root, elements, eval)).traverse(root);
        return elements;
    }

    private static class Accumulator implements NodeVisitor {
        private final ViewImage root;
        private final List<ViewImage> elements;
        private final Evaluator eval;

        Accumulator(ViewImage root, List<ViewImage> elements, Evaluator eval) {
            this.root = root;
            this.elements = elements;
            this.eval = eval;
        }

        public void head(ViewImage node, int depth) {
            if (eval.matches(root, node))
                elements.add(node);

        }

        public void tail(ViewImage node, int depth) {
            // void
        }
    }
}
