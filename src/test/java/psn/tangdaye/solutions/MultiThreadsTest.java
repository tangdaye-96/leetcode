package psn.tangdaye.solutions;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class MultiThreadsTest {


    @Test
    public void testPrintInOrder() throws InterruptedException {
        final StringBuilder sb = new StringBuilder();
        final CountDownLatch latch = new CountDownLatch(3);
        final MultiThreads.Foo foo = new MultiThreads.Foo();
        Runnable r1 = () -> sb.append("first");
        Runnable r2 = () -> sb.append("second");
        Runnable r3 = () -> sb.append("third");
        Thread[] ts = {new Thread(() -> {
            try {
                foo.first(r1);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }), new Thread(() -> {
            try {
                foo.second(r2);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }), new Thread(() -> {
            try {
                foo.third(r3);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })};
        int[] array = {1, 3, 2};
        for (int i : array) {
            ts[i - 1].start();
        }
        latch.await();
        Assert.assertEquals("firstsecondthird", sb.toString());
    }

    @Test
    public void testPrintFoobarAlternately() throws InterruptedException {
        final StringBuilder sb = new StringBuilder();
        final CountDownLatch latch = new CountDownLatch(2);
        final MultiThreads.FooBar fb = new MultiThreads.FooBar(10);
        Runnable r1 = () -> sb.append("foo");
        Runnable r2 = () -> sb.append("bar");
        new Thread(() -> {
            try {
                fb.foo(r1);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fb.bar(r2);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        Assert.assertEquals(60, sb.length());
    }

    @Test
    public void testZeroEvenOdd() throws InterruptedException {
        final StringBuilder sb = new StringBuilder();
        final IntConsumer ic = sb::append;
        final MultiThreads.ZeroEvenOdd zeroEvenOdd = new MultiThreads.ZeroEvenOdd(10);
        final CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(ic);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(ic);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(ic);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        Assert.assertEquals("010203040506070809010", sb.toString());
    }

    @Test
    public void testBuildingH2O() throws InterruptedException {
        final StringBuilder sb = new StringBuilder();
        final MultiThreads.H2O h2O = new MultiThreads.H2O();
        final CountDownLatch latch = new CountDownLatch(2);
        Runnable r1 = () -> sb.append("h");
        Runnable r2 = () -> sb.append("o");
        final String s = "hhhhhhooo";
        new Thread(() -> {
            try {
                for (int i = 0; i < 2 * s.length() / 3; i++) {
                    h2O.hydrogen(r1);
                }
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < s.length() / 3; i++) {
                    h2O.oxygen(r2);
                }
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        for (int i = 0; i < sb.length(); i += 3) {
            char[] t = sb.substring(i, i + 3).toCharArray();
            Arrays.sort(t);
            Assert.assertEquals("[h, h, o]", Arrays.toString(t));
        }
    }

    @Test
    public void testFizzBuzz() throws InterruptedException {
        final List<String> list = new ArrayList<>();
        Runnable printFizz = () -> list.add("fizz");
        Runnable printBuzz = () -> list.add("buzz");
        Runnable printFizzBuzz = () -> list.add("fizzbuzz");
        IntConsumer ic = (i) -> list.add(String.valueOf(i));
        CountDownLatch latch = new CountDownLatch(4);

        MultiThreads.FizzBuzz fizzBuzz = new MultiThreads.FizzBuzz(30);

        new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(ic);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        for (int i = 1; i <= list.size(); i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                Assert.assertEquals(list.get(i - 1), "fizzbuzz");
            } else if (i % 3 == 0) {
                Assert.assertEquals(list.get(i - 1), "fizz");
            } else if (i % 5 == 0) {
                Assert.assertEquals(list.get(i - 1), "buzz");
            } else {
                Assert.assertEquals(list.get(i - 1), String.valueOf(i));
            }
        }
    }

    @Test
    public void testDiningPhilosophers() throws InterruptedException {
        final Stack<int[]> s = new Stack<>();
        int[] result = {0, 0, 0, 0, 0};
        CountDownLatch[] allDone = {new CountDownLatch(1), new CountDownLatch(1), new CountDownLatch(1), new CountDownLatch(1), new CountDownLatch(1)};
        IntConsumer pickLeftFork = i -> System.out.printf("[%d,%d,%d],", i, 1, 1);
        IntConsumer pickRightFork = i -> System.out.printf("[%d,%d,%d],", i, 2, 1);
        IntConsumer eat = i -> System.out.printf("[%d,%d,%d],", i, 0, 3);
        IntConsumer putLeftFork = i -> System.out.printf("[%d,%d,%d],", i, 1, 2);
        IntConsumer putRightFork = i -> System.out.printf("[%d,%d,%d],", i, 2, 2);
        MultiThreads.DiningPhilosophers diningPhilosophers = new MultiThreads.DiningPhilosophers();
        for (int i = 0; i < 5; i++) {
            final int no = i;
            new Thread(() -> {
                try {
                    while (result[no] != 3) {
                        diningPhilosophers.wantsToEat(no, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                        result[no]++;
                    }
                    allDone[no].countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        for (CountDownLatch latch : allDone) latch.await();
        Assert.assertEquals("[3, 3, 3, 3, 3]", Arrays.toString(result));
    }


}
