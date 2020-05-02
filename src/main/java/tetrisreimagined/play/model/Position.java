package tetrisreimagined.play.model;

import java.util.Objects;

public class Position {

    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // quando movemos a peça para a esquerda ou para a direita, movemos em duas unidades em x,
    // já que a proporção da peça é de 2. (por exemplo, a peça IBlock, que normalmente seria
    // composta apenas por uma linha de 4 blocos, no nosso caso tem 2 linhas de 8 blocos)
    // Se mantivessemos o deslocamento em x em apenas uma unidade, poderíamos por exemplo por
    // uma peça IBlock na vertical, seguida de uma coluna vazia, seguida de outra peça IBlock
    // na vertical; como poderíamos depois preencher essa coluna vazia?? Pois, seria impossível,
    // já que não há nenhuma peça tem apenas uma coluna ou uma linha.
    // No entanto, seria melhor efetuar um refactoring para isto ficar mais bonitinho.

    public Position left() {
        return new Position(this.x - 2, this.y);
    }

    public Position right() {
        return new Position(this.x + 2, this.y);
    }

    // quando for para mover para cima ou para baixo, apenas movemos em uma unidade em y,
    // para que o movimento seja mais fluído

    public Position up() {
        return new Position(this.x, this.y - 1);
    }

    public Position down() {
        return new Position(this.x, this.y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }



}
