<h1 style="font-weight: bold; font-size: 36px;">Animals API</h1>

<h2 style="font-weight: bold; font-size: 28px;">Descrição</h2>
<p style="font-size: 14px;">O <b>Animals API</b> é um projeto de backend que permite o armazenamento e gerenciamento de informações sobre animais e suas comidas. As funcionalidades incluem:</p>
<ul style="font-size: 14px;">
  <li>Criação e armazenamento de animais e suas informações.</li>
  <li>Criação de comidas que podem ser associadas a um ou mais animais.</li>
  <li>Associação de comidas a animais existentes.</li>
  <li>Filtros avançados para busca de animais com base em parâmetros específicos.</li>
  <li>Upload de imagens de animais para um bucket S3 da AWS, com armazenamento do link público da imagem no banco de dados.</li>
  <li>Endpoints para listar todos os animais e comidas, além de buscas detalhadas por ID.</li>
</ul>
<p style="font-size: 14px;">Este projeto está rodando em uma instância EC2 da AWS e utiliza um banco de dados RDS. Não é necessário baixar e configurar o projeto localmente para testá-lo, mas o projeto está preparado para execução local, caso desejado.</p>

<h2 style="font-weight: bold; font-size: 28px;">URL do Projeto</h2>
<p style="font-size: 14px;">A API está hospedada e disponível para uso no seguinte endereço:</p>

<p style="font-size: 14px;">54.207.157.234</p>

<h2 style="font-weight: bold; font-size: 28px;">Instalação do Postman</h2>
<p style="font-size: 14px;">Para interagir com a API, você pode utilizar o Postman. Siga os passos abaixo para instalar e configurar:</p>

<h3 style="font-weight: bold; font-size: 20px;">Passo 1: Instalar o Postman</h3>
<ol style="font-size: 14px;">
<li>Acesse o site oficial do Postman: <a href="https://www.postman.com/downloads/">Postman Download</a>.</li>
<li>Baixe a versão mais adequada ao seu sistema operacional (Windows, macOS ou Linux).</li>
<li>Siga as instruções de instalação na tela.</li>
</ol>

<h3 style="font-weight: bold; font-size: 20px;">Passo 2: Importar as Requisições</h3>
<ol style="font-size: 14px;">
<li>Abra o Postman após a instalação.</li>
<li>No menu superior, clique em Import.</li>
<li>Selecione a opção de <b>Upload Files</b> e escolha o arquivo .json fornecido aqui (link para o arquivo de requisições).</li>
<li>Após a importação, todas as requisições da API estarão prontas para uso.</li>
</ol>

<h3 style="font-weight: bold; font-size: 20px;">Passo 3: Testar a API no Postman</h3>
<ol style="font-size: 14px;">
<li>Com o Postman aberto e as requisições já importadas, você verá uma lista de requisições à esquerda, no painel chamado <b>Collections</b>.</li>
<li>Expanda a coleção <b>Testes Animals AWS</b> clicando no ícone de seta ao lado do nome da coleção.</li>
<li>Clique em uma das requisições, como <b>Create Animal</b> (Criar Animal). Ao clicar, você verá os detalhes dessa requisição no painel central.</li>
<li>No painel <b>Body</b>, escolha a opção <b>form-data</b>. É aqui que você pode preencher os campos necessários para criar um animal.
<ul><li>Por exemplo, no campo type, preencha <b>true</b> se o animal for doméstico ou <b>false</b> se ele for selvagem.</li></ul></li>
<li>Após preencher todos os campos, clique no botão <b>Send</b>, localizado no canto superior direito.</li>
<li>O resultado será exibido no painel inferior, mostrando a resposta da API, que pode incluir o status da requisição e o conteúdo retornado, como o ID do animal criado.</li>
<li>Para testar outros endpoints, como listar animais ou adicionar uma comida a um animal, siga o mesmo processo, clicando na requisição desejada, preenchendo os campos necessários e clicando em <b>Send</b>.</li>
</ol>

<hr style="width: 80%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<h2 style="font-weight: bold; font-size: 28px;">Endpoints da Animals API</h2>

<ol>
<li style="font-weight: bold; font-size: 20px;">Criar Animal
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> POST</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/animal</li>
<li><b>Descrição:</b> Cria um novo animal com seus dados, incluindo upload de uma imagem local que será armazenada no bucket S3.</li>
<li><b>Parâmetros (form-data):</b></li>
    <li>type (texto): Define se o animal é doméstico (true) ou selvagem (false). Esta escolha será refletida no frontend.</li>
    <li>kind (texto): Exemplo: Suricato</li>
    <li>animalSpecies (texto): Exemplo: Suricatus Ganhadores</li>
    <li>age (texto): Idade do animal</li>
    <li>name (texto): Nome do animal</li>
    <li>sex (texto): Sexo do animal</li>
    <li>owner (texto): Nome do dono do animal</li>
    <li>author (texto): Exemplo: Gabriel Inácio</li>
    <li>habitat (texto): Exemplo: Selva</li>
    <li>image (arquivo): Upload de uma imagem do animal</li></ul></li>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Criar Comida
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> POST</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/food</li>
<li><b>Descrição:</b> Cria uma nova comida no sistema.</li>
<li><b>Body (JSON):</b></li></ul></li>
{
  <p>"name": "Carne",</p>
  <p>"whereToGet": "Mercado",</p>
  <p>"price": "15.00"</p>
}

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Adicionar Nova Comida a um Animal
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> POST</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/food/animal/{animalId}</li>
<li><b>Descrição:</b> Cria uma nova comida e a associa diretamente a um animal pelo seu animalId.</li>
<li><b>Body (JSON):</b></li></ul></li>
{
  <p>"name": "Sachê",</p>
  <p>"whereToGet": "Loja de animais",</p>
  <p>"price": "4.00"</p>
}

<ul><li style="font-weight: normal; font-size: 14px;"><b>Exemplo de</b> animalId: 9729cc46-338a-4b60-8be3-6c3eb1d1c146</li></ul>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Associar Comida Existente a um Animal
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> POST</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/food/addFoodToAnimal/{animalId}/{foodId}</li>
<li><b>Descrição:</b> Associa uma comida existente a um animal.</li>
<li><b>Parâmetros:</b></li>
    <li>animalId: ID do animal ao qual você quer associar a comida.</li>
    <li>foodId: ID da comida existente a ser associada.</li></ul></li>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Buscar Todos os Animais
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> GET</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/animal</li>
<li><b>Descrição:</b> Retorna a lista de todos os animais cadastrados no sistema.</li></ul></li>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Buscar Todas as Comidas
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> GET</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/food</li>
<li><b>Descrição:</b> Retorna a lista de todas as comidas cadastradas no sistema.</li></ul></li>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Buscar Animais com Filtros
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> GET</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/animal/filter?page=0&size=10&type=&kind=&animalSpecies=&age=&sex&author&habitat</li>
<li><b>Descrição:</b> Busca animais com filtros específicos. Os parâmetros opcionais incluem:</li>
    <li>page: Página da listagem (Exemplo: 0)</li>
    <li>size: Quantidade de resultados por página (Exemplo: 10)</li>
    <li>type: Tipo do animal</li>
    <li>kind: Espécie</li>
    <li>animalSpecies: Espécie do animal</li>
    <li>age: Idade</li>
    <li>sex: Sexo</li>
    <li>author: Autor</li>
    <li>habitat: Habitat do animal</li></ul></li>

<hr style="width: 60%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<li style="font-weight: bold; font-size: 20px;">Buscar Animal por ID
<ul style="font-weight: normal; font-size: 14px;"><li><b>Método:</b> GET</li>
<li><b>URL:</b> 54.207.157.234:8080/animals/animal/{animalId}</li>
<li><b>Descrição:</b> Retorna as informações detalhadas de um animal específico, baseado no animalId.</li>
<li><b>Exemplo de</b> animalId: c4c1ecd8-6644-4a54-9316-2b759bb16ccc</li></ul></li>
</ol>

<hr style="width: 80%; height: 3px; margin: 12px auto 8px auto; border-radius: 3px;">

<h2 style="font-weight: bold; font-size: 28px;">Deploy na AWS</h2>
<p style="font-size: 14px;">O deploy do <b>Animals API</b> foi feito utilizando uma instância EC2 da AWS para rodar a aplicação e um banco de dados RDS para armazenar as informações. O processo incluiu as seguintes etapas:</p>
<ul style="font-size: 14px;">
  <li><b>Configuração da Instância EC2:</b> A instância foi configurada para rodar o backend Java, utilizando Maven para build e execução. Foi criada uma regra no Security Group para permitir tráfego HTTP/HTTPS, possibilitando o acesso à API.</li>

  <li><b>Configuração do Bucket S3:</b> Um bucket S3 foi criado para armazenar as imagens dos animais. A API permite o upload das imagens via form-data, e o link público dessas imagens é armazenado no banco de dados RDS.</li>

  <li><b>Configuração do RDS:</b> O banco de dados RDS foi configurado para gerenciar as informações dos animais e das comidas. Ele está conectado à API para realizar as operações de CRUD e armazenamento dos links de imagem.</li>
</ul>
<p style="font-size: 14px;">O deploy exigiu a criação de permissões adequadas para que a EC2 tivesse acesso ao bucket S3 e ao RDS via IAM Role, garantindo que as operações de upload e armazenamento de dados fossem realizadas de forma segura e eficiente.</p>

<p style="font-size: 14px;">Este projeto está sendo preparado para o frontend, que está sendo desenvolvido pelo meu amigo <a href="https://github.com/guiisbarbosa">Guilherme Barbosa</a>. Fique atento para futuras atualizações!</p>
