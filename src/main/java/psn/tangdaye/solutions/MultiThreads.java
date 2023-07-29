package psn.tangdaye.solutions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 多线程相关
 */
public class MultiThreads {

    /**
     * 1114. 按序打印
     *
     * <p>三个不同的线程 A、B、C 将会共用一个Foo实例。</p>
     * <p>线程 A 将会调用 first() 方法</p>
     * <p>线程 B 将会调用second() 方法</p>
     * <p>线程 C 将会调用 third() 方法</p>
     * <p>请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。</p>
     *
     * <a href="https://leetcode.cn/problems/print-in-order/">https://leetcode.cn/problems/print-in-order/</a>
     */
    public static class Foo {
        CountDownLatch latch1;
        CountDownLatch latch2;

        public Foo() {
            latch1 = new CountDownLatch(1);
            latch2 = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            latch1.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            latch1.await();
            printSecond.run();
            latch2.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            latch2.await();
            printThird.run();
        }
    }

    /**
     * 1115. 交替打印 FooBar
     * <p>两个不同的线程将会共用一个 FooBar实例：</p>
     * <p>线程 A 将会调用foo()方法</p>
     * <p>线程 B 将会调用bar()方法</p>
     * <p>请设计修改程序，以确保 "foobar" 被输出 n 次。</p>
     * <a href="https://leetcode.cn/problems/print-foobar-alternately/">https://leetcode.cn/problems/print-foobar-alternately/</a>
     */
    public static class FooBar {
        private final int n;
        private final Semaphore s1;

        private final Semaphore s2;


        public FooBar(int n) {
            this.n = n;
            this.s1 = new Semaphore(1);
            this.s2 = new Semaphore(0);

        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                s1.acquire();
                printFoo.run();
                s2.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                s2.acquire();
                printBar.run();
                s1.release();
            }
        }
    }

    /**
     * 1116. 打印零与奇偶数
     *
     * <p>现有函数 printNumber 可以用一个整数参数调用，并输出该整数到控制台。</p>
     * <p>
     * <p>例如，调用 printNumber(7) 将会输出 7 到控制台。</p>
     * <p>给你类 ZeroEvenOdd 的一个实例，该类中有三个函数：zero、even 和 odd 。ZeroEvenOdd 的相同实例将会传递给三个不同线程：</p>
     * <p>
     * <p>线程 A：调用 zero() ，只输出 0</p>
     * <p>线程 B：调用 even() ，只输出偶数</p>
     * <p>线程 C：调用 odd() ，只输出奇数</p>
     * <p>修改给出的类，以输出序列 "010203040506..." ，其中序列的长度必须为 2n 。</p>
     *
     * <a href="https://leetcode.cn/problems/print-zero-even-odd/">https://leetcode.cn/problems/print-zero-even-odd/</a>
     */
    public static class ZeroEvenOdd {
        private final int n;
        private final Semaphore semaphoreZero;
        private final Semaphore semaphoreOdd;
        private final Semaphore semaphoreEven;


        public ZeroEvenOdd(int n) {
            this.n = n;
            semaphoreZero = new Semaphore(1);
            semaphoreOdd = new Semaphore(0);
            semaphoreEven = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                semaphoreZero.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) {
                    semaphoreOdd.release();
                } else {
                    semaphoreEven.release();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i < n; i++) {
                if (i % 2 == 1) {
                    semaphoreOdd.acquire();
                    printNumber.accept(i);
                    semaphoreZero.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i++) {
                if (i % 2 == 0) {
                    semaphoreEven.acquire();
                    printNumber.accept(i);
                    semaphoreZero.release();
                }
            }
        }
    }

    /**
     * 1117. H2O 生成
     * <p>现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。</p>
     * <p>存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。</p>
     * <p>氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。</p>
     * <p>这些线程应该三三成组突破屏障并能立即组合产生一个水分子。</p>
     * <p>你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。</p>
     * <p>换句话说:</p>
     * <p>如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。</p>
     * <p>如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。</p>
     * <p>书写满足这些限制条件的氢、氧线程同步代码。</p>
     * <a href="https://leetcode.cn/problems/building-h2o/">https://leetcode.cn/problems/building-h2o/</a>
     */
    public static class H2O {
        private final Semaphore sh;
        private final Semaphore so;

        public H2O() {
            sh = new Semaphore(2);
            so = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            sh.acquire();
            releaseHydrogen.run();
            so.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            so.acquire(2);
            releaseOxygen.run();
            sh.release(2);
        }
    }

    /**
     * 1195. 交替打印字符串
     *
     * <p>编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：</p>
     * <p>
     * <p>如果这个数字可以被 3 整除，输出 "fizz"。</p>
     * <p>如果这个数字可以被 5 整除，输出"buzz"。</p>
     * <p>如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。</p>
     * <p>例如，当n = 15，输出：1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。</p>
     *
     * <a href="https://leetcode.cn/problems/building-h2o/">https://leetcode.cn/problems/fizz-buzz-multithreaded/</a>
     */
    public static class FizzBuzz {
        private final int n;
        private final Semaphore sf;
        private final Semaphore sb;
        private final Semaphore sfb;
        private final Semaphore sm;


        public FizzBuzz(int n) {
            this.n = n;
            sf = new Semaphore(0);
            sb = new Semaphore(0);
            sfb = new Semaphore(0);
            sm = new Semaphore(0);
        }

        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    sf.acquire();
                    printFizz.run();
                    sm.release();
                }
            }
        }

        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    sb.acquire();
                    printBuzz.run();
                    sm.release();
                }
            }
        }

        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    sfb.acquire();
                    printFizzBuzz.run();
                    sm.release();
                }
            }
        }

        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    sfb.release();
                    sm.acquire();
                } else if (i % 3 == 0) {
                    sf.release();
                    sm.acquire();
                } else if (i % 5 == 0) {
                    sb.release();
                    sm.acquire();
                } else {
                    printNumber.accept(i);
                }
            }
        }
    }


    /**
     * 1226. 哲学家进餐
     *
     * <p>5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）</p>
     * <p>所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。</p>
     * <p>假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。</p>
     * <p>设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。</p>
     *
     * <a href="https://leetcode.cn/problems/the-dining-philosophers/">https://leetcode.cn/problems/the-dining-philosophers/</a>
     */
    public static class DiningPhilosophers {
        private final ReentrantLock[] forks = {new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};
        private final Semaphore semaphore = new Semaphore(3);

        public DiningPhilosophers() {
        }

        public void wantsToEat(int no, IntConsumer pickLeftFork, IntConsumer pickRightFork, IntConsumer eat, IntConsumer putLeftFork, IntConsumer putRightFork) throws InterruptedException {
            semaphore.acquire();
            forks[no].lock();
            forks[(no + 4) % 5].lock();
            pickLeftFork.accept(no);
            pickRightFork.accept(no);
            eat.accept(no);
            putRightFork.accept(no);
            putLeftFork.accept(no);
            forks[(no + 4) % 5].unlock();
            forks[no].unlock();
            semaphore.release();
        }
    }
}
