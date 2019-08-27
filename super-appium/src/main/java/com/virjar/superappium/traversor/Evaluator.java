package com.virjar.superappium.traversor;

import com.virjar.superappium.ViewImage;

public abstract class Evaluator {
    protected Evaluator() {
    }

    /**
     * Test if the element meets the evaluator's requirements.
     *
     * @param root    Root of the matching subtree
     * @param element tested element
     * @return Returns <tt>true</tt> if the requirements are met or
     * <tt>false</tt> otherwise
     */
    public abstract boolean matches(ViewImage root, ViewImage element);

    public static class AllElements extends Evaluator {
        @Override
        public boolean matches(ViewImage root, ViewImage element) {
            return true;
        }
    }

    public static class ByTag extends Evaluator {
        private String tag;

        public ByTag(String tag) {
            this.tag = tag;
        }

        @Override
        public boolean matches(ViewImage root, ViewImage element) {
            return element.getType().equals(tag);
        }
    }
}
