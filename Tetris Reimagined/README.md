#LPOO_22 - Tetris Reimagined
Réplica do jogo Tetris, que é um jogo que consiste em empilhar blocos que descem no ecrã, de forma a completarem linhas horizontais.

Este projeto foi desenvolvido por Rui Pinto (up201806441@fe.up.pt) e Tiago Gomes (up201806658@fe.up.pt) para a unidade corricular de LPOO 2019/2020.

##Implemented Features
  * Movimento das peças para a esquerda e direita através do teclado.
  * Movimento das peças para baixo à medida que o tempo passa.
  * Limites das peças dentro da janela.
  * Verificação de colisões entre peças.
  * Soft drop: a peça desce mais rápido quando pressionamos a tecla 'seta para baixo'.
  * Hard drop: a peça desce na arena o máximo possível quando pressionamos a tecla SPACE.
  * Rotação das peças no sentido horário e anti-horário.
  * Remoção de linhas completas: quando uma linha está completamente preenchida por blocos, então é removida da arena (com bugs)
   
   
  ![Contribution guidelines for this project](./Implemented2.png)
    
    
  * Ajuste da posição dos blocos depois de uma linha ter sido removida: os blocos das linhas que estão acima da linha removida descem uma unidade.
  * Nível de dificuldade: aumenta a cada 6 linhas "feitas"; faz aumentar a velocidade da peça.
  * Sistema de pontuação: aumenta quando o jogador "faz uma linha", quando usa o soft ou hard drop; a pontuação adicionada é sempre multiplicada pelo nível.
  * Visualização da próxima peça, na consola.
  * Fim do jogo: o jogo termina quando não for possível a uma peça descer mais na arena a partir da posição inicial.
    
  ![Contribution guidelines for this project](./Implemented1.png)
     
  ![ALT TEXT](https://media.giphy.com/media/XcGdJLs8ql3vhKoqoZ/giphy.gif) 
   
  
  
##Planned Features
  * Top pontuações.
  * Modo Multiplayer
  * Peça em espera: peça que o jogador decidiu guardar para usar mais tarde.
  * Visualização da próxima peça, da pontuação e do nível em modo gráfico.
  * Rotação de toda a arena quando rodamos uma peça.
  * 

#Design
  
  * Model-View-Controller (MVC) and Observer Pattern
  
>**Problem in Context:**
> Encontrar uma estratégia que permitesse conjugar os dados (Model) com a gui (View) e com o controlador que dita as regras 
>do jogo (Controller), não implicando a renovação do código.

>**The Pattern:**
>A arquitetura MVC pressupõe a divisão do projeto e propõe a utilização da divisão do projeto em três partes diferentes,
>referidas em cima. O Model representa os dados, a View dá o display da nossa cena e envia commandos para o Controller
>e o Controller fornece os dados à View e interpreta as ações do utilizador. Decidimos incluir o Observer Pattern no MVC, 
>de modo a facilitar ainda mais a relação entre o Model e a View. Assim sendo, quando o Model é alterado, notifica a View 
>de que foram feitas alterações e redesenha a cena.

>**Implementation:**
![ALT TEXT](./MVC.PNG)
>**Consequences:**
>Com esta implementação, alcançamos uma melhor estruturação do projeto. O processo de notificar e registo de ocorrências,
>agiliza ainda mais a questão de desenhar a cena, uma vez que a mesma só é novamente desenhada se ocorrerem alterações. 

  * Command Pattern
   
>**Problem in Context:** 
>A existência de uma cadeia de ifs na ArenaController (https://github.com/FEUP-LPOO/lpoo-2020-g22/blob/8499d7136abca20b8e0f87ea665597d1d6fe2fd1/Tetris%20Reimagined/src/main/java/tetrisreimagined/play/rules/ArenaController.java#L62-L105)
>que se tornaria cada vez maior à medida
>que aumentássemos o número de comandos a utilizar.
    
>**The Pattern:**
>O Command pattern permite parametrizar objetos com uma ação a executar. Baseia-se em diferentes funcionalidades para cada um deles.
>É comum terem um método "execute" que contém todo o código necessário à execução desse determinado comando. Dada o problema apresentado no tópico acima,
>consideramos que seria uma boa solução, uma vez que iria simplificar o código e desta forma, a ArenaController apenas executaria o comando,
>viabilizando assim o SRP (Single Responsability Principle)

>**Implementation:**
![ALT TEXT](./CommandPattern.PNG)

>**Observer:**
>Consequences: A utilização deste padrão permitiu uma maior simplificação do ArenaController ([ArenaController.java - Result](./src/main/java/tetrisreimagined/play/rules/ArenaController.java)) bem como uma maior abstração dos comandos utilizados.
  
#Code Smells e Sugestões de Refactoring
  * Implementar State
  * Implementar Abstract Factory
  * **Long Method e Duplicate Code:** os métodos rotatePiece de RotateClockWise e RotateCounterClockWise são bastante longos, pelo que os poderíamos ter dividido em vários métodos mais pequenos. Além disso, existe uma certa similaridade entre os métodos, havendo algum código repetido. Poderíamos condensar os comandos RotateClockWise e RotateCounterClockWise, ficando com um comando Rotate, que receberia no construtor qual o tipo de rotação. 
  * **If Statments:** os if's de getCommand, na classe GameViewLanterna, poderiam ser substituídos por um switch statement.
  * **Return null:** no método getBlockById, na classe PieceController, poderíamos criar uma classe NullBlock.
  * 
  *  
  
#Testing
  * Almost everything tested
  * 
  
#Self-evaluation
  *  2dukes:
  * ![Contribution guidelines for this project](./309558.jpg)