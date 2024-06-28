# Grupo do Careca

## Sprint 1 - Até 07/abril
  - Nota de grupo (8 pontos)
    - Modelo UML - restaurante, mesas, requisicoes, cliente (nota de grupo, 8 pontos)
	
  - Nota individual (12 pontos)
    - Implementações e testes + app
    - Documentação das classes.

### Commit d3afd32 (10/04)
Diagrama - sem requisição. sem parâmetros nem retornos ons métodos. - 3

Cliente - sem documentação. atributo "quantPessoas" é da requisição - Pedro Luis 

Main - ok - Pedro Luis - 10

Mesa - sem documentação nem testes. sets desnecessários - Pedro Farias - 4

Requisicao - print na classe. sets desnecessários. sem documentação nem testes - Pedro Lucas - 7

Restaurante - comentarios, sem javadoc. sem testes. Fazendo lógica das requisicoes - Lucas  - 7


## Sprint 2 - Até 19/maio
  - Nota de grupo (6 pontos)
    - Modelo UML atualizado - cardápio e pedidos
	- Estrutura Spring
  
  - Nota individual (14 pontos)	
    - Implementações cardápio e pedidos
    - Controllers
    - Correções anteriores

### Commit c8727f4 (22/05)
Estrutura Sprint - sem.

Diagrama - notações incorretas - print da tela - pedido no local errado - métodos, retornos - 3,6

Cardápio - sem documentação - Pedro Luis

Item - sem documentação - set sem validação - Pedro Luis

Pedido - if para pedidofechado - sem documentação - Pedro Luis

App - com muita quebra de encapsulamento - Pedro Luis	- 8,4

Requisicao - sem método para adicionar produto (lista de pedidos) - encerrar sem processo - sem conta - Pedro Farias - 4,2

Restaurante - sem itens no pedido - Lucas - Pedro Lucas - 8,4 *revisado*

## Sprint 3 - Até 05/junho
  - Nota de grupo (6 pontos)
    - Modelo atualizado - menu fechado
  
  - Nota individual (14 pontos)	
    - Implementações menu fechado e app
    - Correções anteriores

### Revisão 12/06
Diagrama ainda sem menu/pedido fechado - Todos - 3

Branch principal bem desatualizado. Ainda tem classe Garçom, por exemplo. Nada de Spring até o momento.

Cardápio: busca em lista com "for". get lista de itens. 

Item não precisa ter sets. Valores devem ser validadados (descrição vazia, preço negativo...)

Mesa não tem requisição, nem pedido, nem vários sets 

Pedido: getItens  

Pedido de menu fechado com boolean

Cardápio de menu fechado: sem

Requisicao: sets sem sentido. sem receber item. gets em sequencia

Restaurante não está registrando clientes. Sequência de chamadas para "mesa", verificação de regras de outras classes.... 

Todos os commits do Pedro Luis (11) . Pedro Lucas com um commit de teste requisicao. (3)

## Sprint 4 - Apresentação 26/06
  - Nota de grupo 3,6/6 pontos
	- Modelo atualizado - muitos erros de notação (herança/associacão/etc). Faltando o pedido fechado.
	- Apresentação - infelizmente, muito prejudicada por não termos conseguido rodar o sistema adequadamente para os testes 
	
  - Nota individual (14 pontos)
    - Ajustes do último quadro "Projeto GitHub"
    - Correções das sprints anteriores
	
Pedro Lucas: Controllers/Spring. - 8,4    
  - Pedido: sem operação para inserir item.
  - Requisição: somente operações de criar/apagar. Sem nenhuma operação dos requisitos.

Pedro Luis: Main/pedidos e restaurante - 8,4
  - Restaurante: com print na classe. Fazendo lógica de requisicao. 
  - Main: melhorou, mas não tira o cliente da espera. Fazendo get em restaurante para obter cliente. isPresent em optional. (nem estudamos isso...). Sem buscar produto do menu na hora de fazer pedido. É muito código duplicado e trabalho sem necessidade!!
  
Demais alunos: não apresentaram - 0 