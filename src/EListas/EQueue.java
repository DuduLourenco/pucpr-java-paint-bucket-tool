package EListas;

public class EQueue<T> {
    private ENode<T> first;
    private ENode<T> last;

    public void enqueue(T element) {
        ENode<T> node = new ENode<>(element);
        if(first == null) {
            first = node;
        } else {
            last.setNext(node);
        }

        if(last != null) {
            node.setPrevious(last);
        }

        last = node;
    }

    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("EQueue is empty");
        }

        T element = first.getElement();
        first = first.getNext();

        if(first == null) {
            last = null;
        }

        return element;
    }

    public void clear() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ENode<T> getFirst() {
        return first;
    }

    public ENode<T> getLast() {
        return last;
    }
}
