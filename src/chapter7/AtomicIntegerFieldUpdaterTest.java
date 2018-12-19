package chapter7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by xuran on 2018/12/19.
 * 原子更新引用字段
 * 第一步，使用静态方法newUpdater创建一个更新器
 * 第二步，更新的字段必须使用 public volatile 修饰
 */
public class AtomicIntegerFieldUpdaterTest {
    static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
    public static void main(String[] args) {
        User user = new User("ss", 10);
        System.out.println(updater.getAndIncrement(user));
        System.out.println(updater.get(user));
    }

    static class User {
        private String name;
        public volatile int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
