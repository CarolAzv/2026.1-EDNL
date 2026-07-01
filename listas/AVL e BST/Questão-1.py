#1 - Quando dois elementos são removidos de uma árvore binária de pesquisa a árvore final depende da ordem
#em que eles são removidos? Justifique a sua resposta com exemplos.

  Se a árvore binária seguir os padrões de uma árvore AVL então, dependendo da árvore, sim, a árvore final pode ser diferente. Por exemplo,
uma árvore com raiz x, dois filhos a esquerda e um a direita, se a remoção for dos ultimos filhos em cada lado

         10
    6         15
 4      
          
----------------------
         6
              10

----------------------
         10
    6         
    