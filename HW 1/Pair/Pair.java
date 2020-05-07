public class Pair <T1, T2> {
    private T1 obj1;
    private T2 obj2;

    public Pair(T1 obj1, T2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T1 getFirstValue() {
        return this.obj1;
    }

    public T2 getSecondValue() {
        return this.obj2;
    }

    public Class getFirstType(){
        return this.obj1.getClass();
    }

    public Class getSecondType(){
        return this.obj2.getClass();
    }
}

