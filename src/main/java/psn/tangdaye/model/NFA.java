package psn.tangdaye.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 不确定有穷状态机
 */
public class NFA {

    private List<NFANode> nodes;
    private String pattern;
    private int current;

    public NFA(String pattern) {
        this.pattern = pattern;
        nodes = new ArrayList<>();
        current = 0;
        int i = 0;
        while (i < pattern.length()) {
            char c = pattern.charAt(i);
            if (c == '*') {
                if (nodes.size() == 0) {
                    throw new RuntimeException("wrong pattern");
                }
                NFANode pre = nodes.get(nodes.size() - 1);
                pre.infinity = true;
                i++;
            } else {
                NFANode node = new NFANode();
                node.c = c;
                nodes.add(node);
                i++;
            }
        }
    }

    /**
     * 支持回溯
     * -1 失败
     *  0 不变（空变化）
     *  1 下一个字符
     */
    private int next(String s, int i) {
        if (current >= nodes.size()) {
            return back(s, i);
        }
        NFANode node = nodes.get(current);
        if (node.infinity) {
            // 懒匹配
            current += 1;
            return i;
        }
        if (node.c == s.charAt(i) || node.any()) {
            current += 1;
            node.hasMatch = true;
            return i + 1;
        } else {
            return back(s, i);
        }
    }

    private int back(String s, int i) {
        current -= 1;
        while (current >= 0 && i >= 0) {
            NFANode node = nodes.get(current);
            if (node.c == s.charAt(i) || node.any()) {
                if (node.hasMatch && !node.infinity) {
                    return -1;
                }
                // 否则只需要能够匹配上就行
                current = current + 1;
                node.hasMatch = true;
                return i + 1;
            }
            if (node.hasMatch) {
                current -= 1;
                node.hasMatch = false;
                // 如果已经匹配过了，那么需要递归回溯
                return back(s, i - 1);
            }
            current = current - 1;
        }
        return -1;
    }

    private void reset() {
        current = 0;
    }

    public boolean match(String s) {
        int i = 0;
        while (i < s.length()) {
            i = next(s, i);
            if (i < 0) {
                reset();
                return false;
            }
        }

        for (; current < nodes.size(); current++) {
            NFANode node = nodes.get(current);
            if (!node.infinity) {
                reset();
                return false;
            }
        }
        reset();
        return true;
    }

    private static class NFANode {
        private char c;
        private boolean infinity = false;
        private boolean hasMatch = false;

        private boolean any() {
            return c == '.';
        }

        @Override
        public String toString() {
            return c + (infinity ? "*" : "");
        }
    }

    @Override
    public String toString() {
        return pattern;
    }
}