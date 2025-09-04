package EListas;

public class EStack<T> {
    private ENode<T> top;

    public void push(T element) {
        ENode<T> node = new ENode<>(element);
        node.setPrevious(top);
        top = node;
    }

    public T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("EStack is empty");
        }

        T element = top.getElement();
        top = top.getPrevious();

        return element;
    }

    public void clear() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
