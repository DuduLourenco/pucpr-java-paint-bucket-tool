package EListas;

public class ENode<T> {
    private T element;
    private ENode<T> next;
    private ENode<T> previous;

    public ENode(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public ENode<T> getNext() {
        return next;
    }

    public void setNext(ENode<T> next) {
        this.next = next;
    }

    public ENode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(ENode<T> previous) {
        this.previous = previous;
    }
}
