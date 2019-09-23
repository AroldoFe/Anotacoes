<h1> Anotacoes </h1>
<p>Esse projeto foi criado com o intuito de participar da seleção de estágio em Desenvolvimento em Java da ESIG. Por isso, nele foi criada uma aplicação Java Web que trata de Anotações. Essas anotações possuem um título, uma descrição, uma data em que foi cadastrada e uma data em que foi editada por último.
<p>Para isso, utilizou-se o padrão JSF para construção das páginas aproveitando-se da separação das responsabilidades. Além disso, utilizou-se persistência de dados no PostgreSQL. 
<p>Garantindo, assim, o cumprimento dos requisitos mínimos para a seleção A e B. Além disso, utilizou-se ainda o item C dos requisitos adicionais, que é utilizar Hibernate e JPA.
<p>Ainda foi criada uma classe de teste para testar os métodos da classe que se comunica com o banco, na tentativa de cumprir com o requisito adicional H. Entretanto, ao executar, ela não conseguia inicializar a classe RepositorioNota. Por isso, os testes falharam, provavelmente.
<h2> Para execução </h2>
<p>Criar um banco de dados no PostgreSQL chamado notadb com uma tabela nota. Esta contem campos id (Integer), titulo (Text), descricao (Text), datacadastro (Date), dataultimaedicao (Date). O banco notadb pertece ao usuário nota, que possui senha nota.
<p><p> Autor: Aroldo Felix
