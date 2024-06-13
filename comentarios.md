# Comentários - Revisão de 12/06 (fim da Sprint 3)

Todos os comentários referem-se ao código do ramo "master" ou "main". É obrigação do grupo manter o código principal neste ramo. Problemas relatados podem ser:

- ❕❕ - observações e melhorias
- ⚠️ - médios. Erros de lógica, regras mal implementadas... Descontos de até 1 ponto.
- 🚨 - sérios. Regras faltando, problemas de modularidade... Descontos de até 3 pontos.
- 💣 - graves. Falta de classes, requisitos ignorados ... Descontos de 5 ou mais pontos.

## Revisão

- 🚨 branch principal bem desatualizado. Ainda tem classe Garçom, por exemplo. Nada de Spring até o momento.

- ⚠️ cardápio: não se faz busca em lista com "for". Tem método para isso.

- 🚨 cardápio não deve devolver a lista de itens. Deve exibir a lista

- 🚨 cliente não pode ter setId (não muda)

- 🚨 Item não precisa ter sets. É definido no construtor. Valores devem ser validadados (descrição vazia, preço negativo...)
- 🚨 Mesa não tem requisição. A requisição tem uma mesa.
- 🚨 Mesa não tem pedido. A requisição tem um pedido
- 🚨 Mesa tem vários sets. Não deve ter nenhum (é um objeto imutável, a não ser pela disponibilidade)
- 🚨 Pedido não deve ter getItens
- 💣 Ser pedido de menu fechado não pode ser validado por booleano. (isso viola SOLID). Herança com método abstrato de pedir Item
- 💣 Provavelmente faltando alguma maneira de mostrar o cardápio do menu fechado.
- 🚨 Diversos sets sem sentido na Requisição (número de pessoas, por exemplo)
- 🚨 Requisição não recebe pedido. Recebe um item que será inserido no pedido
- 🚨 Requisição dando diversos gets em classes externas no relatório
- 🚨 Restaurante não está registrando clientes
- ⚠️ Método "mesa existe" no restaurante parece ser inútil
- 🚨 Lógica errada no restaurante: sequência de chamadas para "mesa", verificação de regras de outras classes....
- 💣 Não sei se entendi direito: todos os commits do ramo master são do Pedro Luís? O que os demais alunos estão fazendo?
