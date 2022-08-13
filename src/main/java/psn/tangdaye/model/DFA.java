package psn.tangdaye.model;

import lombok.Builder;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 确定有穷状态机
 */
public class DFA {

    private List<DFANode> nodes;
    private int current;

    public DFA(String pattern) {
        nodes = new ArrayList<>();
        current = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '.') {
                if (nodes.size() > 0) {
                    DFANode pre = nodes.get(nodes.size() - 1);
                    if (pre.any) {
                        pre.leastRepeatTime += 1;
                        continue;
                    }
                }
                nodes.add(DFANode.builder().any(true).build());
            } else if (c == '*') {
                if (nodes.size() == 0) {
                    throw new RuntimeException("wrong pattern");
                }
                DFANode pre = nodes.get(nodes.size() - 1);
                if (pre.repeat) {
                    throw new RuntimeException("wrong pattern");
                }
                pre.repeat = true;
            } else {
                if (nodes.size() > 0) {
                    DFANode pre = nodes.get(nodes.size() - 1);
                    if (pre.c == c) {
                        pre.leastRepeatTime += 1;
                        continue;
                    }
                }
                nodes.add(DFANode.builder().c(c).build());
            }
        }
    }

    /**
     * -1 表示错误
     * 0 表示需要重复调用
     * 1 表示正确
     */
    private int next(char c) {
        if (current >= nodes.size()) {
            return -1;
        }
        DFANode node = nodes.get(current);
        if (!node.repeat) {
            if (node.c == c || node.any) {
                current += 1;
                return 1;
            }
            if (current >= 1) {
                int pre = current - 1;
                DFANode preNode = nodes.get(pre);
                if (preNode.repeat && (preNode.any || preNode.c == c)) {
                    preNode.repeatTime += 1;
                    return 1;
                }
            }
            return -1;
        } else {
            current += 1;
            if (node.c == c || node.any) {
                node.repeatTime += 1;
                return 1;
            }
            return 0;
        }
    }

    private void reset() {
        current = 0;
        for (DFANode node : nodes) {
            node.repeatTime = 0;
        }
    }

    public boolean match(String s) {
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int x = next(c);
            if (x < 0) {
                return false;
            }
            if (x > 0) {
                i += 1;
            }
        }
        boolean result = true;
        for (DFANode node : nodes) {
            if (node.repeat && node.leastRepeatTime > node.repeatTime) {
                result = false;
                break;
            }
        }
        int t = current;
        reset();
        return t == nodes.size() && result;
    }

    @Builder
    @Setter
    private static class DFANode {
        private char c;
        private boolean any;
        private boolean repeat;
        private int leastRepeatTime = 0;

        private int repeatTime = 0;
    }
}
