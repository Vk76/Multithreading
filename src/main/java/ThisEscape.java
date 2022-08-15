
class Foo{
    int internalState;
    Foo(){
        setup();
    }
    public void setup(){
        internalState = 1;
    }
}


class Bar extends Foo{
    Bar(){
        super();
    }
    @Override
    public void setup() {
        internalState = 2;
        /* `this` reference got escaped even before initialization */
        new MaliciousCode().invoke(this);
    }
}


class MaliciousCode{
    public void invoke(Foo f){
        f.internalState = 999999999;
    }
}


public class ThisEscape{
    public static void main(String[] args) {
        Foo f = new Bar();
        System.out.println(f.internalState);
    }
}