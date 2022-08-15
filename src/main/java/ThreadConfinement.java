

class User{
    private static int counter;
    private final int userId;
    User(){
        userId = counter;
        counter++;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                '}';
    }
}

class SharedState{
    ThreadLocal<User> user = ThreadLocal.withInitial(User::new);
}

public class ThreadConfinement  {
    public static final SharedState state = new SharedState();


    public static void main(String[] args) {
        /* Each thread will have it's own user because ThreadLocal will make sure that each thread have it's different state*/
        /* `get` call from the new thread will initialize new instance of underlying value in threadLocal */
        for (int i = 0; i < 500; i++) {
            Thread t = new Thread(() -> System.out.println(state.user.get()));
            t.start();
        }
    }
}
