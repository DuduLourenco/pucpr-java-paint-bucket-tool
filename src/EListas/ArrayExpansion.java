package EListas;

public class ArrayExpansion <H> {
    protected H[] data;
    protected int capacity;

    public ArrayExpansion() {
        this.data = (H[]) new Object[10];
    }

    public ArrayExpansion(int capacity) {
        this.data = (H[]) new Object[capacity];
    }

    /**
     * Classe que recebe um array e o retorna com 50% a mais de capacidade
     * @param array Array antigo
     * @return newArray - Array novo com os mesmos dados do antigo e 50% (arredondado) a mais de capacidade
     */
    public H[] arrayUpgrade (H[] array) {
        int tamanho = array.length;
        int novoTamanho = (int) Math.round(tamanho * 1.5);
        H[] newArray = (H[]) new Object[novoTamanho];
        for(int i = 0; i < tamanho; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Retorna a verificação de espaço no array
     * @param array
     * @return bool - true = array cheio ; false = array com espaço
     */
    public boolean isFull(H[] array){
        boolean cheio = true;
        for(int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                cheio = false;
            }
        }
        return cheio;
    }


    /**
     * Recebe um objeto e adiciona na lista
     * @param data Item a ser adicionado na lista
     */
    public void add (H data) {
        if (this.isFull(this.data)){
            this.data = this.arrayUpgrade(this.data);
        }

        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                this.data[i] = data;
                break;
            }
        }
    }

    /**
     * Recebe um objeto e adiciona na lista
     * @param index Índice que será adicionado o data
     * @param data Item a ser adicionado na lista
     * @throws ArrayIndexOutOfBoundsException Quando o índice informado é fora do alcance de índice do array
     */
    public void add (int index, H data) throws ArrayIndexOutOfBoundsException {
        if (this.isFull(this.data)){
            this.data = this.arrayUpgrade(this.data);
        }

        if (index > this.data.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Náo é possível acessar o índice " + index + " do array, pois ele não existe.");
        }

        if (index == this.data.length - 1) {
            this.add(data);
            return;
        }

        //Lógica para passar todos os próximos itens para o lado
        int ind = index;
        H atual = this.data[index];
        H proximo = null;
        do {
            proximo = this.data[ind + 1];
            this.data[ind + 1] = atual;
            atual = proximo;
            ind++;
        } while (atual != null);

        this.data[index] = data;
    }

    public H[] toArray() {
        return this.data;
    }

    /**
     *
     * @param index Índice que terá o conteúdo removido
     * @throws ArrayIndexOutOfBoundsException Quando o índice informado é fora do alcance de índice do array
     */
    public void remove (int index) throws ArrayIndexOutOfBoundsException {

        if (index > this.data.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Náo é possível acessar o índice " + index + " do array, pois ele não existe.");
        }

        H proximo = null;
        for (int ind = index; ind < this.data.length; ind++) {
            if (ind != this.data.length - 1) {
                proximo = this.data[ind + 1];
                this.data[ind] = proximo;
            } else {
                this.data[ind] = null;
            }
        }
    }

    /**
     *
     * @param index Índice do array
     * @param data Conteúdo a ser adicionado
     * @throws ArrayIndexOutOfBoundsException Quando o índice informado é fora do alcance de índice do array
     */
    public void set(int index, H data) throws ArrayIndexOutOfBoundsException {

        if (index > this.data.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Náo é possível acessar o índice " + index + " do array, pois ele não existe.");
        }

        this.data[index] = data;
    }

    public boolean contains(H data) {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == data) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param index Índice do array
     * @throws ArrayIndexOutOfBoundsException Quando o índice informado é fora do alcance de índice do array
     */
    public H get(int index) throws ArrayIndexOutOfBoundsException {

        if (index > this.data.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Náo é possível acessar o índice " + index + " do array, pois ele não existe.");
        }

        return this.data[index];
    }

    /**
     * Printa no terminal o array
     */
    public String toString() {
        System.out.print("[");
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                System.out.print("null");
            } else {
                System.out.print(this.data[i]);
            }
            if (i != this.data.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
        return null;
    }
}

