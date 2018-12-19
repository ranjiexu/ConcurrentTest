package chapter7;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by xuran on 2018/12/19.
 * 原子更新引用类型
 */
public class AtomicReferenceTest {
    static AtomicReference<User> reference = new AtomicReference<>();
    public static void main(String[] args) {
        User user = new User("xx", 10);
        reference.set(user);
        User newUser = new User("ss", 20);
        reference.compareAndSet(user, newUser);
        System.out.println(reference.get().getName());
        System.out.println(reference.get().getAge());
    }

    static class User {
        private String name;
        private int age;

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
