# sglabcon
Sistema de Gestão para Laboratórios de Concreto

O sistema de gestão de laboratórios de concreto (SGLabCon) tem por
objetivo atender empresas de concreto, laboratórios de construção civil e
universidades, possibilitando um controle mais rigoroso dos ensaios de concreto.
Com a aplicação deste sistema, além das tarefas relacionadas aos ensaios de
compressão de concreto se tornar mais organizadas, confiáveis e intuitivas, irá
proporcionar ao gestor da empresa, departamento ou entidade, ter um controle mais
rigoroso sobre a qualidade das suas amostras e sobre os funcionários que executam
os ensaios.

- Requisitos funcionais

Esta seção divide-se em uma abordagem dos requisitos gerais, requisitos
específicos aos ensaios e requisitos não funcionais.
- Geral
a) O sistema deve ser entregue com um usuário administrador previamente
configurado que será alterado depois pelo administrador do sistema e é a
partir dele que novos usuários serão adicionados.
b) O sistema deve autenticar todos os usuários através de usuário e senha
para só depois permitir acesso as suas funcionalidades principais
correspondentes a cada perfil de usuário:
- Laboratorista: Cadastrar coleção de ensaios e ensaio de amostras
individuais, acesso às suas informações de usuário, trocar própria senha e
visualizar ensaios já realizados.
- Administrador: Possui acesso a todas as funcionalidades do
laboratorista e também as demais funcionalidades do sistema: Realizar
cadastro e alteração de usuários, clientes, cidades, estados e parâmetros.
c) O sistema deve ter incorporado no seu banco de dados, alguns
parâmetros normativos que serão utilizados nos cálculos e apresentação
de resultados (Normas da ABNT), que regulamentam os ensaios:
- (Norma NBR 5738 - 2015) Dimensão básica dos corpos de prova
cilíndricos em milímetros: 100, 150, 250 e 450;
- (Norma ISO 7500.1-2004 e ISO 376 - 2011) Classe de máquina:
classe 0,5, classe 1, classe 2 e classe 3;
- (Norma NBR 5739 - 2007) Tipos de ruptura dos corpos de prova
cilíndricos: tipo A, tipo B, tipo C, tipo D, tipo E, tipo F e tipo G;
- Possibilitar o upload de imagem para ilustrar o tipo de ruptura e
auxiliar o laboratorista na escolha do tipo correto através da ilustração
durante a realização do ensaio. As ilustrações se encontram também na
norma NBR 5739 – 2007.
d) Na seção de parâmetros, exibir um breve informativo sobre a importância
da correta configuração dos parâmetros para manter o administrador
sempre em alerta para alterar as informações com cautela.
e) De preferência, programar as telas da seção de parâmetros de modo que
todas as subseções fiquem organizadas por categoria para melhorar a
experiência de uso e minimizar a chance de erro humano nas
configurações.
f) O usuário administrador pode fazer alterações nos parâmetros de normas
que estão no banco de dados caso alguma norma venha a ser alterada. A
mudança será possível através de uma tela de parâmetros do sistema;
g) As mudanças nos parâmetros inevitavelmente são limitadas à estrutura
projetada no sistema, ou seja, se a mudança nas normas for grande o
suficiente para não ser atendida pelas configurações de parâmetros e
escopo atual, a mudança só acontecerá por atualização geral do sistema.
h) O usuário administrador pode fazer o cadastro e alteração de usuários
cadastrados, os quais devem ser únicos e intransferíveis:
- Para cada cadastro de um novo usuário, solicitar usuário, nome
completo, senha (pode ser alterada pelo próprio usuário depois), papel
do usuário no sistema (administrador ou comum), e-mail, além de uma
opção de manter o usuário ativo ou inativo;
i) Qualquer usuário pode acessar as próprias informações de usuário;
j) Qualquer usuário pode alterar sua própria senha de acesso a qualquer
momento;
k) Para usuários com privilégios de administrador: Exibir na página inicial
atalhos para as principais funcionalidades do sistema (acesso à seção de
ensaios, seção de cadastros de clientes e usuários e acesso à seção de
configuração de parâmetros do sistema);
l) Uma barra de ferramentas deve acompanhar todas as telas para
possibilitar o usuário de navegar para qualquer tela do sistema (que o
atual usuário tenha privilégios de acessar) de onde estiver;
m) Se o usuário não tiver privilégios de administrador, não permitir que ele
tenha acesso às funções de cadastros no sistema, nem à configuração de
parâmetros, exceto pelas funções de realização de ensaios de
compressão onde ele deve ter acesso para poder realizar seu trabalho.
n) Para o cadastro de clientes, solicitar dados de CNPJ, nome fantasia,
endereço, bairro, CEP, telefone fixo, telefone móvel, e-mail, UF e Cidade.
o) Oferecer campo de busca para realizar consultas dos ensaios realizados;

- Ensaio de compressão de corpos de prova cilíndricos baseado na NBR 5739-2007
O sistema precisa das seguintes informações para poder finalizar o resultado
do ensaio:
a) Informações do ensaio:
- Data de moldagem;
- Lote;
- Quantidade de amostras a serem ensaiadas;
- Classe da máquina utilizada;
- Tipo de capeamento;
- Resistência de projeto;
- Dimensão básica expressa em milímetros (mm);
- Cliente (preenchimento opcional);
- Usuário que cadastrou as informações do ensaio;
- Campo para submeter observações que forem pertinentes a respeito
daquele ensaio (preenchimento opcional);

b) Informações sobre as amostras individuais:
- Identificação do corpo de prova/amostra (Numeração ou outra
identificação escolhida para identificar os corpos de prova);
- Força aplicada em Newtons (N);
- Data atual do ensaio individual da amostra;
- Usuário que está realizando o ensaio individual da amostra;
- As informações sobre a amostra individual precisam se referenciar a um
ensaio (coleção dos ensaios individuais);
- Altura real do corpo de prova expresso em milímetros (mm);
- Diâmetro real do corpo de prova expresso em milímetros (mm);
- Tipo de ruptura resultada da compressão do corpo de prova
(preenchimento opcional);
- Relação diâmetro/altura (h/d);
c) Dos cálculos do ensaio
- A relação h/d é obtida através do diâmetro real e altura real;
- Resistência final calculada pela fórmula: fc = 4F / π x D2. Onde fc é a
força final em megapascals, F é a força aplicada em newtons, e D é o
diâmetro real do corpo de prova em milímetros;
- Se a relação h/d for menor do que 1,94, a força F deve ser multiplicada
pelo fator de correção correspondente ao h/d encontrado, onde:
- A relação h/d de valor 2 está para o fator 1, assim como 1,50 está
para 0,96;
- A relação h/d de valor 1,50 está para o fator 0,96, assim como 1,25
está para 0,93;
- A relação h/d de valor 1,25 está para o fator 0,93, assim como 1 está
para 0,87;
- Os índices correspondentes à relação h/d não indicada podem ser
obtidos através de interpolação linear, com aproximação de centésimos;
- Status da amostra: Caso atinja a resistência esperada para aquele
ensaio, ou seja, a resistência de projeto: Aprovado; do contrário:
Reprovado;
- Se a relação h/d for maior do que 2,02 a amostra deve ser reprovada.

- Requisitos não funcionais
a) Deve ser compatível com os principais navegadores do mercado;
b) Tolerância a falhas: Em caso de queda abrupta do serviço, os dados no
banco não podem ser corrompidos, por isso, fazer uso de políticas de
transações durante inserções e alterações de registros no banco de dados.
c) Interface amigável, cores sóbrias, sem poluição visual, funções, informações
e campos bem organizados, mensagens de alerta, sucesso e erro com
informações úteis e compreensíveis ao usuário do sistema, levando em
consideração os conceitos de IHC e tratamento de erros;
